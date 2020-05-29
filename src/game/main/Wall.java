package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{

	public Wall(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.wall, x ,y , w, h, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
	
	
}
