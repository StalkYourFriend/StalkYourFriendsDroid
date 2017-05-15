package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.data.net.error.RestApiErrorException;
import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.interactor.note.CreateNoteUseCase;
import csci3310.stalkyourfriends.presentation.view.NoteCreateView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class NoteCreatePresenterTest {

    @Mock CreateNoteUseCase createNoteUseCase;
    @Mock NoteCreateView mockNoteCreateView;
    @Mock Observable mockObservable;

    private NoteCreatePresenter noteCreatePresenter;
    private NoteCreatePresenter.NoteCreateSubscriber noteCreateSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.noteCreatePresenter = new NoteCreatePresenter(this.createNoteUseCase);
        this.noteCreatePresenter.initWithView(this.mockNoteCreateView);
        this.noteCreateSubscriber = this.noteCreatePresenter.new NoteCreateSubscriber();
    }

    @Test
    public void testDestroy() {

        this.noteCreatePresenter.destroy();

        verify(this.createNoteUseCase).unsubscribe();
        assertNull(this.noteCreatePresenter.noteCreateView);
        assertNull(this.noteCreatePresenter.view);
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.noteCreateSubscriber.onComplete();

        verify(this.mockNoteCreateView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.noteCreateSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNoteCreateView).hideLoader();
        verify(this.mockNoteCreateView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.noteCreateSubscriber.onNext(new NoteEntity(1, "", ""));

        verify(this.mockNoteCreateView).hideLoader();
        verify(this.mockNoteCreateView).close();
    }

    @Test
    public void testCreateNewNoteButtonPressed() {

        this.noteCreatePresenter.createButtonPressed("title", "content");

        verify(this.createNoteUseCase).setParams(any(NoteEntity.class));
        verify(this.createNoteUseCase).execute(any(NoteCreatePresenter.NoteCreateSubscriber.class));
    }

}
