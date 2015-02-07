package sml;

/**
 * <p>
 * A BnzInstruction is a type of instruction that can be used to process 
 * <p>
 * SML instructions from a input text file
 * <p>
 * It has a register which is checked to see if value contained is not zero, and label which switches SML processing order. 
 * <p>
 * This Bnz instruction makes check to see resister is non 0 in which case it goes to the SML instruction
 * <p>
 * indicated by the label2.
 * <p>
 * If register value is 0 value goto SML instruction with the label specified by label2 of this BnzInstruction
 * <p>
 * @author  (Davison Bullock)
 * <p>
 * Msc Information Technology PT.
 * @version (Feb 8th, 2015)
 */

public class BnzInstruction extends Instruction{

		//private int result;
		private int op1;
		private String label2;
			
		/**
		 * Construct this BnzInstruction with label, op1, and label2
		 *                                             
		 * @param op1 register this bnz instruction contains                                           
		 * @param label2 label for SML language this bnz instruction is actually directing processing to 
		 */
		
		public BnzInstruction(String label, String op) {       
			super(label, op);
		}

		public BnzInstruction(String label,  int op1, String label2) {
			this(label, "bnz");
			this.op1 = op1;
			this.label2 = label2;
		}

		/**
	     * Executes this bnz instruction
	     * 
	     * @override the instruction (superclass) execute method  
	     * @param machine containing 32 registers that  reads and translates SML Bnz instruction
	     * @return none
	     */
		@Override 
		public void execute(Machine m) {
			int value1 = m.getRegisters().getRegister(op1);
			if (!(value1 == 0)){		
				int pos = m.getLabels().indexOf(label2);
				m.setPc(pos);	
			}		
		}

		/**
	     * Prints this bnz instruction as a string
	     * @override Object toString method with this bnz instruction current state instead of default identity (reference)
	     * @return string representation for instance of this bnz instruction  e.g. typically "[label ="f4", opcode = "bnz", op1=21, label="f3"]"
	     */
		@Override 
		public String toString() {
			return super.toString() + " " + op1 + " " + label2;
		}
	
	}


