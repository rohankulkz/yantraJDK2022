package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//implements roadrunner's mecanum drive class with robot's systems
@TeleOp(name="MecanumV2", group="Drive")
public class MecanumV2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor intake = null;
    private Servo leftIntakeDraw = null;
    private Servo rightIntakeDraw = null;
    RobotMecanumDrive drive = null;
    private boolean aChanged ;
    private boolean passA;
    private int servoState;


    private boolean linearChanged;
    private boolean passLinear;
    private int servoStateLinear;

    //Linear Extension motor and servo

    private DcMotor linearExtension = null;
    private Servo linearServo = null;



    @Override
    public void runOpMode() throws InterruptedException {
        //instantiate mecanum drive
        drive = new RobotMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        aChanged=false;
        passA=false;
        servoState=0;

        linearChanged=false;
        passLinear=false;
        servoStateLinear=0;


        waitForStart();
        runtime.reset();


        while(opModeIsActive()){
            // feed motor input to drive method
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );
            drive.update();

            //update telemetry
            telemetry.addData("Servo intake Postion, Left", rightIntakeDraw.getPosition());
            telemetry.update();
            //Assigns the turing movement


//Logic for toddle gamepade1 a
            if(gamepad1.a == passA){
                aChanged = false;
            }
            else{
                aChanged = true;
            }
            passA = gamepad1.a;

//Logic for toggle gamepad2.a

            if(gamepad2.a == passLinear){
                linearChanged = false;
            }
            else{
                linearChanged = true;
            }
            passLinear = gamepad2.a;



            if(aChanged && gamepad1.a) {
                if(servoState == 0){
                    leftIntakeDraw.setPosition(1);
                    rightIntakeDraw.setPosition(0);
                    intake.setPower(0);
                    servoState = 1;
                }
                else{
                    leftIntakeDraw.setPosition(0.4);
                    rightIntakeDraw.setPosition(0.6);
                    intake.setPower(-1);
                    servoState = 0;
                }
            }


            if(linearChanged && gamepad2.a){
                if(servoStateLinear == 0){
                    linearServo.setPosition(0);
                    servoStateLinear = 1;
                }
                else{
                    linearServo.setPosition(1);
                    servoStateLinear = 0;
                }


            }

        }










    }

}
