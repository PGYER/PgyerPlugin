package com.pgyer.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.pgyersdk.feedback.PgyerFeedbackManager;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;


public class PgyerPlugin extends CordovaPlugin {
    private static String TAG = "PgyerPlugin";


    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        String androidAppId = "";
        try {
            ApplicationInfo applicationInfo = this.cordova.getActivity().getPackageManager().getApplicationInfo(this.cordova.getActivity().getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            androidAppId = bundle.getString("PGYER_APPID");
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }

        if (androidAppId.equals("")) {
            return false;
        }

        final String appId = androidAppId;

        if (action.equals("showFeedback")) {
            new PgyerFeedbackManager.PgyerFeedbackBuilder()
                    .setShakeInvoke(false)       //fasle 则不触发摇一摇，最后需要调用 invoke 方法
                    // true 设置需要调用 register 方法使摇一摇生效
                    .setDisplayType(PgyerFeedbackManager.TYPE.DIALOG_TYPE)   //设置以Dialog 的方式打开
                    .setColorDialogTitle("#FF0000")    //设置Dialog 标题的字体颜色，默认为颜色为#ffffff
                    .setColorTitleBg("#FF0000")        //设置Dialog 标题栏的背景色，默认为颜色为#2E2D2D
                    .setBarBackgroundColor("#FF0000")      // 设置顶部按钮和底部背景色，默认颜色为 #2E2D2D
                    .setBarButtonPressedColor("#FF0000")        //设置顶部按钮和底部按钮按下时的反馈色 默认颜色为 #383737
                    .setColorPickerBackgroundColor("#FF0000")   //设置颜色选择器的背景色,默认颜色为 #272828
                    .setMoreParam("KEY1", "VALUE1") //自定义的反馈数据
                    .setMoreParam("KEY2", "VALUE2") //自定义的反馈数据
                    .builder()
                    .invoke();                  //激活直接显示的方式
            return true;
        }
        if (action.equals("crashRegister")) {
            PgyCrashManager.register();
            return true;
        }
        if (action.equals("checkUpdate")) {
            /** 默认方式 **/
            new PgyUpdateManager.Builder()
                    .register();
            return true;

        }

        return false;
    }
}
