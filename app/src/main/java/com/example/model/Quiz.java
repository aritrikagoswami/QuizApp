package com.example.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
   private String media_url;
   private String no_of_correct_choice;
   private String quiz_id;
   @SerializedName("quiz_options")
   private List<QuizOption> quizOptionList;
   private String quiz_pass_marks;
   private String quiz_title;
   private String quiz_type;
   private String related_training_id;
   private String related_training_title;

   private int selected_option_id=0;

    public int getSelected_option_id() {
        return selected_option_id;
    }

    public void setSelected_option_id(int selected_option_id) {
        this.selected_option_id = selected_option_id;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getNo_of_correct_choice() {
        return no_of_correct_choice;
    }

    public void setNo_of_correct_choice(String no_of_correct_choice) {
        this.no_of_correct_choice = no_of_correct_choice;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public List<QuizOption> getQuizOptionList() {
        return quizOptionList;
    }

    public void setQuizOptionList(List<QuizOption> quizOptionList) {
        this.quizOptionList = quizOptionList;
    }




    public String getQuiz_pass_marks() {
        return quiz_pass_marks;
    }

    public void setQuiz_pass_marks(String quiz_pass_marks) {
        this.quiz_pass_marks = quiz_pass_marks;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    public String getRelated_training_id() {
        return related_training_id;
    }

    public void setRelated_training_id(String related_training_id) {
        this.related_training_id = related_training_id;
    }

    public String getRelated_training_title() {
        return related_training_title;
    }

    public void setRelated_training_title(String related_training_title) {
        this.related_training_title = related_training_title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "media_url='" + media_url + '\'' +
                ", no_of_correct_choice='" + no_of_correct_choice + '\'' +
                ", quiz_id='" + quiz_id + '\'' +
                ", quizOptionList=" + quizOptionList +
                ", quiz_pass_marks='" + quiz_pass_marks + '\'' +
                ", quiz_title='" + quiz_title + '\'' +
                ", quiz_type='" + quiz_type + '\'' +
                ", related_training_id='" + related_training_id + '\'' +
                ", related_training_title='" + related_training_title + '\'' +
                '}';
    }
}
