package csci3310.stalkyourfriends.domain.interactor.user;

import csci3310.stalkyourfriends.domain.entity.MessageEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;
import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class ResetPasswordUseCaseTest {

    private static final String FAKE_MSG = "message";

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;
    @Mock private SessionRepository mockSessionRepository;
    @Mock private UserEntity mockUser;

    private TestObserver<MessageEntity> testObserver;
    private ResetPasswordUseCase resetPasswordUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.testObserver = new TestObserver<>();
        this.resetPasswordUseCase = new ResetPasswordUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockUserRepository, mockSessionRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testResetPasswordWithoutLoggedUseCase() {
        given(this.mockUserRepository.resetPassword(this.mockUser))
                .willReturn(Observable.just(new MessageEntity(FAKE_MSG)));

        this.resetPasswordUseCase.setParams(this.mockUser);
        this.resetPasswordUseCase.buildUseCaseObservable()
                .subscribe(this.testObserver);

        verify(this.mockUserRepository).resetPassword(this.mockUser);
        Assert.assertEquals(FAKE_MSG,
                ((MessageEntity)(testObserver.getEvents().get(0)).get(0)).getMessage());
        verifyNoMoreInteractions(this.mockUserRepository);
        verifyZeroInteractions(this.mockSessionRepository);
        verifyZeroInteractions(this.mockThreadExecutor);
        verifyZeroInteractions(this.mockPostExecutionThread);
    }

    @Test
    public void testResetPasswordWhenLoggedUseCase() {
        given(this.mockSessionRepository.getCurrentUser())
                .willReturn(this.mockUser);
        given(this.mockUserRepository.resetPassword(this.mockUser))
                .willReturn(Observable.just(new MessageEntity(FAKE_MSG)));

        this.resetPasswordUseCase.buildUseCaseObservable().subscribe(this.testObserver);

        verify(this.mockUserRepository).resetPassword(this.mockUser);
        MessageEntity responseMessage = (MessageEntity) (testObserver.getEvents().get(0)).get(0);
        Assert.assertEquals(FAKE_MSG, responseMessage.getMessage());
        verifyNoMoreInteractions(this.mockUserRepository);
        verify(this.mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(this.mockSessionRepository);
        verifyZeroInteractions(this.mockThreadExecutor);
        verifyZeroInteractions(this.mockPostExecutionThread);
    }

}