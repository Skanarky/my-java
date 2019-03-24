package stackandqueue;

import java.util.Arrays;

public class AQueue {
	
	private String[] queueAsArrStr;
	private int queueSize;
	
	// we need a front, rear and numberOfItems to execute FIFO
	private int front, rear, numberOfItems = 0;
	
	AQueue(int size) {
		this.queueSize = size;
		this.queueAsArrStr = new String[size];
		
		Arrays.fill(this.queueAsArrStr, "-1");
	}
	
	public void insert(String val) {
		if (this.numberOfItems + 1 <= this.queueSize) {
			
			this.queueAsArrStr[this.rear] = val;
			this.rear++;
			this.numberOfItems++;
			
			System.out.println(val + " was added!");
			
		} else System.out.println("No room in the queue for: " + val);
		
		this.displayTheQueue();
	}
	
	public void remove() {
		if (this.numberOfItems > 0) {
			
			System.out.println(this.queueAsArrStr[this.front] + " was removed!");
			this.queueAsArrStr[this.front] = "-1";
			this.front++;
			this.numberOfItems--;
			
		} else System.out.println("No items in the queue.");
		
		this.displayTheQueue();
	}
	
	// from high to low -> FIFO
	public void priorityInsert(String val) {
		int i = this.rear - 1;
		
		if (this.numberOfItems + 1 <= this.queueSize) {
			
			if (this.numberOfItems == 0) {
				this.insert(val);
			} else {
				
				for(i = this.rear - 1; i >= 0; --i) {
					
					if(Integer.parseInt(val) > Integer.parseInt(this.queueAsArrStr[i])) {
						this.queueAsArrStr[i + 1] = this.queueAsArrStr[i];
					} else break;
					
				}
				
				System.out.println(val + " was added!");
				
				this.queueAsArrStr[i + 1] = val;
				this.rear++;
				this.numberOfItems++;
				
			}
			
		} else System.out.println("No room in the queue for: " + val); 
		
		this.displayTheQueue();
		
	}
	
	public void peek() {
		if (this.numberOfItems > 0) {
			
			System.out.println("The value of the queue at the first position " + this.front + " is: " + this.queueAsArrStr[this.front]);
			
		} else System.out.println("No items in the queue.");
		
		this.displayTheQueue();
	}
	
	public void displayTheQueue(){
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
		for(int i = 0; i < this.queueSize; ++i){
			System.out.format("| %2s "+ " ", i);
		}
		
		System.out.println("|");
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
		for(int i = 0; i < this.queueSize; ++i){
			if(this.queueAsArrStr[i].equals("-1")) System.out.print("|     ");
			else System.out.print(String.format("| %2s "+ " ", this.queueAsArrStr[i]));
		}
		
		System.out.println("|");
		
		System.out.print(" ");
		for(int i = 0; i < 59; ++i) System.out.print("-");
		
		System.out.println();
		
	}

	public static void main(String[] args) {
		
		AQueue aQueue = new AQueue(10);
		
//		aQueue.insert("5");
//		aQueue.insert("8");
//		aQueue.insert("1");
		
		aQueue.priorityInsert("5");
		aQueue.priorityInsert("8");
		aQueue.priorityInsert("4");
		aQueue.priorityInsert("9");
		
		aQueue.peek();
//		aQueue.remove();
//		aQueue.remove();
//		aQueue.remove();
//		aQueue.peek();
		
	}

}
