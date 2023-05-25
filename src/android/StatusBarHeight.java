package org.apache.cordova.statusbarheight;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.res.Resources;
import android.content.Context;

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
      this.getResourceDim(callbackContext, "navigation_bar_height");
    }

    private void getResourceDim(CallbackContext callbackContext, String resouceName) {
      Context contextApplication = cordova.getActivity().getApplicationContext();
      Resources resources = contextApplication.getResources();

      int resourceId = resources.getIdentifier(resouceName, "dimen", "android");
      if (resourceId > 0) {
        int value = resources.getDimensionPixelSize(resourceId);
        int result = (int) (value / Resources.getSystem().getDisplayMetrics().density);
        callbackContext.success(result);
      } else {
        callbackContext.error("error");
      }
    }
}


