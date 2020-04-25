package com.dpppt;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import org.dpppt.android.sdk.DP3T;

public class DppptModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public DppptModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Dpppt";
    }

    @ReactMethod
    public void init(String appId) {
        DP3T.init(getReactApplicationContext(), appId);
    }

    @ReactMethod
    public void start(String appId) {
        DP3T.start(getReactApplicationContext());
    }

    @ReactMethod
    public void stop(String appId) {
        DP3T.stop(getReactApplicationContext());
    }

    @ReactMethod
    public void sync(String appId) {
        DP3T.sync(getReactApplicationContext());
    }

    @ReactMethod
    public void sendIWasExposed() {
        DP3T.sendIWasExposed(getReactApplicationContext(), null, new CallbackListener<Void>() {
            @Override
            public void onSuccess(Void response) {
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }
}
