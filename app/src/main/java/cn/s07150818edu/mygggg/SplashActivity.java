package cn.s07150818edu.mygggg;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;



import cn.s07150818edu.mygggg.m1home.utils.MyUtils;
import cn.s07150818edu.mygggg.m1home.utils.VersionUpdateUtils;

/**
 * Created by student on 16/12/21.
 */

public class SplashActivity extends AppCompatActivity {
    private TextView mVersionTV;
    private String mVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion= MyUtils.getVersion(getApplicationContext());
        initView();
        final VersionUpdateUtils updateUtils=new VersionUpdateUtils(mVersion,SplashActivity.this);
        new Thread(){
            public void run(){
                updateUtils.getCloudVersion();
            };
        }.start();
    }
    private void initView(){
        mVersionTV= (TextView) findViewById(R.id.tv_splash_version);
        mVersionTV.setText("版本号;"+mVersion);
    }
}
