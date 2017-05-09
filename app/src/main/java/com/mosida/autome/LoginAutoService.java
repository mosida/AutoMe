package com.mosida.autome;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by mosida on 5/7/17.
 */

public class LoginAutoService extends AccessibilityService {

    public static final String TAG = "LoginAutoService";

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
        if (nodeInfo == null) {
            Log.i(TAG, "nodeInfo is null");
            return;
        } else {
            Log.i(TAG, "nodeInfo is not null packageName : " + nodeInfo.getPackageName());
//            for (int i=0; i<nodeInfo.getChildCount(); i++){
//                Log.i(TAG, "****************** getViewIdResourceName : " + nodeInfo.getChild(i).getViewIdResourceName());
//                Log.i(TAG, "****************** getPackageName : " + nodeInfo.getChild(i).getPackageName());
//                Log.i(TAG, "****************** getText : " + nodeInfo.getChild(i).getText());
//                Log.i(TAG, "****************** getWindowId : " + nodeInfo.getChild(i).getWindowId());
//                Log.i(TAG, "****************** getClassName : " + nodeInfo.getChild(i).getClassName());
//            }
        }

        switch (event.getPackageName().toString()){
            case Constants.PACKAGE_GSF_LOGIN:
                Actions.existingAction(nodeInfo);
                Actions.emailsAction(nodeInfo, this);

                break;

        }
    }




    @Override
    public void onInterrupt() {

    }
}
