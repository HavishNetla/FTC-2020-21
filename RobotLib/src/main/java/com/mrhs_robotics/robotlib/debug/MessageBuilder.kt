package com.mrhs_robotics.robotlib.debug

import com.mrhs_robotics.robotlib.util.Pose2d
import com.mrhs_robotics.robotlib.util.Vector2d
import org.json.JSONObject


class MessageBuilder {
	var jo = JSONObject()

	fun robotPosition(pos: Pose2d) {
		jo.put("position", "${pos.x},${pos.y},${pos.heading}")
	}

	fun stickyPoint(point: Vector2d) {
		jo.put("stickyPoint", "${point.x},${point.y}")
	}

	fun point(point: Vector2d) {
		jo.put("point", "${point.x},${point.y}")
	}

	fun line(line: Pair<Vector2d, Vector2d>) {
		jo.put("line", "${line.first.x},${line.first.y},${line.second.x},${line.second.y}")
	}

	fun addLogMessage(label: String, message: String) {
		try {
			jo.put("log", "${jo.get("log")}$label: $message\n")
		} catch (e: Exception) {
			jo.put("log", "$label: $message\n")
		}
	}

	fun addHTML(html: String) {
		try {
			jo.put("html", "${jo.get("html")}$html")
		} catch (e: Exception) {
			jo.put("html", html)
		}
	}
}