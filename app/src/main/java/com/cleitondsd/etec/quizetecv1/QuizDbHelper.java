package com.cleitondsd.etec.quizetecv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cleitondsd.etec.quizetecv1.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizEtec.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";


        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY (" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";


        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {

        Category c1 = new Category("Lógica de Programação");
        addCategory(c1);
        Category c2 = new Category("JAVA");
        addCategory(c2);
        Category c3 = new Category("SQL");
        addCategory(c3);

    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Lógica de Programação, Fácil: A é a correta",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q1);

        Question q2 = new Question("Java, Médio: B é a correta",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q2);

        Question q3 = new Question("SQL, Difícil: C é a correta",
                "A", "B", "C", 3,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q3);

        Question q4 = new Question("Lógica de Programação, Difícil: C é a correta",
                "A", "B", "C", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q4);

        Question q5 = new Question("SQL, Médio: B é a correta",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q5);

        Question q6 = new Question("Java, Difícil: A é a correta",
                "A", "B", "C", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
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
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());

        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestao(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOpcao1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOpcao2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOpcao3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setNumeroResposta(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_DIFFICULTY + " = ? ";

        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestao(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOpcao1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOpcao2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOpcao3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setNumeroResposta(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}


