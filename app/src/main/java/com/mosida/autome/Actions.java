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
    public static final boolean existingAction(AccessibilityNodeInfo nodeInfo) {
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
    public static final boolean emailsAction(AccessibilityNodeInfo nodeInfo, Context context) {

        List<AccessibilityNodeInfo> emailNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/username_edit");

//        Log.i(TAG, nodeInfo.toString());

        if (emailNodes == null || emailNodes.isEmpty()) {
            Log.i(TAG, "Email node is null");
        } else {
            Log.i(TAG, "Email node is not null");

            for (AccessibilityNodeInfo node : emailNodes) {
                if (!node.getText().equals("Email")) {
                    Log.i(TAG, "Email editText is :" + node.getText());
                    return true;
                }

                if (node.isEditable()) {
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
        return false;
    }

    // Password
    // android.widget.EditText
    // com.google.android.gsf.login:id/password_edit
    public static final boolean pwdAction(AccessibilityNodeInfo nodeInfo, Context context) {
        List<AccessibilityNodeInfo> pwdNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/password_edit");

//        Log.i(TAG, nodeInfo.toString());

        if (pwdNodes == null || pwdNodes.isEmpty()) {
            Log.i(TAG, "Password node is null");
        } else {
            Log.i(TAG, "Password node is not null");
            for (AccessibilityNodeInfo node : pwdNodes) {
                node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
//                NullPointerException
//                if (!node.getText().equals("Password")){
//                    Log.i(TAG, "Password editText is :" + node.getText());
//                    return true;
//                }
                if (node.isEditable()) {
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
                    return true;
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
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "next1Nodes perform result is " + performResult);
                    if (performResult) {
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
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "button1Nodes perform result is " + performResult);
                    if (performResult) {
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
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> next2Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (next2Nodes != null && !next2Nodes.isEmpty()) {

            for (AccessibilityNodeInfo node : next2Nodes) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "next2Nodes perform result is " + performResult);
                    if (performResult) {
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

    // INSTALL
    // com.android.vending
    // android.widget.Button
    // com.android.vending:id/buy_button
    public static final boolean installAction(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> installNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/buy_button");
        if (installNodes != null && !installNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : installNodes) {
                node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i(TAG, "installNodes perform result is " + performResult);
                if (performResult) {
//                        if(GmailActiveAutomationService.isGmailActiveService)
//                        {
//                            isInputedPassword = false;
//                        }
//                        node.recycle();
                    return true;
                }

//                node.recycle();
            }
        } else {
            Log.i(TAG, "installNodes is null");
        }
        return false;
    }

    // ACCEPT
    // com.android.vending
    // android.widget.Button
    // com.android.vending:id/continue_button
    public static final boolean acceptAction(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> acceptNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/continue_button");
        if (acceptNodes != null && !acceptNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : acceptNodes) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "acceptNodes perform result is " + performResult);
                    if (performResult) {
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
            Log.i(TAG, "acceptNodes is null");
        }
        return false;
    }

    // com.android.vending:id/cancel_download
    // android.widget.ImageView
    // com.android.vending
    public static final boolean cancelDownloadAction(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> cancelNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/cancel_download");
        if (cancelNodes != null && !cancelNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : cancelNodes) {
                if (node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "cancelNodes perform result is " + performResult);
                    if (performResult) {
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
            Log.i(TAG, "cancelNodes is null");
        }
        return false;
    }


    public static final boolean reviewAction(AccessibilityNodeInfo nodeInfo, Context context){
        if (nodeInfo == null) {
            return false;
        }

        // com.android.vending:id/review_continue_button
        List<AccessibilityNodeInfo> submitNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_continue_button");
        if (submitNodes != null && !submitNodes.isEmpty()){
            for (AccessibilityNodeInfo node : submitNodes) {
                if (node.isEnabled()){
                    if (node.getText().toString().equals("Submit")){
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        return false;
                    }
                    if (node.getText().toString().equals("Finish")){
                        List<AccessibilityNodeInfo> tellNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_comment");
                        if (tellNodes != null && !tellNodes.isEmpty()){
                            for (AccessibilityNodeInfo node2 : tellNodes) {
                                if (node2.isEditable()) {
                                    if (!node2.getText().toString().equals("Tell us what you think")){
                                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        return true;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        // com.android.vending:id/star5
        List<AccessibilityNodeInfo> star5Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/star5");
        if (star5Nodes != null && !star5Nodes.isEmpty()){
            for (AccessibilityNodeInfo node : star5Nodes) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                return false;
            }
        }

        // com.android.vending:id/question_option_text
        List<AccessibilityNodeInfo> yesNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/question_option_text");
        if (yesNodes != null && !yesNodes.isEmpty()){
            for (AccessibilityNodeInfo node : yesNodes) {
                if (node.isEnabled()==true) {

                    node.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);

                }
            }
        }


        // com.android.vending:id/review_comment
        List<AccessibilityNodeInfo> tellNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_comment");
        if (tellNodes != null && !tellNodes.isEmpty()){
            for (AccessibilityNodeInfo node : tellNodes) {
                if (node.isEditable()) {
                    if (!node.getText().toString().equals("Tell us what you think")){
                        return false;
                    }
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);

                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Reviews", Constants.demoReview);
                    clipboard.setPrimaryClip(clip);
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    Log.i(TAG, "Reviews perform result is " + performResult);
                    clipboard.setPrimaryClip(ClipData.newPlainText("", ""));
//                    return false;
                }
//                return false;
            }
        }
        return false;
    }

    // com.android.vending:id/star5
    // android.widget.ImageView
    // com.android.vending


    // Submit
    // android.widget.Button
    // com.android.vending
    // com.android.vending:id/review_continue_button

    // com.android.vending:id/star5
    // android.widget.ImageView
    // com.android.vending

    // Tell us what you think
    // com.android.vending:id/review_comment
    // android.widget.EditText
    // com.android.vending

    // Finish
    // com.android.vending:id/review_continue_button
    // android.widget.Button
    // com.android.vending

    // Yes/Not Sure/No
    // com.android.vending:id/question_option_text
    // com.android.vending
    // android.widget.TextView

    // Factory Club
    // android.widget.TextView
    // com.android.vending:id/title_creator
    // com.android.vending
    public static final boolean findAppCreator(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            Log.i(TAG, "nodeInfo is : null by findAppCreator");
            return false;
        }
        Log.i(TAG, "nodeInfo is not null by findAppCreator");

        List<AccessibilityNodeInfo> creatorNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/title_creator");
        if (creatorNodes != null && !creatorNodes.isEmpty()) {
            Log.i(TAG, "creatorNodes is not null");

            for (AccessibilityNodeInfo node : creatorNodes) {
                if (node.getText().toString().equals(Constants.demoAppCreator)) {
                    Log.i(TAG, "creatorNodes is : "+ node.getText()+ " return true");

                    return true;
                } else {
                    Log.i(TAG, "creatorNodes is : "+ node.getText() +" return false");

                    return false;
                }
            }
        } else {
            Log.i(TAG, "creatorNodes is null");
        }
        return false;
    }


    // com.android.vending
    // android.widget.LinearLayout
    // com.android.vending:id/question_card
    // com.android.vending:id/review_continue_button
    public static final boolean findReviewCard(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> cardNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_continue_button");
        if (cardNodes != null && !cardNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : cardNodes) {
                if (node.isEnabled() == false) {
                    return true;
                }
            }
        } else {
            Log.i(TAG, "cardNodes is null and Scroll");
            List<AccessibilityNodeInfo> recyclerViewNode = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/recycler_view");
            if (recyclerViewNode!=null && !recyclerViewNode.isEmpty()) {
                for (AccessibilityNodeInfo node : recyclerViewNode){
                    node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                }
            }
        }
        return false;
    }
}
