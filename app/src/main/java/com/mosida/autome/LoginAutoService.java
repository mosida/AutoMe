package com.mosida.autome;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mosida on 5/7/17.
 */

public class LoginAutoService extends AccessibilityService {

    public static final String TAG = "LoginAutoService";
    // 登录步骤
    private boolean existingAction = false;
    private boolean accountAction = false;
    private boolean pwdAction = false;
    private boolean next1Action = false;
    private boolean button1Action = false;
    private boolean next2Action = false;

    public static int num = 0;

    // node Actions
    private boolean findAppCreatorAction = false;
    private boolean installAction = false;
    private boolean cancelInstallAction = false;
    private boolean scrollAction = false;
    private boolean submitAction = false;
    private boolean acceptAction = false;
//    private boolean torAcceptAction = false;
    private boolean notNowAction = false;
    private boolean signinSuccessfulAction = false;
    private boolean downloadLargeAppAction = false;
    private boolean getStartedAction = false;


    // node Name
    public static String emailNodeName;
    public static String pwdNodeName;
    public static String reviewContinueButtonNodeName;
    public static String reviewFinishButtonNodeName;
    public static String tellNodeName;
    public static String yesNodeName;

    private static String lang;


    public static String titanState = Constants.TITAN_BACKUP;


    @Override
    public void onCreate() {
        super.onCreate();

        lang = Utils.getCurrentLauguage();
        switch (lang) {
            case Constants.LANG_ZH:
                emailNodeName = Constants.EMAIL_NODE_NAME_ZH;
                pwdNodeName = Constants.PWD_NODE_NAME_ZH;
                reviewContinueButtonNodeName = Constants.REVIEWCONTINUEBUTTON_NODE_NAME_ZH;
                reviewFinishButtonNodeName = Constants.REVIEWFINISHBUTTON_NODE_NAME_ZH;
                tellNodeName = Constants.TELL_NODE_NAME_ZH;
                yesNodeName = Constants.YES_NODE_NAME_Zh;
                break;
            case Constants.LANG_EN:
                emailNodeName = Constants.EMAIL_NODE_NAME_EN;
                pwdNodeName = Constants.PWD_NODE_NAME_EN;
                reviewContinueButtonNodeName = Constants.REVIEWCONTINUEBUTTON_NODE_NAME_EN;
                reviewFinishButtonNodeName = Constants.REVIEWFINISHBUTTON_NODE_NAME_EN;
                tellNodeName = Constants.TELL_NODE_NAME_EN;
                yesNodeName = Constants.YES_NODE_NAME_EN;
                break;
            default:
                break;
        }
//        startGP();
    }

    public static GmailInfo gmailInfo;
    public static String missionState;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (gmailInfo==null){
            File file = new File(Environment.getExternalStorageDirectory(),
                    Constants.accountFile);
            if (file.exists()){
                Log.i(TAG, "file.exists!");
            }else{
                Log.i(TAG, "no file for start gp!");
                return;
            }
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    String result = new String(b);
                    Log.i(TAG, "file data is : "+ result);
                    String[] account = result.split(",");
                    gmailInfo = new GmailInfo();
                    gmailInfo.email = account[0];
                    gmailInfo.password = account[1];
                    gmailInfo.gid = account[2];
                    gmailInfo.comment = account[3];
                    gmailInfo.packageName = account[4];
                    gmailInfo.appCreator = account[5];
                    if (gmailInfo.appCreator.equals("Active")){
                        missionState = "Active";
                    }else{
                        missionState = "Reivew";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.i(TAG, "read myaccount file fail!");
            }

        }


        int eventType = event.getEventType();
        Log.i(TAG, "onAccessibilityEvent eventType : " + eventType);
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.i(TAG, "TYPE_WINDOW_STATE_CHANGED");
                performAutomationAction(event);
                break;

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.i(TAG, "TYPE_WINDOW_CONTENT_CHANGED");
                performAutomationAction(event);
                break;
//            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                Log.i(TAG, "TYPE_VIEW_CLICKED");
//                performAutomationAction(event);
//                break;
            default:
                break;
        }
    }


    @SuppressLint({"NewApi"})
    private void performAutomationAction(AccessibilityEvent event) {

        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();

        Log.i(TAG, "packageName : " + event.getPackageName().toString());

        switch (event.getPackageName().toString()) {
            case Constants.PACKAGE_GSF_LOGIN:
                if (!existingAction) {
                    existingAction = Actions.existingAction(nodeInfo);
                } else {
                    if (!accountAction) {
                        accountAction = Actions.emailsAction(nodeInfo, this);
                    }
                    if (!pwdAction) {
                        pwdAction = Actions.pwdAction(nodeInfo, this);
                    } else {
                        if (!next1Action) {
                            next1Action = Actions.next1Action(nodeInfo);
                        } else {
                            if (!button1Action) {
                                button1Action = Actions.button1Action(nodeInfo);
                            } else {
                                if (!next2Action) {
                                    next2Action = Actions.next2Action(nodeInfo);
                                }

                                if (!notNowAction) {
                                    notNowAction = Actions.notNowAction(nodeInfo);
                                }

                                if (!signinSuccessfulAction) {
                                    signinSuccessfulAction = Actions.existingAction(nodeInfo);
                                }

                            }
                        }
                    }
                }
                break;
            case Constants.PACKAGE_VENDING:
                Log.i(TAG, "PACKAGE_VENDING");
                if (!notNowAction) {
                    notNowAction = Actions.notNowAction(nodeInfo);
                }

                Actions.playDrawerListShowup(nodeInfo, this);
                Actions.completeAccountSetupAction(nodeInfo);
                Actions.retryAction(nodeInfo);
//                if (!torAcceptAction){
                Actions.torAcceptAction(nodeInfo, this);
//                }

                if (!getStartedAction) {
                    getStartedAction = Actions.getStartedAction(nodeInfo, this);
                }

                if (missionState.equals("Active")){
                    try{
                        if (num>3){
                            Intent intent = new Intent(this, BackupService.class);
                            startService(intent);
                            num=-1;
                        }else if (num==-1){

                        }else{
                            Thread.sleep(10000);
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.namo.telljokes"));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            num++;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if (missionState.equals("Reivew")) {
                    if (!findAppCreatorAction) {
                        findAppCreatorAction = Actions.findAppCreator(nodeInfo);
                        Log.i(TAG, "findAppCreatorAction is : " + findAppCreatorAction);

                        if (!findAppCreatorAction) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + gmailInfo.packageName));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        Log.i(TAG, "findAppCreatorAction false");
                    } else {
                        // 下载游戏
                        if (!downloadLargeAppAction) {
                            Log.i(TAG, "downloadLargeAppAction");
                            downloadLargeAppAction = Actions.downloadLargeAppAction(nodeInfo);
                        }


                        // 点击下载按钮
                        if (!installAction) {
                            Log.i(TAG, "installAction false");

                            installAction = Actions.installAction(nodeInfo);
                        } else {
                            if (!acceptAction) {
                                Log.i(TAG, "acceptAction false");
                                acceptAction = Actions.acceptAction(nodeInfo);
                            } else {

                                if (!cancelInstallAction) {
                                    Log.i(TAG, "cancelInstallAction false");
                                    cancelInstallAction = Actions.cancelDownloadAction(nodeInfo);
                                } else {
                                    if (!scrollAction) {
                                        Log.i(TAG, "scrollAction false");
                                        scrollAction = Actions.findReviewCard(nodeInfo);
                                    } else {
                                        if (!submitAction) {
                                            Log.i(TAG, "submitAction false");
                                            submitAction = Actions.reviewAction(nodeInfo, this);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                break;
            case Constants.PACKAGE_TITAN:
                Log.i(TAG, "PACKAGE_TITAN");
                TitanActions.waringAction(nodeInfo);
                if (!isCheckAction) {
                    isCheckAction = TitanActions.checkAction(nodeInfo);
                }
                //                if (titanState.equals(Constants.TITAN_RESOTRE)){
//
//                }
                if (titanState.equals(Constants.TITAN_BACKUP)) {


                    if (!isBackAllAppsAction) {
                        isBackAllAppsAction = TitanActions.backupAllAppAction(nodeInfo);
                    }
                    if (!isDeselectAction) {
                        isDeselectAction = TitanActions.deselectAllAction(nodeInfo);
                    } else {
                        if (!isAccountsCBAction) {
                            isAccountsCBAction = TitanActions.checkboxAccountsAction(nodeInfo);
                        }
                        if (!isBackupconfirmAction) {
                            isBackupconfirmAction = TitanActions.checkboxBackupconfirmAction(nodeInfo);
                        }
                        if (!isKeyguardAction) {
                            isKeyguardAction = TitanActions.checkboxKeyguardAction(nodeInfo);
                        }
                        if (!isSharedstoragebackupAction) {
                            isSharedstoragebackupAction = TitanActions.checkboxSSBackupAction(nodeInfo);
                        }
                        if (!isGmailAction) {
                            isGmailAction = TitanActions.checkboxGmailAction(nodeInfo);
                        }
                        if (!isGAMAction) {
                            isGAMAction = TitanActions.checkboxGAMAction(nodeInfo);
                        }
                        if (!isGBTAction) {
                            isGBTAction = TitanActions.checkboxGBTAction(nodeInfo);
                        }
                        if (!isGPSERAction) {
                            isGPSERAction = TitanActions.checkboxGPSERAction(nodeInfo);
                        }
                        if (!isGPSTOAction) {
                            isGPSTOAction = TitanActions.checkboxGPSTOAction(nodeInfo);
                        }
                        if (!isGSFAction) {
                            isGSFAction = TitanActions.checkboxGSFAction(nodeInfo);
                        }
                        if (!isGPlusAction) {
                            isGPlusAction = TitanActions.checkboxGPlusAction(nodeInfo);
                        }
                        if (!isSSAction) {
                            isSSAction = TitanActions.checkboxSSAction(nodeInfo);
                        }

                        if (isAccountsCBAction && isBackupconfirmAction && isKeyguardAction
                                && isSharedstoragebackupAction && isGmailAction
                                && isGAMAction && isGBTAction && isGPSERAction
                                && isGPSTOAction && isGSFAction && isGPlusAction && isSSAction) {
                            if (!isBackupAction) {
                                isBackupAction = TitanActions.backupAction();
                            }
                        } else {
                            TitanActions.scrollAction();
                        }

                    }
                }
                break;
            default:
                break;
        }
    }

    boolean isBackupAction = false;
    boolean isBackAllAppsAction = false;
    boolean isCheckAction = false;
    boolean isDeselectAction = false;
    boolean isAccountsCBAction = false;
    boolean isBackupconfirmAction = false;
    boolean isKeyguardAction = false;
    boolean isSharedstoragebackupAction = false;
    boolean isGmailAction = false;
    boolean isGAMAction = false;
    boolean isGBTAction = false;
    boolean isGPSERAction = false;
    boolean isGPSTOAction = false;
    boolean isGSFAction = false;
    boolean isGPlusAction = false;
    boolean isSSAction = false;


    @Override
    public void onInterrupt() {

    }
}
