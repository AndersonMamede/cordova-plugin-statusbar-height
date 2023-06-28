package org.apache.cordova.statusbarheight;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class StatusBarHeight extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("echo")) {
            String message = args.getString(0);
            this.echo(message, callbackContext);
            return true;
        }

        if (action.equals("getStatusBarHeight")) {
            this.getStatusBarHeight(callbackContext);
            return true;
        }

        if (action.equals("getNavigationBarHeight")) {
            this.getNavigationBarHeight(callbackContext);
            return true;
        }

        return false;
    }

    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getStatusBarHeight(CallbackContext callbackContext) {
        this.getResourceDim(callbackContext, "status_bar_height");
    }

    private void getNavigationBarHeight(CallbackContext callbackContext) {
        if (!this.hasSoftKeys()) {
            callbackContext.success(0);
        }
        this.getResourceDim(callbackContext, "navigation_bar_height");
    }

    private void getResourceDim(CallbackContext callbackContext, String resourceName) {
        Context contextApplication = cordova.getActivity().getApplicationContext();
        Resources resources = contextApplication.getResources();

        int resourceId = resources.getIdentifier(resourceName, "dimen", "android");
        if (resourceId > 0) {
            int value = resources.getDimensionPixelSize(resourceId);
            int result = (int) (value / Resources.getSystem().getDisplayMetrics().density);
            callbackContext.success(result);
        } else {
            callbackContext.error("error");
        }
    }

    private boolean hasSoftKeys() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            AppCompatActivity ac = cordova.getActivity();
            Display d = ac.getWindowManager().getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            return  (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        }

        Context c = cordova.getActivity().getApplicationContext();
        boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !hasMenuKey && !hasBackKey;
    }
}
