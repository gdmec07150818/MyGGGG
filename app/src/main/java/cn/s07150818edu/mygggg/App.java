package cn.s07150818edu.mygggg;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by student on 16/12/21.
 */

public class App  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        correctSIM();
    }

    private void correctSIM() {
        SharedPreferences sp=getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean protecting=sp.getBoolean("protecting",true);
        if (protecting){
            String bindsim=sp.getString("sim","");
            TelephonyManager tm= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String realsim=tm.getSimSerialNumber();
            if (bindsim.equals(realsim)){
                Log.i("","sim卡未发生变化,还是您的手机");

            }else{
                Log.i("","sim卡发生变化");
                String safenumber=sp.getString("safephone","");
                if (!TextUtils.isEmpty(safenumber)){
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(safenumber,null,"你的清幽手机的SIM卡已经被替换",null,null);
                }
            }
        }
    }


}
