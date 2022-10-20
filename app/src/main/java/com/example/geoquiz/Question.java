package com.example.geoquiz;

public class Question {
    private String textResId;
    private boolean answer;
    private Answered contestada;

    public String getTextResId() {
        return textResId;
    }
    public void setTextResId(String textResId) {
        this.textResId = textResId;
    }
    public boolean isAnswer() {
        return answer;
    }
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    public Answered getContestada() {
        return contestada;
    }
    public void setContestada(Answered contestada) {
        this.contestada = contestada;
    }

    public Question() {
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
