package CT2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Score {
	
	public static int rank = 0;	// thu hang tren bang xep hang
	public static int score_sv[] = new int [11]; // bang xep hang.
	
	public static void Score(String scr) throws IOException 
	{
		
		int t1,t2;
		String out;
		
		//UrlFtp.Download(scr);
		
		FileInputStream f = new FileInputStream(scr);
		Scanner in = new Scanner(new InputStreamReader(f,"UTF-8"));


		for(int i=1; i<=10; i++)
		{
			score_sv[i] = Integer.parseInt(in.next());

		}

		in.close();
		
		for(int i = 1; i<=10; i++)
		{
			if(TanksPlayer.score >  score_sv[i])
			{
				rank = i ;
				t1 = score_sv[i];
				
				for(int j = i; j<=9; j++)
				{
					t2 = score_sv[j+1];
					score_sv[j+1] = t1;
					t1 = t2;
					
				}
				
				score_sv[i] = TanksPlayer.score;
				break;
			}
		}
		
		FileOutputStream fw =new FileOutputStream(scr);
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(fw,"UTF-8"));
		for(int i=1;i<=10;i++)
		{
			out = String.valueOf(score_sv[i]);
			System.out.println(out);
			
			bw.write(out);
			bw.write("\n");

		}
		bw.close();
		
		//UrlFtp.Upload(scr);
	}
	
	
}
