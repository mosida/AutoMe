package com.mosida.autome;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by mosida on 2017/5/9.
 */

public class Actions {
    public static final String TAG = "Actions";

    // Existing
    // com.google.android.gsf.login:id/next_button
    // android.widget.Button
    public static final boolean existingAction(AccessibilityNodeInfo nodeInfo){
        List<AccessibilityNodeInfo> existingNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (existingNodes != null && !existingNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : existingNodes) {
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (performResult) {
//                    node.recycle();
//                    nodeInfo.recycle();
                    return true;
                }
//                node.recycle();
            }
        } else {
            Log.i(TAG, "existingNodes is null");
        }
        return false;
    }

    // Email
    // com.google.android.gsf.login:id/username_edit
    // android.widget.EditText
    public static final boolean emailsAction(AccessibilityNodeInfo nodeInfo, Context context){

        List<AccessibilityNodeInfo> emailNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/username_edit");

//        Log.i(TAG, nodeInfo.toString());

        if(emailNodes == null || emailNodes.isEmpty()) {
            Log.i(TAG, "Email node is null");
        } else {
            Log.i(TAG, "Email node is not null");

            for(AccessibilityNodeInfo node : emailNodes)
            {
                if (!node.getText().equals("Email")){
                    Log.i(TAG, "Email editText is :" + node.getText());
                    return true;
                }

                if(node.isEditable())
                {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
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
                    return true;
                }
//                node.recycle();
            }
        }
        return  false;
    }

    // Password
    // android.widget.EditText
    // com.google.android.gsf.login:id/password_edit
    public static final boolean pwdAction(AccessibilityNodeInfo nodeInfo, Context context){
        List<AccessibilityNodeInfo> pwdNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/password_edit");

//        Log.i(TAG, nodeInfo.toString());

        if(pwdNodes == null || pwdNodes.isEmpty()) {
            Log.i(TAG, "Password node is null");
        } else {
            Log.i(TAG, "Password node is not null");
            for(AccessibilityNodeInfo node : pwdNodes)
            {
                node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
//                NullPointerException
//                if (!node.getText().equals("Password")){
//                    Log.i(TAG, "Password editText is :" + node.getText());
//                    return true;
//                }
                if(node.isEditable())
                {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Password", Constants.demoPwd);
                    clipboard.setPrimaryClip(clip);
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    Log.i(TAG, "Password perform result is " + performResult);
                    clipboard.setPrimaryClip(ClipData.newPlainText("", ""));
					        /*if(performResult)
					        {
								node.recycle();
								return;
					        }*/
                    node.recycle();
                    return  true;
                }
//                node.recycle();
            }
        }
        return false;
    }

    // next1
    // com.google.android.gsf.login:id/next_button
    // android.widget.Button
    public static final boolean next1Action(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> next1Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (next1Nodes != null && !next1Nodes.isEmpty()) {

            for (AccessibilityNodeInfo node : next1Nodes) {
                if(node.isEnabled())
                {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "next1Nodes perform result is " + performResult);
                    if(performResult)
                    {
//                        if(GmailActiveAutomationService.isGmailActiveService)
//                        {
//                            isInputedPassword = false;
//                        }
//                        node.recycle();
                        return true;
                    }
                }
//                node.recycle();
            }
        } else {
            Log.i(TAG, "next1Nodes is null");
        }
        return false;
    }


    // OK
    // android:id/button1
    // android.widget.Button
    public static final boolean button1Action(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> button1Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button1");
        if (button1Nodes != null && !button1Nodes.isEmpty()) {

            for (AccessibilityNodeInfo node : button1Nodes) {
                if(node.isEnabled())
                {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "button1Nodes perform result is " + performResult);
                    if(performResult)
                    {
//                        if(GmailActiveAutomationService.isGmailActiveService)
//                        {
//                            isInputedPassword = false;
//                        }
//                        node.recycle();
                        return true;
                    }
                }
//                node.recycle();
            }
        } else {
            Log.i(TAG, "button1Nodes is null");
        }
        return false;
    }


    // android.widget.Button
    // com.google.android.gsf.login:id/next_button
    public static final boolean next2Action(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo==null){
            return false;
        }

        List<AccessibilityNodeInfo> next2Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (next2Nodes != null && !next2Nodes.isEmpty()) {

            for (AccessibilityNodeInfo node : next2Nodes) {
                if(node.isEnabled())
                {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "next2Nodes perform result is " + performResult);
                    if(performResult)
                    {
//                        if(GmailActiveAutomationService.isGmailActiveService)
//                        {
//                            isInputedPassword = false;
//                        }
//                        node.recycle();
                        return true;
                    }
                }
//                node.recycle();
            }
        } else {
            Log.i(TAG, "next2Nodes is null");
        }
        return false;
    }

}
