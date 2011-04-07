package ch.hsr.gymtastic.domain;

//import static org.junit.Assert.assertEquals;

import static junit.framework.Assert.*;
import org.junit.Test;



public class MarkTest {
    Mark mark;
    
    

    @Test
    public final void testMarks() {
	
	assertEquals(4.5, mark.getdMark());
	
}

    @Test
    public void testGetFinalMark() {
	assertEquals(4.5,mark.getdMark());
    }

}
