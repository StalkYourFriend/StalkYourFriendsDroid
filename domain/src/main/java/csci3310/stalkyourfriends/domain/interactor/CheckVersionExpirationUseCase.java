package csci3310.stalkyourfriends.domain.interactor;

import csci3310.stalkyourfriends.domain.entity.VersionEntity;
import csci3310.stalkyourfriends.domain.executor.PostExecutionThread;
import csci3310.stalkyourfriends.domain.executor.ThreadExecutor;
import csci3310.stalkyourfriends.domain.repository.SessionRepository;
import csci3310.stalkyourfriends.domain.repository.VersionRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckVersionExpirationUseCase extends UseCase<VersionEntity> {

    private VersionRepository versionRepository;
    private SessionRepository sessionRepository;

    @Inject
    public CheckVersionExpirationUseCase(ThreadExecutor threadExecutor,
                                         PostExecutionThread postExecutionThread,
                                         VersionRepository versionRepository,
                                         SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.versionRepository = versionRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable<VersionEntity> buildUseCaseObservable() {
        return this.versionRepository
                                .checkVersionExpiration(this.sessionRepository.getCurrentUser());
    }
}
