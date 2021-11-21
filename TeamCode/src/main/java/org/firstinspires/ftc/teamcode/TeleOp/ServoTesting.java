package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import java.util.ArrayList;

@TeleOp(name="ServoTesting", group="Drive")
//@Disabled
public class ServoTesting extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Servo leftIntakeDraw = null;
    private Servo rightIntakeDraw = null;


    @Override
    public void runOpMode(){
        //Pending Motor names
        //Motor hardware map
        leftIntakeDraw = hardwareMap.get(Servo.class,"iservoleft");
        rightIntakeDraw = hardwareMap.get(Servo.class,"iservoright");

        //Activate OpMode and robot movement
        waitForStart();
        runtime.reset();





        while(opModeIsActive()) {
//            leftIntakeDraw.setDirection(Servo.Direction.FORWARD);
//            rightIntakeDraw.setDirection(Servo.Direction.FORWARD);
            ArrayList<Double> positions = new ArrayList<Double>();
            positions.add(0.2);
            positions.add(0.4);
            positions.add(0.6);
            positions.add(0.8);
            positions.add(1.0);

            for(double i:positions){
                leftIntakeDraw.setPosition(i);
                //update telemetry
                telemetry.addData("Servo intake position", leftIntakeDraw.getPosition());
                telemetry.update();
                sleep(3000);



            }


        }
    }



}
