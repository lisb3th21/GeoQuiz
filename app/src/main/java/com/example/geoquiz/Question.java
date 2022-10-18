package com.example.geoquiz;

public class Question {
    public String getTextResId() {
        return textResId;
    }

    public void setTextResId(String textResId) {
        this.textResId = textResId;
    }

    private String textResId;

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    private boolean answer;
    private Answered contestada;

    public Question() {
    }

    public Answered getContestada() {
        return contestada;
    }

    public void setContestada(Answered contestada) {
        this.contestada = contestada;
    }





    public Question(String textResId, boolean answer){
        this.textResId = textResId;
        this.answer = answer;
        this.contestada = Answered.HIDE;
    }


    @Override
    public String toString() {
        return "Question{" +
                "textResId='" + textResId + '\'' +
                ", answer=" + answer +
                ", contestada=" + contestada +
                '}';
    }
}
