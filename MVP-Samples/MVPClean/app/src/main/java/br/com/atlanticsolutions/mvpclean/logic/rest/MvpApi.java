package br.com.atlanticsolutions.mvpclean.logic.rest;

import br.com.atlanticsolutions.mvpclean.logic.model.pojo.ListUsersResponse;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginRequest;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */

public interface MvpApi {

    @POST("/api/login")
    Call<LoginResponse> postLogin(@Body LoginRequest request);

    @GET("/api/users")
    Call<ListUsersResponse> getUsers(@Query("page") int page);

}
