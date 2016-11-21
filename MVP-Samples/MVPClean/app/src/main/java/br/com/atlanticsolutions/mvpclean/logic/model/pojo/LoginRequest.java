package br.com.atlanticsolutions.mvpclean.logic.model.pojo;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */
public class LoginRequest {
    private String email, password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ArrayList<Integer> validate() {
        ArrayList<Integer> wrongFields = new ArrayList<>();
        if(TextUtils.isEmpty(email)){
            wrongFields.add(FieldType.EMAIL);
        }
        if(TextUtils.isEmpty(password)){
            wrongFields.add(FieldType.PASSWORD);
        }

        return wrongFields.size() > 0 ? wrongFields : null;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FieldType.EMAIL, FieldType.PASSWORD})
    public @interface FieldType{
        int EMAIL = 1;
        int PASSWORD = 2;
    }
}
