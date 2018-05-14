package CT2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Playout {
	public int x;
	public int y;
//	public int f;
//	public int g;
//	public int h;
//	public int open;
//	public int close;
//	public int obstacle;
	public Color color; 
	int type=1; 
	boolean live = true;
	public static ArrayList<Playout> O_Playout = new ArrayList<Playout>() ;
	
	public Playout(int t)
	{
		this.type = t;
		if(t==1)
			this.color = Color.getHSBColor(0.5f, 0.45f, 0.45f);
		if(t==-1)
			this.color = Color.getHSBColor(0.3f, 0.0f, 0.75f);
	}
	
	public Playout(int x,int y) 
	{
		this.x=x;
		this.y=y;
	}
	
	public void Del_Wall()
	{
		this.Set(0);;//Xoa vi tri tren mang.
		this.live = false;
		
	}
	public int Get()
	{
		return this.type;
	}
	public void Set(int t)
	{
		this.type = t;
		if(t==1)
			this.color = Color.getHSBColor(0.5f, 0.45f, 0.45f);
		if(t==-1)
			this.color = Color.getHSBColor(0.5f,0.45f,0.45f);
		if(t==-2)
			this.color = color.MAGENTA;
	}
	
	public void SetX(int x)
	{
		this.x = x;
	}
	public void SetY(int y)
	{
		this.y = y;
	}
	
	public int GetX()
	{
		return this.x;
	}
	public int GetY()
	{
		return this.y;
	}
//
//	public void SetF(int f)
//	{
//		this.f = f;
//	}
//	public void SetG(int g)
//	{
//		this.g = g;
//	}
//	
//	public int GetF()
//	{
//		return this.f;
//	}
//	public int GetG()
//	{
//		return this.g;
//	}
//	
//	public void SetH(int h)
//	{
//		this.h = h;
//	}
//	public void SetOpen(int open)
//	{
//		this.open = open;
//	}
//	
//	public int GetH()
//	{
//		return this.h;
//	}
//	public int GetOpen()
//	{
//		return this.open;
//	}
//
//	public void SetClose(int close)
//	{
//		this.close = close;
//	}
//	public void SetObstacle(int obstacle)
//	{
//		this.obstacle = obstacle;
//	}
//	
//	public int GetClose()
//	{
//		return this.close;
//	}
//	public int GetObstacle()
//	{
//		return this.obstacle;
//	}
	
	public void Paint(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(this.GetX(), this.GetY(), 25, 25);
		g.setColor(this.color.darker());
		g.fillRect(this.GetX()+22, this.GetY(), 3, 25);
		g.fillRect(this.GetX(), this.GetY()+22, 25, 3);
		g.setColor(this.color.brighter());
//		g.fillRect(this.GetX(), wall[i].GetY(), 25, 3);
//		g.fillRect(wall[i].GetX(), wall[i].GetY(), 3, 25);
		for(int j=0;j<=2;j++)
		{	
			g.drawLine(this.GetX(), this.GetY()+j, this.GetX()+24-j, this.GetY()+j);
			g.drawLine(this.GetX()+j, this.GetY(), this.GetX()+j, this.GetY()+24-j);
		}
		
		if(this.type == -1)
		{
			//Ve chu thap
			g.setColor(this.color.darker());
			for(int j=10;j<=11;j++)
				g.drawLine(this.GetX()+j, this.GetY()+4, this.GetX()+j, this.GetY()+11);
			for(int j=10;j<=11;j++)
				g.drawLine(this.GetX()+13, this.GetY()+j, this.GetX()+24, this.GetY()+j);
			for(int j=10;j<=11;j++)
				g.drawLine(this.GetX()+j, this.GetY()+14, this.GetX()+j, this.GetY()+24);
			for(int j=10;j<=11;j++)
				g.drawLine(this.GetX()+4, this.GetY()+j, this.GetX()+11, this.GetY()+j);
			
			g.setColor(this.color.brighter());
			for(int j=13;j<=14;j++)
				g.drawLine(this.GetX()+j, this.GetY()+3, this.GetX()+j, this.GetY()+12);
			for(int j=13;j<=14;j++)
				g.drawLine(this.GetX()+13, this.GetY()+j, this.GetX()+21, this.GetY()+j);
			for(int j=13;j<=14;j++)	
				g.drawLine(this.GetX()+j, this.GetY()+13, this.GetX()+j, this.GetY()+21);
			for(int j=13;j<=14;j++)
				g.drawLine(this.GetX(), this.GetY()+j, this.GetX()+12, this.GetY()+j);
		}
//
	}

}
