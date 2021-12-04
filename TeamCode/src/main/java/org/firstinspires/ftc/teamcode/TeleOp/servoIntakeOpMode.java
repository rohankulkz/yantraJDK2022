package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.driveSystems;

@TeleOp(name="ServoIntakeOpMode", group="Drive")
//@Disabled
public class servoIntakeOpMode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor intake = null;
    private Servo leftIntakeDraw = null;
    private Servo rightIntakeDraw = null;

    private double[] motorValues = {0,0,0,0};


     //servo toggles for A.
    private boolean aChanged = false;
    private boolean passA = false;
    //0 means up, 1 means down
    private int servoState = 0;



    //Servo toggles for Intake Drop  (Controller 2 A)

    private boolean linearChanged = false;
    private boolean passLinear = false;
    //0 means up, 1 means down
    private int servoStateLinear = 0;



    //Linear Extension motor and servo

    private DcMotor linearExtension = null;
    private Servo linearServo = null;


    @Override
    public void runOpMode(){
        //Pending Motor names
        //Motor hardware map
        leftIntakeDraw.scaleRange(0,1);
        rightIntakeDraw.scaleRange(0,1);
        //leftIntakeDraw.setDirection(Servo.Direction.FORWARD);
        //rightIntakeDraw.setDirection(Servo.Direction.REVERSE);

        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();



        frontLeft = hardwareMap.get(DcMotor.class, "frontleftd");
                frontRight = hardwareMap.get(DcMotor.class, "frontrightd");
                        backRight = hardwareMap.get(DcMotor.class, "rearrightd");;
                                backLeft = hardwareMap.get(DcMotor.class, "rearleftd");
                                intake = hardwareMap.get(DcMotor.class,"intake");;
                                leftIntakeDraw = hardwareMap.get(Servo.class,"iservoleft");
                                rightIntakeDraw = hardwareMap.get(Servo.class,"iservoright");
                                linearExtension = hardwareMap.get(DcMotor.class, "intake");
                                linearServo = hardwareMap.get(Servo.class, "intakeServo");






        while(opModeIsActive()) {
            //update telemetry
            telemetry.addData("Servo intake Postion, Left", rightIntakeDraw.getPosition());
            telemetry.update();
            //Assigns the turing movement


            double turn = gamepad1.right_stick_x;

            motorValues = driveSystems.turnDrive(turn);


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


            //Note: These movements override the turning as they cannot go at the same time yet
            //Assigns the strafing movement
            double x = -gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;

            //Driver Note: Strafing always overrides turn so do not attempt both at once.

            motorValues = driveSystems.continousDriveWithCoords(x,y);

            if(x == 0 && y ==0){
                motorValues = driveSystems.turnDrive(gamepad1.right_stick_x);
            }



            //Run the Motors
            frontLeft.setPower(motorValues[0]);
            frontRight.setPower(motorValues[1]);
            backLeft.setPower(motorValues[2]);
            backRight.setPower(motorValues[3]);
            linearExtension.setPower(gamepad2.left_stick_y);

        }
    }
}
