package com.catalyser;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;

import com.catalyser.common.Direction;

@RunWith(PowerMockRunner.class)
public class ExplorationTest extends PowerMockTestCase {

	private static final String ERROR_MESSAGE_OUTSIDE_BOUNDARY = "Incorrect movement instruction. Cannot Proceed";
	private static final String EXPECTED_MESSAGE = "Expected Output: 1 3 N";
	private static final String EXPECTED_MESSAGE_1 = "Expected Output: 1 1 S";
	private static final String EXPECTED_MULTIPLE_MESSAGE ="Expected Output: 1 3 N 5 1 E";
	private static final String INSTRUCTIONS_1 ="LMLMLMLMM";
	private static final String INSTRUCTIONS_2 ="MMRMMRMRRM";
	private static final String INSTRUCTIONS_3 ="RMRML";
	private static final Object EXPECTED_MESSAGE_2 = "Expected Output: 0 2 W";
	private static final Object EXPECTED_MESSAGE_3 = "Expected Output: 2 1 E";
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final Map<Rover, String> roverInstructionMap = new LinkedHashMap<Rover, String>();
	
	@InjectMocks
	private Exploration classToTest;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(output));
		MockitoAnnotations.initMocks(this);
	}

	@Test()
	public void testExploreWithValidInputs() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover = new Rover(new Point(1, 2), Direction.N);
		roverInstructionMap.put(rover, INSTRUCTIONS_1);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		String actual = output.toString();
		actual=actual.replaceAll("\r\n", " ");
		assertEquals(EXPECTED_MESSAGE,actual.trim());
		
	}
	
	@Test()
	public void testExploreWithMultipleValidInputs() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover1 = new Rover(new Point(1, 2), Direction.N);
		roverInstructionMap.put(rover1, INSTRUCTIONS_1);
		final Rover rover2 = new Rover(new Point(3, 3), Direction.E);
		roverInstructionMap.put(rover2, INSTRUCTIONS_2);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		String actual = output.toString();
		actual=actual.replaceAll("\r\n", " ");
		assertEquals(EXPECTED_MULTIPLE_MESSAGE,actual.trim());
		
	}

	@Test(expected = Exception.class)
	public void testExploreWithMovementOutsideBoundary() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover = new Rover(new Point(5, 5), Direction.E);
		roverInstructionMap.put(rover, INSTRUCTIONS_1);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		expected.expectMessage(ERROR_MESSAGE_OUTSIDE_BOUNDARY);

	}
	@Test()
	public void testExploreWithRoverFacingSouth() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover = new Rover(new Point(1, 2), Direction.S);
		roverInstructionMap.put(rover, INSTRUCTIONS_1);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		String actual = output.toString();
		actual=actual.replaceAll("\r\n", " ");
		assertEquals(EXPECTED_MESSAGE_1,actual.trim());
		
	}
	@Test()
	public void testExploreWithRoverFacingWest() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover = new Rover(new Point(1, 2), Direction.W);
		roverInstructionMap.put(rover, INSTRUCTIONS_1);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		String actual = output.toString();
		actual=actual.replaceAll("\r\n", " ");
		assertEquals(EXPECTED_MESSAGE_2,actual.trim());
		
	}

	@Test()
	public void testExploreWithRoverRotating() throws Exception {

		Point upperRightLimit = new Point(5, 5);
		final Rover rover = new Rover(new Point(1, 2), Direction.N);
		roverInstructionMap.put(rover, INSTRUCTIONS_3);
		classToTest = new Exploration(upperRightLimit, roverInstructionMap);
		classToTest.explore();
		String actual = output.toString();
		actual=actual.replaceAll("\r\n", " ");
		assertEquals(EXPECTED_MESSAGE_3,actual.trim());
		
	}
	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}