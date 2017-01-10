import javax.swing.JTextArea;

public class Trie {

	public static char ROOT_CHAR = ' ';
	private Node root;
	
	public Trie(){
		root = new Node(ROOT_CHAR, false);
	}
	
	public Trie(Node aRoot){
		root = aRoot;
	}
	
	public void setRoot(Node aRoot){
		root = aRoot;
	}
	
	public Node getRoot(){
		return root;
	}
	
	private void insertTrie(String aWord, Node aNode){
		if (aWord.length() == 0)
			return;
		
		Node temp = aNode.getLetter(aWord.charAt(0));
		
		if (temp == null){ //The node is not an internal node for this letter
			Node newNode;
			if (aWord.length() <= 1){//The last letter
				newNode = new Node(aWord.charAt(0), true);
			}
			else{//not the last letter
				newNode = new Node(aWord.charAt(0), false);
			}
			
			aNode.addLetter(newNode);
			insertTrie(aWord.substring(1), newNode);
		}
		else{
			insertTrie(aWord.substring(1), temp);
		}
	}
	
	public void insert(String aWord){
		insertTrie(aWord, root);
	}
	
	public void traverseTrie(String word, Node node, JTextArea screen){
		word += node.getKey();
		
		if (node.getWord())
			screen.append(word + "\n");
		
		for (Node child: node.getList()){
			traverseTrie(word, child, screen);
		}		
	}
	/*
	public void traverse(){
		String result = "";
		traverseTrie(result, root);
	}*/
	
	private Node follow(String word, Node aNode){
		String temp = word;
		Node result = root;
		while (temp.length() > 0){
			if (result.getLetter(temp.charAt(0)) != null){
				result = result.getLetter(temp.charAt(0));
				temp = temp.substring(1);
			}
			else return null;
		}
		return result;
	}
	
	public void print(String word, JTextArea screen){
		if (word.length() == 0) return;
		Node start = follow(word, root);
		if (start != null)
			traverseTrie(word.substring(0, word.length() - 1), start, screen);
	}
}
