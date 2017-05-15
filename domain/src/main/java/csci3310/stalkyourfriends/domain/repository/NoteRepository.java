package csci3310.stalkyourfriends.domain.repository;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VoidEntity;

import java.util.List;

import io.reactivex.Observable;

public interface NoteRepository {
    Observable<NoteEntity> createNote(UserEntity user, NoteEntity note);
    Observable<NoteEntity> getNote(UserEntity user, int noteId);
    Observable<List<NoteEntity>> getNotes(UserEntity user);
    Observable<NoteEntity> updateNote(UserEntity user, NoteEntity note);
    Observable<VoidEntity> deleteNote(UserEntity user, int noteId);
}
