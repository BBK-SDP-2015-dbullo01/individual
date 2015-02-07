package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DivInstructionTest {

	Machine m;
	Translator t;;
	int resultValue;
	DivInstruction divInstruction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");;
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	@Test
	public void testToString() {
		divInstruction =  new DivInstruction("f4", 17, 20, 21);
		String str = divInstruction.toString();
		assertEquals("DivInstruction toString is not correct!", "f4: div 20 / 21 to 17" , str);
	}

	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		divInstruction = null;
	}

	@Before
	public void setUp2() throws Exception {
		m = new Machine();
		// t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
	}
	

	@Test
	public void testPosExecute() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(17);
		assertEquals("Divide calculation is not correct!", 4 , resultValue);
	}
	
	@After
	public void tearDown2() throws Exception {
		m = null;
		t = null;
		resultValue = 0;
	}
	
	
	@Before
	public void setUp3() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
	}
	
	
	@Test
	public void testDivInstructionStringString() {
		try {
			divInstruction = new DivInstruction("f4","div");
			assertNotNull("divInstruction is null!", divInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
	    divInstruction = null;
	}
	
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
	}
	
	@Test
	public void testDivInstructionStringIntIntInt() {
		try {
			DivInstruction divInstruction = new DivInstruction("f4",17,20,21);
			assertNotNull("DivInstruction is null!", divInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
	    divInstruction = null;
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
