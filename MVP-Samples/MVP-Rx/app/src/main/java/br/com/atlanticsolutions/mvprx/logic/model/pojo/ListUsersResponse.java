package br.com.atlanticsolutions.mvprx.logic.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */
public class ListUsersResponse {
    private int page, total;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("data")
    private ArrayList<User> users;

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
