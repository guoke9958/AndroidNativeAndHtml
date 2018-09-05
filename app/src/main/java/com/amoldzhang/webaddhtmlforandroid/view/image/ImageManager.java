package com.amoldzhang.webaddhtmlforandroid.view.image;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.amoldzhang.webaddhtmlforandroid.R;
import com.amoldzhang.webaddhtmlforandroid.activity.BaseActivity;
import com.amoldzhang.webaddhtmlforandroid.view.image.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoldZhang on 2016/12/6.
 */
public class ImageManager<T> {

    private Context mContext;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    @SuppressLint("NewApi")
    private ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            view.setAlpha( 0f );

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
            fadeAnim.setDuration( 2500 );
            fadeAnim.start();
        }
    };

    public ImageManager(Context context) {
        this.mContext = context;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    //解决第一次加载的时候只显示占位图，第二次才显示正常的图片
    public void loadUrlImageView(String url, final ImageView imageView){
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .skipMemoryCache(true) //设置跳过内存缓存
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .into(new SimpleTarget<Bitmap>(500, 500) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        // setImageBitmap(bitmap) on CircleImageView
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }



    //预览图片加载
    public void loadPreviewPicture(int imageId,String url, final ImageView imageView, final BaseActivity activity){
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .placeholder(imageId)
                .error(imageId)
                .skipMemoryCache(true) //设置跳过内存缓存
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        activity.dismisProgressDialog();
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        activity.dismisProgressDialog();
                    }
                });
    }

    //图片加载成功或失败回调
    public void loadPreviewPicture(String url, final ImageView imageView){
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .skipMemoryCache(true) //设置跳过内存缓存
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                    }
                });
    }


    // 加载网络图片
    public void loadUrlImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .crossFade()// dontAnimate() //不开启默认动画
                .skipMemoryCache(true) //设置跳过内存缓存
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .into(imageView);
    }



    // 加载网络原图
    public void loadUrlImageLong(int imageID,String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(imageID)
                .error(imageID)
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .crossFade()// dontAnimate() //不开启默认动画
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .into(imageView);
    }


    // 加载drawable图片
    public void loadResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
//                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .crossFade()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .crossFade()
                .diskCacheStrategy( DiskCacheStrategy.RESULT ) //缓存处理后的资源
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadRCircleImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }


    // 加载drawable圆型图片
    public void loadCircleResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }


    // 加载本地圆型图片
    public void loadCircleLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()//淡入淡出动画
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    /**
     * 是图片渐显动画显示
     */
    @SuppressLint("NewApi")
    private void LocalImageAnimator(String path, ImageView imageView){
        Glide
                .with( mContext )
                .load( path )
                .animate( animationObject )
                .into( imageView );
    }



    //图片上传
    public void upLoadImage(String path){
        Glide.with(mContext)
                .load(path)
                .asBitmap()
                .toBytes()
                .centerCrop()
                .into(new SimpleTarget<byte[]>(250, 250) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public void onResourceReady(byte[] data, GlideAnimation anim) {
                        // Post your bytes to a background thread and upload them here.
                    }
                });
    }

    /**
     * 获取图片缓存路径
     * @param imageName
     * @return
     */
    public File getImagePash(String imageName){
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String saveFileName = "save";
        File appDir = new File(file ,saveFileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = imageName + ".png";
        File currentFile = new File(appDir, fileName);
        return currentFile;
    }

    /**
     * 将图片保存到本地
     * @param bmp
     * @param imageName
     */
    public void saveImageToGallery(Bitmap bmp, String imageName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getImagePash(imageName));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *保存图片到缓存文件中
     * @param path
     * @return
     */
    public File downLoadImage(String path){
        File cacheFile = null;
        try {
            FutureTarget<File> future = Glide.with(mContext)
                    .load(path)
                    .downloadOnly(500, 500);
            cacheFile = future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cacheFile;
    }

    public void displayBigImage(String uri, ImageView view) {
        Glide.with(mContext)
                .load(uri).into(view);
    }

    /**
     * 取出缓存中的某一个图片，并显示出来
     * @param url
     * @param imageView
     */
    public void showLoadImage(String url, ImageView imageView){
        Glide.with(mContext)
                .load(url)
                .centerCrop()//是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .into(imageView);
    }

    /**
     * 由图片路径加载完成后通过回调来显示图片
     * SimpleTarget target = new SimpleTarget<Bitmap>(宽度，高度) {
     *    @Override
     *    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
     *        //这里我们拿到回掉回来的bitmap，可以加载到我们想使用到的地方
     *
     *        }
     *  };
     * @param URL
     * @param target
     * @throws ExecutionException
     */
    public void loadImageSimpleTarget(String URL, SimpleTarget target){
        try {
            Glide.with( mContext ) // could be an issue!
                    .load( URL )
                    .asBitmap()   //强制转换Bitmap
                    .into( target );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载图片并获取图片的缓存路径
     * @param url
     * @return
     */
    public File loadImage(String url){
        File file = null;
        try {
            file = Glide.with(mContext)
                    .load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取缓存中的图片，并以指定大小反回Bitmap
     * @param url
     * @return
     */
    public Bitmap getLoadImage(String url, int width, int height){
        Bitmap myBitmap = null;
        try {
            myBitmap = Glide.with(mContext)
                    .load(url)
                    .asBitmap()
                    .into(width, height)
                    .get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myBitmap;
    }
    public void clearImage(){
//    Glide.get(mContext).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }

}
