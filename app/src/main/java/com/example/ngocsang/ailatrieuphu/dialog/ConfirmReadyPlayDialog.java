package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;


/**
 * Created by NgocSang on 10/29/2016.
 */

public class ConfirmReadyPlayDialog extends BaseDialog {

    public ConfirmReadyPlayDialog(Context context) {
        super(context);
    }

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_confirm_ready_play);
        findViewById(R.id.btn_confirm_ready).setOnClickListener(this);
        findViewById(R.id.btn_confirm_cancel).setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm_ready: {
                dismiss();
                confirmListener.onConfirm(Constant.CONFIRM_READY_PLAY);
                break;
            }

            case R.id.btn_confirm_cancel: {
                dismiss();
                confirmListener.onConfirm(Constant.CONFIRM_CANCEL);
                break;
            }

            default: {
                break;
            }
        }
    }
}
