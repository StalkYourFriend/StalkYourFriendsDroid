package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.data.net.error.RestApiErrorException;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.interactor.user.CreateUserUseCase;
import csci3310.stalkyourfriends.presentation.view.RegisterView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class RegisterPresenterTest {

    @Mock CreateUserUseCase mockCreateUserUseCase;
    @Mock RegisterView mockRegisterView;
    @Mock Observable mockObservable;

    private RegisterPresenter registerPresenter;
    private RegisterPresenter.RegisterSubscriber registerSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.registerPresenter = new RegisterPresenter(this.mockCreateUserUseCase);
        this.registerPresenter.initWithView(this.mockRegisterView);
        this.registerSubscriber = this.registerPresenter.new RegisterSubscriber();
    }

    @Test
    public void testDestroy() {

        this.registerPresenter.destroy();

        verify(this.mockCreateUserUseCase).unsubscribe();
        assertNull(this.registerPresenter.registerView);
        assertNull(this.registerPresenter.view);
    }

    @Test
    public void testRegisterUser() throws Exception {

        this.registerPresenter.registerUser("email", "password", "password");

        verify(this.mockRegisterView).showLoader();
        verify(this.mockCreateUserUseCase).setParams(any(UserEntity.class));
        verify(this.mockCreateUserUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.registerSubscriber.onComplete();

        verify(this.mockRegisterView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.registerSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockRegisterView).hideLoader();
        verify(this.mockRegisterView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.registerSubscriber.onNext(new UserEntity("email"));

        verify(this.mockRegisterView).hideLoader();
        verify(this.mockRegisterView).viewNotes();
    }

}
