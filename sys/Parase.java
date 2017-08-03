package sys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import lexer.*;

public class Parase
{
	int labelCount;
	int addrCount;
	String input;
	String result="";
	Stack<String> inputStack=new Stack<String>();
	Stack<Integer>stateStack=new Stack<Integer>();
	Stack<String>symbolStack=new Stack<String>();
	Stack<symItem>itemStack=new Stack<symItem>();
	Stack<Token> tokenStack=new Stack<Token>();
	
	boolean usedToken;
	
	ItemSetCluster itemSet = new ItemSetCluster();

	Produce[] produce = new Produce[33];
	

	private void setProduce()
	{
		
		produce[1] = new Produce("S","programid(id_lists);compound_stmt.");
		
		produce[2] = new Produce("id_lists","id");
		produce[3] = new Produce("id_lists","id_lists,id");
		
		produce[4] = new Produce("compound_stmt","beginoptional_stmtsend");
		
		produce[5] = new Produce("optional_stmts","stmts");
		produce[13] = new Produce("optional_stmts","ε");
		produce[6] = new Produce("stmts","Nstmt");
		produce[7] = new Produce("stmts","stmts;Nstmt");
		
		produce[32] = new Produce("N","ε");
		produce[8] = new Produce("stmt","id:=expr");
		produce[9] = new Produce("stmt","compound_stmt");
		produce[10] = new Produce("stmt","ifM3boolthenN3stmt");
		produce[11] = new Produce("stmt","ifM3boolthenN3stmtelseN31stmt");
		
		produce[24] = new Produce("M3","ε");
		produce[25] = new Produce("N3","ε");
		produce[26] = new Produce("N31","ε");
		
		produce[12] = new Produce("stmt","whileM2booldoN2stmt");
		//produce[13] = new Produce("stmt","ε");
		
		produce[27] = new Produce("M2","ε");
		produce[28] = new Produce("N2","ε");
		
		produce[29] = new Produce("stmt","repeatN1stmtsuntilM1bool");
		
		produce[30] = new Produce("M1","ε");
		produce[31] = new Produce("N1","ε");
		
		produce[14] = new Produce("bool","expr>expr");
		produce[15] = new Produce("bool","expr<expr");
		
		produce[16] = new Produce("expr","expr+term");
		produce[17] = new Produce("expr","expr-term");
		produce[18] = new Produce("expr","term");
		
		produce[19] = new Produce("term","term*factor");
		produce[20] = new Produce("term","term/factor");
		produce[21] = new Produce("term","factor");
		
		produce[22] = new Produce("factor","id");
		produce[23] = new Produce("factor","num");	
		
	}
	
	/**
	 * 
	 * @param in
	 * 初始化语法分析器
	 */
	private String newLabel()
	{
		String la = "L"+labelCount;
		labelCount++;
		
		return la;
	}
	
	private String newAddr()
	{
		String ad = "t"+addrCount;
		addrCount++;
		
		return ad;
	}
	
	public Parase(Stack<String> in)
	{
		usedToken = false;
		labelCount = 0;
		addrCount = 0;
		setProduce();
		this.inputStack=in;
		stateStack.push(0);
		symbolStack.push("$");
		inputStack.push("$");
	}
	
	public Parase(Stack<String> in, Stack<Token> to)
	{
		usedToken = true;
		labelCount = 0;
		addrCount = 0;
		setProduce();
		this.inputStack=in;
		this.tokenStack = to;
		stateStack.push(0);
		symbolStack.push("$");
		inputStack.push("$");
		tokenStack.push(Word.dollar);
	}
	
	public Parase()
	{}
	
	/**
	 * 
	 * @return
	 * 打印状态表
	 */
	public String printstateform()
	{
		//打印状态表
		String res="state\tprogram\t id\t num\t if\t else\t while\t begin\t end\t.\t "
				+ ":=\t <\t +\t  *\t  ;\t (\t )\t $\t "
				+ "id_lists\t compound_stmt\t optional_stmts\t stmts\t stmt\t expr\t bool\t term\t factor\t"
				+ "\r\n";
		ItemSetCluster itemSet = new ItemSetCluster();
		State tmp[] = itemSet.state;
		
		String[] terminal={"program","id","num","if","else","while","begin","end",".",
				":=","<","+","*",";","(",")","$"};
		String[] nonTerminal={"id_lists","compound_stmt","optional_stmts","stmts","stmt","expr",
				"bool","term","factor"};
		
		for(int i = 0;i<tmp.length;i++)
		{
			res+=tmp[i].stateNumber+"\t";
			
			for(int j = 0;j<terminal.length;j++)
			{
				if(tmp[i].act.action.get(terminal[j]) == null)
				{
					res+="\t";
				}
				else
					res+=tmp[i].act.action.get(terminal[j]).toString()+"\t";
			}
			for(int j = 0;j<nonTerminal.length;j++)
			{
				if(tmp[i].go_to._goto.get(nonTerminal[j]) == null)
				{
					res+="\t";
				}
				else
					res+=tmp[i].go_to._goto.get(nonTerminal[j])+"\t";
			}	
			res+="\n";
		}
		
		return res;
	}
	
	
	public String syntax_any()
	{
		String currentinput;//当前输入
		Integer currentstate;//当前状态
		Token currenttoken;
		result="";//结果
		//输入栈压入$
		while(inputStack.size()!=0)
		{
			currentinput=inputStack.getLast();
			if(usedToken)
			{
				currenttoken = tokenStack.getLast();
			}
			currentstate=stateStack.getFirst();
//System.out.println("当前输入: "+currentinput);
//System.out.println("当前状态:"+currentstate);
 //result+="当前输入: "+currentinput+"\n";
 //result+="当前状态:"+currentstate+"\n";
			//输出双栈状态
			
//outStateStack(this.stateStack.inverse());
//outSymbolStack(this.symbolStack.inverse());
//outInput(this.inputStack.inverse());
			
			//获取当前操作
			Pair act=itemSet.state[currentstate].act.action.get(currentinput);
			 if(act==null)
			 {//如果无操作，则报错
				
 System.out.println("出现语法错误\nerror near："+currentinput);
 //result+="出现语法错误\nerror near"+currentinput;
				 return result;
			 }
			 if(act.actionType=='r')//进行规约
			 {
				
					if(act.n == 1)
					{
						//第一个状态 接受状态
//System.out.println("[按照第"+1+"条规则规约:"+produce[1].head+"->"+produce[1].produce+"]");
//result+="[按照第"+1+"条规则规约: "+produce[1].head+"->"+produce[1].produce+"]\n";
//result+="[acc]\n语法分析成功!\n";
System.out.println("\n语法分析成功！");
System.out.print("语法制导翻译成功！");
						break ;
					}
					//进行规约，使用第n个规则
					reduction(act.n);
			 }
			 else if(act.actionType=='s')//进行移入
			 {
				 //转移到状态n
				 shift(act.n);
			 }
		}
		return result;
	}
	private void shift(int n)
	{
		//压入状态栈
		stateStack.push(n);
//System.out.println("[转移至状态 ："+n+"]");
//result+="[转移至状态 ："+n+"]\n";
		
		
		
		//获取输入栈最后一个并删除
		String tmp = inputStack.getLast();
		inputStack.removeLast();
		Token currenttoken;
			
		//System.out.println(testUnit);
		//压入符号栈
		symbolStack.push(tmp);
		
		if(usedToken)
		{
			currenttoken = tokenStack.getLast();
			tokenStack.removeLast();
			
			tmp = currenttoken.getLex();
		}
		
		itemStack.push(new symItem("", tmp, "", "", "", ""));
		
	}

	private void reduction(int n)
	{
//System.out.println("[按照第"+n+"条规则规约:"+produce[n].head+"->"+produce[n].produce+"]");
//result+="[按照第"+n+"条规则规约: "+produce[n].head+"->"+produce[n].produce+"]\n";

		String produce,head;
		head = this.produce[n].head;
		produce = this.produce[n].produce;
		
		
		int count = changeSymbolStack(head,produce, n);
		//System.out.println("count"+count);
		
		
		changeStateStack(head,count);
		
	}

	private void changeStateStack(String head,int count)
	{
    //System.out.print(count+" ");
     //符号栈减少个数等于栈减少个数
 	for(int i=0;i<count;i++)
			stateStack.pop();
    //System.out.print(stateStack.size());		
		int currentState = stateStack.getFirst();
	
		if(null != itemSet.state[currentState].go_to._goto.get(head))
		{
			int newState = itemSet.state[currentState].go_to._goto.get(head);
			stateStack.push(newState);
		}
		else
		{
			System.out.println("规约错误");
		}
		
		
	
	}
	
	private symItem action(int n)
	{
		symItem newItem = new symItem("error","error","error","error","error","error");
		
		
		
		switch(n)
		{
		case 18:         //expr-> term
		case 21:         //term-> factor
		case 22:         //factor->  id 
		case 23:         //factor->  num
			symItem tmpItem = itemStack.get(0);
			
			newItem.addr = tmpItem.addr;
			newItem.fal = tmpItem.fal;
			newItem.tru = tmpItem.tru;
			newItem.next = tmpItem.next;
			newItem.L1 = tmpItem.L1;
			newItem.L2 = tmpItem.L2;
			
			break;
			
		case 20:     //term-> term1 / factor
			newItem.addr = newAddr();
			
			System.out.println(newItem.addr+"="+itemStack.get(2).addr+"/"+itemStack.get(0).addr);
			
			break;
			
		case 19:     //term-> term1 * factor
			newItem.addr = newAddr();
			
			System.out.println(newItem.addr+"="+itemStack.get(2).addr+"*"+itemStack.get(0).addr);
			
			break;
			
		case 17:    //expr-> expr1 - term
			newItem.addr = newAddr();
			
			System.out.println(newItem.addr+"="+itemStack.get(2).addr+"-"+itemStack.get(0).addr);
			
			break;
			
		case 16:    //expr-> expr1 + term 
			newItem.addr = newAddr();
			
			System.out.println(newItem.addr+"="+itemStack.get(2).addr+"+"+itemStack.get(0).addr);
			
			break;
		
			
		case 32:   //N -> ε  {next = newlabel();}
			newItem.next = newLabel();
			newItem.addr = "N";
			break;
			
		case 8:    //stmt-> id:=expr {print gen(stack[top-2].addr‘=’expr.addr); print stack[top-3].next||’:’;}
			System.out.println(itemStack.get(2).addr+"="+itemStack.get(0).addr);

			newItem.addr = "stmt";
			break;
			
		case 24:	//M3 ->  ε  {L1 = newlabel();L2 = newlabel();true=L1; false= L2; }
			
			newItem.L1 = newLabel();
			newItem.L2 = newLabel();
			newItem.tru = newItem.L1;
			newItem.fal = newItem.L2;
			newItem.next = itemStack.get(1).next;
			
			break;
			
		case 25:    //N3 ->  ε
			
			newItem.next = itemStack.get(2).next;
			
			System.out.print(itemStack.get(2).L1+":");
			
			break;
			
		case 26:   //N31 ->  ε
			
			System.out.println("goto " + itemStack.get(5).next);
			
			newItem.next = itemStack.get(5).next;
			
			System.out.print(itemStack.get(5).L2+":");
			
			break;
			
		case 10:    //stmt->  if  M3  bool  then  N3  stmt
			
			System.out.print(itemStack.get(4).L2+":");
			
			break;
			
		case 11:    //stmt->  if  M3  bool  then  N3  stmt  else  N31  stmt 
			
			System.out.print(itemStack.get(7).next+":");
			
			break;
			
		case 30:    //M1->  ε 
			
			newItem.tru = itemStack.get(2).L1;
			
			newItem.fal = itemStack.get(2).next;
			
			newItem.next = itemStack.get(2).next;
			
			break;
			
		case 31:    //N1->  ε 
			
			newItem.L1 = newLabel();
			
			newItem.next = itemStack.get(1).next;
			
			System.out.print(newItem.L1+":");
			
			break;
			
		case 29:    //stmt->  repeat  N1  stmts  until  M1  bool
			
			System.out.print(itemStack.get(1).next+":");
			
			break;
			
		case 27:    //M2->  ε
			
			newItem.L1 = newLabel();
			newItem.L2 = newLabel();
			newItem.tru = newItem.L2;
			newItem.fal = itemStack.get(1).next;
			newItem.next = itemStack.get(1).next;
			
			System.out.print(newItem.L1+":");
			
			break;
			
		case 28:   //N2->  ε
			
			newItem.next = itemStack.get(2).L1;
			
			System.out.print(itemStack.get(2).L2+":");
			
			break;
			
		case 12:   //stmt->  while  M2  bool  do  N2  stmt
			
			System.out.println("goto "+ itemStack.get(4).L1);
			
			System.out.print(itemStack.get(4).next+":");
			
			break;
			
		case 14:   //bool->  expr1  >  expr2 
			
			System.out.print("if " + itemStack.get(2).addr + ">" + itemStack.get(0).addr);
			System.out.println(" goto " + itemStack.get(3).tru);
			System.out.println("goto " + itemStack.get(3).fal);
			newItem.addr = "bool>";
			
			break;
			
		case 15:    //bool->  expr1  <  expr2 
			
			System.out.print("if " + itemStack.get(2).addr + "<" + itemStack.get(0).addr);
			System.out.println(" goto " + itemStack.get(3).tru);
			System.out.println("goto " + itemStack.get(3).fal);
			newItem.addr = "bool<";
			
			break;
		}
		
		return newItem;
	}
	
	private int changeSymbolStack(String head,String produce, int n)
	{
		symItem newItem = new symItem("error","error","error","error","error","error");
		symItem tmpItem = action(n);
		
		newItem.addr = tmpItem.addr;
		newItem.fal = tmpItem.fal;
		newItem.tru = tmpItem.tru;
		newItem.next = tmpItem.next;
		newItem.L1 = tmpItem.L1;
		newItem.L2 = tmpItem.L2;
		
		//将规约的后件删除
		if(produce.equals("ε"))
		{
			this.symbolStack.push(head);
			itemStack.push(newItem);
			return 0;
		//	System.out.print("getfirst: "+symbolStack.getFirst());
			
		}
		else
		{
			//判断应该删除多少个项
			int count = 0;
			int produceLength = produce.length();
			//System.out.println("len: "+produceLength);
			int len = 0;
			while(len < produceLength)
			{
				len += symbolStack.getFirst().length();
				//System.out.print(len+" **");
				count ++;
				if(len <= produceLength)
				{
					symbolStack.pop();
					itemStack.pop();
				}
			}
		//	System.out.print(symbolStack.size());
			//将规约的规则前件加入符号栈
			symbolStack.push(head);
			itemStack.push(newItem);
			return count;
		}
	}
	private void outStateStack(Stack<Integer> s)
	{
		
//System.out.print("状态栈：\n[");
//result+="状态栈：\n[";
		while(!s.isEmpty())
		{
			int tmp = s.pop();
			System.out.print(tmp+" ");
			result+=tmp+" ";
		}
				
		System.out.print("]\t\n");
		result+="]\t\n";
	}
	private void outSymbolStack(Stack<String> s)
	{
//System.out.print("符号栈：\n[");
//result+="符号栈：\n[";
		while(!s.isEmpty())
		{
			String tmp = s.pop();
			System.out.print(tmp+" ");
			result+=tmp+" ";
		}
		System.out.print("]\t\n");
		result+="]\t\n";

	}
	private void outInput(Stack<String> input)
	{
//result+="输入栈：\n[";
System.out.print("输入栈：\n[");
		while(!input.isEmpty())
		{
			String tmp = input.pop();
			System.out.print(tmp+" ");
			result+=tmp+"";
		}
		System.out.print("]\t\n");
		result+="]\t\n";
	}
	public static void main(String[] args) throws IOException{
		Stack<String> input=new Stack<String>();
		String in="";
		
		File file = new File("d://test.txt");
		File out = new File("d://out.txt");

		
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
            	if(tempchar == ' ')
            	{
            		if(in.length()!=0)
            			input.push(in);
            		in="";
            	}
            	else if(tempchar == '\r') 
            	{
            		if((tempchar = reader.read()) == -1)
            		{
            			break;
            		}
            		if(in.length()!=0)
            			input.push(in);
            		in="";
            	}
            	else if(tempchar == '\t')
            	{
            		if(in.length()!=0)
            			input.push(in);
            		in="";
            	}
            	else
            		in += (char)tempchar;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		Parase test=new Parase(input);
		test.syntax_any();

	}
}
/*
  program test(a,b);
  begin
  a:=19;
  end
  
 */
