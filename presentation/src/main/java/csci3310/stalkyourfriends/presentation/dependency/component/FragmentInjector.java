package csci3310.stalkyourfriends.presentation.dependency.component;

import csci3310.stalkyourfriends.presentation.view.fragment.LoginFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.NoteCreateFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.NoteDetailFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.NoteEditFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.NotesFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.RegisterFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.ResetPasswordFragment;
import csci3310.stalkyourfriends.presentation.view.fragment.SettingsFragment;

public interface FragmentInjector {

    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(NotesFragment notesFragment);
    void inject(NoteDetailFragment noteDetailFragment);
    void inject(NoteCreateFragment noteCreateFragment);
    void inject(NoteEditFragment noteEditFragment);
    void inject(SettingsFragment settingsFragment);
    void inject(ResetPasswordFragment resetPasswordFragment);

}
