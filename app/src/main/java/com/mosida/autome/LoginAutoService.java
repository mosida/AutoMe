package com.mosida.autome;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

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

    //
    private boolean installAction = false;
    private boolean cancelInstallAction = false;
    private boolean scrollAction = false;
    private boolean submitAction = false;




    public LoginAutoService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        Log.i(TAG, "onAccessibilityEvent eventType:"+eventType);
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

    @SuppressLint({ "NewApi"})
    private void performAutomationAction(AccessibilityEvent event) {

        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        switch (event.getPackageName().toString()){
            case Constants.PACKAGE_GSF_LOGIN:
                if (!existingAction){
                    existingAction = Actions.existingAction(nodeInfo);
                }else{
                    if (!accountAction){
                        accountAction = Actions.emailsAction(nodeInfo, this);
                    }
                    if (!pwdAction){
                        pwdAction = Actions.pwdAction(nodeInfo, this);
                    }else{
                        if (!next1Action){
                            next1Action = Actions.next1Action(nodeInfo);
                        }else {
                            if (!button1Action){
                                button1Action = Actions.button1Action(nodeInfo);
                            }else{
                                if (!next2Action){
                                    next2Action = Actions.next2Action(nodeInfo);
                                }
                            }
                        }
                    }
                }
                break;
            case Constants.PACKAGE_VENDING:
                if (!Actions.findAppCreator(nodeInfo)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=club.fromfactory"));
                    startActivity(intent);
                }else{
                    // 点击下载按钮

                }

        }
    }




    @Override
    public void onInterrupt() {

    }
}
