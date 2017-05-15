package csci3310.stalkyourfriends.data.net.wrapper;

import csci3310.stalkyourfriends.domain.entity.UserEntity;

public class UserWrapper {

    private UserEntity user;

    public UserWrapper(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
