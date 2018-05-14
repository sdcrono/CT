package CT;

import java.awt.Color;
import java.awt.Graphics;

public class TanksBossv2 extends TanksBot{

	int max_move = 0;
	public static int bot;
	int re_direct =-1;
	int distant =-1;
	boolean alert = false;	
	
	//*********************************************
	
	public TanksBossv2(int x, int y, int huong,int type, int size)
	{
		super(x ,y ,huong , type,size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.size = size;
		this.size = 50;
		this.type = type;
		this.armor = 5;
		this.delay_shoot = 50;
		this.color = Color.getHSBColor(0.01f, 1f, 0.75f);
		this.bot ++;
		O_Tanks.add(this);

		super.Move();
		
	}
	
	//*********************************************
	

	public void CallTanks()
	{
	
		if(this.t_delay_shoot >0)
			this.t_delay_shoot--;
		
		if(this.delay_turn == 0)
		{
			
			

			this.View();//Tu dong phat hien ke dich truoc mat.
			
			super.DelRec();//Xoa vi tri tren map.
			
			if(this.max_move >0 && this.True_Front() && this.alert == false)
			{
				this.Auto_Move(1);// Di chuyen toi truoc 1 don vi.
				this.max_move --;
			}
			else
			{
				this.max_move = 0;
				int t = -1; 
				if (this.alert == false)
					t = this.Auto_Turn();// Random huong co the di duoc.
				else
					if(this.distant != 0)
						t = this.re_direct;
				if(t == this.huong)// Neu la huong truoc mat.
				{
					if(this.True_Front())
						this.Auto_Move(1);// Di chuyen toi truoc max_move don vi.
					if(this.alert == false)
						this.max_move =(int)( Math.random()*100+25);
					else
						this.max_move = this.distant;
				}
				else
					if(t != -1)// Neu con huong co the di chuyen duoc.
					{
				
						super.Setlc(this.X, this.Y, t);// Quay tank toi huong do.
						this.delay_turn = 50;
						
					}
					else
						super.Move();
			}
		}
		else 
			this.delay_turn = this.delay_turn -1;


		
	}

	
	public boolean True_Front()
	{
		if(this.huong == 0 && this.True_Move(this.X, this.Y-2, this.huong))
		{
			return true;
		}else
		if(this.huong == 1 && this.True_Move(this.X+50, this.Y, this.huong))
		{
			return true;
		}else
		if(this.huong == 2 && this.True_Move(this.X, this.Y+50, this.huong))
		{
			return true;
		}else
		if(this.huong ==3 && this.True_Move(this.X-2, this.Y, this.huong))
		{
			return true;
		}
		
		return false;
	}
	
	//*********************************************

	/* Phuong thuc tim huong di.
	*/

	public boolean True_Move(int x, int y, int h)
	{
		if(x<25|| x>874 || y <25 || y>724)
			return false;
		
		if(h == 0 || h == 2)
			if(Map.map[x][y].Get() != 0 || Map.map[x+24][y].Get() != 0 ||Map.map[x+49][y].Get() != 0)
				return false;
		
		if(h == 1 || h == 3)
			if(Map.map[x][y].Get() != 0 || Map.map[x][y+24].Get() != 0 || Map.map[x][y+49].Get() != 0)
				return false;
		
//		if(h == 2)
//			if(Map.map[x][y].Get() != 0 || Map.map[x+14][y].Get() != 0 || Map.map[x+29][y].Get() != 0)
//				return false;
//		
//		if(h == 3)
//			if(Map.map[x][y].Get() != 0 || Map.map[x][y+14].Get() != 0 || Map.map[x][y+29].Get() != 0)
//				return false;
		
		return true;
	}	
	
	public boolean P_Turn(int x, int y, int h, int f)
	{

		if(h == 0 )
		for(int i=0; i<f; i++)
			if(!this.True_Move(x, y-i, h))
				return false;
		
		if(h == 1)
		for(int i=0; i<f; i++)
			if(!this.True_Move(x+i, y, h))
				return false;
		
		if(h == 2)
		for(int i=0; i<f; i++)
			if(!this.True_Move(x, y+i, h))
				return false;
		
		if(h == 3)
		for(int i=0; i<f; i++)
			if(!this.True_Move(x-i, y, h))
				return false;
		
		return true;
	}
	
	public int Auto_Turn()
	{
		int[] A = {0,1,2,3};
		int beh ;// huong phia sau.
		
		if(this.huong == 0 || this.huong ==2)
			beh = Math.abs(this.huong - 2);
		else
			if(this.huong == 1)
				beh = 3;
			else
				beh = 1;
		
		int r = 0; //4 Huong(0: Bac, 1: Dong, 2: Nam, 3: Tay).
		
		while(!(A[0]==A[1]&&A[2]==A[3]))
		{
			r = (int) (Math.random()*4) ;
			
			if(r == A[0])
			{
				A[0] =-1;
				if(r == this.huong || r == beh)
				{	
				//	if((super.Y-10)>24 &&  Map.map[super.X][super.Y-10].Get()==0 && Map.map[super.X+14][super.Y-10].Get()==0 && Map.map[super.X+29][super.Y-10].Get()==0)
					if((super.Y-10)>24 &&  Map.map[super.X][super.Y-10].Get()==0 && Map.map[super.X+24][super.Y-10].Get()==0 && Map.map[super.X+49][super.Y-10].Get()==0)
					{
						if(r == this.huong)
							return 0;	
					}
				}
				else
					if(this.P_Turn(this.X, this.Y-10, r, 5) == true)
					{
						return 0;
					}
				
			}else	
			if(r == A[1])
			{
			
				
				A[1] =-1;
				if(r == this.huong || r == beh)
				{
				//	if((super.X+40)<875 && Map.map[super.X+40][super.Y].Get()==0 && Map.map[super.X+40][super.Y+14].Get()==0 && Map.map[super.X+40][super.Y+29].Get()==0) //Sua lai chieu ngang.
					if((super.X+60)<875 && Map.map[super.X+60][super.Y].Get()==0 && Map.map[super.X+60][super.Y+24].Get()==0 && Map.map[super.X+60][super.Y+49].Get()==0) //Sua lai chieu ngang.
					{
						if(r == this.huong)
						return 1;
					}
				}
				else
					if(this.P_Turn(this.X+40, this.Y, r, 20) == true)
						{	
							
							
							return 1;
							
						}
				
				
			}else
			if(r == A[2])
			{
				A[2] =-1;
				if(r == this.huong || r == beh)
				{
				//	if((super.Y+40)<725 && Map.map[super.X][super.Y+40].Get()==0 && Map.map[super.X+14][super.Y+40].Get()==0 && Map.map[super.X+29][super.Y+40].Get()==0)
					if((super.Y+60)<725 && Map.map[super.X][super.Y+60].Get()==0 && Map.map[super.X+24][super.Y+60].Get()==0 && Map.map[super.X+49][super.Y+60].Get()==0) 
					{
						if(r == this.huong)
							return 2;
					}
				}
				else
					if(this.P_Turn(this.X, this.Y+40, r, 20) == true)
					{
						
						return 2;
						
					}
			}else	
			if(r == A[3])
			{
				A[3] =-1;
				if(r == this.huong || r == beh)
				{
				//	if((super.X-10)>24 && Map.map[super.X-10][super.Y].Get()==0 && Map.map[super.X-10][super.Y+14].Get()==0 && Map.map[super.X-10][super.Y+29].Get()==0)
					if((super.X-10)>24 && Map.map[super.X-10][super.Y].Get()==0 && Map.map[super.X-10][super.Y+24].Get()==0 && Map.map[super.X-10][super.Y+49].Get()==0)
					{
						if(r == this.huong)
							return 3;
					}
				}
				else
					if(this.P_Turn(this.X-10, this.Y, r, 20) == true)
					{
						
						return 3;
					}
				
			}
			
		}
		
		if(r == beh) return beh;
		return -1;
	}
	
	
	/* Phuong thuc di chuyen 1 buoc theo huong truoc mat.
	*/
	
	
	
	/* Phuong thuc phat hien tank dich truoc mat.
	 * 
	*/
	
	public boolean Pc_View1(int x, int y,int a_x, int a_y)
	{
		int[] A= new int[10];// Do rong tam nhin cua xe tang la 10 don vi.
		boolean stop = false;
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{
			while(x>49 && y>49 && x<875 && y<725 && !stop)
			{
				stop = true;
				x = x+a_x;
				for(int i=0;i<10;i++)
					if(A[i] != 1)
					{	if(Map.map[x][y+i].Get() == 3)
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
					{	if(Map.map[x+i][y].Get() == 3)
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
	

	public boolean Pc_View(int x, int y,int a_x, int a_y)
	{
		int dich = 3;
		int d = 0;
		int x_rig = x;
		int y_rig = y;
		int x_lef = x;
		int y_lef = y;
		int x_beh = x;
		int y_beh = y;
		
		int mov_view_box = 0;

		int[] A= new int[10];// Do rong tam nhin cua xe tang la 10 don vi.
		int[] A_rig= new int[10];
		int[] A_lef= new int[10];
		int[] A_beh= new int[10];
		boolean stop = false;
		
		if(this.type == 3)
			dich = 2;
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{
			mov_view_box = a_x*6;
			if(a_x == 1)
			{
				x_rig = x_rig-50+20;
				y_rig = y_rig-20+50;
				x_beh = x_beh-50-10;
				y_beh = y_beh-20+20;
				x_lef = x_lef-50+20;
				y_lef = y_lef-20-10;
			}
			else
			{
				x_rig = x_rig+10+20;
				y_rig = y_rig-20-10;
				x_beh = x_beh+10+50;
				y_beh = y_beh-20+20;
				x_lef = x_lef+10+20;
				y_lef = y_lef-20+50;				
			}
			while(((x>24 && y>24 && x<875 && y<725) || (y_rig>24 && y_rig<725) || (x_beh>24 && x_beh<875) || (y_lef>24 && y_lef<725)) && !stop)
			//while(x>24 && y>24 && x<875 && y<725 && !stop)
			{
				stop = true;
				x = x+a_x;
				y_rig = y_rig +a_x;
				x_beh = x_beh -a_x;
				y_lef = y_lef -a_x;
				d++;
			
				for(int i=0;i<10;i++)
				{
					if(A[i] != 26 && x>24 && x<875 )
					{	
						if(Map.map[x][y+i].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							
							if(a_x == 1)
							{
								this.re_direct = 1;
								this.distant = d-a_x;
							}
							else
							{
								this.re_direct = 3;
								this.distant = d+a_x;
							}
							return true;
						}
						else
							if(Map.map[x][y+i].Get() == 1)
								A[i] += 1;
						
						stop = false;
					}
					
//					if(i < 2)
					if(A_rig[i] != 26 && y_rig>24 && y_rig<725)
					{
						if(Map.map[x_rig+i-mov_view_box][y_rig].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 2;
							else
								this.re_direct = 0;
//							super.Setlc(this.X, this.Y, this.huong);
							return true;
						}
						else
							if(Map.map[x_rig+i-mov_view_box][y_rig].Get() == 1) 
								A_rig[i] += 1;
						stop = false;
					}
					
					if(A_beh[i] != 26 && x_beh>24 && x_beh<875)
					{
						if(Map.map[x_beh][y_beh+i].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 3;
							else
								this.re_direct = 1;
							return true;
						}
						else
							if(Map.map[x_beh][y_beh+i].Get() == 1) 
								A_beh[i] += 1;
						stop = false;
					}
					
//					if(i < 2)
					if(A_lef[i] != 26 && y_lef>24 && y_lef<725)
					{
						if(Map.map[x_lef+i-mov_view_box][y_lef].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 0;
							else
								this.re_direct = 2;
							return true;
						}
						else
							if(Map.map[x_lef+i-mov_view_box][y_lef].Get() == 1) 
								A_lef[i] += 1;
						stop = false;
					}

				}
			}

//		if(this.alert == true)
//			audio.Alarm_off();
		this.alert = false;	

			
		}
		else
			if(a_y == 1 || a_y == -1)// Truong hop huong cua xe tank la Nam va Bac.
			{
				mov_view_box = a_y*6;
				if(a_y == 1)
				{
					x_rig = x_rig-20-10;
					y_rig = y_rig-50+20;
					x_beh = x_beh-20+20;
					y_beh = y_beh-50-10;
					x_lef = x_lef-20+50;
					y_lef = y_lef-50+20;
				}
				else
				{
					x_rig = x_rig-20+50;
					y_rig = y_rig+10+20;
					x_beh = x_beh-20+20;
					y_beh = y_beh+10+50;
					x_lef = x_lef-20-10;
					y_lef = y_lef+10+20;
				}
				while(((x>0 && y>0 && x<850 && y<700) || (x_rig>0 && x_rig<850) || (y_beh>24 && y_beh<700) || (x_lef>0 && x_lef<850)) && !stop)
				//while(x>0 && y>0 && x<850 && y<700 && !stop)
				{
					stop = true;
					y = y+a_y;
					x_rig = x_rig - a_y;
					y_beh = y_beh - a_y;
					x_lef = x_lef + a_y;
					d++;
					for(int i=0;i<10;i++)
					{
						if(A[i] != 26 && y>0 && y<700)
						{
							if(Map.map[x+i][y].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								
								if(a_y == 1)
								{
									this.re_direct = 2;
									this.distant = d-a_y;
								}
								else
								{
									this.re_direct = 0;
									this.distant = d+a_y;
								}
								return true;
							}
							else
								if(Map.map[x+i][y].Get() == 1)
									A[i] += 1;
							
							stop = false;
						}
						
//						if(i < 2)
						if(A_rig[i] != 26 && x_rig>0 && x_rig<850 )
						{
							if(Map.map[x_rig][y_rig+i-mov_view_box].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 3;
								else
									this.re_direct = 1;
								return true;
							}
							else
								if(Map.map[x_rig][y_rig+i-mov_view_box].Get() == 1) 
									A_rig[i] += 1;
							stop = false;
						}
						
						if(A_beh[i] != 26 && y_beh>24 && y_beh<700)
						{
							if(Map.map[x_beh+i][y_beh].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 0;
								else
									this.re_direct = 2;
								return true;
							}
							else
								if(Map.map[x_beh+i][y_beh].Get() == 1) 
									A_beh[i] += 1;
							stop = false;
						}
						
//						if(i < 2)
						if(A_lef[i] != 26 && x_lef>0 && x_lef<850)
						{
							if(Map.map[x_lef][y_lef+i-mov_view_box].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 1;
								else
									this.re_direct = 3;
								return true;
							}
							else
								if(Map.map[x_lef][y_lef+i-mov_view_box].Get() == 1) 
									A_lef[i] += 1;
							stop = false;
						}
					}
				}
				
			}
//		if(this.alert == true)
//			audio.Alarm_off();
		this.alert = false;
		return false;
	}	
	
	public void View()
	{
	
			if(super.huong ==0)
			{
				if(this.Pc_View(this.X+20,this.Y-10,0,-1))
				{
					super.Setlc(this.X, this.Y, this.re_direct);
					this.Shoot();
				}

			}else
			
			if(super.huong ==1)
			{	
				if(this.Pc_View(this.X+50,this.Y+20,1,0))
				{
					super.Setlc(this.X, this.Y, this.re_direct);
					this.Shoot();
				}

			}else
				
			if(super.huong ==2)
			{
				if(this.Pc_View(this.X+20,this.Y+50,0,1))
				{
					super.Setlc(this.X, this.Y, this.re_direct);
					this.Shoot();
				}

			}else
				
			if(super.huong ==3)
			{
				if(this.Pc_View(this.X-10,this.Y+20,-1,0))
				{
					super.Setlc(this.X, this.Y, this.re_direct);
					this.Shoot();
				}
				

			}

		
	}
	

	public void Shoot()
	{
		if(this.t_delay_shoot == 0 && this.live )
		{

			this.t_delay_shoot = this.delay_shoot;
		

			Bullet bullet1 = null ;
			Bullet bullet2 = null ;
			if(this.huong == 0)
			{
				bullet1 = new Bullet(this.X+20, this.Y-1, this.huong, 5, 4, 15, this.type);
				bullet2 = new Bullet(this.X+26, this.Y-1, this.huong, 5, 4, 15, this.type);
			}
			else
			if(this.huong == 1)
			{
				bullet1 = new Bullet(this.X+50, this.Y+20, this.huong, 5, 15, 4, this.type);
				bullet2 = new Bullet(this.X+50, this.Y+26, this.huong, 5, 15, 4, this.type);
			}
			else
			if(this.huong == 2)
			{
				bullet1 = new Bullet(this.X+20, this.Y+50, this.huong, 5, 4, 15, this.type);
				bullet2 = new Bullet(this.X+26, this.Y+50, this.huong, 5, 4, 15, this.type);
			}
			else
			if(this.huong == 3)
			{
				bullet1 = new Bullet(this.X-1, this.Y+20, this.huong, 5, 15, 4, this.type);
				bullet2 = new Bullet(this.X-1, this.Y+26, this.huong, 5, 15, 4, this.type);
			}
			if (bullet1 != null || bullet2 != null)
			{
				audio.Tank_boss_shoot();
				Tanks.O_Bullets.add(bullet1);
				Tanks.O_Bullets.add(bullet2);
			}	
			
			
					
		}
	}
	
	public void veBanh(Graphics g, int x2[],int y2[], int x2rong[], int y2cao[], int huongTiepTheo,int X,int Y)
	{
		int dai =0, rong = 0, j1=0, j2=0;
		g.setColor(this.color);
		g.fillRect(x2[huongTiepTheo],y2[huongTiepTheo],x2rong[huongTiepTheo],y2cao[huongTiepTheo]);
		dai=24;
		rong=14;
		g.setColor(this.color.darker());
		//vien toi doc
		if(huongTiepTheo==0)
		{
			g.fillRect(X+rong-3,Y,3,dai);
		}
		if(huongTiepTheo==1 )
		{
			g.fillRect(X,Y+rong-3,dai,3);
		}
		if(huongTiepTheo==2)
		{
			g.fillRect(X+rong-3,Y,3,dai);
		}
		if(huongTiepTheo==3)
		{
			g.fillRect(X+dai-3,Y,3,rong);
		}
		
		//vien toi ngang
		if(huongTiepTheo==0)
		{
			g.fillRect(X,Y+dai-3,rong,3);
		}
		if(huongTiepTheo==1)
		{
			g.fillRect(X+dai-3,Y,3,rong);
		}
		if(huongTiepTheo==2)
		{
			g.fillRect(X,Y+dai-3,rong,3);
		}
		if(huongTiepTheo==3)
		{
			g.fillRect(X,Y+rong-3,dai,3);
		}

		
		
		
		g.setColor(this.color.brighter());
		for(int j=0;j<=2;j++)
		{	
			//vien sang ngang
			if(huongTiepTheo==0)
			{
				g.drawLine(X, Y+j, X+rong-1-j,Y+j);
			}
			if(huongTiepTheo==1)
			{
				g.drawLine(X, Y+j, X+dai-1-j,Y+j);
			}
			if(huongTiepTheo==2)
			{
				g.drawLine(X, Y+j, X+rong-1-j,Y+j);
			}
			if(huongTiepTheo==3)
			{
				g.drawLine(X, Y+j, X+dai-1-j,Y+j);
			}
			//vien sang doc
			if(huongTiepTheo==0)
			{
				g.drawLine(X+j, Y, X+j, Y+dai-1-j);
			}
			if(huongTiepTheo==1)
			{
				g.drawLine(X+j, Y, X+j, Y+rong-1-j);
			}
			if(huongTiepTheo==2)
			{
				g.drawLine(X+j, Y, X+j, Y+dai-1-j);
			}
			if(huongTiepTheo==3)
			{
				g.drawLine(X+j, Y, X+j, Y+rong-1-j);
			}

		}
		//Tao banh xich
		g.setColor(this.color);
		if(Y%2==0)
			for(int j=4;j<=20;j+=4)
			{	
				if(huongTiepTheo==0 || huongTiepTheo==2)
				{
					j2=j;
				}
				if(huongTiepTheo==1 || huongTiepTheo==3)
				{
					j1=j;
				}
				g.fillRect(X+j1, Y+j2, 2, 2);
			}
		if(Y%2!=0)
			for(int j=2;j<=20;j+=4)
			{	
				if(huongTiepTheo==0 || huongTiepTheo==2)
				{
					j2=j;
				}
				if(huongTiepTheo==1 || huongTiepTheo==3)
				{
					j1=j;
				}
				g.fillRect(X+j1, Y+j2, 2, 2);
			}
//			if(this.X%2==0)
//				for(int j=0;j<=22;j+=4)
//				{	
//					g.fillRect(this.X+a-2,this.Y+j,2,2);
//				}
//			if(this.X%2!=0)
//				for(int j=2;j<=22;j+=4)
//				{	
//					g.fillRect(this.X+a-2,this.Y+j,2,2);
//				}
		if(Y%2==0)
			for(int j=4;j<=20;j+=4)
			{	
				if(huongTiepTheo==0 || huongTiepTheo==2)
				{
					j2=j;
					j1=12;
				}
				if(huongTiepTheo==1 || huongTiepTheo==3)
				{
					j1=j;
					j2=12;
				}
				g.fillRect(X+j1, Y+j2, 2, 2);
			}
		if(Y%2!=0)
			for(int j=2;j<=20;j+=4)
			{	
				if(huongTiepTheo==0 || huongTiepTheo==2)
				{
					j2=j;
					j1=12;
				}
				if(huongTiepTheo==1 || huongTiepTheo==3)
				{
					j1=j;
					j2=12;
				}
				g.fillRect(X+j1, Y+j2, 2, 2);
			}
	}
	
	public void Paint(Graphics g)
	{
		int a;
		//Tao nong xe tank
		int x1t[] = {this.X+20,this.X+35,this.X+20,this.X-6};
		int y1t[] = {this.Y-6,this.Y+20,this.Y+35,this.Y+20};
		int x1p[] = {this.X+26,this.X+35,this.X+26,this.X-6};
		int y1p[] = {this.Y-6,this.Y+26,this.Y+35,this.Y+26};
		int x1rong[] = {4,21,4,21};
		int y1cao[] = {21,4,21,4};
		//Tao banh xe trai truoc
		a=5;
		int x2tt[] = {this.X+0+a,this.X+26,this.X+36-a,this.X+0};
		int y2tt[] = {this.Y+0,this.Y+0+a,this.Y+26,this.Y+36-a};
		int x2ttrong[] = {14,24,14,24};
		int y2ttcao[] = {24,14,24,14};
		//Tao banh xe phai truoc
		a=-5;
		int x2pt[] = {this.X+36+a,this.X+26,this.X+0-a,this.X+0};
		int y2pt[] = {this.Y+0,this.Y+36+a,this.Y+26,this.Y+0-a};
		int x2ptrong[] = {14,24,14,24};
		int y2ptcao[] = {24,14,24,14};
		//Tao banh xe trai sau
		a=5;
		int x2ts[] = {this.X+0+a,this.X+0,this.X+36-a,this.X+26};
		int y2ts[] = {this.Y+26,this.Y+0+a,this.Y+0,this.Y+36-a};
		int x2tsrong[] = {14,24,14,24};
		int y2tscao[] = {24,14,24,14};
		//Tao banh xe phai sau
		a=-5;
		int x2ps[] = {this.X+36+a,this.X+0,this.X+0-a,this.X+26};
		int y2ps[] = {this.Y+26,this.Y+36+a,this.Y+0,this.Y+0-a};
		int x2psrong[] = {14,24,14,24};
		int y2pscao[] = {24,14,24,14};
		a=5;
		//Tao than xe
		int x3[] = {this.X+10+a,this.X+10,this.X+10+a,this.X+15};
		int y3[] = {this.Y+15,this.Y+10+a,this.Y+10,this.Y+10+a};
		int x3rong[] = {20,25,20,25};
		int y3cao[] = {25,20,25,20};


		
		int huongTiepTheo = huong ;		
		//Ve nong xe tank
		switch(huongTiepTheo)
		{	
			case 0:
				g.setColor(this.color.brighter());
				g.fillRect(x1t[huongTiepTheo],y1t[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				g.setColor(this.color);
				g.fillRect(x1p[huongTiepTheo],y1p[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				break;
			case 3:
				g.setColor(this.color);
				g.fillRect(x1t[huongTiepTheo],y1t[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				g.setColor(this.color.brighter());
				g.fillRect(x1p[huongTiepTheo],y1p[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				break;
			case 1:
				g.setColor(this.color);
				g.fillRect(x1t[huongTiepTheo],y1t[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				g.setColor(this.color.darker());
				g.fillRect(x1p[huongTiepTheo],y1p[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				break;
			case 2:
				g.setColor(this.color.darker());
				g.fillRect(x1t[huongTiepTheo],y1t[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				g.setColor(this.color);
				g.fillRect(x1p[huongTiepTheo],y1p[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				break;
		}
		//Ve banh trai truoc xe tank
		a=5;
		if(huongTiepTheo==0)
		this.veBanh(g,x2tt, y2tt, x2ttrong, y2ttcao, huongTiepTheo, this.X+a, this.Y);
		if(huongTiepTheo==1)
		this.veBanh(g,x2tt, y2tt, x2ttrong, y2ttcao, huongTiepTheo, this.X+26, this.Y+a);
		if(huongTiepTheo==2)
		this.veBanh(g,x2tt, y2tt, x2ttrong, y2ttcao, huongTiepTheo, this.X+36-a, this.Y+26);
		if(huongTiepTheo==3)
		this.veBanh(g,x2tt, y2tt, x2ttrong, y2ttcao, huongTiepTheo, this.X, this.Y+36-a);

		//Ve banh phai truoc xe tank
		if(huongTiepTheo==0)
		this.veBanh(g,x2pt, y2pt, x2ptrong, y2ptcao, huongTiepTheo, this.X+26+a, this.Y);
		if(huongTiepTheo==1)
		this.veBanh(g,x2pt, y2pt, x2ptrong, y2ptcao, huongTiepTheo, this.X+26, this.Y+26+a);
		if(huongTiepTheo==2)
		this.veBanh(g,x2pt, y2pt, x2ptrong, y2ptcao, huongTiepTheo, this.X+a, this.Y+26);
		if(huongTiepTheo==3)
		this.veBanh(g,x2pt, y2pt, x2ptrong, y2ptcao, huongTiepTheo, this.X, this.Y+a);


		//Ve banh trai sau xe tank
		if(huongTiepTheo==0)
		this.veBanh(g,x2ts, y2ts, x2tsrong, y2tscao, huongTiepTheo, this.X+a, this.Y+26);
		if(huongTiepTheo==1)
		this.veBanh(g,x2ts, y2ts, x2tsrong, y2tscao, huongTiepTheo, this.X, this.Y+a);
		if(huongTiepTheo==2)
		this.veBanh(g,x2ts, y2ts, x2tsrong, y2tscao, huongTiepTheo, this.X+26+a, this.Y);
		if(huongTiepTheo==3)
		this.veBanh(g,x2ts, y2ts, x2tsrong, y2tscao, huongTiepTheo, this.X+26, this.Y+26+a);

		//Ve banh phai sau xe tank
		if(huongTiepTheo==0)
		this.veBanh(g,x2ps, y2ps, x2psrong, y2pscao, huongTiepTheo, this.X+26+a, this.Y+26);
		if(huongTiepTheo==1)
		this.veBanh(g,x2ps, y2ps, x2psrong, y2pscao, huongTiepTheo, this.X, this.Y+26+a);
		if(huongTiepTheo==2)
		this.veBanh(g,x2ps, y2ps, x2psrong, y2pscao, huongTiepTheo, this.X+a, this.Y);
		if(huongTiepTheo==3)
		this.veBanh(g,x2ps, y2ps, x2psrong, y2pscao, huongTiepTheo, this.X+26, this.Y+a);
	
	
		//Ve than xe tank
		g.setColor(this.color);
		g.fillRect(x3[huongTiepTheo],y3[huongTiepTheo],x3rong[huongTiepTheo],y3cao[huongTiepTheo]);
		g.setColor(this.color.darker());
		a=5;
		if(huongTiepTheo==0)
		{
			for(int j=31;j<=34;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j+a, this.X+25+a, this.Y+j+a);
				g.drawLine(this.X+j, this.Y+15, this.X+j, this.Y+29+10);
			}
			g.setColor(this.color.brighter());
			for(int j=14;j<=17;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j, this.X+43-j+a, this.Y+j);
				g.drawLine(this.X+j+1, this.Y+10+a, this.X+j+1, this.Y+38-j+10+a);
			}
		}
		if(huongTiepTheo==1)
		{
			for(int j=31;j<=34;j++)
			{	
				g.drawLine(this.X+5+a, this.Y+j, this.X+30+a, this.Y+j);
				g.drawLine(this.X+j, this.Y+15, this.X+j, this.Y+34);
			}
			g.setColor(this.color.brighter());
			for(int j=15;j<=18;j++)
			{	
				g.drawLine(this.X+5+a, this.Y+j, this.X+43-j+a, this.Y+j);
				g.drawLine(this.X+j-a, this.Y+10+a, this.X+j-a, this.Y+43-j+a);
			}
		}
		if(huongTiepTheo==2)
		{
			for(int j=25;j<=28;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j+5+2, this.X+25+a, this.Y+j+5+2);
				g.drawLine(this.X+j+a+1, this.Y+10, this.X+j+a+1, this.Y+35);
			}
			g.setColor(this.color.brighter());
			for(int j=5;j<=8;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j+a, this.X+33-j+a, this.Y+j+a);
				g.drawLine(this.X+j+a+5, this.Y+5+a, this.X+j+a+5, this.Y+35-j+a);
			}
		}
		if(huongTiepTheo==3)
		{
			for(int j=31;j<=34;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j, this.X+30+a, this.Y+j);
				g.drawLine(this.X+j+a, this.Y+15, this.X+j+a, this.Y+34);
			}
			g.setColor(this.color.brighter());
			for(int j=15;j<=18;j++)
			{	
				g.drawLine(this.X+10+a, this.Y+j, this.X+48-j+a, this.Y+j);
				g.drawLine(this.X+j, this.Y+10+a, this.X+j, this.Y+43-j+a);
			}
		}
		
		
//		//Ve nap cong
//		g.setColor(this.color.darker());
//		g.fillArc(this.X+10, this.Y+10, 10, 10, 60, 180);
//		//Ve nap cong
//		g.setColor(this.color.brighter());
//		g.fillArc(this.X+10, this.Y+10, 10, 10, 240, 180);
//		
//		g.setColor(this.color);
//		g.fillArc(this.X+13, this.Y+13, 5, 5, 240, 360);
	}
	
	
	
	
}
