//package test;
//
//import java.net.*;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.net.DatagramSocket;
//import java.io.*;
//import java.util.*;
//
//public class ClientMTh {
//	public static Socket cl;
//	public static DataOutputStream gui;
//	public static DataInputStream nhan;
//	public static int e = 0;
//	public static void main(String [] args) throws Exception, InterruptedException{
////		try{
////			cl = new Socket ("localhost", 12340 );
////			
////			gui = new DataOutputStream (cl.getOutputStream());
////			nhan = new DataInputStream (cl.getInputStream());
////			
////			Thread t = new Thread(new Runnable(){
////				public void run()
////				{
////					while(true){
////					try {
////						System.out.println();
////						System.out.println(nhan.readUTF());
////						
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////					//	e.printStackTrace();
////					}
////					}
////				}
////			});
////			
////			t.start();
////			while(true)
////			{
////			
////				Scanner bp = new Scanner(System.in);
////				String s = bp.nextLine();
////				gui.writeUTF(s);
////				
////	
////			}
////		
////		
////		
////		}
////			catch(IOException e){
////			e.printStackTrace();
////			
////		}
//		
//		try{
//			cl = new Socket ("localhost", 12340 );
//			
//			gui = new DataOutputStream (cl.getOutputStream());
//			nhan = new DataInputStream (cl.getInputStream());
//			
//			Thread t = new Thread(new Runnable(){
//				public void run()
//				{
//					while(true){
//					try {
//						System.out.println();
//						String s = nhan.readUTF();
//						System.out.println(s);
//						if(s.equals("Chien thang") || s.equals("Thua roi"))
//						{
//							e=1;
//							break;
//						}
//					} catch (IOException f) {
//						// TODO Auto-generated catch block
//					//	e.printStackTrace();
//					}
//					}
//				}
//			});
//			
//			t.start();
//			while(true)
//			{
//				if(e==1)
//					break;
//				Scanner bp = new Scanner(System.in);
//				if(e==1)
//					break;
//				int x = bp.nextInt();
//				if(e==1)
//					break;
//				gui.writeInt(x);
//				if(e==1)
//					break;
//				
//	
//			}
//		
//		
//		
//		}
//			catch(IOException f){
//			f.printStackTrace();
//			
//		}
//	
//	}
//}

//AAAA
//package test;
//
//import java.net.*;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.net.DatagramSocket;
//import java.io.*;
//import java.util.*;
//
//public class ClientMTh {
//	public static Socket cl;
//	public static DataOutputStream gui;
//	public static DataInputStream nhan;
//	public static int e = 0;
//	public static void main(String [] args) throws Exception, InterruptedException{
//		try{
//			cl = new Socket ("localhost", 12340 );
//			
//			gui = new DataOutputStream (cl.getOutputStream());
//			nhan = new DataInputStream (cl.getInputStream());
//			
//			Thread thr_gui = new Thread(new Runnable(){
//				public void run()
//				{
//					while(true){
//					try {
//						Scanner bp = new Scanner(System.in);
//						String s = bp.nextLine();
//						gui.writeUTF(s);						
//
//					} catch (IOException f) {
//						// TODO Auto-generated catch block
//					//	e.printStackTrace();
//					}
//					}
//				}
//			});
//			
//			Thread thr_nhan = new Thread(new Runnable(){
//				public void run()
//				{
//					while(true){
//					try {
//						String s1 = nhan.readUTF();
//						System.out.println('\n'+s1);
//
//					} catch (IOException f) {
//						// TODO Auto-generated catch block
//					//	e.printStackTrace();
//					}
//					}
//				}
//			});
//			
//			thr_gui.start();
//			thr_nhan.start();
//
//		
//		
//		
//		}
//			catch(IOException f){
//			f.printStackTrace();
//			
//		}
//	
//	}
//}

//bbbb
package CT3;

import java.net.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.PrintStream;
import java.net.Socket;
import java.net.DatagramSocket;
import java.io.*;
import java.util.*;

public class Client {
	public static Socket cl;
	public static DataOutputStream gui;
	public static DataInputStream nhan;
	public static int e = 0;
	public static void main(String [] args) throws Exception, InterruptedException{
		try{
			cl = new Socket ("localhost", 12340 );
			
			gui = new DataOutputStream (cl.getOutputStream());
			nhan = new DataInputStream (cl.getInputStream());
			
			Thread thr_gui = new Thread(new Runnable(){
				public void run()
				{
					while(true){
					try {
						Scanner bp = new Scanner(System.in);
						String s = bp.nextLine();
						gui.writeUTF(s);						

					} catch (IOException f) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					}
					}
				}
			});
			
			Thread thr_nhan = new Thread(new Runnable(){
				public void run()
				{
					while(true){
					try {
						String s1 = nhan.readUTF();
						System.out.println('\n'+s1);

					} catch (IOException f) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					}
					}
				}
			});
			
			thr_gui.start();
			thr_nhan.start();

		
		
		
		}
			catch(IOException f){
			f.printStackTrace();
			
		}
	
	}
}


