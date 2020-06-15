package org.firstinspires.ftc.teamcode.subsystems

class SubsystemHandler {
    private val subsystems = mutableListOf<Subsystem>()

    fun addSubsystem(subsystem: Subsystem) {
        subsystems.add(subsystem)
    }

    fun onInit() {
        for(s in subsystems) {
            s.onInit()
        }
    }

    fun onInitLoop() {
        for(s in subsystems) {
            s.onInitLoop()
        }
    }

    fun onMount() {
        for(s in subsystems) {
            s.onMount()
        }
    }

    fun update() {
        for(s in subsystems) {
            s.update()
        }
    }

    fun onStop() {
        for(s in subsystems) {
            s.onStop()
        }
    }
}