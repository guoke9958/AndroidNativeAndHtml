package com.amoldzhang.webaddhtmlforandroid.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.activity.CustomImageRenderingActivity;
import com.amoldzhang.webaddhtmlforandroid.activity.MultiItemListViewActivity;
import com.amoldzhang.webaddhtmlforandroid.activity.MultiItemRvActivity;
import com.amoldzhang.webaddhtmlforandroid.activity.RecyclerViewActivity;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.amoldzhang.webaddhtmlforandroid.view.StatusBarUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by amoldZhang on 2018/3/26.
 */
public class ThirdFragment extends BaseFragment {

    @Bind(R.id.status_view)
    View statusView;
    @Bind(R.id.id_empty_view)
    TextView mEmptyView;
    @Bind(R.id.id_listview_list)
    ListView mListView;

    private String TAG = ThirdFragment.class.getName();
    private int color = R.color.bg_border;
    private int statusBarHeight; //当前系统状态栏高度

    private List<String> mDatas = new ArrayList<>(Arrays.asList(
            "MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView",
            "绘图"));


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
        View view = inflater.inflate(R.layout.fragment_third_layout, container, false);
        statusBarHeight = StatusBarUtils.getStatusBarHeight(mActivity);
        ButterKnife.bind(this, view);

        setStatusBar();
        initView();
        return view;
    }

    /***
     * 设置沉侵式状态栏
     * @param
     */
    private void setStatusBar() {
        // 设置固定大小的占位符
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) statusView.getLayoutParams(); //取控件View当前的布局参数
        linearParams.height = statusBarHeight;// 控件的高强制设成当前手机状态栏高度
        statusView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件</pre>
        statusView.setBackgroundColor(getResources().getColor(color));
        //设置全屏的沉侵式状态栏
        StatusBarUtils.with(mActivity)
//                .setColor(getResources().getColor(color))
                .init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            GHLog.d(TAG, TAG + "显示");
            setStatusBar();
        } else {
            GHLog.d(TAG, TAG + "隐藏");
        }
    }

    private void initView() {
        mListView.setAdapter(new CommonAdapter<String>(mActivity, R.layout.item_third_fragment_list, mDatas)
        {
            @Override
            public void convert(ViewHolder holder, String o, int pos)
            {
                holder.setText(R.id.id_item_list_title, o);
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView)
            {
                super.onViewHolderCreated(holder, itemView);
            }
        });

        mListView.setEmptyView(mEmptyView);
//        TextView t1 = new TextView(this);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(this);
//        t2.setText("Header 2");
//        mListView.addHeaderView(t1);
//        mListView.addHeaderView(t2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = null;
                switch (position)
                {
                    case 0:
                        intent = new Intent(mActivity, MultiItemListViewActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mActivity, RecyclerViewActivity.class);
                        break;
                    case 2:
                        intent = new Intent(mActivity, MultiItemRvActivity.class);
                        break;
                    case 3:
                        intent = new Intent(mActivity, CustomImageRenderingActivity.class);
                        break;
                    default:
                        displayToast("暂未实现");
                        break;

                }
                if (intent != null)
                    startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
