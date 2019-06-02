package com.cleitondsd.etec.quizetecv1;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionTable implements BaseColumns {

        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "opcao1";
        public static final String COLUMN_OPTION2 = "opcao2";
        public static final String COLUMN_OPTION3 = "opcao3";
        public static final String COLUMN_ANSWER_NR = "numeroResposta";
        public static final String COLUMN_DIFFICULTY = "dificuldade";

    }
}
