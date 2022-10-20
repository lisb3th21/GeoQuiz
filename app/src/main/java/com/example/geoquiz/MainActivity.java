
package com.example.geoquiz;

import android.app.AlertDialog;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Question> question_bank = new ArrayList<>();
    private static final String DEBUG_TAG = "texto";
    static final String BUNDLE_POSICION = "position";
    private int position;

    /**
     * The function onCreate() is called when the activity is created.
     * 
     * @param savedInstanceState A Bundle object containing the activity's
     *                           previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        addQuestions();

        if (savedInstanceState != null) {

            position = savedInstanceState.getInt(BUNDLE_POSICION);
        } else {

            position = 0;
        }
        mostrarPreguntas(question_bank.get(position));

    }

    /**
     * It takes the strings from the arrays in the strings.xml file and adds them to
     * the question_bank
     * array
     */
    public void addQuestions() {
        String[] falsos = getResources().getStringArray(R.array.questionsFalse);
        String[] verdaderos = getResources().getStringArray(R.array.questionsTrue);

        for (int i = 0; i < falsos.length; i++) {
            question_bank.add(new Question(falsos[i], false));
        }
        for (int i = 0; i < verdaderos.length; i++) {
            question_bank.add(new Question(falsos[i], true));
        }
    }

    /**
     * When the user clicks the true button, the button is disabled, and the
     * question is marked as
     * answered, and if all the questions have been answered, the results are
     * calculated.
     * 
     * @param target The view that was clicked.
     */
    public void trueButton(View target) {
        Button bt = (Button) findViewById(R.id.boton_true);
        bt.setEnabled(false);
        if (question_bank.get(position).isAnswer()) {

            question_bank.get(position).setContestada(Answered.CORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        } else {
            question_bank.get(position).setContestada(Answered.INCORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        }
        if (allAnswered()) {
            calculateResults();
        }
    }

    /**
     * When the user clicks the false button, the button is disabled, and the
     * question is marked as
     * answered.
     * 
     * The function is called from the XML file, and the button is disabled.
     * 
     * The function then checks if all the questions have been answered, and if they
     * have, it calls the
     * calculateResults() function.
     * 
     * The calculateResults() function is as follows:
     * 
     * @param target The view that was clicked.
     */
    public void falseButton(View target) {
        Button bt = (Button) findViewById(R.id.boton_false);
        bt.setEnabled(false);
        if (!question_bank.get(position).isAnswer()) {
            question_bank.get(position).setContestada(Answered.CORRECT);
        } else {
            question_bank.get(position).setContestada(Answered.INCORRECT);
        }
        if (allAnswered()) {
            calculateResults();
        }
    }

    /**
     * It displays the question and the buttons
     * 
     * @param question is the question object that contains the question text and
     *                 the answer.
     */
    public void mostrarPreguntas(Question question) {
        TextView v = (TextView) findViewById(R.id.preguntas);
        v.setText(question.getTextResId());
        if (position == 0) {
            findViewById(R.id.boton_anterior).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.boton_anterior).setVisibility(View.VISIBLE);
        }
        if (position == question_bank.size() - 1) {
            findViewById(R.id.boton_siguiente).setVisibility(View.INVISIBLE);
            int contestadas = 0;
            for (Question q : question_bank) {
                if (q.getContestada().equals(Answered.CORRECT) || q.getContestada().equals(Answered.INCORRECT))
                    contestadas++;
            }

        } else {
            findViewById(R.id.boton_siguiente).setVisibility(View.VISIBLE);
        }
        if (question.getContestada().equals(Answered.CORRECT) || question.getContestada().equals(Answered.INCORRECT)) {
            findViewById(R.id.boton_true).setVisibility(View.INVISIBLE);
            findViewById(R.id.boton_false).setVisibility(View.INVISIBLE);

        } else {
            Button bt = (Button) findViewById(R.id.boton_false);
            bt.setEnabled(true);
            bt = (Button) findViewById(R.id.boton_true);
            bt.setEnabled(true);
            findViewById(R.id.boton_true).setVisibility(View.VISIBLE);
            findViewById(R.id.boton_false).setVisibility(View.VISIBLE);
        }

    }

    /**
     * It calculates the number of correct and incorrect answers and displays them
     * in a dialog box
     */
    public void calculateResults() {
        int correctas = 0;
        int incorrectas = 0;
        for (Question question : question_bank) {
            if (question.getContestada().equals(Answered.CORRECT))
                correctas++;
            else
                incorrectas++;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Results");
        builder.setMessage("Corrects= " + correctas + "\nIncorrects= " + incorrectas);
        builder.setPositiveButton("Try again", (dialog, which) -> {
            position = 0;
            question_bank = new ArrayList<>();
            addQuestions();
            mostrarPreguntas(question_bank.get(position));

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * The function is called when the user clicks the next button. It increments
     * the position variable by
     * 1 and then calls the mostrarPreguntas() function to display the next question
     * 
     * @param view The view that was clicked.
     */
    public void nextButton(View view) {
        position++;
        mostrarPreguntas(question_bank.get(position));
    }

    /**
     * The function is called when the user clicks the previous button. It decreases
     * the position by 1 and
     * then calls the function mostrarPreguntas() with the question at the new
     * position.
     * 
     * @param view The view that was clicked.
     */
    public void previousButton(View view) {
        position--;
        mostrarPreguntas(question_bank.get(position));
    }

    /**
     * It returns true if all the questions in the question bank have been answered
     * 
     * @return The number of questions that have been answered correctly or
     *         incorrectly.
     */
    public boolean allAnswered() {
        int cont = 0;
        for (Question q : question_bank) {
            if (q.getContestada().equals(Answered.CORRECT) || q.getContestada().equals(Answered.INCORRECT)) {
                cont++;
            }
        }
        return cont == question_bank.size();
    }

    /**
     * When the activity is destroyed, save the current position in the bundle.
     * 
     * @param savedInstanceState A Bundle object containing the activity's
     *                           previously saved state.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(BUNDLE_POSICION, position);

    }

    /**
     * The onRestoreInstanceState() method is called after onStart() when the
     * activity is being
     * re-initialized from a previously saved state, given here in
     * savedInstanceState
     * 
     * @param savedInstanceState A Bundle object containing the activity's
     *                           previously saved state.
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt(BUNDLE_POSICION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}