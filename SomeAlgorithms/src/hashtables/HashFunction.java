package hashtables;

import java.util.ArrayList;
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

	public static void main(String[] args) {
		
		HashFunction oneHFunc = new HashFunction(30);

//			String[] strToAdd = {"12", "5", "19", "1", "28"};
//			oneHFunc.hashFSimpleStoreIdSameInd(strToAdd, oneHFunc.anArray);

		String[] strToAdd2 = { "100", "510", "170", "214", "268", "398",
			    "235", "802", "900", "723", "699", "1", "16", "999", "890",
			    "725", "998", "978", "988", "990", "989", "984", "320", "321",
			    "400", "415", "450", "50", "660", "624" };
		oneHFunc.hashFStoreIdCalcByMod(strToAdd2, oneHFunc.anArray);
//
		oneHFunc.displayTheHashF();
//		
		oneHFunc.getKey("999");
		oneHFunc.getKey("401");

//		oneHFunc.increaseArrS(31);
//		
//		oneHFunc.increaseArrS(30);
		oneHFunc.increaseArrS(40);
		
		oneHFunc.displayTheHashF();
		
		oneHFunc.getKeyDoubleHash("999");
		oneHFunc.getKeyDoubleHash("624");
		oneHFunc.getKeyDoubleHash("401");
		oneHFunc.getKeyDoubleHash("2");

	}
	
	public void displayTheHashF() {

        int increment = 0;
        
        int numberOfRows = (this.arrSize / 10) + 1;

        for (int m = 0; m < numberOfRows; m++) {
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
				
				if (n >= this.arrSize)
					System.out.print("|      ");

				else if (this.anArray[n].equals("-1"))
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
	
	// PART I - basic and using modulus
	public void hashFSimpleStoreIdSameInd(String[] inputStrings, String[] theArr) {
		
		for (String i : inputStrings) {
			
			theArr[Integer.parseInt(i)] = i;
			
		}
		
	}

	public void hashFStoreIdCalcByMod(String[] inputStrings, String[] theArr) {
		
		for (String i : inputStrings) {
			
			// hashed index
			// modulus of 29 -> the size of our arr will be 30
			int indexToInsert = Integer.parseInt(i) % 29;
//			OR
//			int indexToInsert = Integer.parseInt(i) % (this.arrSize - 1);
			
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
	
	// PART II
	// -> using prime numbers for size to lower amount of collisions
	// (random follows pattern - in nature, which is not prime)
	// -> using double hash (a step) for smaller clusters of filled spots (next to each other)
	// (if storing 10 items, make the size of the map 19 or 23 (prime + double the amount) - plan for double the size)
	public boolean isPrime(int num) {
		
		if (num % 2 == 0)
			return false;
		
		for (int i = 3; i * i <= num; i += 2) {
			
			if (num % i == 0)
				return false;
			
		}
		
		return true;
		
	}
	
	public int getNextPrimeNum(int numCheck) {

		for (int i = numCheck; true; i++) {

			if (this.isPrime(i))
				System.out.println("In prime fn: " + i);
				return i;

		}

	}

	public void increaseArrS(int minSize) {
		
		if (this.arrSize < minSize) {
			
			int newSize = this.getNextPrimeNum(minSize);
			System.out.println("newSize: " + newSize);

			moveArrToDiffSize(newSize);
			
		} else {
			
			System.out.println("The array size is bigger than proposed new size!");
			
		}

	}

	public void moveArrToDiffSize(int newSize) {

		String[] cleanArr = removeEmptySlots(this.anArray);

		this.arrSize = newSize;
		this.anArray = new String[newSize];

		Arrays.fill(this.anArray, "-1");

		this.hashFStoreIdCalcWithPrimeAndDubleHash(cleanArr, this.anArray);

	}

	public String[] removeEmptySlots(String[] arrToClean) {

		ArrayList<String> tempList = new ArrayList<String>();

		for (String i : arrToClean) {

			if (!i.equals("-1") && !i.equals("")) {

				tempList.add(i);

			}

		}

		return tempList.toArray(new String[tempList.size()]);

	}

	public void hashFStoreIdCalcWithPrimeAndDubleHash(String[] inputStrings, String[] theArr) {

		for (String i : inputStrings) {

			// hashed index (double hashed with a step below)
			// modulus of array size (which is a Prime num now)
			int indexToInsert = Integer.parseInt(i) % (this.arrSize -1);
			
			// adding randomizing to avoid clusters of filled spots (good for big maps/arrays)
			// use a prime number (like 5)
			// so in next 1, 2, 3... 7 spots
			int stepDistance = 5 - (Integer.parseInt(i) % 5);

			while (theArr[indexToInsert] != "-1") {
				int tempToShow = indexToInsert;
				indexToInsert += stepDistance;
				System.out.println("Collision in "+ tempToShow + "th index, will try " + indexToInsert + "th instead!");

				// if it gets to arrSize -> go back to 0 index
				indexToInsert %= this.arrSize;
			}

			theArr[indexToInsert] = i;

		}

	}
	
	public String getKeyDoubleHash(String key) {
		
		// double hashed index
		int indexToInsert = Integer.parseInt(key) % (this.arrSize - 1);
		
		int stepDistance = 5 - (Integer.parseInt(key) % 5);
		
		while (this.anArray[indexToInsert] != "-1") {
			
			if (this.anArray[indexToInsert] == key) {
				
				System.out.println(key + " was found in item with index " + indexToInsert);
				
				return this.anArray[indexToInsert];

			}
			
			indexToInsert += stepDistance;
			
			if (indexToInsert >= this.arrSize) {
				
				break;
				
			}

			System.out.println("Not found with regular hash, will try index: " + indexToInsert + " instead!");

		}
		
		System.out.println("Key " + key + " wasn't found in the Hash Table!");
		return null;
		
	}

}
