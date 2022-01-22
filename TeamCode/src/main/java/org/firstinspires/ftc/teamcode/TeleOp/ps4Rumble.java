package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This sample illustrates using the rumble feature of many gamepads.
 *
 * Note: Some gamepads "rumble" better than others.
 *   The Xbox & PS4 have a left (rumble1) and right (rumble2) rumble motor.
 *   These two gamepads have two distinct rumble modes: Large on the left, and small on the right
 *   The ETpark gamepad may only respond to rumble1, and may only run at full power.
 *   The Logitech F310 gamepad does not have *any* rumble ability.
 *
 *   Moral:  You should use this sample to experiment with your specific gamepads to explore their rumble features.
 *
 * The rumble motors are accessed through the standard gamepad1 and gamepad2 objects.
 *   Several new methods were added to the Gamepad class in FTC SDK Rev 7
 *   The key methods are as follows:
 *
 *   .rumble(double rumble1, double rumble2, int durationMs)
 *     This method sets the rumble power of both motors for a specific time duration.
 *     Both rumble arguments are motor-power levels in the 0.0 to 1.0 range.
 *     durationMs is the desired length of the rumble action in milliseconds.
 *     This method returns immediately.
 *     Note:
 *       Use a durationMs of Gamepad.RUMBLE_DURATION_CONTINUOUS to provide a continuous rumble
 *       Use a power of 0, or duration of 0 to stop a rumble.
 *
 *   .rumbleBlips(int count) allows an easy way to signal the driver with a series of rumble blips.
 *     Just specify how many blips you want.
 *     This method returns immediately.
 *
 *   .runRumbleEffect(customRumbleEffect) allows you to run a custom rumble sequence that you have
 *     built using the Gamepad.RumbleEffect.Builder()
 *     A "custom effect" is a sequence of steps, where each step can rumble any of the
 *     rumble motors for a specific period at a specific power level.
 *     The Custom Effect will play as the (un-blocked) OpMode continues to run
 *
 *   .isRumbling() returns true if there is a rumble effect in progress.
 *     Use this call to prevent stepping on a current rumble.
 *
 *   .stopRumble()              Stop any ongoing rumble or custom rumble effect.
 *
 *   .rumble(int durationMs)    Full power rumble for fixed duration.
 *
 *   Note: Whenever a new Rumble command is issued, any currently executing rumble action will
 *   be truncated, and the new action started immediately.  Take these precautions:
 *      1) Do Not SPAM the rumble motors by issuing rapid fire commands
 *      2) Multiple sources for rumble commands must coordinate to avoid tromping on each other.
 *
 *   This can be achieved several possible ways:
 *   1) Only having one source for rumble actions
 *   2) Issuing rumble commands on transitions, rather than states.
 *      e.g. The moment a touch sensor is pressed, rather than the entire time it is being pressed.
 *   3) Scheduling rumble commands based on timed events. e.g. 10 seconds prior to endgame
 *   4) Rumble on non-overlapping mechanical actions. e.g. arm fully-extended or fully-retracted.
 *   5) Use isRumbling() to hold off on a new rumble if one is already in progress.
 *
 * The examples shown here are representstive of how to invoke a gamepad rumble.
 * It is assumed that these will be modified to suit the specific robot and team strategy needs.
 *
 * ########   Read the telemetry display on the Driver Station Screen for instructions.   ######
 *
 * Ex 1)    This example shows a) how to create a custom rumble effect, and then b) how to trigger it based
 *          on game time.  One use for this might be to alert the driver that half-time or End-game is approaching.
 *
 * Ex 2)    This example shows tying the rumble power to a changing sensor value.
 *          In this case it is the Gamepad trigger, but it could be any sensor output scaled to the 0-1 range.
 *          Since it takes over the rumble motors, it is only performed when the Left Bumper is pressed.
 *          Note that this approach MUST include a way to turn OFF the rumble when the button is released.
 *
 * Ex 3)    This example shows a simple way to trigger a 3-blip sequence.  In this case it is
 *          triggered by the gamepad A (Cross) button, but it could be any sensor, like a touch or light sensor.
 *          Note that this code ensures that it only rumbles once when the input goes true.
 *
 * Ex 4)    This example shows how to trigger a single rumble when an input value gets over a certain value.
 *          In this case it is reading the Right Trigger, but it could be any variable sensor, like a
 *          range sensor, or position sensor.  The code needs to ensure that it is only triggered once, so
 *          it waits till the sensor drops back below the threshold before it can trigger again.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */

@TeleOp(name="ps4Rumble", group ="Drive")
public class ps4Rumble extends LinearOpMode{
    boolean lastRumble = false;
    boolean halfMark = false;
    boolean endgameMark = false;
    double halftime = 5.0;
    double endGameWarning = 5.0;
    double endGame = 10.0;
    double lastTime;
    double now;
    double deltaTime=0;
    // endgame starts at 90 sec
    // controller rumbles at 80 sec
    ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode(){
        telemetry.addData(">", "Press Start");
        telemetry.update();

        waitForStart();
        runtime.reset();
//        while(opModeIsActive()){
//            if(!endgameMark){
//                while (runtime.seconds()>=endGameWarning && runtime.seconds()<=endGame){
//                    gamepad1.rumbleBlips(4);
//                }
//            }




            if(!endgameMark){
                if(runtime.seconds()>=80){
                    lastTime = runtime.seconds();
                    while(deltaTime<=10.0){
                        gamepad1.rumbleBlips(3);
                        now=runtime.seconds()   ;
                        deltaTime = now-lastTime;
                }
                    endgameMark=true;
            }

            }











        }












    }





