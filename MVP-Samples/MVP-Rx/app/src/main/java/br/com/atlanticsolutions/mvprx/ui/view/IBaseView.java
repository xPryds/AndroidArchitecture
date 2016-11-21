package br.com.atlanticsolutions.mvprx.ui.view;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */

public interface IBaseView {

    void showProgress();

    void hideProgress();

    void showToastMessage(String message);

    void showSnackMessage(String message);

    void showNoConnectionSnackMessage();

    void showFixedSnackMessage(String message);

    void hideFixedSnackMessage();

    boolean isGooglePlayServicesAvailable();

    boolean isOnline();

    String getResourceString(int resourceId);
}
