package dev.sofie.messhalkantin.service;

import dev.sofie.messhalkantin.model.ApiResponse;
import dev.sofie.messhalkantin.model.Guest;
import dev.sofie.messhalkantin.model.Magang;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("messhall/login")
    Call<ApiResponse<User>> login(@Query("email") String nik,
                                  @Query("password") String password);


    // API FOR MESSHALL

    @GET("messhall/overview")
    Call<ApiResponse<Overview>> getOverviewMesshall(@Query("id_akun")String id,
                                                    @Query("bulan")String bulan);


    @POST("messhall/transaction/guest")
    Call<ApiResponse<Guest>> guestTransaction(@Query("nit") String nit,
                                              @Query("messhall") String messhall);

    @POST("messhall/transaction/user")
    Call<ApiResponse<User>> userTransactionMesshall(@Query("qrcode") String qrcode,
                                                     @Query("messhall") String messhall);

    @POST("messhall/transaction/extra")
    Call<ApiResponse<User>> extraTransaction(@Query("qrcode") String qrcode,
                                              @Query("messhall") String messhall);


    // API FOR KANTIN

    @POST("kantin/transaction/magang")
    Call<ApiResponse<Magang>> magangTransaction(@Query("nim") String nim);

    @POST("kantin/transaction/user")
    Call<ApiResponse<User>> userTransaction(@Query("qrcode") String qrcode);

    @POST("kantin/transaction/useradd")
    Call<ApiResponse<User>> userAddTransaction(@Query("qrcode") String qrcode,
                                               @Query("keterangan") String keterangan,
                                               @Query("mp") String mp);


}
