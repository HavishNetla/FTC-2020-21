package com.example.graph


import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import javax.swing

fun main() {
    val cubicSplineInterpolator: CubicSplineInterpolator = CubicSplineInterpolator(listOf(
            Vector2d(0.0, 0.0),
            Vector2d(1.0, 1.0),
            Vector2d(2.0, 2.0),
            Vector2d(3.0, 3.0),
            Vector2d(4.0, 4.0)
    ))

    cubicSplineInterpolator.interpolate().polynomials.forEach {
        println(it)
    }
    println(cubicSplineInterpolator.interpolate().value(5.0))

    var xData = listOf<Double>(0.0, 1.0, 2.0)
    var yData = listOf<Double>(0.0, 1.0, 2.0)

    var chart: XYChart = QuickChart.getChart("Sample Chart", "X", "Y", "f(x)", xData, yData)

    SwingWrapper(chart).displayChart()

}