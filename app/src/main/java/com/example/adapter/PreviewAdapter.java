package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Quiz;
import com.example.model.QuizOption;
import com.example.model.RecyclerItem;
import com.example.quizapp.R;

import java.util.ArrayList;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.PreviewHolder> {
    ArrayList<Quiz> quizArrayList;
    Context c;

    public PreviewAdapter(ArrayList<Quiz> quizArrayList, Context c) {
        this.quizArrayList = quizArrayList;
        this.c = c;
    }


    @NonNull
    @Override
    public PreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.custom_layout, parent, false);
        PreviewHolder previewHolder = new PreviewHolder(v);
        return previewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewHolder holder, int position) {
        Quiz qz = quizArrayList.get(position);
        holder.quizTitle.setText(position + 1 + ". " + qz.getQuiz_title());
        String selectedOption = null;
        System.out.println("666 " + qz.getQuizOptionList());
        System.out.println("666 " + qz.getSelected_option_id());
        for (QuizOption option : qz.getQuizOptionList()) {
            if (option.getOption_id() == qz.getSelected_option_id()) {
                selectedOption = option.getOption_name();
                break;
            }
        }
        if (selectedOption == null) {
            selectedOption = "No option selected";
        }
        holder.selectedOption.setText("Selected Option: " + selectedOption);

    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

    public class PreviewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout child;
        TextView quizTitle, selectedOption;

        public PreviewHolder(@NonNull View itemView) {
            super(itemView);
            quizTitle = (TextView) itemView.findViewById(R.id.QuizTitle);
            selectedOption = (TextView) itemView.findViewById(R.id.SelectedOption);
        }
    }
}
