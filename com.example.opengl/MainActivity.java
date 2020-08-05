package com.example.opengl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = manager.getDeviceConfigurationInfo();

        boolean supportES2 = info.reqGlEsVersion >= 0x20000;
        if (supportES2) {

            MainRenderer renderer = new MainRenderer();
            MainSurface surfaceView = new MainSurface(this);

            surfaceView.setEGLContextClientVersion(2);
            surfaceView.setRenderer(renderer);

            this.setContentView(surfaceView);
        }
        else {
            Log.e("OPENGL", "your device doesn't support OpenGLES 2");
        }
    }
}
