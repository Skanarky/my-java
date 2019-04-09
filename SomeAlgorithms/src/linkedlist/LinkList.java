package linkedlist;

public class LinkList {
	
	// this is the last item added to the list
	// it's called first because it's the first to go (stack?!)
	public Link firstLink;
	
	public LinkList() {
		
		this.firstLink = null;
		
	}
	
	public boolean isListEmpty() {
		
		return (this.firstLink == null);
		
	}
	
	public void insertFirstLink(String name, int years) {
		
		Link firstOne = new Link(name, years);
		
		firstOne.next = this.firstLink;
		
		this.firstLink = firstOne;
		
	}
	
	public Link removeFirstLink() {
		
		Link removedLink = this.firstLink;
		
		if (!this.isListEmpty()) {
			
			this.firstLink = this.firstLink.next;
			
		} else {
			
			System.out.println("List is empty.");
			
		}
		
		return removedLink;
		
	}
	
	public void displayLinkList() {
		
		Link oneLink = this.firstLink;
		
		if (!this.isListEmpty()) {
			
			while (oneLink != null) {
				
				oneLink.displayLink();
				
				oneLink = oneLink.next;
				
			}
			
		} else {
			
			System.out.println("List is empty.");
			
		}
		
	}
	
	public Link findALink(String name) {
		
		Link oneLink = this.firstLink;
		
		if (!this.isListEmpty()) {
			
			while (oneLink.teamPlayerName != name) {
				
				if (oneLink.next == null) {
					
					return null;
					
				} else {
					
					oneLink = oneLink.next;
					
				}
				
			}
			
		} else {
			
			return null;
			
		}
		
		return oneLink;
		
	}
	
	public Link removeALink(String name) {
		
		Link currentLink = this.firstLink;
		Link previousLink = this.firstLink;
		
		if (!this.isListEmpty()) {
			
			while (currentLink.teamPlayerName != name) {
				
				if (currentLink.next == null) {
					
					return null;
					
				} else {
					
					previousLink = currentLink;
					currentLink = currentLink.next;
					
				}
				
			}
			
			if (currentLink == this.firstLink) {
				
				this.firstLink = this.firstLink.next;
				
			} else {
				
				previousLink.next = currentLink.next;
				
			}
			
		} else {
			
			return null;
			
		}
		
		return currentLink;
		
	}

}
