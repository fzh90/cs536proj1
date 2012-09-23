import java.util.*;

public class symbol_table 
{
	int line_num;
	String id_name; // identifier name
	String id_type; // either int or bool
	List<Integer> uses;	
	
	symbol_table(int lineNum, String name, String type){
		line_num = lineNum;
		id_name  = name;
		id_type  = type;
		uses     = new ArrayList<Integer>();
	}	
	
	public String toString() 
	{
		String info = line_num + ": " + id_name + "(" + id_type + "): ";
		Iterator<Integer> iterator = uses.iterator(); 
		
		while (iterator.hasNext()){
			  info += iterator.next();  
			  if (iterator.hasNext()) info +=",";
		}
		//info += "\n";
		
		return info;
	}
}
