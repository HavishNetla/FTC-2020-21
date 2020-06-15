package com.mrhs_robotics.robotlib.trajectory.constraints

import com.mrhs_robotics.robotlib.util.Pose2d

class DriveConstraints(
		var maxVel: Double,
		var maxAccel: Double,
		var maxAngVel: Double,
		var maxAngAccel: Double
) {
	operator fun get(pose: Pose2d, deriv: Pose2d, secondDeriv: Pose2d): MotionConstraint {
		return MotionConstraint(1.0, 1.0)
	}
}