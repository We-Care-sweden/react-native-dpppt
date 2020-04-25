package com.dpppt;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import org.dpppt.android.sdk.DP3T;
import org.dpppt.android.sdk.internal.backend.CallbackListener;
import org.dpppt.android.sdk.internal.backend.models.ExposeeAuthData;

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
    public void init(String appId, boolean enableDevDiscoveryMode) {
        DP3T.init(getReactApplicationContext(), appId, enableDevDiscoveryMode);
    }

    @ReactMethod
    public void start() {
        DP3T.start(getReactApplicationContext());
    }

    @ReactMethod
    public void stop() {
        DP3T.stop(getReactApplicationContext());
    }

    @ReactMethod
    public void sync() {
        DP3T.sync(getReactApplicationContext());
    }

    @ReactMethod
    public void sendIWasExposed(Date date, String authCode) {
        String inputBase64 = new String(Base64.encode(authCode.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP),
                StandardCharsets.UTF_8);
        DP3T.sendIWasExposed(getReactApplicationContext(), date, new ExposeeAuthData(inputBase64),
                new CallbackListener<Void>() {
                    @Override
                    public void onSuccess(Void response) {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }
                });
    }
}
