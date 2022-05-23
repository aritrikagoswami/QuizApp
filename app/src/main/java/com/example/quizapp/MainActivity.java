package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Quiz;
import com.example.model.QuizOption;
import com.example.model.ResponseModel;
import com.example.network.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    ResponseModel responseModel;
    TextView textBack, quizCount, questionTitle;
    TextView answeredCount, staticTitle;
    ImageView imageBack;
    MaterialButton buttonPrevious, buttonNext;
    ProgressBar determinateBar;
    RadioGroup radiogroupQuizOptions;

    private List<Quiz> quizList = null;
    int currentQuestion = 0;
    int questionAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            try {
                this.getSupportActionBar().hide();
            } catch (NullPointerException e) {
            }
        }
        setContentView(R.layout.activity_main);
        init();
        fetchQuizDetails();
    }

    private void fetchQuizDetails() {
        Call<ResponseModel> call = RetrofitClient.getInstance().getQuizApi().getAll();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    String toast = response.code() + " No Question Arrived ";
                    System.out.println("Not Successful");
                    Toast.makeText(MainActivity.this, toast, Toast.LENGTH_LONG).show();
                    return;
                }

                responseModel = response.body();
                List<HashMap<String, Quiz>> data = (List) responseModel.getData();
                quizList = new ArrayList<Quiz>();
                for (HashMap<String, Quiz> hashQuiz : data) {
                    quizList.add(hashQuiz.get("quiz"));
                }
                populateInitial();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("QUiz", "onFailure" + t.getMessage());
                System.out.println("onFailure" + t.getMessage());
                if (quizList == null || (quizList.size() == 0)) {
                    Toast.makeText(MainActivity.this, "No Questions found!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    public void init() {
        buttonPrevious = findViewById(R.id.previous_btn);
        buttonNext = findViewById(R.id.next_btn);
        textBack = findViewById(R.id.textBack);
        quizCount = findViewById(R.id.quizCount);
        questionTitle = findViewById(R.id.questionTitle);
        imageBack = findViewById(R.id.imageBack);
        determinateBar = findViewById(R.id.determinateBar);
        radiogroupQuizOptions = findViewById(R.id.radiogroupQuizOptions);
        answeredCount = findViewById(R.id.answeredCount);
        staticTitle = findViewById(R.id.staticTitle);
        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        textBack.setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_btn:
                onClickNext();
                break;
            case R.id.previous_btn:
                onClickPrevious();
                break;
            case R.id.imageBack:
                // onClickBack();
                break;
            case R.id.textBack:
                //  onClickBack();
                break;
            default:
                break;
        }
    }

    public void onClickNext() {
        buttonPrevious.setEnabled(true);
        if (currentQuestion == (quizList.size() - 1)) {
            onSubmit();

        } else {
            if (currentQuestion == (quizList.size() - 2)) {
                buttonNext.setText("Submit");
            }
            ++currentQuestion;
            populateForCurrentQuestion();
        }
    }

    public void onClickPrevious() {
        if (currentQuestion == 1) {
            buttonPrevious.setEnabled(false);
        } else if (currentQuestion == quizList.size() - 1) {
            buttonNext.setText("Next");
        }
        --currentQuestion;
        populateForCurrentQuestion();
    }

    public void onSubmit() {
        Intent intent = new Intent(MainActivity.this, PreviewAnswer.class);
        intent.putExtra("quizList", (ArrayList<Quiz>) quizList);
        startActivity(intent);
    }

    public void populateInitial() {
        currentQuestion = 0;
        buttonPrevious.setEnabled(false);
        populateForCurrentQuestion();
        progressSetUp();
    }

    public void populateForCurrentQuestion() {
        Quiz currentQuiz = quizList.get(currentQuestion);
        questionTitle.setText(currentQuiz.getQuiz_title());
        quizCount.setText(String.valueOf(currentQuestion + 1)+".");
        addRadioButtonsForQuizOptions(currentQuiz);
    }

    public void addRadioButtonsForQuizOptions(Quiz currentQuiz) {
        radiogroupQuizOptions.setOrientation(LinearLayout.VERTICAL);
        radiogroupQuizOptions.removeAllViews();
        for (QuizOption quizOption : currentQuiz.getQuizOptionList()) {
            RadioButton rdbtn = new RadioButton(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                rdbtn.setId(View.generateViewId());
            } else {
                rdbtn.setId((int) (Math.random() * 100000));
            }
            rdbtn.setText(quizOption.getOption_name());
            rdbtn.setTag(quizOption.getOption_id());
            rdbtn.setOnClickListener(v -> {
                if (currentQuiz.getSelected_option_id() == 0) {
                    questionAnswered++;
                }
                currentQuiz.setSelected_option_id((Integer) v.getTag());
                progressSetUp();
            });
            rdbtn.setChecked(currentQuiz.getSelected_option_id() == quizOption.getOption_id());
            radiogroupQuizOptions.addView(rdbtn);
        }
    }

    public void progressSetUp() {
        answeredCount.setText(questionAnswered + "/" + quizList.size());
        if (questionAnswered == quizList.size()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                determinateBar.setProgress(100, true);
            } else {
                determinateBar.setProgress(100);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                determinateBar.setProgress((questionAnswered) * (100 / 7), true);
            } else {
                determinateBar.setProgress(questionAnswered * (100 / 7));
            }
        }
    }
}