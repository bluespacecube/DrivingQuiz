package com.example.drivingquiz;

public class Question {
    private String _question;
    private String _answer1;
    private String _answer2;
    private String _answer3;
    private String _answer4;
    private int _correctAnswer;

    public Question(String question,String answer1,String answer2,String answer3,String answer4,int correctAnswer){
        _question = question;
        _answer1 = answer1;
        _answer2 = answer2;
        _answer3 = answer3;
        _answer4 = answer4;
        _correctAnswer = correctAnswer;
    }

    public String getQuestionText(){
        return _question;
    }
    public String[] getQuestionAnswers(){
        return new String[]{_answer1, _answer2, _answer3, _answer4};
    }
    public int getQuestionAnswer(){
        return _correctAnswer;
    }
}
