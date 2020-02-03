package dev.sofie.messhalkantin.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.model.ApiResponse;
import dev.sofie.messhalkantin.model.Guest;
import dev.sofie.messhalkantin.model.Magang;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.model.User;
import dev.sofie.messhalkantin.ui.LoginActivity;
import dev.sofie.messhalkantin.ui.MainActivity;
import dev.sofie.messhalkantin.ui.report.MesshallReportActivity;
import dev.sofie.messhalkantin.ui.transaction.KantinTransactionActivity;
import dev.sofie.messhalkantin.ui.transaction.MesshallTransactionActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {
    private static final String BASE_URL = "http://messhall.dibagus.com/api/v1/";
    private static ApiRepository repository;
    private Api api;
    private Context context;

    private ApiRepository(Context context, Api api) {
        this.api = api;
        this.context = context;
    }

    public static ApiRepository getInstance(Context context) {
        if (repository == null) {
            repository = new ApiRepository(context,new Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(Api.class));
        }
        return repository;
    }

    public void login(String nik, String password) {
        LoginActivity.setLoading(true);
        api.login(nik, password).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {

                if (!response.isSuccessful()) {
                    LoginActivity.setLoading(false);

                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                } else if ((response.body()).getStatus().booleanValue()) {

                    SharedPreferecesHelper.newInstance(context).setUser(response.body().getData());
                    ((Activity) context).finish();
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    LoginActivity.setLoading(false);
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                LoginActivity.setLoading(false);
                Toast.makeText(context,"Internal Server Error !",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<Overview> getMesshallOverview(String id, String bulan) {

        MesshallReportActivity.isLoading(true);
        final MutableLiveData<Overview> result = new MutableLiveData<>();
        api.getOverviewMesshall(id, bulan).enqueue(new Callback<ApiResponse<Overview>>() {
            public void onResponse(Call<ApiResponse<Overview>> call, Response<ApiResponse<Overview>> response) {
                MesshallReportActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if(response.body().getStatus()){
                        result.postValue(response.body().getData());
                        return;
                    }
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }
            public void onFailure(Call<ApiResponse<Overview>> call, Throwable t) {
                MesshallReportActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }





    // KANTIN TRANSACTION
    public void magangTransaction(String nim) {

        KantinTransactionActivity.isLoading(true);
        api.magangTransaction(nim).enqueue(new Callback<ApiResponse<Magang>>() {
            public void onResponse(Call<ApiResponse<Magang>> call, Response<ApiResponse<Magang>> response) {
                KantinTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if(response.body().getStatus()){
                        KantinTransactionActivity.successTxt.setText(response.body().getMsg());
                        KantinTransactionActivity.successCard.setVisibility(View.VISIBLE);
                        KantinTransactionActivity.errorCard.setVisibility(View.GONE);
                        KantinTransactionActivity.cardView.setVisibility(View.GONE);
                        return;
                    }
                    KantinTransactionActivity.errorCard.setVisibility(View.VISIBLE);
                    KantinTransactionActivity.successCard.setVisibility(View.GONE);
                    KantinTransactionActivity.cardView.setVisibility(View.GONE);
                    KantinTransactionActivity.errorTxt.setText(response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }
            public void onFailure(Call<ApiResponse<Magang>> call, Throwable t) {
                KantinTransactionActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void userTransaction(String qrcode){
        KantinTransactionActivity.isLoading(true);
        api.userTransaction(qrcode).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                KantinTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if(response.body().getStatus()){
                        KantinTransactionActivity.nama.setText(response.body().getData().getNama());
                        KantinTransactionActivity.nik.setText(response.body().getData().getNik());
                        KantinTransactionActivity.successCard.setVisibility(View.GONE);
                        KantinTransactionActivity.errorCard.setVisibility(View.GONE);
                        KantinTransactionActivity.cardView.setVisibility(View.VISIBLE);
                        return;
                    }
                    KantinTransactionActivity.errorCard.setVisibility(View.VISIBLE);
                    KantinTransactionActivity.successCard.setVisibility(View.GONE);
                    KantinTransactionActivity.cardView.setVisibility(View.GONE);
                    KantinTransactionActivity.errorTxt.setText(response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                KantinTransactionActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void userAddTransaction(String qrcode,String keterangan,String mp){
        KantinTransactionActivity.isLoading(true);
        api.userAddTransaction(qrcode,keterangan,mp).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                KantinTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if(response.body().getStatus()){

                        KantinTransactionActivity.successTxt.setText(response.body().getMsg());
                        KantinTransactionActivity.successCard.setVisibility(View.VISIBLE);
                        KantinTransactionActivity.errorCard.setVisibility(View.GONE);
                        KantinTransactionActivity.cardView.setVisibility(View.GONE);
                        return;
                    }
                    KantinTransactionActivity.errorCard.setVisibility(View.VISIBLE);
                    KantinTransactionActivity.successCard.setVisibility(View.GONE);
                    KantinTransactionActivity.cardView.setVisibility(View.GONE);
                    KantinTransactionActivity.errorTxt.setText(response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                KantinTransactionActivity.isLoading(false);
                Log.e("test",t.getMessage());
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
