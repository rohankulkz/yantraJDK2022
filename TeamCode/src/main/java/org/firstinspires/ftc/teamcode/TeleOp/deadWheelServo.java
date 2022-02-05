package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class deadWheelServo extends LinearOpMode{
    private Servo deadWheel = null;
    @Override
    public void runOpMode() {
        deadWheel = hardwareMap.get(Servo.class, "deadWheel");
        waitForStart();
        deadWheel.setPosition(0.2);
        deadWheel.setPosition(0.4);
        deadWheel.setPosition(0.6);



    }
}
