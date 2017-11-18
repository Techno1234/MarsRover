package com.catalyser.util;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;

import com.catalyser.Rover;
import com.catalyser.common.Direction;

@PrepareForTest({ Scanner.class, PositionReaderUtil.class })
@RunWith(PowerMockRunner.class)
public class PositionReaderUtilTest extends PowerMockTestCase {
	public static final String ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_X = "Incorrect upper right X coordinate;Cannot be less than or equal to 0";
	public static final String ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_Y = "Incorrect upper right Y coordinate; Cannot be less than or equal to 0";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_X = "Incorrect input for x coordinate ; Required interger";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_Y = "Incorrect input for y coordinate ; Required interger";
	public static final String ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_X = "Incorrect input for rover; x coordinate needs to be an interger";
	public static final String ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_Y = "Incorrect input for rover; y coordinate needs to be an interger";
	public static final String ERROR_MESSAGE_INCORRECT_TYPE_DIRECTION = "Incorrect input for direction; direction can be one of N/S/E/W";

	private static final String INSTRUCTIONS_1 = "LMLMLMLMM";
	private static final String INSTRUCTIONS_2 = "MMRMMRMRRM";

	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() throws Exception {

		PowerMockito.mockStatic(PositionReaderUtil.class);
		System.setOut(new PrintStream(output));

	}

	@Test
	public void readUppperLimitWithValidXY() throws Exception {

		final Point upperRightLimit = new Point();

		final StringBuffer testInputs = new StringBuffer("5 5").append("\n").append("1 2 N").append("\n")
				.append(INSTRUCTIONS_1);
		final Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		assertEquals(5, upperRightLimit.x);
		assertEquals(5, upperRightLimit.y);
	}
	@Test
	public void readUppperLimitWithValidDoubleDigitInputs() throws Exception {

		final Point upperRightLimit = new Point();

		final StringBuffer testInputs = new StringBuffer("15 10").append("\n").append("1 2 N").append("\n")
				.append(INSTRUCTIONS_1);
		final Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		assertEquals(15, upperRightLimit.x);
		assertEquals(10, upperRightLimit.y);
	}

	@Test(expected = Exception.class)
	public void readUppperLimitWithInvalidValueX() throws Exception {

		Point upperRightLimit = new Point();

		StringBuffer testInputs = new StringBuffer("0 5").append("\n").append("1 2 N").append("\n").append("LMLMLML");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_X);

	}

	@Test(expected = Exception.class)
	public void readUppperLimitWithInvalidTypeX() throws Exception {

		Point upperRightLimit = new Point();

		StringBuffer testInputs = new StringBuffer("D 0").append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_X);

	}

	@Test(expected = Exception.class)
	public void readUppperLimitWithInvalidTypeY() throws Exception {

		Point upperRightLimit = new Point();

		StringBuffer testInputs = new StringBuffer("1 D").append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_TYPE_UPPERLIMIT_Y);

	}

	@Test(expected = Exception.class)
	public void readUppperLimitWithInvalidValueY() throws Exception {

		Point upperRightLimit = new Point();

		StringBuffer testInputs = new StringBuffer("5 0").append("\n").append("1 2 N").append("\n").append("LMLMLML");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_VALUE_UPPERLIMIT_Y);
	}

	@Test
	public void testReadRoverInputsValidXY() throws Exception {

		final Point upperRightLimit = new Point();

		final StringBuffer testInputs = new StringBuffer("5 5").append("\n").append("1 2 N").append("\n")
				.append(INSTRUCTIONS_1);
		final Scanner scannerWithInputs = new Scanner(testInputs.toString());

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readUpperLimit", scannerWithInputs,
				upperRightLimit);
		PositionReaderUtil.readUpperLimit(scannerWithInputs, upperRightLimit);
		assertEquals(5, upperRightLimit.x);
		assertEquals(5, upperRightLimit.y);
	}


	@Test()
	public void testReadRoverInputs() throws Exception {

		final StringBuffer testInputs = new StringBuffer("1 2 N ").append("\n").append(INSTRUCTIONS_1).append("\n")
				.append("N").append("\n").append("\n");

		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		Iterator<Entry<Rover, String>> itr = roverInstructionMap.entrySet().iterator();
		Entry<Rover, String> entry = itr.next();
		Rover rover = entry.getKey();
		String instructions = entry.getValue();
		assertEquals(1, rover.getPoint().x);
		assertEquals(2, rover.getPoint().y);
		assertEquals(Direction.N, rover.getDirection());
		assertEquals(INSTRUCTIONS_1, instructions);

	}
	@Test()
	public void testReadRoverDoubleDigitInputs() throws Exception {

		final StringBuffer testInputs = new StringBuffer("12 24 N ").append("\n").append(INSTRUCTIONS_1).append("\n")
				.append("N").append("\n");

		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		Iterator<Entry<Rover, String>> itr = roverInstructionMap.entrySet().iterator();
		Entry<Rover, String> entry = itr.next();
		Rover rover = entry.getKey();
		String instructions = entry.getValue();
		assertEquals(12, rover.getPoint().x);
		assertEquals(24, rover.getPoint().y);
		assertEquals(Direction.N, rover.getDirection());
		assertEquals(INSTRUCTIONS_1, instructions);

	}
	@Test()
	public void testReadMultipleRoverInputs() throws Exception {

		final StringBuffer testInputs = new StringBuffer("1 2 N ").append("\n").append(INSTRUCTIONS_1).append("\n")
				.append("Y").append("\n").append("3 3 E").append("\n").append(INSTRUCTIONS_2).append("\n").append("N")
				.append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		Iterator<Entry<Rover, String>> itr = roverInstructionMap.entrySet().iterator();
		Entry<Rover, String> entry = itr.next();
		Rover rover = entry.getKey();
		String instructions = entry.getValue();
		assertEquals(1, rover.getPoint().x);
		assertEquals(2, rover.getPoint().y);
		assertEquals(Direction.N, rover.getDirection());
		assertEquals(INSTRUCTIONS_1, instructions);

		entry = itr.next();
		rover = entry.getKey();
		instructions = entry.getValue();
		assertEquals(3, rover.getPoint().x);
		assertEquals(3, rover.getPoint().y);
		assertEquals(Direction.E, rover.getDirection());
		assertEquals(INSTRUCTIONS_2, instructions);

	}
	
	@Test(expected = Exception.class)
	public void testReadRoverInvalidInputTypeX() throws Exception {

		final StringBuffer testInputs = new StringBuffer("@ @ @ @ ").append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_X);

	}

	@Test(expected = Exception.class)
	public void testReadRoverInvalidInputTypeY() throws Exception {

		final StringBuffer testInputs = new StringBuffer("0 @ N").append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_ROVER_COORDINATE_Y);

	}
	
	@Test(expected = Exception.class)
	public void testReadRoverInvalidInputTypeDirection() throws Exception {

		final StringBuffer testInputs = new StringBuffer("0 1 @").append("\n");
		Scanner scannerWithInputs = new Scanner(testInputs.toString());

		final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();

		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);

		PowerMockito.doCallRealMethod().when(PositionReaderUtil.class, "readRoverInputs", roverInstructionMap,
				scannerWithInputs);
		PositionReaderUtil.readRoverInputs(roverInstructionMap, scannerWithInputs);
		expected.expectMessage(ERROR_MESSAGE_INCORRECT_TYPE_DIRECTION);

	}


	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}