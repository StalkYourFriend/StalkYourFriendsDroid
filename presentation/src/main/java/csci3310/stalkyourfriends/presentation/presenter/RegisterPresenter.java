package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.interactor.user.CreateUserUseCase;
import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.RegisterView;

import javax.inject.Inject;

@ActivityScope
public class RegisterPresenter extends BasePresenter implements Presenter {

    private CreateUserUseCase createUserUseCase;
    RegisterView registerView;

    @Inject
    public RegisterPresenter(CreateUserUseCase createUserUseCase) {
        super(createUserUseCase);
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.registerView = (RegisterView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.registerView = null;
    }

    public void registerUser(String email, String password, String passwordConfirmation) {
        UserEntity user = new UserEntity(email);
        user.setPassword(password);
        user.setPasswordConfirmation(passwordConfirmation);

        this.showLoader();
        this.createUserUseCase.setParams(user);
        this.createUserUseCase.execute(new RegisterSubscriber());
    }

    protected class RegisterSubscriber extends BaseSubscriber<UserEntity> {

        @Override
        public void onNext(UserEntity user) {
            RegisterPresenter.this.hideLoader();
            RegisterPresenter.this.registerView.viewNotes();
        }

    }

}
