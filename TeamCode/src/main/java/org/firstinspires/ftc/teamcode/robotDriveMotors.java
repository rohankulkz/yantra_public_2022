package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class robotDriveMotors implements Runnable {

    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;

    private double[] motorPowers;

    public robotDriveMotors(String motor1, String motor2, String motor3, String motor4){
        this.motor1 = hardwareMap.get(DcMotor.class, motor1);
        this.motor2 = hardwareMap.get(DcMotor.class, motor2);
        this.motor3 = hardwareMap.get(DcMotor.class, motor3);
        this.motor4 = hardwareMap.get(DcMotor.class, motor4);
        Thread motorThread = new Thread();
        motorThread.start();
    }

    public void setMotorPowers(double[] motorPowers){
        this.motorPowers=motorPowers;
    }


    @Override
    public void run() {
        while (1==1) {
            motor1.setPower(motorPowers[0]);
            motor2.setPower(motorPowers[1]);
            motor3.setPower(motorPowers[2]);
            motor4.setPower(motorPowers[3]);
        }
    }
}
