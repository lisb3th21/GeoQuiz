# GEOQUIZ

**Geoquiz is an application that can test your knowledge of geography.**

<img src="geoquiz.gif"
     alt="Markdown Monster icon"
     height="600px"/>

It is built in Java. It consists of a single Activity where the questions will change. The user must answer. Once you have answered all the questions, a panel with the results will be displayed.

## Question class

The `question class` has the following attributes:

```java
    private String textResId;
    private boolean answer;
    private Answered contestada;
```

`Answered` can be:

- HIDE
- CORRECT
- INCORRECT

## MainActivity

In the MainActivity I create an `ArrayList` of `Questions`. The program will display the questions as we move forward.

```java
    public void nextButton(View view){
        if (position==question_bank.size()-1){
            calculateResults();
        }
        position++;
        mostrarPreguntas(question_bank.get(position));

    }
```

And the style of the buttons change by the following function:

```java 
    public void changeButtons(Button btClick, Button other){
        btClick.setEnabled(false);
        other.setEnabled(false);
        if(question_bank.get(position).getContestada().equals(Answered.CORRECT)){
            btClick.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.correct));
            other.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
        }else if(question_bank.get(position).getContestada().equals(Answered.INCORRECT)){

            btClick.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.incorrect));
            other.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
        }else{
            btClick.setEnabled(true);
            btClick.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
            other.setEnabled(true);
            other.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
        }
    }
```