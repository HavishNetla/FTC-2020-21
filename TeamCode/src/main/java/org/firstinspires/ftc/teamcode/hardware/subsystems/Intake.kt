package org.firstinspires.ftc.teamcode.hardware.subsystems

import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.hardware.util.CachingDcMotor
import org.firstinspires.ftc.teamcode.subsystems.HardwareSubsystem
import org.firstinspires.ftc.teamcode.subsystems.OpMode
import org.openftc.revextensions2.ExpansionHubMotor

class Intake(robot: Robot, opMode: OpMode) : HardwareSubsystem(robot, opMode) {
	private var motor: CachingDcMotor =
			CachingDcMotor(robot.hwMap.get(ExpansionHubMotor::class.java, "I"), 0.05);

	var isReversed: Boolean = false;
	var isMotorOn: Boolean = false;

	var speed: Double = 0.0

	fun setPower() {
		if (isMotorOn) {
			motor.setPower(speed * if (isReversed) -1 else 1)
		} else {
			motor.setPower(0.0)
		}
	}

}