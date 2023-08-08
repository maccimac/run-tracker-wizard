package com.example.runtrackerwizardv11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Program {

    public int id;
    public String name;
    public int level;
    public Round[] course;

    public Program(int id, String name, int level, Round[] course) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.course = course;
    }

    public  Program(){

    }

    public  Program(int _id){
        id=_id;

        switch(_id){
            case 1:


            case 2:
                name = "Pyramid Run";
                level = 2;
                course = new Round[]{
                        new Round(200, 1.5,  0.5),
                        new Round(400, 2, 1),
                        new Round(800, 3, 1),
                        new Round(400, 2, 0.5),
                        new Round(200, 1.5, 0),
                };
                break;

        }

    }


    class Round{
        public double runMeter;
        public int  restSec;
        public int runSec;

        public Round(double _run, double _runMin,  double _restMin ) {
            runMeter = _run;
            restSec = (int) (_restMin * 60);
            runSec = (int) (_runMin * 60);
    }

    public void getTotalKm(){

    }




    // 2: Pyramid run





}}
