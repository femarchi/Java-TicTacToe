
public interface Player {
	
	public int[] getNextMove(GameBoard board);
	public String getName();
	public void setName(String n);
	public char getX_or_O();
	public void setX_or_O(char xo);
	
}
