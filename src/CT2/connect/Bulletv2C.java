package CT2.connect;

import java.awt.Color;
import java.awt.Graphics;

public class Bulletv2C extends BulletC{

	int host;//Dan tanks bot la 1, dan dong minh la 2.
	int sequenceNumber;
	int lv=0;
	public static int trungDongThoi = 0;
	public Bulletv2C(int x, int y, int huong, int type, int wi, int le, int host, int sequenceNumber) {
		super(x, y, huong, type, wi, le, host);

		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.type = type;//loai dan.(1 xuyen 1 lan).
		//Phien ban nang cap la Ma Dan
		this.maDan = 1;
		this.host = host;
		this.sequenceNumber = sequenceNumber;
		this.length = le;
		this.width = wi;
		
		//if(type ==1) lv =1;
		lv = type;
		
		this.color = Color.getHSBColor(0.16f,0.75f,0.75f);
		TanksC.O_Bullets.add(this);
		super.Move();
		

	}
	
	public void CallBullets()
	{

		
		if(lv >0)
		{
			if(Destroy_Tanks()||Destroy_Wall()||Destroy_Bullet())
			{
				lv--;
			

			}
				
			// Di chuyen toi truoc 1 don vi.
			if(this.huong == 0) { this.Y --;}
			if(this.huong == 1) { this.X ++;}
			if(this.huong == 2) { this.Y ++;}
			if(this.huong == 3)	{ this.X--;}
		}
		
		if(lv <=0 || this.X<25 || this.X >875 || this.Y<25 || this.Y>725)
		{

			this.Del_Bullet();
		}
	}
	
	public void changeDirection()
	{
		int temp;
		temp = this.width;
		this.width = this.length;
		this.length = temp;
		if(this.huong == 0 && sequenceNumber == 1)
		{
			this.huong = 1;
			this.Y +=5;
		}
		else
		if(this.huong == 0 && sequenceNumber == 2)
		{
			this.huong = 3;
			this.Y +=5;
		}
		else
		if(this.huong == 1 && sequenceNumber == 1)
		{
			this.huong = 2;
			this.X -= 5;
		}
		else
		if(this.huong == 1 && sequenceNumber == 2)
		{
			this.huong = 0;
			this.X -= 5;
		}
		else
		if(this.huong == 2 && sequenceNumber == 1)
		{
			this.huong = 3;
			this.Y -=5;
		}
		else
		if(this.huong == 2 && sequenceNumber == 2)
		{
			this.huong = 1;
			this.Y -=5;
		}
		else
		if(this.huong == 3 && sequenceNumber == 1)
		{
			this.huong = 0;
			this.X += 5;
		}
		else
		if(this.huong == 3 && sequenceNumber == 2)
		{
			this.huong = 2;
			this.X += 5;
		}		
	}

	public boolean Destroy_Tanks()
	{
		
		int dich = 2;
		boolean t= false;
		if(this.host == 2)
			dich = 3;
		

		if(this.huong == 0)
			for(TanksC o:TanksC.O_Tanks)
			{
				if(o.live ==true && o.type == dich)
				if( ((this.X>=o.X && this.X<=(o.X+o.size -1)) || (this.X+this.width -1>=o.X && this.X+this.width -1<=(o.X+o.size -1))) && (o.Y +o.size -1 == this.Y ))
				{
					if(o.armor>0)
						o.armor--;
					else
						o.Del_Tank();
					t = true;
				}
					
			}
		if(this.huong == 1)
			for(TanksC o:TanksC.O_Tanks)
			{
				if(o.live ==true &&o.type == dich)
				if( ((this.Y>=o.Y && this.Y<=(o.Y+o.size -1)) || (this.Y+this.length-1>=o.Y && this.Y+this.length-1<=(o.Y+o.size -1))) && o.X == this.X )
				{
					if(o.armor>0)
						o.armor--;
					else
						o.Del_Tank();
					t = true;
				}
					
			}
		
		if(this.huong == 2)
			for(TanksC o:TanksC.O_Tanks)
			{
				if(o.live ==true &&o.type == dich)
				if( ((this.X>=o.X && this.X<=(o.X+o.size -1)) || (this.X+this.width-1>=o.X && this.X+this.width-1<=(o.X+o.size -1))) && o.Y == this.Y )
				{
					if(o.armor>0)
						o.armor--;
					else
						o.Del_Tank();
					t = true;
				}
					
			}
		if(this.huong == 3)
			for(TanksC o:TanksC.O_Tanks)
			{
				if(o.live ==true &&o.type == dich)
				if( ((this.Y>=o.Y && this.Y<=(o.Y+o.size -1)) || (this.Y+this.length-1>=o.Y && this.Y+this.length-1<=(o.Y+o.size -1))) && (o.X +o.size -1 == this.X ))
				{
					if(o.armor>0)
						o.armor--;
					else
						o.Del_Tank();
					t = true;
				}
					
			}
		
		return t;
	}
	
	public boolean Destroy_Wall()
	{
		

		boolean t= false;

			
		
			if(this.huong == 0)
				for(Playout o:Playout.O_Playout)
				{
					if(o.live ==true && o.type == 1)
					if( ((this.X>=o.x && this.X<=(o.x+24)) || (this.X+this.width -1>=o.x && this.X+this.width -1<=(o.x+24))) && (o.y +24 == this.Y ))
					{
						if(lv == 1)
							o.Del_Wall();
						else
						if(Bulletv2C.trungDongThoi%2 == 0)
						{
							changeDirection();
							Bulletv2C.trungDongThoi++;
						}
						else
						{
							changeDirection();
							o.Del_Wall();
							Bulletv2C.trungDongThoi++;
						}
						
						
						t = true;
					}
						
				}
			if(this.huong == 1)
				for(Playout o:Playout.O_Playout)
				{
					if(o.live ==true && o.type == 1)
					if( ((this.Y>=o.y && this.Y<=(o.y+24)) || (this.Y+this.length-1>=o.y && this.Y+this.length-1<=(o.y+24))) && o.x == this.X )
					{
						if(lv == 1)
							o.Del_Wall();
						else
						if(Bulletv2C.trungDongThoi%2 == 0)
						{
							changeDirection();
							Bulletv2C.trungDongThoi++;
						}
						else
						{
							changeDirection();
							o.Del_Wall();
							Bulletv2C.trungDongThoi++;
						}
						t = true;
					}
						
				}
			
			if(this.huong == 2)
				for(Playout o:Playout.O_Playout)
				{
					if(o.live ==true && o.type == 1)
					if( ((this.X>=o.x && this.X<=(o.x+24)) || (this.X+this.width-1>=o.x && this.X+this.width-1<=(o.x+24))) && o.y == this.Y )
					{
						if(lv == 1)
							o.Del_Wall();
						else
						if(Bulletv2C.trungDongThoi%2 == 0)
						{
							changeDirection();
							Bulletv2C.trungDongThoi++;
						}
						else
						{
							changeDirection();
							o.Del_Wall();
							Bulletv2C.trungDongThoi++;
						}
						t = true;
					}
						
				}
			if(this.huong == 3)
				for(Playout o:Playout.O_Playout)
				{
					if(o.live ==true && o.type == 1)
					if( ((this.Y>=o.y && this.Y<=(o.y+24)) || (this.Y+this.length-1>=o.y && this.Y+this.length-1<=(o.y+24))) && (o.x +24 == this.X ))
					{
						if(lv == 1)
							o.Del_Wall();
						else
						if(Bulletv2C.trungDongThoi%2 == 0)
						{
							changeDirection();
							Bulletv2C.trungDongThoi++;
						}
						else
						{
							changeDirection();
							o.Del_Wall();
							Bulletv2C.trungDongThoi++;
						}
						t = true;
					}
						
				}

		return t;
	}
	
	
	public boolean Destroy_Bullet()
	{
		

		boolean t= false;


		
		if(this.huong == 0)
			for(BulletC o:TanksC.O_Bullets)
			{
				if(o.live ==true && o.host != this.host && o.huong == 2)
				if( ((this.X>=o.X && this.X<=(o.X+o.width -1)) || (this.X+this.width -1>=o.X && this.X+this.width -1<=(o.X+o.width -1))) && (o.Y +o.length -1 == this.Y ))
				{
					o.Del_Bullet();
					t = true;
				}
					
			}
		if(this.huong == 1)
			for(BulletC o:TanksC.O_Bullets)
			{
				if(o.live ==true && o.host != this.host && o.huong == 3)
				if( ((this.Y>=o.Y && this.Y<=(o.Y+o.width -1)) || (this.Y+this.width-1>=o.Y && this.Y+this.width-1<=(o.Y+o.width -1))) && o.X == this.X +this.length-1)
				{
					o.Del_Bullet();
					t = true;
				}
					
			}
		
		if(this.huong == 2)
			for(BulletC o:TanksC.O_Bullets)
			{
				if(o.live ==true && o.host != this.host && o.huong == 0)
				if( ((this.X>=o.X && this.X<=(o.X+o.width -1)) || (this.X+this.width-1>=o.X && this.X+this.width-1<=(o.X+o.width -1))) && o.Y == this.Y + this.length-1)
				{
					o.Del_Bullet();
					t = true;
				}
					
			}
		if(this.huong == 3)
			for(BulletC o:TanksC.O_Bullets)
			{
				if(o.live ==true && o.host != this.host && o.huong == 1)
				if( ((this.Y>=o.Y && this.Y<=(o.Y+o.width -1)) || (this.Y+this.width-1>=o.Y && this.Y+this.width-1<=(o.Y+o.width -1))) && (o.X +o.length -1 == this.X ))
				{
					o.Del_Bullet();
					t = true;
				}
					
			}
		
		return t;
	}
	
//	public void Paint_Bullet(Graphics g)
//	{
//
//		switch(this.huong)
//		{
//			case 0: case 2:
//				g.setColor(this.color.brighter());
//				g.drawLine(this.X,this.Y,this.X,this.Y+10);
//			
//				g.setColor(this.color);
//				for(int i=1;i<=2;i++)
//					g.drawLine(this.X+i,this.Y,this.X+i,this.Y+10);
//				
//				g.setColor(this.color.darker());
//				g.drawLine(this.X+3,this.Y,this.X+3,this.Y+10);
//				break;
//		
//			case 1: case 3:
//		
//				g.setColor(this.color.brighter());
//				g.drawLine(this.X,this.Y,this.X+10,this.Y);
//				
//				g.setColor(this.color);
//				for(int i=1;i<=2;i++)
//					g.drawLine(this.X,this.Y+i,this.X+10,this.Y+i);
//				
//				g.setColor(this.color.darker());
//				g.drawLine(this.X,this.Y+3,this.X+10,this.Y+3);
//		}		
//	}
	


}
