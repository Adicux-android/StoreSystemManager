package com.gang.store.storesystemmanager.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Administrator on 2018/6/24.
 */

public class DialogUtils {

    private static DialogUtils mInstance = null;

    public static DialogUtils getInstance() {
        if (mInstance == null) {
            mInstance = new DialogUtils();
        }
        return mInstance;
    }

    public interface DialogCallBack {
        void excuteEvent();
    }
    /**
     *
     * @param context
     * @param titleResId
     * @param messageResId
     * @param cancelable
     * @param yesOnClick
     * @param noOnClick
     */
    public void showDialog(Context context, int titleResId, int messageResId, boolean cancelable,
                                  DialogInterface.OnClickListener yesOnClick,
                                  DialogInterface.OnClickListener noOnClick){
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle(titleResId)
                .setMessage(messageResId).setCancelable(cancelable)
                .setPositiveButton("yes",yesOnClick).setNegativeButton("no",noOnClick).create();
        dialog.show();

    }

    /**
     * 自定义View
     * @param context
     * @param titleResId
     * @param v
     * @param cancelable
     */
    public void showDialog(Context context, String titleResId, View v, boolean cancelable,DialogCallBack callBack){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(titleResId)
                .setView(v)
                .setCancelable(cancelable)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callBack.excuteEvent();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     *单选对话框
     * @param context
     * @param titleResId
     * @param arrayId
     * @param choiceOnClickListener
     */
    public void showMultiItemsDialog(Context context, int titleResId, int arrayId,
                                            DialogInterface.OnClickListener choiceOnClickListener){
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle(titleResId)
                .setSingleChoiceItems(arrayId,-1,choiceOnClickListener).setCancelable(true).create();
        dialog.show();
    }
}
