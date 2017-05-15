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

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CreateNoteUseCaseTest {

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
    public void testCreateNoteUseCaseSuccess() {
        NoteEntity note = new NoteEntity(FAKE_ID, FAKE_TITLE, FAKE_CONTENT);
        CreateNoteUseCase createNoteUseCase = new CreateNoteUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockNoteRepository, mockSessionRepository);
        given(mockNoteRepository.createNote(any(UserEntity.class), eq(note)))
                .willReturn(Observable.just(note));
        TestObserver<NoteEntity> testObserver = new TestObserver<>();

        createNoteUseCase.setParams(note);
        createNoteUseCase.buildUseCaseObservable().subscribeWith(testObserver);

        Assert.assertEquals(FAKE_TITLE,
                ((NoteEntity)(testObserver.getEvents().get(0)).get(0)).getTitle());
        Assert.assertEquals(FAKE_CONTENT,
                ((NoteEntity)(testObserver.getEvents().get(0)).get(0)).getContent());
        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockNoteRepository).createNote(null, note);
        verifyNoMoreInteractions(mockNoteRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}