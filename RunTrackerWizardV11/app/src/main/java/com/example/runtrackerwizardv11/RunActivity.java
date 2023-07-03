package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.runtrackerwizardv11.databinding.ActivityRunBinding;

public class RunActivity extends AppCompatActivity {
    static ActivityRunBinding binding;
    Program program;
    static TextView myTextView;
    int currRound;
    int currentRunMeter;
    int currentRestSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRunBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        myTextView = binding.textCount;
//        setContentView(R.layout.activity_run);
        program = new Program(2);

        timerStart();

//        myTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    public static class CB{
        public CB(){

        }
        public static void testCb(){
            binding.btnPause.setText("DONE");
        }
    }




    public void timerStart(){
        CB xyz = new CB();
        AsyncTask fiveSec = new Countdown(10).execute();

    }

    public class Countdown extends AsyncTask<String, Integer, String> implements com.example.runtrackerwizardv11.Countdown {

        int seconds = 0;
        CB callback;

        public Countdown(int _seconds) {
            seconds = _seconds;
        }
//
//        public Countdown(int _seconds, CB _callback) {
//            seconds = _seconds;
//            callback = _callback;
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
//            super.onPostExecute(s);
            myTextView.setText(result);
        }

        @Override
        public void onProgressUpdate(int i) {
//            super.onProgressUpdate(values);
            myTextView.setText("" + i);
        }

        @Override
        protected String doInBackground(String... strings) {
//            return null;
            int i = 0;
            while( i <= seconds){

                onProgressUpdate(seconds-i);
//                publishProgress(i);
                try{
                    Thread.sleep(1000);
                    i++;
                }catch(Exception e){

                }
            }

//            if(callback != null){
//                CB.testCb();
//            }

            return "Done";
        }
    }
}