package csci3310.stalkyourfriends.data.repository;

import csci3310.stalkyourfriends.data.net.RestApi;
import csci3310.stalkyourfriends.data.net.wrapper.UserWrapper;
import csci3310.stalkyourfriends.domain.entity.MessageEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VoidEntity;
import csci3310.stalkyourfriends.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Response;

@Singleton
public class UserDataRepository extends RestApiRepository implements UserRepository {

    private final RestApi restApi;

    @Inject
    public UserDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<UserEntity> createUser(UserEntity user) {
        return this.restApi.createUser(new UserWrapper(user))
                .map(userEntityResponse -> {
                    handleResponseError(userEntityResponse);
                    return userEntityResponse.body();
                });
    }

    @Override
    public Observable<VoidEntity> deleteUser(final UserEntity user) {
        return this.restApi.deleteUser(user.getAuthToken())
                .map(voidResponse -> {
                    handleResponseError(voidResponse);
                    return new VoidEntity();
                });
    }

    @Override
    public Observable<MessageEntity> resetPassword(UserEntity user) {
        return this.restApi.resetPassword(user.getAuthToken(), new UserWrapper(user))
                .map(messageEntityResponse -> {
                    handleResponseError(messageEntityResponse);
                    return messageEntityResponse.body();
                });
    }

    @Override
    public Observable<UserEntity> loginUser(UserEntity user) {
        return this.restApi.doLogin(new UserWrapper(user))
                .map(userEntityResponse -> {
                    handleResponseError(userEntityResponse);
                    return userEntityResponse.body();
                });
    }

    @Override
    public Observable<VoidEntity> logoutUser(UserEntity user) {
        return this.restApi.doLogout(user.getAuthToken())
                .map(voidResponse -> {
                    handleResponseError(voidResponse);
                    return new VoidEntity();
                });
    }
}
