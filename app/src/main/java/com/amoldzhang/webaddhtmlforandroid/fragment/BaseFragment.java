package com.amoldzhang.webaddhtmlforandroid.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amoldzhang.webaddhtmlforandroid.activity.BaseActivity;

/**
 * Created by amoldZhang on 2018/7/13.
 */
public class BaseFragment extends Fragment {

    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    /**
     * Fragment 中TOAS提示
     * @param text
     */
    public void displayToast(String text){
        ((BaseActivity)mActivity).displayToast(text);
    }

}
