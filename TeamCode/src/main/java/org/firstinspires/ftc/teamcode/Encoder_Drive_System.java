/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

// This is a heavy modification of the encoder drive tempalte provided. This version will be much cleaner, and have methods that can be
// used in other classes effectively and efficiently

@Autonomous(name="Encoder_Drive_System")
public class Encoder_Drive_System extends LinearOpMode{


        * Declare OpMode members. */
        Hardware robot   = new Hardware();   // Use a Pushbot's hardware
        public ElapsedTime     runtime = new ElapsedTime();

        static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        static final double     DRIVE_SPEED             = 0.6;
        static final double     TURN_SPEED              = 0.5;


        public void ResetEncoders(){
                robot.frontleftd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontrightd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rearleftd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rearrightd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void CurrentPos(){
                double newfrontleftdTarget = robot.leftDrive.getCurrentPosition();
                double newfrontrightdTarget = robot.rightDrive.getCurrentPosition();
                double newrearleftdTarget = robot.rightDrive.getCurrentPosition();
                double newrearrightdTarget = robot.rightDrive.getCurrentPosition();

        }
        public void EncoderDrive(double speed, double inches){

                // init is a method you can find in the Hardware file. Activates motors and servos, puts them in correct settings for usage


                int newfrontleftdTarget;
                int newfrontrightdTarget;
                int newrearleftdTarget;
                int newrearrightdTarget;

                //Calculate Targets
                newfrontleftdTarget = robot.leftDrive.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newfrontrightdTarget = robot.rightDrive.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newrearleftdTarget = robot.rightDrive.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newrearrightdTarget = robot.rightDrive.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                //Set Targets
                robot.frontleftd.setTargetPosition(newfrontleftdTarget);
                robot.frontrightd.setTargetPosition(newfrontrightdTarget);
                robot.rearleftdTarget.setTargetPosition(newrearleftdTarget);
                robot.rearrightdTarget.setTargetPosition(newrearrightdTargetTarget);


                // Set encoders for run to position mode
                robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();
                robot.leftDrive.setPower(Math.abs(speed));
                robot.rightDrive.setPower(Math.abs(speed));

                // loop to run motors now that motors are in correct settings and targets have been identified
                while(robot.frontleftd.isBusy() || robot.frontrightd.isBusy() || robot.rearrleft.isBusy() || robot.rearright.isBusy()){
                        // Display it for the driver.
                        telemetry.addData("Path1",  "Running to %7d :%7d  %7d: %7d", newfrontleftdTarget,  newfrontrightdTarget, newrearleftdTarget, newrearrightdTarget);
                        telemetry.addData("Path2",  "Running at %7d :%7d  %7d: %7d", robot.frontlefd.getCurrentPosition(), robot.frontrightd.getCurrentPosition(), robot.rearleftd.getCurrentPosition(), robot.rearrightd.getCurrentPosition())
                        telemetry.update();
                }

                //disable motors
                robot.frontleftd.setPower(0);
                robot.frontrightd.setPower(0);
                robot.rearleftd.setPower(0);
                robot.rearrightd.setPower(0);

                //turn off run to position
                robot.frontleftd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontrightd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rearleftd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rearrightd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                }

                }











