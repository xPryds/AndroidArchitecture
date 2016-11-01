package br.com.atlanticsolutions.mvpclean.application;

import android.app.Application;

import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginResponse;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */
public class MvpApplication extends Application{
    private static MvpApplication ourInstance;
    private LoginResponse loginResponse;

    public static MvpApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public void setLoginResponse(LoginResponse response) {
        this.loginResponse = response;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }
}
