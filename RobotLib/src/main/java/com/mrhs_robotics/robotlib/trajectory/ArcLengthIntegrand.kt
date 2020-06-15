package com.mrhs_robotics.robotlib.trajectory

import org.apache.commons.math3.analysis.UnivariateFunction
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Custom Univariate Function for the arc length integrand
 *
 * @author Havish Netla
 *
 * @property dx derivative of the x(t) function
 * @property dy derivative of the y(t) function
 */
class ArcLengthIntegrand(val dx: UnivariateFunction, val dy: UnivariateFunction) : UnivariateFunction {
	/**
	 * returns value at given t value
	 *
	 * @param x input
	 *
	 * @return the value of f(x)
	 */
	override fun value(x: Double): Double {
		return sqrt(dx.value(x).pow(2.0) + dy.value(x).pow(2.0))
	}

	/**
	 * Returns the value of the integral
	 *
	 * @param lower lower bound of t
	 * @param higher upper bound of t
	 * @param maxT the highest value of t that is possible
	 *
	 * @return the value of the integral
	 */
	fun integrate(lower: Double, higher: Double, maxT: Int): Double {
		val trapezoidIntegrator = TrapezoidIntegrator()

		return trapezoidIntegrator.integrate(Int.MAX_VALUE,
				ArcLengthIntegrand(dx, dy), lower, higher)
	}
}
