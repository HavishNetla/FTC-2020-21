package com.mrhs_robotics.robotlib.profile

import com.mrhs_robotics.robotlib.profile.types.MotionProfile
import com.mrhs_robotics.robotlib.profile.types.MotionSegment
import com.mrhs_robotics.robotlib.profile.types.MotionState
import com.mrhs_robotics.robotlib.util.MathUtil.epsilonEquals

class MotionProfileBuilder(start: MotionState) {
	private var currentState = start
	private val segments = mutableListOf<MotionSegment>()

	// constant jerk for a period of time (dT)
	fun appendJerkControl(jerk: Double, dt: Double): MotionProfileBuilder {
		val motionSegment =
				MotionSegment(MotionState(currentState.x, currentState.v, currentState.a, jerk), dt)
		segments.add(motionSegment)
		currentState = motionSegment.end()

		return this
	}

	fun appendAccelerationControl(accel: Double, dt: Double): MotionProfileBuilder {
		val motionSegment =
				MotionSegment(MotionState(currentState.x, currentState.v, accel, currentState.j),
						dt)
		segments.add(motionSegment)
		currentState = motionSegment.end()

		return this
	}

	fun appendProfile(profile: MotionProfile, dt: Double): MotionProfileBuilder {
		for (segment in segments) {
			if (segment.start.j epsilonEquals 0.0) {
				//c accle
				appendAccelerationControl(segment.start.a, segment.dT)
			} else {
				//c jerk
				appendJerkControl(segment.start.j, segment.dT)
			}
		}

		return this
	}

	fun appendProfile(profile: MotionProfile): MotionProfileBuilder {
		for (segment in profile.segments) {
			if (segment.start.j epsilonEquals 0.0) {
				// constant acceleration
				appendAccelerationControl(segment.start.a, segment.dT)
			} else {
				// constant jerk
				appendJerkControl(segment.start.j, segment.dT)
			}
		}
		return this
	}

	fun build() = MotionProfile(segments)
}