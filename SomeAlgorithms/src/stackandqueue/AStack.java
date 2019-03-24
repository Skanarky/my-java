package stackandqueue;

import java.util.Arrays;

public class AStack {
	
	private String[] stackAsArrStr;
	private int stackSize;
	
	// we need a top to execute LIFO
	private int stackTop = -1;
	
	AStack(int size) {
		this.stackSize = size;
		this.stackAsArrStr = new String[size];
		
		Arrays.fill(this.stackAsArrStr, "-1");
	}
	
	public void push(String val) {
		if (this.stackTop + 1 < this.stackSize) {
			
			this.stackTop++;
			this.stackAsArrStr[this.stackTop] = val;
			
			System.out.println(val + " was added!");
			
		} else System.out.println("No room in the stack for: " + val);
		
		this.displayTheStack();
	}
	
	public void pop() {
		if (this.stackTop >= 0) {
			
			System.out.println(this.stackAsArrStr[this.stackTop] + " was removed!");
			this.stackAsArrStr[this.stackTop] = "-1";
			this.stackTop--;
			
		} else System.out.println("No items in the stack.");
		
		this.displayTheStack();
	}
	
	public void peek() {
		if (this.stackTop >= 0) {
			
			System.out.println("The value of the stack at the top position " + this.stackTop + " is: " + this.stackAsArrStr[this.stackTop]);
			
		} else System.out.println("No items in the stack.");
		
		this.displayTheStack();
	}
	
	public void pushMultiVal(String inp) {
		String[] arrInp = inp.split(" ");
		
		for(String i : arrInp) {
			this.push(i);
		}
	}
	
	public void popAllVals() {
		if (this.stackTop >= 0) {
			
			for(int i = this.stackTop; i >= 0; --i) {
				this.pop();
			}
			
			this.stackTop = -1;
			
		} else System.out.println("No items in the stack.");
		
	}
	
	public void displayTheStack(){
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
		for(int i = 0; i < this.stackSize; ++i){
			System.out.format("| %2s "+ " ", i);
		}
		
		System.out.println("|");
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
		for(int i = 0; i < this.stackSize; ++i){
			if(this.stackAsArrStr[i].equals("-1")) System.out.print("|     ");
			else System.out.print(String.format("| %2s "+ " ", this.stackAsArrStr[i]));
		}
		
		System.out.println("|");
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
	}


	public static void main(String[] args) {

		AStack aStack = new AStack(10);
		aStack.push("5");
		aStack.push("9");
		aStack.push("7");
		aStack.push("1");
//		aStack.pop();
		aStack.peek();
//		aStack.popAllVals();
		aStack.pushMultiVal("2 4 6 10 3 8 11");
		aStack.peek();
		aStack.popAllVals();
		aStack.peek();
		
	}

}
