package com.p2m.stourch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Boolean state= true;
    ImageButton flashLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashLight= findViewById(R.id.torchOff);

        flashLight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (state){
                    CameraManager manager= (CameraManager)getSystemService(Context.CAMERA_SERVICE);
                    try {
                        String camID= manager.getCameraIdList()[0];
                        manager.setTorchMode(camID,true);
                        state= false;
                        flashLight.setImageResource(R.drawable.torch_on);
                    }catch (CameraAccessException e){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    CameraManager manager= (CameraManager)getSystemService(Context.CAMERA_SERVICE);
                    try {
                        String camID= manager.getCameraIdList()[0];
                        manager.setTorchMode(camID,false);
                        state= true;
                        flashLight.setImageResource(R.drawable.torch_off);
                    }catch (CameraAccessException e){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}