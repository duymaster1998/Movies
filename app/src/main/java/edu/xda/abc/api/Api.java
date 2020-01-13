package edu.xda.abc.api;

import java.util.List;

import edu.xda.abc.models.ActorModel;
import edu.xda.abc.models.MovieModel;
import edu.xda.abc.models.RatingModel;
import edu.xda.abc.models.ResponseLoginModel;

import edu.xda.abc.models.ResponseUserRegisterModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("users/user")
    Call<ResponseLoginModel> checkLogin(
            @Field("gmail") String gmail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/register")
    Call<ResponseUserRegisterModel> saveUser(
            @Field("fullname") String fullname,
            @Field("gmail") String gmail,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("ratings/insert")
    Call<ResponseUserRegisterModel> saveRating(
            @Field("userid") int userid,
            @Field("movieid") int movieid,
            @Field("content") String content,
            @Field("star") int star

    );

    @FormUrlEncoded
    @POST("ratings/update")
    Call<ResponseUserRegisterModel> updateRating(

            @Field("content") String content,
            @Field("star") int star,
            @Field("userid") int userid,
            @Field("movieid") int movieid

    );

    @GET("movies/alls")
    Call<List<MovieModel>> findAllMovies();

    @GET("movies/alls/hd")
    Call<List<MovieModel>> findAllMoviesHD();

    @GET("movies/alls/tc")
    Call<List<MovieModel>> findAllMoviesTC();

    @GET("movies/alls/other")
    Call<List<MovieModel>> findAllMoviesOther();

    @GET("movies/topmovies")
    Call<List<MovieModel>> findTop4MovieNews();

    @GET("movies/")
    Call<List<MovieModel>> filterMoviesByName(@Query("n") String name);

    @GET("actorfilms/{id}")
    Call<List<ActorModel>> findAllActorMovie(@Path("id") int id);

    @GET("ratings/{id}")
    Call<List<RatingModel>> findAllRatingMovies(@Path("id") int id);


}
