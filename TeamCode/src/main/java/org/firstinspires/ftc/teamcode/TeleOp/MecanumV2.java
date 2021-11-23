package org.firstinspires.ftc.teamcode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.RobotMecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

//implements roadrunner's mecanum drive class with robot's systems
@TeleOp(name="MecanumV2", group="Drive")
public class MecanumV2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    RobotMecanumDrive drive = new RobotMecanumDrive(hardwareMap);
    private Servo leftIntakeDraw = null;
    private Servo rightIntakeDraw = null;
    private DcMotor intake = null;



    @Override
    public void runOpMode() throws InterruptedException {
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftIntakeDraw = hardwareMap.get(Servo.class,"iservoleft");
        rightIntakeDraw = hardwareMap.get(Servo.class,"iservoright");

        waitForStart();
        runtime.reset();
        while(opModeIsActive()){
            //update telemetry
            telemetry.addData("Servo intake Postion", leftIntakeDraw.getPosition());
            telemetry.update();


//            if(gamepad1.a){
//                if(leftIntakeDraw.getPosition()==0.66){
//                    leftIntakeDraw.setPosition(0.0);
//                }else{
//                    leftIntakeDraw.setPosition(0.66);
//                }
//
//            }
            if(gamepad1.a) {
                leftIntakeDraw.setPosition(0.4);
                rightIntakeDraw.setPosition(0.6);
                intake.setPower(1);

            }
            if(gamepad1.b) {
                leftIntakeDraw.setPosition(1);
                rightIntakeDraw.setPosition(0);
                intake.setPower(0);

            }
        }






    }

}
