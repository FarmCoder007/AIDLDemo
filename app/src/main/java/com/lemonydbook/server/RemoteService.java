package com.lemonydbook.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lemonydbook.IPersonManager;
import com.lemonydbook.bean.Personon;
import com.lemonydbook.bean.Test;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 *  另一个进程的远程服务
 */
public class RemoteService extends Service {

    private ArrayList<Personon> Personons;
    private ArrayList<Test> Tests;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Personons = new ArrayList<>();
        Tests = new ArrayList<>();
        Log.e("LeoAidlService", "success onBind");
        return iBinder;
    }

    /**
     * */
    private IBinder iBinder = new IPersonManager.Stub() {

        @Override
        public void addTest(Test test) throws RemoteException {
            Tests.add(test);
        }

        @Override
        public void addPerson(Personon Personon) throws RemoteException {
            Personons.add(Personon);
        }

        @Override
        public List<Personon> getPersonList() throws RemoteException {
            return Personons;
        }
    };
}
