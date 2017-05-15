package csci3310.stalkyourfriends.presentation.view.fragment;

import android.widget.TextView;

import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.presenter.BasePresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteCreatePresenter;
import csci3310.stalkyourfriends.presentation.view.NoteCreateView;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;
import csci3310.stalkyourfriends.presentation.view.activity.base.CleanActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class NoteCreateFragment extends BaseFragment implements NoteCreateView {

    @Inject
    NoteCreatePresenter noteCreatePresenter;

    @Bind(R.id.et_title) TextView titleET;
    @Bind(R.id.et_content) TextView contentET;

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
        return this.noteCreatePresenter;
    }

    public NoteCreatePresenter getNoteCreatePresenter() {
        return noteCreatePresenter;
    }

    @OnClick(R.id.btn_submit)
    public void createButtonPressed() {
        this.noteCreatePresenter.createButtonPressed(   titleET.getText().toString(),
                                                        contentET.getText().toString());
    }

}
