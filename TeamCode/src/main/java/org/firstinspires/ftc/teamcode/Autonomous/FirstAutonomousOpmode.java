package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class FirstAutonomousOpmode extends LinearOpMode {

    public DcMotor duck = null;

    @Override
    public void runOpMode() throws InterruptedException {
        duck = hardwareMap.get(DcMotor.class, "duck");
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);





        Pose2d startPose = new Pose2d(-28.75, 64, Math.toRadians(270));
        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose, false)
                .lineToConstantHeading(new Vector2d(-12,42))
                .addDisplacementMarker(() -> {
                    // This marker runs after the first splineTo()

                    // Run your action in here!
                })
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(), false)
                .lineToLinearHeading(new Pose2d(-64,54,Math.toRadians(90)))
                .addTemporalMarker(2, () -> {

            // This marker runs two seconds into the trajectory


            // Run your action in here!
                    duck.setPower(1);
        })
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end(), false)
                .forward(7.5)
                .build();






        waitForStart();
        while(opModeIsActive()){
//            drive.followTrajectory(t1);
//            drive.followTrajectory(t2);
        }



    }
}
