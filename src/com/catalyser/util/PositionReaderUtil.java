package com.catalyser.util;

import java.awt.Point;
import static com.catalyser.common.Constants.LOWER_LEFT_LIMIT;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.catalyser.Rover;
import com.catalyser.common.Direction;

public class PositionReaderUtil {
	public static final String ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_X = "Incorrect upper right X coordinate;Cannot be less than or equal to 0";
	public static final String ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_Y = "Incorrect upper right Y coordinate; Cannot be less than or equal to 0";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_X = "Incorrect input for x coordinate ; Required interger";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_Y = "Incorrect input for y coordinate ; Required interger";
	public static final String ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_X = "Incorrect input for rover; x coordinate needs to be an interger";
	public static final String ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_Y = "Incorrect input for rover; y coordinate needs to be an interger";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_DIRECTION = "Incorrect input for direction; direction can be one of N/S/E/W";
	private static final String ERROR_MESSAGE_PARSING_INPUTS = "Parsing error.Incorrect format of inputs";
	private static final String MESSAGE_FOR_UPPERLIMIT_INPUTS = "Please enter the upper-right coordinates of the plateau. ";
	private static final String DELIMITER="\\s+";
	private static final Integer NO_OF_INPUTS=2; 

	public static void readUpperLimit(final Scanner scanner, final Point upperRightLimit) throws Exception {

		System.out.println(MESSAGE_FOR_UPPERLIMIT_INPUTS);
		String line = scanner.nextLine();
		String[] subInputs = new String[NO_OF_INPUTS];
		if (StringUtils.isNotEmpty(line)) {
			try {
				subInputs = line.split(DELIMITER);
			} catch (Exception e) {
				throw new Exception(ERROR_MESSAGE_PARSING_INPUTS);
			}
		}

		int x_coordinate = 0;
		try {
			x_coordinate = Integer.parseInt(subInputs[0]);
		} catch (NumberFormatException e) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_X);
		}
		int y_coordinate = 0;
		try {
			y_coordinate = Integer.parseInt(subInputs[1]);

		} catch (NumberFormatException e) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_Y);
		}

		if (x_coordinate <= LOWER_LEFT_LIMIT.getX()) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_X);
		}
		if (y_coordinate <= LOWER_LEFT_LIMIT.getY()) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_Y);
		}
		upperRightLimit.move(x_coordinate, y_coordinate);
	}

	private static void setUpperLimit(final String[] subInputs,final Point upperRightLimit) throws Exception {
		int x_coordinate = 0;
		try {
			x_coordinate = Integer.parseInt(subInputs[0]);
		} catch (NumberFormatException e) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_X);
		}
		int y_coordinate = 0;
		try {
			y_coordinate = Integer.parseInt(subInputs[1]);

		} catch (NumberFormatException e) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_Y);
		}

		if (x_coordinate <= LOWER_LEFT_LIMIT.getX()) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_X);
		}
		if (y_coordinate <= LOWER_LEFT_LIMIT.getY()) {
			throw new Exception(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_Y);
		}
		upperRightLimit.move(x_coordinate, y_coordinate);
		
	}

	public static void readRoverInputs(final Map<Rover, String> rovers, final Scanner scanner) throws Exception {

		Rover rover;
		int x_coordinate = 0, y_coordinate = 0;
		String line = null;
		String[] subInputs = new String[3];
		boolean moreRovers = true;
		while (moreRovers) {
			System.out.println(
					"Enter the current position of the rover.Please note the position is made up of two integers and a letter\n"
							+ "separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation");
			line = scanner.nextLine();
			if (StringUtils.isNotEmpty(line)) {
				try {
					subInputs = line.split("\\s+");
				} catch (Exception e) {
					throw new Exception(ERROR_MESSAGE_PARSING_INPUTS);
				}
			}
			try {
				x_coordinate = Integer.parseInt(subInputs[0]);

			} catch (NumberFormatException e) {
				throw new Exception(ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_X);
			}
			try {
				y_coordinate = Integer.parseInt(subInputs[1]);

			} catch (NumberFormatException e) {
				throw new Exception(ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_Y);
			}
			Direction direction = null;
			try {
				String directionString = subInputs[2];
				if (StringUtils.isNotEmpty(directionString)) {
					direction = Direction.valueOf(directionString.toUpperCase());

				}
			} catch (Exception e) {
				throw new Exception(ERROR_MESSAGE_INCORRECT_TYPE_DIRECTION);
			}
			rover = new Rover(new Point(x_coordinate, y_coordinate), direction);
			System.out.println(
					"Enter the instructions for rover to explore the plateau which should be a sequence of L,M or R ");
			String moves = scanner.nextLine();
			rovers.put(rover, moves);
			System.out.println("Do you want to continue (Y/N)");
			if (scanner.nextLine().equalsIgnoreCase("N")) {
				moreRovers = false;
			}
		}

	}
}
