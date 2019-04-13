package linkedlist;

public class LinkIteratorDELinkList {
	
	Link currentLink;
	Link prevLink;
	
	DoubleEndedLinkedList doubleEndedLL;
	
	public LinkIteratorDELinkList(DoubleEndedLinkedList dELL) {
		
		this.doubleEndedLL = dELL;
		this.currentLink = dELL.firstLink;
		this.prevLink = dELL.lastLink;

	}
	
	public boolean hasNextLink() {
		
		return (this.currentLink.next != null);
		
	}
	
	public Link getNextLink() {

		if (this.hasNextLink()) {

			this.prevLink = this.currentLink;
			this.currentLink = this.currentLink.next;

			return this.currentLink;

		}

		return null;

	}
	
	public void removeLink() {
		
		if (this.prevLink == null) {
			
			this.doubleEndedLL.firstLink = this.currentLink.next;
			
		} else {
			
			this.prevLink.next = this.currentLink.next;
			
			if (this.currentLink.next == null) {
				
				this.currentLink = this.doubleEndedLL.firstLink;
				this.prevLink = null;
				
			} else {
				
				this.currentLink = this.currentLink.next;
				
			}
			
		}
		
	}

}
