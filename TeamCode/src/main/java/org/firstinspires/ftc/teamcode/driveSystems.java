package org.firstinspires.ftc.teamcode;


public class driveSystems {

    public driveSystems(){
    }

    public static double[] continousDrive(double x_value, double y_value){
        // Arjun's test commit using android studio!!
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
}
