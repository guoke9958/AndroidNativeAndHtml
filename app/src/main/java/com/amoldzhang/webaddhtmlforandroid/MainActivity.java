package com.amoldzhang.webaddhtmlforandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amoldzhang.webaddhtmlforandroid.activity.BaseActivity;
import com.amoldzhang.webaddhtmlforandroid.fragment.FirstFragment;
import com.amoldzhang.webaddhtmlforandroid.fragment.FourthFragment;
import com.amoldzhang.webaddhtmlforandroid.fragment.SecondFragment;
import com.amoldzhang.webaddhtmlforandroid.fragment.ThirdFragment;
import com.amoldzhang.webaddhtmlforandroid.http.dialog.ContextUtils;
import com.amoldzhang.webaddhtmlforandroid.http.utils.NetworkUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Bind(R.id.group)
    RadioGroup group;

    public Fragment fragment;

    public FirstFragment firstFrament = new FirstFragment();
    public SecondFragment twoFrament = new SecondFragment();
    public ThirdFragment threeFrament = new ThirdFragment();
    public FourthFragment fourFrament = new FourthFragment();


    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFrament).show(firstFrament).commit();
            fragment = firstFrament;
        }
        initView();
    }

    private void initView() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot_bar_home:
                        if (fragment != firstFrament) {
                            if (firstFrament.isAdded()) {
                                getFragmentManager().beginTransaction().show(firstFrament).hide(fragment).commit();
                            } else {
                                getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFrament).show(firstFrament).hide(fragment).commit();
                            }
                            fragment = firstFrament;
                        }
                        break;
                    case R.id.foot_bar_car:
                        if (fragment != twoFrament) {
                            if (twoFrament.isAdded()) {
                                getFragmentManager().beginTransaction().show(twoFrament).hide(fragment).commit();
                            } else {
                                getFragmentManager().beginTransaction().add(R.id.fragment_container, twoFrament).show(twoFrament).hide(fragment).commit();
                            }
                            fragment = twoFrament;
                        }
                        break;
                    case R.id.foot_bar_found:
                        if (fragment != threeFrament) {
                            if (threeFrament.isAdded()) {
                                getFragmentManager().beginTransaction().show(threeFrament).hide(fragment).commit();
                            } else {
                                getFragmentManager().beginTransaction().add(R.id.fragment_container, threeFrament).show(threeFrament).hide(fragment).commit();
                            }
                            fragment = threeFrament;
                        }
                        break;
                    case R.id.foot_bar_news:
                        if (fragment != fourFrament) {
                            if (fourFrament.isAdded()) {
                                getFragmentManager().beginTransaction().show(fourFrament).hide(fragment).commit();
                            } else {
                                getFragmentManager().beginTransaction().add(R.id.fragment_container, fourFrament).show(fourFrament).hide(fragment).commit();
                            }
                            fragment = fourFrament;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
