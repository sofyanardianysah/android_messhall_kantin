package dev.sofie.messhalkantin.ui.pengaturan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.service.ApiRepository;

public class PengaturanActivity extends AppCompatActivity {
    static ProgressBar mLoading;
    private  static EditText mPassword,mPasswordBaru,mRepeatPassword;
    static SharedPreferecesHelper sharedHelper;
    static ApiRepository repository;
    private static CardView mCard,kembaliCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        repository= ApiRepository.getInstance(getApplicationContext());
        sharedHelper = SharedPreferecesHelper.newInstance(getApplicationContext());
        initUI();
    }

    private void initUI(){
        mLoading = findViewById(R.id.progressBar);
        mPassword = findViewById(R.id.password);
        mPasswordBaru = findViewById(R.id.password_baru);
        mRepeatPassword = findViewById(R.id.ulangi_password);
        mCard = findViewById(R.id.cardView2);
        kembaliCard = findViewById(R.id.kembaliCard);

        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(checkValidation()){
                    repository.ubahPassword(String.valueOf(sharedHelper.getUser().getId()),
                            mPassword.getText().toString(),
                            mPasswordBaru.getText().toString());

                }
            }
        });

        (findViewById(R.id.kembali)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               finish();
            }
        });
    }

    public static void clearForm(){
        mPassword.setText("");
        mPasswordBaru.setText("");
        mRepeatPassword.setText("");
    }



    public boolean checkValidation() {

        if (mPassword.getText().length() == 0) {
            mPassword.setError("Silahkan Masukkan Password !");
            return false;
        } else if (mPasswordBaru.getText().length() == 0) {
            mPasswordBaru.setError("Silahkan Masukkan Password Baru !");
            return false;
        } else if (mRepeatPassword.getText().length() == 0) {
            mRepeatPassword.setError("Silahkan Masukkan Ulangi Password !");
            return false;
        }  else if (mPasswordBaru.getText().length() != mRepeatPassword.getText().length() ) {
            mPasswordBaru.setError("Password Baru dan Ulangin Password tidak sama !");
            mRepeatPassword.setError("Password Baru dan Ulangin Password tidak sama !");
            return false;
        }else {
            return true;
        }

    }

    public static void showLoading(boolean status){
        if(status){
            mLoading.setVisibility(View.VISIBLE);
            mCard.setVisibility(View.GONE);
            kembaliCard.setVisibility(View.GONE);

        }else{
            mLoading.setVisibility(View.GONE);
            mCard.setVisibility(View.VISIBLE);
            kembaliCard.setVisibility(View.VISIBLE);
        }
    }
}
