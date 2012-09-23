import java.util.*;

public class HashTables {
	
	Stack<ScopeInfo> hashStack; // used to link all the scopes together
	
	HashTables(){
		hashStack = new Stack<ScopeInfo>();
	}	
	
	// need to search the stack for the declaration
	public void searchStack(String idname){
		System.out.println("Size: " + hashStack.capacity());
	}
}
