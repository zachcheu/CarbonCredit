package com.example.zachcheu.carboncredit;

import java.util.List;

/**
 * Callback for after points have been matched.
 */
public interface PointMatcherCallback {
	/**
	 * Occurs when a new set of lines are availible.
	 * @param points A list of from,to points
	 */
	public void addLines(List<DrivePointPair> points);
}
