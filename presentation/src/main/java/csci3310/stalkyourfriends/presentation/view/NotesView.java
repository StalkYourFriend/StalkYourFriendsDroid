package csci3310.stalkyourfriends.presentation.view;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;

import java.util.List;

public interface NotesView extends BaseView {

    void showNotes(List<NoteEntity> notes);
    void showNote(int noteId);
    void showExpirationWarning();

}
