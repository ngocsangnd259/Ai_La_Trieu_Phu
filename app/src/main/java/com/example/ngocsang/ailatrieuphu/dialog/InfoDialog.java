package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ngocsang.ailatrieuphu.R;


/**
 * Created by NgocSang on 1/25/2017.
 */

public class InfoDialog extends BaseDialog {

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_info);
        findViewById(R.id.btn_dialog_info_close).setOnClickListener(this);
    }

    public InfoDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_info_close: {
                dismiss();
                break;
            }

            default: {
                break;
            }
        }
    }
}
