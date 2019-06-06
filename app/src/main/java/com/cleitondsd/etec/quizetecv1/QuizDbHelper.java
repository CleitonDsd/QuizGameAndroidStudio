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
        //Java | Fácil

        Question q1 = new Question("O que é necessário para o desenvolvimento na linguagem Java?",
                "JDK, JRE e IDE", "JDK e JRE", "KFC, BK e Mc", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q1);

        Question q2 = new Question("Quando foi lançada a primeira versão do Java?",
                "1997", "1995", "1996", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q2);

        Question q3 = new Question("Os programas feitos em Java são compilados em um formato própio, denomina-se:",
                "ByteCode", "BitCode", "JavaC", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q3);

        Question q4 = new Question("O que a JVM faz?",
                "Java Virtual Machine", "Reponsável por Versionar o Java", "Interpreta os ByteCodes e transforma em código nativo",
                3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q4);

        Question q5 = new Question("Qual é o operador a seguir: \n <condicao> ? <expressao_true> : <expressao_false>",
                "Operador de Desvio de Fluxo", "Operador Ternario", "Operador Binário", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q5);

        Question q6 = new Question("Como são chamadas as intruções de um programa em Java?",
                "TDD", "Cognátas", "Diretivas", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q6);

        Question q7 = new Question("Quais são os Tipos de Estruturas de Controle: ",
                "Simples, Composta e Condicional", "If, While e For", "Repetição Simples, Repetição Condicional e Desvio de Fluxo", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q7);

        Question q8 = new Question("Como é feito um vetor em Java?",
                "tipoVariavel [] nomeVariavel = new tipoVariavel [tamanho]", "nomeVariavel [] tipoVariavel = new tipoVariavel [tamanho]", "tipoVariavel nomeVariavel [][] = new tipoVariavel [tamanho][tamanho]", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q8);

        Question q9 = new Question("O que é uma Classe em Java?",
                "Um objeto definido por um Construtor através de uma classe FINAL", "Modelo definido para um tipo de Objeto", "Cópia Identica de Todos os Atributos, inclusive o nome de outra Classe", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q9);

        Question q10 = new Question("API 3.1 Classe (Java.lang.String) \n o que faz a classe 'String trim()':",
                "Cria uma String que não pode ser alterada", "Faz a verificação do tamanho da String", "Obtém uma nova String sem espaços em Branco", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q10);

        //Java | Médio

        Question q11 = new Question("Para que serve '@override' ?",
                "Confirma que os argumentos são seguros", "Indica que o metódo anotado sobrepõe o outro", "Indica que o metódo anotado sobreescreve o outro", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q11);
        Question q12 = new Question("O que fazem as Exceções(Exception)?",
                "Indica que o parametro é excepcional", "Indicam uma exceção livre", "Indicam a ocorrência de erros", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q12);
        Question q13 = new Question("'throw new RuntimeException('mensagem'); Indica:",
                "Uma exceção não monitorada ", "Uma exceção monitorada  ", "Indica uma exceção livre em quanto executa o projeto", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q13);
        Question q14 = new Question("Para que Serve um pacote?",
                "Separam os projetos para que não de erro no JavaC", "Separar os Construtores em posições diferentes ", "Separa as classes conforme seu propósito ", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q14);
        Question q15 = new Question("O que é uma Interface em Java",
                "Definição de um modelo semântico para outras classes", "É um componente visual do JavaFX ", "Parte Gráfica do Projeto ", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q15);
        Question q16 = new Question("Para que serve a interface Deque<E>?",
                "Define o tipo de Exceção<E> ", "Define filas especiais(double ended queues)", "Define construtores especiais(int new equals) ", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q16);
        Question q17 = new Question("CompareTo, é usado para: ",
                "Compara o objeto com o argumento dado", "Compara a variavel com o metodo dado ", "Compara o tipo de variáveis ", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q17);
        Question q18 = new Question("public static int numero 0; É: ",
                "Uma variável membro estática ", "Uma variável final de valor 0 ", "Uma variável membro protegida ", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q18);
        Question q19 = new Question("Porque o método main(String[]) é estático?",
                "Método Main não é editável", "Porque é uma Classe protegida", "Para que a JVM não realize a instanciação de objetos", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q19);
        Question q20 = new Question("O que faz 'System.gc();' ?",
                "Aciona o coletor de lixos", "Denomina o Garbage Colector (GC)", "Remove objetos usados", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q20);

        //Java | Dificil

        Question q21 = new Question("Para que server a Classe DataOutPutStream ?",
                "Permite a saída de dados de tipos primitivos", "Permite a produção de dados formatados para exibição ", "Retorna o número de bytes no Stream", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q21);
        Question q22 = new Question("Statement stmt = con.createStatement(); \n O que faz?",
                "Lê um estado da classe Swing", "Permite o envio de comando estáticos(SQL)", "Cria um Estado", 2,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q22);
        Question q23 = new Question("static INetAddres getLocalHost(); ",
                "Retorna o endereço IP do host como um objeto InetAddres", "Retorna o endereço IP do host como um objeto Multicas ", "Retorna o endereço Multicas do host como um objeto InetAddres ", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q23);
        Question q24 = new Question("ServerSocket servidor = new ServerSocket(1234);",
                "Interage o objeto servidor com o sockete do AWT", "Cria um soquete utilizada no servidor com 4 posições", "Cria um servidor e usa a porta 1234", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q24);
        Question q25 = new Question("Datagram Sockets, são suportados por quais Protocolos?",
                "HTTP/HTTPS", "UDP/IP", "IP/HTTPS", 2,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q25);
        Question q26 = new Question("Provê a infrastrutura de uma coleção imutável: ",
                "HashSet<E>", "AbstractListCollection<E> ", "AbstractCollection<E> ", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q26);
        Question q27 = new Question("Date, Stringm File... São tipos de classes:",
                "Lexicográfica", "Wrappers", "Comparable<C> ", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q27);
        Question q28 = new Question("A classe Jframe: ",
                "É derivada do JComponent", "É um JComponent", "Não é derivada do JComponent", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q28);
        Question q29 = new Question("Organiza os componentes numa Grade Layout:",
                "GRID Layout", "TabeLess", "Java AWT Tables", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q29);
        Question q30 = new Question("Contém metodos para criacaçõ de visões e estrutura de dados, como streams, destinadas a construtores de biblioteca: ",
                "@SuppressWarnings", "Collectors", "StreamSupport ", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q30);


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


