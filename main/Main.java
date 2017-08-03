package main;


import java.io.IOException;

import lexer.*;
import sys.*;
import sys.Stack;

import java.io.*;
import java.util.*;


public class Main {

	public static final double π=1;
	
	public static void main(String args[]) throws IOException
	{

		int j = 0;
		String in="";
		Stack<String> inS = new Stack<String>();
		
		Vector vecStr = new Vector();
		Vector vecKey = new Vector();
		
		File file = new File("d://test.txt");
		File out = new File("d://out.txt");

		Stack<Token> tokenStack=new Stack<Token>();
		
		if (!out.exists()) {
			out.createNewFile();
		}
		
		FileWriter fw = new FileWriter(out.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

        Reader reader = null;
        try {

            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {

            	in += (char)tempchar;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		Lexer lex=new Lexer(in);
		Token t = lex.scan();

		j++;
		
		in = "";
		
		boolean flag = true;
		
		while(flag)
		{
			while(t == null)
			{
				t = lex.scan();
			}
			
			if(t.tag == 32)
			{
				flag = false;
				break;
			}
			
			
			System.out.println(t.toString());
			bw.write(t.toString()+"\r\n");
			
			tokenStack.push(t);
			in += t.getStr()+" ";
			inS.push(t.getStr());
			
			t = lex.scan();
		}
		System.out.println("***************************符号表**************************");  
		System.out.println(in);  
		Set<String> keys = lex.words.keySet();
		
		for(String key: keys){  
            if(lex.words.get(key).tag == Tag.ID)
            	vecStr.add(key+"\t\t\t" + "id");
            else
            	vecKey.add(key+"\t\t\t" + "关键字");
        } 
		
		Iterator iter = vecKey.iterator();

		while(iter.hasNext()){

		String value = (String)iter.next();

		System.out.println(value);

		}
		
		iter = vecStr.iterator();

		while(iter.hasNext()){

		String value = (String)iter.next();

		System.out.println(value);

		}
		
		if(lex.errList.size() != 0)
		{
			for(int i=0;i<lex.errList.size();i++)
				lex.err+=lex.errList.get(i);
			
			System.out.println(lex.err);
			bw.write(lex.err+"\r\n");
		}
		else
		{
			System.out.println("词法分析成功！");
			
			Parase pa = new Parase(inS, tokenStack);
			
			pa.syntax_any();
			
		}
		
		
		
		bw.close();
		
	}
	
}
