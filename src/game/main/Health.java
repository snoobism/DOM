package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Health extends GameObject{

	private int value = 20;
	public Health(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(getBounds().intersects(Game.player.getBounds())) {
			Game.player.setHp(Game.clamp(Game.player.getHp() + this.value, 0, 100));
			Game.handler.object.remove(this);
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Images.health, x, y, w, h, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

}
