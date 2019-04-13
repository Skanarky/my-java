package linkedlist;

public class Link {
	
	public String teamPlayerName;
	public int yearsExperience;
	
	public Link next;
	
	// for doubly linked list
	public Link previous;
	
	
	public Link(String name, int years) {
		this.teamPlayerName = name;
		this.yearsExperience =  years;
	}
	
	public void displayLink() {
		System.out.println("Player: " + this.teamPlayerName + ";");
		System.out.println("Years: " + this.yearsExperience);
		System.out.println("");
	}
	
	public String toString() {
		return this.teamPlayerName;
	}

	public static void main(String[] args) {
		
		// single ended
//		LinkList oneLinkedList = new LinkList();
//		
//		oneLinkedList.insertFirstLink("David", 3);
//		oneLinkedList.insertFirstLink("John", 1);
//		oneLinkedList.insertFirstLink("Susy", 5);
//		oneLinkedList.insertFirstLink("Jake", 4);
//		
////		oneLinkedList.displayLinkList();
//		
//		System.out.println(oneLinkedList.findALink("John"));
//		
//		oneLinkedList.removeFirstLink();
////		System.out.println(oneLinkedList.findALink("Jake"));
//		
//		oneLinkedList.removeALink("John");
//		System.out.println(oneLinkedList.findALink("John"));
//		
//		oneLinkedList.displayLinkList();
		
		
		// double ended
		DoubleEndedLinkedList oneDoubleEndedLinkedList = new DoubleEndedLinkedList();
		
//		oneDoubleEndedLinkedList.insertInFirstPosition("Ken", 1);
//		oneDoubleEndedLinkedList.insertInFirstPosition("Byorg", 3);
//		oneDoubleEndedLinkedList.insertInLastPosition("Jimmy", 5);
//		oneDoubleEndedLinkedList.insertInFirstPosition("Laura", 2);
//		
//		oneDoubleEndedLinkedList.displayDELinkedList();

		oneDoubleEndedLinkedList.insertInOrder("Ken", 4);
		oneDoubleEndedLinkedList.insertInOrder("Byorg", 3);
		oneDoubleEndedLinkedList.insertInOrder("Jimmy", 5);
		oneDoubleEndedLinkedList.insertInOrder("Laura", 1);
		oneDoubleEndedLinkedList.insertInOrder("Lisa", 2);

		oneDoubleEndedLinkedList.insertAfterKey("Ilian", 2, 4);

		oneDoubleEndedLinkedList.displayDELinkedList();

		LinkIteratorDELinkList oneIteratorForDoubleELL = new LinkIteratorDELinkList(oneDoubleEndedLinkedList);
		
		oneIteratorForDoubleELL.currentLink.displayLink();
		
		System.out.println(oneIteratorForDoubleELL.hasNextLink());
		
		oneIteratorForDoubleELL.getNextLink();
		oneIteratorForDoubleELL.currentLink.displayLink();
		oneIteratorForDoubleELL.removeLink();
		oneIteratorForDoubleELL.currentLink.displayLink();

	}

}
