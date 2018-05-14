//package test;
//
////#ThreadSocket
//
//import java.io.*;
//import java.net.*;
//import java.util.Vector;
//
//
//
//class ServerMTh
//{
//	public static String msgforcl=null;
//	public static ServerSocket ss;
//	public static Vector<MTh> MThV= new Vector<MTh>();
//	public static int r;
//			
//
//	
//	public static void main(String[] args){
////1
////		try {
////		ss=new ServerSocket(12340);
////		
////		while(true)
////			{
//////				Socket sv;
//////				sv = ss.accept();
//////				MTh t=new MTh(sv);
//////				MThV.addElement(t);
//////				t.start();
////		
////			}
////	
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
//		try {
//		ss=new ServerSocket(12340);
//		r = (int) (Math.random()*100);
//		Thread countdown = new Thread(new Runnable(){
//			public void run()
//			{
//				
//				try {
//					Thread.sleep(60*2000);
//					pm(-1);
//					
//					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				
//				}
//			}
//		});
//		countdown.start();
//		while(true)
//			{
//				Socket sv;
//				sv = ss.accept();
//				MTh t=new MTh(sv);
//				MThV.addElement(t);
//				t.start();
//		
//			}
//	
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static synchronized void brmsg(String msg,int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		msgforcl = msg;
//		msgforcl = String.valueOf(port)+" : "+msgforcl;
//		for(MTh t:MThV)
//		{
//			t.reply(msgforcl);
//		}		
//	}
//	
//	public static synchronized void pm(int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(MTh t:MThV)
//		{
//			if(t.s.getPort() == port)
//				t.reply("Chien thang.");
//			else
//				t.reply("Thua roi.");
//
//		}		
//	}
//}



////AAAA
//package test;
//
////#ThreadSocket
//
//import java.io.*;
//import java.net.*;
//import java.util.Scanner;
//import java.util.Vector;
//
//
//
//class ServerMTh
//{
//	public static String msgforcl=null;
//	public static ServerSocket ss;
//	public static Vector<MTh> MThV= new Vector<MTh>();
//	public static int r;
//	public static DataOutputStream gui;
//	public static DataInputStream nhan;			
//
//	
//	public static void main(String[] args){
//	try {
//		ss=new ServerSocket(12340);
//
//		while(true)
//			{
//				Socket sv;
//				sv = ss.accept();
//				gui = new DataOutputStream (sv.getOutputStream());
//				nhan = new DataInputStream (sv.getInputStream());
//				
//				Thread thr_gui = new Thread(new Runnable(){
//					public void run()
//					{
//						while(true){
//						try {
//							Scanner bp = new Scanner(System.in);
//							String s = bp.nextLine();
//							gui.writeUTF(s);						
//
//						} catch (IOException f) {
//							// TODO Auto-generated catch block
//						//	e.printStackTrace();
//						}
//						}
//					}
//				});
//				
//				Thread thr_nhan = new Thread(new Runnable(){
//					public void run()
//					{
//						while(true){
//						try {
//							String s1 = nhan.readUTF();
//							System.out.println('\n'+s1);
//
//						} catch (IOException f) {
//							// TODO Auto-generated catch block
//						//	e.printStackTrace();
//						}
//						}
//					}
//				});
//				thr_gui.start();
//				thr_nhan.start();
//
//		
//			}
//	
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static synchronized void brmsg(String msg,int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		msgforcl = msg;
//		msgforcl = String.valueOf(port)+" : "+msgforcl;
//		for(MTh t:MThV)
//		{
//			t.reply(msgforcl);
//		}		
//	}
//	
//	public static synchronized void pm(int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(MTh t:MThV)
//		{
//			if(t.s.getPort() == port)
//				t.reply("Chien thang.");
//			else
//				t.reply("Thua roi.");
//
//		}		
//	}
//}


//bbbb
package CT2;

//#ThreadSocket

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import CT2.MTh1;



class Server1
{
	public static String msgforcl=null;
	public static ServerSocket ss;
	public static Vector<MTh1> MThV= new Vector<MTh1>(1,1);
	public static int r;
	public static DataOutputStream gui;
	public static DataInputStream nhan;			

	public static synchronized void pm()
	{
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(MTh1 t:MThV)
		{
			t.reply("go");
		}		
	}
	
	public static void main(String[] args){
	try {
		ss=new ServerSocket(12340);

		while(true)
			{
				Socket sv;
				sv = ss.accept();
				MTh1 t=new MTh1(sv);
				MThV.addElement(t);				
				t.getState();
				t.start();
				if(MThV.capacity() >= 3)
					pm();
				System.out.println(MThV.capacity());
			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static synchronized void brmsg(String msg,int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		msgforcl = msg;
//		msgforcl = String.valueOf(port)+" : "+msgforcl;
//		for(MTh t:MThV)
//		{
//			t.reply(msgforcl);
//		}		
//	}
//	
//	public static synchronized void pm(int port)
//	{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(MTh t:MThV)
//		{
//			if(t.s.getPort() == port)
//				t.reply("Chien thang.");
//			else
//				t.reply("Thua roi.");
//
//		}		
//	}
}


////cccc
//package test;
//
////#ThreadSocket
//
//import java.io.*;
//import java.net.*;
//import java.util.Scanner;
//import java.util.Vector;
//
//
//
//class ServerMTh
//{
//	public static String msgforcl=null;
//	public static ServerSocket ss;
//	public static Vector<MTh> MThV= new Vector<MTh>();
//	public static int rand;
//	public static boolean f = false;
//	public static DataOutputStream gui;
//	public static DataInputStream nhan;			
//
//	
//	public static void main(String[] args){
//	try {
//		ss=new ServerSocket(12340);
//		int r = (int) (Math.random()*100);
//		rand = r;
//		System.out.println(rand);
//		while(true)
//			{
//				Socket sv;
//				sv = ss.accept();
//				
//				MTh t=new MTh(sv);
//				MThV.addElement(t);
//				t.getState();
//				t.start();
//			}
//	
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}


////dddd
//package test;
//
////#ThreadSocket
//
//import java.io.*;
//import java.net.*;
//import java.util.Scanner;
//import java.util.Stack;
//import java.util.Vector;
//
//
//
//class ServerMTh
//{
//	public static String msgforcl=null;
//	public static ServerSocket ss;
//	public static Vector<MTh> MThV= new Vector<MTh>();
//	public static int r;
//	public static DataOutputStream gui;
//	public static DataInputStream nhan;
//	public static Stack<String> st = null;
//
//	
//	public static void main(String[] args){
//	try {
//		ss=new ServerSocket(12340);
//
//		while(true)
//			{
//				Socket sv;
//				sv = ss.accept();
//				MTh t=new MTh(sv);
//				MThV.addElement(t);
//				t.getState();
//				t.start();
//			}
//	
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}