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
    static TextView textTimerMain, textDistMain;
    static TextView locationMain, labelMain;
    Countdown currCountDown;
    int currentRunMeter;
    int currentRestSecond;
    boolean stop = false;
    boolean isPaused = false;
    boolean forceNext = false;
    boolean forceEnd = false;
    LocationHelper lh;
    int progId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRunBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();

        progId = extras.getInt("programId");

        textTimerMain = binding.textCount;
        textDistMain = binding.textDistance;
        labelMain = binding.textRunOrRest;
        locationMain = binding.textAddress;
        program = new Program(progId);

        binding.textView3.setText(program.name);
        binding.textView.setText(program.level);

        TimerHelperCallback timerCb = new TimerHelperCallback();


        lh = new LocationHelper( RunActivity.this, locationMain, textDistMain, 0.0);
        for (int currRound = 0; currRound < program.course.length; currRound++) {
            if(!forceEnd){
                Program.Round r = program.course[currRound];
                new Countdown(r.runSec, "Run", currRound, timerCb, r.runMeter).execute();
                new Countdown(r.restSec, "Rest", currRound, timerCb, 0).execute();
            }

        }

        binding.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = !isPaused;
                if(isPaused){
                    binding.btnPause.setText("Resume Timer");
                }else{
                    binding.btnPause.setText("Pause Timer");
                }

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forceNext = true;
            }
        });

        binding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forceEnd = true;
                startActivity( new Intent(RunActivity.this,  DoneActivity.class));
            }
        });
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

    public void timerStart(){
        AsyncTask fiveSec = new Countdown(10).execute();
    }

    public class Countdown extends AsyncTask<String, Integer, String> implements com.example.runtrackerwizardv11.Countdown {

        int seconds = 0;
        int currRound = 0;
        double runMeter = 0;
        String label;
        TimerHelperCallback callback;

        public Countdown(int _seconds) {
            seconds = _seconds;
        }
//
        public Countdown(int _seconds, String _label , int _currRound, TimerHelperCallback _callback,  double _runMeter) {
            currRound = _currRound;
            seconds = _seconds;
            callback = _callback;
            label = _label;
            runMeter = _runMeter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();super.onPreExecute();
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
            textTimerMain.setText("" + i);
        }

        public void end() {
            this.cancel(true);
        }


        @Override
        protected String doInBackground(String... strings) {
            binding.textRoundNo.setText( "Round " + (currRound+1) +" of " + (program.course.length + 1));
            boolean kmMet = false;

            if(label != null){
                labelMain.setText(label);
                if(label=="Rest"){
                    textDistMain.setText("");
                }else{
                    lh.restart(runMeter);
                }
            }

            int i = 0;

            while( i <= seconds && !forceEnd){
                if(forceNext){
                    i=seconds;
                    forceNext = false;
                }
                if(!isPaused){
                    onProgressUpdate(seconds -i);
                    try{
                        Thread.sleep(1000);
                        i++;
                    }catch(Exception e){

                    }
                }else{

                }
            }

            return "Done";
        }
    }
}