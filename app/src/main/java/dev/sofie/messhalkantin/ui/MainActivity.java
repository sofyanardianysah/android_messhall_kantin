package dev.sofie.messhalkantin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.helper.SharedPreferecesHelper;
import dev.sofie.messhalkantin.ui.menu.MenuKantinFragment;
import dev.sofie.messhalkantin.ui.menu.MenuMesshallFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferecesHelper preferecesHelper;
    private static final int CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferecesHelper = SharedPreferecesHelper.newInstance(getApplicationContext());
        initUI();

        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
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


    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }



    }
