package csci3310.stalkyourfriends.presentation.view.fragment;

import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.presenter.BasePresenter;
import csci3310.stalkyourfriends.presentation.presenter.SettingsPresenter;
import csci3310.stalkyourfriends.presentation.view.SettingsView;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsView {

    @Inject SettingsPresenter settingsPresenter;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected BasePresenter presenter() {
        return this.settingsPresenter;
    }

    public SettingsPresenter getSettingsPresenter() {
        return settingsPresenter;
    }

    @OnClick(R.id.tv_logout)
    public void logoutButtonPressed() {
        this.settingsPresenter.logoutUserButtonPressed();
    }

    @OnClick(R.id.tv_delete_account)
    public void deleteAccountButtonPressed() {
        this.settingsPresenter.deleteAccountButtonPressed();
    }

    @OnClick(R.id.tv_terms)
    public void termsButtonPressed() {
        ((Listener)getActivity()).showTerms();
    }

    @OnClick(R.id.tv_privacy)
    public void privacyButtonPressed() {
        ((Listener)getActivity()).showPrivacy();
    }

    public interface Listener {
        void showTerms();
        void showPrivacy();
    }
}
