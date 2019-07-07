package Java;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		ReaderWriter reader = new ReaderWriter();
		Coder coder = new Coder();
		System.out.println("Type 'k' for encode or 'd' for decode");
		Scanner sc = new Scanner(System.in);
		char c = sc.nextLine().charAt(0);
		if(c=='k') {
			System.out.println("Input address to data file, for example C:\\\\Workspace\\\\Resources\\\\dataHuffman\\\\data7.txt");
			String readFile = sc.nextLine();
			List<HashMap <Character,Integer>> chars = reader.readTables(readFile);

			List<List<Node>> nodes = coder.getNodes(chars);
			
			List<Node> node = coder.buildNodeTrees(nodes);
			 	
			coder.evaluateNodes(node);
			List<HashMap<Character, String>> table = coder.getTable(node);
			System.out.println("Input address to output file, for example C:\\\\Workspace\\\\Resources\\\\dataHuffman\\\\encoded7.txt");
			String writeFile = sc.nextLine();
			reader.encode(table, readFile, writeFile);
		
		}else if (c == 'd') {
			System.out.println("Input address to data file, for example C:\\\\Workspace\\\\Resources\\\\dataHuffman\\\\encoded7.txt");
			String readFile = sc.nextLine();
			System.out.println("Input address to output file, for example C:\\\\Workspace\\\\Resources\\\\dataHuffman\\\\decoded7.txt");
			String writeFile = sc.nextLine();
			reader.decode(readFile, writeFile);
		}else {
			System.out.println("Unexpected input");
		}
		
		
	}
}
