package org.firstinspires.ftc.teamcode.TeleOp;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.drive.RobotMecanumDrive;
import org.firstinspires.ftc.teamcode.driveSystems;

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
