package dev.sofie.messhalkantin.service;

import java.util.List;

import dev.sofie.messhalkantin.model.ApiResponse;
import dev.sofie.messhalkantin.model.Guest;
import dev.sofie.messhalkantin.model.Magang;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.model.Report;
import dev.sofie.messhalkantin.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    /** AUTH **/

    @POST("messhall/login")
    Call<ApiResponse<User>> login(@Query("email") String nik,
                                  @Query("password") String password);

    @POST("ubahpassword")
    Call<ApiResponse> ubahPassword(@Query("id") String id,
                                   @Query("password") String password,
                                   @Query("password_baru") String passwordBaru);

    /** API FOR MESSHALL **/

    @GET("messhall/overview")
    Call<ApiResponse<Overview>> getOverviewMesshall(@Query("id_akun")String id,
                                                    @Query("bulan")String bulan);

    @POST("messhall/transaction/guest")
    Call<ApiResponse<Guest>> guestTransaction(@Query("nit") String nit,
                                              @Query("id_messhall") int idMesshall);

    @POST("messhall/transaction/user")
    Call<ApiResponse<User>> userTransactionMesshall(@Query("qrcode") String qrcode,
                                                    @Query("id_messhall") int idMesshall);

    @POST("messhall/transaction/extra")
    Call<ApiResponse<User>> extraTransaction(@Query("qrcode") String qrcode,
                                             @Query("id_messhall") int idMesshall);

    @GET("messhall/report")
    Call<ApiResponse<List<Report>>> reportMesshall(@Query("id_messhall")int idMesshall,
                                                   @Query("bulan")String bulan,
                                                   @Query("user")String user);


    /** API FOR KANTIN **/

    @GET("kantin/overview")
    Call<ApiResponse<Overview>> getKantinOverview(@Query("id_akun")String id,
                                                  @Query("bulan")String bulan);

    @POST("kantin/transaction/magang")
    Call<ApiResponse<Magang>> magangTransaction(@Query("nim") String nim,
                                                @Query("id_messhall") int idMesshall);

    @POST("kantin/transaction/user")
    Call<ApiResponse<User>> userTransaction(@Query("qrcode") String qrcode,
                                            @Query("id_messhall") int idMesshall);

    @POST("kantin/transaction/useradd")
    Call<ApiResponse<User>> userAddTransaction(@Query("qrcode") String qrcode,
                                               @Query("id_messhall") int idMesshall,
                                               @Query("keterangan") String keterangan,
                                               @Query("mp") String mp);

    @GET("kantin/report")
    Call<ApiResponse<List<Report>>> reportKantin(@Query("id_messhall")int idMesshall,
                                                 @Query("bulan")String bulan,
                                                 @Query("status")String status);

}
