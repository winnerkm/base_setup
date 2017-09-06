package com.basesetup.utilities;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kuliza-234 on 05/09/17.
 */

public class DialogUtils {
    private static ProgressDialog mProgressDialog = null;

    public static void displayProgressDialog(Context context, String message, Boolean backButtonCancelable) {
        if (mProgressDialog == null && context != null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(message);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(backButtonCancelable);
        }
    }

    public static void cancelProgressDialog() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog.dismiss();
                mProgressDialog.cancel();
                mProgressDialog = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
