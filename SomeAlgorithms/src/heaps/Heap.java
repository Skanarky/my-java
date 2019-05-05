package heaps;

import java.util.Arrays;

public class Heap {
	
	private SomeData[] oneHeap;
	
	private int itemsInArray = 0;
	
	private int maxSize;
	
	public Heap(int maxSize) {
		
		this.maxSize = maxSize;
		
		this.oneHeap = new SomeData[this.maxSize];
		
	}
	
	public void genRandArr(int randomNum) {
		
		SomeData randData;
		
		for(int i = 0; i < this.maxSize; ++i) {
			
			randData = new SomeData((int) (Math.random() * randomNum) + 1);
			
			this.insert(i, randData);
			this.incremArr();
			
		}
		
	}
	
	public void insert(int index, SomeData newData) {
		
		this.oneHeap[index] = newData;
		
	}
	
	public void incremArr() {
		
		++this.itemsInArray;
		
	}
	
	public SomeData pop() {
		
		if (this.itemsInArray != 0) {
			
			SomeData root = this.oneHeap[0];
			this.oneHeap[0] = this.oneHeap[--this.itemsInArray];
			heapTheArr(0);
			
			return root;
			
		}
		
		return null;
		
	}
	
	public void heapTheArr(int index) {
		
		int largestChild;
		
		SomeData root = this.oneHeap[index];
		
		while (index < this.itemsInArray / 2) {
			
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			
			if (rightChild < this.itemsInArray && this.oneHeap[leftChild].key < this.oneHeap[rightChild].key) {
				
				largestChild = rightChild;
				
			} else {
				
				largestChild = leftChild;
				
			}
			
			if (root.key >= this.oneHeap[largestChild].key)
				break;
			
			this.oneHeap[index] = this.oneHeap[largestChild];
			
			index = largestChild;
			
		}
		
		this.oneHeap[index] = root;
		
	}
	
	public void sortHeap() {
		
		for (int i = this.maxSize - 1; i >= 0; --i) {
			
			SomeData largestNode = this.pop();
			this.insert(i, largestNode);
			
		}
		
	}

	public static void main(String[] args) {
		
		Heap theHeap = new Heap(7);
		
		theHeap.genRandArr(90);
		
		System.out.println("Original Array: ");
		System.out.println(Arrays.toString(theHeap.oneHeap));
		
		for (int i = theHeap.maxSize / 2 - 1; i >= 0; --i) {
			
			theHeap.heapTheArr(i);
			
		}
		
		System.out.println("");
		System.out.println("Heaped Array: ");
		System.out.println(Arrays.toString(theHeap.oneHeap));
		
		theHeap.sortHeap();
		
		System.out.println("");
		System.out.println("Sorted Array: ");
		System.out.println(Arrays.toString(theHeap.oneHeap));
		
	}

}
