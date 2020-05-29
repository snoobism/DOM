package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Minigun extends GameObject{

	public Minigun(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(getBounds().intersects(Game.player.getBounds())) {
			Game.player.gun = Guns.Minigun;
			Game.player.fireRate = 100;
			Game.player.damage = 2;
			Game.score += 50;
			SoundEffect.WEAPON.stop();
			SoundEffect.WEAPON.play();
			Game.handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.minigun, x, y, w, h, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
}
