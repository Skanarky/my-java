package hashtables;

public class HashFunctionHashStrPlusList {

	// can be used for auto completion, dictionaries, spell checker, etc.

	WordList[] anArray;
	int arrSize;
	int itemsInArr = 0;
	
	public String[][] itemsToAdd = {
            { "ace", "Very good" },
            { "act", "Take action" },
            { "add", "Join (something) to something else" },
            { "age", "Grow old" },
            { "ago", "Before the present" },
            { "aid", "Help, assist, or support" },
            { "aim", "Point or direct" },
            { "air", "Invisible gaseous substance" },
            { "all", "Used to refer to the whole quantity" },
            { "amp",
                    "Unit of measure for the strength of an electrical current" },
            { "and", "Used to connect words" }, { "ant", "A small insect" },
            { "any", "Used to refer to one or some of a thing" },
            { "ape", "A large primate" },
            { "apt", "Appropriate or suitable in the circumstances" },
            { "arc", "A part of the circumference of a curve" },
            { "are", "Unit of measure, equal to 100 square meters" },
            { "ark", "The ship built by Noah" },
            { "arm", "Two upper limbs of the human body" },
            { "art", "Expression or application of human creative skill" },
            { "ash", "Powdery residue left after the burning" },
            { "ask", "Say something in order to obtain information" },
            { "asp", "Small southern European viper" },
            { "ass", "Hoofed mammal" },
            { "ate", "To put (food) into the mouth and swallow it" },
            { "atm", "Unit of pressure" },
            { "awe", "A feeling of reverential respect" },
            { "axe", "Edge tool with a heavy bladed head" },
            { "aye", "An affirmative answer" }
    };

	public int hashStringFnForKey(String str) {

		int keyHashVal = 0;

		for (int i = 0; i < str.length(); ++i) {

			int theCharCode = str.charAt(i) - 96;

			keyHashVal = (keyHashVal * 27 + theCharCode) % this.arrSize;

		}

		return keyHashVal;

	}
	
	HashFunctionHashStrPlusList(int size) {
		
		this.arrSize = size;
		this.anArray = new WordList[size];
		
		for (int i = 0; i < this.arrSize; ++i) {
			
			this.anArray[i] = new WordList();
			
		}
		
		addSomeArr(this.itemsToAdd);
		
	}
	
	public void insertWord(Word newWord) {
		
		String wordToHash = newWord.aWord;
		
		int hashedKey = this.hashStringFnForKey(wordToHash);
		
		this.anArray[hashedKey].insertWord(newWord, hashedKey);
		
	}
	
	public void addSomeArr(String[][] itemsToAdd) {
		
		for (int i = 0; i < itemsToAdd.length; ++i) {
			
			String theWord = itemsToAdd[i][0];
			String theDefinition = itemsToAdd[i][1];
			
			Word newWord = new Word(theWord, theDefinition);
			
			this.insertWord(newWord);
			
		}
		
	}
	
	public Word findWord(String wordToBefound) {
		
		int hashedKey = this.hashStringFnForKey(wordToBefound);
		
		Word foundWord = this.anArray[hashedKey].findWord(hashedKey, wordToBefound);
		
		return foundWord;
		
	}
	
	public void displayArr() {
		
		for (int i = 0; i < this.arrSize; ++i) {
			
			System.out.println("Index: " + i);
			this.anArray[i].displayTheWordList();
			
		}
		
	}

}
