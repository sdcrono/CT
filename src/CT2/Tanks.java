package CT2;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.*;




public class Tanks {
	
	static public ArrayList<Tanks> O_Tanks = new ArrayList<Tanks>(); //Mang chua tat ca doi tuong xe tang da tao ra.
	static public ArrayList<Bullet> O_Bullets = new ArrayList<Bullet>();
	//static public ArrayList<Tanks> O_Bullets = new ArrayList<Tanks>();
	
	/* De xoa 1 doi tuong trong 2 mang tren ta khong the xoa truc tiep trong mang.
	 * Vi neu xoa 1 phan tu trong mang , lenh duyet se sai.
	 * Dung bien danh dau live de xac dinh doi tuong co ton tai hay khong. 
	 */
	boolean live = true;
	
	static int new_tank = 0; // So xe tank se duoc sinh ra.
	
	int type;
	int huong;//4 Huong(0: Bac, 1: Dong, 2: Nam, 3: Tay).
	int size;//kich thuoc xe tank.
	int X,Y;
	int armor;
	public static int player =0;
	public static int player_enemy =0;
//	public static int fps =0;
	public int N_player ;
	int width=0, length=0; 
	Audio audio = new Audio();
	Color color;
	boolean stop =true;
	boolean lui = false;//Lui xe.
	int delay_turn = 0;//Gioi han thoi gian quay dau tanks.
	
	int delay_shoot = 0;//Gioi han thoi gian ban cua tanks.
	int t_delay_shoot = 0;
	

//	boolean flash_player = false;	
	

	//*********************************************
	
	//Ham khoi tao tanks.
	public Tanks(int x, int y, int huong, int type, int size)
	{
		
	}
	
	//Ham khoi tao vat the bat ky.
	public Tanks(int x, int y, int huong, int type, int wi, int le, int host)
	{
		
	}
	public void CallTanks()
	{
		
	}
	
	public void CallBullets()
	{
		
	}
	
	protected void Setlc(int x, int y, int huong)
	{

		this.X = x;
		this.Y = y;
		this.huong = huong;
		Move();
		
		//Am thanh

	}

	// Xoa vi tri hien tai tren Map.
	protected void DelRec()
	{
		Map.DelRect(this.X, this.Y);
	}
	
	// Luu vi tri Tank vao Map.
	protected void Move()
	{
		Map.SetRect(this.X, this.Y, this.size, this.size, this.type);
	}
	
	public void Pressed(KeyEvent e) 
	{
		
	}
	
	public void Pressed1(String s) 
	{
		
	}
	
	public void Shoot()
	{
		if(this.t_delay_shoot == 0 && this.live )
		{

			this.t_delay_shoot = this.delay_shoot;
		

			Bullet bullet = null ;
			if(this.huong == 0)
				bullet= new Bullet(this.X+13, this.Y-1, this.huong, 1, 4, 10, this.type);
			else
			if(this.huong == 1)
				bullet = new Bullet(this.X+30, this.Y+13, this.huong, 1, 10, 4, this.type);
			else
			if(this.huong == 2)
				bullet = new Bullet(this.X+13, this.Y+30, this.huong, 1, 4, 10, this.type);
			else
			if(this.huong == 3)
				bullet = new Bullet(this.X-1, this.Y+13, this.huong, 1, 10, 4, this.type);
			if (bullet != null)
			{
				audio.Tank_shoot();
				Tanks.O_Bullets.add(bullet);
			}	
			
			
					
		}
	}
	
	//Phuong thuc huy doi tuong.
	public void Del_Tank()
	{
		int random_voice = 0;
		audio.Tank_destroy();
		DelRec();
		

		if(this.type == 2)
		{
			TanksBot.bot-- ;

//			Shockwave shockwave = null ;
//			if(this.huong == 0)
//			{
//				shockwave = new Shockwave(this.X-1, this.Y-1, this.huong, 999, 30, 50, this.type);
//			}
//			else
//			if(this.huong == 1)
//				shockwave = new Shockwave(this.X+30, this.Y-1, this.huong, 999, 50, 30, this.type);
//			else
//			if(this.huong == 2)
//				shockwave = new Shockwave(this.X-1, this.Y+30, this.huong, 999, 30, 50, this.type);
//			else
//			if(this.huong == 3)
//				shockwave = new Shockwave(this.X-1, this.Y-1, this.huong, 999, 50, 30, this.type);
//		
//			if (shockwave != null )
//			{
//				audio.Tank_shoot();
//				Tanks.O_Bullets.add(shockwave);
//			}				
			
			
//			audio.Girl_die();
			TanksPlayer.score++ ;
			TanksPlayer.streaktocount++;
			audio.Kill_streak();
			
			
			
			
			
			//TanksPlayer.delay_voice = 3000;
			if(TanksPlayer.streaktolive >0)
			{
				
				TanksPlayer.streaktolive = 1000;
//				while (TanksPlayer.delay_voice > 0)
//				{
//					
//				}
				audio.Chain_streak();
				TanksPlayer.streaktostack++;
				
//				System.out.println(TanksPlayer.streaktostack);				
			}
			else
			{
				TanksPlayer.streaktolive = 1000;
				TanksPlayer.streaktostack = 0;
				TanksPlayer.streaktostack++;
			}
			Tanks.new_tank ++; 
		}
		
		if(this.type ==3)
		{
			random_voice = (int)( Math.random()*5);
//			System.out.println(random_voice);
			Tanks.player --;
			if(Tanks.player == 1 && random_voice ==1)
			{
				audio.The_last_standing();
			}
			if(random_voice == 2)
			{
				audio.Tankboss_laughing();
			}
			if(random_voice == 3)
			{
				audio.Tankbot_laughing();
			}
			if(random_voice == 4)
			{
				audio.Tankbot_respect();
			}
			if(TanksPlayer.score > 3)
				audio.Shut_down();
			TanksPlayer.streaktocount = 0;
			TanksPlayer.streaktostack = 0;
//			System.out.println(Tanks.player);
			if(Tanks.player == 0)
			{
				audio.BG_theme_end();
				audio.Tank_losing();
				audio.Ending_theme();
			}
		}
		this.live = false;
		
		
	}
	
	public void Del_Bullet()
	{
		

		this.live = false;
	}
	
	//Puong thuc xet huong di duoc hay khong.
	public boolean True_Move(int x, int y, int h)
	{
		if(x<25|| x>874 || y <25 || y>724)
			return false;
		
		if(h == 0 || h == 2)
			if(Map.map[x][y].Get() != 0 || Map.map[x+14][y].Get() != 0 ||Map.map[x+29][y].Get() != 0)
				return false;
		
		if(h == 1 || h == 3)
			if(Map.map[x][y].Get() != 0 || Map.map[x][y+14].Get() != 0 || Map.map[x][y+29].Get() != 0)
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
	
	
	//Phuong thuc cap nhat vi tri moi( di chuyen toi truoc mat 1 don vi ) vao map. 
	public void Auto_Move(int d)
	{
		
		if(this.huong ==0)
				this.Setlc(this.X,this.Y-d,this.huong);
		else
		
		if(this.huong ==1)
				this.Setlc(this.X+d,this.Y,this.huong);
		else
			
		if(this.huong ==2)

				this.Setlc(this.X,this.Y+d,this.huong);
		else
			
		if(this.huong ==3)

				this.Setlc(this.X-d,this.Y,this.huong);


	}
	
	
	/*Phuong thuc ve.
	 * 
	 */
	public void Paint_Bullet(Graphics g)
	{

	}
	
	public void Paint(Graphics g)
	{
		if(this.type == 4)
			this.color=new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),30);
		
		//Tao nong xe tank
				int x1[] = {this.X+14,this.X+16,this.X+14,this.X-6};
				int y1[] = {this.Y-6,this.Y+14,this.Y+16,this.Y+14};
				int x1rong[] = {2,20,2,20};
				int y1cao[] = {20,2,20,2};
				//Tao banh xe trai
				int x2t[] = {this.X+0,this.X+0,this.X+20,this.X+0};
				int y2t[] = {this.Y+0,this.Y+0,this.Y+0,this.Y+20};
				int x2trong[] = {10,30,10,30};
				int y2tcao[] = {30,10,30,10};
				//Tao banh xe phai
				int x2p[] = {this.X+20,this.X+0,this.X+0,this.X+0};
				int y2p[] = {this.Y+0,this.Y+20,this.Y+0,this.Y+0};
				int x2prong[] = {10,30,10,30};
				int y2pcao[] = {30,10,30,10};
				//Tao than xe
				int x3[] = {this.X+5,this.X+5,this.X+5,this.X+5};
				int y3[] = {this.Y+5,this.Y+5,this.Y+5,this.Y+5};
				int x3rong[] = {20,20,20,20};
				int y3cao[] = {20,20,20,20};


				
				int huongTiepTheo = huong ;		
				//Ve nong xe tank
				switch(huongTiepTheo)
				{	
					case 0:case 3:
						g.setColor(this.color.brighter());
						g.fillRect(x1[huongTiepTheo],y1[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
					case 1:case 2:
						g.setColor(this.color.darker());
						g.fillRect(x1[huongTiepTheo],y1[huongTiepTheo],x1rong[huongTiepTheo],y1cao[huongTiepTheo]);
				}
				
				//Ve banh trai xe tank
				g.setColor(this.color);
				g.fillRect(x2t[huongTiepTheo],y2t[huongTiepTheo],x2trong[huongTiepTheo],y2tcao[huongTiepTheo]);
					//Do bong
				if(huongTiepTheo==0)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+7,this.Y,3,30);
					g.fillRect(this.X,this.Y+27,10,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+j, this.X+9-j, this.Y+j);
						g.drawLine(this.X+j, this.Y, this.X+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.Y%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X,this.Y+j,2,2);
						}
					if(this.Y%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X,this.Y+j,2,2);
						}

					
				}
				if(huongTiepTheo==1)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y,3,10);
					g.fillRect(this.X,this.Y+7,30,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+j, this.X+29-j, this.Y+j);
						g.drawLine(this.X+j, this.Y, this.X+j, this.Y+9-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.X%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y,2,2);
						}
					if(this.X%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y,2,2);
						}
				}
				if(huongTiepTheo==2)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y,3,30);
					g.fillRect(this.X+20,this.Y+27,10,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X+20, this.Y+j, this.X+29-j, this.Y+j);
						g.drawLine(this.X+20+j, this.Y, this.X+20+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.Y%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+28,this.Y+j,2,2);
						}
					if(this.Y%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+28,this.Y+j,2,2);
						}
					
				}
				if(huongTiepTheo==3)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y+20,3,10);
					g.fillRect(this.X,this.Y+27,30,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+20+j, this.X+29-j, this.Y+20+j);
						g.drawLine(this.X+j, this.Y+20, this.X+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.X%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y+28,2,2);
						}
					if(this.X%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y+28,2,2);
						}

				
				}

				
				
				//Ve banh phai xe tank
				g.setColor(this.color);
				g.fillRect(x2p[huongTiepTheo],y2p[huongTiepTheo],x2prong[huongTiepTheo],y2pcao[huongTiepTheo]);
				if(huongTiepTheo==0)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y,3,30);
					g.fillRect(this.X+20,this.Y+27,10,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X+20, this.Y+j, this.X+29-j, this.Y+j);
						g.drawLine(this.X+20+j, this.Y, this.X+20+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.Y%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+28,this.Y+j,2,2);
						}
					if(this.Y%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+28,this.Y+j,2,2);
						}
					
				}
				if(huongTiepTheo==1)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y+20,3,10);
					g.fillRect(this.X,this.Y+27,30,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+20+j, this.X+29-j, this.Y+20+j);
						g.drawLine(this.X+j, this.Y+20, this.X+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.X%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y+28,2,2);
						}
					if(this.X%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y+28,2,2);
						}
				
				}
				if(huongTiepTheo==2)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+7,this.Y,3,30);
					g.fillRect(this.X,this.Y+27,10,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+j, this.X+9-j, this.Y+j);
						g.drawLine(this.X+j, this.Y, this.X+j, this.Y+29-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.Y%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X,this.Y+j,2,2);
						}
					if(this.Y%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X,this.Y+j,2,2);
						}
				}
				if(huongTiepTheo==3)
				{
					g.setColor(this.color.darker());
					g.fillRect(this.X+27,this.Y,3,10);
					g.fillRect(this.X,this.Y+7,30,3);
					g.setColor(this.color.brighter());
					for(int j=0;j<=2;j++)
					{	
						g.drawLine(this.X, this.Y+j, this.X+29-j, this.Y+j);
						g.drawLine(this.X+j, this.Y, this.X+j, this.Y+9-j);
					}
					//Tao banh xich
					g.setColor(this.color);
					if(this.X%2==0)
						for(int j=2;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y,2,2);
						}
					if(this.X%2!=0)
						for(int j=0;j<=28;j+=4)
						{	
							g.fillRect(this.X+j,this.Y,2,2);
						}
				}


			
				//Ve than xe tank
				g.setColor(this.color);
				g.fillRect(x3[huongTiepTheo],y3[huongTiepTheo],x3rong[huongTiepTheo],y3cao[huongTiepTheo]);
				g.setColor(this.color.darker());
				for(int j=16;j<=19;j++)
				{	
					g.drawLine(this.X+5, this.Y+j+5, this.X+19+5, this.Y+j+5);
					g.drawLine(this.X+j+5, this.Y+5, this.X+j+5, this.Y+19+5);
				}
				g.setColor(this.color.brighter());
				for(int j=0;j<=3;j++)
				{	
					g.drawLine(this.X+5, this.Y+j+5, this.X+19-j+5, this.Y+j+5);
					g.drawLine(this.X+j+5, this.Y+5, this.X+j+5, this.Y+19-j+5);
				}
				
				//Ve nap cong
				g.setColor(this.color.darker());
				g.fillArc(this.X+10, this.Y+10, 10, 10, 60, 180);
				//Ve nap cong
				g.setColor(this.color.brighter());
				g.fillArc(this.X+10, this.Y+10, 10, 10, 240, 180);
				
				g.setColor(this.color);
				g.fillArc(this.X+13, this.Y+13, 5, 5, 240, 360);
	}
	


}
