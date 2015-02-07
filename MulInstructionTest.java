package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MulInstructionTest {

	Machine m;
	Translator t;
	int resultValue;
	MulInstruction mulInstruction;
	
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
		mulInstruction =  new MulInstruction("f3", 18, 20, 21);
		String str = mulInstruction.toString();
		assertEquals("mulInstruction toString is not correct!", "f3: mul 20 * 21 to 18" , str);
	}
	
	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		mulInstruction = null;
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
		resultValue = m.getRegisters().getRegister(18);
		assertEquals("Multiplication calculation is not correct!", 4 , resultValue);
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
		// t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
	}
	
	
	@Test
	public void testMulInstructionStringString() {
		try {
			mulInstruction = new MulInstruction("f3","mul");
			assertNotNull("MulInstruction is null!", mulInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
		mulInstruction = null;
	}
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		// t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
	}
	
	@Test
	public void testMulInstructionStringIntIntInt() {
		try {
			mulInstruction = new MulInstruction("f3",18,20,21);
			assertNotNull("mulInstruction is null!", mulInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
		mulInstruction = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
