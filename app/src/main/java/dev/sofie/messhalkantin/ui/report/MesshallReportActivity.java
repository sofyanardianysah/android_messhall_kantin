package dev.sofie.messhalkantin.ui.report;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messhall_report);
        initUI();
        repository = ApiRepository.getInstance(this);
        preferecesHelper = SharedPreferecesHelper.newInstance(this);
        messhallReportVM = ViewModelProviders.of( this).get(MesshallReportVM.class);

        messhallReportVM.getOverview(getApplicationContext(),preferecesHelper.getUserID(),dateOnlyNow()).observe(this,overviewObserver);

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
                spinnerDatePickerMonthYear("karyawan");
            break;
            case R.id.guestBtn:
                spinnerDatePickerMonthYear("guest");
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
        UIHelper.showStatus(statusCard,statusTxt,message,status);
    }

    public Observer overviewObserver = new Observer<Overview>() {
        public void onChanged(Overview overview) {
              karyawan.setText(overview.getTotalMeal().toString());
              magang.setText(overview.getTotalTransaksiTamu().toString());

        }
    };

    final String[] tanggal = {""};
    public void spinnerDatePickerMonthYear(final String user){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MesshallReportActivity.this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tanggal[0] = year +"-"+ (monthOfYear + 1) + "-" + 1 ;
                        repository.messhallReport(tanggal[0],user);
                    }
                }, mYear, mMonth, mDay);
        (datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.show();
    }
}
