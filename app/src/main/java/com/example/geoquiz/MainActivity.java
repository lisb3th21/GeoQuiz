package com.example.geoquiz;

import android.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.TextView;



import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Question> question_bank = new ArrayList<>();
    private static final String DEBUG_TAG = "texto";

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // se añadiran las preguntas que esten en strings.xy
        addQuestions();




        mostrarPreguntas(question_bank.get(position));

    }

    /**
     * Toma las preguntas de string.xml y las instrioduce dentro del array de questions
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

    public void trueButton(View target) {
        if (question_bank.get(position).isAnswer()) {
            question_bank.get(position).setContestada(Answered.CORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        } else {
            question_bank.get(position).setContestada(Answered.INCORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        }
    }

    public void falseButton(View target) {
        if (!question_bank.get(position).isAnswer()) {
            question_bank.get(position).setContestada(Answered.CORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        } else {
            question_bank.get(position).setContestada(Answered.INCORRECT);
            Log.d(DEBUG_TAG, question_bank.get(position).toString());
        }
    }



    public void mostrarPreguntas(Question question) {
        TextView v = (TextView)findViewById(R.id.preguntas);
        v.setText(question.getTextResId());
        if(position==0){
            findViewById(R.id.boton_anterior).setVisibility(View.INVISIBLE);
        }else{
            findViewById(R.id.boton_anterior).setVisibility(View.VISIBLE);
        }
        if(position==question_bank.size()-1){
            findViewById(R.id.boton_siguiente).setVisibility(View.INVISIBLE);
            int contestadas=0;
            for (Question q:question_bank) {
                if(q.getContestada().equals(Answered.CORRECT) || q.getContestada().equals(Answered.INCORRECT)) contestadas++;
            }
            if(contestadas==question_bank.size()-1){
                calculateResults();
            }
        }else{
            findViewById(R.id.boton_siguiente).setVisibility(View.VISIBLE);
        }
        if (question.getContestada().equals(Answered.CORRECT) || question.getContestada().equals(Answered.INCORRECT)) {
            findViewById(R.id.boton_true).setVisibility(View.INVISIBLE);
            findViewById(R.id.boton_false).setVisibility(View.INVISIBLE);

        }
    }


    /**
     * ejecute end of the questions
     */
    public void calculateResults() {
        int corectas = 0;
        int incorrectas = 0;
        for (Question question : question_bank) {
            if (question.getContestada().equals(Answered.CORRECT)) corectas++;
            else incorrectas++;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Results");
        builder.setMessage("Corrects= "+corectas+"\nIncorrects= "+incorrectas);
        builder.setPositiveButton("Try again", (dialog, which) -> {
            position=0;
            question_bank = new ArrayList<>();
            addQuestions();
            mostrarPreguntas(question_bank.get(position));

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void nextButton(View view){
        if (position==question_bank.size()-1){
            calculateResults();
        }
        position++;
        mostrarPreguntas(question_bank.get(position));

    }

    public void previousButton(View view) {

        position--;
        mostrarPreguntas(question_bank.get(position));
    }


}