package CT2.connect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class TanksPlayerC extends TanksC  {

	public static int score;
	public static int streaktocount = 0;
	public static int streaktostack = 0;
	public static int streaktolive = 0;
//	public static int delay_voice = 0;
	public static boolean die = false;
	int t_delay_flash = 0;
	int delay_flash = 0;
	int th_huong;
	boolean shoot_player = false;
	boolean flash_player = false;
	boolean change_weapon_player = false;
	int cd_voice = 0;
	int cd_delay = 0;
	boolean voice_long = false;
	
	int[] weapon = new int[7]; //Chua so luong tung loai dan.
	/*
	 * 1 - dan thuong.
	 * 2 - dan xuyen 2 lop.
	 * 3 - phao.
	 * 4 - nem bom.
	 * 5 - min.
	 * 6 - sua tank.
	 */
	int typeweapon ;//Loai dan dang dung.
	
	public TanksPlayerC(int x, int y, int huong, int type,  int size) {
		super(x, y, huong, type, size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.size = size;
		this.type = type;
		this.armor = 9999;
		this.color = Color.getHSBColor(0.30f,0.75f,0.75f);

			
		this.delay_shoot = 30;
		this.typeweapon = 1;
		
		TanksPlayerC.score = 0;
		
		weapon[1] = weapon[2] = weapon[3] = 10000;
		
		TanksC.player ++;
		this.N_player = TanksC.player; 
		O_Tanks.add(this);

		super.Move();
	}

	public void CallTanks()
	{
		
		if(TanksPlayerC.streaktolive > 0)
			TanksPlayerC.streaktolive--;
//		if(TanksPlayer.delay_voice > 0)
//			TanksPlayer.delay_voice--;
//		System.out.println(TanksPlayer.delay_voice);	
//		System.out.println(TanksPlayer.streaktolive);
		
		if(this.t_delay_shoot >0)
			this.t_delay_shoot--;
		if(this.shoot_player)
			{
				this.UseWeapon();
				this.shoot_player = false;
			}
		if(this.t_delay_flash >0)
		{
			this.t_delay_flash--;
			if(voice_long == true)
//				System.out.println(this.t_delay_flash);
			if(this.t_delay_flash == 0)
				audio.Tank_flash_cd_finished();
		}

		if(this.flash_player)
		{
			this.Flash();
			this.flash_player = false;
		}
		
		

		if(this.change_weapon_player)
		{
			this.SetWeapon();
			this.change_weapon_player = false;
		}
	
		//Neu chua nha phim thi di chuyen.
		if(!stop)
		{
			if(this.delay_turn == 0)
			{
				//-----------------------------
				super.DelRec();
			
				if(this.th_huong == this.huong || this.th_huong == 4 )
				{
	
					boolean check = false;
					int lui = 0;//Huong sau lung xe Tank.
					
					
					if(this.th_huong == 4)
					{
						if(this.huong == 0){lui = 2; check = Check_Turn(lui); }
						if(this.huong == 1){lui = 3; check = Check_Turn(lui);}
						if(this.huong == 2){lui = 0; check = Check_Turn(lui);}
						if(this.huong == 3){lui = 1; check = Check_Turn(lui);}
						
						
					}
					else
						check = Check_Turn(this.huong);
					
					if(check == true)
					{
						audio.Tank_move();
						if(this.th_huong == 4)
						{
							int t =this.huong;
							this.huong = lui;
							super.Auto_Move(1);
							this.huong = t;
							
							
						}
						else		
							super.Auto_Move(1);
					
					}
					else
						super.Move();
					
				}
				else
				{
					super.Setlc(this.X, this.Y, this.th_huong);//Quay theo th_huong.

					this.delay_turn = 10;
				}
				
				//-----------------------------				
			}else
				this.delay_turn --;
		}
		
		
		
	}
	
	public boolean Check_Turn(int huong)
	{
		
			
		if(huong == 0)
		{
	

			if(super.True_Move(this.X, this.Y - 2, huong))
				{

						return true;	
				}

			
		}else	
		if(huong == 1)
		{

			if(super.True_Move(this.X + 32, this.Y, huong))
				{

					return true;
				}
			
			
		}else
		if(huong == 2)
		{


			if(super.True_Move(this.X, this.Y + 32, huong)) 
				{

						return true;
				}

		
		}else	
		if(huong == 3)
		{

			if(super.True_Move(this.X - 2, this.Y, huong))
				{

						return true;
				}

			
		}
		return false;
	}

	
	public void Flash()
	{
		int distance = 0;
		

//		if(voice_long == true)
//			voice_long = false;
		//if(this.t_delay_flash > 0 && this.live && cd_voice == 0 && this.t_delay_flash>400)
		if(this.t_delay_flash > 0 && this.live && cd_voice == 0)
		{
			audio.Tank_flash_cd_unfinished();
			cd_voice++;
//			if(voice_long == false)
//				voice_long = true;
			cd_delay = this.t_delay_flash;
		}

		//if(this.t_delay_flash > 0 && this.live && cd_voice == 1 && this.t_delay_flash>200)
		if(this.t_delay_flash > 0 && this.live && cd_voice == 1 && this.t_delay_flash <(this.cd_delay-170))
		{
			audio.Tank_flash_cd_unfinished_2();
			cd_voice++;
			cd_delay = this.t_delay_flash;
		}
	
		//if(this.t_delay_flash > 0 && this.live && cd_voice == 2 && this.t_delay_flash>100)
		if(this.t_delay_flash > 0 && this.live && cd_voice == 2 && this.t_delay_flash <(this.cd_delay-350))
		{
			audio.Tank_flash_cd_unfinished_3();
			cd_voice++;
		}
		
		if(this.t_delay_flash == 0 && this.live)
		{
			cd_voice = 0;
			this.t_delay_flash = this.delay_flash;
		

			 
			if(this.huong == 0)
			{
				if(this.Y-90 > 25)
					if(MapC.map[this.X][this.Y-90].Get()==0 && MapC.map[this.X+14][this.Y-90].Get()==0 && MapC.map[this.X+29][this.Y-90].Get()==0
					&& MapC.map[this.X][this.Y-75].Get()==0	&& MapC.map[this.X+14][this.Y-75].Get()==0 && MapC.map[this.X+29][this.Y-75].Get()==0
					&& MapC.map[this.X][this.Y-60].Get()==0	&& MapC.map[this.X+14][this.Y-60].Get()==0 && MapC.map[this.X+29][this.Y-60].Get()==0)
					{
	//					this.flash_effect();
						super.DelRec();
						super.Setlc(this.X, this.Y-90, this.huong);
						audio.Tank_flash();
					}
					else
					{
						//distance = this.Pc_View(this.X+10,this.Y-10,0,-1);
						distance = this.Pc_View(this.X,this.Y-10,0,-1);
						super.DelRec();
						if(distance !=0)
							this.Y -=8;
						super.Setlc(this.X,this.Y-distance, this.huong);
						audio.Tank_flash_fail();
					}
				else
					audio.Tank_flash_miss();
			}
			else
			if(this.huong == 1)
			{
				if(this.X+120 < 844)
					if(MapC.map[this.X+120][this.Y].Get()==0 && MapC.map[this.X+120][this.Y+14].Get()==0 && MapC.map[this.X+120][this.Y+29].Get()==0
					&& MapC.map[this.X+105][this.Y].Get()==0	&& MapC.map[this.X+105][this.Y+14].Get()==0 && MapC.map[this.X+105][this.Y+29].Get()==0
					&& MapC.map[this.X+90][this.Y].Get()==0	&& MapC.map[this.X+90][this.Y+14].Get()==0 && MapC.map[this.X+90][this.Y+29].Get()==0)
					{
	//					this.flash_effect();
						super.DelRec();
						super.Setlc(this.X+90, this.Y, this.huong);
						audio.Tank_flash();
					}
					else
					{
						//distance = this.Pc_View(this.X+40,this.Y+10,1,0);
						distance = this.Pc_View(this.X+40,this.Y,1,0);
						super.DelRec();
						if(distance !=0)
							this.X +=8;
						super.Setlc(this.X+distance, this.Y, this.huong);				
						audio.Tank_flash_fail();
					}
				else
					audio.Tank_flash_miss();
			}
				
			else
			if(this.huong == 2)
			{
				if(this.Y+120 < 694)
					if(MapC.map[this.X][this.Y+120].Get()==0 && MapC.map[this.X+14][this.Y+120].Get()==0 && MapC.map[this.X+29][this.Y+120].Get()==0
					&& MapC.map[this.X][this.Y+105].Get()==0	&& MapC.map[this.X+14][this.Y+105].Get()==0 && MapC.map[this.X+29][this.Y+105].Get()==0
					&& MapC.map[this.X][this.Y+90].Get()==0	&& MapC.map[this.X+14][this.Y+90].Get()==0 && MapC.map[this.X+29][this.Y+90].Get()==0)
					{
	//					this.flash_effect();
						super.DelRec();
						super.Setlc(this.X, this.Y+90, this.huong);
						audio.Tank_flash();
					}
					else
					{
						//distance = this.Pc_View(this.X+10,this.Y+40,0,1);
						distance = this.Pc_View(this.X,this.Y+40,0,1);
						super.DelRec();
						if(distance !=0)
							this.Y +=8;
						super.Setlc(this.X,this.Y+distance, this.huong);
						audio.Tank_flash_fail();
					}
				else
					audio.Tank_flash_miss();
			}
			else
			if(this.huong == 3)
			{
				if(this.X-90 > 25)
					if(MapC.map[this.X-90][this.Y].Get()==0 && MapC.map[this.X-90][this.Y+14].Get()==0 && MapC.map[this.X-90][this.Y+29].Get()==0
					&& MapC.map[this.X-75][this.Y].Get()==0	&& MapC.map[this.X-75][this.Y+14].Get()==0 && MapC.map[this.X-75][this.Y+29].Get()==0
					&& MapC.map[this.X-60][this.Y].Get()==0	&& MapC.map[this.X-60][this.Y+14].Get()==0 && MapC.map[this.X-60][this.Y+29].Get()==0)
					{
	//					this.flash_effect();
						super.DelRec();
						super.Setlc(this.X-90, this.Y, this.huong);
						audio.Tank_flash();
					}
					else
					{
						//distance = this.Pc_View(this.X-10,this.Y+10,-1,0);
						distance = this.Pc_View(this.X-10,this.Y,-1,0);
						super.DelRec();
						if(distance !=0)
							this.X -=8;
						super.Setlc(this.X-distance, this.Y, this.huong);
						audio.Tank_flash_fail();
					}
				else
					audio.Tank_flash_miss();
			}
			
			
			this.t_delay_flash = 700;
					
		}
		
	}	

	public int Pc_View(int x, int y,int a_x, int a_y)
	{
		int flash_fail_pos = 0;
		int[] A= new int[30];// Do rong tam nhin cua xe tang la 30 don vi.
		boolean stop = false;
		
		
		
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{
			while(x>24 && y>24 && x<875 && y<725 && !stop)
			{
				stop = true;
				
				for(int i=0;i<30;i++)
					if(A[i] != 1)
					{	
						if(MapC.map[x][y+i].Get() !=0)
						{
							if(a_x == 1)
								flash_fail_pos-=0;
							else
								flash_fail_pos+=0;	
							return flash_fail_pos;
						}
						stop = false;
					}
				x = x+a_x;
				flash_fail_pos++;
			}
			
		}
		else
		{
			while(x>0 && y>0 && x<875 && y<725 && !stop)
			{
				stop = true;
				
				for(int i=0;i<30;i++)
					if(A[i] != 1)
					{	
						if(MapC.map[x+i][y].Get() !=0)
						{
//							System.out.print(i+" ");
							if(a_y == 1)
								flash_fail_pos-=0;
							else
								flash_fail_pos+=0;
//							System.out.println(flash_fail_pos);
							return flash_fail_pos;
						}
						stop = false;
					}
				y = y+a_y;
				flash_fail_pos++;
			}
			
		}
		return flash_fail_pos;
	}	


	
	public void SetWeapon()
	{
		
		if(this.typeweapon == 3)
		{
			this.typeweapon = 1;
		}
		else
			this.typeweapon++;
		
		//System.out.println(this.typeweapon);
	}
	
	public int GetWeapon()
	{
		if(this.weapon[this.typeweapon] >0)
		{
			this.weapon[this.typeweapon] --;
			return this.typeweapon;
		}
		else
			return -1;
	}
	
	public void AddWeapon(int t)
	{
		switch(t)
		{
			case 1:
				weapon[1] += 20;
				break;
			case 2:
				weapon[2] += 10;
				break;
			case 3:
				weapon[3] += 2;
				break;
			case 4:	
				weapon[4] += 1;
				break;
			case 5:
				weapon[5] += 5;
				break;
			case 6:
				break;
				
			default:
				break;
		} 
	}
	
	public void UseWeapon()
	{
		switch (this.GetWeapon())
		{
			case 1:
				super.Shoot();
				break;
			case 2:
				this.Shoot();
				break;
			case 3:
				this.Shoot();
				break;				
			default :
				break;
		}
	}

//	public void Shoot_w2()
//	{
//		if(this.t_delay_shoot == 0 && this.live )
//		{
//
//			this.t_delay_shoot = this.delay_shoot;
//		
//
//			Bulletv2 bullet1 = null ;
//			Bulletv2 bullet2 = null ;
//			if(this.huong == 0)
//			{
//				bullet1= new Bulletv2(this.X+13, this.Y-1, this.huong, 2, 10, 4, this.type, 1);
//				bullet2= new Bulletv2(this.X+13, this.Y-1, this.huong, 2, 10, 4, this.type, 2);
//			}
//			else
//			if(this.huong == 1)
//			{
//				bullet1 = new Bulletv2(this.X+30, this.Y+13, this.huong, 2, 10, 4, this.type,1);
//				bullet2 = new Bulletv2(this.X+30, this.Y+13, this.huong, 2, 10, 4, this.type,2);
//			}
//			else
//			if(this.huong == 2)
//			{
//				bullet1 = new Bulletv2(this.X+13, this.Y+30, this.huong, 2, 10, 4, this.type,1);
//				bullet2 = new Bulletv2(this.X+13, this.Y+30, this.huong, 2, 10, 4, this.type,2);
//			}
//			else
//			if(this.huong == 3)
//			{
//				bullet1 = new Bulletv2(this.X-1, this.Y+13, this.huong, 2, 10, 4, this.type,1);
//				bullet2 = new Bulletv2(this.X-1, this.Y+13, this.huong, 2, 10, 4, this.type,2);
//			}
//			if (bullet1 != null || bullet2 != null)
//			{
//				audio.Tank_shoot();
//				Tanks.O_Bullets.add(bullet1);
//				Tanks.O_Bullets.add(bullet2);
//			}	
//			
//			
//					
//		}
//	}	
//
//	public void Shoot_w3()
//	{
//		if(this.t_delay_shoot == 0 && this.live )
//		{
//
//			this.t_delay_shoot = this.delay_shoot;
//		
//
//			Bulletv3 bullet1 = null ;
//			Bulletv3 bullet2 = null ;
//			Bulletv3 bullet3 = null ;
//			if(this.huong == 0)
//			{
//				bullet1= new Bulletv3(this.X+13, this.Y-1, this.huong, 5, 10, 4, this.type, 1);
//				bullet2= new Bulletv3(this.X+13, this.Y-1, this.huong, 5, 10, 4, this.type, 2);
//				bullet3= new Bulletv3(this.X+13, this.Y-1, this.huong, 5, 10, 4, this.type, 0);
//			}
//			else
//			if(this.huong == 1)
//			{
//				bullet1 = new Bulletv3(this.X+30, this.Y+13, this.huong, 5, 10, 4, this.type,1);
//				bullet2 = new Bulletv3(this.X+30, this.Y+13, this.huong, 5, 10, 4, this.type,2);
//				bullet3 = new Bulletv3(this.X+30, this.Y+13, this.huong, 5, 10, 4, this.type,0);
//			}
//			else
//			if(this.huong == 2)
//			{
//				bullet1 = new Bulletv3(this.X+13, this.Y+30, this.huong, 5, 10, 4, this.type,1);
//				bullet2 = new Bulletv3(this.X+13, this.Y+30, this.huong, 5, 10, 4, this.type,2);
//				bullet3 = new Bulletv3(this.X+13, this.Y+30, this.huong, 5, 10, 4, this.type,2);
//			}
//			else
//			if(this.huong == 3)
//			{
//				bullet1 = new Bulletv3(this.X-1, this.Y+13, this.huong, 5, 10, 4, this.type,1);
//				bullet2 = new Bulletv3(this.X-1, this.Y+13, this.huong, 5, 10, 4, this.type,2);
//				bullet3 = new Bulletv3(this.X-1, this.Y+13, this.huong, 5, 10, 4, this.type,2);
//			}
//			if (bullet1 != null || bullet2 != null || bullet3 != null)
//			{
//				audio.Tank_shoot();
//				Tanks.O_Bullets.add(bullet1);
//				Tanks.O_Bullets.add(bullet2);
//				Tanks.O_Bullets.add(bullet3);
//			}	
//			
//			
//					
//		}
//	}		
	
	public void Pressed(KeyEvent e) {

		int key = -1;
		boolean fire = false;
		boolean flash = false;
		boolean switchWeapon = false;

		if(this.N_player == 1){

			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: key = 0;  break; 
	            case KeyEvent.VK_RIGHT: key = 1;  break;
	            case KeyEvent.VK_DOWN: key = 2;  break;
	            case KeyEvent.VK_LEFT: key = 3;  break;
	            case KeyEvent.VK_N: key = 4; break;
	            case KeyEvent.VK_M: fire = true;  break;
	            case KeyEvent.VK_B: flash = true;  break;
	            case KeyEvent.VK_COMMA: switchWeapon = true;  break;
	            //case KeyEvent.VK_COMMA: SetWeapon(); break;
	            
				default: break;
	            
	            
	        }
		}	
		else{

		switch (e.getKeyCode()) {
			case KeyEvent.VK_W: key = 0;  break; 
		    case KeyEvent.VK_D: key = 1;  break;
		    case KeyEvent.VK_S: key = 2;  break;
		    case KeyEvent.VK_A: key = 3;  break;
		    case KeyEvent.VK_Q: key = 4; break;
		    case KeyEvent.VK_SPACE: fire = true;  break;
		    case KeyEvent.VK_C: flash = true;  break;
		    case KeyEvent.VK_E: SetWeapon(); break;
		    
			default: break;
			}
		}

		if(fire == true  )
		{
			
			this.shoot_player = true;


		}
		else
		{

				if(key !=-1)
				{
					this.th_huong = key;

					this.stop = false;
				}

		}
		
		if(flash == true  )
		{
			
			this.flash_player = true;


		}
		else
		{

				if(key !=-1)
				{
					this.th_huong = key;

					this.stop = false;
				}

		}		

		if(switchWeapon == true  )
		{
			
			this.change_weapon_player = true;


		}
		else
		{

				if(key !=-1)
				{
					this.th_huong = key;

					this.stop = false;
				}

		}	
	
	}
	

	public void flash_effect()
	{
		this.color=new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),30);
	}
}
