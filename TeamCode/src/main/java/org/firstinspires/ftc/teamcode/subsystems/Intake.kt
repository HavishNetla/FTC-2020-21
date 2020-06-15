package org.firstinspires.ftc.teamcode.subsystems

import org.firstinspires.ftc.teamcode.opmodes.OpMode
import org.firstinspires.ftc.teamcode.hardware.CachingDcMotor
import org.openftc.revextensions2.ExpansionHubMotor

class Intake(robot: Robot, opMode: OpMode) : HardwareSubsystem(robot, opMode) {
    private var motor: CachingDcMotor = CachingDcMotor(robot.map.get(ExpansionHubMotor::class.java, "Intake Motor"), 0.05)

}