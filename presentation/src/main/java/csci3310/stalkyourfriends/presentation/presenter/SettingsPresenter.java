package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.domain.interactor.user.DeleteUserUseCase;
import csci3310.stalkyourfriends.domain.interactor.user.DoLogoutUseCase;
import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.SettingsView;

import javax.inject.Inject;

@ActivityScope
public class SettingsPresenter extends BasePresenter implements Presenter {

    private DoLogoutUseCase doLogoutUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    SettingsView settingsView;

    @Inject
    public SettingsPresenter(DoLogoutUseCase doLogoutUseCase, DeleteUserUseCase deleteUserUseCase) {
        super(doLogoutUseCase, deleteUserUseCase);
        this.doLogoutUseCase = doLogoutUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.settingsView = (SettingsView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.settingsView = null;
    }

    public void logoutUserButtonPressed() {
        this.doLogoutUseCase.execute(new BaseSubscriber<>());
        this.settingsView.closeAndDisplayLogin();
    }

    public void deleteAccountButtonPressed() {
        this.deleteUserUseCase.execute(new BaseSubscriber<>());
        this.settingsView.closeAndDisplayLogin();
    }

}
