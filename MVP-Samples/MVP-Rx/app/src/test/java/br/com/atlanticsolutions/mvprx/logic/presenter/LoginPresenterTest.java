package br.com.atlanticsolutions.mvprx.logic.presenter;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alessandro Valenza on 07/11/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class})
public class LoginPresenterTest{

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void checkShowErrorNoInternetConnection(){
        LoginPresenter.ILoginView view = mock(LoginPresenter.ILoginView.class);
        LoginPresenter presenter = new LoginPresenter(view);
        presenter.attemptToLogin("teste","teste");
        verify(view).showNoConnectionSnackMessage();
    }

    @Test
    public void checkShowWrongEmailMessage(){
        LoginPresenter.ILoginView view = mock(LoginPresenter.ILoginView.class);
        LoginPresenter presenter = new LoginPresenter(view);
        when(view.isOnline()).thenReturn(true);
        presenter.attemptToLogin("","teste");
        verify(view).showEmailFieldError(any(String.class));
    }

    @Test
    public void checkShowWrongPasswordMessage(){
        LoginPresenter.ILoginView view = mock(LoginPresenter.ILoginView.class);
        LoginPresenter presenter = new LoginPresenter(view);
        when(view.isOnline()).thenReturn(true);
        presenter.attemptToLogin("teste","");
        verify(view).showPasswordFieldError(any(String.class));
    }
}