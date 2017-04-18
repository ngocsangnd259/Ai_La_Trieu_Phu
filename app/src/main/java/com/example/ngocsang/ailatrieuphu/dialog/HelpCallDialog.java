package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;
import com.example.ngocsang.ailatrieuphu.model.MusicPlayer;

/**
 * Created by NgocSang on 1/16/2017.
 */

public class HelpCallDialog extends BaseDialog {
    private TableRow tbrHelpCall01;
    private TableRow tbrHelpCall02;
    private LinearLayout llHelpCall;
    private ImageView imgHelpCallChosen;
    private TextView txtHelpCallChosen;
    private TextView txtHelpCallAnswer;

    public HelpCallDialog(Context context) {
        super(context);
    }

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_help_call);
        tbrHelpCall01 = (TableRow) findViewById(R.id.tbr_help_call_01);
        tbrHelpCall02 = (TableRow) findViewById(R.id.tbr_help_call_02);
        llHelpCall = (LinearLayout) findViewById(R.id.ll_help_call);
        imgHelpCallChosen = (ImageView) findViewById(R.id.img_help_call_chosen);
        txtHelpCallChosen = (TextView) findViewById(R.id.txt_help_call_chosen);
        txtHelpCallAnswer = (TextView) findViewById(R.id.txt_help_call_answer);
        tbrHelpCall01.setVisibility(View.VISIBLE);
        tbrHelpCall02.setVisibility(View.VISIBLE);
        llHelpCall.setVisibility(View.GONE);
        findViewById(R.id.btn_help_call_01).setOnClickListener(this);
        findViewById(R.id.btn_help_call_02).setOnClickListener(this);
        findViewById(R.id.btn_help_call_03).setOnClickListener(this);
        findViewById(R.id.btn_help_call_04).setOnClickListener(this);
        findViewById(R.id.btn_close_help_call_dialog).setOnClickListener(this);
    }

    public void setUIAnswer(int type, int answer) {
        llHelpCall.setVisibility(View.VISIBLE);
        switch (type) {
            case Constant.CHOSE_HELP_CALL_01: {
                imgHelpCallChosen.setImageResource(R.drawable.atp__activity_player_layout_help_call_01);
                txtHelpCallChosen.setText("Bác sĩ");
                break;
            }

            case Constant.CHOSE_HELP_CALL_02: {
                imgHelpCallChosen.setImageResource(R.drawable.atp__activity_player_layout_help_call_02);
                txtHelpCallChosen.setText("Giáo viên");
                break;
            }

            case Constant.CHOSE_HELP_CALL_03: {
                imgHelpCallChosen.setImageResource(R.drawable.atp__activity_player_layout_help_call_03);
                txtHelpCallChosen.setText("Kĩ sư");
                break;
            }

            case Constant.CHOSE_HELP_CALL_04: {
                imgHelpCallChosen.setImageResource(R.drawable.atp__activity_player_layout_help_call_04);
                txtHelpCallChosen.setText("Phóng viên");
                break;
            }

            default: {
                break;
            }
        }

        if (answer == 1) txtHelpCallAnswer.setText("Theo tôi đáp án đúng là A");
        if (answer == 2) txtHelpCallAnswer.setText("Theo tôi đáp án đúng là B");
        if (answer == 3) txtHelpCallAnswer.setText("Theo tôi đáp án đúng là C");
        if (answer == 4) txtHelpCallAnswer.setText("Theo tôi đáp án đúng là D");
    }

    private void changeVisible() {
        tbrHelpCall01.setVisibility(View.GONE);
        tbrHelpCall02.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onClick(View v) {
        MusicPlayer mpTouch = new MusicPlayer(R.raw.touch_sound);
        mpTouch.play();

        switch (v.getId()) {
            case R.id.btn_help_call_01: {
                changeVisible();
                confirmListener.onConfirm(Constant.CHOSE_HELP_CALL_01);
                break;
            }

            case R.id.btn_help_call_02: {
                changeVisible();
                confirmListener.onConfirm(Constant.CHOSE_HELP_CALL_02);
                break;
            }

            case R.id.btn_help_call_03: {
                changeVisible();
                confirmListener.onConfirm(Constant.CHOSE_HELP_CALL_03);
                break;
            }

            case R.id.btn_help_call_04: {
                changeVisible();
                confirmListener.onConfirm(Constant.CHOSE_HELP_CALL_04);
                break;
            }

            case R.id.btn_close_help_call_dialog: {
                dismiss();
                confirmListener.onConfirm(Constant.CLOSE_HELP_CALL);
                break;
            }

            default: {
                break;
            }
        }
    }
}
