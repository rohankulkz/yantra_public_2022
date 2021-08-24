// early version of coordinate system
// uses encoders from other class
// very early stage and primitive code
// this is a simply a concept of what the actual code could be like
// prolly gonna be complete shit but we may implement it somehow who knows









package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import Encoder_Drive_System;

// double newfrontleftdTarget = robot.leftDrive.getCurrentPosition();
//                double newfrontrightdTarget = robot.rightDrive.getCurrentPosition();
//                double newrearleftdTarget = robot.rightDrive.getCurrentPosition();
//                double newrearrightdTarget = robot.rightDrive.getCurrentPosition();

@Autonomous(name="CoordinateSys")
@Disabled
public class CoordinateSys extends LinearOpMode {

        Encoder_Drive_System mydrive = new Encoder_Drive_System();


        static void TargetCoords(double x_target, double y_target){

                // This code uses a simple coordinate field I drew(check discord for reference)
                //For now, I'm j assuming the robot's current position is (0,0), we will get the position through the encoders
                //method get currentposition

                double current_x=0.0;
                double current_y=0.0;
                // referring to my totally amazing picture on discord, we are assuming the robot's forward side is facing directly the oppposite way of the audience



                // a function to take the current encoder values to give us the current position will eventually replace these two variables



                double[] currentcoordinates = {current_x,current_y};
                // distance formula (y2-y1/x2-x1) done with seperate variables
                double y_distance = y_target-current_y;
                double x_distance = x_target-current_x;

                // drive to target
                // will also turn eventually, once we know how to use the gyrscope
                // for now forwards/backwards, left/right

                // up/down

                mydrive.EncoderDrive(0.7,y_distance);

                // left/right

                if (x_distance>0){
                        // code to turn right with gyroscope
                        mydrive.EncoderDrive(0.7,x_distance);

                }else {
                        //code to turn left with gyroscope
                        mydrive.EncoderDrive(0.7,x_distance);
                }























        }







//        @Override
//        public void runOpMode() {
//
//
//
//
//
//
//        }
}
