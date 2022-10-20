/**
 * Question is a class that contains a question, an answer, and a state of whether it has been answered
 * or not.
 */
package com.example.geoquiz;

public class Question {
    private String textResId;
    private boolean answer;
    private Answered contestada;


/**
 * This function returns the textResId of the current question
 * 
 * @return The textResId is being returned.
 */
    public String getTextResId() {
        return textResId;
    }
/**
 * This function sets the textResId variable to the value of the textResId parameter.
 * 
 * @param textResId The text to be displayed in the TextView
 */
    public void setTextResId(String textResId) {
        this.textResId = textResId;
    }
/**
 * This function returns the value of the boolean variable answer
 * 
 * @return The answer to the question.
 */
    public boolean isAnswer() {
        return answer;
    }
  /**
   * This function sets the answer to the question
   * 
   * @param answer The answer to the question.
   */
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
/**
 * It returns the value of the variable contestada.
 * 
 * @return The value of the variable contestada.
 */
    public Answered getContestada() {
        return contestada;
    }
/**
 * The function sets the value of the variable contestada to the value of the parameter contestada
 * 
 * @param contestada is a boolean value that indicates if the question was answered or not.
 */
    public void setContestada(Answered contestada) {
        this.contestada = contestada;
    }

// A constructor.
    public Question() {
    }

// A constructor.
    public Question(String textResId, boolean answer){
        this.textResId = textResId;
        this.answer = answer;
        this.contestada = Answered.HIDE;
    }

/**
 * The toString() method returns a string representation of the object
 * 
 * @return The textResId, answer, and contestada.
 */
    @Override
    public String toString() {
        return "Question{" +
                "textResId='" + textResId + '\'' +
                ", answer=" + answer +
                ", contestada=" + contestada +
                '}';
    }
}
