package dev.sofie.messhalkantin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.service.ApiRepository;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    static ConstraintLayout layout;
    static ProgressBar loading;
    ApiRepository apiRepository;
    EditText nik;
    EditText password;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);
        apiRepository = ApiRepository.getInstance(this);
        nik =  findViewById(R.id.nik);
        password =  findViewById(R.id.password);
        loading =  findViewById(R.id.loading);
        layout =  findViewById(R.id.constraintLayout);
        (findViewById(R.id.login)).setOnClickListener(this);
    }

    public static void setLoading(boolean isload) {
        if (isload) {
            loading.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            return;
        }
        loading.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }

    private boolean checkValidation() {
        if (nik.getText().length() == 0) {
            nik.setError("Silahkan Masukkan Email");
            return false;
        } else if (password.getText().length() != 0) {
            return true;
        } else {
            password.setError("Silahkan Masukkan Passwords");
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login){
            if (checkValidation()) {
                apiRepository.login(nik.getText().toString(), password.getText().toString());
            }
        }
    }

}