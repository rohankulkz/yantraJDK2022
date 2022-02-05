package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class FirstAutonomousOpmode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RobotMecanumDrive drive = new RobotMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-6,6,Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        Trajectory t1 = drive.trajectoryBuilder(new Pose2d()).forward(6).build();
        Trajectory t2 = drive.trajectoryBuilder(t1.end()).splineTo(new Vector2d(5,6),0).splineTo(new Vector2d(9,-10),0).build();


        waitForStart();
        while(opModeIsActive()){
            drive.followTrajectory(t1);
            drive.followTrajectory(t2);
        }



    }
}
