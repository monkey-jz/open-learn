package com.boyaa.binder.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BookManager mBookManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("RemoteService","onCreate ===");



        Intent intent = new Intent(this, RemoteService.class);
        startService(intent);
        bindService(intent, new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("RemoteService","onServiceConnected ===" + ",pid = " + android.os.Process.myPid() + ",thread name = " + Thread.currentThread().getName());
                mBookManager = BookManager.Stub.asInterface(service);
                try {
                    mBookManager.addBook(new Book("三体"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },0);

        requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},100);


    }

    public void addBook(View view) {
        try {
            mBookManager.addBook(new Book("三体"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i("permission","onRequestPermissionsResult ===");
    }
}