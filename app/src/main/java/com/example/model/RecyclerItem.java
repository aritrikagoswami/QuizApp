package com.example.model;

public class RecyclerItem {
    String  quizTitle, selectedOption;

    public RecyclerItem(String quizTitle, String selectedOption) {
        this.quizTitle = quizTitle;
        this.selectedOption = selectedOption;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
