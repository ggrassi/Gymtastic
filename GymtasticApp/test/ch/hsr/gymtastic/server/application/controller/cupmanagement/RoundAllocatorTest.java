package ch.hsr.gymtastic.server.application.controller.cupmanagement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.RoundAllocator;

public class RoundAllocatorTest {

	private RoundAllocator roundAllocator;
	private List<Squad> squads;
	private Squad s1;
	private Squad s2;
	private Squad s3;
	private Squad s4;
	private Squad s5;
	private Squad s6;
	private int roundNr = 1;

	@Before
	public void setUp() {
		createSquads();
		roundAllocator = new RoundAllocator(getSquads());
	}

	@Test
	public void testRoundAllocation1() {
		Squad allocatedSquad = roundAllocator.getSquad(
				DeviceType.FLOOR_EXCERCISE, roundNr);
		assertEquals(s1, allocatedSquad);
	}

	@Test
	public void testRoundAllocation2() {
		roundNr = 2;
		Squad allocatedSquad = roundAllocator.getSquad(
				DeviceType.FLOOR_EXCERCISE, roundNr);
		assertEquals(s2, allocatedSquad);
	}

	@Test
	public void getDeviceTypeBySquad() {
	DeviceType allocatedDeviceType = roundAllocator.getDeviceType(s1, roundNr);
		assertEquals(DeviceType.FLOOR_EXCERCISE, allocatedDeviceType);
		allocatedDeviceType = roundAllocator.getDeviceType(s2, roundNr);
		assertEquals(DeviceType.POMMEL_HORSE, allocatedDeviceType);
	}

	@After
	public void tearDown() {
		roundNr = 1;
	}

	public List<Squad> getSquads() {
		return squads;
	}

	private void createSquads() {
		s1 = new Squad(1);
		s2 = new Squad(2);
		s3 = new Squad(3);
		s4 = new Squad(4);
		s5 = new Squad(5);
		s6 = new Squad(6);
		fillSquads();
	}

	private void fillSquads() {
		squads = new ArrayList<Squad>();
		squads.add(s1);
		squads.add(s2);
		squads.add(s3);
		squads.add(s4);
		squads.add(s5);
		squads.add(s6);
	}

}
