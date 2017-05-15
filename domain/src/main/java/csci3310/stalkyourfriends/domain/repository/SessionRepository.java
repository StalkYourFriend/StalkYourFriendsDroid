package csci3310.stalkyourfriends.domain.repository;

import csci3310.stalkyourfriends.domain.entity.UserEntity;

public interface SessionRepository {
    UserEntity getCurrentUser();
    void setCurrentUser(UserEntity user);
    void invalidateSession();
}
