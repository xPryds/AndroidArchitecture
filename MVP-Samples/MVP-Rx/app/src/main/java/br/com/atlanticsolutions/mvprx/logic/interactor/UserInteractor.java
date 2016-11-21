package br.com.atlanticsolutions.mvprx.logic.interactor;

import br.com.atlanticsolutions.mvprx.R;
import br.com.atlanticsolutions.mvprx.application.MvpApplication;
import br.com.atlanticsolutions.mvprx.logic.listeners.OnUserListLoadedListener;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.ListUsersResponse;
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

public class UserInteractor {

    public void getListUsers(int page, final OnUserListLoadedListener listener){
        MvpApi mvpService = MvpService.createMvpService();
        mvpService.getUsers(page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<ListUsersResponse>() {
                    @Override
                    public void call(ListUsersResponse listUsersResponse) {
                        if (listUsersResponse == null || listUsersResponse.getUsers() == null) {
                            listener.onListUserError(MvpApplication.getInstance().getString(R.string.error_request));
                        }
                    }
                })
                .subscribe(new Subscriber<ListUsersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onListUserError(e.getMessage());
                    }

                    @Override
                    public void onNext(ListUsersResponse listUsersResponse) {
                        listener.onListUserSuccess(listUsersResponse);
                    }
                });

    }
}