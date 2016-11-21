package br.com.atlanticsolutions.mvprx.logic.rest;

import br.com.atlanticsolutions.mvprx.logic.model.pojo.ListUsersResponse;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.LoginRequest;
import br.com.atlanticsolutions.mvprx.logic.model.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */

public interface MvpApi {

    @POST("/api/login")
    Observable<LoginResponse> postLogin(@Body LoginRequest request);

    @GET("/api/users")
    Observable<ListUsersResponse> getUsers(@Query("page") int page);

}
