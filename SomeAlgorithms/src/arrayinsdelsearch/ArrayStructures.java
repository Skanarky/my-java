package arrayinsdelsearch;

public class ArrayStructures {
	
	private int[] anArray = new int[70];
	
	private int arrSize = 20;
	
	// HELPER METHODS
	
	public void genRandArr() {
		
		for(int i = 0; i < this.arrSize; ++i) {
			this.anArray[i] = (int)(Math.random()*89) + 10;
		}
		
	}
	
	private void swapVal(int indOne, int indTwo) {
		
		if (indOne >= 0 && indTwo <= this.arrSize - 1) {
			int temp = this.anArray[indOne];
			
			this.anArray[indOne] = this.anArray[indTwo];
			
			this.anArray[indTwo] = temp;
		}
		
	}
	
	public void printArr() {
		
		for(int i = 0; i < this.arrSize; ++i) {
			if (i < 10) System.out.print("| " + i);
			else System.out.print("|" + i);
			
		}
		System.out.println("|");
		
		for(int i = 0; i < this.arrSize; ++i) {
//			if (i < 10) System.out.print(" v");
			System.out.print("  v");
		}
		System.out.println("");
		
		for(int i = 0; i < this.arrSize; ++i) {
			System.out.print("|" + this.anArray[i]);
		}
		System.out.println("|");
		
	}
	
	// BASIC OPERATIONS - get, insert, delete
	
	public int getValByInd(int ind) {
		
		if (ind < this.arrSize && ind > -1) {
			System.out.println("Value at index '" + ind + "' is: " + this.anArray[ind]);
			return this.anArray[ind];
		} else {
			System.out.println("The index has to be between 0 and " + (this.arrSize - 1));
			return 0;
		}
	}
	
	public void insValue(int val) {
		
		if (this.arrSize < 35) {
			
			this.anArray[this.arrSize] = val;
			
			this.arrSize++;
			
		} else {
			System.out.println("The array is full");
		}
	}
	
	public void deleteFromIndex(int ind) {
		
		if (ind < this.arrSize && ind > -1) {
			
			for(int i = ind; i < (this.arrSize - 1); ++i) {
				this.anArray[i] = this.anArray[i + 1];
			}
			
			this.arrSize--;
			
		} else {
			System.out.println("The index has to be between 0 and " + (this.arrSize - 1));
		}
	}
	
	
	// SEARCHING
	
	public boolean searchVal(int val) {
		
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
	
	public int linearSearchVal(int val) {
		int numOfValues = 0;
		String indVal = "";
		
		for(int i = 0; i < this.arrSize; ++i) {
			if (this.anArray[i] == val) {
				numOfValues++;
				indVal += " -" + i + "-";
			}
		}
		
		System.out.println("The value " + val + " exists " + numOfValues + " time(s) in the array, at index(s):" + indVal);
		return numOfValues;
		
	}
	
	// for binary search the array needs to be sorted
	// won't find duplicates 
	public boolean binarySearch(int val) {
		boolean tester = false;
		
		int lowInd = 0;
		int highInd = this.arrSize - 1;
		int foundIn = 0;
		
		if (val > (this.anArray[lowInd] - 1) && val < (this.anArray[highInd] + 1)) {

			while(lowInd <= highInd) {
				
				int middleInd = (lowInd + highInd) / 2;			
				
				if (val > this.anArray[middleInd]) {
					lowInd = middleInd + 1;
				} else if (val < this.anArray[middleInd]) {
					highInd = middleInd - 1;
				} else {

					foundIn = middleInd;
					tester = true;
					lowInd = highInd + 1;

				}

			}

		}
		
		if (tester) {
			System.out.println(val + " was found in the array at index: " + foundIn);
		} else {
			System.out.println(val + " was not found in the array");
		}
		
		return tester;
		
	}
	
	
	// SORTING
	
	public void bubbleSortAsc() {

		for(int i = this.arrSize - 1; i > 0; --i) {

			for(int j = 0; j < i; ++j) {

				if (this.anArray[j] > this.anArray[j + 1]) {
					this.swapVal(j, j + 1);
				}

			}

		}

	}
	
	// bubble sort, descending, with a while loop; worse time-complexity?!
	public void bubbleSortDesc() {
        boolean swap = true;

        while(swap) {
            swap = false;
            for(int i = 0; i < this.arrSize - 1; ++i) {
                if(this.anArray[i] < this.anArray[i + 1]) {
                    this.swapVal(i, i + 1);
                    swap = true;
                }
            }
        }

    }
	
	// finds minimum from beginning
	public void selectionSort() {

		for(int i = 0; i < this.arrSize; ++i) {

			int min = i;

			for(int j = i + 1; j < this.arrSize; ++j) {

				if(this.anArray[min] > this.anArray[j]) {
                    min = j;
                }

			}

			if (min != i) this.swapVal(i, min);

        }

	}

	// with the other sort algorithms, after running them, the array is sorted partially at any moment
	// not with this one.
	// it's really fast if the input array is partially sorted, but if there's a small value
	// at the end of the list it get's slower
	public void insertionSort() {
		
		for(int i = 1; i < this.arrSize; ++i) {
			
			int j = i;
			int valToInsert = this.anArray[i];
			
			while(j > 0 && this.anArray[j - 1] > valToInsert) {
				
				this.anArray[j] = this.anArray[j - 1];
				// OR swap, because it's only the value which are next to each other
				// this.swapVal(j - 1, j);
				--j;
				
			}
			
			this.anArray[j] = valToInsert;
			
		}
		
	}
	
	// fixes problems of insertion sort, when one of the smaller values is all the way at the end (get's
	// slower), shell sort partially sorts the array  first using "interval"
	// and then becomes insertion sort;
	// maybe the most recommended, since quick sort is less efficient with smaller arrays
	public void shellSort() {
		
		int inner, outer, valToInsert;
		
		// depends on array size, optimization for interval size
		int interval = 1;
		while (interval <= this.arrSize / 3) {
			
			interval = interval * 3 + 1;

		}

		while (interval > 0) {
			
			for (outer = interval; outer < this.arrSize; ++outer) {
				
				valToInsert = this.anArray[outer];
				
				inner = outer;
				
				while (inner > interval - 1 && this.anArray[inner - interval] >= valToInsert) {
					
					// can't use this.swapVal(indOne, indTwo) -> it messes the algorithm when values are 
					// equal because these are not values 
					// next to each other
					this.anArray[inner] = this.anArray[inner - interval];
					
					inner -= interval;
					
				}
				
				this.anArray[inner] = valToInsert;
				
			}
			
			interval = (interval - 1) / 3;
			
		}
		
	}
	
	// the fastest sorting alg. -> works by partitioning the array (pivot value)
	public void quickSort(int left, int right) {
		
		if (right - left <= 0) {
			return;
		} else {
			int pivot = this.anArray[right];
			
			int pivotLocation = this.partitionArr(left, right, pivot);
			
			this.quickSort(left, pivotLocation - 1);
			this.quickSort(pivotLocation + 1, right);
		}
		
	}
	
	public int partitionArr(int left, int right, int pivot) {
		
		// '- 1' because we increment directly in the while loop condition
		int leftPointer = left - 1;
		int rightPointer = right;
		
		while (true) {
			
			while (leftPointer < this.arrSize - 1 && this.anArray[++leftPointer] < pivot);
			
			while (rightPointer > 0 && this.anArray[--rightPointer] > pivot);
			
			if (leftPointer >= rightPointer) {
				break;
			} else {
				this.swapVal(leftPointer, rightPointer);
			}
			
		}
		
		this.swapVal(leftPointer, right);
		
		return leftPointer;
		
	}

	public static void main(String[] args) {
		
		ArrayStructures arr = new ArrayStructures();
		
		arr.genRandArr();
		
		arr.printArr();
		
		System.out.println("");
		
//		arr.getValByInd(5);
//		
//		arr.getValByInd(-11);
//		
//		arr.searchVal(8);
//		
//		arr.deleteFromIndex(5);
//		System.out.println("");
//		arr.printArr();
//		
//		arr.insValue(99);
//		System.out.println("");
//		arr.printArr();
		
//		arr.linearSearchVal(5);
		
//		arr.bubbleSortAsc();
//		arr.bubbleSortDesc();
//		arr.printArr();
		
		// has to be sorted for binary search
//		arr.bubbleSortAsc();
//		arr.printArr();
//		arr.binarySearch(5);
		
//		arr.selectionSort();
//		arr.printArr();
		
//		arr.insertionSort();
//		arr.printArr();
		
//		arr.quickSort(0, arr.arrSize - 1);
//		arr.printArr();
		
		arr.shellSort();
		arr.printArr();

	}

}
