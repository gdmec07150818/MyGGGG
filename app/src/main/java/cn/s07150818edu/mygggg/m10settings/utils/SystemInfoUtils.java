package cn.s07150818edu.mygggg.m10settings.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
public class SystemInfoUtils {
    public static boolean isServiceRunning (Context context,String className){
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos=am.getRunningServices(200);
        for (ActivityManager.RunningServiceInfo info:infos){
            String serverClassName=info.service.getClassName();
            if (className.equals(serverClassName)){
                return true;
            }
        }
        return false;
    }
}
