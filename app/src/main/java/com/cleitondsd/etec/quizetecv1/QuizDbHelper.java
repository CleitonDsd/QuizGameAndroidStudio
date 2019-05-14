package com.cleitondsd.etec.quizetecv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cleitondsd.etec.quizetecv1.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizEtec.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;


        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionTable() {
        Question q1 = new Question("A é a correta ", "A", "B", "C", 1);
        addQuestion(q1);

        Question q2 = new Question("B é a correta ", "A", "B", "C", 2);
        addQuestion(q2);

        Question q3 = new Question("B é a correta novamente ", "A", "B", "C", 2);
        addQuestion(q3);

        Question q4 = new Question("C é a correta ", "A", "B", "C", 3);
        addQuestion(q4);

        Question q5 = new Question("A é a correta novamente ", "A", "B", "C", 1);
        addQuestion(q5);

        Question q6 = new Question("C é a correta novamente", "A", "B", "C", 3);
        addQuestion(q6);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestao());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOpcao1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOpcao2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOpcao3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getNumeroResposta());

        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }
}
