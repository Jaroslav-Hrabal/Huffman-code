package Java;

public class Node {
	Node left;
	Node right;
	Integer frequency;
	Character c;
	String code;
	
	public Node(Node left, Node right) {
		super();
		this.left = left;
		this.right = right;
		this.frequency= left.getFrequency()+right.getFrequency();
		this.c = null;
		this.code = "";
	}
	public Node(Character character, Integer frequency) {
		this.frequency=frequency;
		this.c = character;
		this.code ="";
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Character getC() {
		return c;
	}
	public void setC(Character c) {
		this.c = c;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	};
	
	
	

}
