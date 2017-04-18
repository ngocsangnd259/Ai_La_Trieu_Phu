package com.example.ngocsang.ailatrieuphu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngocsang.ailatrieuphu.Constant;
import com.example.ngocsang.ailatrieuphu.R;
import com.example.ngocsang.ailatrieuphu.dialog.ConfirmChangeQuestionDialog;
import com.example.ngocsang.ailatrieuphu.dialog.ConfirmReadyPlayDialog;
import com.example.ngocsang.ailatrieuphu.dialog.ConfirmStopGameDialog;
import com.example.ngocsang.ailatrieuphu.dialog.EndGameDialog;
import com.example.ngocsang.ailatrieuphu.dialog.HelpAudienceDialog;
import com.example.ngocsang.ailatrieuphu.dialog.HelpCallDialog;
import com.example.ngocsang.ailatrieuphu.interfaces.OnConfirmListener;
import com.example.ngocsang.ailatrieuphu.manager.QuestionManager;
import com.example.ngocsang.ailatrieuphu.model.MusicPlayer;
import com.example.ngocsang.ailatrieuphu.model.Question;

import java.util.Random;

/**
 * Created by NgocSang on 10/27/2016.
 */

public class PlayActivity extends AppCompatActivity
        implements Runnable, View.OnClickListener {

    private DrawerLayout dlPlayActivity;
    private FrameLayout flQuestion;
    private TextView txtPosQuestion;
    private TextView txtQuestion;
    private TextView txtCaseA;
    private TextView txtCaseB;
    private TextView txtCaseC;
    private TextView txtCaseD;
    private TextView txtTime;
    private TextView txtMoney;

    private FrameLayout btnAnswerA;
    private FrameLayout btnAnswerB;
    private FrameLayout btnAnswerC;
    private FrameLayout btnAnswerD;

    private ImageView imgBgAnswerA;
    private ImageView imgBgAnswerB;
    private ImageView imgBgAnswerC;
    private ImageView imgBgAnswerD;

    private ImageView btnHelp5050;
    private ImageView btnHelpCall;
    private ImageView btnHelpAudience;
    private ImageView btnHelpChangeQuestion;
    private ImageView btnHelpStop;

    private Handler handlerDelay;
    private Handler handlerChangeUI;
    private Animation animation;

    private QuestionManager questionManager;
    private Question question;

    private boolean isGameRunning;
    private boolean isOpenDrawer;
    private boolean isNextQuestion;
    private boolean isInTimeAnswer;
    private boolean isHelp5050;
    private boolean isHelpCall;
    private boolean isHelpAudience;
    private boolean isHelpChangeQuestion;
    private boolean isHelpStop;
    private boolean isOpenConfirmStopGameDialog;
    private boolean isOpenConfirmChangeQuestionDialog;
    private boolean isOpenHelpCall;
    private boolean isMpBgPlayAgain;

    private int timeOpenDrawer;
    private int posQuestion;
    private int time;
    private int timeAnim;
    private int timeHelp5050;
    private int yourAnswer;
    private int count;
    private int chose;
    private int timeHelpCall;
    private int timeHelpAudience;
    private int perA;
    private int perB;
    private int perC;
    private int perD;
    private int maxPer;
    private int pA;
    private int pB;
    private int pC;
    private int pD;
    private int t1;
    private int t2;
    private int tBeginMpQues;

    private ConfirmChangeQuestionDialog confirmChangeQuestionDialog;
    private ConfirmStopGameDialog confirmStopGameDialog;
    private HelpCallDialog helpCallDialog;
    private HelpAudienceDialog helpAudienceDialog;

    private MusicPlayer mpBackground;
    private MusicPlayer mpRule;
    private MusicPlayer mpReady;
    private MusicPlayer mpEnd;
    private MusicPlayer mpHelpAudience1;
    private MusicPlayer mpHelpAudience2;
    private MusicPlayer mpAns;
    private MusicPlayer mpAnsNow;
    private MusicPlayer mpTrueAns;
    private MusicPlayer mpCongrats;
    private MusicPlayer mpQues;
    private MusicPlayer mpImportant;
    private MusicPlayer mpHelp5050;
    private MusicPlayer mpHelpCall1;
    private MusicPlayer mpHelpCall2;
    private MusicPlayer mpGoFind;
    private MusicPlayer mpTouch;

    private int[] delQues;

    private int[] idImgMoney = {
            R.id.img_bg_money_1, R.id.img_bg_money_2, R.id.img_bg_money_3,
            R.id.img_bg_money_4, R.id.img_bg_money_5, R.id.img_bg_money_6,
            R.id.img_bg_money_7, R.id.img_bg_money_8, R.id.img_bg_money_9,
            R.id.img_bg_money_10, R.id.img_bg_money_11, R.id.img_bg_money_12,
            R.id.img_bg_money_13, R.id.img_bg_money_14, R.id.img_bg_money_15
    };

    private int[] idTxtMoney = {
            R.id.txt_money_1, R.id.txt_money_2, R.id.txt_money_3,
            R.id.txt_money_4, R.id.txt_money_5, R.id.txt_money_6,
            R.id.txt_money_7, R.id.txt_money_8, R.id.txt_money_9,
            R.id.txt_money_10, R.id.txt_money_11, R.id.txt_money_12,
            R.id.txt_money_13, R.id.txt_money_14, R.id.txt_money_15
    };

    private String[] money = {
            "200.000", "400.000", "600.000", "1000.000", "2000.000", "3000.000",
            "6000.000", "10.000.000", "14.000.000", "22.000.000", "30.000.000",
            "40.000.000", "60.000.000", "85.000.000", "150.000.000"
    };

    private void initializeComponents() {
        dlPlayActivity = (DrawerLayout) findViewById(R.id.dl_play_activity);
        flQuestion = (FrameLayout) findViewById(R.id.fl_question);

        txtPosQuestion = (TextView) findViewById(R.id.txt_pos_question);
        txtQuestion = (TextView) findViewById(R.id.txt_question);
        txtCaseA = (TextView) findViewById(R.id.txt_case_a);
        txtCaseB = (TextView) findViewById(R.id.txt_case_b);
        txtCaseC = (TextView) findViewById(R.id.txt_case_c);
        txtCaseD = (TextView) findViewById(R.id.txt_case_d);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtMoney = (TextView) findViewById(R.id.txt_money);

        btnAnswerA = (FrameLayout) findViewById(R.id.btn_answer_a);
        btnAnswerB = (FrameLayout) findViewById(R.id.btn_answer_b);
        btnAnswerC = (FrameLayout) findViewById(R.id.btn_answer_c);
        btnAnswerD = (FrameLayout) findViewById(R.id.btn_answer_d);

        imgBgAnswerA = (ImageView) findViewById(R.id.img_bg_answer_a);
        imgBgAnswerB = (ImageView) findViewById(R.id.img_bg_answer_b);
        imgBgAnswerC = (ImageView) findViewById(R.id.img_bg_answer_c);
        imgBgAnswerD = (ImageView) findViewById(R.id.img_bg_answer_d);

        btnHelp5050 = (ImageView) findViewById(R.id.btn_help_5050);
        btnHelpCall = (ImageView) findViewById(R.id.btn_help_call);
        btnHelpAudience = (ImageView) findViewById(R.id.btn_help_audience);
        btnHelpChangeQuestion = (ImageView) findViewById(R.id.btn_help_change_question);
        btnHelpStop = (ImageView) findViewById(R.id.btn_help_stop);

        flQuestion.setVisibility(View.INVISIBLE);
        btnAnswerA.setVisibility(View.INVISIBLE);
        btnAnswerB.setVisibility(View.INVISIBLE);
        btnAnswerC.setVisibility(View.INVISIBLE);
        btnAnswerD.setVisibility(View.INVISIBLE);

        btnAnswerA.setOnClickListener(this);
        btnAnswerB.setOnClickListener(this);
        btnAnswerC.setOnClickListener(this);
        btnAnswerD.setOnClickListener(this);

        btnHelp5050.setOnClickListener(this);
        btnHelpCall.setOnClickListener(this);
        btnHelpAudience.setOnClickListener(this);
        btnHelpChangeQuestion.setOnClickListener(this);
        btnHelpStop.setOnClickListener(this);

        // drawer layout full screen
        View mSlidingView = findViewById(R.id.slider);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mSlidingView.getLayoutParams();
        params.width = metrics.widthPixels;
        mSlidingView.setLayoutParams(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initializeComponents();
        initializeHandlerChangeUI();
        confirmPlayGame();
    }

    private void animAnswerSelected() {
        switch (yourAnswer) {
            case 1: {
                imgBgAnswerA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                imgBgAnswerA.startAnimation(animation);
                break;
            }

            case 2: {
                imgBgAnswerB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                imgBgAnswerB.startAnimation(animation);
                break;
            }

            case 3: {
                imgBgAnswerC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                imgBgAnswerC.startAnimation(animation);
                break;
            }

            case 4: {
                imgBgAnswerD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                imgBgAnswerD.startAnimation(animation);
                break;
            }

            default: {
                break;
            }
        }
    }

    private void animTrueAnswer() {
        switch (yourAnswer) {
            case 1: {
                imgBgAnswerA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerA.startAnimation(animation);
                break;
            }

            case 2: {
                imgBgAnswerB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerB.startAnimation(animation);
                break;
            }

            case 3: {
                imgBgAnswerC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerC.startAnimation(animation);
                break;
            }

            case 4: {
                imgBgAnswerD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerD.startAnimation(animation);
                break;
            }

            default: {
                break;
            }
        }
    }

    private void animWrongAnswer() {
        switch (yourAnswer) {
            case 1: {
                imgBgAnswerA.clearAnimation();
                imgBgAnswerA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            }

            case 2: {
                imgBgAnswerB.clearAnimation();
                imgBgAnswerB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            }

            case 3: {
                imgBgAnswerC.clearAnimation();
                imgBgAnswerC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            }

            case 4: {
                imgBgAnswerD.clearAnimation();
                imgBgAnswerD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            }

            default: {
                break;
            }
        }

        switch (question.getTrueCase()) {
            case 1: {
                imgBgAnswerA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerA.startAnimation(animation);
                break;
            }

            case 2: {
                imgBgAnswerB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerB.startAnimation(animation);
                break;
            }

            case 3: {
                imgBgAnswerC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerC.startAnimation(animation);
                break;
            }

            case 4: {
                imgBgAnswerD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                imgBgAnswerD.startAnimation(animation);
                break;
            }

            default: {
                break;
            }
        }
    }

    private void setDefaultAnimBtnAnswer() {
        imgBgAnswerA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        imgBgAnswerB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        imgBgAnswerC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        imgBgAnswerD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        imgBgAnswerA.clearAnimation();
        imgBgAnswerB.clearAnimation();
        imgBgAnswerC.clearAnimation();
        imgBgAnswerD.clearAnimation();
    }

    private void initializeHandlerChangeUI() {
        handlerChangeUI = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constant.UPDATE_NEW_QUESTION: {
                        updateNewQuestion();
                        break;
                    }

                    case Constant.UPDATE_DRAWER_MONEY: {
                        findViewById(idImgMoney[posQuestion - 1]).setVisibility(View.VISIBLE);
                        if (posQuestion - 2 >= 0) {
                            findViewById(idImgMoney[posQuestion - 2]).setVisibility(View.INVISIBLE);
                            TextView textView = (TextView) findViewById(idTxtMoney[posQuestion - 2]);
                            if ((posQuestion - 1) % 5 == 0) {
                                textView.setTextColor(0xfffff9c4);
                            } else {
                                textView.setTextColor(0xff616161);
                            }
                        }
                        break;
                    }

                    case Constant.OPEN_DRAWER: {
                        dlPlayActivity.openDrawer(GravityCompat.START);
                        dlPlayActivity.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                        break;
                    }

                    case Constant.CLOSE_DRAWER: {
                        dlPlayActivity.closeDrawers();
                        dlPlayActivity.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;
                    }

                    case Constant.UPDATE_TIME: {
                        txtTime.setText(time + "");
                        break;
                    }

                    case Constant.UPDATE_MONEY: {
                        txtMoney.setText(money[posQuestion - 1] + "");
                        break;
                    }

                    case Constant.ANIM_ANSWER_SELECTED: {
                        animAnswerSelected();
                        break;
                    }

                    case Constant.ANIM_TRUE_ANSWER: {
                        animTrueAnswer();
                        break;
                    }

                    case Constant.ANIM_WRONG_ANSWER: {
                        animWrongAnswer();
                        break;
                    }

                    case Constant.SET_DEFAULT_ANIM_BTN_ANSWER: {
                        setDefaultAnimBtnAnswer();
                        break;
                    }

                    case Constant.ACCEPTED: {
                        openEndGameDialog(Constant.ACCEPTED);
                        break;
                    }

                    case Constant.WRONG_ANSWER: {
                        txtMoney.setText(yourMoney(Constant.WRONG_ANSWER));
                        openEndGameDialog(Constant.WRONG_ANSWER);
                        break;
                    }

                    case Constant.TIME_LIMIT: {
                        txtMoney.setText(yourMoney(Constant.TIME_LIMIT));
                        if (isOpenConfirmStopGameDialog) {
                            confirmStopGameDialog.dismiss();
                            isOpenConfirmStopGameDialog = false;
                        }
                        if (isOpenConfirmChangeQuestionDialog) {
                            confirmChangeQuestionDialog.dismiss();
                            isOpenConfirmChangeQuestionDialog = false;
                        }
                        openEndGameDialog(Constant.TIME_LIMIT);
                        break;
                    }

                    case Constant.HELP_5050: {
                        for (int i = 0; i < 2; i++) {
                            if (delQues[i] == 1) btnAnswerA.setVisibility(View.INVISIBLE);
                            if (delQues[i] == 2) btnAnswerB.setVisibility(View.INVISIBLE);
                            if (delQues[i] == 3) btnAnswerC.setVisibility(View.INVISIBLE);
                            if (delQues[i] == 4) btnAnswerD.setVisibility(View.INVISIBLE);
                        }
                        break;
                    }

                    case Constant.HELP_CALL: {
                        openHelpCallDialog();
                        break;
                    }

                    case Constant.HELP_AUDIENCE: {
                        openHelpAudienceDialog();
                        break;
                    }

                    case Constant.CHANGE_QUESTION: {
                        openConfirmChangeQuestionDialog();
                        break;
                    }

                    case Constant.STOP_GAME: {
                        openConfirmStopGameDialog();
                        break;
                    }

                    case Constant.CHOSE_HELP_CALL_01:
                    case Constant.CHOSE_HELP_CALL_02:
                    case Constant.CHOSE_HELP_CALL_03:
                    case Constant.CHOSE_HELP_CALL_04: {
                        helpCallDialog.setUIAnswer(msg.what, question.getTrueCase());
                        break;
                    }

                    case Constant.UPDATE_HELP_AUDIENCE: {
                        helpAudienceDialog.setUIAnswer(pA, pB, pC, pD, perA, perB, perC, perD, maxPer);
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        };
    }

    private void updateNewQuestion() {
        txtPosQuestion.setText("Câu " + posQuestion);
        txtQuestion.setText(question.getQuestion());
        txtCaseA.setText("A: " + question.getCaseA());
        txtCaseB.setText("B: " + question.getCaseB());
        txtCaseC.setText("C: " + question.getCaseC());
        txtCaseD.setText("D: " + question.getCaseD());
        txtTime.setText(time + "");

        btnAnswerA.setClickable(true);
        btnAnswerB.setClickable(true);
        btnAnswerC.setClickable(true);
        btnAnswerD.setClickable(true);

        flQuestion.setVisibility(View.VISIBLE);
        btnAnswerA.setVisibility(View.VISIBLE);
        btnAnswerB.setVisibility(View.VISIBLE);
        btnAnswerC.setVisibility(View.VISIBLE);
        btnAnswerD.setVisibility(View.VISIBLE);

        setDefaultAnimBtnAnswer();
    }

    private void confirmPlayGame() {
        handlerDelay = new Handler();
        handlerDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                dlPlayActivity.openDrawer(GravityCompat.START);
                dlPlayActivity.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mpRule = new MusicPlayer(Constant.LUAT_CHOI);
                mpRule.play();

                handlerDelay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dlPlayActivity.closeDrawers();
                        dlPlayActivity.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        openConfirmReadyPlayDialog();
                    }
                }, Constant.TIME_DELAY_FIRST_OPEN_DRAWER);
            }
        }, Constant.TIME_DELAY_BEFORE_FIRST_OPEN_DRAWER);
    }

    private void openConfirmReadyPlayDialog() {
        mpReady = new MusicPlayer(Constant.READY);
        mpReady.play();
        ConfirmReadyPlayDialog confirmReadyPlayDialog = new ConfirmReadyPlayDialog(this);
        confirmReadyPlayDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                switch (result) {
                    case Constant.CONFIRM_READY_PLAY: {
                        mpReady.stop();
                        startGame();
                        break;
                    }

                    case Constant.CONFIRM_CANCEL: {
                        finish();
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        });
        confirmReadyPlayDialog.show();
    }

    private void openEndGameDialog(int type) {
        EndGameDialog endGameDialog = new EndGameDialog(this);
        switch (type) {
            case Constant.ACCEPTED: {
                endGameDialog.setTxtResult("XIN CHÚC MỪNG !");
                mpEnd = new MusicPlayer(R.raw.best_player);
                mpEnd.play();
                break;
            }

            case Constant.STOP_GAME: {
                endGameDialog.setTxtResult("BẠN ĐÃ DỪNG CUỘC CHƠI");
                mpEnd = new MusicPlayer(Constant.LOSE);
                mpEnd.play();
                break;
            }

            case Constant.WRONG_ANSWER: {
                endGameDialog.setTxtResult("KẾT QUẢ");
                mpEnd = new MusicPlayer(Constant.LOSE);
                mpEnd.play();
                break;
            }

            case Constant.TIME_LIMIT: {
                endGameDialog.setTxtResult("HẾT GIỜ");
                mpEnd = new MusicPlayer(R.raw.out_of_time);
                mpEnd.play();
                break;
            }

            default: {
                break;
            }
        }
        if (type == Constant.ACCEPTED) {
            endGameDialog.setTxtPosQuestion("Bạn đã vượt qua câu hỏi số 15");
        } else {
            endGameDialog.setTxtPosQuestion("Bạn đã vượt qua câu hỏi số " + (posQuestion - 1));
        }
        endGameDialog.setTxtMoney(txtMoney.getText().toString() + " VND");
        endGameDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                switch (result) {
                    case Constant.CLOSE_PLAY_ACTIVITY: {
                        finish();
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        });
        endGameDialog.show();
    }

    private void openHelpCallDialog() {
        helpCallDialog = new HelpCallDialog(this);
        helpCallDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                if (result == Constant.CLOSE_HELP_CALL) {
                    isInTimeAnswer = true;
                    isMpBgPlayAgain = true;
                } else chose = result;
            }
        });
        helpCallDialog.show();
    }

    private void openHelpAudienceDialog() {
        helpAudienceDialog = new HelpAudienceDialog(this);
        helpAudienceDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                switch (result) {
                    case Constant.CLOSE_HELP_AUDIENCE: {
                        isInTimeAnswer = true;
                        isHelpAudience = false;
                        isMpBgPlayAgain = true;
                        if (mpHelpAudience1 != null) mpHelpAudience1.stop();
                        if (mpHelpAudience2 != null) mpHelpAudience2.stop();
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        });
        helpAudienceDialog.show();
    }

    private void openConfirmChangeQuestionDialog() {
        isOpenConfirmChangeQuestionDialog = true;
        confirmChangeQuestionDialog = new ConfirmChangeQuestionDialog(this);
        confirmChangeQuestionDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                switch (result) {
                    case Constant.CONFIRM_CHANGE_QUESTION_YES: {
                        //change question
                        btnHelpChangeQuestion.setClickable(false);
                        btnHelpChangeQuestion.setImageResource(R.drawable.atp__activity_player_button_image_help_change_question_x);
                        question = questionManager.changeQuestion();
                        time = 30;
                        count = 0;
                        updateNewQuestion();
                        isOpenConfirmChangeQuestionDialog = false;
                        break;
                    }

                    case Constant.CONFIRM_CHANGE_QUESTION_NO: {
                        isOpenConfirmChangeQuestionDialog = false;
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        });
        confirmChangeQuestionDialog.show();
    }

    private void openConfirmStopGameDialog() {
        isOpenConfirmStopGameDialog = true;
        confirmStopGameDialog = new ConfirmStopGameDialog(this);
        confirmStopGameDialog.setConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm(int result) {
                switch (result) {
                    case Constant.CONFIRM_STOP_GAME_YES: {
                        if (mpBackground != null) mpBackground.stop();
                        openEndGameDialog(Constant.STOP_GAME);
                        isGameRunning = false;
                        isOpenConfirmStopGameDialog = false;
                        break;
                    }

                    case Constant.CONFIRM_STOP_GAME_NO: {
                        isOpenConfirmStopGameDialog = false;
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        });
        confirmStopGameDialog.show();
    }

    private String yourMoney(int type) {
        if (posQuestion - 1 >= 5 && posQuestion - 1 < 10) {
            return money[4];
        }
        if (posQuestion - 1 >= 10) {
            return money[9];
        }
        return "0";
    }

    private int getPercentTrueAnswer() {
        Random r = new Random();
        if (posQuestion <= 5) return 75 + r.nextInt(26);
        if (posQuestion <= 10) return 40 + r.nextInt(21);
        return 25 + r.nextInt(26);
    }

    private void startGame() {
        time = 30;
        timeAnim = 0;
        timeOpenDrawer = 0;
        timeHelpCall = 0;
        timeHelpAudience = 0;
        chose = -1;
        isGameRunning = true;
        isNextQuestion = true;
        isOpenDrawer = false;
        isInTimeAnswer = false;
        isHelp5050 = false;
        isHelpAudience = false;
        isHelpCall = false;
        isHelpChangeQuestion = false;
        isHelpStop = false;
        isOpenConfirmStopGameDialog = false;
        isOpenHelpCall = false;
        isMpBgPlayAgain = false;
        questionManager = new QuestionManager(this);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_bg_button_answer);
        new Thread(this).start();
    }

    private void checkAnswer() {
        timeAnim++;
        if (timeAnim == 1) {
            handlerChangeUI.sendEmptyMessage(Constant.ANIM_ANSWER_SELECTED);
            mpAns = null;
            if (yourAnswer == 1) mpAns = new MusicPlayer(Constant.ANS_A);
            if (yourAnswer == 2) mpAns = new MusicPlayer(Constant.ANS_B);
            if (yourAnswer == 3) mpAns = new MusicPlayer(Constant.ANS_C);
            if (yourAnswer == 4) mpAns = new MusicPlayer(Constant.ANS_D);
            mpAns.play();
            t1 = Constant.TIME_ANIM_SELECTED_ANSWER;
            if (posQuestion == 5 || posQuestion == 10 || posQuestion == 15)
                t1 = Constant.TIME_ANIM_SELECTED_ANSWER_IMPORTANT;
        }

        if ((posQuestion == 5 || posQuestion == 10 || posQuestion == 15) && timeAnim == Constant.TIME_ANIM_SELECTED_ANSWER) {
            mpAnsNow = new MusicPlayer(Constant.ANS_NOW);
            mpAnsNow.play();
        }

        if (timeAnim == t1) {
            if (yourAnswer == question.getTrueCase()) {
                handlerChangeUI.sendEmptyMessage(Constant.ANIM_TRUE_ANSWER);
                handlerChangeUI.sendEmptyMessage(Constant.UPDATE_MONEY);
                mpTrueAns = null;
                if (yourAnswer == 1) mpTrueAns = new MusicPlayer(Constant.TRUE_A);
                if (yourAnswer == 2) mpTrueAns = new MusicPlayer(Constant.TRUE_B);
                if (yourAnswer == 3) mpTrueAns = new MusicPlayer(Constant.TRUE_C);
                if (yourAnswer == 4) mpTrueAns = new MusicPlayer(Constant.TRUE_D);
                mpTrueAns.play();
                t2 = Constant.TIME_ANIM_CONFIRM_TRUE_ANSWER;
            } else {
                handlerChangeUI.sendEmptyMessage(Constant.ANIM_WRONG_ANSWER);
                MusicPlayer mpWrongAns = null;
                if (question.getTrueCase() == 1) mpWrongAns = new MusicPlayer(Constant.LOSE_A);
                if (question.getTrueCase() == 2) mpWrongAns = new MusicPlayer(Constant.LOSE_B);
                if (question.getTrueCase() == 3) mpWrongAns = new MusicPlayer(Constant.LOSE_C);
                if (question.getTrueCase() == 4) mpWrongAns = new MusicPlayer(Constant.LOSE_D);
                mpWrongAns.play();
                t2 = Constant.TIME_ANIM_CONFIRM_WRONG_ANSWER;
            }
        }

        if (timeAnim == t1 + t2) {
            handlerChangeUI.sendEmptyMessage(Constant.SET_DEFAULT_ANIM_BTN_ANSWER);
            timeAnim = 0;
            if (yourAnswer == question.getTrueCase()) {
                if (posQuestion == 15) {
                    handlerChangeUI.sendEmptyMessage(Constant.ACCEPTED);
                    isGameRunning = false;
                } else {
                    isNextQuestion = true;
                    isInTimeAnswer = false;
                }
            } else {
                isGameRunning = false;
                handlerChangeUI.sendEmptyMessage(Constant.WRONG_ANSWER);
            }
        }
    }

    private void prepareNextQuestion() {
        question = questionManager.getNextQuestion();
        posQuestion = questionManager.getPosQuestion();
        yourAnswer = -1;
        time = 30;
        timeAnim = 0;
        isNextQuestion = false;
        isOpenDrawer = true;
        isInTimeAnswer = false;
    }

    private int[] getIdQues(int pos) {
        if (pos == 1) return Constant.QUES_1;
        if (pos == 2) return Constant.QUES_2;
        if (pos == 3) return Constant.QUES_3;
        if (pos == 4) return Constant.QUES_4;
        if (pos == 5) return Constant.QUES_5;
        if (pos == 6) return Constant.QUES_6;
        if (pos == 7) return Constant.QUES_7;
        if (pos == 8) return Constant.QUES_8;
        if (pos == 9) return Constant.QUES_9;
        if (pos == 10) return Constant.QUES_10;
        if (pos == 11) return Constant.QUES_11;
        if (pos == 12) return Constant.QUES_12;
        if (pos == 13) return Constant.QUES_13;
        if (pos == 14) return Constant.QUES_14;
        return Constant.QUES_15;
    }

    private void openAndCloseDrawer() {
        timeOpenDrawer++;
        if (timeOpenDrawer == 1) {
            tBeginMpQues = 1;
            handlerChangeUI.sendEmptyMessage(Constant.UPDATE_DRAWER_MONEY);
            handlerChangeUI.sendEmptyMessage(Constant.OPEN_DRAWER);
            if (posQuestion == 6 || posQuestion == 11) {
                if (posQuestion == 6) {
                    mpCongrats = new MusicPlayer(Constant.VUOT_MOC_1);
                    mpCongrats.play();
                } else {
                    mpCongrats = new MusicPlayer(Constant.VUOT_MOC_2);
                    mpCongrats.play();
                }
                tBeginMpQues = 7500;
            }
        }

        if (timeOpenDrawer == tBeginMpQues) {
            mpQues = new MusicPlayer(getIdQues(posQuestion));
            mpQues.play();
        }

        if (timeOpenDrawer == tBeginMpQues + Constant.TIME_OPEN_DRAWER) {
            handlerChangeUI.sendEmptyMessage(Constant.CLOSE_DRAWER);
            isOpenDrawer = false;
            isInTimeAnswer = true;
            timeOpenDrawer = 0;
            isMpBgPlayAgain = true;
            handlerChangeUI.sendEmptyMessage(Constant.UPDATE_NEW_QUESTION);
            if (posQuestion == 5 || posQuestion == 10 || posQuestion == 15) {
                mpImportant = new MusicPlayer(R.raw.important);
                mpImportant.play();
            }
        }
    }

    private void help5050() {
        timeHelp5050++;
        if (timeHelp5050 == 1) {
            mpHelp5050 = new MusicPlayer(Constant.SOUND_5050);
            mpHelp5050.play();
        }

        if (timeHelp5050 == Constant.TIME_HELP_5050) {
            Random random = new Random();
            int r = random.nextInt(3);
            int dem = -1;
            int sl = -1;
            delQues = new int[2];
            for (int i = 1; i <= 4; i++) {
                if (i != question.getTrueCase()) {
                    dem++;
                    if (dem != r) {
                        delQues[++sl] = i;
                    }
                }
            }
            handlerChangeUI.sendEmptyMessage(Constant.HELP_5050);
            isInTimeAnswer = true;
            isHelp5050 = false;
            isMpBgPlayAgain = true;
        }
    }

    private void helpCall() {
        if (!isOpenHelpCall) {
            mpHelpCall1 = new MusicPlayer(R.raw.help_call);
            mpHelpCall1.play();
            handlerChangeUI.sendEmptyMessage(Constant.HELP_CALL);
            isOpenHelpCall = true;
        } else {
            if (chose != -1) {
                timeHelpCall++;
                if (timeHelpCall == 1) {
                    mpHelpCall2 = new MusicPlayer(R.raw.call);
                    mpHelpCall2.play();
                }
                if (timeHelpCall == Constant.TIME_HELP_CALL) {
                    handlerChangeUI.sendEmptyMessage(chose);
                    isHelpCall = false;
                }
            }
        }
    }

    private void helpAudience() {
        timeHelpAudience++;
        if (timeHelpAudience == 1) {
            mpHelpAudience1 = new MusicPlayer(R.raw.khan_gia);
            mpHelpAudience1.play();
            Random r = new Random();
            if (btnAnswerA.getVisibility() == View.VISIBLE && btnAnswerB.getVisibility() == View.VISIBLE
                    && btnAnswerC.getVisibility() == View.VISIBLE && btnAnswerD.getVisibility() == View.VISIBLE) {
                if (question.getTrueCase() == 1) {
                    perA = getPercentTrueAnswer();
                    perB = r.nextInt(101 - perA);
                    perC = r.nextInt(101 - perA - perB);
                    perD = 100 - perA - perB - perC;
                }

                if (question.getTrueCase() == 2) {
                    perB = getPercentTrueAnswer();
                    perA = r.nextInt(101 - perB);
                    perC = r.nextInt(101 - perA - perB);
                    perD = 100 - perA - perB - perC;
                }

                if (question.getTrueCase() == 3) {
                    perC = getPercentTrueAnswer();
                    perA = r.nextInt(101 - perC);
                    perB = r.nextInt(101 - perA - perC);
                    perD = 100 - perA - perB - perC;
                }

                if (question.getTrueCase() == 4) {
                    perD = getPercentTrueAnswer();
                    perA = r.nextInt(101 - perD);
                    perB = r.nextInt(101 - perA - perD);
                    perC = 100 - perA - perB - perD;
                }
            } else {
                if (question.getTrueCase() == 1) {
                    perA = getPercentTrueAnswer();
                    if (btnAnswerB.getVisibility() == View.INVISIBLE) perB = 0;
                    else perB = 100 - perA;
                    if (btnAnswerC.getVisibility() == View.INVISIBLE) perC = 0;
                    else perC = 100 - perA;
                    if (btnAnswerD.getVisibility() == View.INVISIBLE) perD = 0;
                    else perD = 100 - perA;
                }

                if (question.getTrueCase() == 2) {
                    perB = getPercentTrueAnswer();
                    if (btnAnswerA.getVisibility() == View.INVISIBLE) perA = 0;
                    else perA = 100 - perB;
                    if (btnAnswerC.getVisibility() == View.INVISIBLE) perC = 0;
                    else perC = 100 - perB;
                    if (btnAnswerD.getVisibility() == View.INVISIBLE) perD = 0;
                    else perD = 100 - perB;
                }

                if (question.getTrueCase() == 3) {
                    perC = getPercentTrueAnswer();
                    if (btnAnswerA.getVisibility() == View.INVISIBLE) perA = 0;
                    else perA = 100 - perC;
                    if (btnAnswerB.getVisibility() == View.INVISIBLE) perB = 0;
                    else perB = 100 - perC;
                    if (btnAnswerD.getVisibility() == View.INVISIBLE) perD = 0;
                    else perD = 100 - perC;
                }

                if (question.getTrueCase() == 4) {
                    perD = getPercentTrueAnswer();
                    if (btnAnswerA.getVisibility() == View.INVISIBLE) perA = 0;
                    else perA = 100 - perD;
                    if (btnAnswerB.getVisibility() == View.INVISIBLE) perB = 0;
                    else perB = 100 - perD;
                    if (btnAnswerC.getVisibility() == View.INVISIBLE) perC = 0;
                    else perC = 100 - perD;
                }
            }

            maxPer = Math.max(Math.max(perA, perB), Math.max(perC, perD));
            pA = 0;
            pB = 0;
            pC = 0;
            pD = 0;
            handlerChangeUI.sendEmptyMessage(Constant.HELP_AUDIENCE);
        }

        if (timeHelpAudience >= Constant.TIME_HELP_AUDIENCE) {
            if (timeHelpAudience == Constant.TIME_HELP_AUDIENCE) {
                mpHelpAudience2 = new MusicPlayer(R.raw.hoi_y_kien_chuyen_gia_01b);
                mpHelpAudience2.play();
            }

            if ((timeHelpAudience - Constant.TIME_HELP_AUDIENCE) % 30 == 0) {
                pA++;
                pB++;
                pC++;
                pD++;
                handlerChangeUI.sendEmptyMessage(Constant.UPDATE_HELP_AUDIENCE);
            }
        }

        if (pA >= perA && pB >= perB && pC >= perC && pD >= perD) isHelpAudience = false;
    }

    private void helpChangeQuestion() {
        handlerChangeUI.sendEmptyMessage(Constant.CHANGE_QUESTION);
        isHelpChangeQuestion = false;
    }

    private void helpStop() {
        handlerChangeUI.sendEmptyMessage(Constant.STOP_GAME);
        isHelpStop = false;
    }

    @Override
    public void run() {
        count = 0;
        mpGoFind = new MusicPlayer(Constant.GO_FIND);
        mpGoFind.play();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (isGameRunning) {
            if (isNextQuestion) { // tao moi cau hoi
                prepareNextQuestion();
            }
            if (isOpenDrawer) { // mo drawer
                openAndCloseDrawer();
            }
            if (isHelp5050) {
                help5050();
            }
            if (isHelpCall) {
                helpCall();
            }
            if (isHelpAudience) {
                helpAudience();
            }
            if (isHelpChangeQuestion) {
                helpChangeQuestion();
            }
            if (isHelpStop) {
                helpStop();
            }

            if (isInTimeAnswer) { // trong thoi gian tra loi cau hoi
                if (isMpBgPlayAgain) {
                    if (posQuestion < 5) mpBackground = new MusicPlayer(R.raw.background_music);
                    else if (posQuestion <= 10)
                        mpBackground = new MusicPlayer(R.raw.background_music_b);
                    else mpBackground = new MusicPlayer(R.raw.background_music_c);
                    mpBackground.loop();
                    isMpBgPlayAgain = false;
                }

                if (yourAnswer != -1) {
                    checkAnswer();
                }

                if (yourAnswer == -1) {
                    count++;
                    if (count == 950) {
                        count = 0;
                        time--;
                        handlerChangeUI.sendEmptyMessage(Constant.UPDATE_TIME);
                    }
                    if (time == 0) {
                        handlerChangeUI.sendEmptyMessage(Constant.TIME_LIMIT);
                        isGameRunning = false;
                    }
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAllButtonUnClickable() {
        btnAnswerA.setClickable(false);
        btnAnswerB.setClickable(false);
        btnAnswerC.setClickable(false);
        btnAnswerD.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        mpTouch = new MusicPlayer(R.raw.touch_sound);
        mpTouch.play();

        switch (v.getId()) {
            case R.id.btn_answer_a: {
                yourAnswer = 1;
                setAllButtonUnClickable();
                mpBackground.stop();
                break;
            }

            case R.id.btn_answer_b: {
                yourAnswer = 2;
                setAllButtonUnClickable();
                mpBackground.stop();
                break;
            }

            case R.id.btn_answer_c: {
                yourAnswer = 3;
                setAllButtonUnClickable();
                mpBackground.stop();
                break;
            }

            case R.id.btn_answer_d: {
                yourAnswer = 4;
                setAllButtonUnClickable();
                mpBackground.stop();
                break;
            }

            case R.id.btn_help_5050: {
                if (yourAnswer != -1) break;
                if (isInTimeAnswer) {
                    btnHelp5050.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
                    btnHelp5050.setClickable(false);
                    isHelp5050 = true;
                    timeHelp5050 = 0;
                    isInTimeAnswer = false;
                    mpBackground.stop();
                }
                break;
            }

            case R.id.btn_help_call: {
                if (yourAnswer != -1) break;
                if (isInTimeAnswer) {
                    btnHelpCall.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
                    btnHelpCall.setClickable(false);
                    isHelpCall = true;
                    isInTimeAnswer = false;
                    mpBackground.stop();
                }
                break;
            }

            case R.id.btn_help_audience: {
                if (yourAnswer != -1) break;
                if (isInTimeAnswer) {
                    btnHelpAudience.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
                    btnHelpAudience.setClickable(false);
                    isHelpAudience = true;
                    isInTimeAnswer = false;
                    mpBackground.stop();
                }
                break;
            }

            case R.id.btn_help_change_question: {
                if (yourAnswer != -1) break;
                if (isInTimeAnswer) {
                    isHelpChangeQuestion = true;
                }
                break;
            }

            case R.id.btn_help_stop: {
                if (yourAnswer != -1) break;
                if (isInTimeAnswer) {
                    isHelpStop = true;
                }
                break;
            }

            default: {
                yourAnswer = -1;
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        isHelpStop = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mpBackground != null) mpBackground.stop();
        if (mpRule != null) mpRule.stop();
        if (mpReady != null) mpReady.stop();
        if (mpEnd != null) mpEnd.stop();
        if (mpHelpAudience1 != null) mpHelpAudience1.stop();
        if (mpHelpAudience2 != null) mpHelpAudience2.stop();
        if (mpAns != null) mpAns.stop();
        if (mpAnsNow != null) mpAnsNow.stop();
        if (mpTrueAns != null) mpTrueAns.stop();
        if (mpCongrats != null) mpCongrats.stop();
        if (mpQues != null) mpQues.stop();
        if (mpImportant != null) mpImportant.stop();
        if (mpHelp5050 != null) mpHelp5050.stop();
        if (mpHelpCall1 != null) mpHelpCall1.stop();
        if (mpHelpCall2 != null) mpHelpCall2.stop();
        if (mpGoFind != null) mpGoFind.stop();
        if (mpTouch != null) mpTouch.stop();
    }
}
