package edu.xda.abc.presenters.impl;

import edu.xda.abc.models.ResponseLoginModel;
import edu.xda.abc.models.ResponseUserRegisterModel;
import edu.xda.abc.models.UserModel;
import edu.xda.abc.presenters.UserPresenter;
import edu.xda.abc.utils.Utils;
import edu.xda.abc.views.UserRegisterView;
import edu.xda.abc.views.UserView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PSUserPresenter implements UserPresenter {
    private UserView userView;
    private UserRegisterView userRegisterView;

    public PSUserPresenter(UserView userView,UserRegisterView userRegisterView) {
        this.userView = userView;
        this.userRegisterView=userRegisterView;
    }

    @Override
    public void checkLogin(String username, String password) {
        userView.loading();
        Utils.getInstance()
                .checkLogin(username,password)
                .enqueue(new Callback<ResponseLoginModel>() {
                    @Override
                    public void onResponse(Call<ResponseLoginModel> call, Response<ResponseLoginModel> response) {
                        if (response.isSuccessful() && response.body()!=null){
                            userView.hideLoading();
                            if(response.body().getErr())
                                userView.loginFailure(response.body().getMessage());
                            else
                                userView.loginSuccess(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLoginModel> call, Throwable t) {
                        userView.hideLoading();
                        userView.onError(t.getLocalizedMessage());
                    }
                });
    }

    //Register
    public void registerUser(UserModel userModel){
        userView.loading();
        Utils.getInstance()
                .saveUser(
                        userModel.getFullname()
                        ,userModel.getGmail()
                        ,userModel.getUserpassword())
                .enqueue(new Callback<ResponseUserRegisterModel>() {
                    @Override
                    public void onResponse(Call<ResponseUserRegisterModel> call, Response<ResponseUserRegisterModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            userView.hideLoading();
                            if (response.body().getStatus() != null) {
                                userRegisterView.registerSuccess(response.body().getStatus());
                            } else
                                userRegisterView.registerFailure("Tên đăng nhập đã tồn tại!");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUserRegisterModel> call, Throwable t) {
                        userView.hideLoading();
                        userRegisterView.registerFailure("Đăng ký thất bại!");
                    }


                });

    }
}
