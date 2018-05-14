package CT3.connect;

import java.awt.Color;


public class TanksBotClient extends TanksClient 
{
	
	int max_move = 0;
	public static int bot;
	
	
	//*********************************************
	
	public TanksBotClient(int x, int y, int huong,int type, int size)
	{
		super(x ,y ,huong , type,size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.size = size;
		this.type = type;
		this.delay_shoot = 50;
		if(this.type == 3)
		{
			this.color = Color.getHSBColor(0.2f, 1f, 0.75f);
			TanksClient.player ++;
			
		}
		else{
			this.color = Color.getHSBColor(0.01f, 1f, 0.75f);
			TanksBotClient.bot ++;
		}
		O_Tanks.add(this);

		super.Move();
		
	}
//	
//	//*********************************************
//	
//
//	public void CallTanks()
//	{
//	
//		if(this.t_delay_shoot >0)
//			this.t_delay_shoot--;
//		
//		if(this.delay_turn == 0)
//		{
//			
//			
//
//			this.View();//Tu dong phat hien ke dich truoc mat.
//			
//			super.DelRec();//Xoa vi tri tren map.
//			
//			if(this.max_move >0 && this.True_Front())
//			{
//				this.Auto_Move(1);// Di chuyen toi truoc 1 don vi.
//				this.max_move --;
//			}
//			else
//			{
//				this.max_move = 0;
//				int t = this.Auto_Turn();// Random huong co the di duoc.
//				if(t == this.huong)// Neu la huong truoc mat.
//				{
//					this.Auto_Move(1);// Di chuyen toi truoc max_move don vi.
//					this.max_move =(int)( Math.random()*100+25);
//				}
//				else
//					if(t != -1)// Neu con huong co the di chuyen duoc.
//					{
//				
//						super.Setlc(this.X, this.Y, t);// Quay tank toi huong do.
//						this.delay_turn = 50;
//					}
//					else
//						super.Move();
//			}
//		}
//		else 
//			this.delay_turn = this.delay_turn -1;
//
//
//		
//	}
//	public boolean True_Front()
//	{
//		if(this.huong == 0 && super.True_Move(this.X, this.Y-2, this.huong))
//		{
//			return true;
//		}else
//		if(this.huong == 1 && super.True_Move(this.X+32, this.Y, this.huong))
//		{
//			return true;
//		}else
//		if(this.huong == 2 && super.True_Move(this.X, this.Y+32, this.huong))
//		{
//			return true;
//		}else
//		if(this.huong ==3 && super.True_Move(this.X-2, this.Y, this.huong))
//		{
//			return true;
//		}
//		
//		return false;
//	}
//	
//	//*********************************************
//
//	/* Phuong thuc tim huong di.
//	*/
//	
//	public boolean P_Turn(int x, int y, int h, int f)
//	{
//
//		if(h == 0 )
//		for(int i=0; i<f; i++)
//			if(!super.True_Move(x, y-i, h))
//				return false;
//		
//		if(h == 1)
//		for(int i=0; i<f; i++)
//			if(!super.True_Move(x+i, y, h))
//				return false;
//		
//		if(h == 2)
//		for(int i=0; i<f; i++)
//			if(!super.True_Move(x, y+i, h))
//				return false;
//		
//		if(h == 3)
//		for(int i=0; i<f; i++)
//			if(!super.True_Move(x-i, y, h))
//				return false;
//		
//		return true;
//	}
//	
//	public int Auto_Turn()
//	{
//		int[] A = {0,1,2,3};
//		int beh ;// huong phia sau.
//		
//		if(this.huong == 0 || this.huong ==2)
//			beh = Math.abs(this.huong - 2);
//		else
//			if(this.huong == 1)
//				beh = 3;
//			else
//				beh = 1;
//		
//		int r = 0; //4 Huong(0: Bac, 1: Dong, 2: Nam, 3: Tay).
//		
//		while(!(A[0]==A[1]&&A[2]==A[3]))
//		{
//			r = (int) (Math.random()*4) ;
//			
//			if(r == A[0])
//			{
//				A[0] =-1;
//				if(r == this.huong || r == beh)
//				{	
//					if((super.Y-10)>24 &&  MapClient.map[super.X][super.Y-10].Get()==0 && MapClient.map[super.X+14][super.Y-10].Get()==0 && MapClient.map[super.X+29][super.Y-10].Get()==0)
//					{
//						if(r == this.huong)
//							return 0;	
//					}
//				}
//				else
//					if(this.P_Turn(this.X, this.Y-10, r, 20) == true)
//					{
//						
//						return 0;
//					}
//				
//			}else	
//			if(r == A[1])
//			{
//			
//				
//				A[1] =-1;
//				if(r == this.huong || r == beh)
//				{
//					if((super.X+40)<875 && MapClient.map[super.X+40][super.Y].Get()==0 && MapClient.map[super.X+40][super.Y+14].Get()==0 && MapClient.map[super.X+40][super.Y+29].Get()==0) //Sua lai chieu ngang.
//					{
//						if(r == this.huong)
//						return 1;
//					}
//				}
//				else
//					if(this.P_Turn(this.X+40, this.Y, r, 20) == true)
//						{	
//							
//							
//							return 1;
//							
//						}
//				
//				
//			}else
//			if(r == A[2])
//			{
//				A[2] =-1;
//				if(r == this.huong || r == beh)
//				{
//					if((super.Y+40)<725 && MapClient.map[super.X][super.Y+40].Get()==0 && MapClient.map[super.X+14][super.Y+40].Get()==0 && MapClient.map[super.X+29][super.Y+40].Get()==0) 
//					{
//						if(r == this.huong)
//							return 2;
//					}
//				}
//				else
//					if(this.P_Turn(this.X, this.Y+40, r, 20) == true)
//					{
//						
//						return 2;
//						
//					}
//			}else	
//			if(r == A[3])
//			{
//				A[3] =-1;
//				if(r == this.huong || r == beh)
//				{
//					if((super.X-10)>24 && MapClient.map[super.X-10][super.Y].Get()==0 && MapClient.map[super.X-10][super.Y+14].Get()==0 && MapClient.map[super.X-10][super.Y+29].Get()==0)
//					{
//						if(r == this.huong)
//							return 3;
//					}
//				}
//				else
//					if(this.P_Turn(this.X-10, this.Y, r, 20) == true)
//					{
//						
//						return 3;
//					}
//				
//			}
//			
//		}
//		
//		if(r == beh) return beh;
//		return -1;
//	}
//	
//	
//	/* Phuong thuc di chuyen 1 buoc theo huong truoc mat.
//	*/
//	
//	
//	
//	/* Phuong thuc phat hien tank dich truoc mat.
//	 * 
//	*/
//	
//	public boolean Pc_View(int x, int y,int a_x, int a_y)
//	{
//		int dich = 3;
//		int[] A= new int[10];// Do rong tam nhin cua xe tang la 10 don vi.
//		boolean stop = false;
//		
//		if(this.type == 3)
//			dich = 2;
//		
//		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
//		{
//			while(x>24 && y>24 && x<875 && y<725 && !stop)
//			{
//				stop = true;
//				x = x+a_x;
//				for(int i=0;i<10;i++)
//					if(A[i] != 1)
//					{	if(MapClient.map[x][y+i].Get() == dich)
//							return true;
//						else
//							if(MapClient.map[x][y+i].Get() == 1)
//								A[i] = 1;
//						stop = false;
//					}
//			}
//			
//		}
//		else
//		{
//			while(x>0 && y>0 && x<850 && y<700 && !stop)
//			{
//				stop = true;
//				y = y+a_y;
//				for(int i=0;i<10;i++)
//					if(A[i] != 1)
//					{	if(MapClient.map[x+i][y].Get() == dich)
//							return true;
//						else
//							if(MapClient.map[x+i][y].Get() == 1)
//								A[i] = 1;
//						stop = false;
//					}
//			}
//			
//		}
//		
//		return false;
//	}
//	
//	public void View()
//	{
//	
//			if(super.huong ==0)
//			{
//				if(this.Pc_View(this.X+10,this.Y-10,0,-1))
//					super.Shoot();
//
//			}else
//			
//			if(super.huong ==1)
//			{	
//				if(this.Pc_View(this.X+40,this.Y+10,1,0))
//					super.Shoot();
//
//			}else
//				
//			if(super.huong ==2)
//			{
//				if(this.Pc_View(this.X+10,this.Y+40,0,1))
//					super.Shoot();
//
//			}else
//				
//			if(super.huong ==3)
//			{
//				if(this.Pc_View(this.X-10,this.Y+10,-1,0))
//					super.Shoot();
//				
//
//			}
//
//		
//	}
	
	
	
	
	
}
