package lexer;

public class Word extends Token {
	public String lexeme = "";
	public Word(String s,int tag) { super(tag); lexeme = s; }
	public String toString() 
	{ 
		if(tag==Tag.ID)
			return "<id,"+lexeme+">";
		else
			return "<"+lexeme+">";
		}
	public String toString(boolean flag) 
	{ 
		if(tag==Tag.ID)
			return "<id,"+lexeme+">";
		else
			return "<"+tag+" "+lexeme+">";
		}
	@Override
	public String getStr() {
		if(tag==Tag.ID)
			return "id";
		else
			return lexeme;
	}
	
	public String getLex() {
			return lexeme;
	}
	
	public static final Word
		
		le=new Word("<",Tag.LE),
		colon=new Word(";", Tag.COLON),		
		add=new Word("+",Tag.ADD),
		divide = new Word("/", Tag.DEVIDE),
		decrease = new Word("-", Tag.DECREASE),
		multiply=new Word("*", Tag.MULTIPLY),	
		eval=new Word(":=",Tag.EVAL),		
		lbracke=new Word("(",Tag.LBRACKE),
		rbracke=new Word(")",Tag.RBRACKE),
		comma=new Word(",",Tag.COMMA),
		stop=new Word(".",Tag.STOP),
		comment=new Word(".",Tag.COMMENT),
		dollar=new Word("$", Tag.DOLLAR)
		;
		;
}	
