package csci3310.stalkyourfriends.presentation.view.activity;

import android.os.Bundle;

import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;
import csci3310.stalkyourfriends.presentation.view.activity.base.CleanActivity;
import csci3310.stalkyourfriends.presentation.view.fragment.NoteCreateFragment;

public class NoteCreateActivity extends CleanActivity {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new NoteCreateFragment());
        }
    }

}
