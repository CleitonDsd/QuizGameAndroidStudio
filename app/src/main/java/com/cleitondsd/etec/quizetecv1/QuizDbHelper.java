package com.cleitondsd.etec.quizetecv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cleitondsd.etec.quizetecv1.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

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
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Fácil: A é a correta",
                "A", "B", "C", 1, Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Question q2 = new Question("Médio: B é a correta",
                "A", "B", "C", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q2);
        Question q3 = new Question("Médio: C é a correta",
                "A", "B", "C", 3, Question.DIFFICULTY_MEDIUM);
        addQuestion(q3);
        Question q4 = new Question("Difícil: A é a correta",
                "A", "B", "C", 1, Question.DIFFICULTY_HARD);
        addQuestion(q4);
        Question q5 = new Question("Difícil: B é a correta",
                "A", "B", "C", 2, Question.DIFFICULTY_HARD);
        addQuestion(q5);
        Question q6 = new Question("Difícil: C é a correta",
                "A", "B", "C", 3, Question.DIFFICULTY_HARD);
        addQuestion(q6);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestao());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOpcao1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOpcao2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOpcao3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getNumeroResposta());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());

        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestao(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOpcao1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOpcao2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOpcao3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setNumeroResposta(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));

                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestao(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOpcao1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOpcao2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOpcao3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setNumeroResposta(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}


