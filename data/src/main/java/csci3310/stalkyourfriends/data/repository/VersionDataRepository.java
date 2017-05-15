package csci3310.stalkyourfriends.data.repository;

import csci3310.stalkyourfriends.data.net.RestApi;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VersionEntity;
import csci3310.stalkyourfriends.domain.repository.VersionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class VersionDataRepository extends RestApiRepository implements VersionRepository {

    private final RestApi restApi;

    @Inject
    public VersionDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<VersionEntity> checkVersionExpiration(UserEntity user) {
        return this.restApi.checkVersionExpiration(user.getAuthToken())
                .map(versionEntityResponse -> {
                    handleResponseError(versionEntityResponse);
                    return versionEntityResponse.body();
                });
    }

}
