package br.com.atlanticsolutions.mvpclean.ui.activity;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.ui.view.IBaseView;
import br.com.atlanticsolutions.mvpclean.utils.NetworkStatsUtil;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProgressDialog progressDialog;
    private Snackbar fixedSnackbar;

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getText(R.string.loading_message));
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnackMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_layout), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showNoConnectionSnackMessage() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_layout), getText(R.string.sem_conexao), Snackbar.LENGTH_LONG);
        TextView txtMessage = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        txtMessage.setTextColor(ContextCompat.getColor(this, R.color.no_connection_yellow));
        snackbar.show();
    }

    @Override
    public void showFixedSnackMessage(String message) {
        fixedSnackbar = Snackbar.make(findViewById(R.id.coordinator_layout), message, Snackbar.LENGTH_INDEFINITE);
        fixedSnackbar.show();
    }

    @Override
    public void hideFixedSnackMessage() {
        if(fixedSnackbar == null)
            return;
        fixedSnackbar.dismiss();
        fixedSnackbar = null;
    }

    /**
     * Requires Google Play Services API in Gradle
     * https://developers.google.com/android/guides/setup
     */
    public boolean isGooglePlayServicesAvailable() {
        return false;
        /*GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;*/
    }

    @Override
    public boolean isOnline() {
        return NetworkStatsUtil.isConnected(this);
    }
}
