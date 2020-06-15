package com.mrhs_robotics.robotlib.profile.types

/**
 * Kinematic State of a Motion Profile
 *
 * @author Havish Netla
 * @property x represents the distance along the path
 * @property v velocity
 * @property a acceleration
 * @property j jerk
 */
class MotionState(val x: Double, val v: Double, val a: Double = 0.0, val j: Double = 0.0) {

	operator fun get(t: Double): MotionState = MotionState(
			x + v * t + a / 2 * t * t + j / 6 * t * t * t,
			v + a * t + j / 2 * t * t,
			a + j * t,
			j
	)

	fun flipped() = MotionState(-x, -v, -a, -j)

	override fun toString() = String.format("(x=%.3f, v=%.3f, a=%.3f, j=%.3f)", x, v, a, j)
}