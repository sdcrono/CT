package CT3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Map {
	
	static Screen screen = new Screen(); // Doi tuong hien thi.
	static Playout[][] map = new Playout[900][750];
	
	
	//*********************************************		
	public static void SetMap() throws UnsupportedEncodingException, FileNotFoundException {
		
		//Khoi tao va gan doi tuong Wall vao map. 
		Playout wall = new Playout(0);//Nen.
		for(int i = 25; i<=724; i++)
			for(int j = 25; j<=874; j++)
				map[j][i] = wall;
		
		
		//Khoi tao tuong bao ngoai.
		Playout wall1;
		Playout wall2;
		
		for(int n = 1; n<=36; n++)
		{	
			wall1 = new Playout(-1);
			wall1.SetX(25*(n-1));
			wall1.SetY(0);
			wall2 = new Playout(-1);
			wall2.SetX(25*(n-1));
			wall2.SetY(725);
			
			Playout.O_Playout.add(wall1);
			Playout.O_Playout.add(wall2);

			for(int i = 25*(n-1); i<=25*n-1; i++)
				for(int j = 0; j<=24; j++)
					{
						map[i][j] = wall1;
						map[i][j+725] = wall2;
					}
		}
		
		for(int n = 1; n<=30; n++)
		{
			wall1 = new Playout(-1);
			wall1.SetX(0);
			wall1.SetY(25*(n-1));
			wall2 = new Playout(-1);
			wall2.SetX(875);
			wall2.SetY(25*(n-1));
			
			Playout.O_Playout.add(wall1);
			Playout.O_Playout.add(wall2);

			for(int i = 25*(n-1); i<=25*n-1; i++)
				for(int j = 0; j<=24; j++)
					{
						map[j][i] = wall1;
						map[j+875][i] = wall2;
					}
		}
		//
		
		
		//Load file wall : doi tuong wall.
		int t;
		FileInputStream f = new FileInputStream("wall.txt");
		Scanner in = new Scanner(new InputStreamReader(f,"UTF-8"));

		while(in.hasNext())
		{
			t = Integer.parseInt(in.next());
			wall = new Playout(1); 
			wall.SetX(t);
			t = Integer.parseInt(in.next());
			wall.SetY(t);
			Playout.O_Playout.add(wall);

		}
		in.close();

		//Load wall vao mang map.
		
		for(Playout i:Playout.O_Playout)
		{
			for(int u = 0; u <=24; u++)
				for(int v = 0; v <=24; v++)
					map[i.GetX()+u][i.GetY()+v] = i; 
			
		}
		
		screen.setSize(900, 775);
		screen.setLocationRelativeTo(null);
		screen.addKeyListener(screen);
		screen.setVisible(true);
			
	}
		
	
	//*********************************************
	/*Class draw map.
	 */
	public static class Screen extends Frame implements KeyListener
	{

		int WIDTH = 900;

		public Screen() 
		{
			super("Clash of Tank");
			setSize(WIDTH, WIDTH);
			setResizable(false);
			addWindowListener(new WindowAdapter() 
			{
				public void windowClosing(WindowEvent e) 
				{
					System.exit(0);
				}
			});
		}
		
		public void update(Graphics g)
		{
			paint(g);
		}
		

		public void paint(Graphics g) {
			BufferedImage image = (BufferedImage) createImage(900,750);
			Graphics g2 = image.getGraphics();
			if(ClashofTanks.pause)
			{
				drawIntro(g2);
			}
			else
			{
				drawBG(g2);
				drawWall(g2);
				drawTank(g2);
				drawBullet(g2);
				if(Tanks.player == 0)
					try {
						drawEndGame(g2);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			g.drawImage(image, 0, 25, this);
			
		}

		void drawBG(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 900, 750);
		}

		void drawWall(Graphics g)
		{

			for(Playout o:Playout.O_Playout)
			{
				if(o.live)
				o.Paint(g);

				
			}
		}
		
		void drawTank(Graphics g) 
		{
			
			for(Tanks o:Tanks.O_Tanks)
			{
				if(o.live)
				o.Paint(g);
				
			}
								

		}
		void drawBullet(Graphics g)
		{

			for(Tanks o:Tanks.O_Bullets)
				{
					if(o.live)
					o.Paint_Bullet(g);
			
				}

		}
		
		void drawIntro(Graphics g)
		{
			String textVersion = "Prototype";
			String text = "ClashoFTanks";
			String textSolo = "Press key 1 for Singleplay Mode";
			String textCoop = "Press key 2 for Multiplay Mode (Local Co-op)";
			String textP2 = "P2";
			String textP1 = "P1";
			String textW = "W";
			String textASD = "A  S  D";
			String textShoot2 = "SPACE";
			String textShoot1 = "M";
			String textReserve2 = "Q";
			String textReserve1 = "N";
			String textU = "^";
			String textLDR = "<  v  >";
			String textScore = "Score: "  +  String.valueOf(TanksPlayer.score);

			int x = 300;
		    int y = 300;
		    Font fontVer = new Font("Algerian", Font.HANGING_BASELINE, 20);
		    Font font = new Font("Georgia", Font.ITALIC, 50);
		    Font fontSoloCoop = new Font("Georgia", Font.ITALIC, 30);
		    Font fontMove = new Font("Georgia", Font.BOLD, 30);
		    Graphics2D g1 = (Graphics2D) g;
		    TextLayout textLayoutVer = new TextLayout(textVersion, fontVer, g1.getFontRenderContext());
		    TextLayout textLayout = new TextLayout(text, font, g1.getFontRenderContext());
		    TextLayout textLayoutSolo = new TextLayout(textSolo, fontSoloCoop, g1.getFontRenderContext());
		    TextLayout textLayoutCoop = new TextLayout(textCoop, fontSoloCoop, g1.getFontRenderContext());
		    TextLayout textLayoutP2 = new TextLayout(textP1, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutP1 = new TextLayout(textP2, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutW = new TextLayout(textW, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutASD = new TextLayout(textASD, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutU = new TextLayout(textU, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutLDR = new TextLayout(textLDR, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutS2 = new TextLayout(textShoot2, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutS1 = new TextLayout(textShoot1, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutR2 = new TextLayout(textReserve2, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutR1 = new TextLayout(textReserve1, fontMove, g1.getFontRenderContext());
		    TextLayout textLayoutScore = new TextLayout(textScore, font, g1.getFontRenderContext());
		    
		    g1.setPaint(Color.BLACK);
		    textLayoutVer.draw(g1, x + 300, y - 25);
		    g1.setPaint(new Color(150, 150, 150));
		    textLayout.draw(g1, x + 3, y + 3);
		    textLayoutSolo.draw(g1, x - 50, y + 300);
		    textLayoutCoop.draw(g1, x - 50, y + 350);
		    textLayoutP2.draw(g1, x + 300, y + 130);
		    textLayoutP1.draw(g1, x - 37, y + 130);
		    textLayoutW.draw(g1, x - 37, y + 160);
		    textLayoutU.draw(g1, x + 305, y + 160);
		    textLayoutASD.draw(g1, x - 70, y + 190);
		    textLayoutLDR.draw(g1, x + 270, y + 190);
		    textLayoutS2.draw(g1, x - 200, y + 160);
		    textLayoutS1.draw(g1, x + 425, y + 160);
		    textLayoutR2.draw(g1, x - 37, y + 220);
		    textLayoutR1.draw(g1, x + 305, y + 220);
		    textLayoutScore.draw(g1, x + 75, y - 150);

		    g1.setPaint(Color.RED);
		    textLayout.draw(g1, x, y);
			
		}
		void drawEndGame(Graphics g) throws FileNotFoundException, UnsupportedEncodingException
		{
			//
			String text = "GAME OVER.";
			String textScore = ""; 
			int x = 300;
		    int y = 600;
		    Font font = new Font("Georgia", Font.ITALIC, 50);
		    Font fontMove = new Font("Georgia", Font.BOLD, 30);
		    Graphics2D g1 = (Graphics2D) g;
		    TextLayout textLayout = new TextLayout(text, font, g1.getFontRenderContext());
		    TextLayout textLayoutScore;
		    g1.setPaint(new Color(150, 150, 150));
		    textLayout.draw(g1, x + 3, y + 3);
		    g1.setPaint(Color.RED);
		    textLayout.draw(g1, x, y);
		    
//		    while(!UrlFtp.success)
//		    {
//		    	
//		    }
		    int score_sv[] = new int [11];
			int bienGiuViTri=1,t1,t2,n=1;
			String sc;
			if(ClashofTanks.Co_Op==true)
				sc="sc21.txt";
			else
				sc="sc11.txt";
			FileInputStream f = new FileInputStream(sc);
			Scanner in = new Scanner(new InputStreamReader(f,"UTF-8"));
			while(in.hasNext())
			{
				score_sv[n] = Integer.parseInt(in.next());
				n++;
			}
			n=1;
			
			for(int i = 1; i<=10; i++)
			{
				if(TanksPlayer.score >  score_sv[i])
				{
					t1 = score_sv[i];
					
					for(int j = i; j<=9; j++)
					{
						t2 = score_sv[j+1];
						score_sv[j+1] = t1;
						t1 = t2;
						
					}
					
					score_sv[i] = TanksPlayer.score;
					bienGiuViTri = i;
					break;
				}
			}
			g1.setPaint(new Color(150, 150, 150));
			while(n<=10)
			{
//				score_sv[n] = Integer.parseInt(in.next());
				if(n==bienGiuViTri)
				{
					g1.setPaint(Color.RED);
				}
				textScore = String.valueOf(n) + ". " + String.valueOf(score_sv[n]);
				textLayoutScore = new TextLayout(textScore, font, g1.getFontRenderContext());
				textLayoutScore.draw(g1, x + 75, y - 600 + n*50);
				n++;
				g1.setPaint(new Color(150, 150, 150));
			}

			
			
			//
		}
		
		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode()== KeyEvent.VK_1 )
			{
				ClashofTanks.Co_Op = false;
				ClashofTanks.pause = false;
				ClashofTanks.Intro = false;
			}
			if(e.getKeyCode()== KeyEvent.VK_2 )
			{
				ClashofTanks.Co_Op = true;
				ClashofTanks.pause = false;
				ClashofTanks.Intro = false;
			}

			if(e.getKeyCode()== KeyEvent.VK_ENTER && !ClashofTanks.pause && !ClashofTanks.Intro)
				ClashofTanks.pause = true;
			else
				if(e.getKeyCode()== KeyEvent.VK_ENTER && !ClashofTanks.Intro)
					ClashofTanks.pause = false;
				
			for(Tanks o:Tanks.O_Tanks)
			{
				if(o.type==3)
				{
					if(o.N_player ==1)
					{
						if( e.getKeyCode()== KeyEvent.VK_UP || e.getKeyCode()== KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()== KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_M || e.getKeyCode()==KeyEvent.VK_N) 
								o.Pressed(e);
					}	
					else
					if( e.getKeyCode()== KeyEvent.VK_W || e.getKeyCode()== KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()== KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_Q) 
						o.Pressed(e);
					

				
				}
			}
			
	    }


		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			

			for(Tanks o:Tanks.O_Tanks)
				if(o.type==3)
				{

					if(o.N_player == 1)

						{
							if(e.getKeyCode() == KeyEvent.VK_UP)
								if(o.huong == 0)
									o.stop = true;

							if(e.getKeyCode() == KeyEvent.VK_DOWN)
								if(o.huong == 2)
									o.stop = true;

							if(e.getKeyCode() == KeyEvent.VK_LEFT)
								if(o.huong == 3)
									o.stop = true;

							if(e.getKeyCode() == KeyEvent.VK_RIGHT)
								if(o.huong == 1)
									o.stop = true;
							if(e.getKeyCode() == KeyEvent.VK_M){}
							if(e.getKeyCode() == KeyEvent.VK_N )
								o.stop = true;


								
				
						}
					if(o.N_player == 2)
					{
						if(e.getKeyCode() == KeyEvent.VK_W)
							if(o.huong == 0)
								o.stop = true;

						if(e.getKeyCode() == KeyEvent.VK_S)
							if(o.huong == 2)
								o.stop = true;

						if(e.getKeyCode() == KeyEvent.VK_A)
							if(o.huong == 3)
								o.stop = true;

						if(e.getKeyCode() == KeyEvent.VK_D)
							if(o.huong == 1)
								o.stop = true;
						if(e.getKeyCode() == KeyEvent.VK_SPACE){}
						if(e.getKeyCode()==KeyEvent.VK_Q)
							o.stop = true;


			
					}
				}
			
		}
		
		public static void audio(String name) throws UnsupportedAudioFileException, LineUnavailableException
		{

				try {
					AudioInputStream t = AudioSystem.getAudioInputStream(new File(name).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(t);
					clip.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
		}


	}
	
	//*********************************************		

	
	/* Phuong thuc cap nhat vi tri cua doi tuong vao mang map.
	*/
	public static void SetRect(int x, int y, int r, int d, int ty )
	{
				
		Playout wall = new Playout(ty);
		
		for(int i=0; i<r; i++ )
		{
			for(int j=0; j<d; j++)

				map[x+i][y+j] = wall;				

			
		}
	}
	
	
	/* Phuong thuc xoa vi tri cua doi tuong trong mang map.
	*/
	public static void DelRect(int x, int y)
	{

				map[x][y].Set(0);
				
	}
	
	public static void Paint()
	{
		
		screen.repaint();
	}

}
