package ch.hsr.gymtastic.technicalServices.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImportStartListTest {

	public class ImportStartListStub extends ImportStartList {

		public void readImport(String line) {
			
				String splitArray[] = line.split("\t");
				List<String> importLine = new ArrayList<String>();
				for (int i = 0; i < splitArray.length; i++) {
					importLine.add(splitArray[i]);
				}
				System.out.println(importLine);
				importList.add(importLine);
			
		}

	}

	private ImportStartListStub startList;
	private String line;
	private List<String> tmp;

	@Before
	public void setUp() throws Exception {
		startList = new ImportStartListStub();
		line = "1	1	P1	Luca	Scheiwiler	Test Strasse 1	2000	TZR	sg";
		tmp = new ArrayList<String>();
		tmp.add("1");
		tmp.add("1");
		tmp.add("P1");
		tmp.add("Luca");
		tmp.add("Scheiwiler");
		tmp.add("Test");
		tmp.add("Strasse 1");
		tmp.add("2000");
		tmp.add("TZR");
		tmp.add("sg");
	}


	@Test
	public void testReadImport() {
		startList.readImport(line);
		assertEquals(startList.importList.get(0).get(0),tmp.get(0) );
	}

}
