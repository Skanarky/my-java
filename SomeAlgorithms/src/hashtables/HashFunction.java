package hashtables;

import java.util.Arrays;

public class HashFunction {
	
	String[] anArray;
	int arrSize;
	int itemsInArr = 0;
	
	public HashFunction(int size) {
		
		this.arrSize = size;
		this.anArray = new String[size];
		// using '-1' to represent empty/default value in the array
		Arrays.fill(this.anArray, "-1");

	}

	public void hashFSimpleStoreIdSameInd(String[] inputStrings, String[] theArr) {
		
		for (String i : inputStrings) {
			
			theArr[Integer.parseInt(i)] = i;
			
		}
		
	}

	public void hashFStoreIdCalcByMod(String[] inputStrings, String[] theArr) {
		
		for (String i : inputStrings) {
			
			// modulus of 29 -> the size of our arr is 30
			int indexToInsert = Integer.parseInt(i) % 29;
			
			while (theArr[indexToInsert] != "-1") {
				
				++indexToInsert;
				
				
				
			}
			
			theArr[indexToInsert] = i;
			
		}
		
	}

	public static void main(String[] args) {
		
		HashFunction oneHFunc = new HashFunction(30);
		String[] strToAdd = {"12", "5", "19", "1", "28"};
		
		oneHFunc.hashFSimpleStoreIdSameInd(strToAdd, oneHFunc.anArray);
		
		oneHFunc.displayTheHashF();

	}
	
	public void displayTheHashF() {

        int increment = 0;

        for (int m = 0; m < 3; m++) {
            increment += 10;

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {
                System.out.format("| %3s " + " ", n);
            }

            System.out.println("|");

            for (int n = 0; n < 71; n++)
            	System.out.print("-");
            
			System.out.println();
            
			for (int n = increment - 10; n < increment; n++) {
                if (this.anArray[n].equals("-1"))
                    System.out.print("|      ");
                else
                    System.out.print(String.format("| %3s " + " ", this.anArray[n]));
            }

            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

        }

    }

}
