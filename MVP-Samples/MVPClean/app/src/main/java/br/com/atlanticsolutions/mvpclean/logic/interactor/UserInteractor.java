package br.com.atlanticsolutions.mvpclean.logic.interactor;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.application.MvpApplication;
import br.com.atlanticsolutions.mvpclean.application.MvpConstants;
import br.com.atlanticsolutions.mvpclean.logic.listeners.OnUserListLoadedListener;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.ListUsersResponse;
import br.com.atlanticsolutions.mvpclean.logic.rest.MvpApi;
import br.com.atlanticsolutions.mvpclean.logic.rest.MvpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class UserInteractor {

    public void getListUsers(int page, final OnUserListLoadedListener listener){
        MvpApi mvpService = MvpService.createMvpService();
        mvpService.getUsers(page)
                .enqueue(new Callback<ListUsersResponse>() {
            @Override
            public void onResponse(Call<ListUsersResponse> call, Response<ListUsersResponse> response) {
                int responseCode = response.code();
                if(responseCode != MvpConstants.REQUEST_CODE_SUCCESS){
                    listener.onListUserError(MvpApplication.getInstance().getString(R.string.error_request));
                    return;
                }

                ListUsersResponse listUsers = response.body();
                listener.onListUserSuccess(listUsers);
            }

            @Override
            public void onFailure(Call<ListUsersResponse> call, Throwable t) {
                listener.onListUserError(t.getMessage());
            }
        });
    }
}
