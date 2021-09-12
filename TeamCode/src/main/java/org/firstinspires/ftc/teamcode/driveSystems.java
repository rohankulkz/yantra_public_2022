package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.List;

public class driveSystems {

    public driveSystems(){
    }

    public static double[] continousDriveWithCoords(double x_value, double y_value){
        /*
        * Method takes joystick input and returns 4 motor powers from -1 to 1 to create a continuous mechanum drive
        * Commit movement allows the system to either commit the movement and return data, or just return data -----DEPRECATED------
        * This method returns 4 motor powers for an all wheel drive
        * NOTE: Motor value may need to be made negative or positive and their order manipulated to fit your drive train
        *
        *
        *
        *
        * */


        double[] motor_powers = {0,0,0,0};  // {fl, fr, rl, rr}
        double theta = 0.0;
        double t[] = {  0, 0.25*Math.PI, 0.5*Math.PI, 0.75*Math.PI, Math.PI,
                                    1.25*Math.PI, 1.5*Math.PI, 1.75*Math.PI, 2.0*Math.PI};
        // d = direction, in this order = fl -> fr -> rl -> rr
        double d[][] = {    {-1, -1, 1, 1}, // E
                            {-1, 0, 0, 1}, // NE
                            {-1, 1, -1, 1}, // N
                            {0, 1, -1, 0}, // NW
                           {1, 1, -1, -1}, // W
                            {1, 0, 0, -1}, // SW
                            {1, -1, 1, -1}, // S
                            {0, -1, 1, 0}, // SE
                            {-1, -1, 1, 1}}; // E
        int fl = 0, fr = 1, rl = 2, rr = 3;
        double mag = Math.sqrt(x_value*x_value + y_value*y_value);
        int direction = 0;
        theta = Math.atan2(y_value, x_value);

        if (theta < 0.0)
        {
          theta = theta + 2*Math.PI;
        }

        int start = ((int) (theta/(0.25*Math.PI))) % 8;
        int end = start + 1;
        for (int i = 0; i < 4; i++)
             {
              motor_powers[i] = mag*(d[start][i] + ((theta - t[start])/(t[end] - t[start]))*(d[end][i] - d[start][i]));
             }


        return motor_powers;
    }
    public static double[] turnDrive(double x_turn_value){
        
        /*
        This method takes an x value of a joystick and converts it into 4 motor powers for a quick and smooth rotation.
        This method is recommended for teleOp as it takes a coordiate.
        
        
        */
        
     double[] motorValues = {x_turn_value,x_turn_value,x_turn_value,x_turn_value};

     return motorValues;
    }

    public static double[] turnDriveWithAngle(double angle_of_turn, double speed_of_turn){

        /*
        * This method takes an angle, and a speed, and returns the powers for the motors
        * Method is optimal for autonomous code
        * NOTE: Positive angle for clockwise rotation and negative angle for counterclockwise rotation (this may change with different motor configurations)
        *
        *
        *
        * */



        double[] motorValues = {0,0,0,0};

        if (angle_of_turn > 0){
            for (int i = 0; i < 4; i++) {
                motorValues[i] = 1 * Math.abs(speed_of_turn);
            }
        }
        else if(angle_of_turn < 0) {
            for (int i = 0; i < 4; i++) {
                motorValues[i] = -1 * Math.abs(speed_of_turn);
            }
        }
        




        return motorValues;
    }

    public static double[] continousDriveWithAngle(double theta, double speed){
        /*
         * Method takes an angle and returns 4 motor powers from -1 to 1 to create a continuous mechanum drive
         * Commit movement allows the system to either commit the movement and return data, or just return data -----DEPRECATED------
         * This method returns 4 motor powers for an all wheel drive
         * NOTE: Motor value may need to be made negative or positive and their order manipulated to fit your drive train
         * NOTE: This method is optimal for autonomous code as it takes an angle as an input
         *
         *
         *
         * */


        double[] motor_powers = {0,0,0,0};  // {fl, fr, rl, rr}

        double t[] = {  0, 0.25*Math.PI, 0.5*Math.PI, 0.75*Math.PI, Math.PI,
                1.25*Math.PI, 1.5*Math.PI, 1.75*Math.PI, 2.0*Math.PI};
        // d = direction, in this order = fl -> fr -> rl -> rr
        double d[][] = {    {-1, -1, 1, 1}, // E
                {-1, 0, 0, 1}, // NE
                {-1, 1, -1, 1}, // N
                {0, 1, -1, 0}, // NW
                {1, 1, -1, -1}, // W
                {1, 0, 0, -1}, // SW
                {1, -1, 1, -1}, // S
                {0, -1, 1, 0}, // SE
                {-1, -1, 1, 1}}; // E
        int fl = 0, fr = 1, rl = 2, rr = 3;

        int direction = 0;


        if (theta < 0.0)
        {
            theta = theta + 2*Math.PI;
        }

        int start = ((int) (theta/(0.25*Math.PI))) % 8;
        int end = start + 1;
        for (int i = 0; i < 4; i++)
        {
            motor_powers[i] = speed*(d[start][i] + ((theta - t[start])/(t[end] - t[start]))*(d[end][i] - d[start][i]));
        }


        return motor_powers;
    }

    public static List<double[]> auto_nav(double travel_angle, double speed, double distance, double turn_angle){

        //This is a test program to see how exactly one would run a turn strafe and break up the commands.

        List<double[]> motor_commands = new ArrayList<>();

        double[] travel_motor = continousDriveWithAngle(travel_angle, speed);

        double[] turn_motor = turnDriveWithAngle(turn_angle, speed);


        //

        double travel_time = distance/speed;

        int num_of_steps = (int)travel_time;

        for (int i = 0; i < num_of_steps*2; i++) {
            if(i%2 == 0){
                motor_commands.add(travel_motor);
            }
            else {
                motor_commands.add(turn_motor);
            }
        }


        return motor_commands;


    }

}
