package lexer;

public class Real extends Token {
	public final float value;
	public Real(float v) { super(Tag.REAL); value = v; }
	public String toString() { return "<real," + value+">"; }
	public String getStr() {
		// TODO Auto-generated method stub
		return "num";
	}
	public String getLex() {
		return ""+value;
	}
}
