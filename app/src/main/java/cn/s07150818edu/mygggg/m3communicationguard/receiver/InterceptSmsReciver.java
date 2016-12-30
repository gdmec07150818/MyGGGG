package cn.s07150818edu.mygggg.m3communicationguard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import cn.s07150818edu.mygggg.m3communicationguard.db.dao.BlackNumberDao;

/**
 * Created by Administrator on 2016/12/21.
 */
public class InterceptSmsReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences mSP=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean BlackNumStatus=mSP.getBoolean("BlackNumStatus",true);
        if (BlackNumStatus){
            return;
        }
        BlackNumberDao dao=new BlackNumberDao();
        Object[] objs= (Object[]) intent.getExtras().get("pdus");
        for (Object obj:objs){
            //SmsManager smsManager=SmsManager           报错
            ;

        }
    }
}
