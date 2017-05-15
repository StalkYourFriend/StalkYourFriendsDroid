package csci3310.stalkyourfriends.domain.interactor.note;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.NoteRepository;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class UpdateNoteUseCaseTest {

    private static final int FAKE_ID = 1;
    private static final String FAKE_TITLE = "MyTitle";
    private static final String FAKE_CONTENT = "MyContent";

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private NoteRepository mockNoteRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testUpdateNoteUseCaseSuccess() {
        NoteEntity note = new NoteEntity(FAKE_ID, FAKE_TITLE, FAKE_CONTENT);
        UpdateNoteUseCase updateNoteUseCase = new UpdateNoteUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockNoteRepository, mockSessionRepository);
        TestObserver<NoteEntity> testObserver = new TestObserver<>();
        given(mockNoteRepository.updateNote(any(UserEntity.class), eq(note)))
                .willReturn(Observable.just(note));

        updateNoteUseCase.setParams(note);
        updateNoteUseCase.buildUseCaseObservable().subscribe(testObserver);

        Assert.assertEquals(FAKE_TITLE,
                ((NoteEntity)(testObserver.getEvents().get(0)).get(0)).getTitle());
        Assert.assertEquals(FAKE_CONTENT,
                ((NoteEntity)(testObserver.getEvents().get(0)).get(0)).getContent());
        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockNoteRepository).updateNote(null, note);
        verifyNoMoreInteractions(mockNoteRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}