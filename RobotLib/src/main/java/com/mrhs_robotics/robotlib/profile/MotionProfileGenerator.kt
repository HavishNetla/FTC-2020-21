package com.mrhs_robotics.robotlib.profile

import com.mrhs_robotics.robotlib.profile.types.MotionProfile
import com.mrhs_robotics.robotlib.profile.types.MotionState
import kotlin.math.abs
import kotlin.math.pow

object MotionProfileGenerator {
	// Generates acceleration limited trap profile
	fun generateMotionProfile(
			start: MotionState,
			goal: MotionState,
			maxVel: Double,
			maxAccel: Double,
			overshoot: Boolean = false
	): MotionProfile {
		val requiredAccel = (goal.v.pow(2) - start.v.pow(2)) / (2 * (goal.x - start.x))
		val accelProfile = generateAccelProfile(start, maxVel, maxAccel)
		val deccelProfile = accelProfile.reversed()

		val teepeeprofile = accelProfile + deccelProfile // theres no coast its just a pyramid
		val remainingDist = goal.x - teepeeprofile.end().x

		//if (remainingDist >= 0.0) {
		val timeToTraverse = remainingDist / maxVel
		// 3
		return MotionProfileBuilder(start)
				.appendProfile(accelProfile)
				.appendAccelerationControl(0.0, timeToTraverse)
				.appendProfile(deccelProfile)
				.build()
		//}

	}

	//accel limited cuz no one gives a shit about jerk
	fun generateAccelProfile(start: MotionState, maxVel: Double, maxAccel: Double): MotionProfile {
		val velocityDiff = abs(start.v - maxVel)
		val accelDeccelTime = velocityDiff / maxAccel

		val builder = MotionProfileBuilder(start)

		if (start.v > maxVel) { // decelerate ee e  e  e  e   e    e
			builder.appendAccelerationControl(-maxAccel, accelDeccelTime)
		} else { // accelerate    e  e  e  e  e  e e ee
			builder.appendAccelerationControl(maxAccel, accelDeccelTime)
		}

		return builder.build()
	}


}