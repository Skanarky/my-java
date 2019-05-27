package hashtables;

public class Word {
	
	public String aWord;
	public String definition;
	
	public int key;
	
	// linked list item
	public Word next;
	
	public Word(String inpWord, String inpDefinition) {
		
		this.aWord = inpWord;
		this.definition = inpDefinition;
		
	}
	
	public String toString() {
		
		return this.aWord + ": " + this.definition;
		
	}

}
