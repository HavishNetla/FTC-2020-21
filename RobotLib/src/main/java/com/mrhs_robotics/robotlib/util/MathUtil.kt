package com.mrhs_robotics.robotlib.util

import kotlin.math.abs

object MathUtil {
	private const val EPSILON = 1e-6
	infix fun Double.epsilonEquals(other: Double) = abs(this - other) < EPSILON
}