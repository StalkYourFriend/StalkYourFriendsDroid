package csci3310.stalkyourfriends.domain.interactor.user;

import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.interactor.UseCase;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class DoLoginUseCase extends UseCase<UserEntity> {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private UserEntity user;

    @Inject
    public DoLoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                          UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(UserEntity user) {
        this.user = user;
    }

    @Override
    protected Observable<UserEntity> buildUseCaseObservable() {
        return this.userRepository.loginUser(this.user)
                .doOnNext(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        sessionRepository.setCurrentUser(userEntity);
                    }
                });
    }
}
