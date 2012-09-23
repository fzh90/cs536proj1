/*
 * This class defines data structures and utility routines used to count declarations and uses.
 * You should update it to support cross-reference analysis. Feel free to rename the class
 * if you wish.
 */
//import java.io.*;
import java.util.*;
public class ScopeInfo {
	int number; 	// sequence number of this scope (starting at 1)
	int line;   	// Source line this scope begins at
	int declsCount;	// Number of declarations in this scope 
	int usesCount;	// Number of identifier uses in this scope 
	ScopeInfo next; // Next ScopeInfo node (in list of all scopes found and processed)	
	ScopeInfo prev; // Previous ScopeInfo node
	//Hashtable<String, symbol_table> hashTable; // key is identNode's idname, does not preserve order
	LinkedHashMap<String, symbol_table> hashTable; // used because insertion-order is preserved

	
	ScopeInfo(int num, int l){
		//hashTable = new Hashtable<String, symbol_table>();
		hashTable = new LinkedHashMap<String, symbol_table>();
		number=num;
		line=l;
		declsCount=0;
		usesCount=0;
		next=null;
		prev=null;
	}
	
	ScopeInfo(int l){
		//hashTable = new Hashtable<String, symbol_table>();
		hashTable = new LinkedHashMap<String, symbol_table>();
		number=0;
		line=l;
		declsCount=0;
		usesCount=0;
		next=null;
		prev=null;
	}
	public String toString() {
		String thisLine="Scope "+number+ " (at line "+line+"): "+declsCount+" declaration(s), "+
	                     usesCount+" identifier use(s)"+"\n";		
		
		Collection<symbol_table> ids = hashTable.values();
		Iterator<symbol_table> iter  = ids.iterator();
		//String thisLine=""; // initialized to satisfy compiler
		while (iter.hasNext()) 
		{
			System.out.println(iter.next());
			//thisLine += iter.next();
		}
		
		// move to next scope
		if (next == null)
			return thisLine;
		else return thisLine+next.toString(); // recursive?
	   }
	
	// Follow list to its end. Then append newNode as new end of list.
	// Also set number in newNode to be one more than number in previous
	//  end of list node. Thus append numbers list nodes in sequence as
	//  the list is buLinkedHashMap linkedHashMap = new LinkedHashMap();

	public static void append(ScopeInfo list, ScopeInfo newNode){
		while (list.next != null){
			list=list.next;
		}
		list.next=newNode;
		newNode.prev=list;
		newNode.number=list.number+1;		
	}
	
	// traverse the scopes to find the variable declaration
	// return true if found
	public static boolean search(String name, ScopeInfo currentScope, int lineNum){
		
		while(currentScope.prev != null)
		{
			if (currentScope.hashTable.containsKey(name)){
				currentScope.hashTable.get(name).uses.add(lineNum);				
				return true;
			}
			else{				
				currentScope = currentScope.prev;
				if (currentScope.number == 1 && currentScope.hashTable.containsKey(name)){ // global scope
					currentScope.hashTable.get(name).uses.add(lineNum);
					return true;
				}				
			}
		}		
		
		return false; // not found: undeclared!
	}

	// debug: print from current to outwards
	public static void printAll(ScopeInfo currentScope){
		for (int i = currentScope.number; i >=1; i--)
		{
			System.out.println("Scope " + i + ": " + currentScope.hashTable);
			currentScope = currentScope.prev;
		}		
	}
	
//  Used to test this class
	public static void  main(String args[]) {
		ScopeInfo test = new ScopeInfo(1,1);
		System.out.println("Begin test of ScopeInfo");
		append(test,new ScopeInfo(2));
		append(test,new ScopeInfo(3));
		System.out.println(test);
		System.out.println("End test of ScopeInfo");		
	}
}
