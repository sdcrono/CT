package CT3;




public class Syn {
	
	static int stop_bullet = 3, t =1;
	
	 public synchronized void Threadbullet()
	{			
		try {
		//*****************************	
			
		while(stop_bullet ==0)
			wait();

		
		for(Tanks o:Tanks.O_Bullets)
		{
			if(o.live)
			o.CallBullets();
		}
		ClashofTanks.Del_Objec();
		Map.Paint();

		if(Tanks.new_tank != 0 && TanksBot.bot <50)
		{
			int hs = (int) (Math.random()*3+1);
			ClashofTanks.Add_Tanks_Bot(Tanks.new_tank*hs);
			
		}
		Tanks.new_tank = 0;
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


			for(Tanks o:Tanks.O_Tanks)
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
