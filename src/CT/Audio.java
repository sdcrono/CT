package CT;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import CT.Map.Screen;


public class Audio {
	
	//int delay_move =0;
	public void Tank_move()
	{

//		if(this.delay_move == 0)
//		{	
//			try {
//				Screen.audio("Battle City di.wav");
//			} catch (UnsupportedAudioFileException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (LineUnavailableException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			this.delay_move = 50;
//			
//		}
//		else
//			this.delay_move --;

	}
	
	public void Tank_shoot()
	{
		
		try {
			Screen.audio("Battle City ban.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Tank_boss_shoot()
	{
		
		try {
			Screen.audio("cannon.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Drift()
	{
		try {
			Screen.audio("tires.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public void Tank_destroy()
	{
		
		try {
			Screen.audio("Battle City no.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Tank_losing()
	{
		
		try {
			Screen.audio("losing.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Intro_theme()
	{
		
		try {
			Screen.audioLoop("USF4Theme8Bit.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void Intro_theme_end()
	{
		
		try {
			Screen.audioLoopStop("USF4Theme8Bit.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		
	
	public void BG_theme()
	{
		
		try {
			Screen.audioLoop("bg theme.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void BG_theme_end()
	{
		
		try {
			Screen.audioLoopStop("bg theme.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		
	
	public void Ending_theme()
	{
		
		try {
			Screen.audio("Will U Remember Mi.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void Tank_flash()
	{
		
		try {
			Screen.audio("Flash_success.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

	public void Tank_flash_miss()
	{
		
		try {
			Screen.audio("Flash_miss.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Tank_flash_fail()
	{
		
		try {
			Screen.audio("Flash_fail.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Tank_flash_cd_unfinished()
	{
		
		try {
			Screen.audio("ability_cd.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Tank_flash_cd_unfinished_2()
	{
		
		try {
			Screen.audio("ability_cd_2.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void Tank_flash_cd_unfinished_3()
	{
		
		try {
			Screen.audio("ability_cd_3.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public void Tank_flash_cd_finished()
	{
		
		try {
			Screen.audio("saberup.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void Alarm()
	{
		try {
			Screen.audio("targetingwarning.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}		

	public void Alarm_off()
	{
		try {
			Screen.audio("saberdown.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public void Tankboss_laughing()
	{
		
		try {
			Screen.audio("laugh_like_a_boss.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Tankbot_respect()
	{
		
		try {
			Screen.audio("impressive.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Tankbot_laughing()
	{
		
		try {
			Screen.audio("laugh_taunt.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	public void Girl_die()
	{
		try {
			Screen.audio("girldie.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public void Shut_down()
	{
		try {
			Screen.audio("shutdown.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void Kill_streak()
	{

		if(TanksPlayer.score == 1)
		{
			try {
				Screen.audio("firstblood.wav");
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		switch(TanksPlayer.streaktocount)
		{
			case 3:			
				try {
					Screen.audio("killingspree.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:			
				try {
					Screen.audio("dominating.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:			
				try {
					Screen.audio("megakill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:			
				try {
					Screen.audio("unstoppable.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:			
				try {
					Screen.audio("whickedsick.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:			
				try {
					Screen.audio("ludicrouskill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:			
				try {
					Screen.audio("monsterkill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 10:			
				try {
					Screen.audio("godlike.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	
		if(TanksPlayer.score >10)			
			try {
				Screen.audio("holyshit.wav");
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void Kill_streak_female()
	{


		switch(TanksPlayer.score)
		{
			case 1:
				try {
					Screen.audio("f_headshot.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:			
				try {
					Screen.audio("f_killingspree.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:			
				try {
					Screen.audio("f_dominating.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:			
				try {
					Screen.audio("f_megakill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:			
				try {
					Screen.audio("f_unstoppable.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:			
				try {
					Screen.audio("f_wickedsick.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:			
				try {
					Screen.audio("f_ludacrisskill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:			
				try {
					Screen.audio("f_monsterkill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 10:			
				try {
					Screen.audio("f_godlike.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 11:			
				try {
					Screen.audio("f_holyshit.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	
		if(TanksPlayer.score >10)			
			try {
				Screen.audio("stfu.wav");
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
	}

	public void Chain_streak()
	{


		switch(TanksPlayer.streaktostack)
		{
			case 1:
				try {
					Screen.audio("doublekill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;				
			case 2:			
				try {
					Screen.audio("triplekill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:			
				try {
					Screen.audio("ultrakill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:			
				try {
					Screen.audio("rampage.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:			
				try {
					Screen.audio("flawless.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:			
				try {
					Screen.audio("ownage.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
//			case 8:			
//				try {
//					Screen.audio("ludicrouskill.wav");
//				} catch (UnsupportedAudioFileException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (LineUnavailableException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 9:			
//				try {
//					Screen.audio("monsterkill.wav");
//				} catch (UnsupportedAudioFileException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (LineUnavailableException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 10:			
//				try {
//					Screen.audio("godlike.wav");
//				} catch (UnsupportedAudioFileException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (LineUnavailableException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
		}
//	
//		if(TanksPlayer.score >10)			
//			try {
//				Screen.audio("holyshit.wav");
//			} catch (UnsupportedAudioFileException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (LineUnavailableException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
	}
	
	public void Enemy_kill_streak()
//	public void Kill_streak()
	{


		switch(TanksPlayer.score)
		{
			case 0:
				try {
					Screen.audio("f_headshot.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 2:			
				try {
					Screen.audio("f_killingspree.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:			
				try {
					Screen.audio("f_dominating.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:			
				try {
					Screen.audio("f_megakill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:			
				try {
					Screen.audio("f_unstoppable.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:			
				try {
					Screen.audio("f_wickedsick.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:			
				try {
					Screen.audio("f_ludacrisskill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:			
				try {
					Screen.audio("f_monsterkill.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:			
				try {
					Screen.audio("f_godlike.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 10:			
				try {
					Screen.audio("f_holyshit.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	
		if(TanksPlayer.score >10)			
			try {
				Screen.audio("stfu.wav");
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	public void The_last_standing()
	{
		try {
			Screen.audio("f_oneandonly.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
