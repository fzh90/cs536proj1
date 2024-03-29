// No need to change anything in this class!
// Define methods to unparse each kind of AST node
public class Unparsing extends Visitor {
	
	static void genIndent(int indent){
		for (int i=1;i<=indent;i++)
//			System.out.print("\t");
		    System.out.print("   ");
	}
	static void printOp(int op) {
		switch (op) {
			case sym.PLUS:
				System.out.print(" + ");
				break;
			case sym.MINUS:
				System.out.print(" - ");
				break;
			case sym.EQ:
				System.out.print(" == ");
				break;
			case sym.NOTEQ:
				System.out.print(" != ");
				break;
			default:
				throw new Error();
		}
	}

	
	 void visit(csxLiteNode n,int indent){
		//System.out.println ("\n\nStart 2nd unparsing:\n"); 
		System.out.println(n.linenum + ":\t" + " {");
		this.visit(n.progDecls,1);
		this.visit(n.progStmts,1);
		System.out.println(n.linenum + ":\t" + " } EOF");
	}
   // unparse.visit((csxLiteNode) root.value,0);
	
	void visit(fieldDeclsNode n,int indent){
		//System.out.println ("In fieldDeclsNode\n");
		
			this.visit(n.thisField,indent);
			this.visit(n.moreFields,indent);
	}
	void visit(nullFieldDeclsNode n,int indent){}

	void visit(stmtsNode n,int indent){
		  //System.out.println ("In stmtsNode\n");
		  this.visit(n.thisStmt,indent);
		  this.visit(n.moreStmts,indent);

	}
	void visit(nullStmtsNode n,int indent){}

	void visit(varDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
	    this.visit(n.varType,0);
		System.out.print(" ");
		this.visit(n.varName,0);
		System.out.println(";");
	};
	
	void visit(nullTypeNode n,int ident){}
	
	void visit(intTypeNode n,int ident){
		System.out.print("int");
	}
	void visit(boolTypeNode n,int ident){
		System.out.print("bool");
	}
	void visit(identNode n,int indent){
		System.out.print(n.idname);
	}
	void visit(asgNode n,int indent){
		System.out.print(n.linenum + ":\t"); 
		genIndent(indent);
		this.visit(n.target,0);
		System.out.print(" = ");
		this.visit(n.source,0);
		System.out.println(";");
	} 
	  void visit(ifThenNode n,int indent){
		  System.out.print(n.linenum + ":\t");
		  genIndent(indent);
		  System.out.print("if (");
		  this.visit(n.condition,0);
		  System.out.println(")");
		  this.visit(n.thenPart,indent+1);
		  // No else parts in CSXlite
	  }
	  
	  void visit(blockNode n,int indent){
		  System.out.print(n.linenum + ":\t");
			genIndent(indent);
			System.out.println("{");
			this.visit(n.decls,indent+1);
			this.visit(n.stmts,indent+1);
			System.out.print(n.linenum + ":\t");
			genIndent(indent);
			System.out.println("}");
	  }

	
	  void visit(binaryOpNode n,int indent){
		  
		  System.out.print("(");
			this.visit(n.leftOperand,0);
			printOp(n.operatorCode);
			this.visit(n.rightOperand,0);
			System.out.print(")");
	  }

	
	
	void visit(intLitNode n,int indent){
		if (n.intval>=0)
			System.out.print(n.intval);
		else	System.out.print("~"+-n.intval);
	}


}
