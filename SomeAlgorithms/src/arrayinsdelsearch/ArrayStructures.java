package arrayinsdelsearch;

public class ArrayStructures {
	
	private int[] anArray = new int[35];
	
	private int arrSize = 10;
	
	public void genRandArr() {
		
		for(int i = 0; i < this.arrSize; ++i) {
			this.anArray[i] = (int)(Math.random()*10);
		}
		
	}
	
	public void printArr() {
		
		for(int i = 0; i < this.arrSize; ++i) {
			System.out.print("|" + i);
		}
		System.out.println("|");
		
		for(int i = 0; i < this.arrSize; ++i) {
			System.out.print(" v");
		}
		System.out.println("");
		
		for(int i = 0; i < this.arrSize; ++i) {
			System.out.print("|" + this.anArray[i]);
		}
		System.out.println("|");
		
	}
	
	public int getValByInd(int ind) {
		
		if (ind < this.arrSize && ind > -1) {
			System.out.println("Value at index '" + ind + "' is: " + this.anArray[ind]);
			return this.anArray[ind];
		} else {
			System.out.println("The index has to be between 0 and " + (this.arrSize - 1));
			return 0;
		}
	}
	
	public boolean containVal(int val) {
		
		boolean tester = false;
		
		for(int i = 0; i < this.arrSize; ++i) {
			if (this.anArray[i] == val) {
				tester = true;
				break;
			}
		}
		
		if (tester) {
			System.out.println(val + " was found in the array");
		} else {
			System.out.println(val + " was not found in the array");
		}
		
		return tester;
	}

	public static void main(String[] args) {
		
		ArrayStructures arr = new ArrayStructures();
		
		arr.genRandArr();
		
		arr.printArr();
		
		System.out.println("");
		
		arr.getValByInd(5);
		
		arr.getValByInd(-11);
		
		arr.containVal(8);

	}

}
