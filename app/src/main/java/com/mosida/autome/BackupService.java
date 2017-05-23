package com.mosida.autome;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;

/**
 * Created by mosida on 5/17/17.
 */

public class BackupService extends IntentService{

    public static final String TAG = "BackupService";

    public static final String titanBackupDir = "TitaniumBackup";

    public BackupService() {
        super("BackupService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        startTitan();
    }

    public boolean checkBackFileExist(){
        File sdDir = Environment.getExternalStorageDirectory();
        File file = new File(sdDir + "/" + titanBackupDir /* what you want to load in SD card */);
        Log.i(TAG, "file path : "+file.getAbsolutePath());
        if (!file.exists()) {
            return false;
        }
        return true;
    }


    public void startTitan(){
        boolean isFileExist = checkBackFileExist();
        Log.i(TAG, "the backup file is exist : "+isFileExist);
        if (isFileExist){
            Log.i(TAG, "Restore the files!");
            LoginAutoService.titanState = Constants.TITAN_RESOTRE;


        }else{
            Log.i(TAG, "Backup the files!");
            LoginAutoService.titanState = Constants.TITAN_BACKUP;

        }

        startTitanApp(this);
    }


    public static void startTitanApp(Context context){
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(Constants.PACKAGE_TITAN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        context.startActivity(intent);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
