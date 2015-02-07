package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinInstructionTest {

	Machine m;
	Translator t;
	LinInstruction linInstruction;
	int resultValue;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	
	@Before
	public void setUp() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	@Test
	public void testToString() {
		linInstruction =  new LinInstruction("f1", 21, 1);
		String str = linInstruction.toString();
		assertEquals("LinInstruction toString is not correct!", "f1: lin register 21 value is 1" , str);
	}
	
	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		linInstruction = null;
	}
	
	@Before
	public void setUp2() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}
	
	
	@Test
	public void testPosExecute() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(21);
		assertEquals("Lin instruction is not correct!", 1 , resultValue);
	}
	
	@After
	public void tearDown2() throws Exception {
		m = null;
		t = null;
		linInstruction = null;
	}
	


	@Before
	public void setUp3() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	@Test
	public void testLinInstructionStringString() {
		try {
			linInstruction = new LinInstruction("f1","lin");
			assertNotNull("LinInstruction is null!", linInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
		linInstruction = null;
	}

	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	@Test
	public void testLinInstructionStringIntInt() {
		try {
			linInstruction = new LinInstruction("f1",21, 1);
			assertNotNull("LinInstruction is null!", linInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
		linInstruction = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
