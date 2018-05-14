package CT3.connect;




public class Syn {
	
//	static int stop_bullet = 3, t =1;
//	
//	 public synchronized void Threadbullet()
//	{			
//		try {
//		//*****************************	
//			
//		while(stop_bullet ==0)
//			wait();
//
//		
//		for(TanksClient o:TanksClient.O_Bullets)
//		{
//			if(o.live)
//			o.CallBullets();
//		}
//		CoTClient.Del_Objec();
//		MapClient.Paint();
//
//		if(TanksClient.new_tank != 0 && TanksBotClient.bot <50)
//		{
//			int hs = (int) (Math.random()*3+1);
//			CoTClient.Add_Tanks_Bot(TanksClient.new_tank*hs);
//			
//		}
//		TanksClient.new_tank = 0;
//		stop_bullet --;
//		if(stop_bullet == 0)
//		notify();
//		
//		//*****************************
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public synchronized void Threadbot()
//	{
//		try {
//			//*****************************	
//				
//			while(stop_bullet >0)
//				wait();
//
//
//			for(TanksClient o:TanksClient.O_Tanks)
//			{
//
//				if(o.live)
//				o.CallTanks();
//			}
//
//			
//			stop_bullet = 3;
//			notify();
//			
//			//*****************************
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//
//	}

}
