package com.example.drivingquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<Question> mDataset;
    private int[] userAnswers;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder (View v){
            super(v);
            view = v;
        }
        public void setInvisible(){
            view.setVisibility(View.INVISIBLE);
        }
        //Binds question data and user answer to the correct answer card view
        public void bind(int questionNumber, Question question, int userAnswer){
            TextView questionLabelTV = view.findViewById(R.id.questionLabelTV);
            TextView questionTV = view.findViewById(R.id.questionTV);
            TextView correctAnwerTV = view.findViewById(R.id.correctAnswerTV);
            RadioButton answer1RB = (RadioButton) view.findViewById(R.id.answerChoice1RB);
            RadioButton answer2RB = (RadioButton) view.findViewById(R.id.answerChoice2RB);
            RadioButton answer3RB = (RadioButton) view.findViewById(R.id.answerChoice3RB);
            RadioButton answer4RB = (RadioButton) view.findViewById(R.id.answerChoice4RB);
            answer1RB.setText(question.getQuestionAnswers()[0]);
            answer2RB.setText(question.getQuestionAnswers()[1]);
            answer3RB.setText(question.getQuestionAnswers()[2]);
            answer4RB.setText(question.getQuestionAnswers()[3]);
            RadioGroup rg = view.findViewById(R.id.answerChoicesRG);
            switch (userAnswer){
                case 1:
                    rg.check(R.id.answerChoice1RB);
                    break;
                case 2:
                    rg.check(R.id.answerChoice2RB);
                    break;
                case 3:
                    rg.check(R.id.answerChoice3RB);
                    break;
                case 4:
                    rg.check(R.id.answerChoice4RB);
                    break;
            }
            questionLabelTV.setText("Question "+questionNumber);
            questionTV.setText(question.getQuestionText());
            correctAnwerTV.setText("Correct Answer:"+ question.getQuestionAnswers()[question.getQuestionAnswer()-1]);
        }
    }
    public RecyclerViewAdapter(ArrayList<Question> questionList,int[] userAnswerList){
        mDataset = questionList;
        mDataset.add(new Question("","","","","",0));
        userAnswers = userAnswerList;
    }
    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_answer_view,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position == mDataset.size() - 1){
            holder.setInvisible();
        }else {
            holder.bind(position + 1, mDataset.get(position), userAnswers[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
