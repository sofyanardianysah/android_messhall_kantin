package dev.sofie.messhalkantin.ui.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.service.ApiRepository;


public class MesshallTransactionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TRANSACTION_TYPE = "type";
    public static final String USER_GUEST = "guest";
    public static final String USER_EMPLOYEE = "employee";
    public static CardView errorCard,successCard,cardView;
    private Button buttonScan,kembaliBtn,submit,tidak;
    public static TextView nama, nik,successTxt,errorTxt;

    private static ProgressBar loading;
    private IntentIntegrator qrScan;
    private ApiRepository repository;


    private void initUI(){
        loading = findViewById(R.id.progressBar);
        errorCard = findViewById(R.id.errorCard);
        successCard = findViewById(R.id.successCard);
        cardView = findViewById(R.id.cardview);

        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        successTxt = findViewById(R.id.successTxt);
        errorTxt = findViewById(R.id.errorTxt);


        submit = findViewById(R.id.submit);
        tidak = findViewById(R.id.no);
        buttonScan =  findViewById(R.id.buttonScan);
        kembaliBtn = findViewById(R.id.kembali);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        submit.setOnClickListener(this);
        tidak.setOnClickListener(this);
        kembaliBtn.setOnClickListener(this);
        buttonScan.setOnClickListener(this);

    }


    public static void isLoading(boolean b) {
        if(b){
            loading.setVisibility(View.VISIBLE);
            errorCard.setVisibility(View.GONE);
            successCard.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            return;
        }
        loading.setVisibility(View.GONE);
        errorCard.setVisibility(View.VISIBLE);
        successCard.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_messhall);
        repository = ApiRepository.getInstance(this);
        initUI();
    }



    private String userType(String nik){
            int length = nik.length();
            if(length == 6){
                return USER_GUEST;
            }else if(length > 7 ){
                return USER_EMPLOYEE;
            }else return "User Tidak Ditemukan !" ;
    }



    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                showError("Transaksi dibatalkan");
            } else {
                String id = result.getContents();
                //if qr contains data
                switch (userType(id))
                {
                    case USER_EMPLOYEE:

                        break;
                    case USER_GUEST:

                        break;
                   default:
                       showError("Tipe User Tidak Ditemukan !");
                       break;
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonScan:
                qrScan.initiateScan();
                break;
            case R.id.kembali:
                finish();
                break;
            case R.id.submit:
                break;

            case R.id.no:
                clearTextView();
                showError("Transaksi telah dibatalkan");
                break;
            default:
                break;
        }
    }

    private void clearTextView(){
        nama.setText("-");
        nik.setText("-");

    }

    public static void showError(String message){
        errorCard.setVisibility(View.VISIBLE);
        errorTxt.setText(message);
        successCard.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
    }

    public static void showSuccess(String message){
        errorCard.setVisibility(View.GONE);
        errorTxt.setText(message);
        successCard.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.GONE);
    }
}
