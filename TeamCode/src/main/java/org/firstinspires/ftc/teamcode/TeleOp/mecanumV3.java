package org.firstinspires.ftc.teamcode.TeleOp;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="mecanumV3", group="Drive")
//@Disabled
public class mecanumV3 extends LinearOpMode implements Runnable {

    private ElapsedTime runtime = new ElapsedTime();
    RobotMecanumDrive drive = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor intake = null;
    private Servo leftIntakeDraw = null;
    private Servo rightIntakeDraw = null;

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


    boolean lastRumble = false;
    boolean halfMark = false;
    boolean endgameMark = false;
    double halftime = 60.0;
    double endGameWarning = 80.0;
    double endGame = 90.0;
    mecanumV3 thread = null;


    @Override
    public void runOpMode(){
        //activate roadrunner mecanum drive
        drive = new RobotMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Pending Motor names
        //Motor hardware map

        //leftIntakeDraw.setDirection(Servo.Direction.FORWARD);
        //rightIntakeDraw.setDirection(Servo.Direction.REVERSE);

        //create thread
        thread = new mecanumV3();



        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();



//        frontLeft = hardwareMap.get(DcMotor.class, "frontleftd");
//        frontRight = hardwareMap.get(DcMotor.class, "frontrightd");
//        backRight = hardwareMap.get(DcMotor.class, "rearrightd");;
//        backLeft = hardwareMap.get(DcMotor.class, "rearleftd");
        intake = hardwareMap.get(DcMotor.class,"intake");;
        leftIntakeDraw = hardwareMap.get(Servo.class,"iservoleft");
        rightIntakeDraw = hardwareMap.get(Servo.class,"iservoright");
        linearExtension = hardwareMap.get(DcMotor.class, "linear");
        linearServo = hardwareMap.get(Servo.class, "linearServo");


        leftIntakeDraw.scaleRange(0,1);
        rightIntakeDraw.scaleRange(0,1);






        while(opModeIsActive()) {
            //run thread
            thread.start();


            //update telemetry
            telemetry.addData("Servo intake Postion, Left", rightIntakeDraw.getPosition());
            telemetry.update();
            //Assigns the turing movement


            double turn = gamepad1.right_stick_x;

//            motorValues = driveSystems.turnDrive(turn);


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


//            //Note: These movements override the turning as they cannot go at the same time yet
//            //Assigns the strafing movement
//            double x = -gamepad1.left_stick_x;
//            double y = -gamepad1.left_stick_y;
//
//            //Driver Note: Strafing always overrides turn so do not attempt both at once.
//
//            motorValues = driveSystems.continousDriveWithCoords(x,y);
//
//            if(x == 0 && y ==0){
//                motorValues = driveSystems.turnDrive(gamepad1.right_stick_x);
//            }



//            //Run the Motors
//            frontLeft.setPower(motorValues[0]);
//            frontRight.setPower(motorValues[1]);
//            backLeft.setPower(motorValues[2]);
//            backRight.setPower(motorValues[3]);
//            linearExtension.setPower(gamepad2.left_stick_y);

            // feed controller input
            //run motors
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

        }
    }

    @Override
    public void run() {
        telemetry.addData(">", "Press Start");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){
            if(!halfMark){
                telemetry.addData(">", "Halftime Alert Countdown: %3.0f Sec \n", (halftime - runtime.seconds()) );
            }
            if(!endgameMark){
                telemetry.addData(">", "EndGame Alert Countdown: %3.0f Sec \n", (endGame - runtime.seconds()) );
            }
            if(runtime.seconds()==halftime){
                gamepad1.rumbleBlips(2);
                gamepad2.rumbleBlips(2);
            }else{
                if(!halfMark){
                    halfMark=true;
                }

            }
            if(runtime.seconds()>=endGameWarning && runtime.seconds()<=endGame){
                gamepad1.rumbleBlips(3);
                gamepad2.rumbleBlips(3);
            }
            else{
                if(!endgameMark){
                    endgameMark=true;
                }
            }

            telemetry.update();




        }

    }
}
