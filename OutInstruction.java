package sml;

/**
 * <p>
 * A OutInstruction is a type of instruction that can be used to process 
 * <p>
 * SML instructions from an input text file
 * <p>
 * It has a register whose value is output to the console. 
 * <p>
 * This Out instruction makes *no* checks to ensure its the contents are "in bounds".
 * <p>
 * @author  (Davison Bullock)
 * <p>
 * Msc Information Technology PT.
 * @version (Feb 8th, 2015)
 */

public class OutInstruction extends Instruction{
	private int op1;
	
	/**
	 * Construct this OutInstruction with op1
	 *
	 * @param op1 register containing value this out instruction will print to console                                           
	 */
	public OutInstruction(String label, String op) {       
		super(label, op);
	}

	public OutInstruction(String label, int op1) {
		this(label, "out");
		this.op1 = op1;
	}

	/**
     * Executes this out instruction
     * 
     * @override the instruction (superclass) execute method  
     * @param machine containing 32 registers that reads and translates SML Out instruction
     * @return none
     */
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		System.out.println(this.toString() + " = " + value1);
	}

	/**
     * Prints this out instruction as a string
     * @override Object toString method with this out instruction current state instead of default identity (reference)
     * @return string representation for instance of this out instruction  e.g. typically "[label ="f4", opcode = "out", op1 = 21]"
     */
	
	@Override
	public String toString() {
		return super.toString() + " " + op1;
	}
	
	public int getOp1(){
		return this.op1;
	}
}
