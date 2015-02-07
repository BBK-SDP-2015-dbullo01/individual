package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AddInstructionTest {
	Machine m;
	Translator t;
	int resultValue;
	AddInstruction addInstruction;
	
	
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
		addInstruction =  new AddInstruction("f2", 22, 20, 21);
		String str = addInstruction.toString();
		assertEquals("AddInstruction toString is not correct!", "f2: add 20 + 21 to 22" , str);
	}

	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		addInstruction = null;
	}
	
	@Before
	public void setUp2() throws Exception {
		m = new Machine();
	    //t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
	}
	
	@Test
	public void testPosExecute() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(22);
		assertEquals("Add calculation is not correct!", 5 , resultValue);
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
	public void testAddInstructionStringString() {
		try {
			addInstruction = new AddInstruction("f2","add");
			assertNotNull("addInstruction is null!", addInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
		addInstruction = null;
	}
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
	}
	
	@Test
	public void testAddInstructionStringIntIntInt() {
		try {
			AddInstruction addInstruction = new AddInstruction("f2",22,20,21);
			assertNotNull("addInstruction is null!", addInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
		addInstruction = null;
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
}
