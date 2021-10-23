package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.driveSystems;


/*
* This is drive train test opmode.
*
*
* It implments mechanum drive and other features
*
*
*
* */

@TeleOp(name="Drive-Train", group="Drive")
//@Disabled
public class testOpMode extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private double[] motorValues = {0,0,0,0};


    @Override
    public void runOpMode(){
        //Pending Motor names
        //Motor hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontleftd");
        frontRight = hardwareMap.get(DcMotor.class, "frontrightd");
        backLeft = hardwareMap.get(DcMotor.class, "rearleftd");
        backRight = hardwareMap.get(DcMotor.class, "rearrightd");

        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {
            //Assigns the turing movement
            double turn = gamepad1.right_stick_x;

            motorValues = driveSystems.turnDrive(turn);

            //Note: These movements override the turning as they cannot go at the same time yet
            //Assigns the strafing movement
            double x = -gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;

            motorValues = driveSystems.continousDriveWithCoords(x,y);

            if(x == 0 && y ==0){
                motorValues = driveSystems.turnDrive(gamepad1.right_stick_x);
            }



            //Run the Motors
            frontLeft.setPower(motorValues[0]);
            frontRight.setPower(motorValues[1]);
            backLeft.setPower(motorValues[2]);
            backRight.setPower(motorValues[3]);

        }
    }



}
