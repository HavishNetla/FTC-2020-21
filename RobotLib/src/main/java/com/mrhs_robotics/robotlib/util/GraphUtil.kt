package com.mrhs_robotics.robotlib.util

import com.mrhs_robotics.robotlib.profile.types.MotionProfile
import com.mrhs_robotics.robotlib.trajectory.Cubic2dSpline
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import org.knowm.xchart.style.MatlabTheme

const val DPI = 300
const val GRAPH_DIR =
		"/Users/havish/Dev/SkyStone/RobotLib/src/main/java/com/example/robotlib/graphs/"
const val RESOLUTION = 500

object GraphUtil {
	fun displayChart(chart: XYChart) =
			SwingWrapper(chart).displayChart()

	fun saveChart(chart: XYChart, fileName: String) =
			BitmapEncoder.saveBitmapWithDPI(chart, GRAPH_DIR + fileName,
					BitmapEncoder.BitmapFormat.PNG,
					DPI);

	fun graphCubicSpline(spline: Cubic2dSpline): XYChart {
		val xData = mutableListOf<Double>()
		val yData = mutableListOf<Double>()

		for (x in 0..RESOLUTION) {
			var value = x / (RESOLUTION / spline.approximateLength)

			var xVal = spline.value(value).x
			var yVal = spline.value(value).y

			xData.add(xVal)
			yData.add(yVal)
		}

		val graph = QuickChart.getChart("Spline", "X", "Y", "f(x)", xData, yData)
		graph.styler.theme = MatlabTheme()

		return graph
	}

	fun graphMotionProfile(mp: MotionProfile): XYChart {
		val timeData =
				(0..RESOLUTION).map { it / RESOLUTION.toDouble() * mp.duration() }.toDoubleArray()

		val positionData = timeData.map { mp[it].x }.toDoubleArray()
		val velocityData = timeData.map { mp[it].v }.toDoubleArray()
		val accelerationData = timeData.map { mp[it].a }.toDoubleArray()

		val data = listOf(positionData, velocityData, accelerationData)
		val labels = listOf("x(t)", "v(t)", "a(t)")

		val graph = QuickChart.getChart(
				"Motion Profile",
				"time (sec)",
				"",
				labels.toTypedArray(),
				timeData,
				data.toTypedArray()
		)
		graph.styler.theme = MatlabTheme()

		return graph
	}
}