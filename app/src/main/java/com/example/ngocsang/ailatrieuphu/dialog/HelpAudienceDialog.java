package com.example.ngocsang.ailatrieuphu.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;
import com.example.ngocsang.ailatrieuphu.model.MusicPlayer;


/**
 * Created by NgocSang on 1/19/2017.
 */

public class HelpAudienceDialog extends BaseDialog {
    private TextView txtPercentA;
    private TextView txtPercentB;
    private TextView txtPercentC;
    private TextView txtPercentD;
    private View vBarChartA;
    private View vBarChartB;
    private View vBarChartC;
    private View vBarChartD;

    public HelpAudienceDialog(Context context) {
        super(context);
    }

    public void setUIAnswer(int pA, int pB, int pC, int pD, int perA, int perB, int perC, int perD, int maxPer) {
        final float scale = getContext().getResources().getDisplayMetrics().density;

        if (pA <= perA) {
            ViewGroup.LayoutParams params = vBarChartA.getLayoutParams();
            params.height = (int) (scale * 140 * pA) / maxPer;
            vBarChartA.setLayoutParams(params);
            txtPercentA.setText(pA + "%");
        }

        if (pB <= perB) {
            ViewGroup.LayoutParams params = vBarChartB.getLayoutParams();
            params.height = (int) (scale * 140 * pB) / maxPer;
            vBarChartB.setLayoutParams(params);
            txtPercentB.setText(pB + "%");
        }

        if (pC <= perC) {
            ViewGroup.LayoutParams params = vBarChartC.getLayoutParams();
            params.height = (int) (scale * 140 * pC) / maxPer;
            vBarChartC.setLayoutParams(params);
            txtPercentC.setText(pC + "%");
        }

        if (pD <= perD) {
            ViewGroup.LayoutParams params = vBarChartD.getLayoutParams();
            params.height = (int) (scale * 140 * pD) / maxPer;
            vBarChartD.setLayoutParams(params);
            txtPercentD.setText(pD + "%");
        }
    }

    @Override
    protected void initializeComponents() {
        setContentView(R.layout.dialog_help_audience);
        txtPercentA = (TextView) findViewById(R.id.txt_percent_a);
        txtPercentB = (TextView) findViewById(R.id.txt_percent_b);
        txtPercentC = (TextView) findViewById(R.id.txt_percent_c);
        txtPercentD = (TextView) findViewById(R.id.txt_percent_d);
        vBarChartA = findViewById(R.id.v_help_audience_barchart_a);
        vBarChartB = findViewById(R.id.v_help_audience_barchart_b);
        vBarChartC = findViewById(R.id.v_help_audience_barchart_c);
        vBarChartD = findViewById(R.id.v_help_audience_barchart_d);
        findViewById(R.id.btn_close_help_audience).setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btn_close_help_audience: {
                MusicPlayer mpTouch = new MusicPlayer(R.raw.touch_sound);
                mpTouch.play();
                dismiss();
                confirmListener.onConfirm(Constant.CLOSE_HELP_AUDIENCE);
                break;
            }

            default: {
                break;
            }
        }
    }
}
