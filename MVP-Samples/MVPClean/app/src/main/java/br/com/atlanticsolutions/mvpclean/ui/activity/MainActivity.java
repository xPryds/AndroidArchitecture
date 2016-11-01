package br.com.atlanticsolutions.mvpclean.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.User;
import br.com.atlanticsolutions.mvpclean.logic.presenter.MainPresenter;
import br.com.atlanticsolutions.mvpclean.ui.adapters.UserListAdapter;
import br.com.atlanticsolutions.mvpclean.utils.RecyclerItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainPresenter.IMainView{

    @BindView(R.id.rvUsers)
    protected RecyclerView rvUsers;
    @BindView(R.id.btnTryAgain)
    protected Button btnTryAgain;
    @BindView(R.id.swipeLayout)
    protected SwipeRefreshLayout swipeLayout;

    private MainPresenter presenter;

    //region Android Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new MainPresenter(this);
    }
    //endregion

    //Region IMainLogin
    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
        this.presenter.start();
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_main);
    }

    @Override
    public void setupRecyclerView() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvUsers.setHasFixedSize(true);
        rvUsers.setAdapter(new UserListAdapter(this, new ArrayList<User>()));
        rvUsers.addOnItemTouchListener(new RecyclerItemClickListener(this, rvUsers, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void populateRecyclerView(ArrayList<User> users) {
        ((UserListAdapter)rvUsers.getAdapter()).replaceAll(users);
    }

    @Override
    public void clearList() {
        ((UserListAdapter)rvUsers.getAdapter()).replaceAll(new ArrayList<User>());
    }

    @Override
    public void showTryAgainButton() {
        btnTryAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTryAgainButton() {
        btnTryAgain.setVisibility(View.GONE);
    }

    @Override
    public void setupRefreshLayout() {
        swipeLayout.setOnRefreshListener(presenter);
    }

    @Override
    public void hideSwipeRefreshProgress() {
        swipeLayout.setRefreshing(false);
    }
    //endregion

    //region Button Actions
    @OnClick(R.id.btnTryAgain)
    protected void onBtnTryAgainTouched (){
        presenter.attemptToLoadList();
    }
    //endregion
}
