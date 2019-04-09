package linkedlist;

public class Link {
	
	public String teamPlayerName;
	public int yearsExperience;
	
	public Link next;
	
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
		
		LinkList oneLinkedList = new LinkList();
		
		oneLinkedList.insertFirstLink("David", 3);
		oneLinkedList.insertFirstLink("John", 1);
		oneLinkedList.insertFirstLink("Susy", 5);
		oneLinkedList.insertFirstLink("Jake", 4);
		
//		oneLinkedList.displayLinkList();
		
		System.out.println(oneLinkedList.findALink("John"));
		
		oneLinkedList.removeFirstLink();
//		System.out.println(oneLinkedList.findALink("Jake"));
		
		oneLinkedList.removeALink("John");
		System.out.println(oneLinkedList.findALink("John"));
		
		oneLinkedList.displayLinkList();

	}

}
