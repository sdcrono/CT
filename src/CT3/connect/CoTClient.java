package CT3.connect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;


public class CoTClient 
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
			for(TanksClient i:TanksClient.O_Bullets)
			{
			
				if(i.live == false)
				{
					t = TanksClient.O_Bullets.indexOf(i);
					stop = false;
				}
				
			}
			
			if(!stop)
			TanksClient.O_Bullets.remove(t);
		
		}
	}
	
//	public static void Add_Tanks_Bot(int n) //Sinh toi da n Tank.
//	{
//		int nb = 0; // dem so tank da sinh ra. 
//		TanksClient t1;
//		int i= 425;
//		while( i<=570)
//		{
//			if(MapClient.map[i][25].Get() == 0 && MapClient.map[i+24][25].Get() == 0)
//			{	
//				t1 = new TanksBotClient(i,25,2,2,30);
//				nb ++;
//				i += 30;
//				if(nb == n) break;
//			
//			}
//			
//			if(MapClient.map[i][700].Get() == 0 && MapClient.map[i+24][700].Get() == 0)
//			{	
//				t1 = new TanksBotClient(i,700,2,2,30);
//				nb ++;
//				i += 30;
//				if(nb == n) break;
//			
//			}
//			
//			i++;
//				
//		}
//		
//	}

	public static Socket cl1;
	public static Socket cl2;
	public static DataOutputStream gui;
	public static DataInputStream nhan;

	
	public static void main(String[] args) throws Exception 
	{
		cl1 = new Socket ("localhost", 12340 );
		
		gui = new DataOutputStream (cl1.getOutputStream());
		nhan = new DataInputStream (cl1.getInputStream());
		Syn syn = new Syn();
		MapClient.SetMap();



		while(true)
		{
			String f = nhan.readUTF();
			System.out.println(f);
			if(f.equals("go")) break;
		}
		
		
//		while(CoTClient.pause)
//		{
//			int f1 = nhan.readInt();
//			System.out.println(f1);
//			if(f1 == 1){
				
//				//break;			
//			}
//		}
		cl2 = new Socket ("localhost", 12341 );
		gui = new DataOutputStream (cl2.getOutputStream());
		nhan = new DataInputStream (cl2.getInputStream());
		
		CoTClient.pause = false;
		
		TanksClient t1;
		t1 = new TanksPlayerClient(800,300,3,3,30);
		t1 = new TanksBotClient(25,25,2,2,30);
		t1 = new TanksBotClient(25,75,2,2,30);
		t1 = new TanksBotClient(25,115,2,2,30);
		t1 = new TanksBotClient(25,150,2,2,30);
		t1 = new TanksBotClient(360,363,3,2,30);
		
////		if(ClashofTanks.Co_Op)
////		{
////			t1 = new TanksPlayer(800,425,3,3,30);
////		
////		}
////		else
////			t1 = new TanksBot(800,425,3,3,30); // Tanks dong minh.
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Thread bot = new Thread(new ThreadTanks1(syn));
//
//		Thread bullet = new Thread(new Threadbullet1(syn));
//		
//		bullet.start();
//		
//		try {
//			Thread.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	
//
//		bot.start();
	
	}
	
	

}


////*********************************************
///* Luong xu ly dan.
//*/
//class Threadbullet1 implements Runnable
//{
//	Syn syn;
//	public Threadbullet1(Syn syn)
//	{
//		this.syn = syn;
//	}
//	
//	public void run()
//	{
//		while(TanksClient.player != 0)
//		{
//
//			if(CoTClient.pause)
//			{
//				
//			}
//			else
//			{
//				syn.Threadbullet();
//			
//				try {
//					Thread.sleep(2);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//			}
//			
//
//		}
//		MapClient.Paint();
//		
//		System.out.println(TanksPlayerClient.score);
//		if(CoTClient.Co_Op==true)
//		{
//			try {
//				ScoreClient.Score("sc21.txt");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			try {
//				ScoreClient.Score("sc11.txt");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//
//
//	}
//	
//}
//
////*********************************************
///* Luong xu ly tanks bot.
//*/
//class ThreadTanks1 implements Runnable
//{
//	Syn syn;
//	
//	public ThreadTanks1(Syn syn)
//	{
//		this.syn = syn;
//	}
//	
//	public void run()
//	{
//
//		while(TanksClient.player != 0)
//		{
//			if(CoTClient.pause)
//			{
//				
//			}
//			else
//			{
//				syn.Threadbot();
//
//				try {
//					Thread.sleep(8);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//}

