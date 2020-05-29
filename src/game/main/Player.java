package game.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import game.main.Game.STATE;

public class Player extends GameObject{
	
	public int[] keys = {0, 0, 0, 0};// WASD 
	private int hp = 100;
	public int damage = 6;
	private boolean invulnerability = false;
	private int invulnCountdown = 1000;
	
	public boolean canFire = true;
	public int fireRate = 800; 
	
	public Guns gun = Guns.Pistol;
	public int bulletsFired = 0;


	
	public Player(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
	}

	@Override
	public void tick() {
		// check for movement 
		if(bulletsFired > 80) {
			this.hp -= 1;
		}
		if(keys[0] == 1) { // W
			y -= velY;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					y += velY;
				}
			}
		}
		if(keys[1] == 1) { // A
			x -= velX;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX;
				}
			}
		}
		if(keys[2] == 1) { // S
			y += velY;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					y -= velY;
				}
			}
		}
		if(keys[3] == 1) { // D
			x += velX;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					x -= velX;
				}
			}
		}
		x = Game.clamp(x, 0, Game.WIDTH - w);
		y = Game.clamp(y, 0, Game.HEIGHT - h);
		
		collision();
		
		if(Game.mousePressed == true) {
			if(Game.gameState == STATE.Game) {
				if(canFire == true) {

					if (gun == Guns.Pistol) {
						SoundEffect.BULLET.stop();
						SoundEffect.BULLET.play();
						Game.handler.addObject(new Bullet(x + w/2, y + w/2, Game.t/15, Game.t/15, 10, ID.Bullet, Game.mx, Game.my));	
					}
					
					if (gun == Guns.Shotgun) {
						SoundEffect.SHOTGUN.stop();
						SoundEffect.SHOTGUN.play();
						for(int i = 0; i < 7; i++)
						Game.handler.addObject(new Bullet(x + w/2, y + w/2, Game.t/15, Game.t/15, 15, ID.Bullet, Game.mx + (new Random()).nextInt(Game.t) - Game.t/2, Game.my + (new Random()).nextInt(Game.t) - Game.t/2));						
					}
					
					if (gun == Guns.Minigun) {
						SoundEffect.BULLET.stop();
						SoundEffect.BULLET.play();
						bulletsFired++;
						Game.handler.addObject(new Bullet(x + w/2, y + w/2, Game.t/15, Game.t/15, 25, ID.Bullet, Game.mx + (new Random()).nextInt(Game.t) - Game.t/2, Game.my + (new Random()).nextInt(Game.t * 2) - Game.t));		
						new Timer().schedule(
								new TimerTask() {
										@Override
										public void run() {
											if(bulletsFired > 0) {
												bulletsFired--;
											}
										}
									}, 10000
								);
					}
					canFire = false;	
					new Timer().schedule(
						new TimerTask() {
								@Override
								public void run() {
									canFire = true;
								}
							}, fireRate
						);
				}
			}			
		}
		
	}
	
	private void collision() {
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			if(tempObject.id == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(((BasicEnemy)tempObject).getHp() > 0 && invulnerability == false) {
						hp-=10;
						SoundEffect.PAIN.stop();
						SoundEffect.PAIN.play();
						invulnerability = true;
						new Timer().schedule(
							new TimerTask() {
								@Override
								public void run() {
									invulnerability = false;
								}
							}, invulnCountdown
						);
					}else if(((BasicEnemy)tempObject).getHp() <= 0){
						hp = (Game.clamp(hp + 1, 0, 100));
						SoundEffect.BODY.stop();
						SoundEffect.BODY.play();
						Game.handler.removeObject(tempObject);
					}
	
				}				
			}
			if(tempObject.id == ID.FlyingEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(((FlyingEnemy)tempObject).getHp() > 0 && invulnerability == false) {
						hp-=10;
						SoundEffect.PAIN.stop();
						SoundEffect.PAIN.play();
						invulnerability = true;
						new Timer().schedule(
							new TimerTask() {
								@Override
								public void run() {
									invulnerability = false;
								}
							}, invulnCountdown
						);
					}else if(((FlyingEnemy)tempObject).getHp() <= 0){
						hp = (Game.clamp(hp + 1, 0, 100));
						SoundEffect.BODY.stop();
						SoundEffect.BODY.play();
						Game.handler.removeObject(tempObject);
					}
	
				}				
			}

		}

	}

	@Override
	public void render(Graphics g) {
		int centerX = x + w / 2;
		int centerY = y + h / 2;
		double angle = Math.atan2(centerY - Game.my, centerX - Game.mx) - Math.PI / 2;
		
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform transform = g2.getTransform();
		g2.rotate(angle, centerX, centerY);
		g.drawImage(Images.player, x, y, w, h, null);

		g2.setTransform(transform);
		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}
