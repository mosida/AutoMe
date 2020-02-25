package com.mosida.autome;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
    public static final boolean next_buttonAction(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> existingNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
        if (existingNodes != null && !existingNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : existingNodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "next_buttonAction is null");
        }
        return false;
    }

    // Email
    // com.google.android.gsf.login:id/username_edit
    // android.widget.EditText
    public static final boolean emailsAction(AccessibilityNodeInfo nodeInfo, Context context) {
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> emailNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/username_edit");

//        Log.i(TAG, nodeInfo.toString());

        if (emailNodes == null || emailNodes.isEmpty()) {
            Log.i(TAG, "Email node is null");
        } else {
            Log.i(TAG, "Email node is not null");

            for (AccessibilityNodeInfo node : emailNodes) {
                if (!node.getText().equals(LoginAutoService.emailNodeName)) {
                    Log.i(TAG, "Email editText is :" + node.getText());
                    return true;
                }

                if (node.isEditable()) {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(LoginAutoService.emailNodeName, LoginAutoService.gmailInfo.email);
                    clipboard.setPrimaryClip(clip);
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    Log.i(TAG, "Email perform result is " + performResult);
                    clipboard.setPrimaryClip(ClipData.newPlainText("", ""));
                    return true;
                }
            }
        }
        return false;
    }

    // Password
    // android.widget.EditText
    // com.google.android.gsf.login:id/password_edit
    public static final boolean pwdAction(AccessibilityNodeInfo nodeInfo, Context context) {
        if (nodeInfo == null) {
            return false;
        }
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
                    ClipData clip = ClipData.newPlainText(LoginAutoService.pwdNodeName, LoginAutoService.gmailInfo.password);
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
//    public static final boolean next1Action(AccessibilityNodeInfo nodeInfo) {
//        if (nodeInfo == null) {
//            return false;
//        }
//        List<AccessibilityNodeInfo> next1Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
//        if (next1Nodes != null && !next1Nodes.isEmpty()) {
//
//            for (AccessibilityNodeInfo node : next1Nodes) {
//                if (node.isEnabled()) {
//                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
//                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    Log.i(TAG, "next1Nodes perform result is " + performResult);
//                    if (performResult) {
////                        if(GmailActiveAutomationService.isGmailActiveService)
////                        {
////                            isInputedPassword = false;
////                        }
////                        node.recycle();
//                        return true;
//                    }
//                }
////                node.recycle();
//            }
//        } else {
//            Log.i(TAG, "next1Nodes is null");
//        }
//        return false;
//    }


    // OK
    // android:id/button1
    // android.widget.Button
    public static final boolean button1Action(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> button1Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button1");
        if (button1Nodes != null && !button1Nodes.isEmpty()) {

            for (AccessibilityNodeInfo node : button1Nodes) {
                if (node!=null && node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "button1Nodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "button1Nodes is null");
        }
        return false;
    }


    // android.widget.Button
    // com.google.android.gsf.login:id/next_button
//    public static final boolean next2Action(AccessibilityNodeInfo nodeInfo) {
//        if (nodeInfo == null) {
//            return false;
//        }
//
//        List<AccessibilityNodeInfo> next2Nodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
//        if (next2Nodes != null && !next2Nodes.isEmpty()) {
//
//            for (AccessibilityNodeInfo node : next2Nodes) {
//                if (node.isEnabled()) {
//                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
//                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    Log.i(TAG, "next2Nodes perform result is " + performResult);
//                    if (performResult) {
//                        return true;
//                    }
//                }
//            }
//        } else {
//            Log.i(TAG, "next2Nodes is null");
//        }
//        return false;
//    }

    // com.android.vending:id/positive_button
    // android.widget.Button
    // com.android.vending
    // ACCEPT
    public static final boolean torAcceptAction(AccessibilityNodeInfo nodeInfo, Context context) {
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> torAcceptNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/positive_button");
        if (torAcceptNodes != null && !torAcceptNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : torAcceptNodes) {
                if (node!=null){
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "torAcceptNodes perform result is " + performResult);
                    if (performResult) {
                        try {
                            Thread.sleep(10000);
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + LoginAutoService.gmailInfo.packageName));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "torAcceptNodes is null");
        }

        return false;
    }

    // com.android.vending:id/not_now_button
    // NO THANKS
    // android.widget.TextView
    // com.android.vending
    public static final boolean notNowAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            return false;
        }

        List<AccessibilityNodeInfo> notNowNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/not_now_button");
        if (notNowNodes != null && !notNowNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : notNowNodes) {
                node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i(TAG, "notNowNodes perform result is " + performResult);
                if (performResult) {
                    try {
                        Thread.sleep(3000);
                        ShellUtils.execCommand("input tap 600 750", true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        } else {
            Log.i(TAG, "notNowNodes is null");
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
                if (node!=null){
                    try {
                        Thread.sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "installNodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
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
                if (node!=null && node.isEnabled()) {
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "acceptNodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
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
                if (node!=null && node.isEnabled()) {
                    try {
                        Thread.sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "cancelNodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
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
                    if (node.getText().toString().equals(LoginAutoService.reviewContinueButtonNodeName)){
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        return false;
                    }
                    if (node.getText().toString().equals(LoginAutoService.reviewFinishButtonNodeName)){
                        List<AccessibilityNodeInfo> tellNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_comment");
                        if (tellNodes != null && !tellNodes.isEmpty()){
                            for (AccessibilityNodeInfo node2 : tellNodes) {
                                if (node2.isEditable()) {
                                    if (!node2.getText().toString().equals(LoginAutoService.tellNodeName)){
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

        // Yes
        // com.android.vending:id/question_option_text
        List<AccessibilityNodeInfo> yesNodes = nodeInfo.findAccessibilityNodeInfosByText(LoginAutoService.yesNodeName);
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
                    if (!node.getText().toString().equals(LoginAutoService.tellNodeName)){
                        return false;
                    }
                    node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);

                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Reviews", LoginAutoService.gmailInfo.comment);
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
                if (node.getText().toString().equals(LoginAutoService.gmailInfo.appCreator)) {
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


    // Proceed
    // com.android.vending
    // android.widget.Button
    // android:id/button1
    public static final boolean downloadLargeAppAction(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> downloadLargeAppNodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button1");
        if (downloadLargeAppNodes != null && !downloadLargeAppNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : downloadLargeAppNodes) {
                node.performAction(AccessibilityNodeInfo.ACTION_FOCUS); // 获取焦点
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i(TAG, "downloadLargeAppNodes perform result is " + performResult);
                if (performResult) {
                    return true;
                }
            }
        } else {
            Log.i(TAG, "downloadLargeAppNodes is null");
        }
        return false;
    }

    // android.widget.ListView
    // com.android.vending:id/play_drawer_list
    // com.android.vending
    public static final boolean playDrawerListShowup(AccessibilityNodeInfo nodeInfo, Context context) {
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> downloadLargeAppNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/play_drawer_list");
        if (downloadLargeAppNodes != null && !downloadLargeAppNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : downloadLargeAppNodes) {
                if (node!=null){
                    Log.i(TAG, "playDrawerListShowup is showup right now!");
                    ShellUtils.execCommand("input tap 650 218", true);

//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+LoginAutoService.gmailInfo.packageName));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                    return true;
                }
            }
        } else {
            Log.i(TAG, "playDrawerListShowup is not showup");
        }
        return false;
    }

    // GET STARTED
    // com.android.vending:id/play_onboard_center_button
    // android.widget.Button
    // com.android.vending
    public static final boolean getStartedAction(AccessibilityNodeInfo nodeInfo, Context context){
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> getStartedActionNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/play_onboard_center_button");
        if (getStartedActionNodes != null && !getStartedActionNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : getStartedActionNodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "getStartedAction perform result is " + performResult);
                    if (performResult) {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+LoginAutoService.gmailInfo.packageName));
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "getStartedAction is null");
        }
        return false;
    }


    // Continue
    // android:id/button1
    // android.widget.Button
    // com.android.vending
    public static final boolean completeAccountSetupAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> completeAccountSetupNodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button1");
        if (completeAccountSetupNodes != null && !completeAccountSetupNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : completeAccountSetupNodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "completeAccountSetupNodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "completeAccountSetupNodes is null");
        }
        return false;
    }

    // Retry
    // com.android.vending:id/retry_button
    public static final boolean retryAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> retryNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/retry_button");
        if (retryNodes != null && !retryNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : retryNodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "retryNodes perform result is " + performResult);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "retryNodes is null");
        }
        return false;
    }

    // com.android.vending:id/review_timestamp
    // 已经打过
    public static final boolean reviewedAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            return false;
        }
        List<AccessibilityNodeInfo> reviewedNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/review_timestamp");
        if (reviewedNodes != null && !reviewedNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : reviewedNodes) {
                if (node!=null){
                    Log.i(TAG, "reviewedNodes perform result is reviewed");
                    return true;
                }
            }
        } else {
            Log.i(TAG, "reviewedNodes is null");
        }
        return false;
    }
}
