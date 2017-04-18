package com.example.ngocsang.ailatrieuphu.manager;

import android.content.Context;


import com.example.ngocsang.ailatrieuphu.model.Question;

import java.util.ArrayList;

/**
 * Created by NgocSang on 10/29/2016.
 */

public class QuestionManager {
    private ArrayList<Question> questions;
    private DatabaseManager databaseManager;
    private int posQuestion;

    public QuestionManager(Context context) {
        databaseManager = new DatabaseManager(context);
        questions = databaseManager.get15Questions();
        posQuestion = -1;
    }

    public Question getNextQuestion() {
        posQuestion++;
        if (posQuestion >= questions.size()) {
            return null;
        }
        return questions.get(posQuestion);
    }

    public Question changeQuestion() {
        Question question = databaseManager.getOneQuestion("Question" + (posQuestion + 1));
        return question;
    }

    public int getPosQuestion() {
        return posQuestion + 1;
    }

    public int getSize() {
        return questions.size();
    }
}
