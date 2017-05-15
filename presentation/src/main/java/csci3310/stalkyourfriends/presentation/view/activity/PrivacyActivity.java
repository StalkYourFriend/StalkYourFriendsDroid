package csci3310.stalkyourfriends.presentation.view.activity;

import csci3310.stalkyourfriends.data.net.RestApi;
import csci3310.stalkyourfriends.presentation.view.activity.base.WebViewActivity;

public class PrivacyActivity extends WebViewActivity {

    private static final String TERMS_URL = RestApi.URL_BASE + "/privacy";

    @Override
    public String getUrl() {
        return TERMS_URL;
    }

}
