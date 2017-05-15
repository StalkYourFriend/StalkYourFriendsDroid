package csci3310.stalkyourfriends.presentation.dependency.component;

import android.content.Context;
import android.content.SharedPreferences;

import csci3310.stalkyourfriends.data.net.RestApi;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.NoteRepository;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;
import csci3310.stalkyourfriends.domain.repository.VersionRepository;
import csci3310.stalkyourfriends.presentation.dependency.module.ApplicationModule;
import csci3310.stalkyourfriends.presentation.dependency.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    Context context();
    SharedPreferences sharedPreferences();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    SessionRepository sessionRepository();
    RestApi restApi();
    UserRepository userRepository();
    NoteRepository noteRepository();
    VersionRepository versionRepository();

}
