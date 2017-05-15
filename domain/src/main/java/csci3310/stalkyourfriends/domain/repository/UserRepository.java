package csci3310.stalkyourfriends.domain.repository;

import csci3310.stalkyourfriends.domain.entity.MessageEntity;
import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VoidEntity;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<UserEntity> createUser(UserEntity user);
    Observable<VoidEntity> deleteUser(UserEntity user);
    Observable<MessageEntity> resetPassword(UserEntity user);

    Observable<UserEntity> loginUser(UserEntity user);
    Observable<VoidEntity> logoutUser(UserEntity user);
}
