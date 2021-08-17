package org.firstinspires.ftc.teamcode;


public class test {

    public static void main(String[] args) {


        double[] values = driveSystems.continousDriveWithCoords(-0.4,0.6);

        for (int i = 0; i < 4; i++) {
            System.out.println("Section "+i+": "+values[i]);
        }

    }


}
