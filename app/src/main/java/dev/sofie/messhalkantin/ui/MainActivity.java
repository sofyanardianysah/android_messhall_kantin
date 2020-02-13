package dev.sofie.messhalkantin.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.ui.menu.MenuKantinFragment;
import dev.sofie.messhalkantin.ui.menu.MenuMesshallFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferecesHelper preferecesHelper;
    private static final int PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferecesHelper = SharedPreferecesHelper.newInstance(getApplicationContext());
        initUI();

        checkPermission();

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Pemberitahuan");
            builder.setMessage("Apakah anda ingin keluar ?");
            builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferecesHelper helper = SharedPreferecesHelper.newInstance(MainActivity.this);
                    helper.clearUser();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void initUI() {
        ImageView logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);

        if(preferecesHelper.getUser().getRole().equals("kantin")){
            loadFragment(MenuKantinFragment.newInstance());
        }else if(preferecesHelper.getUser().getRole().equals("messhall")){
            loadFragment(MenuMesshallFragment.newInstance());
        }


    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment).commit();
        }
    }

    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        PERMISSION_REQUEST
                );
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        PERMISSION_REQUEST
                );
            }
        }else {
            // Do something, when permissions are already granted
//            Toast.makeText(mContext,"Permissions already granted",Toast.LENGTH_SHORT).show();
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
                if(
                        (grantResults.length >0) && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)
                ){
                    // Permissions are granted
                    Toast.makeText(MainActivity.this,"Akses diperbolehkan.",Toast.LENGTH_SHORT).show();
                }else {
                    // Permissions are denied
                    Toast.makeText(MainActivity.this,"Akses tidak diperbolehkan.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }



    }
