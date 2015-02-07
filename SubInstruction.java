package sml;
/**
 * <p>
 * A SubInstruction is a type of instruction that can be used to process 
 * <p>
 * SML instructions from an input text file
 * <p>
 * It has a result register, and two operands stored in 2 diff registers. 
 * <p>
 * It carries out a subtraction calculation of its stored operands and stores the result in the result register.
 * <p>
 * This Sub instruction makes *no* checks to ensure its the contents are "in bounds".
 * <p>
 * @author  (Davison Bullock)
 * <p>
 * Msc Information Technology PT.
 * @version (Feb 8th, 2015)
 */


public class SubInstruction extends Instruction {
	private int result;
	private int op1;
	private int op2;
	
	/**
	 * Construct this SubInstruction with label, op1, and label2
	 *
	 * @param label2 label for SML language this sub instruction is actually directing processing to 
	 * @param result register where result of subtraction calculation will be stored 
	 * @param op1 register (first operand for calculation) this sub instruction contains
	 * @param op2 register (second operand for calculation) this sub instruction contains                                           
	 */
	
	public SubInstruction(String label, String op) {       
		super(label, op);
	}

	public SubInstruction(String label, int result, int op1, int op2) {
		this(label, "sub");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 - value2);
	}
	
	/**
     * Prints this sub instruction as a string
     * @override Object toString method with this sub instruction current state instead of default identity (reference)
     * @return string representation for instance of this sub instruction  e.g. typically "[label ="f4", opcode = "sub", result = 21, op1 = 20, op2 = 21]"
     */
	@Override
	public String toString() {
		return super.toString() + " " + op1 + " - " + op2 + " to " + result;
	}
}
