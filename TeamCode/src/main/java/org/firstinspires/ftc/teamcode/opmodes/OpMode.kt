package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.Robot
import org.firstinspires.ftc.teamcode.subsystems.SubsystemHandler


open class OpMode : LinearOpMode() {
	protected var subsystemHandler = SubsystemHandler()
	protected var robot: Robot? = null

	protected fun onInit() {}

	protected fun initLoop() {}

	protected fun onMount() {}

	protected fun update() {}

	protected fun onStop() {}
	override fun runOpMode() {
		TODO("Not yet implemented")
	}

}