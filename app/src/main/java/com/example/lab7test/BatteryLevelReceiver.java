package com.example.lab7test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryLevelReceiver";
    private int battery = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        float batteryPct = level * 100 / (float)scale;
        if(batteryPct <= 20 && battery == 0){
            Log.d(TAG,"手机处于低电量状态");
            battery = 1;
        }
        else if(batteryPct >20 && battery == 1){
            Log.d(TAG,"手机脱离低电量状态");
            battery = 0;
        }
    }
}
