package csci3310.stalkyourfriends.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.presenter.BasePresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteEditPresenter;
import csci3310.stalkyourfriends.presentation.view.NoteEditView;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class NoteEditFragment extends BaseFragment implements NoteEditView {

    @Inject
    NoteEditPresenter noteEditPresenter;

    @Bind(R.id.et_title) EditText titleET;
    @Bind(R.id.et_content) EditText contentET;
    @Bind(R.id.btn_submit) Button submitButton;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_create_edit;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteEditPresenter;
    }

    public NoteEditPresenter getNoteEditPresenter() {
        return noteEditPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        submitButton.setText(getActivity().getResources().getText(R.string.button_save));
        return view;
    }

    @Override
    public void showNote(NoteEntity note) {
        titleET.setText(note.getTitle());
        contentET.setText(note.getContent());
    }

    @OnClick(R.id.btn_submit)
    public void updateNoteButtonPressed() {
        this.noteEditPresenter.updateNote(  titleET.getText().toString(),
                                            contentET.getText().toString());
    }

    @Override
    public int getNoteId() {
        return ((Listener)getActivity()).getNoteId();
    }

    public interface Listener {
        int getNoteId();
    }

}
