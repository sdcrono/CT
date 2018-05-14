package CT;

public class Item {
	
	static int delay = 500;
	int X, Y;
	/*
	 * 1 - dan thuong.
	 * 2 - dan xuyen 2 lop.
	 * 3 - phao.
	 * 4 - nem bom.
	 * 5 - min.
	 * 6 - sua tank.
	 * 7 - hoi mau tuc thoi.
	 * 8 - hoi giap.
	 */
	int type;
	static Item i1,i2;
	

	public Item(int x, int y, int type)
	{
		this.X = x;
		this.Y = y;
		this.type = type;
		
	}
	public static void PItem()
	{
		int x, y, type;
		
		if(delay == 0){
			
			x = (int) (Math.random()*700);
			y = (int) (Math.random()*850);
			type = (int) (Math.random()*9 +1);
			i1 = new Item(x, y, type);
			
			
			
			x = (int) (Math.random()*700);
			y = (int) (Math.random()*850);
			type = (int) (Math.random()*9 +1);
			i2 = new Item(x, y, type);
			delay = 500;
		}
		else
			delay--;
		
		

	}
}
