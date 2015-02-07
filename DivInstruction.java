package sml;

/**
 * <p>
 * A DivInstruction is a type of instruction that can be used to process 
 * <p>
 * SML instructions from a input text file
 * <p>
 * It has a result register, and two operands stored in 2 diff registers. 
 * <p>
 * It carries out an integer division calculation using its stored operands and stores result in the result register.
 * <p>
 * This Div instruction makes *no* checks to ensure its the contents are "in bounds".
 * <p>
 * @author  (Davison Bullock)
 * <p>
 * Msc Information Technology PT.
 * @version (Feb 8th, 2015)
 */

public class DivInstruction extends Instruction {
	private int result;
	private int op1;
	private int op2;
 
	/**
	 * Construct this DivInstruction with label, op1, and op2
	 *
	 * @param label2 label for SML language this div instruction is actually directing processing to 
	 * @param result register where result of division calculation will be stored 
	 * @param op1 register (first operand for calculation) this div instruction contains
	 * @param op2 register (second operand for calculation) this div instruction contains                                           
	 */
	public DivInstruction(String label, String op) {       
		super(label, op);
	}

	public DivInstruction(String label, int result, int op1, int op2) {
		this(label, "div");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	/**
     * Executes this div instruction
     * 
     * @override the instruction (superclass) execute method  
     * @param machine containing 32 registers that reads and translates SML Div instruction
     * @return none
     */
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 / value2);
	}

	/**
     * Prints this div instruction as a string
     * @override Object toString method with this div instruction current state instead of default identity (reference)
     * @return string representation for instance of this div instruction  e.g. typically "[label ="f4", opcode = "div", result = 21, op1 = 20, op2 = 21]"
     */
	
	@Override
	public String toString() {
		return super.toString() + " " + op1 + " / " + op2 + " to " + result;
	}
	
}

