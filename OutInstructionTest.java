package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OutInstructionTest {
	
	Machine m;
	Translator t;
	int resultValue;
	OutInstruction outInstruction;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		m = new Machine();
	    //t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
		
	}

	@Test
	public void testToString() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(16);
		assertEquals("Out instruction toString method is not correct!", "f8: out 16 = 3" , "f8: out 16 = " + resultValue);

	}
	
	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		resultValue = 0;
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
		resultValue = m.getRegisters().getRegister(16);
		assertEquals("Out instruction is not correct!", 3 , resultValue);
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
	    //t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	
	@Test
	public void testOutInstructionStringString() {
		try {
			outInstruction = new OutInstruction("f8","out");
			assertNotNull("OutInstruction is null!", outInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
	}
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
	    //t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}
	
	@Test
	public void testOutInstructionStringInt() {
		try {
			outInstruction = new OutInstruction("f8",16);
			assertNotNull("OutInstruction is null!", outInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
