package com.mosida.autome;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by mosida on 5/19/17.
 */

public class TitanActions {

    public static final String TAG = "TitanActions";

    // OK
    // android:id/button3
    // android.widget.Button
    // com.keramidas.TitaniumBackup
    public static final boolean waringAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "warningNodes nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> warningNodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/button3");
        if (warningNodes != null && !warningNodes.isEmpty()) {
            Log.i(TAG, "warningNodes is not null");
            for (AccessibilityNodeInfo node : warningNodes) {
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (performResult) {
                    return true;
                }
            }
        } else {
            Log.i(TAG, "warningNodes is null");
        }
        return false;
    }

    // index 1
    // android.widget.TextView
    // Batch
    public static final boolean checkAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkNodes nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> checkNodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/action_bar");
        if (checkNodes != null && !checkNodes.isEmpty()) {
            Log.i(TAG, "checkNodes is not null");
            for (AccessibilityNodeInfo node : checkNodes) {
                ShellUtils.execCommand("input tap 600 55", true);
                return true;
            }
        } else {
            Log.i(TAG, "checkNodes is null");
        }
        return false;
    }

    // Backup all user apps + system data
    public static final boolean backupAllAppAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "backupAllAppAction nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> backupAppsNode = nodeInfo.findAccessibilityNodeInfosByText("Backup all user apps + system data");
        if (backupAppsNode != null && !backupAppsNode.isEmpty()) {
            Log.i(TAG, "backupAppsNode is not null");
            for (AccessibilityNodeInfo node : backupAppsNode) {
                if (node!=null){
                    List<AccessibilityNodeInfo> runNode = node.getParent().getParent().findAccessibilityNodeInfosByText("RUN");
                    for (AccessibilityNodeInfo node2 : runNode){
                        if (node2==null){
                            Log.i(TAG, "runNode is null ");

                        }else{
                            boolean performResult = node2.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            Log.i(TAG, "runNode is not null : "+performResult);
                        }
                        Log.i(TAG, "backupAppsNode is not null");
                        return true;
                    }
                }else{
                    Log.i(TAG, "backupAppsNode is null");
                }
            }
        } else {
            Log.i(TAG, "backupAppsNode is null");
        }
        return false;
    }

}
