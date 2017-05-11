package gui;

import org.newdawn.slick.Graphics;

/**
 * Created by Tic-Tac on 09/05/2017.
 */
public class Timer {
	public long startTime;
	public long minutes;
	public long secondes;
	public long dixiemes;
	public long millis;

	public Timer(long time){
		startTime=System.currentTimeMillis();
		millis=startTime-time;
		while(millis>999){
			millis-=1000;
			secondes++;
		}
		while(secondes>59){
			secondes-=60;
			minutes++;
		}
		dixiemes=millis/100;
	}

	public void update(int i){
		millis+=i;
		while(millis>999){
			millis-=1000;
			secondes++;
		}
		while(secondes>59){
			secondes-=60;
			minutes++;
		}
		dixiemes=millis/100;
	}
	public void render(Graphics g){
		g.drawString("Temps ecoulé:"+minutes+":"+secondes+":"+dixiemes, 700,100);
	}

	public void reset(){
		minutes=0;
		millis=0;
		secondes=0;
		dixiemes=0;
	}

}
