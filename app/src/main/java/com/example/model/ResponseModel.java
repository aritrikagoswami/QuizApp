package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseModel {
    private List<HashMap<String,Quiz>> data ;
    private Boolean error;
    private String message;

    public List<HashMap<String, Quiz>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, Quiz>> data) {
        this.data = data;
    }

    /*
        public List getData() {
            return this.data;
        }

        public void setData(List data) {
            this.data = data;
        }


     */
    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


