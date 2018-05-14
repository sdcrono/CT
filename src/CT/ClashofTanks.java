package CT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ClashofTanks 
{
	public static boolean pause = true;
	public static boolean Co_Op = false;
	public static boolean Intro = true;
	static Audio audio1 = new Audio();
	public static void Del_Objec()
	{
		boolean stop = false;
		int t = -1;
		while(!stop)
		{
			stop = true;
			for(Tanks i:Tanks.O_Bullets)
			{
			
				if(i.live == false)
				{
					t = Tanks.O_Bullets.indexOf(i);
					stop = false;
				}
				
			}
			
			if(!stop)
			Tanks.O_Bullets.remove(t);
		
		}
	}
	
	public static void Add_Tanks_Bot(int n) //Sinh toi da n Tank.
	{
		int nb = 0; // dem so tank da sinh ra. 
		Tanks t1;
		int i= 425;
		while( i<=570)
		{
			if(Map.map[i][25].Get() == 0 && Map.map[i+24][25].Get() == 0)
			{	
				t1 = new TanksBotv2(i,25,2,2,30);
				nb ++;
				i += 30;
				if(nb == n) break;
			
			}
			
			if(Map.map[i][700].Get() == 0 && Map.map[i+24][700].Get() == 0)
			{	
				t1 = new TanksBotv2(i,700,2,2,30);
				nb ++;
				i += 30;
				if(nb == n) break;
			
			}
			
			i++;
				
		}
		
	}
	
	public static void main(String[] args) throws Exception 
	{

		Syn syn = new Syn();
		Map.SetMap();
		Tanks t1;
		t1 = new TanksPlayerv2(800,300,3,3,30);
//		t1 = new TanksPlayer(750,300,3,3,30);
//		t1 = new TanksPlayer(750,400,3,3,30);
//		t1 = new TanksPlayer(25,690,3,3,30);
//		t1 = new TanksPlayer(300,330,3,3,30);
		audio1.Intro_theme();
		while(ClashofTanks.pause)
		{
			Map.Paint();
		}
		audio1.Intro_theme_end();
		audio1.BG_theme();

		
////		t1 = new TanksBoss(25,25,2,2,50);
		t1 = new TanksBoss(330,350,2,2,50);
		t1 = new TanksBotv2(25,75,2,2,30);
		t1 = new TanksBotv2(25,115,2,2,30); 
		t1 = new TanksBotv2(25,150,2,2,30);
		t1 = new TanksBotv2(26,363,2,2,30);
		
//		t1 = new TanksBossv2(750,600,0,2,30);
//		t1 = new TanksBotv2(750,600,0,2,30);
//		t1 = new TanksBotv2(750,100,0,2,30);
//		t1 = new TanksBotv3(25,300,0,2,30);
//		t1 = new TanksBotv2(360,363,3,2,30);
		
		if(ClashofTanks.Co_Op)
		{
//			t1 = new TanksPlayer(800,420,3,3,30);
		
		}
		else
		{
			t1 = new TanksBot(800,420,3,3,30); // Tanks dong minh.
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread bot = new Thread(new ThreadTanks(syn));

		Thread bullet = new Thread(new Threadbullet(syn));
		
		bullet.start();
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	

		bot.start();
	
	}
	
	

}


//*********************************************
/* Luong xu ly dan.
*/
class Threadbullet implements Runnable
{
	Syn syn;
	public Threadbullet(Syn syn)
	{
		this.syn = syn;
	}
	
	public void run()
	{
		while(Tanks.player != 0)
		{

			if(ClashofTanks.pause)
			{
				
			}
			else
			{
				syn.Threadbullet();
			
				try {
					Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			

		}
		Map.Paint();
		
		System.out.println(TanksPlayer.score);
		if(ClashofTanks.Co_Op==true)
		{
			try {
				Score.Score("sc2.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Score.Score("sc1.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


	}
	
}

//*********************************************
/* Luong xu ly tanks bot.
*/
class ThreadTanks implements Runnable
{
	Syn syn;
	
	public ThreadTanks(Syn syn)
	{
		this.syn = syn;
	}
	
	public void run()
	{

		while(Tanks.player != 0)
		{
			if(ClashofTanks.pause)
			{
				
			}
			else
			{
				syn.Threadbot();

				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

