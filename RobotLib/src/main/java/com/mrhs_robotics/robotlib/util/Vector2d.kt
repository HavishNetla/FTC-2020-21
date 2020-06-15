package com.mrhs_robotics.robotlib.util

import kotlin.math.*

/**
 * Vector 2d Class
 *
 * @author Havish Netla
 *
 * @property x the x value
 * @property y the y value
 */
class Vector2d(var x: Double, var y: Double) {

	/**
	 * @return normalizes the Vector
	 */
	fun norm(): Double {
		return sqrt(x * x + y * y)
	}

	/**
	 * @return the angle of the Vector
	 */
	fun angle(): Double {
		return atan2(y, x)
	}

	/**
	 * @param other another Vector2d
	 *
	 * @return the sum of the two vectors
	 */
	operator fun plus(other: Vector2d): Vector2d {
		return Vector2d(x + other.x, y + other.y)
	}

	/**
	 * @param other another Vector2d
	 *
	 * @return the difference of the two vectors
	 */
	operator fun minus(other: Vector2d): Vector2d {
		return Vector2d(x - other.x, y - other.y)
	}

	/**
	 * @param scalar value to scale vector by
	 *
	 * @return the Vector times the scalar
	 */
	operator fun times(scalar: Double): Vector2d {
		return Vector2d(x * scalar, y * scalar)
	}

	/**
	 * @param scalar value to scale vector by
	 *
	 * @return the Vector divided by the scalar
	 */
	operator fun div(scalar: Double): Vector2d {
		return Vector2d(x / scalar, y / scalar)
	}

	/**
	 * @return the negated Vector
	 */
	operator fun unaryMinus(): Vector2d {
		return Vector2d(-x, -y)
	}

	/**
	 * @param other another Vector
	 *
	 * @return the dot product
	 */
	fun dot(other: Vector2d): Double {
		return x * other.x + y * other.y
	}

	/**
	 * @param other another Vector
	 *
	 * @return the distance to the Vector
	 */
	fun distanceTo(other: Vector2d): Double {
		return this.minus(other).norm()
	}

	/**
	 * @param angle an angle
	 *
	 * @return a new rotated vector
	 */
	fun rotated(angle: Double): Vector2d {
		val newX = x * cos(angle) - y * sin(angle)
		val newY = x * sin(angle) + y * cos(angle)

		return Vector2d(newX, newY)
	}

	/**
	 * @param other another Vector
	 * @return the distance from this vector to the other vector
	 */
	fun dist(other: Vector2d): Double {
		return sqrt((this.x - other.x).pow(2.0) + (this.y - other.y).pow(2.0))
	}

	/**
	 * @return a String representation of the Vector
	 */
	override fun toString(): String {
		return String.format("(%.3f, %.3f)", x, y)
	}

	/**
	 * @param other another Vector
	 *
	 * @return if they are equal
	 */
	override fun equals(other: Any?): Boolean {
		return if (other is Vector2d) {
			abs(x - other.x) < 1e-4 && abs(y - other.y) < 1e-4
		} else {
			false
		}
	}

	/**
	 * @return the hashcode
	 */
	override fun hashCode(): Int {
		var result = x.hashCode()
		result = 31 * result + y.hashCode()
		return result
	}
}
