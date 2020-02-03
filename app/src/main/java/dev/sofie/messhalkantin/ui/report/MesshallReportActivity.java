package dev.sofie.messhalkantin.ui.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.model.Overview;

import static dev.sofie.messhalkantin.helper.DateHelper.dateOnlyNow;

public class MesshallReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    static private ProgressBar progressBar;
    static private CardView back,card;
    private MesshallReportVM messhallReportVM;
    private SharedPreferecesHelper preferecesHelper;
    private TextView karyawan,magang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messhall_report);
        initUI();
        preferecesHelper = SharedPreferecesHelper.newInstance(this);
        messhallReportVM = ViewModelProviders.of( this).get(MesshallReportVM.class);

        messhallReportVM.getOverview(getApplicationContext(),preferecesHelper.getUserID(),dateOnlyNow()).observe(this,overviewObserver);

    }

    private void initUI(){
        mBack = findViewById(R.id.backButton);
        mBack.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        back =findViewById(R.id.backCard);
        card = findViewById(R.id.cardView);
        karyawan = findViewById(R.id.karyawan);
        magang = findViewById(R.id.magang);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton){
            finish();
        }
    }

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

    public Observer overviewObserver = new Observer<Overview>() {
        public void onChanged(Overview overview) {
              karyawan.setText(overview.getTotalMeal().toString());
              magang.setText(overview.getTotalTransaksiTamu().toString());

        }
    };
}
