package com.amoldzhang.webaddhtmlforandroid.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amoldzhang.pulltorefeshview.PullToRefreshBase;
import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.adapter.DividerItemDecoration;
import com.amoldzhang.webaddhtmlforandroid.view.pulltorefresh.PullToRefreshRecyclerView;
import com.amoldzhang.webaddhtmlforandroid.view.pulltorefresh.loadinglayout.JingDongHeaderLayout;
import com.amoldzhang.webaddhtmlforandroid.view.pulltorefresh.loadinglayout.TmallFooterLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadmoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoldZhang on 2018/4/4.
 */

public class RecyclerViewActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private EmptyWrapper mEmptyWrapper;
    private LoadmoreWrapper mLoadMoreWrapper;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;


    @Override
    public void onCreateView(Bundle savedInstanceState){
        setContentView(R.layout.activity_recyclerview);
        initView();
    }

    private void initView(){
        initDatas();

        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) this.findViewById(R.id.main_act_recyclerview);
        mPullToRefreshRecyclerView.setHeaderLayout(new JingDongHeaderLayout(this));
        mPullToRefreshRecyclerView.setFooterLayout(new TmallFooterLayout(this));

        initRecyclerView();

        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new GetDataTask().execute("下拉刷新");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new GetDataTask().execute("上拉加载");
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new CommonAdapter<String>(this, R.layout.item_third_fragment_list, mDatas)
        {
            @Override
            protected void convert(ViewHolder holder, String s, int position)
            {
                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

//        mWrapAdapter = new WrapAdapter<>(mAdapter);
//        mWrapAdapter.adjustSpanSize(mRecyclerView);
//        mRecyclerView.setAdapter(mWrapAdapter);

        initHeaderAndFooter();

        initEmptyView();

//        mLoadMoreWrapper = new LoadmoreWrapper(mHeaderAndFooterWrapper);
//        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadmoreWrapper.OnLoadMoreListener()
//        {
//            @Override
//            public void onLoadMoreRequested()
//            {
//                new Handler().postDelayed(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        for (int i = 0; i < 10; i++)
//                        {
//                            mDatas.add("Add:" + i);
//                        }
//                        mLoadMoreWrapper.notifyDataSetChanged();
//
//                    }
//                }, 3000);
//            }
//        });
//        mRecyclerView.setAdapter(mLoadMoreWrapper);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position)
            {
                Toast.makeText(RecyclerViewActivity.this, "pos = " + position, Toast.LENGTH_SHORT).show();
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position)
            {
                return false;
            }
        });
    }

    private void initEmptyView()
    {
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, mRecyclerView, false));
    }

    private void initHeaderAndFooter(){
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
    }


    private class GetDataTask extends AsyncTask<String, Void, Integer[]> {

        @Override
        protected Integer[] doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }

            if(params[0].equals("下拉刷新")) {
                return new Integer[0];
            } else {
                Integer[] a1 = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
                return a1;
            }
        }

        @Override
        protected void onPostExecute(Integer[] result) {
            for (int i = 'A'; i <= 'z'; i++)
            {
                mDatas.add((char) i + "");
            }
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshRecyclerView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private void initDatas(){
        for (int i = 'A'; i <= 'z'; i++)
        {
            mDatas.add((char) i + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_linear:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.action_staggered:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        mRecyclerView.setAdapter(mLoadMoreWrapper);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();


    }

    @Override
    public void onStop(){
        super.onStop();


    }
}
