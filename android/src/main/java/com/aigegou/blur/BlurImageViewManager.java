package com.aigegou.blur;

import com.aigegou.blur.BlurImageView;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
/**
 * Created by herbert on 3/25/16.
 */
public class BlurImageViewManager extends SimpleViewManager<BlurImageView> {
    public static final String REACT_CLASS = "RCTBlurImageView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected BlurImageView createViewInstance(ThemedReactContext reactContext) {
        return new BlurImageView(reactContext);
    }
    @ReactProp(name = "blurRadius", defaultInt = 0)
    public void setRadius(BlurImageView view, int radius) {
        int adjustedRadius = radius / 4;
        view.setRadiusAndUpdate(adjustedRadius);
    }

    @ReactProp(name = "sampling", defaultInt = 1)
    public void setSampling(BlurImageView view, int sampling) {
        view.setSamplingAndUpdate(sampling);
    }

    @ReactProp(name = "source")
    public void setSource(BlurImageView view, @Nullable ReadableMap sources) {
        String url = sources.getString("uri");
        view.setImageUrlAndUpdate(url);
    }
}
