package csci3310.stalkyourfriends.presentation.view;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;

public interface NoteEditView extends BaseView {

    void showNote(NoteEntity note);
    int getNoteId();

}
