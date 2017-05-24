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
           ShellUtils.execCommand("input tap 600 55", true);
           return true;
        } else {
            Log.i(TAG, "checkNodes is null");
        }
        return false;
    }

    // backup
    public static final boolean backupAction(){
        ShellUtils.execCommand("input tap 700 100", true);
        return true;
    }

    // restore
    public static final boolean restoreAction(){
        ShellUtils.execCommand("input tap 700 100", true);
        return true;
    }

    // Scroll Action
    public static final boolean scrollAction(){
        // adb shell input swipe 250 250 300 300
        try{
            Thread.sleep(2000);
            ShellUtils.execCommand("input swipe 300 800 300 500", true);
        }catch (Exception e){
            e.printStackTrace();
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
                    List<AccessibilityNodeInfo> runNode = node.getParent().findAccessibilityNodeInfosByText("RUN");
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

    // Deselect all
    // android.widget.Button
    public static final boolean deselectAllAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "deselectAll nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> deselectAllNodes = nodeInfo.findAccessibilityNodeInfosByText("Deselect all");
        if (deselectAllNodes != null && !deselectAllNodes.isEmpty()) {
            Log.i(TAG, "deselectAll is not null");
            for (AccessibilityNodeInfo node : deselectAllNodes) {
                boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (performResult) {
                    return true;
                }
            }
        } else {
            Log.i(TAG, "deselectAll is null");
        }
        return false;
    }


    // options



    // Accounts
    public static final boolean checkboxAccountsAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxAccounts nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> checkboxAccountsNodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_ACCOUNT);
        if (checkboxAccountsNodes != null && !checkboxAccountsNodes.isEmpty()) {
            Log.i(TAG, "checkboxAccounts is not null");
            for (AccessibilityNodeInfo node : checkboxAccountsNodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxAccountsNodes is null");
        }
        return false;
    }

    // com.android.backupconfirm 4.4.4-eng.genymotion.20170320.221842
    public static final boolean checkboxBackupconfirmAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxBackupconfirm nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_BACKUPCONFIRM);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxBackupconfirm is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxBackupconfirm is null");
        }
        return false;
    }


    // com.android.keyguard 4.4.4-eng.genymotion.20170320.221842
    public static final boolean checkboxKeyguardAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxKeyguard nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_KEYGUARD);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxKeyguard is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxKeyguard is null");
        }
        return false;
    }

    // com.android.sharedstoragebackup 4.4.4-eng.genymotion.20170320.221842
    public static final boolean checkboxSSBackupAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "heckboxSSBackup nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_SHAREDSTORAGEBACKUP);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "heckboxSSBackup is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "heckboxSSBackup is null");
        }
        return false;
    }


    // Gmail 4.7.2 (967015)
    public static final boolean checkboxGmailAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGmail nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GMAIL);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGmail is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxGmail is null");
        }
        return false;
    }

    // Google Account Manager 4.4.2-940549
    public static final boolean checkboxGAMAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGAM nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GAM);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGAM is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxGAM is null");
        }
        return false;
    }

    // Google Backup Transport 4.4.2-940549
    public static final boolean checkboxGBTAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGBT nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GBT);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGBT is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxGBT is null");
        }
        return false;
    }

    // Google Play services 10.2.98 (070-146496160)
    public static final boolean checkboxGPSERAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGPSER nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPSER);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGPSER is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxGPSER is null");
        }
        return false;
    }


    // Google Play Store 7.8.16.P-all [0] [PR] 155590935
    public static final boolean checkboxGPSTOAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGPSTO nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPSTO);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGPSTO is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            List<AccessibilityNodeInfo> nodes2 = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPSTO2);
            if (nodes2 != null && !nodes2.isEmpty()) {
                Log.i(TAG, "checkboxGPSTO is not null");
                for (AccessibilityNodeInfo node : nodes2) {
                    if (node != null) {
                        boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        if (performResult) {
                            return true;
                        }
                    }
                }
            }else{
                List<AccessibilityNodeInfo> nodes3 = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPSTO3);
                if (nodes3 != null && !nodes3.isEmpty()) {
                    Log.i(TAG, "checkboxGPSTO is not null");
                    for (AccessibilityNodeInfo node : nodes3) {
                        if (node != null) {
                            boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            if (performResult) {
                                return true;
                            }
                        }
                    }
                }
            }
            Log.i(TAG, "checkboxGPlus is null");
        }
        return false;
    }

    // Google Services Framework 4.4.2-940549
    public static final boolean checkboxGSFAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGSF nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GSF);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGSF is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxGSF is null");
        }
        return false;
    }


    // Google+ 9.10.0.152874827
    public static final boolean checkboxGPlusAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxGPlus nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPLUS);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxGPlus is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            List<AccessibilityNodeInfo> nodes2 = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_GPLUS2);
            if (nodes2 != null && !nodes2.isEmpty()) {
                Log.i(TAG, "checkboxGPlus is not null");
                for (AccessibilityNodeInfo node : nodes2) {
                    if (node != null) {
                        boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        if (performResult) {
                            return true;
                        }
                    }
                }
            }
            Log.i(TAG, "checkboxGPlus is null");
        }
        return false;
    }


    // Settings Storage 4.4.4-eng.genymotion.20170320.221842 (SETTINGS/BLUETOOTH)
    public static final boolean checkboxSSAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "checkboxSS nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(Constants.PHONE_SS);
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "checkboxSS is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if (performResult) {
                        return true;
                    }
                }
            }
        } else {
            Log.i(TAG, "checkboxSS is null");
        }
        return false;
    }

    // Restore all apps with data
    public static final boolean restoreAllAppsWithDataAction(AccessibilityNodeInfo nodeInfo){
        if (nodeInfo == null) {
            Log.i(TAG, "restoreAllAppsWithData nodeInfo is null");
            return false;
        }
        List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText("Restore all apps with data");
        if (nodes != null && !nodes.isEmpty()) {
            Log.i(TAG, "restoreAllAppsWithData is not null");
            for (AccessibilityNodeInfo node : nodes) {
                if (node!=null){
                    AccessibilityNodeInfo runNode = node.getParent().getParent().getChild(0);
                    if (runNode!=null){
                        boolean performResult = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        if (performResult) {
                            return true;
                        }
                    }
                }
            }
        } else {
            Log.i(TAG, "restoreAllAppsWithData is null");
            scrollAction();
        }
        return false;
    }
}
