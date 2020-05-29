package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public HUD() {
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("LucidaSans", Font.BOLD, Game.t/3);
		g.setColor(Color.gray);
		g.fillRect(Game.t/2, Game.t/2, Game.t, Game.t/6);
		g.setColor(Color.green);
		g.fillRect(Game.t/2, Game.t/2, (int)((float)Game.player.getHp()/100 * Game.t), Game.t/6);
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(String.valueOf(Game.score), Game.t/2, Game.t);
	}
}
