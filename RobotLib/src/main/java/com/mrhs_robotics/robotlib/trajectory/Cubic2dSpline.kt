package com.mrhs_robotics.robotlib.trajectory

import com.mrhs_robotics.robotlib.util.Vector2d
import org.apache.commons.math3.analysis.UnivariateFunction
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction

/**
 * Parametric 2d Cubic Spline Interpolater
 *
 * @author Havish Netla
 *
 * @property points a list of points to interpolate
 */
class Cubic2dSpline(var points: List<Vector2d>) {
	private val xPoints: MutableList<Vector2d> = mutableListOf<Vector2d>()
	private val yPoints: MutableList<Vector2d> = mutableListOf<Vector2d>()

	private val xSpline: PolynomialSplineFunction
	private val ySpline: PolynomialSplineFunction

	private val tcPairs: MutableList<Pair<Double, Double>> = mutableListOf<Pair<Double, Double>>()
	var approximateLength = 0.0

	init {
		for (i in points.indices) {
			xPoints.add(Vector2d(i.toDouble(),
					points[i].x))
			yPoints.add(Vector2d(i.toDouble(),
					points[i].y))
		}
		xSpline = interpolate(xPoints)
		ySpline = interpolate(yPoints)

		for (x in 1..(points.lastIndex / 0.05).toInt()) {
			tcPairs.add(Pair(x * 0.05, arcLength(0.0, x * 0.05)))
		}

		approximateLength = tcPairs[tcPairs.lastIndex].second - 0.01
	}

	/**
	 * Interpolates the points
	 *
	 * @param data list of points
	 *
	 * @return a Polynomial Spline Function
	 */
	private fun interpolate(data: List<Vector2d>): PolynomialSplineFunction {
		val xs = mutableListOf<Double>()
		val ys = mutableListOf<Double>()

		data.forEach {
			xs.add(it.x)
			ys.add(it.y)
		}

		return SplineInterpolator().interpolate(xs.toDoubleArray(), ys.toDoubleArray())
	}

	/**
	 * @param c how far along the curve you are (arc length)
	 * @param t t value
	 *
	 * @return the point
	 */
	fun value(c: Double, t: Double = cValue(c)): Vector2d {
		return Vector2d(xSpline.value(t),
				ySpline.value(t))
	}

	/**
	 * returns a t value for c
	 * @param c the arc length
	 *
	 * @return the t value
	 */
	fun cValue(c: Double): Double {
		var tcPair = 0.0
		for (i in 1 until tcPairs.lastIndex) {
			if (c > tcPairs[i - 1].second && c < tcPairs[i + 1].second) {
				tcPair = interTCPair(tcPairs[i - 1], tcPairs[i + 1], c)
			}
		}
		return tcPair
	}

	/**
	 * Interpolates a linear t and c pair
	 *
	 * @param low the lower bound
	 * @param high the higher bound
	 * @param cVal the target cVal
	 *
	 * @return the t value
	 */
	private fun interTCPair(low: Pair<Double, Double>, high: Pair<Double, Double>, cVal: Double): Double {
		val linearInterpolator: LinearInterpolator = LinearInterpolator()

		val interp = linearInterpolator.interpolate(listOf(low.second, high.second).toDoubleArray(), listOf(low.first, high.first).toDoubleArray())

		return interp.value(cVal)
	}

	/**
	 * @return the derivative of x(t)
	 */
	fun xDerivative(): UnivariateFunction {
		return xSpline.derivative()
	}

	/**
	 * @return the derivative of y(t)
	 */
	fun yDerivative(): UnivariateFunction {
		return ySpline.derivative()
	}

	/**
	 * Returns the arc length from two values of t
	 *
	 * @param lower the lower bound
	 * @param higher the higher bound
	 *
	 * @return the arc length
	 */
	fun arcLength(lower: Double, higher: Double): Double {
		return ArcLengthIntegrand(xDerivative(),
				yDerivative()).integrate(lower, higher, points.lastIndex)
	}
}
