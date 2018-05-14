package CT;

import java.awt.Color;

public class Turrets extends Tanks{

	
	
	public Turrets(int x, int y, int huong, int type, int size) {
		super(x ,y ,huong , type, size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.type = type;
		this.size = size;
		
		this.delay_shoot = 20;
		this.color = Color.getHSBColor(0.2f, 1f, 0.75f);
		if(this.type == 2)
			TanksBot.bot ++;
		super.Move();
		O_Tanks.add(this);

	}
	
	public void CallTanks()
	{
	
		int t;
		if(this.t_delay_shoot >0)
			this.t_delay_shoot--;
		
		this.View();
		if(this.delay_turn == 0)
		{
		
			
			t = (int) (Math.random()*4) ;
			this.huong = t;
//			System.out.print("haha "+this.huong +", ");
			this.delay_turn = 70;
			
			
		}
		else 
			this.delay_turn --;
	}
	
	/* Phuong thuc phat hien tank dich truoc mat.
	 * 
	*/
	
	public boolean Pc_View(int x, int y,int a_x, int a_y)
	{
		int dich = 3;
		int[] A= new int[10];// Do rong tam nhin cua xe tang la 10 don vi.
		boolean stop = false;
		
		if(this.type == 3)
			dich = 2;
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{
			while(x>24 && y>24 && x<875 && y<725 && !stop)
			{
				stop = true;
				x = x+a_x;
				for(int i=0;i<10;i++)
					if(A[i] != 1)
					{	if(Map.map[x][y+i].Get() == dich)
							return true;
						else
							if(Map.map[x][y+i].Get() == 1)
								A[i] = 1;
						stop = false;
					}
			}
			
		}
		else
		{
			while(x>0 && y>0 && x<850 && y<700 && !stop)
			{
				stop = true;
				y = y+a_y;
				for(int i=0;i<10;i++)
					if(A[i] != 1)
					{	if(Map.map[x+i][y].Get() == dich)
							return true;
						else
							if(Map.map[x+i][y].Get() == 1)
								A[i] = 1;
						stop = false;
					}
			}
			
		}
		
		return false;
	}
	
	public void View()
	{
	
			if(super.huong ==0)
			{
				if(this.Pc_View(this.X+10,this.Y-10,0,-1))
					super.Shoot();

			}else
			
			if(super.huong ==1)
			{	
				if(this.Pc_View(this.X+40,this.Y+10,1,0))
					super.Shoot();

			}else
				
			if(super.huong ==2)
			{
				if(this.Pc_View(this.X+10,this.Y+40,0,1))
					super.Shoot();

			}else
				
			if(super.huong ==3)
			{
				if(this.Pc_View(this.X-10,this.Y+10,-1,0))
				{
					super.Shoot();
					System.out.print(this.huong +", ");
				}
				

			}

		
	}
	

}
