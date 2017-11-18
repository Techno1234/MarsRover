package com.catalyser;

import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.catalyser.util.PositionReaderUtil;

public class Main {

	public static void main(String[] args) {
		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();
		final Point upperRightLimit = new Point();
		
		try (Scanner scanner = new Scanner(System.in)) {
			readInputs(scanner, upperRightLimit, roverInstructionMap);
			Exploration exploration = new Exploration(upperRightLimit, roverInstructionMap);
			exploration.explore();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void readInputs(final Scanner scanner, final Point upperRightLimit,
			final Map<Rover, String> roverInstructionMap) throws Exception {
		PositionReaderUtil.readUpperLimit(scanner, upperRightLimit);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scanner);
	}

}
