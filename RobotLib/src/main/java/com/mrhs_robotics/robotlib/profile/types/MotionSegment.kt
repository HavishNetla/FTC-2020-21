package com.mrhs_robotics.robotlib.profile.types

// segment with constant accell
class MotionSegment(val start: MotionState, val dT: Double) {

	operator fun get(t: Double) = start[t]

	fun end() = start[dT]

	fun reversed(): MotionSegment {
		val end = end()
		val state = MotionState(end.x, end.v, -end.a, end.j)
		return MotionSegment(state, dT)
	}

	fun flipped() = MotionSegment(start.flipped(), dT)

	override fun toString() = "start motion state: $start, dT: $dT"
}