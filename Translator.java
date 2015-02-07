package sml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
		// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();				
			program = prog;
			program.clear();
			
			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	public Instruction getInstruction(String label) {               
		int s1; // Possible operands of the instruction              
		int s2;
		int r;
		int x;

		if (line.equals(""))
			return null;
		
		String ins = scan();
		
		//Reflection to replace switch statement
		StringBuilder sb = new StringBuilder();
		sb.append("sml.");
		sb.append(ins.toUpperCase().charAt(0));
		sb.append(ins.substring(1));
		sb.append("Instruction");
		
		
		Class<?> c = null;
		
		try{
			c = Class.forName(sb.toString());
		}
	    catch(ClassNotFoundException e){
	    	e.printStackTrace();
	    }
		
		Constructor<?> con = null;
					
		Field[] fields = c.getDeclaredFields();
		
		ArrayList<Class<?>> arrayListTypes = new ArrayList<>();
		Field[] fieldsInClass = c.getDeclaredFields();
		for(Field fieldInClass : fieldsInClass){
			arrayListTypes.add(fieldInClass.getType());
		}
		
		Class<?> c1;
		Class<?> c2;
		Class<?> c3;
		
		//Call non default constructor of class with 3 declared fields
		if (fields.length == 3){
			 r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			
			c1 = arrayListTypes.get(0);
			c2 = arrayListTypes.get(1);
			c3 = arrayListTypes.get(2);
		
			try {
				con = c.getConstructor(new Class[]{String.class, c1, c2, c3});   // c1, c2, c3 done because was thinking about extensibility
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		
			try{	
				return (Instruction) con.newInstance(new Object[] { label, r,  s1, s2});  
				//Was trying to see if I could make extensible also. 
				//Was thinking object instance parameter values could be stored and retrieved from an arrayList of type <Class<?>  
				//when obtained from the constructor and retrieved and used above 
			} catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		
		// Call non default constructor of class with 1 declared field
		if (fields.length == 1){
			s1 = scanInt(); 
			c1 = arrayListTypes.get(0);
			try {
				con = c.getConstructor(new Class[]{String.class, c1});
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
				
			try{
				return (Instruction) con.newInstance(new Object[] {label, s1});
			} catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		
		// Call non default constructor of class with 2 declared fields
		if (fields.length == 2){
			c1 = arrayListTypes.get(0);
			c2 = arrayListTypes.get(1);
			
			try {
				con = c.getConstructor(new Class[]{String.class, c1, c2});
			}catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			
			if (c1 == int.class && c2 == int.class){ //Lin
				s1 = scanInt();
				s2 = scanInt();
				
				try{		
					return (Instruction) con.newInstance(new Object[] {label, s1, s2});			    
				} catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException e1) {				
					e1.printStackTrace();
				}
			}
			
			if(c1 == int.class && c2 == String.class){ //Bnz
				s1 = scanInt();
							
				Scanner sc2 = new Scanner(line);
				String label2 = sc2.next();
				sc2.close();
				try{	
				    return (Instruction) con.newInstance(new Object[] {label, s1, label2});
				} catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
				
			}	
		}
		
		return null;
		
		//*** PREVIOUS 2 declared field constructor attempt **** 
		
		//Field op1Field;
	    //Integer op1Value;
	    //Field registerField;
	    //Integer registerValue;
	    //Field valueField;
	    //Integer valueValue;
	    //Field label2Field;
	    //String label2Value;
		
		//if (c1 == int.class && c2 == int.class){   //Lin
		//	s1 = scanInt();
		//	s2 = scanInt();
			
		//	//Class p = linInstruction.getClass();
		//	try{
		//		LinInstruction linInstruction = new LinInstruction(label,s1, s2);
		//		registerField = LinInstruction.class.getDeclaredField("register");
		//		registerField.setAccessible(true);
		//		registerValue = (Integer) registerField.get(linInstruction);
		//		//System.out.println("register: " + registerField.toString());    //DEBUG
		//		valueField = LinInstruction.class.getDeclaredField("value");
		//		valueField.setAccessible(true);
		//		valueValue = (Integer) valueField.get(linInstruction);
		//		//System.out.println("value: " + valueField.toString());  		//DEBUG
		//		return (Instruction) con.newInstance(new Object[] {label, registerValue, valueValue});
		//	}catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException | NoSuchFieldException e1) {
		//		e1.printStackTrace();
		//	}
		//}
		
		//if(c1 == int.class && c2 == String.class){ //Bnz
		//	s1 = scanInt();
		//	Scanner sc3 = new Scanner(line);
		//	String label2 = sc3.next();
		//	try{
		//		BnzInstruction bnzInstruction = new BnzInstruction(label,s1,label2); 
		//		op1Field = BnzInstruction.class.getDeclaredField("op1");
		//		op1Field.setAccessible(true);
		//	    op1Value = (Integer) op1Field.get(bnzInstruction);
		//	    //System.out.println("op1: " + op1Field.toString());				//DEBUG
		//	    label2Field = BnzInstruction.class.getDeclaredField("label2");
		//	    label2Field.setAccessible(true);
		//	    label2Value = (String) label2Field.get(bnzInstruction);
		//	    //System.out.println("value: " + label2Field.toString());			//DEBUG
		//	    return (Instruction) con.newInstance(new Object[] {label, op1Value, label2Value});
		//	} catch (InstantiationException | IllegalArgumentException |IllegalAccessException | InvocationTargetException | NoSuchFieldException e1) {
		//		e1.printStackTrace();
		//	}
		//}	
	//}		
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}