package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.driveSystems;


/*
 * This is a test opmode
 * Runs drive motors infinetly
 * Meant for changing motor directions to put correct configurations in roadrunner
 */

@TeleOp(name="MotorPowerTest", group="Drive")
//@Disabled
public class MotorPowerTest extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;



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
            //change to ensure robot drives straight in a forwards direction
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.FORWARD);

            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0.5);

        }
    }



}
