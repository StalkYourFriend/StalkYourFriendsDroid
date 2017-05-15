package csci3310.stalkyourfriends.domain.interactor;

import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VersionEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.VersionRepository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CheckVersionExpirationUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private VersionRepository mockVersionRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testGetNotesUseCaseSuccess() {
        CheckVersionExpirationUseCase checkVersionExpirationUseCase =
                new CheckVersionExpirationUseCase(mockThreadExecutor, mockPostExecutionThread,
                                                mockVersionRepository, mockSessionRepository);
        given(mockVersionRepository.checkVersionExpiration(any(UserEntity.class)))
                .willReturn(Observable.just(new VersionEntity("01/01/2001")));
        TestObserver<VersionEntity> testObserver = new TestObserver<>();

        checkVersionExpirationUseCase.buildUseCaseObservable().subscribe(testObserver);

        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockVersionRepository).checkVersionExpiration(null);
        Assert.assertEquals("01/01/2001",
                ((VersionEntity)(testObserver.getEvents().get(0)).get(0)).getState());
        verifyNoMoreInteractions(mockVersionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}