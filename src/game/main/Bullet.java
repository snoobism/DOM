package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	private double mx;
	private double my;
	private double angle;
	

	public Bullet(int x, int y, int w, int h, int v, ID id, int mx, int my) {
		super(x, y, w, h, v, id);

		this.mx = mx;
		this.my = my;
	    this.angle = (double) Math.atan(Math.abs(this.y - this.my) / Math.abs(this.x - this.mx)); // in PI
	    if(this.mx>Game.player.x){
            
	        this.velX= (v * Math.cos(this.angle)); 
	    }
	    if(this.my>Game.player.y){
	           
	        this.velY=  (v * Math.sin(this.angle));
	    }
	    if(this.mx<Game.player.x){
	        
	        this.velX= (-v * Math.cos(this.angle));
	    }
	    if(this.my<Game.player.y){
	        
	        this.velY= (-v * Math.sin(this.angle));
	    }
	}

	@Override
	public void tick() {

		for(int i = 0; i < Game.handler.enemies.size(); i++) {
			GameObject tempObject = Game.handler.enemies.get(i);
			if(tempObject.id == ID.Enemy)
			if(getBounds().intersects(((BasicEnemy)tempObject).getBounds())){
				Game.handler.removeObject(this);
				((BasicEnemy)tempObject).setHp(((BasicEnemy)tempObject).getHp() - Game.player.damage);
				SoundEffect.HIT.stop();
				SoundEffect.HIT.play();
				if(((BasicEnemy)tempObject).getHp() <= 0) {
					SoundEffect.DEAD.stop();
					SoundEffect.DEAD.play();
					Game.score += 100;
				}
			}
			if(tempObject.id == ID.FlyingEnemy)
			if(getBounds().intersects(((FlyingEnemy)tempObject).getBounds())){
				Game.handler.removeObject(this);
				((FlyingEnemy)tempObject).setHp(((FlyingEnemy)tempObject).getHp() - Game.player.damage);
				SoundEffect.FLY_HIT.stop();
				SoundEffect.FLY_HIT.play();
				if(((FlyingEnemy)tempObject).getHp() <= 0) {
					SoundEffect.FLY_DEAD.stop();
					SoundEffect.FLY_DEAD.play();
					Game.score += 100;
				}
			}
		}
		for(int i = 0; i < Game.handler.walls.size(); i++) {
			GameObject tempObject = Game.handler.walls.get(i);
			if(getBounds().intersects(((Wall)tempObject).getBounds())){
				Game.handler.removeObject(this);
			}
		}
		this.x += velX;
		this.y += velY;
		
		if(x < 0 || x > Game.WIDTH || y < 0 || y > Game.HEIGHT) {
			Game.handler.removeObject(this);
		}
	}
	


	@Override
	public void render(Graphics g) {
		g.drawImage(Images.bullet, x, y, w, h, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

}
