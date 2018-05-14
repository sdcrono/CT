package CT3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ClashofTanks 
{
	public static boolean pause = true;
	public static boolean Co_Op = false;
	public static boolean Intro = true;
	
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
				t1 = new TanksBot(i,25,2,2,30);
				nb ++;
				i += 30;
				if(nb == n) break;
			
			}
			
			if(Map.map[i][700].Get() == 0 && Map.map[i+24][700].Get() == 0)
			{	
				t1 = new TanksBot(i,700,2,2,30);
				nb ++;
				i += 30;
				if(nb == n) break;
			
			}
			
			i++;
				
		}
		
	}

	public static Socket cl;
	public static DataOutputStream gui;
	public static DataInputStream nhan;
	public static ServerSocket ss;
	public static Vector<MTh2> MThV= new Vector<MTh2>(1,1);
	public static int r;

	
	public static void main(String[] args) throws Exception 
	{
//		cl = new Socket ("localhost", 12340 );
//		
//		gui = new DataOutputStream (cl.getOutputStream());
//		nhan = new DataInputStream (cl.getInputStream());
		
		ss=new ServerSocket(12341);
		int dem = 1;
		while(true)
		{
			Socket sv;
			sv = ss.accept();
			MTh2 t=new MTh2(sv, dem);
			Tanks t2;
			t2 = new TanksPlayer(800,300+(50*(dem-1)),3,3,30);
			dem++;
			MThV.addElement(t);				
			t.getState();
			t.start();
			if(MThV.capacity() >= 3)
				break;
		}
		Syn syn = new Syn();
		Map.SetMap();
//		int coop = 1;
//		for(MTh2 t:ClashofTanks.MThV)
//		{
//			t.reply(coop);
//		}
		while(ClashofTanks.pause)
		{
			
		}

//		while(true)
//		{
//			String f = nhan.readUTF();
//			System.out.println(f);
//			if(f.equals("go")) break;
//		}		
		Tanks t1;
		//t1 = new TanksPlayer(800,300,3,3,30);
		t1 = new TanksBot(25,25,2,2,30);
		t1 = new TanksBot(25,75,2,2,30);
		t1 = new TanksBot(25,115,2,2,30);
		t1 = new TanksBot(25,150,2,2,30);
		t1 = new TanksBot(360,363,3,2,30);
		
//		if(ClashofTanks.Co_Op)
//		{
//			t1 = new TanksPlayer(800,425,3,3,30);
//		
//		}
//		else
//			t1 = new TanksBot(800,425,3,3,30); // Tanks dong minh.
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
				Score.Score("sc21.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Score.Score("sc11.txt");
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

