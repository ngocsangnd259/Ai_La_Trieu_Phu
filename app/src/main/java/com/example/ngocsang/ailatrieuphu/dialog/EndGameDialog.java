package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;


/**
 * Created by NgocSang on 10/30/2016.
 */

public class EndGameDialog extends BaseDialog {
    private TextView txtResult;
    private TextView txtPosQuestion;
    private TextView txtMoney;

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_end_game);
        txtResult = (TextView) findViewById(R.id.txt_dialog_result);
        txtPosQuestion = (TextView) findViewById(R.id.txt_dialog_pos_question);
        txtMoney = (TextView) findViewById(R.id.txt_dialog_money);
        findViewById(R.id.btn_dialog_end_game_close).setOnClickListener(this);
    }

    public EndGameDialog(Context context) {
        super(context);
    }

    public void setTxtResult(String s) {
        txtResult.setText(s);
    }

    public void setTxtPosQuestion(String s) {
        txtPosQuestion.setText(s);
    }

    public void setTxtMoney(String s) {
        txtMoney.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_end_game_close: {
                dismiss();
                confirmListener.onConfirm(Constant.CLOSE_PLAY_ACTIVITY);
                break;
            }

            default: {
                break;
            }
        }
    }
}
