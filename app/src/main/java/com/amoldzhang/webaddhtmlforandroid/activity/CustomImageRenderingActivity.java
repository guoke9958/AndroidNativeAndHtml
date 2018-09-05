package com.amoldzhang.webaddhtmlforandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.view.StatusBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomImageRenderingActivity extends BaseActivity {

    @Bind(R.id.heald_bar)
    View healdBar;
    @Bind(R.id.status_view)
    View statusView;
    @Bind(R.id.title)
    TextView title;


    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_image_rendering);
        ButterKnife.bind(this);
        setTitleBar();
    }

    private void setTitleBar() {
        title.setText("自定义绘图");
    }

    @Override
    protected void onResume() {
        super.onResume();
        int color = R.color.transparent_half;
        setStatusBar(color);
    }

    /***
     * 设置沉侵式状态栏
     * @param
     */
    private void setStatusBar(int color) {
        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        // 设置固定大小的占位符
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) statusView.getLayoutParams(); //取控件View当前的布局参数
        linearParams.height = statusBarHeight;// 控件的高强制设成当前手机状态栏高度
        statusView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件</pre>
        statusView.setBackgroundColor(getResources().getColor(color));
        //设置全屏的沉侵式状态栏
        StatusBarUtils.with(this)
//                .setColor(getResources().getColor(color))
                .init();
    }

    @OnClick(R.id.left_back)
    public void onViewClicked() {
        finish();
    }
}
