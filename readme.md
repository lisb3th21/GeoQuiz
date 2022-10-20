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