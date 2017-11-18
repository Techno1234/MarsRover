package com.catalyser;

import static com.catalyser.common.Constants.LOWER_LEFT_LIMIT;

import java.awt.Point;
import java.util.Map;

import com.catalyser.common.Direction;

public class Exploration {

	private Point upperRightLimit;

	private Map<Rover, String> roverInstructionMap;

	public Exploration(Point upperRightLimit, Map<Rover, String> roverInstructionMap) {
		super();
		this.upperRightLimit = upperRightLimit;
		this.roverInstructionMap = roverInstructionMap;
	}

	public void explore() throws Exception {

		for (Map.Entry<Rover, String> entry : roverInstructionMap.entrySet()) {
			moveRover(entry.getKey(), entry.getValue());
		}
		printOutput(roverInstructionMap);

	}

	private void printOutput(final Map<Rover, String> roverInstructionMap) {
		System.out.println("Expected Output:");
		roverInstructionMap.forEach((rover, s) -> {
			System.out.println(rover.getPoint().x + " " + rover.getPoint().y + " " + rover.getDirection().toString());
		});

	}

	private void moveRover(Rover rover, String instructionSequence) throws Exception {
		int length = instructionSequence.length();
		char instruction;
		for (int i = 0; i < length; i++) {
			instruction = instructionSequence.charAt(i);
			if (instruction == 'L') {
				rotateLeft(rover);
			} else if (instruction == 'R') {
				rotateRight(rover);
			} else if (instruction == 'M') {
				moveForward(rover);
			}
		}
	}

	private void rotateLeft(Rover rover) {
		switch (rover.getDirection()) {
		case N:
			rover.setDirection(Direction.W);
			break;
		case S:
			rover.setDirection(Direction.E);
			break;
		case E:
			rover.setDirection(Direction.N);
			break;
		case W:
			rover.setDirection(Direction.S);
			break;
		}
	}

	private void rotateRight(Rover rover) {
		switch (rover.getDirection()) {
		case N:
			rover.setDirection(Direction.E);
			break;
		case S:
			rover.setDirection(Direction.W);
			break;
		case E:
			rover.setDirection(Direction.S);
			break;
		case W:
			rover.setDirection(Direction.N);
			break;
		}
	}

	private void moveForward(final Rover rover) throws Exception {
		switch (rover.getDirection()) {
		case N:
			rover.getPoint().translate(0, 1);
			break;
		case S:
			rover.getPoint().translate(0, -1);
			break;
		case E:
			rover.getPoint().translate(1, 0);
			break;
		case W:
			rover.getPoint().translate(-1, 0);
			break;
		}

		checkWithInPlateau(rover);
	}

	private void checkWithInPlateau(final Rover rover) throws Exception {
		final int x_coordinate = rover.getPoint().x;
		final int y_coordinate = rover.getPoint().y;

		final int x_upperLimit = this.upperRightLimit.x;
		final int y_upperLimit = this.upperRightLimit.y;

		final int x_lowerLimit = LOWER_LEFT_LIMIT.x;
		final int y_lowerLimit = LOWER_LEFT_LIMIT.y;

		if (x_coordinate < x_lowerLimit || x_coordinate > x_upperLimit || y_coordinate < y_lowerLimit
				|| y_coordinate > y_upperLimit) {
			throw new Exception("Incorrect movement instruction. Cannot Proceed");
		}
	}

}
