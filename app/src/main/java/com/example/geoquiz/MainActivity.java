package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    HashMap<String, Boolean> question_bank=new HashMap<>();
    private static  final String DEBUG_TAG = "texto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // se añadiran las preguntas que esten en strings.xml
        addQuestions();

        // creando variables de los botones
        View trueButton = findViewById(R.id.boton_true);
        View falseButton = findViewById(R.id.boton_false);
        TextView question = findViewById(R.id.preguntas);
        View nextButton = findViewById(R.id.boton_siguiente);
        View previousButton = findViewById(R.id.boton_anterior);
HashMap<String, Boolean> question_bankCopy = question_bank;

//trueButton.setOnClickListener();
        int contador=1;
        Random ran = new Random();
        Object[] values = question_bank.keySet().toArray();
        String randomValue = (String) values[ran.nextInt(values.length)];

        do{

            question.setText(randomValue);
            question_bankCopy.remove(randomValue);


        }while (question_bankCopy.size()==0);

    }

    public void addQuestions(){
        String[] falsos = getResources().getStringArray(R.array.questionsFalse);
        String[] verdaderos = getResources().getStringArray(R.array.questionsTrue);

        for (int i = 0; i < falsos.length; i++) {
            Log.d(DEBUG_TAG, String.valueOf(falsos.length));
            Log.d(DEBUG_TAG, falsos[i]);
            this.question_bank.put(falsos[i], false);
        }
        for (int i = 0; i < verdaderos.length ; i++) {
            this.question_bank.put(verdaderos[i], true);
        }


    }
}