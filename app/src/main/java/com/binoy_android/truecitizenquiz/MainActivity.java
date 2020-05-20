package com.binoy_android.truecitizenquiz;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private TextView questionTextView;

    private int currentQuestionIndex = 0;

    private Question [] questionsBank = new Question[]{

            new Question(R.string.question_Ishaan, true),
            new Question(R.string.question_Daddy,true),
            new Question(R.string.Capital_of_England,true),
            new Question(R.string.Comparative_adjective,true),
            new Question(R.string.question_Maths,false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);

        questionTextView = findViewById(R.id.answer_text_view);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.false_button:
              //  Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                break;
            case R.id.true_button:
                //Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                break;
            case R.id.next_button:
                // go to next questions
                // currentQuestionIndex ++ ; // incrementing by 1

                currentQuestionIndex = (currentQuestionIndex +1) % questionsBank.length;
                updateQuestion();
        }
    }
    private void updateQuestion (){
        // lets testing on our log d
        // Log.d("Current", "onClick: " +currentQuestionIndex);
        questionTextView.setText(questionsBank[currentQuestionIndex].getAnswerResId());
    }
    private void checkAnswer (boolean userChooseCorrect){
        boolean answerTrue = questionsBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId;

        if (userChooseCorrect == answerTrue){
            toastMessageId = R.string.correct_answer;
        }else {
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
