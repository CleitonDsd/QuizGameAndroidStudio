package com.cleitondsd.etec.quizetecv1;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {


    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    private int id;
    private String questao;
    private String opcao1, opcao2, opcao3;
    private int numeroResposta;
    private String difficulty;
    private int categoryID;

    public Question() {
    }

    public Question(String questao, String opcao1, String opcao2, String opcao3,
                    int numeroResposta, String difficulty, int categoryID) {
        this.questao = questao;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.numeroResposta = numeroResposta;
        this.difficulty = difficulty;
        this.categoryID = categoryID;
    }

    //Get e Set

    protected Question(Parcel in) {
        id = in.readInt();
        questao = in.readString();
        opcao1 = in.readString();
        opcao2 = in.readString();
        opcao3 = in.readString();
        numeroResposta = in.readInt();
        difficulty = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(questao);
        dest.writeString(opcao1);
        dest.writeString(opcao2);
        dest.writeString(opcao3);
        dest.writeInt(numeroResposta);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public String getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(String opcao3) {
        this.opcao3 = opcao3;
    }

    public int getNumeroResposta() {
        return numeroResposta;
    }

    public void setNumeroResposta(int numeroResposta) {
        this.numeroResposta = numeroResposta;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static String[] getAllDifficultyLevels() {

        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };

    }
}
