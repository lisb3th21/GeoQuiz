package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Question> question_bank= new ArrayList<>();
    private static  final String DEBUG_TAG = "texto";
    private int numPreguntas;
    private final Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // se añadiran las preguntas que esten en strings.xy
        addQuestions();

        // creando variables de los botones
        Button trueButton = findViewById(R.id.boton_true);
        Button falseButton = findViewById(R.id.boton_false);
        TextView questionView = findViewById(R.id.preguntas);
        Button nextButton = findViewById(R.id.boton_siguiente);
        Button previousButton = findViewById(R.id.boton_anterior);


        int contador=1;
        numPreguntas = question_bank.size();
        Question question ;
//        while (contador<numPreguntas){
            question = getHideQuestion();
            contador++;
//        }
    questionView.setText(question.getTextResId());
    }

    public void addQuestions(){
        String[] falsos = getResources().getStringArray(R.array.questionsFalse);
        String[] verdaderos = getResources().getStringArray(R.array.questionsTrue);

        for (int i = 0; i < falsos.length; i++) {
            question_bank.add(new Question(falsos[i], false));
        }
        for (int i = 0; i < verdaderos.length ; i++) {
            question_bank.add(new Question(falsos[i], true));
        }
    }

    public void trueButton(View target, Question question){
        if(question.isAnswer()){
            question.setContestada(Answered.CORRECT);
        }else{
            question.setContestada(Answered.INCORRECT);
        }
    }

    public void falseButton(View target, Question question){
        if(!question.isAnswer()){
            question.setContestada(Answered.CORRECT);
        }else{
            question.setContestada(Answered.INCORRECT);
        }
    }

    public Question getHideQuestion(){
        Question question = new Question();
        boolean hide = false;
        do {
            question = question_bank.get(ran.nextInt(question_bank.size()));
            if(question.getContestada().equals(Answered.HIDE)){
                hide = true;
            }
        }while (hide);
        return  question;
    }

}