package br.com.atlanticsolutions.mvprx.logic.interactor;

import br.com.atlanticsolutions.mvprx.R;
import br.com.atlanticsolutions.mvprx.application.MvpApplication;
import br.com.atlanticsolutions.mvprx.logic.listeners.OnLoginFinishedListener;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.LoginRequest;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.LoginResponse;
import br.com.atlanticsolutions.mvprx.logic.rest.MvpApi;
import br.com.atlanticsolutions.mvprx.logic.rest.MvpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class LoginInteractor {
    public void postLogin(LoginRequest request, final OnLoginFinishedListener listener) {
        MvpApi mvpService = MvpService.createMvpService();

        mvpService.postLogin(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<LoginResponse>() {
                    @Override
                    public void call(LoginResponse loginResponse) {
                        if (loginResponse == null) {
                            listener.onLoginError(MvpApplication.getInstance().getString(R.string.error_request));
                        }
                        else if(loginResponse.getError() != null) {
                            listener.onLoginError(loginResponse.getError());
                        }
                    }
                })
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onLoginError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        listener.onLoginSuccess(loginResponse);
                    }
                });
    }
}
