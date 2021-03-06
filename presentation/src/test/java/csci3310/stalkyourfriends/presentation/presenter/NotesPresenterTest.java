package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.data.net.error.RestApiErrorException;
import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.entity.VersionEntity;
import csci3310.stalkyourfriends.domain.interactor.CheckVersionExpirationUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.GetNotesUseCase;
import csci3310.stalkyourfriends.presentation.view.NotesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class NotesPresenterTest {

    @Mock GetNotesUseCase getNotesUseCase;
    @Mock CheckVersionExpirationUseCase checkVersionExpirationUseCase;
    @Mock NotesView mockNotesView;
    @Mock Observable mockObservable;

    private NotesPresenter notesPresenter;
    private NotesPresenter.NotesSubscriber notesSubscriber;
    private NotesPresenter.VersionExpirationSubscriber versionExpirationSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.notesPresenter = new NotesPresenter(this.getNotesUseCase,
                                                 this.checkVersionExpirationUseCase);
        this.notesPresenter.initWithView(this.mockNotesView);
        this.notesSubscriber = this.notesPresenter.new NotesSubscriber();
        this.versionExpirationSubscriber = this.notesPresenter.new VersionExpirationSubscriber();
    }

    @Test
    public void testDestroy() {

        this.notesPresenter.destroy();

        verify(this.getNotesUseCase).unsubscribe();
        verify(this.checkVersionExpirationUseCase).unsubscribe();
        assertNull(this.notesPresenter.notesView);
        assertNull(this.notesPresenter.view);
    }

    @Test
    public void testGetNotes() throws Exception {

        this.notesPresenter.resume();

        verify(this.mockNotesView).showLoader();
        verify(this.getNotesUseCase).execute(any(BasePresenter.BaseSubscriber.class));
        verify(this.checkVersionExpirationUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.notesSubscriber.onComplete();

        verify(this.mockNotesView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.notesSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.notesSubscriber.onNext(new ArrayList<NoteEntity>());

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showNotes(any(List.class));
    }

    @Test
    public void testVersionSubscriberOnNextWithDate() {
        VersionEntity versionEntity = new VersionEntity(VersionEntity.VERSION_WARNED);

        this.versionExpirationSubscriber.onNext(versionEntity);

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showExpirationWarning();
    }

    @Test
    public void testVersionSubscriberOnNextWithoutDate() {
        VersionEntity versionEntity = new VersionEntity();

        this.versionExpirationSubscriber.onNext(versionEntity);

        verify(this.mockNotesView).hideLoader();
        verifyNoMoreInteractions(this.mockNotesView);
    }

}
