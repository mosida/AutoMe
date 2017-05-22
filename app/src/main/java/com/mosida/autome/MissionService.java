package com.mosida.autome;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;

/**
 * Created by mosida on 5/14/17.
 */

public class MissionService extends IntentService {

    public static final String TAG = "MissionService";



    public MissionService() {
        super("MissionService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        startGP();

    }


    public void startGP(){

        File file = new File(Environment.getExternalStorageDirectory(),
                Constants.accountFile);
        if (file.exists()){

        }else{
            Log.i(TAG, "no file for start gp!");
        }



        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.solo.security"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
