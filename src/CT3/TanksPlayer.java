package CT3;

import java.awt.Color;
import java.awt.event.KeyEvent;


public class TanksPlayer extends Tanks  {

	public static int score;
	public static boolean die = false;
	int th_huong;
	boolean shoot_player = false;
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
	
	public TanksPlayer(int x, int y, int huong, int type,  int size) {
		super(x, y, huong, type, size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.size = size;
		this.type = type;
		this.color = Color.getHSBColor(0.30f,0.75f,0.75f);
		this.delay_shoot = 30;
		this.typeweapon = 1;
		
		TanksPlayer.score = 0;
		
		weapon[1] = 10000;
		Tanks.player ++;
		this.N_player = Tanks.player; 
		O_Tanks.add(this);

		super.Move();
	}

	public void CallTanks()
	{
		

		if(this.t_delay_shoot >0)
			this.t_delay_shoot--;
		if(this.shoot_player)
			{
				this.UseWeapon();
				this.shoot_player = false;
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
	
	public void SetWeapon()
	{
		
		if(this.typeweapon == 7)
		{
			this.typeweapon = 1;
		}
		else
			this.typeweapon++;
		
		
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
			default :
				break;
		}
	}
	
	public void Pressed(KeyEvent e) {

		int key = -1;
		boolean fire = false;

		if(this.N_player == 1){

			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: key = 0;  break; 
	            case KeyEvent.VK_RIGHT: key = 1;  break;
	            case KeyEvent.VK_DOWN: key = 2;  break;
	            case KeyEvent.VK_LEFT: key = 3;  break;
	            case KeyEvent.VK_N: key = 4; break;
	            case KeyEvent.VK_M: fire = true;  break;
	            case KeyEvent.VK_COMMA: SetWeapon(); break;
	            
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
		
	}

	public void Pressed1(String s) {

		int key = -1;
		boolean fire = false;


//		switch (s) {
//			case "UP": key = 0;  break;
//			case "RIGHT": key = 1;  break;
//			case "DOWN": key = 2;  break;
//			case "LEFT": key = 3;  break;
//			case "N": key = 4; break;
//			case "M": fire = true;  break;
//
//			default: break;
//
//
//		}

		if ("UP".equals(s)) {
			key = 0;
		} else if ("RIGHT".equals(s)) {
			key = 1;
		} else if ("DOWN".equals(s)) {
			key = 2;
		} else if ("LEFT".equals(s)) {
			key = 3;
		} else if ("N".equals(s)) {
			key = 4;
		} else if ("M".equals(s)) {
			fire = true;
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
		
	}
	
}
