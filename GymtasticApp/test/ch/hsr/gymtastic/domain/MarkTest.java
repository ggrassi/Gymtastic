package ch.hsr.gymtastic.domain;

//import static org.junit.Assert.assertEquals;

import static junit.framework.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MarkTest {
    Mark mark;
    Mark emptyMark;

    @Before
    public void runBeforeEveryTest() {
	mark = new Mark(4.5, 4.3, 3.4, 4.5, 0.2, 0.5);
	emptyMark = new Mark(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @After
    public void runAfterEveryTest() {
    }

    @Test
    public final void testDMarksAndEMarks() {

	assertEquals(4.5, mark.getdMark());
	assertEquals(4.3, mark.geteMarkOne());
	assertEquals(3.4, mark.getEmarkTwo());
	assertEquals(4.5, mark.geteMarkThree());
    }

    @Test
    public final void testPenalty() {
	assertEquals(0.2, mark.getPenalty());
    }

    @Test
    public final void testBonus() {
	assertEquals(0.5, mark.getBonus());
    }

    @Test
    public void testGetFinalMark() {
	assertEquals(8.866666666666667, mark.getFinalMark());
    }
    
    @Test
    public void testEmptyMark() {
	assertEquals(0.0, emptyMark.getFinalMark());
    }

}
