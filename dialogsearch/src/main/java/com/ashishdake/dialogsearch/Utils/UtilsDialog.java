package com.ashishdake.dialogsearch.Utils;

import android.app.Activity;
import android.app.AlertDialog;

import com.ashishdake.dialogsearch.R;

public class UtilsDialog {
    public static AlertDialog.Builder createAlertDialog(Activity mActivity){
        return new AlertDialog.Builder(mActivity, R.style.AppTheme_Dialog);
    }
}
