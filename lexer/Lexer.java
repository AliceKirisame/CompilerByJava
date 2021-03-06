﻿package lexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Lexer {
	public static int line = 1;
	char peek = ' ';
	char[] tmp=new char[1];
	public Hashtable<String, Word> words = new Hashtable();
	public ArrayList<Token> arr=new ArrayList<Token>();
	public char[] in=null;
	public int next=0;
	boolean flag;
	public String result="输出：\n";
	public String err="错误：\n";
	public ArrayList<Err> errList=new ArrayList<Err>();

	void reserve(Word w) { words.put(w.lexeme, w); }
	public Lexer(String in) {

	reserve( new Word("program",    Tag.PROGRAM)    );
	arr.add(new  Word("do",   Tag.PROGRAM) );
	reserve(new  Word("end",   Tag.END)   );
	arr.add(new  Word("end",   Tag.END) );
	reserve(new  Word("begin", Tag.BEGIN) );
	arr.add(new  Word("begin",   Tag.BEGIN) );
	reserve( new Word("if",    Tag.IF)    );
	arr.add(new  Word("if",   Tag.IF) );
	reserve( new Word("then",  Tag.THEN)  );
	arr.add(new  Word("then",   Tag.THEN) );
	reserve( new Word("while", Tag.WHILE) );
	arr.add(new  Word("while",   Tag.WHILE) );
	reserve( new Word("do",    Tag.DO)    );
	arr.add(new  Word("do",   Tag.DO) );
	reserve( new Word("else",    Tag.ELSE)    );
	arr.add(new  Word("else",   Tag.ELSE) );
	reserve( new Word("repeat",    Tag.REPEAT)    );
	arr.add(new  Word("repeat",   Tag.REPEAT) );
	reserve( new Word("until",    Tag.UNTIL)    );
	arr.add(new  Word("until",   Tag.UNTIL) );
	this.in=in.toCharArray();
	flag=true;
}
	/**
	 * 
	 * @throws IOException
	 * 读取一个字符
	 */
	void readch() throws IOException {
		if(next>in.length-1)
			{
			//System.out.println(in.length);
			flag=false;
			peek=' '; 
			//System.out.println(flag+"f");
			}
		else
		{
			peek =Character.toLowerCase(in[next]);
		//	System.out.println(peek);
			next++;
		}
	}
	boolean readch(char c) throws IOException {
		readch();
		if( peek != c ) return false;
		peek = ' ';
		return true;
	}

	public Token scan() throws IOException {
		
		for(;;readch())
		{
			/**
			 *处理空格和换行
			 */
	
			if(next==in.length)
			{
				flag=false;break;
			}
			if( peek == ' ' || peek == '\t' ) continue;
			else if( peek == '\r' ) 
			{
				readch();
				line = line + 1;
			}
			else break;
		}
		/**
		 * 关系符和运算符
		 */
		switch( peek ) {
		case '<':
			readch();
			return Word.le;
		case ':':
			if(readch('=')) return Word.eval;
			
			else {
				errList.add( new Err(Tag.ERROR,"错误行数:"+line+"  错误类型：:符号错误(是否要输入 ':='?)"));
				return null;//返回错误
			}
		case '/':
			if(readch('/'))
			{
				while(flag == true && !readch('\n'));
				peek = ' ';
				return Word.comment;
			}		
			else return Word.divide;
			
		case '(':
			readch();
			return Word.lbracke;
		case ')':
			readch();
			return Word.rbracke;
		case '+':
			readch();
			return Word.add;
		case '*':
			readch();
			return Word.multiply;
		case ',':
			readch();
			return Word.comma;
		case '.':
			readch();
			return Word.stop;
		case ';':
			readch();
			return Word.colon;
		case '-':
			readch();
			return Word.decrease;
		default:break;		
		}
		/**
		 * 整数
		 */
		if( Character.isDigit(peek) ) {
			int v = 0;
			int tmp=0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				
				readch();
				
			} while( Character.isDigit(peek) );
			
			/**
			 *判断十六进制
			 */
			if(peek=='x'&&v==0)
			{	
				readch();
				do
				{
					if(Character.isDigit(peek))
						tmp=Character.digit(peek,10);
					else 
						tmp=(int)peek-87;
					//System.out.println(tmp);
					v=16*v+tmp;
					
					readch();
				}while(Character.isLetterOrDigit(peek));
			}
			else if(peek=='y'&&v==0)
			{	
				readch();
				do
				{
					if(Character.isDigit(peek))
						tmp=Character.digit(peek,10);
					else 
						tmp=(int)peek-87;
					//System.out.println(tmp);
					v=24*v+tmp;
					
					readch();
				}while(Character.isLetterOrDigit(peek));
			}
			
			if(v<0)
			{
				errList.add( new Err(Tag.ERROR,"错误行数:"+line+"  错误类型：整数越界"));
			}
			
			if( peek != '.' ) return new Num(v);
			float x = v; float d = 10;
			for( ; ; ) {
				readch();
				if( !Character.isDigit(peek) ) break;
				x = x + Character.digit(peek, 10) / d; d = d * 10;
			}
			return new Real(x);
			
		}
		/**
		 * 标识符
		 */
		if( Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek); readch();
				//包括下划线
			} while( Character.isLetterOrDigit(peek)||peek=='_');
			String s = b.toString();
	
			/**
			 * 是否是保留字
			 */
			Word w = (Word)words.get(s);
			
			if( w != null ) return w;			
			w = new Word(s, Tag.ID);
			/**
			 * 放入符号表中
			 */
			words.put(s, w);
			arr.add(w);
			return w;
		}
		Token tok = new Token(peek);
		
		if(tok.tag != 32)
		{
			errList.add( new Err(Tag.ERROR,"错误行数:"+line+"  错误类型：未知字符:"+(char)peek));
		}
		
		readch();
		return tok;
	}
}
