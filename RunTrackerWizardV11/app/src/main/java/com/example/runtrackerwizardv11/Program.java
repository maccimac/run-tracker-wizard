package com.example.runtrackerwizardv11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Program {

    public int id;
    public String name;
    public String level;
    public Round[] course;

//    public Program(int id, String name, int level, Round[] course) {
//        this.id = id;
//        this.name = name;
//        this.level = level;
//        this.course = course;
//    }

    public  Program(){

    }

    public  Program(int _id){
        id=_id;

        switch(_id){
            case 1:
                name = "Incremental Improvement Run";
                level = "Beginner";
                course = new Round[]{
                        new Round(100, 2,  0.5),
                        new Round(150, 2, 1),
                        new Round(200, 2, 1),
                        new Round(250, 3, 1),
                        new Round(300, 3, 0),
                };
                break;


            case 2:
                name = "Pyramid Run";
                level = "Intermediate";
                course = new Round[]{
                        new Round(200, 1.5,  0.5),
                        new Round(400, 2, 1),
                        new Round(800, 3, 1),
                        new Round(400, 2, 0.5),
                        new Round(200, 1.5, 0),
                };
                break;

            case 3:
                name = "Consistent Run";
                level = "Intermediate";
                course = new Round[]{
                        new Round(500, 2.5,  1.5),
                        new Round(350, 2.5,  1.5),
                        new Round(350, 2.5,  1.5),
                        new Round(350, 2.5,  1.5),
                        new Round(350, 2.5,  1.5),
                };
                break;

            case 4:
                name = "Taper Down Run";
                level = "Advanced";
                course = new Round[]{
                        new Round(1000, 10,  1.5),
                        new Round(600, 8,  2),
                        new Round(400, 4,  1.5),
                        new Round(400, 4,  2),
                        new Round(800, 10,  1.5),
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
