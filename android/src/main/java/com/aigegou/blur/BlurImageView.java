package com.aigegou.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import javax.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;

import java.io.File;
import java.io.IOException;

/**
 * Created by herbert on 3/25/16.
 */
public class BlurImageView extends ImageView {

    private final Context context;
    private final BlurImageView thisImg;
    private String color;
    private int radius;
    private int sampling;
    private String imageUrl;
    private GetHeadBitmapTask getheadBitmapTask;

    public BlurImageView(Context context) {
        super(context);
        this.context = context;
        thisImg = this;
        this.setScaleType(ScaleType.FIT_XY);
    }

    public BlurImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        thisImg = this;
        this.setScaleType(ScaleType.FIT_XY);
    }

    public void setImageUrlAndUpdate(String imageUrl) {
        setImageUrl(imageUrl);
        Render();
    }

    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRadiusAndUpdate(int radius) {
        setRadius(radius);
        Render();
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    public void setSamplingAndUpdate(int sampling) {
        setSampling(sampling);
        Render();
    }

    private void setSampling(int sampling) {
        this.sampling = sampling;
    }

    public void setColorAndUpdate(String color) {
        this.color = color;
        Render();
    }

    private void Render() {
        if (!TextUtils.isEmpty(imageUrl)) {
            if (getheadBitmapTask != null) {
                getheadBitmapTask.cancel(true);
                getheadBitmapTask = null;
            }
            getheadBitmapTask = new GetHeadBitmapTask();
            getheadBitmapTask.execute(imageUrl);
        }
    }

    class GetHeadBitmapTask extends AsyncTask<String, String, Bitmap> {
        Bitmap bitmap = null;
        String imageUrl = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                imageUrl = strings[0];
                bitmap = loadImg(context, imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                Bitmap blurredBitmap = fastBlurImage(bitmap);
                thisImg.setImageBitmap(blurredBitmap);
            }
        }
    }

    public static Bitmap loadImg(Context context, String url) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(url.trim())) {
            try {
                bitmap = Picasso.with(context).load(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private Bitmap fastBlurImage(Bitmap bitmap) {
        Bitmap bitmapBlur = null;

        if (bitmap != null) {
            bitmapBlur = FastBlur.of(context, bitmap, this.radius, this.sampling);
        }

        return bitmapBlur;
    }



}
