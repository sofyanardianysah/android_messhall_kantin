package dev.sofie.messhalkantin.ui.report;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dev.sofie.messhalkantin.R;

public class CanteenReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_report);
        initUI();
    }

    private void initUI(){
        mBack = findViewById(R.id.backButton);
        mBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton){
            finish();
        }
    }
}
