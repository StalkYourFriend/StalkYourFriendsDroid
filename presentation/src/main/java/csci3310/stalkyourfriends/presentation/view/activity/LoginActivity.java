package csci3310.stalkyourfriends.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.view.activity.base.CleanActivity;
import csci3310.stalkyourfriends.presentation.view.fragment.LoginFragment;

public class LoginActivity extends CleanActivity implements LoginFragment.Listener {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new LoginFragment());
        }
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }

    @Override
    public void viewNotes() {
        Intent notesIntent = new Intent(this, MainActivity.class);
        notesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(notesIntent);
    }

    @Override
    public void displayRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void forgotPassword() {
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }

}
