package csci3310.stalkyourfriends.presentation.view.activity.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import csci3310.stalkyourfriends.data.net.error.RestApiErrorException;
import csci3310.stalkyourfriends.presentation.BaseApplication;
import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.dependency.component.FragmentInjector;
import csci3310.stalkyourfriends.presentation.view.BaseView;
import csci3310.stalkyourfriends.presentation.view.activity.LoginActivity;

public abstract class CleanActivity extends BaseActivity implements BaseView {

    private FragmentInjector fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }

    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((BaseApplication)getApplication()).getFragmentInjector();
        }
    }

    @Override
    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            switch (((RestApiErrorException) error).getStatusCode()) {
                case RestApiErrorException.UNAUTHORIZED:
                    closeAndDisplayLogin();
                    break;
                case RestApiErrorException.UPGRADE_REQUIRED:
                    createUpgradeDialog();
                    break;
                default:
                    showMessage(error.getMessage());
            }
        }
        else Toast.makeText(context(), getResources().getString(R.string.message_error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeAndDisplayLogin() {
        Intent notesIntent = new Intent(this, LoginActivity.class);
        notesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(notesIntent);
    }

    private void createUpgradeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.message_expired) + ".\n" +
                getResources().getString(R.string.message_update) + ".")
                .setPositiveButton(R.string.message_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToUpgrater();
                    }
                }).create().show();
    }

    private void navigateToUpgrater() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=csci3310.stalkyourfriends"));
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        this.finish();
    }

}
