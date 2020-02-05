package dev.sofie.messhalkantin.ui.report;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.MoneyHelper;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.helper.UIHelper;
import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.service.ApiRepository;

import static dev.sofie.messhalkantin.helper.DateHelper.dateOnlyNow;

public class CanteenReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private CanteenReportVM canteenReportVM;
    private SharedPreferecesHelper preferecesHelper;
    private static CardView back;
    private static CardView card;
    private static CardView statusCard;
    private static TextView statusTxt;
    private static ProgressBar progressBar;
    private TextView magang,lembur,kasbon;
    private Button magangBtn,kasbonBtn,lemburBtn;
    private ApiRepository repository;
    final String[] tanggal = {""};
    private static Context mContext;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_report);
        mContext = this;
        initUI();
        preferecesHelper = SharedPreferecesHelper.newInstance(this);
        repository = ApiRepository.getInstance(this);
        canteenReportVM = ViewModelProviders.of( this).get(CanteenReportVM.class);
        canteenReportVM.getOverview(getApplicationContext(),preferecesHelper.getUserID(),dateOnlyNow()).observe(this,overviewObserver);
    }

    private void initUI(){
        mBack = findViewById(R.id.backButton);
        mBack.setOnClickListener(this);
        back =findViewById(R.id.backCard);
        card = findViewById(R.id.cardView);
        statusCard = findViewById(R.id.statusCard);
        statusTxt = findViewById(R.id.statusTxt);
        progressBar = findViewById(R.id.progressBar);
        magang = findViewById(R.id.magang);
        lembur = findViewById(R.id.lembur);
        kasbon = findViewById(R.id.kasbon);
        kasbonBtn = findViewById(R.id.kasbonBtn);
        kasbonBtn.setOnClickListener(this);
        magangBtn = findViewById(R.id.magangBtn);
        magangBtn.setOnClickListener(this);
        lemburBtn = findViewById(R.id.lemburBtn);
        lemburBtn.setOnClickListener(this);
    }

    public static void showStatus(boolean status, String message){
        UIHelper.showStatus(statusCard,statusTxt,message,mContext,status);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.kasbonBtn:
                spinnerDatePickerMonthYear("kasbon");
                break;
            case R.id.magangBtn:
                spinnerDatePickerMonthYear("anak magang");
                break;
            case R.id.lemburBtn:
                spinnerDatePickerMonthYear("lembur");
                break;
        }

    }

    public Observer overviewObserver = new Observer<Overview>() {
        public void onChanged(Overview overview) {
            lembur.setText(overview.getTotalLembur().toString());
            magang.setText(overview.getTotalMagang().toString());
            kasbon.setText(MoneyHelper.keRupiah(overview.getTotalKasbon()));

        }
    };

    public void spinnerDatePickerMonthYear(final String status){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(CanteenReportActivity.this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tanggal[0] = year +"-"+ (monthOfYear + 1) + "-" + 1 ;
                        repository.kantinReport(tanggal[0],status);
                    }
                }, mYear, mMonth, mDay);
        (datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.show();
    }
}
