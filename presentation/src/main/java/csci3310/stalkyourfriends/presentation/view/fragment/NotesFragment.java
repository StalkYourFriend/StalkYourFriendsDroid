package csci3310.stalkyourfriends.presentation.view.fragment;

import android.widget.ListView;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.presentation.R;
import csci3310.stalkyourfriends.presentation.presenter.BasePresenter;
import csci3310.stalkyourfriends.presentation.presenter.NotesPresenter;
import csci3310.stalkyourfriends.presentation.view.NotesView;
import csci3310.stalkyourfriends.presentation.view.activity.base.BaseActivity;
import csci3310.stalkyourfriends.presentation.view.adapter.NotesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class NotesFragment extends BaseFragment implements NotesView {

    @Inject
    NotesPresenter notesPresenter;

    @Bind(R.id.listview) ListView listView;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected BasePresenter presenter() {
        return this.notesPresenter;
    }

    public NotesPresenter getNotesPresenter() {
        return notesPresenter;
    }

    @Override
    public void showNotes(List<NoteEntity> notes) {
        NotesAdapter adapter = new NotesAdapter(getActivity());
        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onNoteItemClicked(NoteEntity note) {
                NotesFragment.this.showNote(note.getId());
            }
        });
        adapter.setNotes(notes);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_create_new_note)
    public void createNewNoteButtonPressed() {
        ((Listener)getActivity()).diplayNoteCreator();
    }

    @Override
    public void showNote(int noteId) {
        ((Listener)getActivity()).showNote(noteId);
    }

    @Override
    public void showExpirationWarning() {
        showMessage(getResources().getString(R.string.message_expiration) + ".\n" +
                    getResources().getString(R.string.message_update) + ".");
    }

    public interface Listener {
        void diplayNoteCreator();
        void showNote(int noteId);
    }

}
