package com.amoldzhang.webaddhtmlforandroid.view.rendering;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 *  图像自定义绘制
 * Created by amoldZhang on 2018/7/13.
 */
public class MyView extends View{

    private ViewGroup viewGroup;
    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;  //自定义画笔

    public MyView(Context context) {
        super(context);
        viewGroup =  (ViewGroup) ((Activity)context).getWindow().getDecorView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (viewGroup != null){
            dstBmp = makeDst(viewGroup.getWidth(),viewGroup.getHeight());
        }else{
            dstBmp = makeDst(width *3,height*3);
        }
        srcBmp = makeSrc(width,height);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerID = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);

        // 先画目标图像：圆形bitmap
        canvas.drawBitmap(dstBmp, 0, 0, mPaint);
        // 然后设置图像混合模式：
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        // 在源图像上生成结果图并更新到目标图像上
        canvas.drawBitmap(srcBmp, 400, 400, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }

    static Bitmap makeDst(int w, int h) {
        // 新建一个空白的bitmap
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setColor(0xFFFFCC44);
        p.setARGB(125,5,5,5);
//        c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);
        c.drawRect(new RectF(0, 0, w*3/4, h*3/4), p);
        return bm;
    }

    /**
     * 新建一个源图像
     * @param w
     * @param h
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static Bitmap makeSrc(int w, int h) {
        //新建一个空白的bitmap
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
//        c.drawRect(w/3, h/3, w*19/20, h*19/20, p);
        c.drawOval(w/3, h/3, w*19/20, h*19/20, p);
        return bm;
    }


}
