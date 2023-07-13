package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.runtrackerwizardv11.databinding.ActivityRunBinding;

public class RunActivity extends AppCompatActivity {
    static ActivityRunBinding binding;
    Program program;
    static TextView textTimerMain;
    static TextView labelMain;
    Countdown currCountDown;
    int currentRunMeter;
    int currentRestSecond;
    boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRunBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        textTimerMain = binding.textCount;
        labelMain = binding.textRunOrRest;
        program = new Program(2);
        startRound(program.course[0]);
    }

    public void goToDistanceTracker(View view) {
        startActivity( new Intent(this.getApplicationContext(), DistanceTrackingActivity.class)
                );
    }

    public static class TimerHelperCallback{
        public TimerHelperCallback(){

        }
        public static void testCb(){
            binding.btnEnd.setText("DONE");
        }

        public static void declareRest(){
            binding.textRunOrRest.setText("Rest");
        }

        public static void declareRun(){
            binding.textRunOrRest.setText("Run");
        }


    }

    public void startRound(Program.Round r){
        TimerHelperCallback xyz = new TimerHelperCallback();
        for (int currRound = 0; currRound < program.course.length; currRound++) {
            new Countdown(r.runMeter/10, "Run", currRound, xyz).execute();
            new Countdown(r.restSec, "Rest", currRound, xyz).execute();
        }
    }


    public void timerStart(){

        AsyncTask fiveSec = new Countdown(10).execute();
    }


    public class Countdown extends AsyncTask<String, Integer, String> implements com.example.runtrackerwizardv11.Countdown {

        int seconds = 0;
        int currRound = 0;
        String label;
        TimerHelperCallback callback;

        public Countdown(int _seconds) {
            seconds = _seconds;
        }
//
        public Countdown(int _seconds, String _label , int _currRound, TimerHelperCallback _callback) {
            currRound = _currRound;
            seconds = _seconds;
            callback = _callback;
            label = _label;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();super.onPreExecute();
            if(label != null){
                labelMain.setText(label);
            }
        }

        @Override
        protected void onPostExecute(String result) {
//            super.onPostExecute(s);
            if(callback != null){
                TimerHelperCallback.testCb();
            }
//            myTextView.setText(result);
        }

        @Override
        public void onProgressUpdate(int i) {
//            super.onProgressUpdate(values);
            textTimerMain.setText("" + i);
        }

        @Override
        protected String doInBackground(String... strings) {
//            currCountDown = this;
            binding.textRoundNo.setText( "Round " + (currRound+1) +" of " + (program.course.length + 1));
            if(label != null){
                labelMain.setText(label);
            }
            int i = 0;
            while( i <= seconds){
                onProgressUpdate(seconds -i);
//                publishProgress(i);
                try{
                    Thread.sleep(1000);
                    i++;
                }catch(Exception e){

                }
            }

            return "Done";
        }
    }
}