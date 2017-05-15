package csci3310.stalkyourfriends.presentation.view.activity.base;

import android.os.Bundle;

import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;
import csci3310.stalkyourfriends.presentation.view.fragment.WebViewFragment;

public abstract class WebViewActivity extends BaseActivity implements WebViewFragment.Listener {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new WebViewFragment());
        }
    }

}
