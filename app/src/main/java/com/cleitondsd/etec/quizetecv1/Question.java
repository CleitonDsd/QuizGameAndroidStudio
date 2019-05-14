package com.cleitondsd.etec.quizetecv1;

public class Question  {

    private String questao;
    private String opcao1, opcao2, opcao3;
    private int numeroResposta;

    public  Question(){}

    public Question(String questao, String opcao1, String opcao2, String opcao3, int numeroResposta) {
        this.questao = questao;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.numeroResposta = numeroResposta;
    }

    //Get e Set

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
}
