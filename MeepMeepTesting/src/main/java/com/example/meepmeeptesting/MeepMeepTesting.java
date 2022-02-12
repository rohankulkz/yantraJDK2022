package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

public class MeepMeepTesting {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep mm = new MeepMeep(800)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_FREIGHT_FRENZY)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)
                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(60), Math.toRadians(60), 11.5)
                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-34, 56, 0))
                                drive.trajectorySequenceBuilder(new Pose2d(-28.75, 64, Math.toRadians(270)))
                                        .lineToConstantHeading(new Vector2d(-12,42))
                                        .lineToLinearHeading(new Pose2d(-64,54,Math.toRadians(90)) )
                                        .forward(10)
                                        .turn(Math.toRadians(-90))
                                        .forward(100)
//
//                                .turn(Math.toRadians(270))
//
//                                .splineTo(new Vector2d(0, 0), 90)
//                                .splineToConstantHeading(new Vector2d(-13,45),Math.toRadians(270))
//                                        //TODO DROP BOX
//                                        .back(15)
//                                        .splineTo(new Vector2d(-65,57), Math.toRadians(360) )
//
                                .build()
                )
                .start();
    }
}