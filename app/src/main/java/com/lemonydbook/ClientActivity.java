package com.lemonydbook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.lemonydbook.bean.Personon;
import com.lemonydbook.server.RemoteService;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClientActivity extends AppCompatActivity {

    private IPersonManager iPersonManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.enjoy.binder");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        //测试进程间通信
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.e("leo", "------------onClick:" + Thread.currentThread());
                    iPersonManager.addPerson(new Personon("leo", 3));
                    List<Personon> persons = iPersonManager.getPersonList();
                    Log.e("leo", persons.toString() + "," + Thread.currentThread());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("leo", "onServiceConnected: success");
            iPersonManager =  IPersonManager.Stub.asInterface(service);// proxy
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("leo", "onServiceDisconnected: success");
            iPersonManager = null;
        }
    };
}
