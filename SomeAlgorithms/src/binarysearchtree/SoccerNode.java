package binarysearchtree;

public class SoccerNode {
	
	// jersey num is the key
	int jerseyNumber;
	String playerPosition;
	
	SoccerNode leftChild;
	SoccerNode rightChild;
	
	SoccerNode(int i, String pos) {
		this.jerseyNumber = i;
		this.playerPosition = pos;
	}
	
	public String toString() {
		return "No. " + this.jerseyNumber + " is a " + this.playerPosition;
	}
	
}
