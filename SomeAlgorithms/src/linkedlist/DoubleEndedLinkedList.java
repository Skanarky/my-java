package linkedlist;

public class DoubleEndedLinkedList {
	
	public Link firstLink;
	public Link lastLink;
	
	public DoubleEndedLinkedList() {
		
		this.firstLink = null;
		this.lastLink = null;
		
	}
	
	public boolean isEmpty() {
		
		return (this.firstLink == null);
		
	}
	
	public void displayDELinkedList() {
		
		Link oneLink = this.firstLink;
		
		if (!this.isEmpty()) {
			
			while (oneLink != null) {
				
				oneLink.displayLink();
				
				oneLink = oneLink.next;
				
			}
			
		} else {
			
			System.out.println("List is empty.");
			
		}
		
	}
	
	public void insertInFirstPosition(String name, int years) {
		
		Link aLink = new Link(name, years);
		
		if (isEmpty()) {
			
			this.lastLink = aLink;
			
		} else {
			
			this.firstLink.previous = aLink;
			
		}
		
		aLink.next = this.firstLink;
		this.firstLink = aLink;
		
	}
	
	public void insertInLastPosition(String name, int years) {
		
		Link aLink = new Link(name, years);
		
		if (isEmpty()) {
			
			this.firstLink = aLink;
			
		} else {
			
			this.lastLink.next = aLink;
			
			aLink.previous = this.lastLink; 
			
		}
		
		this.lastLink = aLink;
		
	}
	
	public boolean insertAfterKey(String name, int years, int keyPosition) {
		
		Link aLink = new Link(name, years);
		
		Link currentLink = this.firstLink;
		
		if (this.isEmpty()) {
			
			return false;

		} else {
			
			while (currentLink.yearsExperience != keyPosition) {

				currentLink = currentLink.next;
				
				if (currentLink == null) {

					return false;

				}
	
			}
			
			aLink.next = currentLink.next;
			aLink.previous = currentLink;
			currentLink.next = aLink;
	
			return true;

		}

	}
	
	public void insertInOrder(String name, int years) {
		
		Link aLink = new Link(name, years);
		
		Link prevLink = null;
		Link currentLink = this.firstLink;
		
		if (isEmpty()) {
			
			this.insertInFirstPosition(name, years);
			
		} else {
			
			while ((currentLink != null) && (years > currentLink.yearsExperience)) {
				
				prevLink = currentLink;
				currentLink = currentLink.next;
				
			}
			
			if (prevLink == null) {
				
				this.firstLink = aLink;
				
			} else {
				
				prevLink.next = aLink;
				
			}
			
			aLink.next = currentLink;
			
		}
		
	}

}
