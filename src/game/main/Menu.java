package game.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.main.Game.STATE;


public class Menu extends MouseAdapter{
	

	public Menu() {

	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// start
		if (mouseOver(mx, my, Game.WIDTH/4, Game.HEIGHT/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8) && Game.gameState == STATE.Menu){
			Game.gameState = STATE.Game;
			Game.handler = new Handler();
			Game.player = new Player(Game.WIDTH/2, Game.HEIGHT/2, Game.t/4, Game.t/2, Game.t/20, ID.Player);
			Game.handler.addObject(Game.player);
			Game.score = 0;
			for(int i = 0; i < Game.mapH; i++) {
				for(int j = 0; j < Game.mapW; j++) {
					if(Game.map[i][j] == 'w') {
						Game.handler.addObject(new Wall((Game.WIDTH/Game.mapW)*j, (Game.HEIGHT/Game.mapH)*i, Game.WIDTH/Game.mapW, Game.HEIGHT/Game.mapH, 0, ID.Wall));	
					}
					if(Game.map[i][j] == 'e') {
						Game.handler.addObject(new BasicEnemy((Game.WIDTH/Game.mapW)*j, (Game.HEIGHT/Game.mapH)*i, Game.t/2, Game.t/4, 1, ID.Enemy));
					}
				}
				
			}
		}
		

		
		// exit
		if (mouseOver(mx, my, Game.WIDTH/4, Game.HEIGHT*3/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8)  && Game.gameState == STATE.Menu){
			System.exit(0);
		}
		

	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void tick() {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
		if(mx > x && mx < x + w) {
			if(my > y && my < y + h) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
		    g.drawImage(Images.bg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			
		    g.drawImage(Images.dom, Game.WIDTH/4, Game.HEIGHT/40, Game.WIDTH/2, Game.HEIGHT/5, null);
			
			//g.setColor(Color.white);
			//g.fillRect(Game.WIDTH/4, Game.HEIGHT/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8);
		    g.drawImage(Images.start, Game.WIDTH/4, Game.HEIGHT/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8, null);
	
			
			//g.setColor(Color.white);
			//g.fillRect(Game.WIDTH/4, Game.HEIGHT*3/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8);
		    g.drawImage(Images.exit, Game.WIDTH/4, Game.HEIGHT*3/4 + Game.t/2, Game.WIDTH/2, Game.HEIGHT/8, null);
		}
	}
}
