package com.example.jakfood.network;

import com.example.jakfood.model.ResponseKategori;
import com.example.jakfood.model.ResponseMakanan;
import com.example.jakfood.model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("registeruser.php")
    Call<ResponseUser> requestRegister(
            @Field("vsnama") String nama,
            @Field("vsalamat") String alamat,
            @Field("vsjenkel") String jenkel,
            @Field("vsnotelp") String notelp,
            @Field("vsusername") String username,
            @Field("vspassword") String password,
            @Field("vslevel") String level

    );

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<ResponseUser> requestLogin(
            @Field("edtusername") String username,
            @Field("edtpassword") String password,
            @Field("vslevel") String level

    );

    @FormUrlEncoded
    @POST("getdatamakanan.php")
    Call<ResponseMakanan> getDataMakanan(
            @Field("vsiduser") String iduser,
            @Field("vsidkastrkategorimakanan") String kategorimakanan

    );

    @GET("kategorimakanan.php")
    Call<ResponseKategori> getKategoriMakanan();

    @FormUrlEncoded
    @POST("deletedatamakanan.php")
    Call<ResponseUser> deleteData(
            @Field("vsidmakanan") String idmakanan

    );

}
