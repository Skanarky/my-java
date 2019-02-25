package binarysearch;

public class BinaryTree {

	SoccerNode root;

	public void addSoccerNode(int jerseyNum, String position) {

		SoccerNode newNode = new SoccerNode(jerseyNum, position);

		if (this.root == null) {

			this.root = newNode;

		} else {

			// the focusNode is a key for this algorithm 
			// we use is to have 'focus' and dig deeper so we can 
			// determine parents and children against its value
			SoccerNode focusNode = this.root;

			SoccerNode parent;

			while(true) {
				parent = focusNode;

				if (jerseyNum < focusNode.jerseyNumber) {

					focusNode = focusNode.leftChild;

					if (focusNode == null) {

						parent.leftChild = newNode;
						return;

					}

				} else {
					
					focusNode = focusNode.rightChild;
					
					if (focusNode == null) {
						
						parent.rightChild = newNode;
						return;
						
					}

				}

			}

		}

	}
	
	public SoccerNode findSoccerNode(int jerseyNum) {
		
		SoccerNode focusNode = this.root;
		
		while (focusNode.jerseyNumber != jerseyNum) {
			
			if (jerseyNum < focusNode.jerseyNumber) {
				
				focusNode = focusNode.leftChild;
				
			} else {
				
				focusNode = focusNode.rightChild;
				
			}
			
			if (focusNode == null) 
				return null;
			
		}
		
		return focusNode;
	}
	
	// from small to big
	public void inOrderTraverseTree(SoccerNode focusNode) {
		
		if (focusNode != null) {
			
			this.inOrderTraverseTree(focusNode.leftChild);
			
			System.out.println(focusNode);
			
			this.inOrderTraverseTree(focusNode.rightChild);
			
		}
		
	}
	
	//  for deleting copy; all parents and hitting nulls, starting from root; pre-fixing
	public void preOrderTraverseTree(SoccerNode focusNode) {
		
		if (focusNode != null) {
			
			System.out.println(focusNode);
			
			this.preOrderTraverseTree(focusNode.leftChild);
			
			this.preOrderTraverseTree(focusNode.rightChild);
			
		}
		
	}
	
	// for deleting the tree; all children and up, starting from bottom left; post-fixing
	public void postOrderTraverseTree(SoccerNode focusNode) {
		
		if (focusNode != null) {

			this.postOrderTraverseTree(focusNode.leftChild);

			this.postOrderTraverseTree(focusNode.rightChild);

			System.out.println(focusNode);

		}

	}
	
	public static void main(String[] args) {
	
		BinaryTree soccerTeam = new BinaryTree();

		soccerTeam.addSoccerNode(12, "Goalie");
		soccerTeam.addSoccerNode(15, "Left Back");
		soccerTeam.addSoccerNode(6, "Right Back");
		soccerTeam.addSoccerNode(19, "Central Defender 1");
		soccerTeam.addSoccerNode(37, "Central Defender 2");
		soccerTeam.addSoccerNode(8, "Left Midfielder");
		soccerTeam.addSoccerNode(47, "Right Midfielder");
		soccerTeam.addSoccerNode(26, "Central Midfielder");
		soccerTeam.addSoccerNode(9, "Playmaker");
		soccerTeam.addSoccerNode(3, "Central Scorer");
		soccerTeam.addSoccerNode(89, "Wing");

//		soccerTeam.inOrderTraverseTree(soccerTeam.root);
		soccerTeam.preOrderTraverseTree(soccerTeam.root);
//		soccerTeam.postOrderTraverseTree(soccerTeam.root);
		
		System.out.println("\nFind players 9 and 99: ");
		System.out.println(soccerTeam.findSoccerNode(9));
		System.out.println(soccerTeam.findSoccerNode(99));
		
	}

}
