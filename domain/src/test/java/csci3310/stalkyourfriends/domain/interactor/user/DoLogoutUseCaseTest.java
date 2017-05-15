package csci3310.stalkyourfriends.domain.interactor.user;

import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DoLogoutUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testDoLogoutUseCaseSuccess() {
        DoLogoutUseCase doLogoutUseCase = new DoLogoutUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockUserRepository, mockSessionRepository);

        doLogoutUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verify(mockSessionRepository).invalidateSession();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockUserRepository).logoutUser(null);
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}