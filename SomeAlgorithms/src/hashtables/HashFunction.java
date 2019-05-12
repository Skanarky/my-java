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
			
			// hashed index
			// modulus of 29 -> the size of our arr is 30
			int indexToInsert = Integer.parseInt(i) % 29;
			
			while (theArr[indexToInsert] != "-1") {
				
				++indexToInsert;
				System.out.println("Collision, will try index: " + indexToInsert + " instead!");
				
				// if it gets to arrSize -> go back to 0 index
				indexToInsert %= this.arrSize;
			}
			
			theArr[indexToInsert] = i;
			
		}
		
	}
	
	public String getKey(String key) {
		
		// hashed index
		int indexToInsert = Integer.parseInt(key) % 29;
		
		while (this.anArray[indexToInsert] != "-1") {
			
			if (this.anArray[indexToInsert] == key) {
				
				System.out.println(key + " was found in item with index " + indexToInsert);
				
				return this.anArray[indexToInsert];

			}
			
			
			++indexToInsert;
			
			if (indexToInsert == this.arrSize) {
				
				break;
				
			}

			System.out.println("Not found with regular hash, will try next index: " + indexToInsert + " instead!");

		}
		
		System.out.println("Key " + key + " wasn't found in the Hash Table!");
		return null;
		
	}

	public static void main(String[] args) {
		
		HashFunction oneHFunc = new HashFunction(30);

//		String[] strToAdd = {"12", "5", "19", "1", "28"};
//		oneHFunc.hashFSimpleStoreIdSameInd(strToAdd, oneHFunc.anArray);

		String[] strToAdd2 = { "100", "510", "170", "214", "268", "398",
			    "235", "802", "900", "723", "699", "1", "16", "999", "890",
			    "725", "998", "978", "988", "990", "989", "984", "320", "321",
			    "400", "415", "450", "50", "660", "624" };
		oneHFunc.hashFStoreIdCalcByMod(strToAdd2, oneHFunc.anArray);

		oneHFunc.displayTheHashF();
		
		oneHFunc.getKey("999");
		oneHFunc.getKey("401");

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
