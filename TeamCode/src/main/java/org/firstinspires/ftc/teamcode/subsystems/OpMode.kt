package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.Robot


class OpMode : LinearOpMode() {
	protected var subsystemHandler: SubsystemHandler = SubsystemHandler()
	protected lateinit var robot: Robot
	protected fun onInit() {}
	protected fun initLoop() {}
	protected fun onMount() {}
	protected fun update() {}
	protected fun onStop() {}

	@Throws(InterruptedException::class)
	override fun runOpMode() {
		robot = Robot(hardwareMap, this)
		subsystemHandler.add(robot)

		onInit()
		subsystemHandler.onInit()

		while (!isStarted && !isStopRequested) {
			subsystemHandler.initLoop()
			initLoop()
		}

		subsystemHandler.onMount()
		onMount()

		while (opModeIsActive() && !isStopRequested) {
			subsystemHandler.update()
			update()
		}

		subsystemHandler.onStop()
		onStop()
	}
}
