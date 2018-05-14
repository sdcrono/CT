package CT2.connect;

import java.util.Iterator;




public class Syn {
	
	static int stop_bullet = 3, t =1;
	
	 public synchronized void Threadbullet()
	{			
		try {
		//*****************************	
			
		while(stop_bullet ==0)
			wait();

		
		for(TanksC o:TanksC.O_Bullets)
//		for(Iterator<Bullet> o = Tanks.O_Bullets.iterator(); o.hasNext();)
		{
			if(o.live)
			o.CallBullets();
		}
		ClashofTanksC.Del_Objec();
		MapC.Paint();

		if(TanksC.new_tank != 0 && TanksBotC.bot <50)
		{
			int hs = (int) (Math.random()*3+1);
			ClashofTanksC.Add_Tanks_Bot(TanksC.new_tank*hs);
			
		}
		TanksC.new_tank = 0;
		stop_bullet --;
		if(stop_bullet == 0)
		notify();
		
		//*****************************
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void Threadbot()
	{
		try {
			//*****************************	
				
			while(stop_bullet >0)
				wait();


			for(TanksC o:TanksC.O_Tanks)
			{

				if(o.live)
				o.CallTanks();
			}

			
			stop_bullet = 3;
			notify();
			
			//*****************************
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}

}
