package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;

/**
 * Created by NgocSang on 1/15/2017.
 */

public class ConfirmChangeQuestionDialog extends BaseDialog {
    public ConfirmChangeQuestionDialog(Context context) {
        super(context);
    }

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_confirm_change_question);
        findViewById(R.id.btn_confirm_change_question_yes).setOnClickListener(this);
        findViewById(R.id.btn_confirm_change_question_no).setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm_change_question_yes: {
                dismiss();
                confirmListener.onConfirm(Constant.CONFIRM_CHANGE_QUESTION_YES);
                break;
            }

            case R.id.btn_confirm_change_question_no: {
                dismiss();
                confirmListener.onConfirm(Constant.CONFIRM_CHANGE_QUESTION_NO);
                break;
            }
        }
    }
}
