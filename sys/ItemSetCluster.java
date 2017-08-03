package sys;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * ***/
public class ItemSetCluster {
	public State[] state = new State[64];
	
	ItemSetCluster()
	{
		
		state[0] = new State(0);
		state[0].go_to._goto.put("S", 1);
		state[0].act.action.put("program", new Pair('s',2));
		
		//S1
		state[1] = new State(1);
		state[1].act.action.put("$", new Pair('r',1));
		//S2		
		state[2] = new State(2);
		state[2].act.action.put("id", new Pair('s',3));
	
		//S3	
		state[3] = new State(3);
		state[3].act.action.put("(", new Pair('s',4));
	
		//S4
		state[4] = new State(4);
		state[4].act.action.put("id", new Pair('s',6));
		state[4].go_to._goto.put("id_lists",5);
		//S5
		state[5] = new State(5);
		state[5].act.action.put(")", new Pair('s',7));
		state[5].act.action.put(",", new Pair('s',8));
		
		state[6] = new State(6);
		state[6].act.action.put(",", new Pair('r',2));
		state[6].act.action.put(")", new Pair('r',2));
		//S7
		state[7] = new State(7);
		state[7].act.action.put(";", new Pair('s',9));
	
		//S8
		state[8] = new State(8);
		state[8].act.action.put("id", new Pair('s',10));
	
		//S9
		state[9] = new State(9);
		state[9].go_to._goto.put("compound_stmt", 11);
		state[9].act.action.put("begin", new Pair('s',12));
		
		//S10		
		state[10] = new State(10);
		state[10].act.action.put(",", new Pair('r',3));
		state[10].act.action.put(")", new Pair('r',3));

		//S11
		state[11] = new State(11);
		state[11].act.action.put(".", new Pair('s',13));
		state[11].act.action.put("$", new Pair('r',1));
		
		//S12
		state[12] = new State(12);
		
		/*
		state[12].go_to._goto.put("stmt",16);
		state[12].act.action.put("id", new Pair('s',17));
		state[12].go_to._goto.put("compound_stmt",18);
		state[12].act.action.put("if", new Pair('s',19));
		state[12].act.action.put("while", new Pair('s',20));
		state[12].act.action.put("begin", new Pair('s',12));
		*/
		state[12].go_to._goto.put("N",51);
		state[12].go_to._goto.put("optional_stmts",14);
		state[12].go_to._goto.put("stmts",15);
		state[12].act.action.put("end", new Pair('r',13));
		
		state[12].act.action.put("id", new Pair('r',32));
		state[12].act.action.put("begin", new Pair('r',32));
		state[12].act.action.put("if", new Pair('r',32));
		state[12].act.action.put("while", new Pair('r',32));
		state[12].act.action.put("repeat", new Pair('r',32));

		//S13
		state[13] = new State(13);
		state[13].act.action.put("$", new Pair('r',1));

		//S14
		state[14] = new State(14);
		state[14].act.action.put("end", new Pair('s',21));
		
		//S15
		state[15] = new State(15);
		state[15].act.action.put(";", new Pair('s',22));
		state[15].act.action.put("end", new Pair('r',5));

		//S16
		state[16] = new State(16);
		state[16].act.action.put(";", new Pair('r',6));
		state[16].act.action.put("end", new Pair('r',6));

		state[16].act.action.put("until", new Pair('r',6));
		
		//S17		
		state[17] = new State(17);
		state[17].act.action.put(":=", new Pair('s',23));
		//S18
		state[18] = new State(18);
		state[18].act.action.put(";", new Pair('r',9));
		state[18].act.action.put("end", new Pair('r',9));
		state[18].act.action.put("else", new Pair('r',9));
		
		state[18].act.action.put("until", new Pair('r',9));
		//19
		state[19] = new State(19);
	
		state[19].act.action.put("id", new Pair('s',28));
		state[19].act.action.put("num", new Pair('s',29));
		state[19].go_to._goto.put("factor", 27);
		state[19].go_to._goto.put("term", 26);
		state[19].go_to._goto.put("expr", 25);
		state[19].go_to._goto.put("bool", 24);
	
		
		state[20] = new State(20);
		state[20].act.action.put("id", new Pair('s',28));
		state[20].act.action.put("num", new Pair('s',29));
		state[20].go_to._goto.put("bool",30);
		state[20].go_to._goto.put("term",26);
		state[20].go_to._goto.put("expr", 25);
		state[20].go_to._goto.put("factor",27);
		//21
		state[21] = new State(21);
		state[21].act.action.put(".", new Pair('r',4));
		state[21].act.action.put(";", new Pair('r',4));
		state[21].act.action.put("end", new Pair('r',4));
		state[21].act.action.put("else", new Pair('r',4));
		state[21].act.action.put("$", new Pair('r',4));
		
		state[21].act.action.put("until", new Pair('r',4));
		//22
		state[22] = new State(22);
		/*
		state[22].act.action.put("id", new Pair('s',17));
		state[22].act.action.put("if", new Pair('s',19));
		state[22].act.action.put("while", new Pair('s',20));
		state[22].act.action.put("begin", new Pair('s',12));
		state[22].go_to._goto.put("stmt", 31);
		state[22].go_to._goto.put("compound_stmt", 18);
		state[22].act.action.put("else", new Pair('r',13));
		state[22].act.action.put(";", new Pair('r',13));
		state[22].act.action.put("end", new Pair('r',13));
		*/
		state[22].go_to._goto.put("N", 63);
		state[22].act.action.put("id", new Pair('r',32));
		state[22].act.action.put("begin", new Pair('r',32));
		state[22].act.action.put("if", new Pair('r',32));
		state[22].act.action.put("while", new Pair('r',32));
		state[22].act.action.put("repeat", new Pair('r',32));
		
		
		state[23] = new State(23);
		state[23].act.action.put("id", new Pair('s',28));
		state[23].act.action.put("num", new Pair('s',29));
		state[23].go_to._goto.put("term", 26);
		state[23].go_to._goto.put("expr", 32);
		state[23].go_to._goto.put("factor", 27);
		//24 二义性，按照else就近移入
		
		state[24] = new State(24);
		state[24].act.action.put("then", new Pair('s',33));

		
		state[25] = new State(25);
		state[25].act.action.put(">", new Pair('s',34));
		state[25].act.action.put("<", new Pair('s',35));
		state[25].act.action.put("+", new Pair('s',36));
		state[25].act.action.put("-", new Pair('s',37));
	
		
		state[26] = new State(26);
		state[26].act.action.put(">", new Pair('r',18));
		state[26].act.action.put("<", new Pair('r',18));
		state[26].act.action.put("+", new Pair('r',18));
		state[26].act.action.put("-", new Pair('r',18));
		state[26].act.action.put("then", new Pair('r',18));
		state[26].act.action.put("do", new Pair('r',18));
		state[26].act.action.put("else", new Pair('r',18));
		state[26].act.action.put(";", new Pair('r',18));
		state[26].act.action.put("end", new Pair('r',18));
		
		state[26].act.action.put("*", new Pair('s',38));
		state[26].act.action.put("/", new Pair('s',39));
		
		state[26].act.action.put("until", new Pair('r',18));
		
		state[27] = new State(27);
		state[27].act.action.put("*", new Pair('r',21));
		state[27].act.action.put("/", new Pair('r',21));
		state[27].act.action.put(">", new Pair('r',21));
		state[27].act.action.put("<", new Pair('r',21));
		state[27].act.action.put("+", new Pair('r',21));
		state[27].act.action.put("-", new Pair('r',21));
		state[27].act.action.put("then", new Pair('r',21));
		state[27].act.action.put("do", new Pair('r',21));
		
		state[27].act.action.put(";", new Pair('r',21));
		state[27].act.action.put("end", new Pair('r',21));
		state[27].act.action.put("else", new Pair('r',21));
		
		state[27].act.action.put("until", new Pair('r',21));
		
		state[28] = new State(28);
		state[28].act.action.put("*", new Pair('r',22));
		state[28].act.action.put("/", new Pair('r',22));
		state[28].act.action.put(">", new Pair('r',22));
		state[28].act.action.put("<", new Pair('r',22));
		state[28].act.action.put("+", new Pair('r',22));
		state[28].act.action.put("-", new Pair('r',22));
		state[28].act.action.put("then", new Pair('r',22));
		state[28].act.action.put("do", new Pair('r',22));
		state[28].act.action.put("else", new Pair('r',22));
		state[28].act.action.put(";", new Pair('r',22));
		state[28].act.action.put("end", new Pair('r',22));
		
		state[28].act.action.put("until", new Pair('r',22));
		

		
		state[29] = new State(29);
		state[29].act.action.put("*", new Pair('r',23));
		state[29].act.action.put("/", new Pair('r',23));
		state[29].act.action.put(">", new Pair('r',23));
		state[29].act.action.put("<", new Pair('r',23));
		state[29].act.action.put("+", new Pair('r',23));
		state[29].act.action.put("-", new Pair('r',23));
		state[29].act.action.put("then", new Pair('r',23));
		state[29].act.action.put("do", new Pair('r',23));
		state[29].act.action.put("else", new Pair('r',23));
		state[29].act.action.put(";", new Pair('r',23));
		state[29].act.action.put("end", new Pair('r',23));
		
		state[29].act.action.put("until", new Pair('r',23));
		
		
		state[30] = new State(30);
		state[30].act.action.put("do", new Pair('s',40));

		
		state[31] = new State(31);
		state[31].act.action.put(";", new Pair('r',7));
		state[31].act.action.put("end", new Pair('r',7));

		state[31].act.action.put("until", new Pair('r',7));
		
		state[32] = new State(32);
		
		state[32].act.action.put(";", new Pair('r',8));
		state[32].act.action.put("end", new Pair('r',8));
		state[32].act.action.put("else", new Pair('r',8));
		state[32].act.action.put("+", new Pair('s',36));
		state[32].act.action.put("-", new Pair('s',37));
		
		state[32].act.action.put("until", new Pair('r',8));
		
		state[33] = new State(33);
		/*
		state[33].act.action.put("id", new Pair('s',17));
		state[33].act.action.put("if", new Pair('s',19));
		state[33].act.action.put("while", new Pair('s',20));
		state[33].act.action.put("begin", new Pair('s',12));
		state[33].go_to._goto.put("stmt", 41);
		state[33].go_to._goto.put("compound_stmt",18);

		state[33].act.action.put("end", new Pair('r',13));
		state[33].act.action.put("else", new Pair('r',13));
		state[33].act.action.put(";", new Pair('r',13));
		 */
		
		state[33].go_to._goto.put("N3", 60);
		state[33].act.action.put("id", new Pair('r',25));
		state[33].act.action.put("begin", new Pair('r',25));
		state[33].act.action.put("if", new Pair('r',25));
		state[33].act.action.put("while", new Pair('r',25));
		state[33].act.action.put("repeat", new Pair('r',25));
		
		
		state[34] = new State(34);
		state[34].act.action.put("id", new Pair('s',28));
		state[34].act.action.put("num", new Pair('s',29));
		state[34].go_to._goto.put("expr", 42);
		state[34].go_to._goto.put("term", 26);
		state[34].go_to._goto.put("factor",27);
		
		state[35] = new State(35);
		state[35].go_to._goto.put("expr", 43);
		state[35].go_to._goto.put("term", 26);
		state[35].go_to._goto.put("factor",27);
		state[35].act.action.put("id", new Pair('s',28));
		state[35].act.action.put("num", new Pair('s',29));
		
		
		
		
		state[36] = new State(36);
		state[36].go_to._goto.put("term", 44);
		state[36].go_to._goto.put("factor", 27);
		state[36].act.action.put("id", new Pair('s',28));
		state[36].act.action.put("num", new Pair('s',29));
		
		state[37] = new State(37);
		state[37].go_to._goto.put("term", 45);
		state[37].go_to._goto.put("factor", 27);
		state[37].act.action.put("id", new Pair('s',28));
		state[37].act.action.put("num", new Pair('s',29));
		
		state[38] = new State(38);
		state[38].go_to._goto.put("factor", 46);
		state[38].act.action.put("id", new Pair('s',28));
		state[38].act.action.put("num", new Pair('s',29));
		
		state[39] = new State(39);
		state[39].go_to._goto.put("factor", 47);
		state[39].act.action.put("id", new Pair('s',28));
		state[39].act.action.put("num", new Pair('s',29));

		state[40] = new State(40);
		/*
		state[40].go_to._goto.put("stmt", 48);
		state[40].act.action.put("id", new Pair('s',17));
		state[40].go_to._goto.put("compound_stmt",18);
		state[40].act.action.put("if", new Pair('s',19));
		state[40].act.action.put("while", new Pair('r',20));
		state[40].act.action.put("begin", new Pair('s',12));
		
		state[40].act.action.put("end", new Pair('r',13));
		state[40].act.action.put("else", new Pair('r',13));
		state[40].act.action.put(";", new Pair('r',13));
		*/
		state[40].go_to._goto.put("N2", 62);
		state[40].act.action.put("id", new Pair('r',28));
		state[40].act.action.put("begin", new Pair('r',28));
		state[40].act.action.put("if", new Pair('r',28));
		state[40].act.action.put("while", new Pair('r',28));
		state[40].act.action.put("repeat", new Pair('r',28));
		
		//移入规约冲突
		state[41] = new State(41);
		state[41].act.action.put("else", new Pair('s',49));
		state[41].act.action.put(";", new Pair('r',10));
		state[41].act.action.put("end", new Pair('r',10));
	//	state[41].act.action.put("else", new Pair('r',10));
		
		state[42] = new State(42);
		state[42].act.action.put("then", new Pair('r',14));
		state[42].act.action.put("do", new Pair('r',14));
		state[42].act.action.put("+", new Pair('s',36));
		state[42].act.action.put("-", new Pair('s',37));
		
		state[42].act.action.put(";", new Pair('r',14));
		
		state[43] = new State(43);
		state[43].act.action.put("then", new Pair('r',15));
		state[43].act.action.put("do", new Pair('r',15));
		state[43].act.action.put("+", new Pair('s',36));
		state[43].act.action.put("-", new Pair('s',37));
		
		state[43].act.action.put(";", new Pair('r',15));
		
		state[44] = new State(44);
		
		state[44].act.action.put(">", new Pair('r',16));
		state[44].act.action.put("<", new Pair('r',16));
		state[44].act.action.put("+", new Pair('r',16));
		state[44].act.action.put("-", new Pair('r',16));
		state[44].act.action.put("then", new Pair('r',16));
		state[44].act.action.put("do", new Pair('r',16));
		state[44].act.action.put("else", new Pair('r',16));
		state[44].act.action.put(";", new Pair('r',16));
		state[44].act.action.put("end", new Pair('r',16));
		
		state[44].act.action.put("*", new Pair('s',38));
		state[44].act.action.put("/", new Pair('s',39));
		
		state[44].act.action.put("until", new Pair('r',16));
		
		state[45] = new State(45);
		
		state[45].act.action.put(">", new Pair('r',17));
		state[45].act.action.put("<", new Pair('r',17));
		state[45].act.action.put("+", new Pair('r',17));
		state[45].act.action.put("-", new Pair('r',17));
		state[45].act.action.put("then", new Pair('r',17));
		state[45].act.action.put("do", new Pair('r',17));
		state[45].act.action.put("else", new Pair('r',17));
		state[45].act.action.put(";", new Pair('r',17));
		state[45].act.action.put("end", new Pair('r',17));
		
		state[45].act.action.put("*", new Pair('s',38));
		state[45].act.action.put("/", new Pair('s',39));
		
		state[45].act.action.put("until", new Pair('r',17));
		
		
		state[46] = new State(46);
		state[46].act.action.put("*", new Pair('r',19));
		state[46].act.action.put("/", new Pair('r',19));
		state[46].act.action.put(">", new Pair('r',19));
		state[46].act.action.put("<", new Pair('r',19));
		state[46].act.action.put("+", new Pair('r',19));
		state[46].act.action.put("-", new Pair('r',19));
		state[46].act.action.put("then", new Pair('r',19));
		state[46].act.action.put("do", new Pair('r',19));
		state[46].act.action.put("else", new Pair('r',19));
		state[46].act.action.put(";", new Pair('r',19));
		state[46].act.action.put("end", new Pair('r',19));
		state[46].act.action.put("until", new Pair('r',19));
		
		state[47] = new State(47);
		state[47].act.action.put("*", new Pair('r',20));
		state[47].act.action.put("/", new Pair('r',20));
		state[47].act.action.put(">", new Pair('r',20));
		state[47].act.action.put("<", new Pair('r',20));
		state[47].act.action.put("+", new Pair('r',20));
		state[47].act.action.put("-", new Pair('r',20));
		state[47].act.action.put("then", new Pair('r',20));
		state[47].act.action.put("do", new Pair('r',20));
		state[47].act.action.put("else", new Pair('r',20));
		state[47].act.action.put(";", new Pair('r',20));
		state[47].act.action.put("end", new Pair('r',20));
		state[47].act.action.put("until", new Pair('r',20));
		
		state[48] = new State(48);
		state[48].act.action.put("else", new Pair('r',12));
		state[48].act.action.put(";", new Pair('r',12));
		state[48].act.action.put("end", new Pair('r',12));
		state[48].act.action.put("until", new Pair('r',12));
		
		state[49] = new State(49);
		/*
		state[49].go_to._goto.put("stmt", 50);
		state[49].act.action.put("id", new Pair('s',17));
		state[49].go_to._goto.put("compound_stmt", 18);
		state[49].act.action.put("if", new Pair('s',19));
		state[49].act.action.put("while", new Pair('s',20));
		state[49].act.action.put("begin", new Pair('s',12));
		
		state[49].act.action.put("end", new Pair('r',13));
		state[49].act.action.put("else", new Pair('r',13));
		state[49].act.action.put(";", new Pair('r',13));
		*/
		state[49].go_to._goto.put("N31", 61);
		state[49].act.action.put("id", new Pair('r',26));
		state[49].act.action.put("begin", new Pair('r',26));
		state[49].act.action.put("if", new Pair('r',26));
		state[49].act.action.put("while", new Pair('r',26));
		state[49].act.action.put("repeat", new Pair('r',26));
		
		state[50] = new State(50);
		state[50].act.action.put(";", new Pair('r',11));
		state[50].act.action.put("end", new Pair('r',11));
		state[50].act.action.put("else", new Pair('r',11));
		state[50].act.action.put("until", new Pair('r',11));
		
		state[51] = new State(51);
		
		state[51].go_to._goto.put("stmt",16);
		state[51].act.action.put("id", new Pair('s',17));
		state[51].go_to._goto.put("compound_stmt",18);
		state[51].act.action.put("if", new Pair('s',52));
		state[51].act.action.put("while", new Pair('s',53));
		state[51].act.action.put("begin", new Pair('s',12));
		state[51].act.action.put("repeat", new Pair('s',54));
		
		state[52] = new State(52);
		
		state[52].go_to._goto.put("M3", 19);
		state[52].act.action.put("id", new Pair('r',24));
		state[52].act.action.put("begin", new Pair('r',24));
		state[52].act.action.put("if", new Pair('r',24));
		state[52].act.action.put("while", new Pair('r',24));
		state[52].act.action.put("repeat", new Pair('r',24));
		
		state[53] = new State(53);
		
		state[53].go_to._goto.put("M2", 20);
		state[53].act.action.put("id", new Pair('r',27));
		state[53].act.action.put("begin", new Pair('r',27));
		state[53].act.action.put("if", new Pair('r',27));
		state[53].act.action.put("while", new Pair('r',27));
		state[53].act.action.put("repeat", new Pair('r',27));
		
		state[54] = new State(54);
		
		state[54].go_to._goto.put("N1", 55);
		state[54].act.action.put("id", new Pair('r',31));
		state[54].act.action.put("begin", new Pair('r',31));
		state[54].act.action.put("if", new Pair('r',31));
		state[54].act.action.put("while", new Pair('r',31));
		state[54].act.action.put("repeat", new Pair('r',31));
		
		state[55] = new State(55);
		
		state[55].go_to._goto.put("N", 51);
		state[55].go_to._goto.put("stmts", 56);
		state[55].act.action.put("id", new Pair('r',32));
		state[55].act.action.put("begin", new Pair('r',32));
		state[55].act.action.put("if", new Pair('r',32));
		state[55].act.action.put("while", new Pair('r',32));
		state[55].act.action.put("repeat", new Pair('r',32));
		
		state[56] = new State(56);
		
		state[56].act.action.put(";", new Pair('s',22));
		state[56].act.action.put("until", new Pair('s',57));

		state[57] = new State(57);
		
		state[57].go_to._goto.put("M1", 58);
		state[57].act.action.put("id", new Pair('r',30));
		state[57].act.action.put("begin", new Pair('r',30));
		state[57].act.action.put("if", new Pair('r',30));
		state[57].act.action.put("while", new Pair('r',30));
		state[57].act.action.put("repeat", new Pair('r',30));
		
		state[58] = new State(58);
		state[58].act.action.put("id", new Pair('s',28));
		state[58].act.action.put("num", new Pair('s',29));
		state[58].go_to._goto.put("bool",59);
		state[58].go_to._goto.put("term",26);
		state[58].go_to._goto.put("expr", 25);
		state[58].go_to._goto.put("factor",27);
		
		state[59] = new State(59);
		state[59].act.action.put(";", new Pair('r',29));
		state[59].act.action.put("end", new Pair('r',29));
		state[59].act.action.put("else", new Pair('r',29));
		state[59].act.action.put("until", new Pair('r',29));
		
		state[60] = new State(60);
		
		state[60].go_to._goto.put("stmt",41);
		state[60].act.action.put("id", new Pair('s',17));
		state[60].go_to._goto.put("compound_stmt",18);
		state[60].act.action.put("if", new Pair('s',52));
		state[60].act.action.put("while", new Pair('s',53));
		state[60].act.action.put("begin", new Pair('s',12));
		state[60].act.action.put("repeat", new Pair('s',54));
		
		state[61] = new State(61);
		
		state[61].go_to._goto.put("stmt",50);
		state[61].act.action.put("id", new Pair('s',17));
		state[61].go_to._goto.put("compound_stmt",18);
		state[61].act.action.put("if", new Pair('s',52));
		state[61].act.action.put("while", new Pair('s',53));
		state[61].act.action.put("begin", new Pair('s',12));
		state[61].act.action.put("repeat", new Pair('s',54));
		
		state[62] = new State(62);
	
		state[62].go_to._goto.put("stmt",48);
		state[62].act.action.put("id", new Pair('s',17));
		state[62].go_to._goto.put("compound_stmt",18);
		state[62].act.action.put("if", new Pair('s',52));
		state[62].act.action.put("while", new Pair('s',53));
		state[62].act.action.put("begin", new Pair('s',12));
		state[62].act.action.put("repeat", new Pair('s',54));
		
		state[63] = new State(63);
		
		state[63].go_to._goto.put("stmt",31);
		state[63].act.action.put("id", new Pair('s',17));
		state[63].go_to._goto.put("compound_stmt",18);
		state[63].act.action.put("if", new Pair('s',52));
		state[63].act.action.put("while", new Pair('s',53));
		state[63].act.action.put("begin", new Pair('s',12));
		state[63].act.action.put("repeat", new Pair('s',54));
		

	}
	public static void main(String[] args) throws IOException
	{
		ItemSetCluster itemSet = new ItemSetCluster();
		State tmp[] = itemSet.state;
		
	/*	if(null == tmp[0].act.action.get("id"))
			System.out.println("null");
		else
			System.out.println(tmp[0].act.action.get("id"));
		*/
	//	System.out.println(tmp.length);
		FileWriter out = new FileWriter("d:\\out.txt");
		
		String[] terminal={"program","id","num","if","else","while","begin","end",
				":=","<=",">","+","-","*","/",";","(",")","$"};
		String[] nonTerminal={"id_lists","compound_stmt","optional_stmts","stmts","stmt","expr",
				"bool","term","factor"};

		

		
		
		out.write("状态\tprogram\t id\t num\t if\t else\t while\t begin\t end\t "
				+ ":=\t <=\t >\t +\t -\t *\t /\t ;\t (\t )\t $\t "
				+ " id_lists\t compound_stmt\t optional_stmts\t stmts\t stmt\t expr\t bool\t term\t factor\t"
				+ "\r\n");
		System.out.println("aaaaaaaa"+tmp[34].go_to._goto.get("term"));
		for(int i = 0;i<tmp.length;i++)
		{
			out.write(tmp[i].stateNumber+"\t");
			
			for(int j = 0;j<terminal.length;j++)
			{
				if(tmp[i].act.action.get(terminal[j]) == null)
				{
					out.write("\t");
				//	System.out.println(i+" "+ terminal[j]);
				}
				else
					out.write(tmp[i].act.action.get(terminal[j]).toString()+"\t");
			}
			for(int j = 0;j<nonTerminal.length;j++)
			{
				if(tmp[i].go_to._goto.get(nonTerminal[j]) == null)
				{
					out.write("\t");
				//	System.out.println(i+" "+ nonTerminal[j]);
				}
				else
					out.write(tmp[i].go_to._goto.get(nonTerminal[j])+"\t");
			}	
			out.write("\r\n");
		}
		out.flush();
		out.close();
	}
}