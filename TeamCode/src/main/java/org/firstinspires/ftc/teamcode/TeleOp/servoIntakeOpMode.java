package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    private Servo internalservo = null;
    private DcMotor duck = null;

    private double[] motorValues = {0,0,0,0};


     //servo toggles for A.
    private boolean aChanged = false;
    private boolean passA = false;


    private boolean dChanged = false;
    private boolean passd = false;

    //0 means up, 1 means down
    private int servoState = 0;



    //Servo toggles for Intake Drop  (Controller 2 A)

    private boolean linearChanged = false;
    private boolean passLinear = false;
    //0 means up, 1 means down
    private int servoStateLinear = 0;

    //Servo toggles for internal Servo
    private boolean internalservoChanged = false;
    private boolean passinternalservo = false;
    private int internalservoState = 0;



    //Linear Extension motor and servo

    private DcMotor linearExtension = null;
    private Servo linearServo = null;


    @Override
    public void runOpMode(){
        //Pending Motor names
        //Motor hardware map

        //leftIntakeDraw.setDirection(Servo.Direction.FORWARD);
        //rightIntakeDraw.setDirection(Servo.Direction.REVERSE);

        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();



        frontLeft = hardwareMap.get(DcMotor.class, "frontleftd");
                frontRight = hardwareMap.get(DcMotor.class, "frontrightd");
                        backRight = hardwareMap.get(DcMotor.class, "rearrightd");;
                                backLeft = hardwareMap.get(DcMotor.class, "rearleftd");
                                intake = hardwareMap.get(DcMotor.class,"intake");
                                leftIntakeDraw = hardwareMap.get(Servo.class,"iservoleft");
                                rightIntakeDraw = hardwareMap.get(Servo.class,"iservoright");
                                linearExtension = hardwareMap.get(DcMotor.class, "linear");
                                linearServo = hardwareMap.get(Servo.class, "linearServo");
                                internalservo = hardwareMap.get(Servo.class, "internalservo");

                                internalservo.setDirection(Servo.Direction.REVERSE);
                                duck = hardwareMap.get(DcMotor.class, "duck");


        leftIntakeDraw.scaleRange(0,1);
        rightIntakeDraw.scaleRange(0,1);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);







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



            //Logic for toddle gamepade1 a
            if(gamepad1.left_bumper == passA){
                dChanged = false;
            }
            else{
                dChanged = true;
            }
            passd = gamepad1.a= gamepad1.left_bumper;


//Logic for toggle gamepad2.a

            if(gamepad1.b == passLinear){
                linearChanged = false;
            }
            else{
                linearChanged = true;
            }
            passLinear = gamepad1.b;

//logic for toggle gamepad1.right_bumper
            if(gamepad1.right_bumper == passinternalservo){
                internalservoChanged = false;
            }
            else{
                internalservoChanged = true;
            }
            passinternalservo = gamepad1.right_bumper;




            if(aChanged && gamepad1.a) {
                if(servoState == 0){
                    //Up position
                    //Set Internal Servo to not be destroyed
                    //internalservo.setPosition(0.7);


                    leftIntakeDraw.setPosition(0.9);
                    rightIntakeDraw.setPosition(0.1);
                    intake.setPower(0);
                    servoState = 1;
                }
                else{
                    //down position

                    //Set Internal Servo to not be destroyed
                    //internalservo.setPosition(0.7);



                    leftIntakeDraw.setPosition(0.4);
                    rightIntakeDraw.setPosition(0.6);
                    
                    servoState = 0;
                }
            }


            if(dChanged && gamepad1.left_bumper) {
                if(servoState == 0){
                    //Up position
                    //Set Internal Servo to not be destroyed
                    //internalservo.setPosition(0.7);


                    duck.setPower(1);
                }
                else{
                    //down position

                    //Set Internal Servo to not be destroyed
                    //internalservo.setPosition(0.7);



                    duck.setPower(-1);
                }
            }

            float rev = gamepad1.right_trigger;

            intake.setPower(-rev);

            if(linearChanged && gamepad1.b){
                if(servoStateLinear == 0){
                    linearServo.setPosition(0.0);
                    servoStateLinear = 1;
                    telemetry.addData("Position: ", linearServo.getPosition());
                }
                else{
                    telemetry.addData("Position: ", linearServo.getPosition());
                    linearServo.setPosition(0.9
                    );
                    servoStateLinear = 0;
                }


            }

            if(internalservoChanged && gamepad1.right_bumper){
                if(internalservoState == 0){
                    //in position
                    internalservo.setPosition(0.25);
                    internalservoState = 1;
                }
                else{
                    //out position
                    internalservo.setPosition(0.5);
                    internalservoState = 0;
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


            //Linear Extension
            if(gamepad1.dpad_up){
                    linearExtension.setPower(1);
        }
            else if (gamepad1.dpad_down){
                linearExtension.setPower(-1);
            }
            else{
                linearExtension.setPower((0));
            }

        }
    }
}
