package csci3310.stalkyourfriends.domain.interactor.note;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.NoteRepository;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class CreateNoteUseCase extends UseCase<NoteEntity> {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    private NoteEntity note;

    @Inject
    public CreateNoteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(NoteEntity note) {
        this.note = note;
    }

    @Override
    protected Observable<NoteEntity> buildUseCaseObservable() {
        return this.noteRepository.createNote(this.sessionRepository.getCurrentUser(), this.note);
    }
}
