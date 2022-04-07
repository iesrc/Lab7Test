package com.example.lab7test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private static final String TAG = "PowerConnectionReceiver";
    private boolean power = true;

    @Override
    public void onReceive(Context context, Intent intent){

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if(isCharging && power){
            power = false;
            if(usbCharge){
                Log.d(TAG,"手机正在使用usb接口充电。");
            }
            else if(acCharge){
                Log.d(TAG,"手机正在使用交流充电器充电。");
            }
            else {
                Log.d(TAG,"手机正在使用未知方式充电。");
            }
        }
        else if(!isCharging){
            power = true;
        }
    }
}
