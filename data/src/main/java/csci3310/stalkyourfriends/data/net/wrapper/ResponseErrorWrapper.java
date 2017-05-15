package csci3310.stalkyourfriends.data.net.wrapper;

import csci3310.stalkyourfriends.data.net.error.ResponseErrorEntity;

public class ResponseErrorWrapper {

    private ResponseErrorEntity error;

    public ResponseErrorWrapper(ResponseErrorEntity error) {
        this.error = error;
    }

    public ResponseErrorEntity getError() {
        return error;
    }

    public void setError(ResponseErrorEntity error) {
        this.error = error;
    }
}
