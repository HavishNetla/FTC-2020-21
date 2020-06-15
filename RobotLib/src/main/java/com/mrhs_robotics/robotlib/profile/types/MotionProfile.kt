package com.mrhs_robotics.robotlib.profile.types

import com.mrhs_robotics.robotlib.profile.MotionProfileBuilder
import kotlin.math.max
import kotlin.math.min

class MotionProfile(segments: List<MotionSegment>) {
	internal val segments: MutableList<MotionSegment> = segments.toMutableList()

	operator fun get(t: Double): MotionState {
		var remainingTime = max(0.0, min(t, duration()))

		for (segment in segments) {
			if (remainingTime <= segment.dT) {
				return segment[remainingTime]
			}
			remainingTime -= segment.dT
		}
		return segments.lastOrNull()?.end() ?: MotionState(0.0, 0.0)
	}

	fun duration() = segments.sumByDouble { it.dT }

	fun reversed() = MotionProfile(segments.map { it.reversed() }.reversed())

	fun flipped() = MotionProfile(segments.map { it.flipped() })

	fun start() = get(0.0)
	fun end() = get(duration())

	operator fun plus(other: MotionProfile): MotionProfile {
		val builder = MotionProfileBuilder(start())
		builder.appendProfile(this)
		builder.appendProfile(other)
		return builder.build()
	}
}