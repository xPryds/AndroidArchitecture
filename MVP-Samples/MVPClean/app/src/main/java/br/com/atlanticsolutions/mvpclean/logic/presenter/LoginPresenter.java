package br.com.atlanticsolutions.mvpclean.logic.presenter;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import java.util.ArrayList;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.application.MvpApplication;
import br.com.atlanticsolutions.mvpclean.logic.interactor.LoginInteractor;
import br.com.atlanticsolutions.mvpclean.logic.listeners.OnLoginFinishedListener;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginRequest;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginResponse;
import br.com.atlanticsolutions.mvpclean.ui.view.IBaseView;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class LoginPresenter implements IBasePresenter, OnLoginFinishedListener {
    private final MvpApplication application;
    private final ILoginView view;
    private final LoginInteractor interactor;

    public LoginPresenter(@NonNull ILoginView view) {
        this.view = checkNotNull(view, "View cannot be null");
        this.application = MvpApplication.getInstance();
        this.interactor = new LoginInteractor();
        this.view.setPresenter(this);
}

    @Override
    public void start() {
        view.initToolbar();
    }

    public void attemptToLogin(String email, String password) {
        if(!view.isOnline()){
            view.showNoConnectionSnackMessage();
            return;
        }

        LoginRequest request = new LoginRequest(email, password);
        ArrayList<Integer> validation = request.validate();

        if (!performValidation(validation)) {
            return;
        }

        view.showProgress();
        interactor.postLogin(request, this);
    }

    private boolean performValidation(ArrayList<Integer> validation) {
        if (validation != null) {
            for (int fieldName : validation) {
                switch (fieldName) {
                    case LoginRequest.FieldType.EMAIL:
                        view.showEmailFieldError();
                        break;
                    case LoginRequest.FieldType.PASSWORD:
                        view.showPasswordFieldError();
                        break;
                }
            }
            return false;
        }
        return true;
    }

    //region OnLoginFinishedListener
    @Override
    public void onLoginSuccess(LoginResponse response) {
        view.hideProgress();
        application.setLoginResponse(response);
        view.navigateToMainActivity();
    }

    @Override
    public void onLoginError(String error) {
        view.hideProgress();
        view.showToastMessage(error);
    }
    //endregion

    public interface ILoginView extends IBaseView {
        void setPresenter(LoginPresenter presenter);

        void initToolbar();

        void navigateToMainActivity();

        void showEmailFieldError();

        void showPasswordFieldError();
    }
}
