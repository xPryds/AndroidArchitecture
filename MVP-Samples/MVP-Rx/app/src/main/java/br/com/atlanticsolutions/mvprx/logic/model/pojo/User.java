package br.com.atlanticsolutions.mvprx.logic.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */
public class User {
    private int id;
    private String avatar;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
