package com.mosida.autome;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
    public static final void existingAction(AccessibilityNodeInfo nodeInfo){
        List<AccessibilityNodeInfo> existingNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/next_button");
            if (existingNodes != null && !existingNodes.isEmpty()) {

            for (AccessibilityNodeInfo node : existingNodes) {
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (performResult) {
//                    node.recycle();
//                    nodeInfo.recycle();
                    return;
                }
//                node.recycle();
            }
        } else {
            Log.i(TAG, "existingNodes is null");
        }
    }

    // Email
    // com.google.android.gsf.login:id/username_edit
    // android.widget.EditText
    public static final void emailsAction(AccessibilityNodeInfo nodeInfo, Context context){

        List<AccessibilityNodeInfo> emailNodes = nodeInfo.findAccessibilityNodeInfosByViewId("com.google.android.gsf.login:id/username_edit");

//        Log.i(TAG, nodeInfo.toString());

        if(emailNodes == null || emailNodes.isEmpty()) {
            Log.i(TAG, "Email node is null");
        } else {
            Log.i(TAG, "Email node is not null");

            for(AccessibilityNodeInfo node : emailNodes)
            {
                if (!node.getText().equals("Email")){
                    Log.i(TAG, "editText is :" + node.getText());
                    return;
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
                }
                node.recycle();
            }
        }

    }

}
