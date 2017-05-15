package csci3310.stalkyourfriends.domain.interactor.user;

import csci3310.stalkyourfriends.domain.entity.VoidEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

public class DeleteUserUseCase extends UseCase<VoidEntity> {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    @Inject
    public DeleteUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable<VoidEntity> buildUseCaseObservable() {
        return this.userRepository.deleteUser(this.sessionRepository.getCurrentUser())
                .doOnComplete(new Action() {
                    @Override
                    public void run() {
                        sessionRepository.invalidateSession();
                    }
                });
    }
}
