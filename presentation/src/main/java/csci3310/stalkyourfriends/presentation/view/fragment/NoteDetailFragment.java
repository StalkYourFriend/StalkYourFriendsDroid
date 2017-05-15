package csci3310.stalkyourfriends.presentation.view.fragment;

import android.widget.TextView;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.presenter.BasePresenter;
import csci3310.stalkyourfriends.presentation.presenter.NoteDetailPresenter;
import csci3310.stalkyourfriends.presentation.view.NoteDetailView;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;

public class NoteDetailFragment extends BaseFragment implements NoteDetailView {

    @Inject
    NoteDetailPresenter noteDetailPresenter;

    @Bind(R.id.tv_title) TextView titleTV;
    @Bind(R.id.tv_content) TextView contentTV;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_detail;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteDetailPresenter;
    }

    public NoteDetailPresenter getNoteDetailPresenter() {
        return noteDetailPresenter;
    }

    @Override
    public void showNote(NoteEntity note) {
        titleTV.setText(note.getTitle());
        contentTV.setText(note.getContent());
    }

    @Override
    public int getNoteId() {
        return ((Listener)getActivity()).getNoteId();
    }

    public interface Listener {
        int getNoteId();
    }

}
