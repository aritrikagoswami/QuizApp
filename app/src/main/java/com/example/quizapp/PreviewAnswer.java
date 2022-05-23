package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.adapter.PreviewAdapter;
import com.example.model.Quiz;
import com.example.model.RecyclerItem;

import java.util.ArrayList;

public class PreviewAnswer extends AppCompatActivity {
    ArrayList<Quiz> quizList;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_answer);

        quizList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizList");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        rAdapter = new PreviewAdapter(quizList, PreviewAnswer.this);
        recyclerView.setAdapter(rAdapter);
        recyclerView.setLayoutManager(layoutManager);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
    }
}