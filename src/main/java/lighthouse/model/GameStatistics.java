package lighthouse.model;

import lighthouse.util.ListenerList;

/**
 * POJO containing some statistics about the game.
 */
public class GameStatistics {
	private int moveCount = 0;
	private double avgDistanceToGoal = 0;
	
	private final ListenerList<Integer> moveCountListeners = new ListenerList<>("GameStatistics.moveCountListeners");
	private final ListenerList<Double> distanceToGoalListeners = new ListenerList<>("GameStatistics.distanceToGoalListeners");
	
	public int getMoveCount() { return moveCount; }
	
	public double getAvgDistanceToGoal() { return avgDistanceToGoal; }
	
	public void reset() {
		moveCount = 0;
		avgDistanceToGoal = 0;
		moveCountListeners.fire(moveCount);
		distanceToGoalListeners.fire(avgDistanceToGoal);
	}
	
	public void incrementMoveCount() {
		moveCount++;
		moveCountListeners.fire(moveCount);
	}
	
	public void setAvgDistanceToGoal(double avgDistanceToGoal) {
		this.avgDistanceToGoal = avgDistanceToGoal;
		distanceToGoalListeners.fire(avgDistanceToGoal);
	}
	
	public ListenerList<Integer> getMoveCountListeners() { return moveCountListeners; }
	
	public ListenerList<Double> getDistanceToGoalListeners() { return distanceToGoalListeners; }
}
