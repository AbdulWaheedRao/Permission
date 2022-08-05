package com.example.permission;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btncall;
EditText etNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncall=findViewById(R.id.btncall);
        etNo=findViewById(R.id.etNo);
        ActivityResultLauncher<String> callPermissionRequest=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                  callPhone(etNo.getText().toString());
                }
            }
        });
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Calling", Toast.LENGTH_SHORT).show();
                    callPhone(etNo.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Requesting", Toast.LENGTH_SHORT).show();
                    callPermissionRequest.launch(Manifest.permission.CALL_PHONE);
                }
            }
        });
    }
    private void callPhone(String no){
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+no));
        startActivity(intent);

    }
}