package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Coder {
    	public List<List<Node>> getNodes(List<HashMap <Character,Integer>> chars) {
    		List<List<Node>> listNodes= new ArrayList<List<Node>>();	
    		for(HashMap <Character,Integer> mp:chars) {
    			List<Node> nodes = new ArrayList<Node>();
    			Iterator it = mp.entrySet().iterator();
    		    while (it.hasNext()) {
    		        Map.Entry pair = (Map.Entry)it.next();
    		        Character c = (Character) pair.getKey();
    		        Integer i = (Integer) pair.getValue();
    		        Node n = new Node(c,i);
    		        nodes.add(n);
    		        it.remove(); // avoids a ConcurrentModificationException
    		    }
    		    listNodes.add(nodes);
    		}
    		return listNodes;
    	}
    	
    	public List<Node> buildNodeTrees(List<List<Node>> nodeList) {
    		List<Node> result = new ArrayList<Node>();
    		for(List<Node>nodes:nodeList) {
    			while(nodes.size()>1) {
    				Node nodemin1 = nodes.get(0);
    				for(Node node:nodes) {
    					if(nodemin1.getFrequency()>node.getFrequency()) {
    						nodemin1=node;
    					}
    				}
    				nodes.remove(nodemin1);
    				//if(nodes.size()==1)break;
    				Node nodemin2 = nodes.get(0);
    				for(Node node:nodes) {
    					if(nodemin2.getFrequency()>node.getFrequency()) {    						
    						nodemin2=node;
    					}
    				}
    				nodes.remove(nodemin2);
    				Node newN= new Node(nodemin1,nodemin2);
    				nodes.add(newN);			
    			}
    			result.add(nodes.get(0));
    		} 		
    		return result;
    	}
    	public void evaluateNodes(List<Node> nodes) {
    		for(Node node:nodes) {
    			node.setCode("");
    			evaluateNode(node); 			
    		}
    	}
    	
    	private void evaluateNode(Node node) {
    		Node nodeL = node.getLeft();
    		if(nodeL!=null) {
				nodeL.setCode(node.getCode()+"0");
				evaluateNode(nodeL);
    		}
			Node nodeR = node.getRight();
			if(nodeR!=null) {
				nodeR.setCode(node.getCode()+"1");
				evaluateNode(nodeR);
			}
			
    	}
    	public List<HashMap<Character, String>> getTable(List<Node>nodes){
    		List<HashMap<Character, String>> tables = new ArrayList<HashMap<Character, String>>();
    		for(Node node:nodes) {
    			HashMap<Character, String> table = new HashMap<Character, String>();
    			getCodes(node, table); 			
    			tables.add(table);
    		}
    		return tables;
    	}

		private void getCodes(Node node, HashMap<Character, String> table) {
			Node nodeL = node.getLeft();
    		if(nodeL!=null) {
				if(nodeL.getC()!=null) {
					table.put(nodeL.getC(), nodeL.getCode());
				}else {
					getCodes(nodeL, table); 		
				}
    		}
			Node nodeR = node.getRight();
			if(nodeR!=null) {
				if(nodeR.getC()!=null) {
					table.put(nodeR.getC(), nodeR.getCode());
				}else {
					getCodes(nodeR, table); 		
				}
    		}
			
		}

}
