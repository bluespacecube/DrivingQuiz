package com.example.drivingquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private int[] userAnswers;
    private int currentQuestion= 1;
    private ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //Get question amount from previous activity
        Intent intent = getIntent();
        int questionAmount = intent.getIntExtra(MainActivity.QUESTION_AMOUNT_EXTRA,10);
        //Create questions
        questionList = getQuestions(questionAmount);
        userAnswers = new int[questionAmount];
        //Start quiz
        setQuestion(questionList.get(0),0);
    }

    //Get user answer and save ut userAnswers array and show next question
    public void showNextQuestion(View view){
        RadioGroup rg = findViewById(R.id.answerChoicesRG);
        //Save user answer
        switch (rg.getCheckedRadioButtonId()){
            case R.id.answerChoice1RB:
                userAnswers[currentQuestion-1]=1;
                break;
            case R.id.answerChoice2RB:
                userAnswers[currentQuestion-1]=2;
                break;
            case R.id.answerChoice3RB:
                userAnswers[currentQuestion-1]=3;
                break;
            case R.id.answerChoice4RB:
                userAnswers[currentQuestion-1]=4;
                break;
        }
        //Check if any more questions
        if(currentQuestion != questionList.size()){
            //Increment currentQuestion int and show next question
            currentQuestion = currentQuestion+1;
            setQuestion(questionList.get(currentQuestion-1),userAnswers[currentQuestion-1]);
        }
        //There is no more questions
        else{
            //Show alert asking for confirmation of finish
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Finish Quiz?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    markQuestions();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }

    public void showPreviousQuestion(View view){
        if(currentQuestion != 1){
            //Decrement currentQuestion int and show previous question with the users answer
            currentQuestion=currentQuestion-1;
            setQuestion(questionList.get(currentQuestion-1),userAnswers[currentQuestion-1]);
        }
    }

    //Create list of random questions
    public ArrayList<Question> getQuestions(int amountOfQuestions){
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("You're turning left into a side road. What hazard should you be especially aware of:","One way street","Pedestrians","Traffic congestion","Parked vehicles",2));
        questions.add(new Question("In which circumstances may you use hazard warning lights","When driving on a motorway to warn traffic behind of a hazard ahead","When your double-parked on a two way road","When your direction indicators aren't working","When warning oncoming traffic that you intent to stop",1));
        questions.add(new Question("After passing your driving test, you suffer from ill health. This affects your driving. What must you do","Inform your local police","Avoid using motorways","Always drive accompanied","Inform the licencing authority",4));
        questions.add(new Question("When driving a car fitted with automatic transmission, what would you use the 'kick down' for?","Cruise control","Quick acceleration","Slow braking","Fuel economy",2));
        questions.add(new Question("What can seriously affect your ability to concentrate","Drugs","Busy roads","Tinted windows","Contact lenses",1));
        questions.add(new Question("You find that your eyesight has become very poor and your optician cannot help you. By law, who should you tell?","The driver licensing authority","Your own doctor","The local police","Another optician",1));
        questions.add(new Question("When are you allowed to wait in a box junction?","Never","When you wish to turn right but are prevented from doing so by oncoming traffic","When you are at a zebra crossing","When you are stuck in a queue of traffic",2));
        questions.add(new Question("You are driving towards traffic lights that have been on green for a while. What should you do?","Brake hard so that you can be sure of stopping if they do change to red","Be prepared to stop","Speed up so that they can get through then before they change","Stay at the same speed",2));
        questions.add(new Question("Following too closely behind a large vehicle is not a good idea because","Your upholstery could be damaged","The life of your battery could get shorter","The length of your journey will increase","You might need to change your speed drastically",4));
        questions.add(new Question("When you are driving towards an unmarked crossroads, how will you know if you have right of way?","If you are driving on the newest-looking road","If you have the largest vehicle","If your driving faster than everyone else","No way to know for certain",4));
        questions.add(new Question("At a puffin crossing, which color follows the green signal?","Steady red","Flashing amber","Steady amber","Flashing green",3));//q10
        questions.add(new Question("At which type of crossing are cyclists allowed to ride across with pedestrians?","Toucan","Puffin","Pelican","Zebra",1));
        questions.add(new Question("On a road where trams operate which of these vehicles will me most at risk from the tram rails", "Cars", "Cycles", "Buses", "Lorries", 2));
        questions.add(new Question("You're following two cyclists. They approach a roundabout in the left-hand lane. In which direction should you expect the cyclists to go?", "Left", "Right", "Any direction", "Straight ahead", 3));
        questions.add(new Question("You see a horse rider as you approach a roundabout. What should you do if they're signalling right but keeping well to the left?", "Proceed as normal", "Keep close to them", "Cut in front of them", "Stay well back", 4));
        questions.add(new Question("You're on a country road. What should you expect to see coming towards you on your side of the road?", "Motorcycles", "Bicyles", "Pedestrians", "Horse riders", 3));
        questions.add(new Question("A horse rider is in the left-hand lane approaching a roundabout. WHere should you expect the rider to go?", "In any direction", "To the right", "To the left", "Straight ahead", 1));
        questions.add(new Question("Where should you never overtakle a cyclist?", "Just before you turn left", "On a left hand-band", "On a one-way street", "On a dual carriageway", 1));
        questions.add(new Question("Who may use toucan crossings?", "Motorcyclits and cyclists", "Motorcyclists and pedestrians", "Only cyclists", "Cyclists and pedestrians", 4));
        questions.add(new Question("What should you do when passing a sheep on a road?", "Briefly sound your horn", "Go very slowly", "Pass quickly but quietly", "Herd them to the side of the road", 2));
        questions.add(new Question("You want to reverse into a side road, but you aren't sure that the area behind your car is clear. What should you do?", "Look through the read window only", "Get out and check", "Check the mirrors only", "Carry on, assuming it's clear", 2));
        questions.add(new Question("Who's especially in danger of not being seen as you reverse your car?", "Motorcyclists", "Car drivers", "Cyclists", "Children", 4));
        Collections.shuffle(questions);
        while(questions.size()> amountOfQuestions){
            questions.remove(questions.size() - 1);
        }
        return questions;
    }

    //Change the displayed question
    public void setQuestion(Question question, int answerPos){
        TextView questionLabelTV = findViewById(R.id.questionLabelTextView);
        questionLabelTV.setText("Question "+currentQuestion);
        View questionView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.question_view, null);
        TextView qTV = questionView.findViewById(R.id.questionTV);
        RadioButton answer1RB = (RadioButton) questionView.findViewById(R.id.answerChoice1RB);
        RadioButton answer2RB = (RadioButton) questionView.findViewById(R.id.answerChoice2RB);
        RadioButton answer3RB = (RadioButton) questionView.findViewById(R.id.answerChoice3RB);
        RadioButton answer4RB = (RadioButton) questionView.findViewById(R.id.answerChoice4RB);
        qTV.setText(question.getQuestionText());
        answer1RB.setText(question.getQuestionAnswers()[0]);
        answer2RB.setText(question.getQuestionAnswers()[1]);
        answer3RB.setText(question.getQuestionAnswers()[2]);
        answer4RB.setText(question.getQuestionAnswers()[3]);
        RadioGroup rg = questionView.findViewById(R.id.answerChoicesRG);
        switch (answerPos){
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
        CardView qfl = findViewById(R.id.questionCL);
        qfl.removeAllViews();
        qfl.addView(questionView);
    }

    //Mark questions and show finish screen
    public void markQuestions(){
        TextView questionLabelTV = findViewById(R.id.questionLabelTextView);
        questionLabelTV.setText("Results");
        TextView ratv = findViewById(R.id.reviewAnswersTV);
        ratv.setVisibility(View.VISIBLE);
        Button nxtBtn = findViewById(R.id.backButton);
        Button backBtn = findViewById(R.id.nextButton);
        nxtBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        int score =0;
        for(int i = 0; i< questionList.size();i++){
            System.out.println("MQ:"+(i+1)+" UANS:"+userAnswers[i]+" ANS:"+questionList.get(i).getQuestionAnswer());
            if (questionList.get(i).getQuestionAnswer() == userAnswers[i]) {
                score++;
            }
        }
        View scoreView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_view, null);
        TextView scoreTV = scoreView.findViewById(R.id.scoreTV);
        scoreTV.setText(score+"/"+questionList.size());
        CardView fl =findViewById(R.id.questionCL);
        RecyclerView rc = findViewById(R.id.questionAnswersRV);
        rc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rc.setAdapter(new RecyclerViewAdapter(questionList,userAnswers));
        fl.removeAllViews();
        fl.addView(scoreView);
    }
}