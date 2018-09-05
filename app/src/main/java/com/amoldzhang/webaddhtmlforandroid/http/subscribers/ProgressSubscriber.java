package com.amoldzhang.webaddhtmlforandroid.http.subscribers;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.activity.BaseActivity;
import com.amoldzhang.webaddhtmlforandroid.http.progress.ProgressCancelListener;
import com.amoldzhang.webaddhtmlforandroid.http.progress.ProgressDialogHandler;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.amoldzhang.webaddhtmlforandroid.tools.StringUtils;
import com.amoldzhang.webaddhtmlforandroid.tools.Utils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;


/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by amoldZhang on 2017/7/13.
 */
public class ProgressSubscriber <T> extends Subscriber<T> implements ProgressCancelListener {

    private boolean flage;
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Activity context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Activity context, boolean flage) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.flage = flage;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
    }

    private void showProgressDialog(String massageString){
        if (mProgressDialogHandler != null) {
            Message message = new Message();
            message.what = ProgressDialogHandler.SHOW_PROGRESS_DIALOG;
            message.obj = massageString;
            mProgressDialogHandler.handleMessage(message);
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (flage){
            showProgressDialog("加载中，请稍候...");
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (flage){
            dismissProgressDialog();
        }else{
            if (!this.isUnsubscribed()) {
                this.unsubscribe();
            }
        }
//        Toast.makeText(context, "网络连接成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context,"当前网络不可用，请检查网络是否正常！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "当前网络不可用，请检查网络是否正常！", Toast.LENGTH_SHORT).show();
        } else {
            try {
                if(!StringUtils.isEmpty(e.getCause().getMessage())){
                    displayToast(e.getCause().getMessage());
                }
            } catch (Exception e1) {
            }
        }
        try {
            GHLog.e("网络连接失败","code = " + e.getMessage() + "Error ＝" + e.getCause().getMessage());
        } catch (Exception e1) {
        }
        if (flage){
            dismissProgressDialog();
        }else{
            if (!this.isUnsubscribed()) {
                this.unsubscribe();
            }
        }
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
        }
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void displayToast(String text) {
        if (text == null)
            return;
        View view = context.getLayoutInflater().inflate(R.layout.myself_toast, null);
        TextView message = (TextView) view.findViewById(R.id.chapterName);
        message.setText(text);
        Toast start = new Toast(context);
        int get_height = Utils.getWindowsHight(context.getApplication()) / 6;
        start.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0,
                get_height);
        start.setDuration(Toast.LENGTH_SHORT);
        start.setView(view);
        start.show();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onCancel();
        }
    }
}
