package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
 * This is a test opmode
 * Runs drive motors infinetly
 * Meant for changing motor directions to put correct configurations in roadrunner
 */

@TeleOp(name="OdometryDirectionTest", group="Drive")
//@Disabled
public class OdometryDirectionTest extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;



    @Override
    public void runOpMode(){
        //Pending Motor names
        //Motor hardware map
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontleftd");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontrightd");
        backLeft = hardwareMap.get(DcMotorEx.class, "rearleftd");
        backRight = hardwareMap.get(DcMotorEx.class, "rearrightd");

        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {
            // test encoder directions to ensure all odometers return correct values



        }
    }



}
