package com.amoldzhang.webaddhtmlforandroid.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amoldzhang.pulltorefeshview.PullToRefreshBase;
import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.activity.RecyclerViewActivity;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.amoldzhang.webaddhtmlforandroid.view.pulltorefresh.PullToRefreshRecyclerView;
import com.amoldzhang.webaddhtmlforandroid.view.StatusBarUtils;
import com.amoldzhang.webaddhtmlforandroid.view.pulltorefresh.loadinglayout.JingDongHeaderLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadmoreWrapper;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by amoldZhang on 2018/3/26.
 */
public class SecondFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;

    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private EmptyWrapper mEmptyWrapper;
    private LoadmoreWrapper mLoadMoreWrapper;


    private String TAG = SecondFragment.class.getName();
    private int color = R.color.bg_border;
    private boolean refreshing = false; //是否可以刷新
    private boolean loading = false; //是否可以加载更多
    private RecyclerView mRecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_second_layout, container, false);
        setStatusBar();
        ButterKnife.bind(this, view);
        initView();
        return view;
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

    /***
     * 设置沉侵式状态栏
     * @param
     */
    private void setStatusBar() {
        StatusBarUtils.with(mActivity)
                .init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView() {
        initDatas();
        mRecyclerView = pullToRefreshRecyclerView.getRefreshableView();
        pullToRefreshRecyclerView.setHeaderLayout(new JingDongHeaderLayout(mActivity));
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(false);

//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new CommonAdapter<String>(mActivity, R.layout.item_third_fragment_list, mDatas){
            @Override
            protected void convert(ViewHolder holder, String s, int position)
            {
                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 滚动状态变化时回调
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // RecyclerView.canScrollVertically(-1) 的值表示是否能向上滚动，false表示已经滚动到顶部
                if(!refreshing && !recyclerView.canScrollVertically(-1)){
                    refreshing = true;
                    refreshDate();
                }

                // RecyclerView.canScrollVertically(1) 的值表示是否能向下滚动，false表示已经滚动到底部
                if(!loading && !recyclerView.canScrollVertically(1)){
                    loading = true;
                    loadMoreDate();
                }
            }

            /**
             * 滚动时回调
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                // dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                // dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                // dy < 0 时为手指向下滚动,列表滚动显示上面的内容
            }
        });

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        initHeaderAndFooter();

        initEmptyView();

        pullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        refreshDate();
                        mLoadMoreWrapper.notifyDataSetChanged();
                        // Call onRefreshComplete when the list has been refreshed.
                        pullToRefreshRecyclerView.onRefreshComplete();
                    }
                }, 1500);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        for (int i = 0; i < 10; i++){
                            mDatas.add("Add:" + i);
                        }
                        mLoadMoreWrapper.notifyDataSetChanged();
                        // Call onRefreshComplete when the list has been refreshed.
                        pullToRefreshRecyclerView.onRefreshComplete();
                    }
                }, 1500);

            }
        });

        mLoadMoreWrapper = new LoadmoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadmoreWrapper.OnLoadMoreListener(){
            @Override
            public void onLoadMoreRequested(){

            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position){
                Toast.makeText(mActivity, "pos = " + position, Toast.LENGTH_SHORT).show();
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position){
                return false;
            }
        });
    }



    private void refreshDate() {
        for (int i = 0; i < 10; i++){
            mDatas.add("Add:" + i);
        }
    }

    private void loadMoreDate() {

    }

    private void initEmptyView(){
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(LayoutInflater.from(mActivity).inflate(R.layout.empty_view, mRecyclerView, false));
    }

    private void initHeaderAndFooter(){
//        TextView t1 = new TextView(mActivity);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(mActivity);
//        t2.setText("Header 2");
//        mHeaderAndFooterWrapper.addHeaderView(t1);
//        mHeaderAndFooterWrapper.addHeaderView(t2);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_image, mRecyclerView, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast("添加的头部点击");
            }
        });
        mHeaderAndFooterWrapper.addHeaderView(view);
    }

    private void initDatas(){
        for (int i = 'A'; i <= 'z'; i++){
            mDatas.add((char) i + "");
        }
    }
}
