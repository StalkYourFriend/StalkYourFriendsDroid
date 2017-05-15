package csci3310.stalkyourfriends.presentation;

import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.presenter.LoginPresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteCreatePresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteDetailPresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteEditPresenter;
import csci3310.stalkyourfriends.presentation.presenter.NotesPresenter;
import csci3310.stalkyourfriends.presentation.presenter.RegisterPresenter;
import csci3310.stalkyourfriends.presentation.presenter.ResetPasswordPresenter;
import csci3310.stalkyourfriends.presentation.presenter.SettingsPresenter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestMockerModule {

    @Provides @ActivityScope
    LoginPresenter provideLoginPresenter() {
        return Mockito.mock(LoginPresenter.class);
    }

    @Provides @ActivityScope
    NoteCreatePresenter provideNoteCreatePresenter() {
        return Mockito.mock(NoteCreatePresenter.class);
    }

    @Provides @ActivityScope
    NoteDetailPresenter provideNoteDetailPresenter() {
        return Mockito.mock(NoteDetailPresenter.class);
    }

    @Provides @ActivityScope
    NotesPresenter provideNotesPresenter() {
        return Mockito.mock(NotesPresenter.class);
    }

    @Provides @ActivityScope
    RegisterPresenter provideRegisterPresenter() {
        return Mockito.mock(RegisterPresenter.class);
    }

    @Provides @ActivityScope
    SettingsPresenter provideSettingsPresenter() {
        return Mockito.mock(SettingsPresenter.class);
    }

    @Provides @ActivityScope
    NoteEditPresenter provideNoteEditPresenter() {
        return Mockito.mock(NoteEditPresenter.class);
    }

    @Provides @ActivityScope
    ResetPasswordPresenter provideResetPasswordPresenter() {
        return Mockito.mock(ResetPasswordPresenter.class);
    }

}
