package csci3310.stalkyourfriends.presentation.presenter;

import csci3310.stalkyourfriends.presentation.view.BaseView;

public interface Presenter {

    void initWithView(BaseView view);
    void resume();
    void pause();
    void destroy();

}
