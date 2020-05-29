package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Shotgun extends GameObject{

	public Shotgun(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(getBounds().intersects(Game.player.getBounds())) {
			Game.player.gun = Guns.Shotgun;
			Game.player.fireRate = 1200;
			Game.player.damage = 2;
			Game.score += 100;
			SoundEffect.WEAPON.stop();
			SoundEffect.WEAPON.play();
			Game.handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.shotgun, x, y, w, h, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
}
