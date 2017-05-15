package csci3310.stalkyourfriends.domain.interactor.note;

import csci3310.stalkyourfriends.domain.entity.VoidEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.NoteRepository;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class DeleteNoteUseCase extends UseCase<VoidEntity> {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    private int noteId;

    @Inject
    public DeleteNoteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(int noteId) {
        this.noteId = noteId;
    }

    @Override
    protected Observable<VoidEntity> buildUseCaseObservable() {
        return this.noteRepository.deleteNote(this.sessionRepository.getCurrentUser(), this.noteId);
    }
}
