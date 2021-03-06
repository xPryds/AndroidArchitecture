package br.com.atlanticsolutions.mvprx.logic.listeners;

import br.com.atlanticsolutions.mvprx.logic.model.pojo.LoginResponse;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public interface OnLoginFinishedListener {
    void onLoginSuccess(LoginResponse response);
    void onLoginError(String error);
}
