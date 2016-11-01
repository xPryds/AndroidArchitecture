package br.com.atlanticsolutions.mvpclean.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.logic.presenter.LoginPresenter;
import br.com.atlanticsolutions.mvpclean.logic.presenter.LoginPresenter.ILoginView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.etEmail)
    protected EditText etEmail;
    @BindView(R.id.etPassword)
    protected EditText etPassword;

    private LoginPresenter presenter;

    //region Android Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new LoginPresenter(this);
    }
    //endregion

    //region ILoginView
    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
        this.presenter.start();
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showEmailFieldError(String errorMessage) {
        etEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordFieldError(String errorMessage) {
        etPassword.setError(errorMessage);
    }
    //endregion

    //region Button Actions
    @OnClick(R.id.btnLogin)
    public void onBtnLoginTouched() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        presenter.attemptToLogin(email, password);
    }
    //endregion
}
