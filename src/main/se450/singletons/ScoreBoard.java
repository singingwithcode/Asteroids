package main.se450.singletons;

import javax.swing.JTextField;

import main.se450.interfaces.IObservable;

/**
 * This class is used to keep track of the score displayed to the player
 * @author Matt Klich
 */
public class ScoreBoard implements IObservable {
	
	private static ScoreBoard scoreBoard = null;
	private int totalScore;
	private JTextField scoreField;
	private Boolean gameOver = false;
	
	static {
		scoreBoard = new ScoreBoard();
	}

	private ScoreBoard() {
		totalScore = 0;
	}
	
	public static ScoreBoard getScoreBoard() {
		return scoreBoard;
	}
	
	public void setScoreField(JTextField scoreField) {
		this.scoreField = scoreField;
	}
	
	public void scored(int scoreAmt) {
		totalScore = totalScore + scoreAmt;
		update();
	}
	
	public void setGameOverTrue() {
		gameOver = true;
	}
	
	public Boolean getIfGameOver() {
		return gameOver;
	}
	
	@Override
	public void update() {
		if (gameOver) {
			scoreField.setText("GAME OVER... Total Score: " + totalScore);
		} else {
			scoreField.setText("Score: " + totalScore);
		}
	}
}
