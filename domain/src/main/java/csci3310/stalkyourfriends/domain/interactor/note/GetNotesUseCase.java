package csci3310.stalkyourfriends.domain.interactor.note;

import csci3310.stalkyourfriends.domain.entity.NoteEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.NoteRepository;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNotesUseCase extends UseCase<List<NoteEntity>> {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    @Inject
    public GetNotesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                           NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable<List<NoteEntity>> buildUseCaseObservable() {
        return this.noteRepository.getNotes(this.sessionRepository.getCurrentUser());
    }
}
