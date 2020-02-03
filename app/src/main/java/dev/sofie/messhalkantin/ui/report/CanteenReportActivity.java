package dev.sofie.messhalkantin.ui.report;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.MoneyConvert;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.model.Overview;

import static dev.sofie.messhalkantin.helper.DateHelper.dateOnlyNow;

public class CanteenReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private CanteenReportVM canteenReportVM;
    private SharedPreferecesHelper preferecesHelper;
    private static CardView back;
    private static CardView card;
    private static ProgressBar progressBar;
    private TextView magang,lembur,kasbon;

    public static void isLoading(boolean status){
        if(status){
            back.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            return;
        }

        back.setVisibility(View.VISIBLE);
        card.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_report);
        initUI();
        preferecesHelper = SharedPreferecesHelper.newInstance(this);
        canteenReportVM = ViewModelProviders.of( this).get(CanteenReportVM.class);
        canteenReportVM.getOverview(getApplicationContext(),preferecesHelper.getUserID(),dateOnlyNow()).observe(this,overviewObserver);
    }

    private void initUI(){
        mBack = findViewById(R.id.backButton);
        mBack.setOnClickListener(this);
        back =findViewById(R.id.backCard);
        card = findViewById(R.id.cardView);
        progressBar = findViewById(R.id.progressBar);
        magang = findViewById(R.id.magang);
        lembur = findViewById(R.id.lembur);
        kasbon = findViewById(R.id.kasbon);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton){
            finish();
        }
    }

    public Observer overviewObserver = new Observer<Overview>() {
        public void onChanged(Overview overview) {
            lembur.setText(overview.getTotalLembur().toString());
            magang.setText(overview.getTotalMagang().toString());
            kasbon.setText(MoneyConvert.keRupiah(overview.getTotalKasbon()));

        }
    };
}
