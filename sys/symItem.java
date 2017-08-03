package sys;

public class symItem {
	String next;
	String addr;
	String L1;
	String L2;
	String tru;
	String fal;
	public symItem(String n, String ad, String l1, String l2, String t, String f)
	{
		this.next = n;
		this.addr = ad;
		this.L1 = l1;
		this.L2 = l2;
		this.tru = t;
		this.fal = f;
	}
}
