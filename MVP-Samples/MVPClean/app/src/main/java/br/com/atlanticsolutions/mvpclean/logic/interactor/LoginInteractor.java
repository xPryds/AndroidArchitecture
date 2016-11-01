package br.com.atlanticsolutions.mvpclean.logic.interactor;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.application.MvpApplication;
import br.com.atlanticsolutions.mvpclean.logic.listeners.OnLoginFinishedListener;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginRequest;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginResponse;
import br.com.atlanticsolutions.mvpclean.logic.rest.MvpApi;
import br.com.atlanticsolutions.mvpclean.logic.rest.MvpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class LoginInteractor {
    public void postLogin(LoginRequest request, final OnLoginFinishedListener listener){
        MvpApi mvpService = MvpService.createMvpService();

        mvpService.postLogin(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()){
                    listener.onLoginError(MvpApplication.getInstance().getString(R.string.error_request));
                    return;
                }

                LoginResponse loginResponse = response.body();
                listener.onLoginSuccess(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onLoginError(t.getMessage());
            }
        });
    }
}
