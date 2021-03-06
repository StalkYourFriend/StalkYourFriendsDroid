package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.data.net.error.RestApiErrorException;
import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.interactor.note.DeleteNoteUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.GetNoteUseCase;
import csci3310.stalkyourfriends.domain.interactor.note.UpdateNoteUseCase;
import csci3310.stalkyourfriends.presentation.view.NoteEditView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class NoteEditPresenterTest {

    @Mock GetNoteUseCase getNoteUseCase;
    @Mock UpdateNoteUseCase updateNoteUseCase;
    @Mock DeleteNoteUseCase deleteNoteUseCase;
    @Mock NoteEditView mockNoteEditView;
    @Mock Observable mockObservable;

    private NoteEditPresenter noteEditPresenter;
    private NoteEditPresenter.GetNoteSubscriber getNoteSubscriber;
    private NoteEditPresenter.UpdateNoteSubscriber updateNoteSubscriber;
    private NoteEditPresenter.DeleteNoteSubscriber deleteNoteSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.noteEditPresenter = new NoteEditPresenter(this.updateNoteUseCase,
                                                       this.getNoteUseCase, this.deleteNoteUseCase);
        this.noteEditPresenter.initWithView(this.mockNoteEditView);
        this.getNoteSubscriber = this.noteEditPresenter.new GetNoteSubscriber();
        this.updateNoteSubscriber = this.noteEditPresenter.new UpdateNoteSubscriber();
        this.deleteNoteSubscriber = this.noteEditPresenter.new DeleteNoteSubscriber();
    }

    @Test
    public void testDestroy() {

        this.noteEditPresenter.destroy();

        verify(this.getNoteUseCase).unsubscribe();
        verify(this.updateNoteUseCase).unsubscribe();
        assertNull(this.noteEditPresenter.noteEditView);
        assertNull(this.noteEditPresenter.view);
    }

    @Test
    public void testGetNote() throws Exception {

        verify(this.mockNoteEditView).getNoteId();
        verify(this.mockNoteEditView).showLoader();
        verify(this.getNoteUseCase).setParams(any(int.class));
        verify(this.getNoteUseCase).execute(any(NoteEditPresenter.GetNoteSubscriber.class));
    }

    @Test
    public void testGetSubscriberOnCompleted() {

        this.getNoteSubscriber.onComplete();

        verify(this.mockNoteEditView).hideLoader();
    }

    @Test
    public void testGetSubscriberOnError() {

        this.getNoteSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetSubscriberOnNext() {

        this.getNoteSubscriber.onNext(new NoteEntity(1, "", ""));

        verify(this.mockNoteEditView).showLoader();
        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).showNote(any(NoteEntity.class));
    }

    @Test
    public void testUpdateNote() {

        this.noteEditPresenter.updateNote("", "");

        verify(this.mockNoteEditView, atLeast(1)).getNoteId();
        verify(this.mockNoteEditView, atLeast(1)).showLoader();
        verify(this.updateNoteUseCase).setParams(any(NoteEntity.class));
        verify(this.updateNoteUseCase).execute(any(NoteEditPresenter.UpdateNoteSubscriber.class));
    }

    @Test
    public void testUpdateSubscriberOnCompleted() {

        this.updateNoteSubscriber.onComplete();

        verify(this.mockNoteEditView).hideLoader();
    }

    @Test
    public void testUpdateSubscriberOnError() {

        this.updateNoteSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateSubscriberOnNext() {

        this.updateNoteSubscriber.onNext(new NoteEntity(1, "", ""));

        verify(this.mockNoteEditView).showLoader();
        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).close();
    }

    @Test
    public void testDeleteNoteButtonPressed() {

        this.noteEditPresenter.deleteNoteButtonPressed();

        verify(this.mockNoteEditView, atLeast(1)).getNoteId();
        verify(this.deleteNoteUseCase).setParams(anyInt());
        verify(this.deleteNoteUseCase).execute(any(NoteEditPresenter.DeleteNoteSubscriber.class));
    }

    @Test
    public void testDeleteSubscriberOnCompleted() {

        this.deleteNoteSubscriber.onComplete();

        verify(this.mockNoteEditView).hideLoader();
    }

    @Test
    public void testDeleteSubscriberOnError() {

        this.deleteNoteSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDeleteSubscriberOnNext() {

        this.deleteNoteSubscriber.onNext(null);

        verify(this.mockNoteEditView).hideLoader();
        verify(this.mockNoteEditView).close();
    }

}
