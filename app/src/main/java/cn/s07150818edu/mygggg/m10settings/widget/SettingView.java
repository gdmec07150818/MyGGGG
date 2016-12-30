package cn.s07150818edu.mygggg.m10settings.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import cn.s07150818edu.mygggg.R;


/**
 * Created by Administrator on 2016/12/22.
 */
public class SettingView  extends RelativeLayout {
        private String setTitle="";
        private String status_on="";
        private String status_off="";
        private TextView mSettingTitleTV;
        private TextView mSettingStatusTV;
         private ToggleButton mToggleButton;
    private boolean isChecked;
    private OnCheckedStatusIsChanged onCheckedStatusIsChanged;
    //一段代码
    public SettingView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view=View.inflate(context,R.layout.ui_settings_view,null);
        this.addView(view);
        mSettingTitleTV= (TextView) findViewById(R.id.tv_setting_title);
        mSettingStatusTV= (TextView) findViewById(R.id.tv_setting_status);
        mToggleButton= (ToggleButton) findViewById(R.id.toggle_setting_status);
        mSettingTitleTV.setText(setTitle);
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean i) {
                setChecked(isChecked);
                onCheckedStatusIsChanged.onCheckedChanged(SettingView.this,isChecked);

            }
        });
    }
    public boolean isChecked(){
        return mToggleButton.isChecked();
    }
    public void setChecked(boolean checked){
        mToggleButton.setChecked(checked);
        isChecked=checked;
        if (checked){
            if (!TextUtils.isEmpty(status_on)){
                mSettingStatusTV.setText(status_on);
            }
        }else{
            if (!TextUtils.isEmpty(status_off)){
                mSettingStatusTV.setText(status_off);
            }
        }

    }
    public void setStatus(String staus_on,String status_off,boolean checked){
        if (checked){
            mSettingStatusTV.setText(staus_on);

        }else{
            mSettingStatusTV.setText(status_off);
        }
        mToggleButton.setChecked(checked);
    }
    public void OnCheckedStatusIsChanged(OnCheckedStatusIsChanged onCheckedStatusIsChanged){
        this.onCheckedStatusIsChanged=onCheckedStatusIsChanged;
    }
    public interface OnCheckedStatusIsChanged{
        void onCheckedChanged(View view,boolean isChecked);
    }
    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypeArray=context.obtainStyledAttributes(attrs, R.styleable.SettingView);
        setTitle=mTypeArray.getString(R.styleable.SettingView_settitle);
        status_on=mTypeArray.getString(R.styleable.SettingView_status_on);
        status_off=mTypeArray.getString(R.styleable.SettingView_status_off);
        isChecked=mTypeArray.getBoolean(R.styleable.SettingView_status_ischecked,false);
        mTypeArray.recycle();
        init(context);
        setStatus(status_on,status_off,isChecked);

    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


}
