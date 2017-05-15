package csci3310.stalkyourfriends.domain.interactor.user;

import csci3310.stalkyourfriends.domain.entity.MessageEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ResetPasswordUseCase extends UseCase<MessageEntity> {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private UserEntity user;

    @Inject
    public ResetPasswordUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(UserEntity user) {
        this.user = user;
    }

    @Override
    protected Observable<MessageEntity> buildUseCaseObservable() {
        if (this.user == null) this.user = sessionRepository.getCurrentUser();
        return this.userRepository.resetPassword(this.user);
    }
}
