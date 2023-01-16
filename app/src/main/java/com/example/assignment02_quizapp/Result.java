package com.example.assignment02_quizapp;

public class Result{
    private String Question;
    private String Answer;
    private String SubmittedAnswer;
    private String Status;

    public String getAnswer() {
        return Answer;
    }

    public Result( String question,String answer, String submittedAnswer, String status) {
        this.Answer = answer;
        this.SubmittedAnswer = submittedAnswer;
        this.Status = status;
        this.Question = question;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public void setAnswer(String answer) {
        this.Answer = answer;
    }

    public String getSubmittedAnswer() {
        return SubmittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.SubmittedAnswer = submittedAnswer;
    }

    public Result() {
    }
}
