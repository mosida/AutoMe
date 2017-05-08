package com.mosida.autome;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by mosida on 5/7/17.
 */

public class LoginAutoService extends AccessibilityService {

    public static final String TAG = "LoginAutoService";

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
        if (event.getPackageName().equals("com.google.android.gsf.login")){
            Log.i(TAG, "packageName is right");
        }else{
            Log.i(TAG, "packageName is not right, goodbye");
            return;
        }

        AccessibilityNodeInfo nodeInfo = event.getSource();
        if (nodeInfo == null) {
            Log.i(TAG, "nodeInfo is null");
            return;
        } else {
            Log.i(TAG, "nodeInfo is not null packageName : " + nodeInfo.getPackageName());
            for (int i=0; i<nodeInfo.getChildCount(); i++){
                Log.i(TAG, "****************** getViewIdResourceName : " + nodeInfo.getChild(i).getViewIdResourceName());
                Log.i(TAG, "****************** getPackageName : " + nodeInfo.getChild(i).getPackageName());
                Log.i(TAG, "****************** getText : " + nodeInfo.getChild(i).getText());
                Log.i(TAG, "****************** getWindowId : " + nodeInfo.getChild(i).getWindowId());
                Log.i(TAG, "****************** getClassName : " + nodeInfo.getChild(i).getClassName());


            }
        }

        // Existing
        // com.google.android.gsf.login:id/next_button
        // android.widget.Button
        List<AccessibilityNodeInfo> existingNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (existingNodes != null && !existingNodes.isEmpty()) {
            for (AccessibilityNodeInfo node : existingNodes) {
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (performResult) {
                    node.recycle();
                    nodeInfo.recycle();
                    return;
                }
                node.recycle();
            }
        } else {
            Log.i(TAG, "existingNodes is null");
        }

        // Email
        // com.google.android.gsf.login:id/username_edit
        // android.widget.EditText
        List<AccessibilityNodeInfo> emailNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/username_edit");
        if(emailNodes == null || emailNodes.isEmpty()) {
            Log.i(TAG, "Email node is null");
        } else {
            Log.i(TAG, "Email node is not null");

            for(AccessibilityNodeInfo node : emailNodes)
            {
                if(node.isEditable())
                {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Email", Constants.demoAccount);
                    clipboard.setPrimaryClip(clip);
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    Log.i(TAG, "Email perform result is " + performResult);
                    clipboard.setPrimaryClip(ClipData.newPlainText("", ""));
					        /*if(performResult)
					        {
								node.recycle();
								return;
					        }*/
                }
                node.recycle();
            }
        }



    }

    //根据文字列表找出满足的元素
    private final AccessibilityNodeInfo getNodeInfoFromStringList(AccessibilityNodeInfo accessibilityNodeInfo, List list) {
        AccessibilityNodeInfo accessibilityNodeInfo2 = null;
        int i = 0;
        while (accessibilityNodeInfo2 == null && i < accessibilityNodeInfo.getChildCount()) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
            if (child == null) {
                child = accessibilityNodeInfo2;
            } else if (child.getText() == null || !list.contains(child.getText().toString())) {
                child = getNodeInfoFromStringList(child, list);
            } else {
                Log.i(TAG, "settingService: 选中的名字是"+child.getText().toString());
            }
            i++;
            accessibilityNodeInfo2 = child;
        }
        return accessibilityNodeInfo2;
    }

    @Override
    public void onInterrupt() {

    }
}
