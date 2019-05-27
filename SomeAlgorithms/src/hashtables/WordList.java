package hashtables;

import java.util.Scanner;

public class WordList {
	
	// linked list
	
	public Word firstWord = null;
	
	public void insertWord(Word newWord, int hashedKey) {
		
		Word prev = null;
		Word current = this.firstWord;
		
		newWord.key = hashedKey;
		
		// storing words in order
		while (current != null && newWord.key > current.key) {
			
			prev = current;
			
			current = current.next;
			
		}
		
		if (prev == null)
			this.firstWord = newWord;
		else 
			prev.next = newWord;
		
		newWord.next = current;
		
	}
	
	public void displayTheWordList() {
		
		Word current = this.firstWord;
		
		while (current != null) {
			
			System.out.println(current);
			
			current = current.next;
			
		}
		
	}
	
	public Word findWord(int hashedKey, String wordToFind) {
		
		Word current = this.firstWord;
		
		// words are in order!
		while (current != null && current.key <= hashedKey) {
			
//			if (current.key == hashedKey)
			if (current.aWord.equals(wordToFind))
				return current;
			else
				current = current.next;
			
		}
		
		return null;
		
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		HashFunctionHashStrPlusList hashTableWords = new HashFunctionHashStrPlusList(11);
		
		String wordLookUp = "";
		
		while (!wordLookUp.equalsIgnoreCase("x")) {
			System.out.println("");
			System.out.println("Look for 3-character-long words starting with an 'a': ");
			
			wordLookUp = input.nextLine();
			
			System.out.println(hashTableWords.findWord(wordLookUp));
			
		}

	}

}
