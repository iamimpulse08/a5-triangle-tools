package triangle.optimiser;

import triangle.StdEnvironment;
import triangle.abstractSyntaxTrees.AbstractSyntaxTree;
import triangle.abstractSyntaxTrees.Program;
import triangle.abstractSyntaxTrees.actuals.ConstActualParameter;
import triangle.abstractSyntaxTrees.actuals.EmptyActualParameterSequence;
import triangle.abstractSyntaxTrees.actuals.FuncActualParameter;
import triangle.abstractSyntaxTrees.actuals.MultipleActualParameterSequence;
import triangle.abstractSyntaxTrees.actuals.ProcActualParameter;
import triangle.abstractSyntaxTrees.actuals.SingleActualParameterSequence;
import triangle.abstractSyntaxTrees.actuals.VarActualParameter;
import triangle.abstractSyntaxTrees.aggregates.MultipleArrayAggregate;
import triangle.abstractSyntaxTrees.aggregates.MultipleRecordAggregate;
import triangle.abstractSyntaxTrees.aggregates.SingleArrayAggregate;
import triangle.abstractSyntaxTrees.aggregates.SingleRecordAggregate;
import triangle.abstractSyntaxTrees.commands.*;
import triangle.abstractSyntaxTrees.declarations.BinaryOperatorDeclaration;
import triangle.abstractSyntaxTrees.declarations.ConstDeclaration;
import triangle.abstractSyntaxTrees.declarations.FuncDeclaration;
import triangle.abstractSyntaxTrees.declarations.ProcDeclaration;
import triangle.abstractSyntaxTrees.declarations.SequentialDeclaration;
import triangle.abstractSyntaxTrees.declarations.UnaryOperatorDeclaration;
import triangle.abstractSyntaxTrees.declarations.VarDeclaration;
import triangle.abstractSyntaxTrees.expressions.ArrayExpression;
import triangle.abstractSyntaxTrees.expressions.BinaryExpression;
import triangle.abstractSyntaxTrees.expressions.CallExpression;
import triangle.abstractSyntaxTrees.expressions.CharacterExpression;
import triangle.abstractSyntaxTrees.expressions.EmptyExpression;
import triangle.abstractSyntaxTrees.expressions.Expression;
import triangle.abstractSyntaxTrees.expressions.IfExpression;
import triangle.abstractSyntaxTrees.expressions.IntegerExpression;
import triangle.abstractSyntaxTrees.expressions.LetExpression;
import triangle.abstractSyntaxTrees.expressions.RecordExpression;
import triangle.abstractSyntaxTrees.expressions.UnaryExpression;
import triangle.abstractSyntaxTrees.expressions.VnameExpression;
import triangle.abstractSyntaxTrees.formals.ConstFormalParameter;
import triangle.abstractSyntaxTrees.formals.EmptyFormalParameterSequence;
import triangle.abstractSyntaxTrees.formals.FuncFormalParameter;
import triangle.abstractSyntaxTrees.formals.MultipleFormalParameterSequence;
import triangle.abstractSyntaxTrees.formals.ProcFormalParameter;
import triangle.abstractSyntaxTrees.formals.SingleFormalParameterSequence;
import triangle.abstractSyntaxTrees.formals.VarFormalParameter;
import triangle.abstractSyntaxTrees.terminals.CharacterLiteral;
import triangle.abstractSyntaxTrees.terminals.Identifier;
import triangle.abstractSyntaxTrees.terminals.IntegerLiteral;
import triangle.abstractSyntaxTrees.terminals.Operator;
import triangle.abstractSyntaxTrees.types.AnyTypeDenoter;
import triangle.abstractSyntaxTrees.types.ArrayTypeDenoter;
import triangle.abstractSyntaxTrees.types.BoolTypeDenoter;
import triangle.abstractSyntaxTrees.types.CharTypeDenoter;
import triangle.abstractSyntaxTrees.types.ErrorTypeDenoter;
import triangle.abstractSyntaxTrees.types.IntTypeDenoter;
import triangle.abstractSyntaxTrees.types.MultipleFieldTypeDenoter;
import triangle.abstractSyntaxTrees.types.RecordTypeDenoter;
import triangle.abstractSyntaxTrees.types.SimpleTypeDenoter;
import triangle.abstractSyntaxTrees.types.SingleFieldTypeDenoter;
import triangle.abstractSyntaxTrees.types.TypeDeclaration;
import triangle.abstractSyntaxTrees.visitors.ActualParameterSequenceVisitor;
import triangle.abstractSyntaxTrees.visitors.ActualParameterVisitor;
import triangle.abstractSyntaxTrees.visitors.ArrayAggregateVisitor;
import triangle.abstractSyntaxTrees.visitors.CommandVisitor;
import triangle.abstractSyntaxTrees.visitors.DeclarationVisitor;
import triangle.abstractSyntaxTrees.visitors.ExpressionVisitor;
import triangle.abstractSyntaxTrees.visitors.FormalParameterSequenceVisitor;
import triangle.abstractSyntaxTrees.visitors.IdentifierVisitor;
import triangle.abstractSyntaxTrees.visitors.LiteralVisitor;
import triangle.abstractSyntaxTrees.visitors.OperatorVisitor;
import triangle.abstractSyntaxTrees.visitors.ProgramVisitor;
import triangle.abstractSyntaxTrees.visitors.RecordAggregateVisitor;
import triangle.abstractSyntaxTrees.visitors.TypeDenoterVisitor;
import triangle.abstractSyntaxTrees.visitors.VnameVisitor;
import triangle.abstractSyntaxTrees.vnames.DotVname;
import triangle.abstractSyntaxTrees.vnames.SimpleVname;
import triangle.abstractSyntaxTrees.vnames.SubscriptVname;
import triangle.syntacticAnalyzer.SourcePosition;

public class ConstantFolder implements ActualParameterVisitor<Void, AbstractSyntaxTree>,
		ActualParameterSequenceVisitor<Void, AbstractSyntaxTree>, ArrayAggregateVisitor<Void, AbstractSyntaxTree>,
		CommandVisitor<Void, AbstractSyntaxTree>, DeclarationVisitor<Void, AbstractSyntaxTree>,
		ExpressionVisitor<Void, AbstractSyntaxTree>, FormalParameterSequenceVisitor<Void, AbstractSyntaxTree>,
		IdentifierVisitor<Void, AbstractSyntaxTree>, LiteralVisitor<Void, AbstractSyntaxTree>,
		OperatorVisitor<Void, AbstractSyntaxTree>, ProgramVisitor<Void, AbstractSyntaxTree>,
		RecordAggregateVisitor<Void, AbstractSyntaxTree>, TypeDenoterVisitor<Void, AbstractSyntaxTree>,
		VnameVisitor<Void, AbstractSyntaxTree> {
	{

	}

	@Override
	public AbstractSyntaxTree visitConstFormalParameter(ConstFormalParameter ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitFuncFormalParameter(FuncFormalParameter ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitProcFormalParameter(ProcFormalParameter ast, Void arg) {
		ast.I.visit(this);
		ast.FPS.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitVarFormalParameter(VarFormalParameter ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Void arg) {
		ast.FT.visit(this);
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitDotVname(DotVname ast, Void arg) {
		ast.I.visit(this);
		ast.V.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSimpleVname(SimpleVname ast, Void arg) {
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSubscriptVname(SubscriptVname ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.V.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitAnyTypeDenoter(AnyTypeDenoter ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitArrayTypeDenoter(ArrayTypeDenoter ast, Void arg) {
		ast.IL.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitBoolTypeDenoter(BoolTypeDenoter ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitCharTypeDenoter(CharTypeDenoter ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitErrorTypeDenoter(ErrorTypeDenoter ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSimpleTypeDenoter(SimpleTypeDenoter ast, Void arg) {
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitIntTypeDenoter(IntTypeDenoter ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitRecordTypeDenoter(RecordTypeDenoter ast, Void arg) {
		ast.FT.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitMultipleRecordAggregate(MultipleRecordAggregate ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.I.visit(this);
		ast.RA.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSingleRecordAggregate(SingleRecordAggregate ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitProgram(Program ast, Void arg) {
		ast.C.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitOperator(Operator ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitCharacterLiteral(CharacterLiteral ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitIntegerLiteral(IntegerLiteral ast, Void arg) {
		return ast;
	}

	@Override
	public AbstractSyntaxTree visitIdentifier(Identifier ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Void arg) {
		ast.FP.visit(this);
		ast.FPS.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Void arg) {
		ast.FP.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitArrayExpression(ArrayExpression ast, Void arg) {
		ast.AA.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitBinaryExpression(BinaryExpression ast, Void arg) {
		AbstractSyntaxTree replacement1 = ast.E1.visit(this);
		AbstractSyntaxTree replacement2 = ast.E2.visit(this);
		ast.O.visit(this);

		// if visiting a child node returns something, it's either the original constant
		// (IntegerLiteral) or a folded version replacing the expression at that child
		// node
		// If both child nodes are not null; return a folded version of this
		// BinaryExpression
		// Otherwise, at least one child node isn't constant (foldable) so just replace
		// the foldable child nodes with their folded equivalent and return null
		//&& (replacement1 instanceof IntegerLiteral && replacement2 instanceof IntegerLiteral)

		if ((replacement1 != null && replacement2 != null) ) {
			return foldBinaryExpression(replacement1, replacement2, ast.O); // either an integer expression, or vname expression
		}
		else if (replacement1 != null) {
			ast.E1 = (Expression) replacement1;
		}
		else if (replacement2 != null) {
			ast.E2 = (Expression) replacement2;
		}

		// if we get here, we can't fold any higher than this level
		return null;
	}

	@Override
	public AbstractSyntaxTree visitCallExpression(CallExpression ast, Void arg) {
		ast.APS.visit(this);
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitCharacterExpression(CharacterExpression ast, Void arg) {
		ast.CL.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitEmptyExpression(EmptyExpression ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitIfExpression(IfExpression ast, Void arg) {
		AbstractSyntaxTree replacement1 = ast.E1.visit(this);
		if (replacement1 != null) {
			ast.E1 = (Expression) replacement1;
		}
		AbstractSyntaxTree replacement2 = ast.E2.visit(this);
		if (replacement2 != null) {
			ast.E2 = (Expression) replacement2;
		}
		AbstractSyntaxTree replacement3 = ast.E3.visit(this);
		if (replacement3 != null) {
			ast.E3 = (Expression) replacement3;
		}

		return null;
	}

	@Override
	public AbstractSyntaxTree visitIntegerExpression(IntegerExpression ast, Void arg) {
		return ast;
	}

	@Override
	public AbstractSyntaxTree visitLetExpression(LetExpression ast, Void arg) {
		ast.D.visit(this);
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitRecordExpression(RecordExpression ast, Void arg) {
		ast.RA.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitUnaryExpression(UnaryExpression ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}

		ast.O.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitVnameExpression(VnameExpression ast, Void arg) {
		ast.V.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Void arg) {
		ast.ARG1.visit(this);
		ast.ARG2.visit(this);
		ast.O.visit(this);
		ast.RES.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitConstDeclaration(ConstDeclaration ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitFuncDeclaration(FuncDeclaration ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.FPS.visit(this);
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitProcDeclaration(ProcDeclaration ast, Void arg) {
		ast.C.visit(this);
		ast.FPS.visit(this);
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSequentialDeclaration(SequentialDeclaration ast, Void arg) {
		ast.D1.visit(this);
		ast.D2.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitTypeDeclaration(TypeDeclaration ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Void arg) {
		ast.ARG.visit(this);
		ast.O.visit(this);
		ast.RES.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitVarDeclaration(VarDeclaration ast, Void arg) {
		ast.I.visit(this);
		ast.T.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitAssignCommand(AssignCommand ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.V.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitCallCommand(CallCommand ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitEmptyCommand(EmptyCommand ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitIfCommand(IfCommand ast, Void arg) {
		ast.C1.visit(this);
		ast.C2.visit(this);
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitLetCommand(LetCommand ast, Void arg) {
		ast.C.visit(this);
		ast.D.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSequentialCommand(SequentialCommand ast, Void arg) {
		ast.C1.visit(this);
		ast.C2.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitWhileCommand(WhileCommand ast, Void arg) {
		ast.C.visit(this);
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}
	public AbstractSyntaxTree visitLoopCommand(LoopCommand ast, Void arg) {
		ast.C1.visit(this);
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		ast.C2.visit(this);
		return null;
	}

	// TODO uncomment if you've implemented the repeat command
//	@Override
//	public AbstractSyntaxTree visitRepeatCommand(RepeatCommand ast, Void arg) {
//		ast.C.visit(this);
//		AbstractSyntaxTree replacement = ast.E.visit(this);
//		if (replacement != null) {
//			ast.E = (Expression) replacement;
//		}
//		return null;
//	}

	@Override
	public AbstractSyntaxTree visitMultipleArrayAggregate(MultipleArrayAggregate ast, Void arg) {
		ast.AA.visit(this);
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSingleArrayAggregate(SingleArrayAggregate ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Void arg) {
		return null;
	}

	@Override
	public AbstractSyntaxTree visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Void arg) {
		ast.AP.visit(this);
		ast.APS.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitSingleActualParameterSequence(SingleActualParameterSequence ast, Void arg) {
		ast.AP.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitConstActualParameter(ConstActualParameter ast, Void arg) {
		AbstractSyntaxTree replacement = ast.E.visit(this);
		if (replacement != null) {
			ast.E = (Expression) replacement;
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitFuncActualParameter(FuncActualParameter ast, Void arg) {
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitProcActualParameter(ProcActualParameter ast, Void arg) {
		ast.I.visit(this);
		return null;
	}

	@Override
	public AbstractSyntaxTree visitVarActualParameter(VarActualParameter ast, Void arg) {
		ast.V.visit(this);
		return null;
	}

	/**
	 // assistance function for foldBooleanExpression.
	 public AbstractSyntaxTree unwrapIdentifier(AbstractSyntaxTree ast) {
	 VnameExpression vNameAST = (VnameExpression) ast;
	 SimpleVname simpleVnameAST = (SimpleVname) vNameAST.V;
	 return simpleVnameAST.I.decl;
	 }

	 // started work on how to fold booleans further such that => b := true; if b \/ ((1+2)=3) would resolve to "if true" - but then realised this is a little out of scope, but folding is cool :)
	 // didn't fully implement this as I caught myself doing more work than is required for the assignment.
	 public AbstractSyntaxTree foldBooleanExpression(AbstractSyntaxTree ast1, AbstractSyntaxTree ast2, Operator o) {
	 boolean b1 = Boolean.parseBoolean(unwrapIdentifier(ast1).toString());
	 boolean b2 = Boolean.parseBoolean(unwrapIdentifier(ast2).toString());

	 Identifier foldedValue;
	 Identifier foldedValue1 = new Identifier("true", ast1.getPosition());
	 Identifier foldedValue2 = new Identifier("false", ast2.getPosition());

	 foldedValue = b1 || b2 ? foldedValue1  : foldedValue2;


	 return foldedValue;
	 }
	 **/

	// task 7
	public AbstractSyntaxTree IdentifierAsVname(Object foldedValue, SourcePosition pos) {
		Identifier booleanIdentifier = new Identifier(foldedValue.toString(), pos);
		booleanIdentifier.decl = foldedValue.toString().equals("true") ? StdEnvironment.trueDecl : StdEnvironment.falseDecl;
		SimpleVname simpleVname = new SimpleVname(booleanIdentifier, pos);
		VnameExpression Vname = new VnameExpression(simpleVname, pos);
		Vname.type = StdEnvironment.booleanType;
		return Vname;
	}


	// task 7
	public AbstractSyntaxTree foldBinaryExpression(AbstractSyntaxTree node1, AbstractSyntaxTree node2, Operator o) {
		// the only case we know how to deal with for now is two IntegerExpressions
		Object foldedValue = getFoldedValue(node1, node2, o);
		if (foldedValue instanceof Integer) {
			IntegerLiteral il = new IntegerLiteral(foldedValue.toString(), node1.getPosition());
			IntegerExpression ie = new IntegerExpression(il, node1.getPosition());
			ie.type = StdEnvironment.integerType;
			return ie;
		}
		else if (foldedValue instanceof Boolean) {
			// avoids duplication of code by assigning value of bID based on shorthand if notation for cleaner code, we can simply just check if it evaluates to true - thank you for providing an example of how to use this in Compiler.java with System.exit() method
			return IdentifierAsVname(foldedValue, node1.getPosition());
		}
		// any unhandled situation (i.e., not foldable) is ignored
		return null;
	}

	private Object getFoldedValue(AbstractSyntaxTree node1, AbstractSyntaxTree node2, Operator o) {

		Object foldedValue = null;

		if ((node1 instanceof IntegerExpression) && (node2 instanceof IntegerExpression)) {
			int int1 = (Integer.parseInt(((IntegerExpression) node1).IL.spelling));
			int int2 = (Integer.parseInt(((IntegerExpression) node2).IL.spelling));
			// handling comparison operation folding + the initial add
			// if else statements since switch requires primitive types although performance cost trade-offs are likely negligible especially at such a small scale
			if (o.decl == StdEnvironment.addDecl) {
				foldedValue = int1 + int2;
			}
			else if (o.decl == StdEnvironment.equalDecl) {
				foldedValue = int1 == int2;
			}
			else if (o.decl == StdEnvironment.unequalDecl) {
				foldedValue = int1 != int2;
			}
			else if (o.decl == StdEnvironment.lessDecl) {
				foldedValue = int1 < int2;
			}
			else if (o.decl == StdEnvironment.greaterDecl) {
				foldedValue = int1 > int2;
			}
			else if (o.decl == StdEnvironment.notlessDecl) {
				foldedValue = int1 >= int2;
			}
			else if (o.decl == StdEnvironment.notgreaterDecl) {
				foldedValue = int1 <= int2;
			}
			return foldedValue;
		}
		/**
		 * not implemented
		 *
		 else if ((node1 instanceof VnameExpression) && (node2 instanceof VnameExpression)) {
		 if (o.decl == StdEnvironment.addDecl) {
		 foldBooleanExpression(node1, node2, o);
		 }
		 }
		 **/
		// errored out or not relevant to us.
		return null;
	}

}
