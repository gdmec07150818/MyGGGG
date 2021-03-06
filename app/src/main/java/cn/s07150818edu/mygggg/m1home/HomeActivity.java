package cn.s07150818edu.mygggg.m1home;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import cn.s07150818edu.mygggg.R;
import cn.s07150818edu.mygggg.m1home.adapter.HomeAdapter;



/**
 * Created by student on 16/12/19.
 */

public class HomeActivity extends AppCompatActivity {
    private GridView gv_home;
    private SharedPreferences msharedPreferences;
    private DevicePolicyManager policyManager;
    private ComponentName componentName;
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        msharedPreferences=getSharedPreferences("config",MODE_PRIVATE);

        gv_home= (GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));

        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (isSetUpPassword()){
                            showInterPswdDialog();
                        }else{
                            showSetUpPswdDialog();
                        }
                        break;
//                    case 1:
//                        startActivity();
//                        break;
//                    case 2:
//                        startActivity();
//                        break;
//                    case 3:
//                        startActivity();
//                        break;
//                    case 4:
//                        startActivity();
//                        break;
//                    case 5:
//                        startActivity();
//                        break;
//                    case 6:
//                        startActivity();
//                        break;
//                    case 7:
//                        startActivity();
//                        break;
//                    case 8:
//                        startActivity();
//                        break;
                }
            }
        });
//        policyManager= (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
//        componentName=new ComponentName(this,MyDeviceAdminRecieve.class);
//        boolean active=policyManager.isAdminActive(componentName);
//        if (!active){
//            Intent intent=new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
//            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"获取超级管理员权限,用于远程锁屏和清除数据");
//            startActivity(intent);
//
//        }
    }

    /*-----------------   */


    public void startActivity(Class<?> cls) {
        Intent intent=new Intent(HomeActivity.this,cls);
        startActivity(intent);
    }

    private void showSetUpPswdDialog() {

    }
    /*-----------------   */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis()-mExitTime)>2000){
                Toast.makeText(this,"再按一次推出程序",Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();

            }else{
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /*-----------------   */

    private void showInterPswdDialog() {
    }
    /*-----------------   */

//    private void savePswd(String affirmPwsd){
//        SharedPreferences.Editor editor=msharedPreferences.edit();
//        editor.putString("PhoneAntiTheftPWD", MD5Utils.encode(affirmPwsd));
//        editor.commit();
//    }
    /*-----------------   */
    private String getPassword(){
        String password=msharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return "";
        }
        return  password;
    }

    private boolean isSetUpPassword() {
        String password=msharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
}
