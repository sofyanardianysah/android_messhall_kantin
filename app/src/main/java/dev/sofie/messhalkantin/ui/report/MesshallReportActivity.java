package dev.sofie.messhalkantin.ui.report;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.helper.UIHelper;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.service.ApiRepository;

import static dev.sofie.messhalkantin.helper.DateHelper.dateOnlyNow;

public class MesshallReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack,karyawanBtn,guestBtn;
    static private ProgressBar progressBar;
    static private CardView back,card,statusCard;
    private MesshallReportVM messhallReportVM;
    private SharedPreferecesHelper preferecesHelper;
    private TextView karyawan,magang;
    private static TextView statusTxt;
    private ApiRepository repository;
    final String[] tanggal = {""};
    private  static Context mContext;
    private static final int PERMISSION_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messhall_report);
        initUI();
        mContext = this;
        repository = ApiRepository.getInstance(this);
        preferecesHelper = SharedPreferecesHelper.newInstance(this);
        messhallReportVM = ViewModelProviders.of( this).get(MesshallReportVM.class);
        messhallReportVM.getOverview(getApplicationContext(), String.valueOf(preferecesHelper.getUser().getIdMesshall()),dateOnlyNow()).observe(this,overviewObserver);

    }

    private void initUI(){
        statusCard = findViewById(R.id.statusCard);
        statusTxt = findViewById(R.id.statusTxt);
        mBack = findViewById(R.id.backButton);
        mBack.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        back =findViewById(R.id.backCard);
        card = findViewById(R.id.cardView);
        karyawan = findViewById(R.id.karyawan);
        magang = findViewById(R.id.magang);
        karyawanBtn = findViewById(R.id.karyawanBtn);
        karyawanBtn.setOnClickListener(this);
        guestBtn = findViewById(R.id.guestBtn);
        guestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                finish();
            break;
            case R.id.karyawanBtn:
                this.user = "karyawan";
                checkPermission();
            break;
            case R.id.guestBtn:
                this.user = "guest";
                checkPermission();
            default:
                break;
        }

    }

    public static void isLoading(boolean status){
        if(status){
            back.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            statusCard.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            return;
        }

        back.setVisibility(View.VISIBLE);
        card.setVisibility(View.VISIBLE);
        statusCard.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    public static void showStatus(boolean status, String message){
        UIHelper.showStatus(statusCard,statusTxt,message,mContext,status);
    }

    public Observer overviewObserver = new Observer<Overview>() {
        public void onChanged(Overview overview) {
              karyawan.setText(overview.getTotalMeal().toString());
              magang.setText(overview.getTotalTransaksiTamu().toString());

        }
    };

    String user = "";
    public void spinnerDatePickerMonthYear(){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final int idMesshall = preferecesHelper.getUser().getIdMesshall();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MesshallReportActivity.this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tanggal[0] = year +"-"+ (monthOfYear + 1) + "-" + 1 ;
                        repository.messhallReport(idMesshall,tanggal[0],user);

                    }
                }, mYear, mMonth, mDay);
        (datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.show();
    }


    protected void checkPermission(){
        if( ContextCompat.checkSelfPermission(MesshallReportActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if( ActivityCompat.shouldShowRequestPermissionRationale(
                    MesshallReportActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                ActivityCompat.requestPermissions(MesshallReportActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST
                );
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(MesshallReportActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST
                );
            }
        }else {
            // Do something, when permissions are already granted
            spinnerDatePickerMonthYear();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_REQUEST:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) && (grantResults[0]  == PackageManager.PERMISSION_GRANTED)
                ){
                    spinnerDatePickerMonthYear();
                    // Permissions are granted
                    Toast.makeText(MesshallReportActivity.this,"Akses diperbolehkan.",Toast.LENGTH_SHORT).show();
                }else {
                    // Permissions are denied
                    Toast.makeText(MesshallReportActivity.this,"Akses tidak diperbolehkan.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
