package br.com.atlanticsolutions.mvprx.logic.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import br.com.atlanticsolutions.mvprx.logic.interactor.UserInteractor;
import br.com.atlanticsolutions.mvprx.logic.listeners.OnUserListLoadedListener;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.ListUsersResponse;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.User;
import br.com.atlanticsolutions.mvprx.ui.view.IBaseView;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */
public class MainPresenter implements IBasePresenter, OnUserListLoadedListener, SwipeRefreshLayout.OnRefreshListener {
    private final UserInteractor interactor;
    private final IMainView view;
    private ListUsersResponse listUsers;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.interactor = new UserInteractor();
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initToolbar();
        view.setupRefreshLayout();
        view.setupRecyclerView();
        attemptToLoadList();
    }

    public void attemptToLoadList() {
        view.clearList();
        if(!view.isOnline()){
            view.showNoConnectionSnackMessage();
            view.hideSwipeRefreshProgress();
            view.showTryAgainButton();
            return;
        }
        view.hideTryAgainButton();
        view.showProgress();
        interactor.getListUsers(0,this);
    }

    //region OnUserListLoadedListener
    @Override
    public void onListUserSuccess(ListUsersResponse listUsers) {
        this.listUsers = listUsers;
        view.populateRecyclerView(listUsers.getUsers());
        view.hideProgress();
        view.hideSwipeRefreshProgress();
    }

    @Override
    public void onListUserError(String message) {
        view.hideProgress();
        view.hideSwipeRefreshProgress();
        view.showToastMessage(message);
        view.showTryAgainButton();
    }
    //endregion

    //region SwipeRefreshLayout.OnRefreshListener
    @Override
    public void onRefresh() {
        attemptToLoadList();
    }
    //endregion

    public interface IMainView extends IBaseView{
        void setPresenter(MainPresenter presenter);

        void initToolbar();

        void setupRecyclerView();

        void populateRecyclerView(ArrayList<User> users);

        void clearList();

        void showTryAgainButton();

        void hideTryAgainButton();

        void setupRefreshLayout();

        void hideSwipeRefreshProgress();
    }
}