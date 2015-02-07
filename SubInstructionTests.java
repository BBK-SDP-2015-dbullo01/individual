package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SubInstructionTests {

	Machine m;
	Translator t;
	int resultValue;
	SubInstruction subInstruction;
	
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
		subInstruction =  new SubInstruction("f5", 16, 17, 21);
		String str = subInstruction.toString();
		assertEquals("SubInstruction toString is not correct!", "f5: sub 17 - 21 to 16" , str);
	}
	
	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		subInstruction = null;
	}
	
	@Before
	public void setUp2() throws Exception {
		m = new Machine();
        //Translator t = new Translator(args[0]);
        t = new Translator("sml/SMLprogram.txt");
	}
	

	@Test
	public void testExecute() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(16);
		assertEquals("Subtraction calculation is not correct!", 3 , resultValue);
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
	public void testSubInstructionStringString() {
		subInstruction = new SubInstruction("f5","sub");
		assertNotNull("SubInstruction is null!", subInstruction);
	}
	
	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
		subInstruction = null;
	}
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
	}
	
	@Test
	public void testSubInstructionStringIntIntInt() {
		try {
			SubInstruction subInstruction = new SubInstruction("f5",16,17,21);
			assertNotNull("subInstruction is null!", subInstruction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
		subInstruction = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
