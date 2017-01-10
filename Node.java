import java.util.ArrayList;
import java.util.Collections;

public class Node implements Comparable<Node>{
	private char key;
	private ArrayList<Node> letters;
	private boolean isWord;
	
	public Node(char aKey, boolean flag){
		key = aKey;
		letters = new ArrayList<Node>();
		isWord = flag;
	}
	
	public void setWord(boolean flag){
		isWord = flag;
	}
	
	public boolean getWord(){
		return isWord;
	}
	
	public void setKey(char aKey){
		key = aKey;
	}
	
	public char getKey(){
		return key;
	}
	
	public void addLetter(Node aNode){
		letters.add(aNode);
		Collections.sort(letters);
	}
	
	public Node getLetter(char aKey){
		for (int i = 0; i < letters.size(); i++){
			Node temp = letters.get(i);
			if (temp.getKey() == aKey)
				return temp;
		}
		return null;
	}	
	
	public ArrayList<Node> getList(){
		return letters;
	}
	
	public int compareTo(Node other){
		if ((this.key == '\'') || (this.key < other.getKey())) return -1;
		else if(this.key == other.getKey()) return 0;
		else return 1;
	}
}
