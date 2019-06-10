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

        Category c1 = new Category("LÓGICA");
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

        Question q3 = new Question("Os programas feitos em Java são compilados em um formato própio, denominado de:",
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
                "TDD", "Cognatas", "Diretivas", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q6);

        Question q7 = new Question("Quais são os tipos de estruturas de controle: ",
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

        Question q10 = new Question("API 3.1 Classe (Java.lang.String) \n O que faz a classe 'String trim()':",
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
        Question q14 = new Question("Para que Serve um pacote(OO)?",
                "Separam os projetos para que não de erro no JavaC", "Separar os Construtores em posições diferentes ", "Separa as classes conforme seu propósito ", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q14);
        Question q15 = new Question("O que é uma Interface em Java",
                "Definição de um modelo semântico para outras classes", "É um componente visual do JavaFX ", "Parte Gráfica do Projeto ", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q15);
        Question q16 = new Question("Para que serve a interface Deque<E>?",
                "Define o tipo de Exceção<E> ", "Define filas especiais(double, ended, queues)", "Define construtores especiais(int new equals) ", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q16);
        Question q17 = new Question("CompareTo, é usado para: ",
                "Compara o objeto com o argumento dado", "Compara a variável com o metodo dado ", "Compara o tipo de variáveis ", 2,
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
        Question q27 = new Question("Date, String, File... São tipos de classes:",
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

        //Lógica de programação fácil

        Question q31 = new Question("Conjunto das regras e procedimentos lógicos",
                "Logaritmo", "Algoritmo", "Algarismo", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q31);
        Question q32 = new Question("O que é uma variável? ",
                "Uma posição localizada no endereço de memória", "Uma posição de uma função", "conjunto das regras e procedimentos lógicos", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q32);
        Question q33 = new Question("Um método padronizado para comunicar instruções para um computador.",
                "Assembly", "Conjunto de Logaritmos", "Linguagem de Programação", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q33);
        Question q34 = new Question("Linguagem de programação que segue as características da arquitetura do computador: ",
                "C", "C++", "C#", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q34);
        Question q35 = new Question("Longe do código de máquina e mais próximo à linguagem humana.",
                "Java", "Linguagens de Alto Nível", "Kotlin", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q35);
        Question q36 = new Question("O VisuAlg prevê quatro tipos de dados:",
                "Verdadeiros, Falsos e Lógicos", "int, boolean, String e double", "inteiro, real, cadeia de caracteres e lógico ", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q36);
        Question q37 = new Question("É uma forma de escrever algoritmo: ",
                "Pseudocódigo ", "Linguagem de programação", "Assembly", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q37);
        Question q38 = new Question("Definição de IDE: ",
                "Ambiente Superficial de Desenvolvimento", "Ambiente Integral de Desenvolvimento", "Ambiente Parcial de Desenvolvimento", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q38);
        Question q39 = new Question("18.5 é a variável de um tipo: ",
                "real", "inteiro", "boolean", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q39);
        Question q40 = new Question("Selecione a IDE: ",
                "Sublime Text3", "Visual Studio Code", "Eclipse", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING_LOGIC);
        addQuestion(q40);

        //Lógica de programação médio

        Question q41 = new Question("Conjunto ordenado de dados do mesmo tipo: ",
                "Variáveis", "Constraint", "Vetor", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q41);
        Question q42 = new Question("O que é uma subrotina: ",
                "Uma classe com poucos métodos", "Função", "Listas de sub itens de uma classe", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q42);
        Question q43 = new Question("Valor proveniente de uma variável: ",
                "Ponteiro Nulo", "Subrotina", "Parametro", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q43);
        Question q44 = new Question("Argumento é: ",
                "é o valor que você passa para a função", " é a variável que irá receber um valor em uma função", "Comentário no código", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q44);
        Question q45 = new Question("Paramêtro é: ",
                "Passagem de valor ou referência sem referências", "é o valor que você passa para a função", " é a variável que irá receber um valor em uma função", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q45);
        Question q46 = new Question("Procedimentos em Visualg: ",
                "é uma subrotina que não retorna nenhum valor ", "é um parametro que não retorna nenhum valor ", "é um subprograma que não retorna nenhum valor ", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q46);
        Question q47 = new Question("Processo de encontrar e reduzir defeitos: ",
                "Debug", "Bug", "AntiDebugger", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q47);
        Question q48 = new Question("Estruturas de dados do tipo LIFO (last-in first-out): ",
                "EverStack Pilha", "Stack", "Spack", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q48);
        Question q49 = new Question("Ramo da computação que estuda os diversos mecanismos de organização de dados: ",
                "Estrutura de Dados", "Machine Learning", "Ciência de Dados", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q49);
        Question q50 = new Question("Tipo de diagrama: ",
                "UML", "Pilha fluxocardiograma", "Fluxograma", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING_LOGIC);
        addQuestion(q50);

        //Lógica de programação difícil

        Question q51 = new Question(" computador só entende linguagem de 0's e 1's. \nNesse sentido, é importante conhecer como fazer a conversão de um número da base decimal para um número na base binária. \nO número 87, se escrito na base binária, equivale a:",
                "1111 0111", "0101 0101", "0101 0111", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q51);
        Question q52 = new Question("Para a determinação da parte decimal de um número real, pode-se utilizar a função INT(x), como no exemplo a seguir, onde INT(x) retorna a parte inteira de x.\n" +
                "\n" +
                "x = 3.1415926; \n" +
                "escreva x-INT(x)",
                "Correto", "Falso", "X = 4.6569", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q52);
        Question q53 = new Question(" Por exemplo, um comando if com a expressão  if not (A and B) pode ser reescrito, para quaisquer valores lógicos de A e B, com a expressão:",
                "A or B", "not A or not B", "not (not A or not B)", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q53);
        Question q54 = new Question("((((true AND true) OR false) AND true) AND (true OR (true AND false))) \n" +
                "Considerando os operadores lógicos AND (e) e OR (ou), e os operandos lógicos true (verdadeiro) e false (falso),\n" +
                "é CORRETO afirmar que o valor lógico dessa expressão é:  ",
                "verdadeiro.", "falso", "indefinido", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q54);
        Question q55 = new Question("\n" +
                "\n" +
                "\n" +
                "Fórmula G é uma forma normal disjuntiva da fórmula F,\n" +
                "quando F e G são logicamente equivalentes e,além disso, G é uma disjunção de fórmulas, as quais são conjunções,\n" +
                "cujos termos são todas as proposições atômicas,com eventuais negações, que aparecem em F. Nessas condições, sendo p, q e r proposições\n" +
                " atômicas, uma forma normal disjuntiva para a fórmula\n" +
                " F = (p → q) ∧(q → r) é G = X1 ∨ X2 ∨ X3 ∨ X4, onde, dentre as fórmulas X1, X2, X3 e X4, não figura:  ",
                "(¬p) ∧ (¬q) ∧ (¬r).", "(¬p) ∧ (¬q) ∧ r", " p ∧ q ∧ (¬r).", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q55);
        Question q56 = new Question("Em se tratando de linguagens procedimentais, os dados são globais e, portanto, acessíveis a todos os procedimentos.",
                "Sim, Somente em Assembly", "Errada", "Certo", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q56);
        Question q57 = new Question("Utilizando-se linguagens fracamente tipadas, é possível alterar o tipo de dado contido em uma variável durante a execução do programa.",
                "Correta", "Errado", "Somente em Java", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q57);
        Question q58 = new Question("Considerando A = 10, B = 7 e C = 6, assinale a opção correta relacionada à lógica de programação.",
                " ((A + C) < (B * 2) OR (C + B * 3) < (A * 3))", "(C * 3) <= (3 + C * 2)", "(B * 4) >= (A + A * 2) AND (5 + 5) >= (A))", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q58);
        Question q59 = new Question("Sobre proposições lógicas, assinale a alternativa correta.",
                "Se A = F e B = F, então (A → B) = F", "Se A = V e B = F, então (B → A) = V", "Se A = F e B = V, então (A → B) ∧ (B → A) = V", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q59);
        Question q60 = new Question("Qual das expressões lógicas abaixo NÃO pode ser usada para determinar se um dado mês do ano tem 31 dias? ",
                "MES=1 OR MES=3 OR MES=5 OR MES=7 OR MES=8 AND MES=10 AND MES=12",
                "NOT(MES=4 OR MES=6 OR MES=9 OR MES=11 OR MES=2)",
                "NOT(MES=4 AND MES=6 AND MES=9 AND MES=11 AND MES=2)", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING_LOGIC);
        addQuestion(q60);

        //SQL Fácil

        Question q61 = new Question("Qual a definição palavra SQL:",
                "Smart Query Language.", "Structured Query Logical.", "Structured Query Language.", 3, Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q61);
        Question q62 = new Question("SGBD é um sistema: ",
                "Gerenciador de Banco de Dados.", "Sistema desenvolvido sem Banco de Dados.", "Sistema ERP NoSQL.", 1,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q62);
        Question q63 = new Question("Exemplo de SGBDR",
                "Redis.", "Access.", "MongoDB.", 2,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q63);
        Question q64 = new Question("Banco de dados: ",
                "Grupo de diretivas.", "É um conjunto de arquivos estáticos.", "São coleções organizadas de dados que se relacionam.", 3,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q64);
        Question q65 = new Question("Algo de interesse para a comunidade de usuários do banco de dados: ",
                "Conjunto-Resultado.", "Múltiplas pesquisas focando na UI.", "Entidade.", 3,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q65);
        Question q66 = new Question("Registros no banco de dados, podem ser denominados de: ",
                "Fuplas.", "Tuplas.", "Colunas.", 2,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q66);
        Question q67 = new Question("O formato AAAA-MM-DD, é feito por qual tipo de dado: ",
                "Date.", "DateTime.", "TimeStamp.", 1,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q67);
        Question q68 = new Question("Identificador único de cada linha em uma tabela",
                "Chave Primária", "Id", "Código.", 1,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q68);
        Question q69 = new Question("CRUD, o que é?'",
                "Create, Read, Updata e Delete.", "Create, Read, Update e Delete.", "Create, Read, Updata e Drop.", 2,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q69);
        Question q70 = new Question("Qual SGBD mais utilizado no mundo?",
                "SQL Server.", "MySQL.", "Oracle Database.", 3,
                Question.DIFFICULTY_EASY, Category.SQL);
        addQuestion(q70);

        //SQL Médio

        Question q71 = new Question("Quais são os comandos em SQL",
                "DDL, DML, DCL e TCL, DQL", "DDL, DML, DCL e DTL", "DDL, DML, DCL e TCL", 1,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q71);
        Question q72 = new Question("Qual o código SQL que retorna como resultado os registros entre as tabelas GRU e CGH " +
                "\nque têm a mesma informação no campo Nome, e ainda os registros na tabela CGH" +
                "\nque não tem nome igual na tabela GRU?",
                " ", " ", " ", 1,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q72);
        Question q73 = new Question("Qual o código SQL contém comandos do tipo DDL?",
                "Select * into questoes from questoesmodelo.", "Delete from questoes.", "Insert into questoes select * from questoesmodelo.", 3,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q73);
        Question q74 = new Question("A Linguagem de Consulta Estruturada (SQL) fornece uma gama de tipos de dados que podem ser " +
                "\nutilizados para definir a forma com que informações serão armazenadas no banco de dados.",

                "VARCHAR (tamanho): sequência de caracteres de tamanho variável. " +
                        "\nOs espaços não ocupados pelo texto não são armazenados, são ignorados," +
                        "com capacidade para armazenar de 1 a 255 caracteres.",
                "CHAR (tamanho): sequência de caracteres de tamanho fixo. Os espaços não ocupados pelo texto são armazenados, com capacidade de 1 a 128 caracteres.",
                "DATETIME: permite o armazenamento apenas de hora.", 1,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q74);
        Question q75 = new Question("Essa linguagem(SQL) é dividida em quatro conjuntos, sendo eles linguagens:",
                "Para controle de acesso a dados, para transações, orientada a objetos e de estruturação.",
                "Manipulação de dados, de definição de dados, para controle de transações e para controle de acesso a dados.",
                "Estruturação, de dados, para argumentação de controles e orientada a objetos.", 2,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q75);
        Question q76 = new Question("Pseudocolunas são colunas que não fazem parte da tabela, mas que têm características de colunas. No PL/SQL, pode-se utilizar as seguintes pseudocolunas, EXCETO:",

                "ROWNUM - Número da linha selecionada em uma tabela. Não é afetada pela cláusula ORDER BY.",
                "COLNUM – Número da coluna selecionada em uma tabela. Não é afetada pela cláusula ORDER BY.",
                "CURRVAL - Valor corrente de uma sequência armazenada no banco de dados.", 2,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q76);
        Question q77 = new Question("SELECT C.NOME, SUM(E.SALARIO) " +
                "\nFROM ENFERMEIRO E, CLINICA C " +
                "\nWHERE E.COD_CLI = C.DOD_CLI " +
                "\nGROUP BY C.NOME;" +
                "\nQuantao ao código acima, assinale a alternativa correta: ",

                "A função SUM não pode ser utilizada em uma instrução SQL que contenha a cláusula SELECT.",
                "A consulta tem por finalidade mostrar o nome do enfermeiro, de cada clínica, que possui o maior salário.",
                "A consulta tem por finalidade mostrar o custo total, pago em salários aos enfermeiros, por clínica.", 3,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q77);
        Question q78 = new Question("O comando SQL para extrair uma informação de um banco de dados é:",
                "Select.", "Import.", "Extract.", 1,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q78);
        Question q79 = new Question("Acerca de banco de dados, julgue os itens seguintes. NoSQL são bancos de dados que não aceitam expressões SQL e devem ser armazenados na nuvem.",
                "Com exceção do NOMySQL", "Certo", "Errado", 3,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q79);
        Question q80 = new Question("“Essa função do SQL funciona como uma expressão CASE, onde são testados os valores diferentes de NULL. " +
                "\nDesse modo, o primeiro valor que for diferente de NULL será retornado por tal função.” Assinale-a:",
                "NULLIF.", "EXCEPT DISTINCT.", "INTERSECT.", 2,
                Question.DIFFICULTY_MEDIUM, Category.SQL);
        addQuestion(q80);

        //SQL Difícl

        Question q81 = new Question("instruções SQL que são enviadas ao banco de dados por um invasor SOS sistema com a finalidade de provocar algum dano.",
                "STRING_SANITIZE", "GROUP BY", "SQL Injection", 3,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q81);
        Question q82 = new Question("Com relação à linguagem de consulta estruturada (SQL), marque a única alternativa INCORRETA.",
                "Entre os componentes SQL, aquele que permite aos usuários criar novos componentes com índices e tabelas é chamado de DDL (Data Definition Language).",
                "Create user, Grant, Revoke, são exemplos de controle de autorizações de dados e licenças de usuários provenientes do componente DCL (Data Control Language).",
                "A DML (Data Manipulation Language) permite a manipulação de dados armazenados em um banco de dados, exemplo: alter, insert, update e delete.", 3,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q82);
        Question q83 = new Question("A respeito dos conceitos e comandos SQL SERVER e DB2 e dos bancos de dados textuais, julgue os itens de 85 a 89. O comando CREATE VIEW não está disponível para SQL Server, versão 2005.",
                "Certo", "Somente no MongouMan", "Errado", 3,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q83);
        Question q84 = new Question("Qual o comando com a cláusula WHERE, dentre os abaixo, que está correto?",
                "WHERE COMM IS NULL", "WHERE COMM = 'NULL'", "WHERE COMM NULL", 2,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q84);
        Question q85 = new Question("Qual é o comando com a cláusula WHERE abaixo que está correto?",
                "WHERE SALARY BETWEEN 10000 AND 14000", "WHERE SALARY BETWEEN 14000 AND 10000", "WHERE SALARY BETWEEN (14000 AND 10000", 1,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q85);
        Question q86 = new Question("Qual o comando com a cláusula SELECT, dentre os abaixo, que está correto?",
                "SELECT (JOB, DEPT) FROM Q.STAFF", "SELECT JOB | DEPT FROM Q.STAF", "SELECT JOB, CONCAT (DEPT) FROM Q.STAFF", 1,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q86);
        Question q87 = new Question("Qual a Query, dentre as abaixo, que está correta?",
                "SELECT * FROM Q.STAFF FOR CURSOR ONLY\n" +
                        "\nORDER BY JOB" +
                        "\nWHERE SALARY > 15,000",

                "SELECT * FROM Q.STAFF WHERE SALARY < 15000" +
                        "\nORDER BY JOB" +
                        "\nFOR FETCH ONLY\n",

                "SELECT * FROM Q.STAFF WHERE SALARY > 15000" +
                        "\nORDER BY JOB" +
                        "\nFOR ACCESS ONLY", 2,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q87);
        Question q88 = new Question("Qual a função do comando ROLLBACK?",
                "Desfazer as alterações em caso de abend.", "Incluir os índices em uma tabela.", "Atualizar os dados em caso de abend.", 1,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q88);
        Question q89 = new Question("O que é uma VIEW?",
                "É uma visão física para atualizar uma tabela.", "É uma visão lógica ou máscara sobre uma ou mais tabelas.", "É uma visão lógica para acessar um arquivo.", 2,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q89);
        Question q90 = new Question("Qual tipo de argumento lhe permite prover um número arbitrário de argumentos?",
                "ByRef", "ParamArray", "Named", 2,
                Question.DIFFICULTY_HARD, Category.SQL);
        addQuestion(q90);

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


