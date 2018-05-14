//package test;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.Scanner;
//
////////
//public class MTh extends Thread{
//	Socket s;
//	DataOutputStream gui;
//	public MTh(Socket sv) {
//		this.s=sv;
//	}
//
//	public void reply(String traVe)
//	{
//		try {
//			gui.writeUTF(traVe);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void run(){
//		
//		
////		try {
////					
////		 
////			DataInputStream nhan=new DataInputStream(s.getInputStream());
////			gui=new DataOutputStream(s.getOutputStream());
////			
////			
////			
////			
////			while(true)
////			{
//////				Scanner bp = new Scanner(System.in);
//////				String s1 = bp.nextLine();
////				
////				
////				String s1 = nhan.readUTF();
////				
////				System.out.println("Port "+s.getPort()+": "+s1);
////				ServerMTh.brmsg(s1,s.getPort());
////				
//////				gui.writeUTF("dung");
////				
////			}
//			
//		try {		
//			 
//			DataInputStream nhan=new DataInputStream(s.getInputStream());
//			gui=new DataOutputStream(s.getOutputStream());	
//			
//			while(true)
//			{
//				int x = nhan.readInt();
//				if(x < ServerMTh.r)
//					gui.writeUTF("nho hon");
//					else if(x > ServerMTh.r)
//						gui.writeUTF("lon hon");
//					else
//						ServerMTh.pm(s.getPort());;
//
//				
//			}			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		
//		
//
//	}
//}

//bbbb
package CT2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import CT2.Tanks;
import CT3.MTh;

//////
public class MTh3 extends Thread{
	Socket s;
	int N_player;
	DataOutputStream gui;
	DataInputStream nhan;
	
	public MTh3(Socket sv, int numPlayer) {
		try {
			this.s=sv;
			nhan=new DataInputStream(s.getInputStream());
			gui=new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void reply(String traVe)
	{
		try {
			gui.writeUTF(traVe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reply(int traVe)
	{
		try {
			gui.writeInt(traVe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		
		try {
			while(true)
			{
//				Scanner bp = new Scanner(System.in);
//				String s1 = bp.nextLine();			
				
				String s1 = nhan.readUTF();
				
				System.out.println("Port "+s.getPort()+": "+s1);
				for(Tanks o:Tanks.O_Tanks)
					if(o.type==3)
					{
						if(o.N_player == this.N_player)
						{
							if(s1.equals("UP") || s1.equals("DOWN")|| s1.equals("LEFT") || s1.equals("RIGHT") || s1.equals("M") || s1.equals("B") || s1.equals("CO") || s1.equals("G") || s1.equals("H") || s1.equals("J") || s1.equals("K") || s1.equals("N"))
								o.Pressed1(s1);
							if(s1.equals("UPS") || s1.equals("DOWNS")|| s1.equals("LEFTS") || s1.equals("RIGHTS") || s1.equals("MS") || s1.equals("BS") || s1.equals("COS") || s1.equals("GS") || s1.equals("HS") || s1.equals("JS") || s1.equals("KS") || s1.equals("NS"))
							{
								{
									if(s1.equals("UPS"))
										if(o.huong == 0)
											o.stop = true;

									if(s1.equals("DOWNS"))
										if(o.huong == 2)
											o.stop = true;

									if(s1.equals("LEFTS"))
										if(o.huong == 3)
											o.stop = true;

									if(s1.equals("RIGHTS"))
										if(o.huong == 1)
											o.stop = true;
									if(s1.equals("MS")){}
									if(s1.equals("NS"))
										o.stop = true;		
								}
							}

						}
					}
				//ServerMTh.brmsg(s1,s.getPort());
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
//				s1 = String.valueOf(s.getPort())+" : "+s1;
//				for(MTh2 t:Server.MThV)
//				{
//					t.reply(s1);
//				}	

				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				gui.close();
				nhan.close();
				s.close();
				
			}catch (IOException ei) {

			}finally{
				Server1.MThV.removeElement(this);
			}
			
		}
		
		
		

	}
}


////ccccc
//package test;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.Scanner;
//import java.util.Vector;
//
////////
//public class MTh extends Thread{
//	Socket s;
//	DataOutputStream gui;
//	DataInputStream nhan;
//	
//	
//	public MTh(Socket sv) {
//		try {
//			this.s=sv;
//			nhan=new DataInputStream(s.getInputStream());
//			gui=new DataOutputStream(s.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public void reply(String traVe)
//	{
//		try {
//			gui.writeUTF(traVe);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void run(){
//		
//		
//		try {
//			while(true)
//			{
////				Scanner bp = new Scanner(System.in);
////				String s1 = bp.nextLine();			
//
//				int a1 = nhan.readInt();
//				if(ServerMTh.f == true)
//				{
//					this.reply("Thua roi");
//					break;
//				}
//				if(a1 < ServerMTh.rand)
//					this.reply("nho hon");
//				else
//					if(a1 > ServerMTh.rand)
//						this.reply("lon hon");
//					else
//					{
//						this.reply("Chinh xac");
//						for(MTh t:ServerMTh.MThV)
//						{
//							if(!t.equals(this))
//							t.reply("Thua roi");
//						}	
//						ServerMTh.f = true;
//						break;
//
//					}
//
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
////				String s1 = " ";
////				for(MTh t:ServerMTh.MThV)
////				{
////					t.reply(s1);
////				}	
//
//				
//			}
//
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//			try{
//				gui.close();
//				nhan.close();
//				s.close();
//				
//			}catch (IOException ei) {
//
//			}finally{
//				ServerMTh.MThV.removeElement(this);
//			}
//			
//		}
//		
//		
//		
//
//	}
//}

////dddd
//package test;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.Scanner;
//import java.util.Vector;
//
////////
//public class MTh extends Thread{
//	Socket s;
//	DataOutputStream gui;
//	DataInputStream nhan;
//	
//	public MTh(Socket sv) {
//		try {
//			this.s=sv;
//			nhan=new DataInputStream(s.getInputStream());
//			gui=new DataOutputStream(s.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public void reply(String traVe)
//	{
//		try {
//			gui.writeUTF(traVe);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void run(){
//		
//		
//		try {
//			while(true)
//			{
////				Scanner bp = new Scanner(System.in);
////				String s1 = bp.nextLine();			
//				
//				String s1 = nhan.readUTF();
//				
//				System.out.println("Port "+s.getPort()+": "+s1);
//				
//				ServerMTh.st.push(s1);
//				//ServerMTh.brmsg(s1,s.getPort());
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//				s1 = String.valueOf(s.getPort())+" : "+s1;
//				if(!ServerMTh.st.empty())
//				for(MTh t:ServerMTh.MThV)
//				{
//					String s2 = ServerMTh.st.pop(); 
//					t.reply(s2);
//				}	
//
//				
//			}
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//			try{
//				gui.close();
//				nhan.close();
//				s.close();
//				
//			}catch (IOException ei) {
//
//			}finally{
//				ServerMTh.MThV.removeElement(this);
//			}
//			
//		}
//		
//		
//		
//
//	}
//}

//package servermulthr;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.Scanner;
//
////////
//public class CvServer1 extends Thread{
//	 Socket s;
//	DataOutputStream gui;
//	public CvServer1(Socket sv) {
//		this.s=sv;
//	}
//
//	public void br(String in)
//	{
//		try {
//			gui.writeUTF(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void run(){
//		
//		
//		try {
//			DataInputStream nhancl=new DataInputStream(s.getInputStream());
//			gui=new DataOutputStream(s.getOutputStream());
//			
///*			Thread show = new Thread(new Runnable(){
//				public void run()
//				{
//					while(true){
//					try {
//						
//						
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//					//	e.printStackTrace();
//					}
//					}
//				}
//			});
//					
//		//	DataInputStream nhansv=new DataInputStream(System.in); 
//			
//			
//			show.start();
//			
//			*/
//			while(!server.stop)
//			{
////				Scanner bp = new Scanner(System.in);
////				String nhansv = bp.nextLine();
////				gui.writeUTF(nhansv);
//				
//				String kq = nhancl.readUTF();
//				System.out.println(s.getPort()+" "+kq);
//				if(Integer.parseInt(kq)>server.rand)
//				{
//					gui.writeUTF("Lon hon");
//				}else
//					if(Integer.parseInt(kq)<server.rand)
//					{
//						gui.writeUTF("Be hon");
//					}
//					else{
//						server.win(s.getPort());
//					}
//
//				
//			}
//			
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		
//		
//
//	}
//}

