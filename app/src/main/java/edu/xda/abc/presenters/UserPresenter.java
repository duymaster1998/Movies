package edu.xda.abc.presenters;

import edu.xda.abc.models.UserModel;

public interface UserPresenter {
    void checkLogin(String username, String password);
    void registerUser(UserModel userModel);
}
