package dev.sofie.messhalkantin.ui.transaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.helper.UIHelper;
import dev.sofie.messhalkantin.service.ApiRepository;


public class MesshallTransactionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TRANSACTION_TYPE = "type";
    public static final String USER_GUEST = "guest";
    public static final String USER_EMPLOYEE = "employee";
    public static CardView statusCard,cardView;
    private Button frontCameraBtn,backCameraBtn,kembaliBtn,submit,tidak;
    public static TextView nama, nik,statusTxt;
    private static ProgressBar loading;
    private IntentIntegrator qrScan;
    private ApiRepository repository;
    private int messhall= 0;
    private static Context mContext;
    public String qrcode = "";
    private static final int PERMISSION_REQUEST = 100;

    private void initUI(){
        loading = findViewById(R.id.progressBar);

        statusCard = findViewById(R.id.statusCard);
        cardView = findViewById(R.id.cardview);

        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        statusTxt = findViewById(R.id.statusTxt);

        submit = findViewById(R.id.submit);
        tidak = findViewById(R.id.no);
        frontCameraBtn = findViewById(R.id.frontCameraBtn);
        backCameraBtn = findViewById(R.id.backCameraBtn);
        kembaliBtn = findViewById(R.id.kembali);


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String itemname = parent.getItemAtPosition(position).toString();
//                if (itemname.equals("Pilih Messhall")) {
//                    frontCameraBtn.setVisibility(View.GONE);
//                    backCameraBtn.setVisibility(View.GONE);
//
//
//                } else if (itemname.equals("Mallomo")) {
//                    frontCameraBtn.setVisibility(View.GONE);
//                    backCameraBtn.setVisibility(View.GONE);
//                    messhall = itemname;
//
//                } else if (itemname.equals("SBM")) {
//                    frontCameraBtn.setVisibility(View.VISIBLE);
//                    backCameraBtn.setVisibility(View.VISIBLE);
//                    messhall = itemname;
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                frontCameraBtn.setVisibility(View.GONE);
//                backCameraBtn.setVisibility(View.GONE);
//            }
//        });

        qrScan = new IntentIntegrator(this);
        qrScan.setBeepEnabled(true);
        qrScan.addExtra("PROMPT_MESSAGE","");
        qrScan.setOrientationLocked(false);
        submit.setOnClickListener(this);
        tidak.setOnClickListener(this);
        kembaliBtn.setOnClickListener(this);
        frontCameraBtn.setOnClickListener(this);
        backCameraBtn.setOnClickListener(this);

    }


    public static void isLoading(boolean b) {
        if(b){
            loading.setVisibility(View.VISIBLE);
            statusCard.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            return;
        }
        loading.setVisibility(View.GONE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_messhall);
        mContext = this;
        repository = ApiRepository.getInstance(this);
        SharedPreferecesHelper helper = SharedPreferecesHelper.newInstance(this);
        messhall =  helper.getUser().getIdMesshall();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                showStatus(false,"Transaksi dibatalkan");
            } else {
                String id = result.getContents();
                qrcode  = id;
                switch (userType(id))
                {
                    case USER_EMPLOYEE:
                        repository.userTransactionMesshall(id,messhall);
                        break;
                    case USER_GUEST:
                        repository.guestTransaction(id,messhall);
                        break;
                   default:
                       showStatus(false,"Tipe User Tidak Ditemukan !");
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

            case R.id.backCameraBtn:
                checkPermission();
                break;
            case R.id.kembali:
                finish();
                break;
            case R.id.submit:
                repository.extraTransactionMesshall(qrcode,messhall);
                break;

            case R.id.no:
                clearTextView();
                showStatus(false,"Transaksi telah dibatalkan");
                cardView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void clearTextView(){
        nama.setText("-");
        nik.setText("-");

    }

    public static void showStatus(boolean status, String message){
        UIHelper.showStatus(statusCard,statusTxt,message,mContext,status);
    }

    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(MesshallTransactionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(MesshallTransactionActivity.this,Manifest.permission.CAMERA)){
                // If we should give explanation of requested permissions
                ActivityCompat.requestPermissions(MesshallTransactionActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(MesshallTransactionActivity.this,new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
            }
        }else{
            qrScan.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
            qrScan.initiateScan();
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
                if((grantResults.length >0) && (grantResults[0]  == PackageManager.PERMISSION_GRANTED)){
                    qrScan.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
                    qrScan.initiateScan();
                    Toast.makeText(MesshallTransactionActivity.this,"Akses diperbolehkan.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MesshallTransactionActivity.this,"Akses tidak diperbolehkan.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
