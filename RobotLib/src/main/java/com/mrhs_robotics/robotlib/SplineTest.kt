package com.mrhs_robotics.robotlib

import com.mrhs_robotics.robotlib.debug.MessageBuilder
import com.mrhs_robotics.robotlib.debug.MessageSender
import com.mrhs_robotics.robotlib.profile.MotionProfileGenerator.generateMotionProfile
import com.mrhs_robotics.robotlib.profile.types.MotionState
import com.mrhs_robotics.robotlib.trajectory.Cubic2dSpline
import com.mrhs_robotics.robotlib.util.GraphUtil.graphCubicSpline
import com.mrhs_robotics.robotlib.util.GraphUtil.saveChart
import com.mrhs_robotics.robotlib.util.Pose2d
import com.mrhs_robotics.robotlib.util.RESOLUTION
import com.mrhs_robotics.robotlib.util.Vector2d

fun main() {
	val points = listOf(
			Vector2d(0.0, 0.0),
			Vector2d(90.0, 150.0),
			Vector2d(200.0, 70.0),
			Vector2d(300.0, 300.0)
	)
	val cubicSpline: Cubic2dSpline =
			Cubic2dSpline(points)

	//saveChart(graphCubicSpline(cubicSpline), "testSpline")

	val mp = generateMotionProfile(
			start = MotionState(0.0, 0.0, 0.0),
			goal = MotionState(10.0, 0.0, 0.0),
			maxVel = 5.0,
			maxAccel = 5.0
	)
	val messageSender = MessageSender(6969)
	messageSender.startServer()

	for (x in 0..RESOLUTION) {
		val messageBuilder = MessageBuilder()

		val value = x / (RESOLUTION / cubicSpline.approximateLength)

		val xVal = cubicSpline.value(value).x
		val yVal = cubicSpline.value(value).y

		messageBuilder.stickyPoint(Vector2d(xVal, yVal))
		messageSender.sendMessage(messageBuilder.jo)
		Thread.sleep(15)
	}

	while (true) {
		val messageBuilder = MessageBuilder()
		messageBuilder.point(Vector2d(100.0, 100.0))

		var pos = Pose2d(
				Math.random() * 201,
				Math.random() * 201,
				Math.random() * 201
		)
		//messageBuilder.stickyPoint(pos.pos())
		messageBuilder.robotPosition(pos)

		for (x in 0..RESOLUTION) {
			val value = x / (RESOLUTION / cubicSpline.approximateLength)

			val xVal = cubicSpline.value(value).x
			val yVal = cubicSpline.value(value).y

			messageBuilder.stickyPoint(Vector2d(xVal, yVal))
		}

		messageBuilder.addLogMessage("Robot Position", pos.toString())
		messageBuilder.addHTML("<h1>sup<h1>")

		messageSender.sendMessage(messageBuilder.jo)

		println(messageBuilder.jo)
		Thread.sleep(1000)
	}
}

