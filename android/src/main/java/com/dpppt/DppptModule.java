package com.dpppt;

import android.net.Uri;
import android.util.Base64;
import android.content.Intent;
import android.Manifest;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import org.dpppt.android.sdk.DP3T;
import org.dpppt.android.sdk.TracingStatus;
import org.dpppt.android.sdk.backend.models.ExposeeAuthMethodJson;
import org.dpppt.android.sdk.backend.ResponseCallback;

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
    public void status(Promise promise) {
        TracingStatus tracingStatus = DP3T.getStatus(getReactApplicationContext());
        WritableMap map = Arguments.createMap();
        map.putInt("numberOfContacts", tracingStatus.getNumberOfContacts());
        map.putBoolean("isAdvertising", tracingStatus.isAdvertising());
        map.putBoolean("isReceiving", tracingStatus.isReceiving());
        map.putDouble("lastSyncDate", tracingStatus.getLastSyncDate());
        promise.resolve(map);
    }

    @ReactMethod
    public void sendIAmInfected(Date date, String codeInputBase64) {
        DP3T.sendIAmInfected(getReactApplicationContext(), date, new ExposeeAuthMethodJson(codeInputBase64),
                new ResponseCallback<Void>() {
                    @Override
                    public void onSuccess(Void response) {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }
                });
    }

    @ReactMethod
    public void requestIgnoreBatteryOptimizations() {
        getReactApplicationContext().startActivity(new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                Uri.parse("package:" + getReactApplicationContext().getPackageName())));
    }

    @ReactMethod
    public void requestLocationAccess() {
        String[] permissions = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
        ActivityCompat.requestPermissions(getReactApplicationContext(), permission, 1);
    }
}
