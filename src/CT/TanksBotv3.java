package CT;

import java.awt.Color;
import java.util.ArrayList;


public class TanksBotv3 extends Tanks 
{
	
	int max_move = 0;
	//0:X; 1:Y; 2:F; 3:G; 4:H; 5:open; 6:close; 7:huong dua theo vi tri truoc do; 8:huong dua theo vi tri hien tai
	int [][][] chaseArea = new int[200][200][89];
	int [] reconstruct = new int[800000];
	int reconstruct_count = 0;
	int re_direct =-1;
	int distant =-1;
	int enemy_Pos_X = -1;
	int enemy_Pos_Y = -1;
	boolean alert = false;
	boolean alertArea = false;
	public static int bot;
	public static ArrayList <Playout> O_neighbors = new ArrayList<Playout>();
	public static ArrayList <Playout> O_open = new ArrayList<Playout>();
	public static ArrayList <Playout> O_openMinPath = new ArrayList<Playout>();
//	private XY this1;
	
	
	//*********************************************
	
	public TanksBotv3(int x, int y, int huong,int type, int size)
	{
		super(x ,y ,huong , type,size);
		
		this.X = x;
		this.Y = y;
		this.huong = huong;
		this.size = size;
		this.type = type;
		this.armor = 0;
		this.delay_shoot = 50;
		if(this.type == 3)
		{
			this.color = Color.getHSBColor(0.2f, 1f, 0.75f);
			Tanks.player ++;
			
		}
		else{
			//this.color = Color.getHSBColor(0.01f, 1f, 0.75f);
			this.color = Color.getHSBColor(0.01f, 0.75f, 1f);
			TanksBotv3.bot ++;
		}
		
		O_Tanks.add(this);

		super.Move();
		
	}
	
	//*********************************************
	

	public void CallTanks()
	{
//		this.Create_ChaseArea(5);
//		System.out.println(chaseArea[5][5][0]+"-"+chaseArea[5][5][1]+"  ");
		if(this.t_delay_shoot >0)
			this.t_delay_shoot--;
		
		if(this.delay_turn == 0)
		{
			
			

//			this.View();//Tu dong phat hien ke dich truoc mat.
			this.View_v2();
			super.DelRec();//Xoa vi tri tren map.
			
			if(this.max_move >0 && this.True_Front() && this.alert == false)
			{
				this.Auto_Move(1);// Di chuyen toi truoc 1 don vi.
				this.max_move --;
			}
			else
			{
				this.max_move = 0;
				int t = -1; 
				if (this.alert == false)
				{
					if(this.alertArea == true)
						t = this.re_direct;
					else
						t = this.Auto_Turn();// Random huong co the di duoc.
				}
				else
					if(this.distant != 0)
						t = this.re_direct;
				if(t == this.huong)// Neu la huong truoc mat.
				{
					if(this.True_Front())
						this.Auto_Move(1);// Di chuyen toi truoc max_move don vi.
					if(this.alert == false)
					{
						if(this.alertArea == true)
							this.max_move = 30;
						else 
							this.max_move =(int)( Math.random()*100+25);
					}
					else
					{
						this.max_move = this.distant;
//						System.out.println(this.distant);
					}
					
				}
				else
					if(t != -1)// Neu con huong co the di chuyen duoc.
					{
				
						super.Setlc(this.X, this.Y, t);// Quay tank toi huong do.
						this.delay_turn = 50;
						
					}
					else
						super.Move();
			}
		}
		else 
			this.delay_turn = this.delay_turn -1;


		
	}
	
	

	
	public boolean True_Front()
	{
		if(this.huong == 0 && super.True_Move(this.X, this.Y-2, this.huong))
		{
			return true;
		}else
		if(this.huong == 1 && super.True_Move(this.X+32, this.Y, this.huong))
		{
			return true;
		}else
		if(this.huong == 2 && super.True_Move(this.X, this.Y+32, this.huong))
		{
			return true;
		}else
		if(this.huong ==3 && super.True_Move(this.X-2, this.Y, this.huong))
		{
			return true;
		}
		
		return false;
	}
	
	//*********************************************

	/* Phuong thuc tim huong di.
	*/
	
	public boolean P_Turn(int x, int y, int h, int f)
	{

		if(h == 0 )
		for(int i=0; i<f; i++)
			if(!super.True_Move(x, y-i, h))
				return false;
		
		if(h == 1)
		for(int i=0; i<f; i++)
			if(!super.True_Move(x+i, y, h))
				return false;
		
		if(h == 2)
		for(int i=0; i<f; i++)
			if(!super.True_Move(x, y+i, h))
				return false;
		
		if(h == 3)
		for(int i=0; i<f; i++)
			if(!super.True_Move(x-i, y, h))
				return false;
		
		return true;
	}
	
	public int Auto_Turn()
	{
		int[] A = {0,1,2,3};
		int beh ;// huong phia sau.
		
		if(this.huong == 0 || this.huong == 2)
			beh = Math.abs(this.huong - 2);
		else
			if(this.huong == 1)
				beh = 3;
			else
				beh = 1;
		
		int r = 0; //4 Huong(0: Bac, 1: Dong, 2: Nam, 3: Tay).
		
		while(!(A[0]==A[1]&&A[2]==A[3]))
		{
			r = (int) (Math.random()*4) ;
			
			if(r == A[0])
			{
				A[0] =-1;
				if(r == this.huong || r == beh)
				{	
					if((super.Y-10)>24 &&  Map.map[super.X][super.Y-10].Get()==0 && Map.map[super.X+14][super.Y-10].Get()==0 && Map.map[super.X+29][super.Y-10].Get()==0)
					{
						if(r == this.huong)
							return 0;	
					}
				}
				else
					if(this.P_Turn(this.X, this.Y-10, r, 20) == true)
					{
						
						return 0;
					}
				
			}else	
			if(r == A[1])
			{
			
				
				A[1] =-1;
				if(r == this.huong || r == beh)
				{
					if((super.X+40)<875 && Map.map[super.X+40][super.Y].Get()==0 && Map.map[super.X+40][super.Y+14].Get()==0 && Map.map[super.X+40][super.Y+29].Get()==0) //Sua lai chieu ngang.
					{
						if(r == this.huong)
						return 1;
					}
				}
				else
					if(this.P_Turn(this.X+40, this.Y, r, 20) == true)
						{	
							
							
							return 1;
							
						}
				
				
			}else
			if(r == A[2])
			{
				A[2] =-1;
				if(r == this.huong || r == beh)
				{
					if((super.Y+40)<725 && Map.map[super.X][super.Y+40].Get()==0 && Map.map[super.X+14][super.Y+40].Get()==0 && Map.map[super.X+29][super.Y+40].Get()==0) 
					{
						if(r == this.huong)
							return 2;
					}
				}
				else
					if(this.P_Turn(this.X, this.Y+40, r, 20) == true)
					{
						
						return 2;
						
					}
			}else	
			if(r == A[3])
			{
				A[3] =-1;
				if(r == this.huong || r == beh)
				{
					if((super.X-10)>24 && Map.map[super.X-10][super.Y].Get()==0 && Map.map[super.X-10][super.Y+14].Get()==0 && Map.map[super.X-10][super.Y+29].Get()==0)
					{
						if(r == this.huong)
							return 3;
					}
				}
				else
					if(this.P_Turn(this.X-10, this.Y, r, 20) == true)
					{
						
						return 3;
					}
				
			}
			
		}
		
		if(r == beh) return beh;
		return -1;
	}
	
//	public class XY	
//	{
//		int x;
//		int y;
//	}
//	
//	XY minpath()
//	{
//		int min=10000, dem = 0;
//		XY vmin = new XY();
////		System.out.println("1 " + Map.map[724][0].type );
////		System.out.println("2 " + Map.map[360][363].type );
////		int vmin_v2 = -1, dem =0;
//		//		for(int i=0; i<=725; i+=25)
////			for(int j=0; j<=875; j+=25)
////			{
////				if(o_neighbors2[j][i].f < min && o_neighbors2[j][i].open == 1 && o_neighbors2[j][i].close == 0 && o_neighbors2[j][i].type == 0)
////				{
////					min = Map.map[j][i].f;
////					vmin.x = j;
////					vmin.y = i;
////				}
////			}
////		TanksBot.O_openMinPath = O_open2;
////		for(Playout p:TanksBot.O_openMinPath)
////		{
////			System.out.println(dem);
////			System.out.println(p.x + " " + p.y);
////			dem++;
////		}
////		dem = 0;
////		O_openMinPath.get(0);
//		for(Playout p:TanksBot.O_open)
//		{
//			
////			System.out.println(dem +  " " + min +  " " + p.f +  " " + p.close +  " " + p.type);
////			System.out.println(dem+ " " + p.open );
////			if(O_openMinPath.size() == 4)
////				System.out.println(p.x + " " + p.y);
////				System.out.println(dem);
//			if(p.f <= min && p.open == 1 && p.close == 0 && p.type == 0)
//			{
//				dem = O_open.indexOf(p);
//				min = p.f;
////				vmin_v2 = dem;
////				System.out.println(vmin_v2);
//				vmin.x = p.x;
//				vmin.y = p.y;
////				System.out.println("vmin " + p.x + " " + p.y);
////				System.out.println(O_openMinPath.size());
//			}			
//		}
////		if(!O_open.isEmpty())
////			O_open.remove(dem);
////		O_openMinPath.clear();
////		System.out.println("aaa");
//		//M[1][vmin] = 0;
//		return vmin;
//	}
//
//	XY minpathHaveRemove()
//	{
//		int min=10000, dem = 0;
//		XY vmin = new XY();
////		System.out.println("1 " + Map.map[724][0].type );
////		System.out.println("2 " + Map.map[360][363].type );
////		int vmin_v2 = -1, dem =0;
//		//		for(int i=0; i<=725; i+=25)
////			for(int j=0; j<=875; j+=25)
////			{
////				if(o_neighbors2[j][i].f < min && o_neighbors2[j][i].open == 1 && o_neighbors2[j][i].close == 0 && o_neighbors2[j][i].type == 0)
////				{
////					min = Map.map[j][i].f;
////					vmin.x = j;
////					vmin.y = i;
////				}
////			}
////		TanksBot.O_openMinPath = O_open2;
////		for(Playout p:TanksBot.O_openMinPath)
////		{
////			System.out.println(dem);
////			System.out.println(p.x + " " + p.y);
////			dem++;
////		}
////		dem = 0;
////		O_openMinPath.get(0);
//		for(Playout p:TanksBot.O_open)
//		{
//			
////			System.out.println(dem +  " " + min +  " " + p.f +  " " + p.close +  " " + p.type);
////			System.out.println(dem+ " " + p.open );
////			if(O_openMinPath.size() == 4)
////				System.out.println(p.x + " " + p.y);
////				System.out.println(dem);
//			if(p.f <= min && p.open == 1 && p.close == 0 && p.type == 0)
//			{
//				dem = O_open.indexOf(p);
//				min = p.f;
////				vmin_v2 = dem;
////				System.out.println(vmin_v2);
//				vmin.x = p.x;
//				vmin.y = p.y;
////				System.out.println("vmin " + p.x + " " + p.y);
////				System.out.println(O_openMinPath.size());
//			}			
//		}
//		if(!O_open.isEmpty())
//			O_open.remove(dem);
////		O_openMinPath.clear();
////		System.out.println("aaa");
//		//M[1][vmin] = 0;
//		return vmin;
//	}
//	
//	public int Heuristics(int x, int y, Tanks d)
//	{
//		int Distance = 1;
//
//		return Distance*(Math.abs(x-d.X)+Math.abs(y-d.Y));
//	}
//	
//	
//	public int Auto_Turn_v2()
//	{
//		int Player_PosX = 0, Player_PosY = 0, huong = 3 , dem = 0;
//		XY ThisEnemy = new XY();
//		ThisEnemy.x = this.X;
//		ThisEnemy.y = this.Y;
//		for(int i=25; i<=724; i++)
//			for(int j=25; j<=874; j++)
//			{
//				dieuHuong[j][i] = -1;
//				Playout wall3 = new Playout(0);
//				wall3.SetX(j);
//				wall3.SetY(i);
//				wall3.SetF(20000000);
//				wall3.SetG(20000000);
//				wall3.SetClose(0);
////				Map.map[j][i].f = 200;
////				Map.map[j][i].g = 200;
////				Map.map[j][i].SetX(j);
////				Map.map[j][i].SetY(i);
////				Map.map[j][i].close = 0;
//				for(Tanks t: Tanks.O_Tanks)
//					if(t.type == 3 && t.N_player == 1)
//					{
//						wall3.SetH(Heuristics(j,i,t));
////						Map.map[j][i].h = Heuristics(j,i,t);
//						Player_PosX = t.X;
//						Player_PosY = t.Y;
//					}
//				if(Map.map[j][i].type == 0)
//				{
//					Map.map[j][i] = wall3;
//				}
//			}
////		for(int i=25; i<=724; i++)
////			for(int j=25; j<=874; j++)
////			{
////				Map.map[j][i].x = j;
////				Map.map[j][i].y = i;
////				Map.map[j][i].f = j;
////				System.out.println(i + " " + j + " " + Map.map[j-2][i].x + " " + Map.map[j-2][i].y + " " + Map.map[j-2][i].f);
////				System.out.println(i + " " + j + " " + Map.map[j-1][i].x + " " + Map.map[j-1][i].y + " " + Map.map[j-1][i].f);
////				System.out.println(i + " " + j + " " + Map.map[j][i].x + " " + Map.map[j][i].y + " " + Map.map[j][i].f);
////				System.out.println(i + " " + j + " " + Map.map[j+1][i].x + " " + Map.map[j+1][i].y + " " + Map.map[j+1][i].f);
////				System.out.println(i + " " + j + " " + Map.map[j+2][i].x + " " + Map.map[j+2][i].y + " " + Map.map[j+2][i].f);
////			}
//		for(int i=25; i<=724; i++)
//			for(int j=25; j<=874; j++)
//			{
////				System.out.println(i + " " + j + " " + Map.map[j][i].x + " " + Map.map[j][i].y + " " + Map.map[j][i].type);
////				System.out.println(dieuHuong[j][i]);
//			}
////		Playout wall4 = new Playout(0);
////		wall4.SetF(0);
////		wall4.SetG(0);
////		wall4.SetOpen(1);
////		wall4.SetClose(0);
//
//		Map.map[ThisEnemy.x][ThisEnemy.y].f = 0;
//		Map.map[ThisEnemy.x][ThisEnemy.y].g = 0;
//		Map.map[ThisEnemy.x][ThisEnemy.y].h = 0;
//		Map.map[ThisEnemy.x][ThisEnemy.y].open = 1;
////		Map.map[ThisEnemy.x][ThisEnemy.y].SetX(ThisEnemy.x);
////		Map.map[ThisEnemy.x][ThisEnemy.y].SetY(ThisEnemy.y);
//		O_open.add(Map.map[ThisEnemy.x][ThisEnemy.y]);
//		dem = 0;
////		for(Playout p:TanksBot.O_open)
////			System.out.println(Map.map[ThisEnemy.x][ThisEnemy.y].x + " " + Map.map[ThisEnemy.x][ThisEnemy.y].y);
//		reconstruct[reconstruct_count]=-1;
//		reconstruct_count++;
////		while(!(ThisEnemy.x == Player_PosX && ThisEnemy.y == Player_PosY ) || dem <= 10)
//		while(!O_open.isEmpty())
//		{
//			dem++;
//			XY PosU = new XY();
//			PosU = minpathHaveRemove();
//			Map.map[PosU.x][PosU.y].close = 1;
//			dieuHuong[PosU.x][PosU.y-1] = 0;
//			dieuHuong[PosU.x+1][PosU.y] = 1;
//			dieuHuong[PosU.x][PosU.y+1] = 2;
//			dieuHuong[PosU.x-1][PosU.y] = 3;
//
//			if(reconstruct_count >= 500)
//				break;
////			Map.map[ThisEnemy.x][ThisEnemy.y-1].SetX(ThisEnemy.x);
////			Map.map[ThisEnemy.x][ThisEnemy.y-1].SetY(ThisEnemy.y-1);
////			Map.map[ThisEnemy.x+1][ThisEnemy.y].SetX(ThisEnemy.x+1);
////			Map.map[ThisEnemy.x+1][ThisEnemy.y].SetY(ThisEnemy.y);
////			Map.map[ThisEnemy.x][ThisEnemy.y+1].SetX(ThisEnemy.x);
////			Map.map[ThisEnemy.x][ThisEnemy.y+1].SetY(ThisEnemy.y+1);
////			Map.map[ThisEnemy.x-1][ThisEnemy.y].SetX(ThisEnemy.x-1);
////			Map.map[ThisEnemy.x-1][ThisEnemy.y].SetY(ThisEnemy.y);
//
//			O_neighbors.add(Map.map[PosU.x][PosU.y-1]);
//			O_neighbors.add(Map.map[PosU.x+1][PosU.y]);
//			O_neighbors.add(Map.map[PosU.x][PosU.y+1]);
//			O_neighbors.add(Map.map[PosU.x-1][PosU.y]);
//			
////			for(Playout q:TanksBot.O_open)
////			{
////				System.out.print(dem + " ");
////				System.out.println(q.GetX() + " " + q.GetY());
//////				dem++;
////			}
//						
////			Playout u = null;
//			
////			u.x = PosU.x;
////			u.y = PosU.y;
////			PosU.x = this.X;
////			PosU.y = this.Y;
////			Map.map[PosU.x][PosU.y].close = 1;
////			System.out.println(O_neighbors.size());
//			for(Playout p:TanksBot.O_neighbors)
//			{
//				int temp = 0; 
//				temp = Map.map[PosU.x][PosU.y].g + 1;
//				if(Map.map[p.x][p.y].open == 1 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
//				{
//					Map.map[p.x][p.y].open = 0;
//				}
//
//				if(Map.map[p.x][p.y].close == 1 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
//				{
//					Map.map[p.x][p.y].close = 0;
//				}
//				
//				if(Map.map[p.x][p.y].open == 0 && Map.map[p.x][p.y].close == 0 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
//				{
//					Map.map[p.x][p.y].g = temp;
//					Map.map[p.x][p.y].open = 1;
//					Map.map[p.x][p.y].SetX(p.x); 
//					Map.map[p.x][p.y].SetY(p.y);
//					O_open.add(Map.map[p.x][p.y]);
//					Map.map[p.x][p.y].f = Map.map[p.x][p.y].g + Map.map[p.x][p.y].h;
//				}
//				
//
//			}
//			
////			if(Map.map[PosU.x][PosU.y].type == 3)
////			{
////				break;
////			}
//			
//			
//			XY PosK = new XY();
//			PosK = minpath();
////			O_neighbors.clear();
////			System.out.println("PosK " + PosK.x + " " + PosK.y);
////			System.out.println("PosU " + PosU.x + " " + PosU.y);
//			if((PosK.x == PosU.x+1) && (PosK.y == PosU.y))
//			{
//				reconstruct[reconstruct_count] = 1;
//				reconstruct_count++;
//			}
//
//			if((PosK.x == PosU.x-1) && (PosK.y == PosU.y))
//			{
//				reconstruct[reconstruct_count] = 3;
//				reconstruct_count++;
//			}
//
//			if((PosK.x == PosU.x) && (PosK.y == PosU.y-1))
//			{
//				reconstruct[reconstruct_count] = 0;
//				reconstruct_count++;
//			}
//			
//			if((PosK.x == PosU.x) && (PosK.y == PosU.y+1))
//			{
//				reconstruct[reconstruct_count] = 2;
//				reconstruct_count++;
//			}
//			O_neighbors.clear();
//		}
////		int Direction = 0;
////		while(Direction != -1)
////		{
////			if(dieuHuong [Player_PosX][Player_PosY] == 0)
////			{
////				Direction = dieuHuong [Player_PosX][Player_PosY - 1];
////				Player_PosY = Player_PosY - 1;
////			}
////			if(dieuHuong [Player_PosX][Player_PosY] == 1)
////			{
////				Direction = dieuHuong [Player_PosX + 1][Player_PosY];
////				Player_PosX = Player_PosX + 1;
////			}
////			if(dieuHuong [Player_PosX][Player_PosY] == 0)
////			{
////				Direction = dieuHuong [Player_PosX][Player_PosY + 1];
////				Player_PosY = Player_PosY + 1;
////			}
////			if(dieuHuong [Player_PosX][Player_PosY] == 0)
////			{
////				Direction = dieuHuong [Player_PosX - 1][Player_PosY];
////				Player_PosX = Player_PosX -1;
////			}
////
////		}
//		reconstruct_count = 0;
//		for(int i =0; i<=10; i++)
//			System.out.println(reconstruct[i]);
//		return reconstruct[1];
////		return Direction;
//	}
//	
//
////	public int Auto_Turn_v3()
////	{
////		int Player_PosX = 0, Player_PosY = 0, huong = 3 , dem = 0;
////		XY ThisEnemy = new XY();
////		ThisEnemy.x = this.X;
////		ThisEnemy.y = this.Y;
////		for(int i=25; i<=724; i++)
////			for(int j=25; j<=874; j++)
////			{
////				Map.map[j][i].f = 200;
////				Map.map[j][i].g = 200;
////				Map.map[j][i].SetX(j);
////				Map.map[j][i].SetY(i);
////				Map.map[j][i].close = 0;
////				for(Tanks t: Tanks.O_Tanks)
////					if(t.type == 3 && t.N_player == 1)
////					{
////						Map.map[j][i].h = Heuristics(j,i,t);
////						Player_PosX = t.X;
////						Player_PosY = t.Y;
////					}
////			}
////		Map.map[ThisEnemy.x][ThisEnemy.y].f = 0;
////		Map.map[ThisEnemy.x][ThisEnemy.y].g = 0;
////		Map.map[ThisEnemy.x][ThisEnemy.y].h = 0;
////		Map.map[ThisEnemy.x][ThisEnemy.y].open = 1;
//////		Map.map[ThisEnemy.x][ThisEnemy.y].SetX(ThisEnemy.x);
//////		Map.map[ThisEnemy.x][ThisEnemy.y].SetY(ThisEnemy.y);
////		O_open.add(Map.map[ThisEnemy.x][ThisEnemy.y]);
////		dem = 0;
//////		for(Playout p:TanksBot.O_open)
//////			System.out.println(Map.map[ThisEnemy.x][ThisEnemy.y].x + " " + Map.map[ThisEnemy.x][ThisEnemy.y].y);
////		reconstruct[reconstruct_count]=-1;
////		reconstruct_count++;
////		while(!(ThisEnemy.x == Player_PosX && ThisEnemy.y == Player_PosY ) || dem <= 10)
////		{
////			dem++;
////			XY PosU = new XY();
////			PosU = minpath();
////			Map.map[PosU.x][PosU.y].close = 1;
////			if(reconstruct_count >= 10)
////				break;
//////			Map.map[ThisEnemy.x][ThisEnemy.y-1].SetX(ThisEnemy.x);
//////			Map.map[ThisEnemy.x][ThisEnemy.y-1].SetY(ThisEnemy.y-1);
//////			Map.map[ThisEnemy.x+1][ThisEnemy.y].SetX(ThisEnemy.x+1);
//////			Map.map[ThisEnemy.x+1][ThisEnemy.y].SetY(ThisEnemy.y);
//////			Map.map[ThisEnemy.x][ThisEnemy.y+1].SetX(ThisEnemy.x);
//////			Map.map[ThisEnemy.x][ThisEnemy.y+1].SetY(ThisEnemy.y+1);
//////			Map.map[ThisEnemy.x-1][ThisEnemy.y].SetX(ThisEnemy.x-1);
//////			Map.map[ThisEnemy.x-1][ThisEnemy.y].SetY(ThisEnemy.y);
////
////			O_neighbors.add(Map.map[ThisEnemy.x][ThisEnemy.y-1]);
////			O_neighbors.add(Map.map[ThisEnemy.x+1][ThisEnemy.y]);
////			O_neighbors.add(Map.map[ThisEnemy.x][ThisEnemy.y+1]);
////			O_neighbors.add(Map.map[ThisEnemy.x-1][ThisEnemy.y]);
////			
////			for(Playout q:TanksBot.O_open)
////			{
////				System.out.print(dem + " ");
////				System.out.println(q.GetX() + " " + q.GetY());
//////				dem++;
////			}
////						
//////			Playout u = null;
////			
//////			u.x = PosU.x;
//////			u.y = PosU.y;
//////			PosU.x = this.X;
//////			PosU.y = this.Y;
//////			Map.map[PosU.x][PosU.y].close = 1;
//////			System.out.println(O_neighbors.size());
////			for(Playout p:TanksBot.O_neighbors)
////			{
////				int temp = 0; 
////				temp = Map.map[PosU.x][PosU.y].g + 1;
////				if(Map.map[p.x][p.y].open == 1 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
////				{
////					Map.map[p.x][p.y].open = 0;
////				}
////
////				if(Map.map[p.x][p.y].close == 1 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
////				{
////					Map.map[p.x][p.y].close = 0;
////				}
////				
////				if(Map.map[p.x][p.y].open == 0 && Map.map[p.x][p.y].close == 0 && Map.map[p.x][p.y].type == 0 && temp < Map.map[p.x][p.y].g)
////				{
////					Map.map[p.x][p.y].g = temp;
////					Map.map[p.x][p.y].open = 1;
////					Map.map[p.x][p.y].SetX(p.x); 
////					Map.map[p.x][p.y].SetY(p.y);
////					O_open.add(Map.map[p.x][p.y]);
////					Map.map[p.x][p.y].f = Map.map[p.x][p.y].g + Map.map[p.x][p.y].h;
////				}
////				
////
////			}
////			
////			XY PosK = new XY();
////			PosK = minpath();
//////			O_neighbors.clear();
//////			System.out.println("PosK " + PosK.x + " " + PosK.y);
//////			System.out.println("PosU " + PosU.x + " " + PosU.y);
////			if((PosK.x == PosU.x+1) && (PosK.y == PosU.y))
////			{
////				reconstruct[reconstruct_count] = 1;
////				reconstruct_count++;
////			}
////
////			if((PosK.x == PosU.x-1) && (PosK.y == PosU.y))
////			{
////				reconstruct[reconstruct_count] = 3;
////				reconstruct_count++;
////			}
////
////			if((PosK.x == PosU.x) && (PosK.y == PosU.y-1))
////			{
////				reconstruct[reconstruct_count] = 0;
////				reconstruct_count++;
////			}
////			
////			if((PosK.x == PosU.x) && (PosK.y == PosU.y+1))
////			{
////				reconstruct[reconstruct_count] = 2;
////				reconstruct_count++;
////			}
//////			System.out.println("aaa");
//////			System.out.println(reconstruct_count);
////		}
//////		huong = minpath(O_open);
////		O_neighbors.clear();
////		reconstruct_count = 0;
////		for(int i =0; i<=10; i++)
////			System.out.println(reconstruct[i]);
////		return huong;
////	}
//
//	
	
	
	
	
	/* Phuong thuc di chuyen 1 buoc theo huong truoc mat.
	*/
	
	
	
	/* Phuong thuc phat hien tank dich truoc mat.
	 * 
	*/
	
	public boolean Pc_View(int x, int y,int a_x, int a_y)
	{
		int dich = 3;
		int d = 0;
		int x_rig = x;
		int y_rig = y;
		int x_lef = x;
		int y_lef = y;
		int x_beh = x;
		int y_beh = y;
		
		int mov_view_box = 0;

		int[] A= new int[10];// Do rong tam nhin cua xe tang la 10 don vi.
		int[] A_rig= new int[10];
		int[] A_lef= new int[10];
		int[] A_beh= new int[10];
		boolean stop = false;
		
		if(this.type == 3)
			dich = 2;
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{
			mov_view_box = a_x*6;
			if(a_x == 1)
			{
				x_rig = x_rig-40+10;
				y_rig = y_rig-10+40;
				x_beh = x_beh-40-10;
				y_beh = y_beh-10+10;
				x_lef = x_lef-40+10;
				y_lef = y_lef-10-10;
			}
			else
			{
				x_rig = x_rig+10+10;
				y_rig = y_rig-10-10;
				x_beh = x_beh+10+40;
				y_beh = y_beh-10+10;
				x_lef = x_lef+10+10;
				y_lef = y_lef-10+40;				
			}
			while(((x>24 && y>24 && x<875 && y<725) || (y_rig>24 && y_rig<725) || (x_beh>24 && x_beh<875) || (y_lef>24 && y_lef<725)) && !stop)
			//while(x>24 && y>24 && x<875 && y<725 && !stop)
			{
				stop = true;
				x = x+a_x;
				y_rig = y_rig +a_x;
				x_beh = x_beh -a_x;
				y_lef = y_lef -a_x;
				d++;
			
				for(int i=0;i<10;i++)
				{
					if(A[i] != 1 && x>24 && x<875 )
					{	
						if(Map.map[x][y+i].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							
							if(a_x == 1)
							{
								this.re_direct = 1;
								this.distant = d-a_x;
							}
							else
							{
								this.re_direct = 3;
								this.distant = d+a_x;
							}
							return true;
						}
						else
							if(Map.map[x][y+i].Get() == 1)
								A[i] = 1;
						
						stop = false;
					}
					
//					if(i < 2)
					if(A_rig[i] != 1 && y_rig>24 && y_rig<725)
					{
						if(Map.map[x_rig+i-mov_view_box][y_rig].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 2;
							else
								this.re_direct = 0;
//							super.Setlc(this.X, this.Y, this.huong);
							return true;
						}
						else
							if(Map.map[x_rig+i-mov_view_box][y_rig].Get() == 1) 
								A_rig[i] = 1;
						stop = false;
					}
					
					if(A_beh[i] != 1 && x_beh>24 && x_beh<875)
					{
						if(Map.map[x_beh][y_beh+i].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 3;
							else
								this.re_direct = 1;
							return true;
						}
						else
							if(Map.map[x_beh][y_beh+i].Get() == 1) 
								A_beh[i] = 1;
						stop = false;
					}
					
//					if(i < 2)
					if(A_lef[i] != 1 && y_lef>24 && y_lef<725)
					{
						if(Map.map[x_lef+i-mov_view_box][y_lef].Get() == dich)
						{
							if(this.alert == false)
								audio.Alarm();
							this.alert = true;
							this.distant = d-a_x;
							if(a_x == 1)
								this.re_direct = 0;
							else
								this.re_direct = 2;
							return true;
						}
						else
							if(Map.map[x_lef+i-mov_view_box][y_lef].Get() == 1) 
								A_lef[i] = 1;
						stop = false;
					}

				}
			}

//		if(this.alert == true)
//			audio.Alarm_off();
		this.alert = false;	

			
		}
		else
			if(a_y == 1 || a_y == -1)// Truong hop huong cua xe tank la Nam va Bac.
			{
				mov_view_box = a_y*6;
				if(a_y == 1)
				{
					x_rig = x_rig-10-10;
					y_rig = y_rig-40+10;
					x_beh = x_beh-10+10;
					y_beh = y_beh-40-10;
					x_lef = x_lef-10+40;
					y_lef = y_lef-40+10;
				}
				else
				{
					x_rig = x_rig-10+40;
					y_rig = y_rig+10+10;
					x_beh = x_beh-10+10;
					y_beh = y_beh+10+40;
					x_lef = x_lef-10-10;
					y_lef = y_lef+10+10;
				}
				while(((x>0 && y>0 && x<850 && y<700) || (x_rig>0 && x_rig<850) || (y_beh>24 && y_beh<700) || (x_lef>0 && x_lef<850)) && !stop)
				//while(x>0 && y>0 && x<850 && y<700 && !stop)
				{
					stop = true;
					y = y+a_y;
					x_rig = x_rig - a_y;
					y_beh = y_beh - a_y;
					x_lef = x_lef + a_y;
					d++;
					for(int i=0;i<10;i++)
					{
						if(A[i] != 1 && y>0 && y<700)
						{
							if(Map.map[x+i][y].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								
								if(a_y == 1)
								{
									this.re_direct = 2;
									this.distant = d-a_y;
								}
								else
								{
									this.re_direct = 0;
									this.distant = d+a_y;
								}
								return true;
							}
							else
								if(Map.map[x+i][y].Get() == 1)
									A[i] = 1;
							
							stop = false;
						}
						
//						if(i < 2)
						if(A_rig[i] != 1 && x_rig>0 && x_rig<850 )
						{
							if(Map.map[x_rig][y_rig+i-mov_view_box].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 3;
								else
									this.re_direct = 1;
								return true;
							}
							else
								if(Map.map[x_rig][y_rig+i-mov_view_box].Get() == 1) 
									A_rig[i] = 1;
							stop = false;
						}
						
						if(A_beh[i] != 1 && y_beh>24 && y_beh<700)
						{
							if(Map.map[x_beh+i][y_beh].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 0;
								else
									this.re_direct = 2;
								return true;
							}
							else
								if(Map.map[x_beh+i][y_beh].Get() == 1) 
									A_beh[i] = 1;
							stop = false;
						}
						
//						if(i < 2)
						if(A_lef[i] != 1 && x_lef>0 && x_lef<850)
						{
							if(Map.map[x_lef][y_lef+i-mov_view_box].Get() == dich)
							{
								if(this.alert == false)
									audio.Alarm();
								this.alert = true;
								this.distant = d-a_y;
								if(a_y == 1)
									this.re_direct = 1;
								else
									this.re_direct = 3;
								return true;
							}
							else
								if(Map.map[x_lef][y_lef+i-mov_view_box].Get() == 1) 
									A_lef[i] = 1;
							stop = false;
						}
					}
				}
				
			}
//		if(this.alert == true)
//			audio.Alarm_off();
		this.alert = false;
		return false;
	}
	
	public void View()
	{
	
			if(super.huong ==0)
			{
				if(this.Pc_View(this.X+10,this.Y-10,0,-1))
				{

					super.Setlc(this.X, this.Y, this.re_direct);
//					this.delay_turn = 50;
					super.Shoot();
//					this.Auto_Move(1);
				}

			}else
			
			if(super.huong ==1)
			{	
				if(this.Pc_View(this.X+40,this.Y+10,1,0))
				{

					super.Setlc(this.X, this.Y, this.re_direct);
//					this.delay_turn = 50;
					super.Shoot();
//					this.Auto_Move(1);
				}

			}else
				
			if(super.huong ==2)
			{
				if(this.Pc_View(this.X+10,this.Y+40,0,1))
				{

					super.Setlc(this.X, this.Y, this.re_direct);
//					this.delay_turn = 50;
					super.Shoot();
//					this.Auto_Move(1);
				}

			}else
				
			if(super.huong ==3)
			{
				if(this.Pc_View(this.X-10,this.Y+10,-1,0))
				{

					super.Setlc(this.X, this.Y, this.re_direct);
//					this.delay_turn = 50;
					super.Shoot();
//					this.Auto_Move(1);
				}
				

			}

		
	}
	
	
	public boolean Pc_View_v2(int x, int y,int a_x, int a_y,int doRong)
	{
		int dich = 3;
		int d = 0;
		int x_ori = x;
		int y_ori = y;
		//chieu dai/tam xa cua tam nhin xe tang 
		int banKinh = 0;
		
		if(this.alertArea == false)
			banKinh = 7;
		else
			if(this.alertArea == true)
				banKinh = 10;
		int[] A= new int[50];// Do rong tam nhin cua xe tang la 10 don vi.
		boolean stop = false;
		
		if(this.type == 3)
			dich = 2;
		
		if(a_x == 1 || a_x == -1)// Truong hop huong cua xe tank la Dong va Tay.
		{

			while((x>0 && y>0 && x<875 && y<725) && !stop)
			//while(x>24 && y>24 && x<875 && y<725 && !stop)
			{
				stop = true;
				x = x+a_x;
				d++;
				// Do rong tam nhin xe bang do rong ben trai + kich thuoc xe + do rong ben phai
				for(int i=0;i<doRong*10*2+this.size;i++)
//				for(int i=0;i<50;i++)	
				{
					if(y+i>0 && y+i<700 && x>0 && x<875 && (x < (x_ori+this.size*banKinh)) && (x > (x_ori-this.size*banKinh)))
					{	
						if(Map.map[x][y+i].Get() == dich)
						{
//							if(this.alertArea == false)
//								audio.Alarm();
							this.alertArea = true;
//							System.out.println("ON13");
							enemy_Pos_X = x;
							enemy_Pos_Y = y+i;
							
							return true;
						}
						
						stop = false;
					}

				}
			}

//		if(this.alert == true)
//			audio.Alarm_off();
		this.alertArea = false;	
//		System.out.println("OFF13");
			
		}
		else
			if(a_y == 1 || a_y == -1)// Truong hop huong cua xe tank la Nam va Bac.
			{


				while((x>0 && y>0 && x<850 && y<700) && !stop)
				//while(x>0 && y>0 && x<850 && y<700 && !stop)
				{
					stop = true;
					y = y+a_y;

					d++;
					// Do rong tam nhin xe bang do rong ben trai + kich thuoc xe + do rong ben phai
					for(int i=0;i<doRong*10*2+this.size;i++)
//					for(int i=0;i<50;i++)
					{
						if(x+i>0 && x+i<850 && y>0 && y<700 && (y < (y_ori+this.size*banKinh)) && (y > (y_ori-this.size*banKinh)))
						{
							if(Map.map[x+i][y].Get() == dich)
							{
//								if(this.alertArea == false)
//									audio.Alarm();
								this.alertArea = true;
//								System.out.println("ON02");
								enemy_Pos_X = x;
								enemy_Pos_Y = y+i;						

								return true;
							}
						
							stop = false;
						}

					}
				}
				
			}
//		if(this.alert == true)
//			audio.Alarm_off();
		this.alertArea = false;
//		System.out.println("OFF02");
		return false;
	}

	
	public int Heuristics(int x, int y, int e_x, int e_y)
	{
		int Distance = 1;

		return Distance*(Math.abs(x-(e_x+15))+Math.abs(y-(e_y+15)));
	}	

	public int Stupid_Move()
	{
		int h = -1;
		int v = 999;
		int beh = -1;
		int behDaoDong = 35;
		int hDaoDong = 25;
		if(this.huong == 0 || this.huong == 2)
			beh = Math.abs(this.huong - 2);
		else
			if(this.huong == 1)
				beh = 3;
			else
				beh = 1;
		
		int[] h_value= new int[4] ;
		System.out.println(this.enemy_Pos_X+" va "+this.enemy_Pos_Y);
		if(this.enemy_Pos_X != -1 && this.enemy_Pos_Y != -1)
		{
			h_value [0] = Heuristics(this.X+14, this.Y-10, this.enemy_Pos_X, this.enemy_Pos_Y);


			
			if(this.huong == 0)
			{
				//h_value [0] -= hDaoDong;
				System.out.print(">");
			}

			if(beh == 0)
			{
				//h_value [0] += behDaoDong;
				System.out.print("b");
			}
			System.out.println("0:"+h_value [0]);
			for(int i = 0; i<= 10; i+=5)
			{
				if(this.Y-10-i <= 25)
				{
					if(i == 0)
					{
						h_value [0] += 999;
						System.out.println("0f"+h_value [0]);
					}
					break;
				}
				else
					if(Map.map[this.X][this.Y-10-i].Get() == 1 || Map.map[this.X+14][this.Y-10-i].Get() == 1 || Map.map[this.X+29][this.Y-10-i].Get() == 1)
					{
						if(this.huong == 0)
						{
							h_value [1] -= hDaoDong;
							h_value [3] -= hDaoDong;
						}

						h_value [0] += 999;
						System.out.println("0-"+h_value [0]);
						break;
					}
			}
			
			h_value [1] = Heuristics(this.X+10+this.size, this.Y+14, this.enemy_Pos_X, this.enemy_Pos_Y);
			if(this.huong == 1)
			{
				//h_value [1] -= hDaoDong;
				System.out.print(">");
			}
			if(beh == 1)
			{
				//h_value [1] += behDaoDong;
				System.out.print("b");
			}
			System.out.println("1:"+h_value [1]);
			for(int i = 0; i<= 10; i+=5)
			{
				if(this.X+10+this.size+i >= 875)
				{
					if(i == 0)
					{
						h_value [1] += 999;
						System.out.println("1f"+h_value [1]);
					}
					break;
				}
				else
					if(Map.map[this.X+10+this.size+i][this.Y].Get() == 1 || Map.map[this.X+this.size+i][this.Y+14].Get() == 1 || Map.map[this.X+this.size+i][this.Y+29].Get() == 1)
					{
						if(this.huong == 1)
						{
							h_value [0] -= hDaoDong;
							h_value [2] -= hDaoDong;
						}

						h_value [1] += 999;
						System.out.println("1-"+h_value [1]);
						break;
					}
			}
			
			h_value [2] = Heuristics(this.X+14, this.Y+10+this.size, this.enemy_Pos_X, this.enemy_Pos_Y);
			if(this.huong == 2)
			{
				//h_value [2] -= hDaoDong;
				System.out.print(">");
			}
			if(beh == 2)
			{
				//h_value [2] += behDaoDong;
				System.out.print("b");
			}
			System.out.println("2:"+h_value [2]);
			for(int i = 0; i<= 10; i+=5)
			{
				if(this.Y+10+this.size+i >= 725)
				{
					if(i == 0)
					{
						h_value [2] += 999;
						System.out.println("2f"+h_value [2]);
					}
					break;
				}
				else
					if(Map.map[this.X][this.Y+10+this.size+i].Get() == 1 || Map.map[this.X+14][this.Y+10+this.size+i].Get() == 1 || Map.map[this.X+29][this.Y+10+this.size+i].Get() == 1)
					{
						if(this.huong == 2)
						{
							h_value [1] -= hDaoDong;
							h_value [3] -= hDaoDong;
						}

						h_value [2] += 999;
						System.out.println("2-"+h_value [2]);
						break;
					}
			}
			
			h_value [3] = Heuristics(this.X-10, this.Y+14, this.enemy_Pos_X, this.enemy_Pos_Y);
			if(this.huong == 3)
			{
				//h_value [3] -= hDaoDong;
				System.out.print(">");
			}
			if(beh == 3)
			{
				//h_value [3] += behDaoDong;
				System.out.print("b");
			}
			System.out.println("3:"+h_value [3]);
			for(int i = 0; i<= 10; i+=5)
			{
				if(this.X-10-i <= 25)
				{
					if(i == 0)
					{
						h_value [3] += 999;
						System.out.println("3f"+h_value [3]);
					}
					break;
				}
				else
					if(Map.map[this.X-10-i][this.Y].Get() == 1 || Map.map[this.X-10-i][this.Y+14].Get() == 1 || Map.map[this.X-10-i][this.Y+29].Get() == 1)
					{
						if(this.huong == 3)
						{
							h_value [0] -= hDaoDong;
							h_value [2] -= hDaoDong;
						}

						h_value [3] += 999;
						System.out.println("3-"+h_value [3]);
						break;
					}
			
			}

			
			for(int i = 0; i <= 3; i++)
			{
				if(h_value [i] < v)
				{
					v = h_value [i];
					h = i;
				}
			}
			
		}
		System.out.println("h:"+h+"_this.max:"+this.max_move);
		System.out.println();
		if(this.max_move == 0)
			return h;
		else
			return this.huong;
	}
	
	
	public void Create_ChaseArea(int banKinh)
	{
		int a = 15;
		int b = 0;
		System.out.println(this.X+"+"+this.Y+"  ");
		//vi du bankinh = 5: quet 1 mang 11x11, do co so 0 nen tu 0 -> 10 la 11 so
//		for(int i = 0; i<=banKinh*2; i++)
//		{
//			for(int j = 0; j<=banKinh*2; j++)
//			{
		for(int i = 0; i<=banKinh*4; i++)
		{
			for(int j = 0; j<=banKinh*4; j++)
			{
				if(i>banKinh)
				{
					chaseArea[i][j][0]= this.X + a + (i-banKinh)*25;
					chaseArea[i][j][1]= this.Y + a + (j-banKinh)*25;
					chaseArea[i][j][2]= 0;
					chaseArea[i][j][3]= 200;
					chaseArea[i][j][4]= Heuristics(chaseArea[i][j][0], chaseArea[i][j][1], this.enemy_Pos_X, this.enemy_Pos_Y);
					chaseArea[i][j][5]= 0;
					chaseArea[i][j][6]= 0;
					chaseArea[i][j][7]= -1;
					chaseArea[i][j][8]= -1;
////					System.out.print(chaseArea[i][j][0]+"+"+chaseArea[i][j][1]+"+"+chaseArea[i][j][4]+"  ");
				}
				else
					if(i<=banKinh)
					{
						chaseArea[i][j][0]= this.X + a - (banKinh-i)*25;
						chaseArea[i][j][1]= this.Y + a - (banKinh-j)*25;
						chaseArea[i][j][2]= 0;
						chaseArea[i][j][3]= 200;
						chaseArea[i][j][4]= Heuristics(chaseArea[i][j][0], chaseArea[i][j][1], this.enemy_Pos_X, this.enemy_Pos_Y);
						chaseArea[i][j][5]= 0;
						chaseArea[i][j][6]= 0;
						chaseArea[i][j][7]= -1;
						chaseArea[i][j][8]= -1;
////						System.out.print(chaseArea[i][j][0]+"-"+chaseArea[i][j][1]+"-"+chaseArea[i][j][1]+"  ");
					}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public int[] minPath(int banKinh)
	{
		int min=9999;
		// 0:vi tri i; 1:vi tri j; 2:huong;
		int []hmin = new int [3];
//		for(int i = 0; i<=banKinh*2; i++)
//		{
//			for(int j = 0; j<=banKinh*2; j++)
		for(int i = 0; i<=banKinh*4; i++)
		{
			for(int j = 0; j<=banKinh*4; j++)
			{
				if(chaseArea[i][j][0] > 25 && chaseArea[i][j][0] < 875 && chaseArea[i][j][1] > 25 && chaseArea[i][j][1] < 725)
					if(chaseArea[i][j][2] < min && chaseArea[i][j][5] == 1 && chaseArea[i][j][6] == 0)
					{
						min = chaseArea[i][j][2];
						if(chaseArea[i][j][7] != -1)
						{
							hmin[0] = i;
							hmin[1] = j;
							//hmin[2] = chaseArea[i][j][7];
						}
						else
						{
							hmin[0] = i;
							hmin[1] = j;
							//hmin[2] = -2;
						}

					}
			}
		}
		return hmin;
		
	}
	
	public int Truy_Dau(int[] trace)
	{
		int huong = -1;
		while (chaseArea[trace[0]][trace[1]][8] != -1)
		{
//			if(chaseArea[trace[0]][trace[1]-1][7] == -1 && trace[1]-1 >=0)
//			{
//				huong = 0;
//				System.out.print("-1");
//				break;
//			}
//			if(chaseArea[trace[0]][trace[1]+1][7] == -1  && trace[1]+1 >=0)
//			{
//				huong = 2;
//				System.out.print("-1");
//				break;
//			}
//			if(chaseArea[trace[0]-1][trace[1]][7] == -1  && trace[0]-1 >=0)
//			{
//				huong = 3;
//				System.out.print("-1");
//				break;
//			}
//			if(chaseArea[trace[0]+1][trace[1]][7] == -1  && trace[0]+1 >=0)
//			{
//				huong = 1;
//				System.out.print("-1");
//				break;
//			}
			
			if(chaseArea[trace[0]][trace[1]][8] == 0)
			{
				trace[1]++;
				//luu cho vi tri moi huong cua vi tri dang xet
				chaseArea[trace[0]][trace[1]][7] = 0;
				System.out.print("0<-");
			}
			if(chaseArea[trace[0]][trace[1]][8] == 2)
			{
				trace[1]--;
				//luu cho vi tri moi huong cua vi tri dang xet
				chaseArea[trace[0]][trace[1]][7] = 2;
				System.out.print("2<-");
			}
			if(chaseArea[trace[0]][trace[1]][8] == 3)
			{
				trace[0]++;
				//luu cho vi tri moi huong cua vi tri dang xet
				chaseArea[trace[0]][trace[1]][7] = 3;
				System.out.print("3<-");
			}
			if(chaseArea[trace[0]][trace[1]][8] == 1)
			{
				trace[0]--;
				//luu cho vi tri moi huong cua vi tri dang xet
				chaseArea[trace[0]][trace[1]][7] = 1;
				System.out.print("1<-");
			}
			
		}
		return chaseArea[trace[0]][trace[1]][7];
	}
	
	
	public int Smart_Move()
	{
		int h = -1;
		int v = 999;
		int beh = -1;
		int behDaoDong = 35;
		int hDaoDong = 25;
////		int banKinh = 5;
		int banKinh = 20;
		int[] h_value= new int[200] ;
		int count = 0;
		//[0]: X; [1]: Y
		int[] trace= new int[2];

		
		this.Create_ChaseArea(banKinh);
		chaseArea[banKinh][banKinh][2] = 0;
		chaseArea[banKinh][banKinh][3] = 0;
		chaseArea[banKinh][banKinh][4] = 0;
		chaseArea[banKinh][banKinh][5] = 1;
		chaseArea[banKinh-1][banKinh][7] = 3;
		chaseArea[banKinh+1][banKinh][7] = 1;
		chaseArea[banKinh][banKinh-1][7] = 0;
		chaseArea[banKinh][banKinh+1][7] = 2;

////		for(int i=0; i<banKinh*2; i++)
		for(int i=0; i<banKinh*5; i++)
		{
			int dich = 3;
			if(this.type == 3)
				dich = 2;
			int []huongToiUu = new int [3];
			huongToiUu = minPath(banKinh);
			chaseArea[huongToiUu[0]][huongToiUu[1]][6] = 1;
//			Map.map[chaseArea[huongToiUu[0]][huongToiUu[1]][0]][chaseArea[huongToiUu[0]][huongToiUu[1]][1]].Set(-2);
			


			
			//kiem tra neu vi tri toi uu tiep theo thuoc hurtbox cua dich thi hoan thanh 

			
			
			h_value[count] = chaseArea[huongToiUu[0]][huongToiUu[1]][8];
			count ++;
//			if(chaseArea[huongToiUu[0]][huongToiUu[1]][0] > 25 && chaseArea[huongToiUu[0]][huongToiUu[1]][0] < 875
//			&&	chaseArea[huongToiUu[0]][huongToiUu[1]][1] > 25 && chaseArea[huongToiUu[0]][huongToiUu[1]][1] < 725
//					)
//				if(Map.map[chaseArea[huongToiUu[0]][huongToiUu[1]][0]][chaseArea[huongToiUu[0]][huongToiUu[1]][1]].Get() == dich)
//					break;
			
			System.out.println(chaseArea[huongToiUu[0]][huongToiUu[1]][0]+"+"+chaseArea[huongToiUu[0]][huongToiUu[1]][1]+"+"+chaseArea[huongToiUu[0]][huongToiUu[1]][8]+"  ");
//			for(int k=0; k<=banKinh*2; k++)
//			{
//				for(int l=0; l<=banKinh*2; l++)
			for(int k=0; k<=banKinh*4; k++)
			{
				for(int l=0; l<=banKinh*4; l++)					
				{
	//				int temp = M[2][u] + A[u][k];
					int temp = 0;
					int coTheDiDc = 0;
					
					//xem vi tri nay co phai huong Tay ko
					if(
					(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]-25) && 
					(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]) //&&
					//(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)
					)
					
					{
						System.out.print("T:");
						coTheDiDc = 1;
//						chaseArea[k][l][7] = 3;
//						System.out.print(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][7]+"  ");
//						if(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
//							System.out.println("BX");
					}
					
					//xem vi tri nay co phai huong Dong ko
					if(
					(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]+25) && 
					(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]) //&&
					//(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)
					)
					
					{
						System.out.print("D:");
						coTheDiDc = 1;
//						chaseArea[k][l][7] = 1;
//						System.out.print(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][7]+"  ");
//						if(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
//							System.out.println("BX");
					}

					//xem vi tri nay co phai huong Bac ko
					if(
					(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]) && 
					(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]-25) //&&
					//(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)
					)
					
					{
						System.out.print("B:");
						coTheDiDc = 1;
//						chaseArea[k][l][7] = 0;
//						System.out.print(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][7]+"  ");
//						if(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
//							System.out.println("BX");

					}
					
					//xem vi tri nay co phai huong Nam ko
					if(
					(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]) && 
					(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]+25) //&&
					//(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)
					)
					
					{
						System.out.print("N:");
						coTheDiDc = 1;
//						chaseArea[k][l][7] = 2;
//						System.out.print(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][7]+"  ");
//						if(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
//							System.out.println("BX");

					}					
					
					temp = chaseArea[huongToiUu[0]][huongToiUu[1]][3] + coTheDiDc;
//					if(M[4][k] == 1 && A[u][k] != 0 && temp < M[2][k])
//					M[4][k] = 0;
					if(chaseArea[k][l][5] == 1 && coTheDiDc != 0 && temp < chaseArea[k][l][3] && (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1))
						chaseArea[k][l][5] = 0;
					
//					if(M[5][k] == 1 && A[u][k] != 0 && temp < M[2][k])
//						M[5][k] = 0;

					if(chaseArea[k][l][6] == 1 && coTheDiDc != 0 && temp < chaseArea[k][l][3] && (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1))
						chaseArea[k][l][6] = 0;
					
//					if(M[4][k] == 0 && M[5][k] == 0 && A[u][k] != 0 && temp < M[2][k])
//					{
//						M[2][k] = temp;
//						M[4][k] = 1;
//						M[1][k] =  M[2][k] + M[3][k];
//						Trace[k] = u;
//					}
					if(chaseArea[k][l][0] > 25 && chaseArea[k][l][0] < 875 && chaseArea[k][l][1] > 25 && chaseArea[k][l][1] < 725
//					&&	chaseArea[k][l][0]+14 > 25 && chaseArea[k][l][0]+14 < 875 && chaseArea[k][l][1]+14 > 25 && chaseArea[k][l][1]+14 < 725
//					&&	chaseArea[k][l][0]-15 > 25 && chaseArea[k][l][0]-15 < 875 && chaseArea[k][l][1]-15 > 25 && chaseArea[k][l][1]-15 < 725
					)
					
					{
						if(chaseArea[k][l][5] == 0 && chaseArea[k][l][6] == 0 && coTheDiDc != 0 && temp < chaseArea[k][l][3]
						&& (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)		
//						&& (Map.map[chaseArea[k][l][0]+14][chaseArea[k][l][1]].Get() != 1)
//						&& (Map.map[chaseArea[k][l][0]-15][chaseArea[k][l][1]].Get() != 1)
//						&& (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]+14].Get() != 1)
//						&& (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]-15].Get() != 1)
								
								)
						{

							boolean choPhep = false;


							
							
							//xem vi tri nay co phai huong Tay ko
							if(
							(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]-25) && 
							(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]) 
							)
							
							{
								System.out.print("[T]");
								if(chaseArea[k][l][1]+14 > 25 && chaseArea[k][l][1]+14 < 725
								&& chaseArea[k][l][1]-15 > 25 && chaseArea[k][l][1]-15 < 725)
									if((Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]+14].Get() != 1)
									&& (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]-15].Get() != 1))								
									{
										chaseArea[k][l][8] = 3;
										choPhep = true;
										System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
									}
									else
										System.out.println("[XH2]");
								else
									System.out.println("[XH1]");
								if(i != 0 || Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 3)
								{
									chaseArea[k][l][8] = 3;
									choPhep = true;
									System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
								}
	
							}
							
							//xem vi tri nay co phai huong Dong ko
							if(
							(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]+25) && 
							(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]) 
							)
							
							{
								System.out.print("[D]");
								if(chaseArea[k][l][1]+14 > 25 && chaseArea[k][l][1]+14 < 725
								&& chaseArea[k][l][1]-15 > 25 && chaseArea[k][l][1]-15 < 725)
									if((Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]+14].Get() != 1)
									&& (Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]-15].Get() != 1))		
									{
										choPhep = true;
										chaseArea[k][l][8] = 1;
										System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
									}
									else
										System.out.println("[XH2]");
								else
									System.out.println("[XH1]");
								if(i != 0 || Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 3)
								{
									chaseArea[k][l][8] = 1;
									choPhep = true;
									System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
								}
							}
	
							//xem vi tri nay co phai huong Bac ko
							if(
							(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]) && 
							(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]-25) 
							)
							
							{
								System.out.print("[B]");
								if(chaseArea[k][l][0]+14 > 25 && chaseArea[k][l][0]+14 < 875 
								&& chaseArea[k][l][0]-15 > 25 && chaseArea[k][l][0]-15 < 875)
									if((Map.map[chaseArea[k][l][0]+14][chaseArea[k][l][1]].Get() != 1)
									&& (Map.map[chaseArea[k][l][0]-15][chaseArea[k][l][1]].Get() != 1))
									{
										choPhep = true;
										chaseArea[k][l][8] = 0;
										System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
									}
									else
										System.out.println("[XH2]");
								else
									System.out.println("[XH1]");
								if(i != 0 || Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 3)
								{
									chaseArea[k][l][8] = 0;
									choPhep = true;
									System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
								}
							}
							
							//xem vi tri nay co phai huong Nam ko
							if(
							(chaseArea[k][l][0] == chaseArea[huongToiUu[0]][huongToiUu[1]][0]) && 
							(chaseArea[k][l][1] == chaseArea[huongToiUu[0]][huongToiUu[1]][1]+25) //&&
							//(Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() != 1)
							)
							
							{
								System.out.print("[N]");
								if(chaseArea[k][l][0]+14 > 25 && chaseArea[k][l][0]+14 < 875 
								&& chaseArea[k][l][0]-15 > 25 && chaseArea[k][l][0]-15 < 875)
									if((Map.map[chaseArea[k][l][0]+14][chaseArea[k][l][1]].Get() != 1)
									&& (Map.map[chaseArea[k][l][0]-15][chaseArea[k][l][1]].Get() != 1))
									{
										choPhep = true;
										chaseArea[k][l][8] = 2;
										System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
									}
									else
										System.out.println("[XH2]");
								else
									System.out.println("[XH1]");
								if(i != 0 || Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 3)
								{
									chaseArea[k][l][8] = 2;
									choPhep = true;
									System.out.println(chaseArea[k][l][0]+"+"+chaseArea[k][l][1]+"+"+chaseArea[k][l][8]+"  ");
								}
							}	
							
							if(choPhep == true)
							{
								chaseArea[k][l][3] = temp;
								chaseArea[k][l][5] = 1;
								chaseArea[k][l][2] = chaseArea[k][l][3] + chaseArea[k][l][4];
								System.out.print(chaseArea[k][l][3]+"@");
								System.out.print(chaseArea[k][l][4]+"@");
								System.out.println(chaseArea[k][l][2]);
							}
									
	//						h_value[count] = chaseArea[k][l][7];
	//						count ++;
						}
						else
							//Di roi
							if(coTheDiDc == 1 && chaseArea[k][l][6] == 1)
								System.out.println("[X6]");
							else
								//Con trong hang doi
								if(coTheDiDc == 1 && chaseArea[k][l][5] == 1)
									System.out.println("[X5]");
								else
								{
									if(chaseArea[k][l][0] >25 && chaseArea[k][l][0] <875 && chaseArea[k][l][1] >25 && chaseArea[k][l][1] <725)
									{
										//Gap tuong		
										if(coTheDiDc == 1 && Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
											System.out.println("[XB]");
										else
											if(coTheDiDc == 1)
												System.out.println("[XO1]");
									}
									else
										//Gap tuong ngoai
										if(chaseArea[k][l][0] <=25 || chaseArea[k][l][0] >=875 || chaseArea[k][l][1] <=25 || chaseArea[k][l][1] >=725)
										{
											if(coTheDiDc == 1)
												System.out.println("[X-1]");
										}
										else
											if(coTheDiDc == 1)
												System.out.println("[XO2]");
								}	
					}
					else

						//Di roi
						if(coTheDiDc == 1 && chaseArea[k][l][6] == 1)
							System.out.println("[X6]");
						else
							//Con trong hang doi
							if(coTheDiDc == 1 && chaseArea[k][l][5] == 1)
								System.out.println("[X5]");
							else
							{
								if(chaseArea[k][l][0] >25 && chaseArea[k][l][0] <875 && chaseArea[k][l][1] >25 && chaseArea[k][l][1] <725)
								{
									//Gap tuong		
									if(coTheDiDc == 1 && Map.map[chaseArea[k][l][0]][chaseArea[k][l][1]].Get() == 1)
										System.out.println("[XB]");
									else
										if(coTheDiDc == 1)
											System.out.println("[XO1]");
								}
								else
									//Gap tuong ngoai
									if(chaseArea[k][l][0] <=25 || chaseArea[k][l][0] >=875 || chaseArea[k][l][1] <=25 || chaseArea[k][l][1] >=725)
									{
										if(coTheDiDc == 1)
											System.out.println("[X-1]");
									}
									else
										if(coTheDiDc == 1)
											System.out.println("[XO2]");
							}					
				}
		

			}
			
			trace[0]= huongToiUu[0];
			trace[1]= huongToiUu[1];
			System.out.println("TraceH:"+chaseArea[trace[0]][trace[1]][8]);
			System.out.println();
			if(chaseArea[huongToiUu[0]][huongToiUu[1]][0] > 25 && chaseArea[huongToiUu[0]][huongToiUu[1]][0] < 875
			&&	chaseArea[huongToiUu[0]][huongToiUu[1]][1] > 25 && chaseArea[huongToiUu[0]][huongToiUu[1]][1] < 725
					)
				if(Map.map[chaseArea[huongToiUu[0]][huongToiUu[1]][0]][chaseArea[huongToiUu[0]][huongToiUu[1]][1]].Get() == dich)
					break;			
		}
		
		System.out.println();
		for(int i =0; i <=8 ; i++)
		{
			System.out.print(h_value[i]+"->");
		}
		System.out.println();
//		int t;
//		t = Truy_Dau(trace);
		h = Truy_Dau(trace);
//		h=h_value[1];
		System.out.println("h:"+h);
		System.out.println();
		if(h == -1)
			return 1;
		else
			return h;
	}	
	
	public void View_v2()
	{
		//do rong cua tam nhin
		int doRong = 2;
		// Huong 0
		if(this.Pc_View_v2(this.X-doRong*10,this.Y-10,0,-1,doRong))
		{
			//this.re_direct = this.Stupid_Move();
			this.re_direct = this.Smart_Move();
			super.Setlc(this.X, this.Y, this.re_direct);

			enemy_Pos_X = -1;
			enemy_Pos_Y = -1;
//			super.Shoot();
		}
		else

		// Huong 1
		if(this.Pc_View_v2(this.X+10+this.size,this.Y-doRong*10,1,0,doRong))
		{
			//this.re_direct = this.Stupid_Move();
			this.re_direct = this.Smart_Move();
			super.Setlc(this.X, this.Y, this.re_direct);

			enemy_Pos_X = -1;
			enemy_Pos_Y = -1;
//			super.Shoot();

		}
		else
			
		//Huong 2
		if(this.Pc_View_v2(this.X-doRong*10,this.Y+10+this.size,0,1,doRong))
		{
			//this.re_direct = this.Stupid_Move();
			this.re_direct = this.Smart_Move();
			super.Setlc(this.X, this.Y, this.re_direct);
			
			enemy_Pos_X = -1;
			enemy_Pos_Y = -1;
//			super.Shoot();

		}
		else
			
		//Huong 3
		if(this.Pc_View_v2(this.X-10,this.Y-doRong*10,-1,0,doRong))
		{
			//this.re_direct = this.Stupid_Move();
			this.re_direct = this.Smart_Move();
			super.Setlc(this.X, this.Y, this.re_direct);
			enemy_Pos_X = -1;
			enemy_Pos_Y = -1;
//			super.Shoot();

		}
	
	}	
	
	
}
