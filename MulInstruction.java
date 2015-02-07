package sml;

/**
 * <p>
 * A MulInstruction is a type of instruction that can be used to process 
 * <p>
 * SML instructions from a input text file
 * <p>
 * It has a result register, and two operands stored in 2 diff registers. 
 * <>
 * It carries out a multiplication calculation of its stored operands and stores the result in the result register.
 * <p>
 * This Mul instruction makes *no* checks 
 * <p>
 * to ensure its the contents are "in bounds".
 * <p>
 * @author  (Davison Bullock)
 * <p>
 * Msc Information Technology PT.
 * @version (Feb 8th, 2015)
 */

public class MulInstruction extends Instruction{
	protected int result;
	private int op1;
	private int op2;

	/**
	 * Construct this MulInstruction with label, op1, and op2
	 *
	 * @param label2 label for SML language this mul instruction is actually directing processing to 
	 * @param result register where result of multiplication calculation will be stored 
	 * @param op1 register (first operand for calculation) this mul instruction contains
	 * @param op2 register (second operand for calculation) this mul instruction contains                                           
	 */
	public MulInstruction(String label, String op) {       
		super(label, op);
	}

	public MulInstruction(String label, int result, int op1, int op2) {
		this(label, "mul");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	/**
     * Executes this mul instruction
     * 
     * @override the instruction (superclass) execute method  
     * @param machine containing 32 registers that reads and translates SML Mul instruction
     * @return none
     */
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 * value2);
	}

	/**
     * Prints this mul instruction as a string
     * @override Object toString method with this mul instruction current state instead of default identity (reference)
     * @return string representation for instance of this mul instruction  e.g. typically "[label ="f4", opcode = "mul", result = 21, op1 = 20, op2 = 21]"
     */
	@Override
	public String toString() {
		return super.toString() + " " + op1 + " * " + op2 + " to " + result;
	}
	
}

