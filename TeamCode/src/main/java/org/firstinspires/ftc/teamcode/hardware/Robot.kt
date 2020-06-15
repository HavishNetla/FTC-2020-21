package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.hardware.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.OpMode
import org.firstinspires.ftc.teamcode.subsystems.Subsystem
import org.openftc.revextensions2.ExpansionHubEx
import org.openftc.revextensions2.RevBulkData


class Robot(var hwMap: HardwareMap, opMode: OpMode) : Subsystem() {
	var expansionHubRight: ExpansionHubEx = hwMap.get(ExpansionHubEx::class.java, "Expansion Hub 1")
	var expansionHubLeft: ExpansionHubEx = hwMap.get(ExpansionHubEx::class.java, "Expansion Hub 2")

	var bulkDataRight: RevBulkData? = null
		get() {
			if (!bulkDataUpdatedThisCycleRight) {
				field = expansionHubRight.bulkInputData
				bulkDataUpdatedThisCycleRight = true
			}
			return field
		}
		private set

	var bulkDataLeft: RevBulkData? = null
		get() {
			if (!bulkDataUpdatedThisCycleLeft) {
				field = expansionHubLeft.bulkInputData
				bulkDataUpdatedThisCycleLeft = true
			}
			return field
		}
		private set

	private var bulkDataUpdatedThisCycleRight = false
	private var bulkDataUpdatedThisCycleLeft = false

	val subsystemIntake: Intake = Intake(this, opMode)

	private val lastTime = 0.0
	override fun update() {
		bulkDataUpdatedThisCycleRight = false
		bulkDataUpdatedThisCycleLeft = false
	}

	init {
		subsystemHandler.add(subsystemIntake)
	}
}