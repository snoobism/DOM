package game.main;

import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Spawner {
	
	// Health t/5
	// div t/2 | t/4
	private int x, y, w, h, id;
	private int[] arrW = {Game.t / 2, Game.t / 4};
	private int[] arrH = {Game.t / 4, Game.t / 4};
	private int num = 2;
	private boolean spawning = true;
	private boolean spawningPower = true;
	private int timer = 500;
	private int timerPowerUp = 1000 * 10;
	
	public void tick() {
		if(spawning == true) {
			this.spawn();
			spawning = false;
			new Timer().schedule(
				new TimerTask() {
					@Override
					public void run() {
						spawning = true;
					}
				}, timer
			);
			
		}
		if(spawningPower == true) {
			this.spawnPower();
			spawningPower = false;
			new Timer().schedule(
					new TimerTask() {
						@Override
						public void run() {
							spawningPower = true;
						}
					}, timerPowerUp
				);
		}
		
	}
	
	private void spawnPower() {
		Random r = new Random();
		x = r.nextInt(Game.WIDTH);
		y = r.nextInt(Game.HEIGHT);
		id = r.nextInt(7);
		w = Game.t/2;
		h = Game.t/2;
		boolean intersection = false;
		
		Rectangle spawnedObject = new Rectangle(x, y, w, h);
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			if(spawnedObject.getBounds().intersects(tempObject.getBounds())) {
				if(tempObject.getId() != ID.Enemy) {
					intersection = true;
				}

			}
		}
		if(intersection == true) {
			spawnPower();
		}
		if(intersection == false) {
			if(id == 0 || id == 4) {
				Game.handler.addObject(new Shotgun(x, y, w, h, 0, ID.Shotgun));		
			}
			if(id == 1 || id == 2) {
				Game.handler.addObject(new Pistol(x, y, w, h, 0, ID.Pistol));		
			}
			if(id == 5) {
				Game.handler.addObject(new Minigun(x, y, w, h, 0, ID.Minigun));		
			}
			if(id == 7 || id == 6) {
				Game.handler.addObject(new Health(x, y, w, h, 0, ID.Minigun));		
			}
		}
	}

	private void spawn() {
		Random r = new Random();
		x = r.nextInt(Game.WIDTH);
		y = r.nextInt(Game.HEIGHT);
		id = r.nextInt(num);
		w = arrW[id];
		h = arrH[id];
		
		boolean intersection = false;
		
		Rectangle spawnedObject = new Rectangle(x, y, w, h);
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			if(spawnedObject.getBounds().intersects(tempObject.getBounds())) {
				if(tempObject.getId() == ID.Enemy) {
					if(((BasicEnemy)tempObject).getHp() > 0){
						intersection = true;
					}
				}
				else
				if(tempObject.getId() == ID.FlyingEnemy) {
					if(((FlyingEnemy)tempObject).getHp() > 0){
						intersection = true;
					}
				}
				else{
					intersection = true;
				}
			}
		}
		
		if(intersection == false) {
		
			if(id == 0) {
				Game.handler.addObject(new BasicEnemy(x, y, w, h, 1, ID.Enemy));
			}
			if(id == 1) {
				Game.handler.addObject(new FlyingEnemy(x, y, w, h, 2, ID.FlyingEnemy));
			}	
		}
		
	}
}
