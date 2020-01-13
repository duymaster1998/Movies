package edu.xda.abc.views;

import edu.xda.abc.models.ResponseLoginModel;

public interface UserView {
    void loginFailure(String message);
    void loginSuccess(ResponseLoginModel model);
    void onError(String err);
    void loading();
    void hideLoading();
}
