package org.firstinspires.ftc.teamcode.subsystems

abstract class Subsystem {
    val subsystemHandler = SubsystemHandler()

    open fun onInit() {}
    open fun onInitLoop() {}
    open fun onMount() {}
    open fun update() {}
    open fun onStop() {}
}