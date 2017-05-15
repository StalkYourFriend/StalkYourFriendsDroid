package csci3310.stalkyourfriends.domain.repository;

import csci3310.stalkyourfriends.domain.entity.UserEntity;
import csci3310.stalkyourfriends.domain.entity.VersionEntity;

import io.reactivex.Observable;

public interface VersionRepository {
    Observable<VersionEntity> checkVersionExpiration(UserEntity user);
}
