package com.example.ngocsang.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.ngocsang.ailatrieuphu.interfaces.OnConfirmListener;

/**
 * Created by NgocSang on 1/16/2017.
 */

public class BaseDialog extends Dialog
        implements View.OnClickListener {
    protected OnConfirmListener confirmListener;

    protected void initializeComponents() {
    }

    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initializeComponents();
    }

    public void setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onClick(View v) {
    }
}
