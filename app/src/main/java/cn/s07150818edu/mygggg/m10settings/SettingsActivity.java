package cn.s07150818edu.mygggg.m10settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import cn.s07150818edu.mygggg.R;
import cn.s07150818edu.mygggg.m10settings.utils.SystemInfoUtils;
import cn.s07150818edu.mygggg.m10settings.widget.SettingView;

/**
 * Created by Administrator on 2016/12/22.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener,SettingView.OnCheckedStatusIsChanged {


    private SettingView mBlackNumSV;
    private SettingView mAppLockSV;
    private SharedPreferences mSP;
    private boolean running;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        mSP=getSharedPreferences("config",MODE_PRIVATE);
        initView();
    }

    private void initView() {
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.bright_blue)
        );
        ImageView mLeftimgv= (ImageView) findViewById(R.id.imgv_leftbtn);
        ((TextView)findViewById(R.id.tv_title)).setText("设置中心");
        mLeftimgv.setOnClickListener(this);
        mLeftimgv.setImageResource(R.drawable.back);
        mBlackNumSV= (SettingView) findViewById(R.id.sv_blacknumber_set);
        mAppLockSV= (SettingView) findViewById(R.id.sv_applock_set);
        mBlackNumSV.setOnCheckedStatusIsChanged(this);
        mAppLockSV.setOnCheckedStatusIsChanged(this);

    }

    @Override
    protected void onStart() {
        running= SystemInfoUtils.isServiceRunning(this,"cn.itcast.mobliesafe.ApplockService");
        mAppLockSV.setChecked(running);
        mBlackNumSV.setChecked(mSP.getBoolean("BlackNumStatus",true));
        super.onStart();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgv_leftbtn:
                finish();
                break;
        }

    }
    private void saveStatus(String keyname,boolean isChecked){
        if (!TextUtils.isEmpty(keyname)){
            SharedPreferences.Editor edit=mSP.edit();
            edit.putBoolean(keyname,isChecked);
            edit.commit();
        }
    }

    @Override
    public void onCheckedChanged(View view, boolean isChecked) {
            switch (view.getId()){
                case R.id.sv_blacknumber_set:
                    saveStatus("BlackNumStatus",isChecked);
                    break;
            }
    }
}
