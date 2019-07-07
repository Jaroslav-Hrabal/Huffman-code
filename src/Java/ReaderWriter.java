package Java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReaderWriter {
	public List<HashMap <Character,Integer>> readTables(String input){ 
		List<HashMap <Character,Integer>> list = new ArrayList<HashMap <Character,Integer>>();
		File file = new File(input);
	    if (!file.exists()) {
	      System.out.println(input + " does not exist.");
	      return null;
	    }
	    if (!(file.isFile() && file.canRead())) {
	      System.out.println(file.getName() + " cannot be read from.");
	      return null;
	    }
	    try {
	      FileInputStream fis = new FileInputStream(file);
	      char current;
	      while (fis.available() > 0) {
	    	  HashMap<Character,Integer> data = new HashMap<Character, Integer>();
	    	  for(int i=0; i<8192; i++) {
	    		  	if (fis.available() > 0) {
	    		  		int tmp = fis.read();
	    		  		if(tmp>127)tmp=95;
	    		  		current = (char) tmp;
			  	        if(data.containsKey(current)) {
			  	        	data.put(current, data.get(current)+1);
			  	        }else {
			  	        	data.put(current, 0);
			  	        }
	    		  	}else {
	    		  		if(data.containsKey('_')) {
			  	        	data.put('_', data.get('_')+1);
			  	        }else {
			  	        	data.put('_', 0);
			  	        }
	    		  	}
	  	        
	    	  }
	    	  list.add(data);
	      }
	    	  fis.close();
	        //System.out.print(current);
	      
	     
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return list;
	}
	
	public void encode(List<HashMap<Character, String>> tables, String input, String output) {
		
		File file = new File(input);
	    if (!file.exists()) {
	      System.out.println(input + " does not exist.");
	     
	    }
	    if (!(file.isFile() && file.canRead())) {
	      System.out.println(file.getName() + " cannot be read from.");
	      
	    }
	    File fileW = new File(output);
	    
	    try {
	      FileInputStream fis = new FileInputStream(file);
	      //FileOutputStream fos = new FileOutputStream(fileW);
	      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
  	            new FileOutputStream(new File(output)), StandardCharsets.UTF_8));
	      char current;
	      int j = 0;
	      while(fis.available() > 0) {
	    	  HashMap<Character,String> table = tables.get(j);
	    	  j++;
	    	  
	    	  writer.write(table.size()+ System.lineSeparator());
	    	    table.forEach((key, value) -> {
					try {
						writer.write(key + "=" + value + System.lineSeparator());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
	    	    writer.flush();
	    	    
		      //DataOutputStream dos = new DataOutputStream(fos);
		      for(int i=0; i<8192; i++) {
		    	  if(fis.available() > 0) {
		    		  	int tmp = fis.read();
	    		  		if(tmp>127)tmp=95;
	    		  		current = (char) tmp;
		    	  writer.write(table.get(current));
		    	  //dos.writeUTF(table.get(current));
		    	  }else {
		    		  writer.write(table.get('_'));
		    		  //dos.writeChar('_');
		    	  }
		      }
		     writer.flush();
		      //dos.close();
	      }
	      
	    
	     
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	}
	
	public void decode(String input, String output) throws NumberFormatException, IOException {
		BufferedWriter  bw = null;
		if(output.isEmpty()) {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}else {
			File fileW = new File(output);
			FileWriter fw = new FileWriter(fileW);
			bw = new BufferedWriter(fw);
		}
	    
		File file = new File(input);
	    FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BufferedReader br  = new BufferedReader(fr);
	    String str = "";
	    while((str=br.readLine())!=null && str.length()!=0) {
	    int count = Integer.parseInt(str);
	    //System.out.println(count);
	    HashMap<String, Character> map = new HashMap<String, Character>();
	    for(int i = 0; i<count; i++) {
	    	String tmp = br.readLine();
	    	if(tmp.charAt(0)=='=') {
	    		map.put(tmp.substring(2), '=');
	    		
	    	}else {
	    	String[] split = tmp.split("=");
	    	Character symbol = (Character) split[0].charAt(0);
	    	map.put(split[1], symbol);
	    		
	    	}	    		
	    	
	    		
	    	
	    	
	    	
	    }
	    //System.out.println(map);
	    String s = "";
	    int i = 0;
	    while(i<8192) {
	    s +=""+(char)br.read();
	    //System.out.println(s);
	    if(map.containsKey(s)) {
	    	bw.write(map.get(s));
	    	//System.out.println(s);
	    	s="";
	    	i++;
	    }
	    }
	    bw.flush();
	}}
}
