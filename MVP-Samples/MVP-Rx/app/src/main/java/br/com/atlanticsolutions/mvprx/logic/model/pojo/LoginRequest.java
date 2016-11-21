package br.com.atlanticsolutions.mvprx.logic.model.pojo;

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

    public Pair<String, ArrayList<String>> validate() {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> wrongFields = new ArrayList<>();
        if(TextUtils.isEmpty(email)){
            builder.append("\n")
                    .append(FieldType.EMAIL);
            wrongFields.add(FieldType.EMAIL);
        }
        if(TextUtils.isEmpty(password)){
            builder.append("\n")
                    .append(FieldType.PASSWORD);
            wrongFields.add(FieldType.PASSWORD);
        }
        if (builder.length() > 0) {
            builder.insert(0, "Preencha o(s) seguinte(s) campo(s): \n");
        }

        return new Pair<>(builder.toString(), wrongFields);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FieldType.EMAIL, FieldType.PASSWORD})
    public @interface FieldType{
        String EMAIL = "E-mail";
        String PASSWORD = "Senha";
    }
}
