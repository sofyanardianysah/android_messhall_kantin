package dev.sofie.messhalkantin.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.sofie.messhalkantin.helper.ExcelHelper;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.model.ApiResponse;
import dev.sofie.messhalkantin.model.Guest;
import dev.sofie.messhalkantin.model.Magang;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.model.Report;
import dev.sofie.messhalkantin.model.User;
import dev.sofie.messhalkantin.ui.LoginActivity;
import dev.sofie.messhalkantin.ui.MainActivity;
import dev.sofie.messhalkantin.ui.pengaturan.PengaturanActivity;
import dev.sofie.messhalkantin.ui.report.CanteenReportActivity;
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
            repository = new ApiRepository(context, new Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(Api.class));
        }
        return repository;
    }

    /**
     * Auth
     **/

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
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ubahPassword(String id, String password,String passwordBaru) {
        PengaturanActivity.showLoading(true);
        api.ubahPassword(id,password, passwordBaru).enqueue(new Callback<ApiResponse>() {
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                PengaturanActivity.showLoading(false);
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                } else if ((response.body()).getStatus().booleanValue()) {
                    PengaturanActivity.clearForm();
                    Toast.makeText(context,response.body().getMsg(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<ApiResponse> call, Throwable t) {
                PengaturanActivity.showLoading(false);
                Toast.makeText(context,"Internal Server Error !",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Report
     **/

    public void kantinReport(int idMesshall,String bulan, final String status) {
        CanteenReportActivity.isLoading(true);
        api.reportKantin(idMesshall,bulan, status).enqueue(new Callback<ApiResponse<List<Report>>>() {
            public void onResponse(Call<ApiResponse<List<Report>>> call, Response<ApiResponse<List<Report>>> response) {
                CanteenReportActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        CanteenReportActivity.showStatus(true, response.body().getMsg());
                        boolean isSusccess = ExcelHelper.report("kantin_report_" + status, response.body().getData());
                        if (isSusccess) {
                            CanteenReportActivity.showStatus(true, "Report telah dibuat, silahkan cek folder MesshallReport");
                        } else {
                            CanteenReportActivity.showStatus(false, "Report gagal dibuat, silahkan coba lagi");
                        }
                        return;
                    }
                    CanteenReportActivity.showStatus(false, response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();

            }

            public void onFailure(Call<ApiResponse<List<Report>>> call, Throwable t) {
                CanteenReportActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void messhallReport(int idMesshall,final String bulan, final String user) {
        Log.e("id_messhall", String.valueOf(idMesshall));
        MesshallReportActivity.isLoading(true);
        api.reportMesshall(idMesshall,bulan, user).enqueue(new Callback<ApiResponse<List<Report>>>() {
            public void onResponse(Call<ApiResponse<List<Report>>> call, Response<ApiResponse<List<Report>>> response) {
                MesshallReportActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        MesshallReportActivity.showStatus(true, response.body().getMsg());
                        boolean isSusccess = ExcelHelper.report("messhall_report_" + user, response.body().getData());
                        if (isSusccess) {
                            MesshallReportActivity.showStatus(true, "Report telah dibuat, silahkan cek folder MesshallReport");
                        } else {
                            MesshallReportActivity.showStatus(false, "Report gagal dibuat, silahkan coba lagi");
                        }
                        return;
                    }
                    MesshallReportActivity.showStatus(false, response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();

            }

            public void onFailure(Call<ApiResponse<List<Report>>> call, Throwable t) {
                MesshallReportActivity.isLoading(false);
                Log.e("error", t.getMessage());
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Transaction
     **/

    public void guestTransaction(String nit, int messhall) {
        MesshallTransactionActivity.isLoading(true);
        api.guestTransaction(nit, messhall).enqueue(new Callback<ApiResponse<Guest>>() {
            public void onResponse(Call<ApiResponse<Guest>> call, Response<ApiResponse<Guest>> response) {
                MesshallTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        MesshallTransactionActivity.showStatus(true,response.body().getMsg());
                        return;
                    }
                    MesshallTransactionActivity.showStatus(false,response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<ApiResponse<Guest>> call, Throwable t) {
                MesshallTransactionActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void userTransactionMesshall(String qrcode, int messhall) {
        MesshallTransactionActivity.isLoading(true);
        api.userTransactionMesshall(qrcode, messhall).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                MesshallTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        if (response.body().getData() != null) {
                            MesshallTransactionActivity.nama.setText(response.body().getData().getNama());
                            MesshallTransactionActivity.nik.setText(response.body().getData().getNik());
                            MesshallTransactionActivity.showStatus(false,response.body().getMsg());
                            MesshallTransactionActivity.cardView.setVisibility(View.VISIBLE);
                        } else {
                            MesshallTransactionActivity.showStatus(true,response.body().getMsg());

                        }

                        return;
                    }
                    MesshallTransactionActivity.showStatus(false,response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                MesshallTransactionActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void extraTransactionMesshall(String qrcode, int messhall) {
        MesshallTransactionActivity.isLoading(true);
        api.extraTransaction(qrcode, messhall).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                MesshallTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        if (response.body().getData() != null) {
                            MesshallTransactionActivity.nama.setText(response.body().getData().getNama());
                            MesshallTransactionActivity.nik.setText(response.body().getData().getNik());
                            MesshallTransactionActivity.showStatus(false,response.body().getMsg());
                            MesshallTransactionActivity.cardView.setVisibility(View.VISIBLE);
                        } else {
                            MesshallTransactionActivity.showStatus(true,response.body().getMsg());

                        }

                        return;
                    }
                    MesshallTransactionActivity.showStatus(false,response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                MesshallTransactionActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void magangTransaction(String nim, int idMesshall) {
        KantinTransactionActivity.isLoading(true);
        api.magangTransaction(nim,idMesshall).enqueue(new Callback<ApiResponse<Magang>>() {
            public void onResponse(Call<ApiResponse<Magang>> call, Response<ApiResponse<Magang>> response) {
                KantinTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        KantinTransactionActivity.showStatus(true, response.body().getMsg());
                        return;
                    }
                    KantinTransactionActivity.showStatus(false, response.body().getMsg());
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

    public void userTransaction(String qrcode,int idMesshall) {
        KantinTransactionActivity.isLoading(true);
        api.userTransaction(qrcode,idMesshall).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                KantinTransactionActivity.isLoading(false);

                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        KantinTransactionActivity.nama.setText(response.body().getData().getNama());
                        KantinTransactionActivity.nik.setText(response.body().getData().getNik());
                        KantinTransactionActivity.statusCard.setVisibility(View.GONE);
                        KantinTransactionActivity.cardView.setVisibility(View.VISIBLE);
                        return;
                    }
                    KantinTransactionActivity.showStatus(false, response.body().getMsg());
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

    public void userAddTransaction(String qrcode,int idMesshall, String keterangan, String mp) {
        KantinTransactionActivity.isLoading(true);
        api.userAddTransaction(qrcode,idMesshall, keterangan, mp).enqueue(new Callback<ApiResponse<User>>() {
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                KantinTransactionActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        KantinTransactionActivity.showStatus(true, response.body().getMsg());
                        return;
                    }
                    KantinTransactionActivity.showStatus(false, response.body().getMsg());
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                KantinTransactionActivity.isLoading(false);
                Log.e("test", t.getMessage());
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Overview
     **/

    public MutableLiveData<Overview> getMesshallOverview(String id, String bulan) {

        MesshallReportActivity.isLoading(true);
        final MutableLiveData<Overview> result = new MutableLiveData<>();
        api.getOverviewMesshall(id, bulan).enqueue(new Callback<ApiResponse<Overview>>() {
            public void onResponse(Call<ApiResponse<Overview>> call, Response<ApiResponse<Overview>> response) {
                MesshallReportActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
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

    public MutableLiveData<Overview> getKantinOverview(String id, String bulan) {

        CanteenReportActivity.isLoading(true);
        final MutableLiveData<Overview> result = new MutableLiveData<>();
        api.getKantinOverview(id, bulan).enqueue(new Callback<ApiResponse<Overview>>() {
            public void onResponse(Call<ApiResponse<Overview>> call, Response<ApiResponse<Overview>> response) {
                CanteenReportActivity.isLoading(false);
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        result.postValue(response.body().getData());
                        return;
                    }
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<ApiResponse<Overview>> call, Throwable t) {
                CanteenReportActivity.isLoading(false);
                Toast.makeText(context, "Internal Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }

}
