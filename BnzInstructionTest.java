package sml;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BnzInstructionTest {
	Machine m;
	Translator t;
	int resultValue;
	BnzInstruction bnzInstruction;
	
	
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
	public void testPosBnzExecute() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(20);
		assertEquals("Bnz calculation is not correct!", 0 , resultValue);
	}


	@After
	public void tearDown() throws Exception {
		m = null;
		t = null;
		bnzInstruction = null;
	}
	
	@Before
	public void setUp2() throws Exception {
		m = new Machine();
	    //t = new Translator(args[0]);
		t = new Translator("sml/SMLprogram.txt");
	}

	@Test
	public void testPosBnzExecuteWithMinusRegisterValue() {
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		resultValue = m.getRegisters().getRegister(1);
		assertEquals("Bnz calculation is not correct!", 0 , resultValue);
	}

	@After
	public void tearDown2() throws Exception {
		m = null;
		t = null;
		bnzInstruction = null;
	}
	
	
	@Before
	public void setUp3() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}
	
	@Test
	public void testToString() {
		bnzInstruction =  new BnzInstruction("f7", 20, "f6");
		String str = bnzInstruction.toString();
		assertEquals("BnzInstruction toString is not correct!", "f7: bnz 20 f6" , str);
	}
	
	@After
	public void tearDown3() throws Exception {
		m = null;
		t = null;
		bnzInstruction = null;
	}
	
	
	@Before
	public void setUp4() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}

	@Test
	public void testBnzInstructionStringString() {
		try {
			bnzInstruction = new BnzInstruction("f7","bnz");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown4() throws Exception {
		m = null;
		t = null;
		bnzInstruction = null;
	}
	
	@Before
	public void setUp5() throws Exception {
		m = new Machine();
		t = new Translator("sml/SMLprogram.txt");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	}
	
	@Test
	public void testBnzInstructionStringIntString() {
		try {
			bnzInstruction = new BnzInstruction("f7",20,"f6");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown5() throws Exception {
		m = null;
		t = null;
		bnzInstruction = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
