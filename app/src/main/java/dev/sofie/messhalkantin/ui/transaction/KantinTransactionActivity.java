package dev.sofie.messhalkantin.ui.transaction;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.UIHelper;
import dev.sofie.messhalkantin.service.ApiRepository;
import dev.sofie.messhalkantin.ui.MainActivity;

public class KantinTransactionActivity extends AppCompatActivity implements View.OnClickListener {
    public  static final String USER_INTERN = "intern";
    public  static final String USER_EMPLOYEE = "employee";
    public  static CardView  statusCard, cardView;
    private Button frontCameraBtn,backCameraBtn, kembaliBtn, submit, tidak;
    public  static TextView nama, nik, kasbonTxt, statusTxt;
    private static ProgressBar loading;
    private IntentIntegrator qrScan;
    private EditText kasbon;
    private Spinner transaksi;
    private ApiRepository repository;
    public  static String qrcode = "";
    private String keterangan = "";
    private static Context mContext;
    private static final int PERMISSION_REQUEST = 100;
    private void initUI() {

        transaksi = findViewById(R.id.transaksi);
        kasbonTxt = findViewById(R.id.textView20);
        transaksi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemname = parent.getItemAtPosition(position).toString();
                if (itemname.equals("Pilih Transaksi")) {
                    submit.setVisibility(View.GONE);
                    tidak.setVisibility(View.GONE);
                    kasbonTxt.setVisibility(View.GONE);
                    kasbon.setVisibility(View.GONE);
                } else if (itemname.equals("Lembur")) {
                    keterangan = itemname.toLowerCase();
                    submit.setVisibility(View.VISIBLE);
                    tidak.setVisibility(View.VISIBLE);
                    kasbonTxt.setVisibility(View.GONE);
                    kasbon.setVisibility(View.GONE);
                } else if (itemname.equals("Kasbon")) {
                    keterangan = itemname.toLowerCase();
                    submit.setVisibility(View.VISIBLE);
                    tidak.setVisibility(View.VISIBLE);
                    kasbonTxt.setVisibility(View.VISIBLE);
                    kasbon.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                submit.setVisibility(View.GONE);
                tidak.setVisibility(View.GONE);
                kasbonTxt.setVisibility(View.GONE);
                kasbon.setVisibility(View.GONE);
            }
        });
        kasbon = findViewById(R.id.kasbon);
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

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setBeepEnabled(true);
        qrScan.addExtra("PROMPT_MESSAGE","");

        submit.setOnClickListener(this);
        tidak.setOnClickListener(this);
        kembaliBtn.setOnClickListener(this);
        frontCameraBtn.setOnClickListener(this);
        backCameraBtn.setOnClickListener(this);

    }


    public static void isLoading(boolean b) {
        if (b) {
            loading.setVisibility(View.VISIBLE);
            statusCard.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            return;
        }
        loading.setVisibility(View.GONE);
    }

    private String userType(String nik) {
        int length = nik.length();
        if (length == 7) {
            return USER_INTERN;
        } else if (length > 7) {
            return USER_EMPLOYEE;
        } else return "Tipe User Tidak Ditemukan !";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_kantin);
        mContext = this;
        repository = ApiRepository.getInstance(this);
        initUI();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {

            } else {
                String id = result.getContents();
                showStatus(false,"Transaksi Dibatalkan !");
                switch (userType(id)) {
                    case USER_EMPLOYEE:
                        qrcode = id;
                        repository.userTransaction(id);
                        clearTextView();
                        break;
                    case USER_INTERN:
                        repository.magangTransaction(id);
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

    public static void showStatus(boolean status, String message){
        UIHelper.showStatus(statusCard,statusTxt,message,mContext,status);
    }

    private void clearTextView() {
        nama.setText("-");
        nik.setText("-");
        kasbon.setText("");
        transaksi.setSelection(0);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backCameraBtn:
                checkPermission();
                break;
            case R.id.kembali:
                finish();
                break;
            case R.id.submit:
                String mykasbon = kasbon.getText().toString();
                if (mykasbon.length() == 0) {
                    mykasbon = "0";
                }
                repository.userAddTransaction(qrcode, keterangan, mykasbon);
                break;
            case R.id.no:
                clearTextView();
                showStatus(false,"Transaksi telah dibatalkan");
                break;
            default:
                break;
        }
    }


    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(KantinTransactionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(KantinTransactionActivity.this,Manifest.permission.CAMERA)){
                // If we should give explanation of requested permissions
                ActivityCompat.requestPermissions(KantinTransactionActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(KantinTransactionActivity.this,new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
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
                    Toast.makeText(KantinTransactionActivity.this,"Akses diperbolehkan.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(KantinTransactionActivity.this,"Akses tidak diperbolehkan.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


}
