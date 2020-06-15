package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.opmodes.OpMode
import org.openftc.revextensions2.ExpansionHubEx
import org.openftc.revextensions2.RevBulkData

class Robot(val map: HardwareMap, val opMode: OpMode) : Subsystem() {
    var expansionHubLeft: ExpansionHubEx = map.get(ExpansionHubEx::class.java, "Expansion Hub 2")
    var expansionHubRight: ExpansionHubEx = map.get(ExpansionHubEx::class.java, "Expansion Hub 1")

    private var bulkDataLeft: RevBulkData? = null
    private var bulkDataRight: RevBulkData? = null

    var bulkDataLeftUpdatedThisCycle = false
    var bulkDataRightUpdatedThisCycle = false


    init {

        //subsystemHandler.addSubsystem()
    }

    override fun update() {
        bulkDataLeftUpdatedThisCycle = false
        bulkDataRightUpdatedThisCycle = false
    }

    fun getBulkDataRight(): RevBulkData? {
        if (!bulkDataRightUpdatedThisCycle) {
            bulkDataRight = expansionHubRight.bulkInputData
            bulkDataRightUpdatedThisCycle = true
        }
        return bulkDataRight
    }

    fun getBulkDataLeft(): RevBulkData? {
        if (!bulkDataLeftUpdatedThisCycle) {
            bulkDataLeft = expansionHubLeft.bulkInputData
            bulkDataLeftUpdatedThisCycle = true
        }
        return bulkDataLeft
    }
}