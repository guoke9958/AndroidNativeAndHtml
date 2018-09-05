package com.amoldzhang.webaddhtmlforandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.http.dialog.LoadProgressDialog;
import com.amoldzhang.webaddhtmlforandroid.tools.Utils;
import com.amoldzhang.webaddhtmlforandroid.view.image.ImageManager;

import butterknife.ButterKnife;

/**
 * Created by amoldZhang on 2018/2/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private ImageManager mImageManager;  //图片加载空件工具创建
    private LoadProgressDialog dialog;    //点击可取消的等待条

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        onCreateView(savedInstanceState);
    }

    /**
     * 创建布局
     */
    public abstract void onCreateView(@Nullable Bundle savedInstanceState);


    /**
     * 图片加载控件
     * @return
     */
    public ImageManager getImageManager() {
        if (mImageManager == null){
            mImageManager = new ImageManager(this);
        }
        return mImageManager;
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void displayToast(String text) {
        if (text == null)
            return;
        View view = getLayoutInflater().inflate(R.layout.myself_toast, null);
        TextView message = (TextView) view.findViewById(R.id.chapterName);
        message.setText(text);
        Toast start = new Toast(this);
        int get_height = Utils.getWindowsHight(getApplication()) / 6;
        start.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0,
                get_height);
        start.setDuration(Toast.LENGTH_SHORT);
        start.setView(view);
        start.show();
    }

    /**
     * 加载进度条
     *
     * @param message
     */
    public void showProgressDialog(String message) {
        if (dialog == null){
            dialog = new LoadProgressDialog(this, message);
        }
        dialog.show();
    }

    /**
     * 关闭进度条
     */
    public void dismisProgressDialog() {
        if (dialog == null) {
            return;
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }


}
