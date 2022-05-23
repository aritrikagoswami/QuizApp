package com.example.model;

import java.io.Serializable;

public class QuizOption implements Serializable {
  private int is_right;
  private int option_id;
  private String option_name;


    public int getIs_right() {
        return is_right;
    }

    public void setIs_right(int is_right) {
        this.is_right = is_right;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }


    @Override
    public String toString() {
        return "QuizOption{" +
                "is_right=" + is_right +
                ", option_id=" + option_id +
                ", option_name='" + option_name + '\'' +
                '}';
    }
}
