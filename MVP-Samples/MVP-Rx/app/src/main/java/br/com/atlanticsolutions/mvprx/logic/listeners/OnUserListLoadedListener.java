package br.com.atlanticsolutions.mvprx.logic.listeners;

import br.com.atlanticsolutions.mvprx.logic.model.pojo.ListUsersResponse;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public interface OnUserListLoadedListener {
    void onListUserSuccess(ListUsersResponse listUsers);
    void onListUserError(String message);
}
