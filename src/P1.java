/*  This is the driver of the compilation process:
 *  The CSX Lite program is scanned and parsed, and an AST is built.
 *  At line 56 change the call to countDeclsAndUses into
 *  a call to buildCrossReferences. This should begin the AST traversal that
 *  cross references identifier declarations and uses. The class object returned
 *  by the call to buildCrossReferences should have a toString method that
 *  displays the results of the cross-reference in the required format (see project 1
 *  definition)
 */

import java.io.*;
import java_cup.runtime.*;

public class P1 {
	public static void
	main(String args[]) throws java.io.IOException {

		System.out.println ("--- Author: Faiz Lurman ---");
		System.out.println ("--- UWID  : 9063032438  ---");

		if (args.length != 1) {  
			System.out.println(
					"Error: Input file must be named on command line." ); 
			System.exit(-1);
		}


		java.io.FileInputStream yyin = null;

		try {
			yyin = new java.io.FileInputStream(args[0]);
		} catch (FileNotFoundException notFound){
			System.out.println ("Error: unable to open input file.");
			System.exit(-1);
		}

		Scanner.init(yyin); // Initialize Scanner class for parser

		parser csxParser = new parser(); 

		Symbol root=null;
		try {
			root = csxParser.parse(); // do the parse
			System.out.println ("CSX program parsed correctly.");

		} catch (Exception e) {
			System.out.println ("Compilation terminated due to syntax errors.");  
			System.exit(0);
		}
		System.out.println ("Here is its unparsing:");
		//((csxLiteNode)root.value).Unp(0);
		Unparsing unparse = new Unparsing();
		//   ASTNode ASTroot = (csxLiteNode) root.value;
		unparse.visit((csxLiteNode) root.value,0);
		//   unparse.visit(ASTroot,0);

		System.out.println ("\n\nHere is an analysis of identifier declarations and uses:");
		//System.out.println (((csxLiteNode)root.value).countDeclsAndUses());  
		System.out.println (((csxLiteNode)root.value).buildCrossReferences());  //!

		return;
	}
}
