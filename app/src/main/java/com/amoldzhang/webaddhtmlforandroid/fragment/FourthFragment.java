package com.amoldzhang.webaddhtmlforandroid.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.amoldzhang.webaddhtmlforandroid.tools.Utils;
import com.amoldzhang.webaddhtmlforandroid.view.StatusBarUtils;


/**
 * Created by amoldZhang on 2018/3/26.
 */
public class FourthFragment extends BaseFragment {

    private String TAG = FourthFragment.class.getName();
    private int color = R.color.colorPrimary;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.setGroupVisible(R.menu.pie,false);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_layout, container, false);
        setStatusBar();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            GHLog.d(TAG,TAG + "显示");
            setStatusBar();
        }else{
            GHLog.d(TAG,TAG + "隐藏");
        }
    }

    /***
     * 设置沉侵式状态栏
     * @param
     */
    private void setStatusBar() {
        StatusBarUtils.with(mActivity)
//                .setColor(getResources().getColor(R.color.transparent))
                .init();
    }

}
