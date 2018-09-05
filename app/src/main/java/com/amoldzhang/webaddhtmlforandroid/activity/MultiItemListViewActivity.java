package com.amoldzhang.webaddhtmlforandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.adapter.lv.ChatAdapter;
import com.amoldzhang.webaddhtmlforandroid.mode.ChatMessage;
import com.zhy.adapter.abslistview.CommonAdapter;

/**
 * Created by amoldZhang on 2018/4/4.
 */

public class MultiItemListViewActivity  extends AppCompatActivity {

    private ListView mListView;
    private CommonAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_third_layout);

        mListView = (ListView) findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapter(this, ChatMessage.MOCK_DATAS));

    }

}
