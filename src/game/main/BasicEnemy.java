package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class BasicEnemy extends GameObject{
	
	private int hp = 6;
	private Player player;
	private double angle = 0;
	public BasicEnemy(int x, int y, int w, int h, int v, ID id) {
		super(x, y, w, h, v, id);
		this.player = Game.player;
	}

	@Override
	public void tick() {
		if(x + w/2 > player.getX() + player.w/2) {
			this.x -= this.velX;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX;
				}
			}
			for(int i = 0; i < Game.handler.enemies.size(); i++) {
				GameObject tempObject = Game.handler.enemies.get(i);
				if(!tempObject.equals(this))
					if(getBounds().intersects(tempObject.getBounds())) {
						this.x += this.velX;
					}
			}
			if(getDistance(this, player) < player.w/2) {
				this.x += this.velX;
			}
			
		}
		if(x + w/2 < player.getX() + player.w/2) {
			this.x += this.velX;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					this.x -= this.velX;
				}
			}
			for(int i = 0; i < Game.handler.enemies.size(); i++) {
				GameObject tempObject = Game.handler.enemies.get(i);
				if(!tempObject.equals(this))
					if(getBounds().intersects(tempObject.getBounds())) {
						this.x -= this.velX;
					}
			}
			if(getDistance(this, player) < player.w/2) {
				this.x -= this.velX;
			}
		}
		if(y + h/2> player.getY() + player.h/2) {
			this.y -= this.velY;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					this.y += this.velY;
				}
			}
			for(int i = 0; i < Game.handler.enemies.size(); i++) {
				GameObject tempObject = Game.handler.enemies.get(i);
				if(!tempObject.equals(this))
					if(getBounds().intersects(tempObject.getBounds())) {
						this.y += this.velY;
					}
			}
			if(getDistance(this, player) < player.w/2) {
				this.y += this.velY;
			}
		}
		if(y + h/2 < player.getY() + player.h/2 ) {
			this.y += this.velY;
			for(int i = 0; i < Game.handler.walls.size(); i++) {
				GameObject tempObject = Game.handler.walls.get(i);
				if(getBounds().intersects(tempObject.getBounds())) {
					this.y -= this.velY;
				}
			}
			for(int i = 0; i < Game.handler.enemies.size(); i++) {
				GameObject tempObject = Game.handler.enemies.get(i);
				if(!tempObject.equals(this))
					if(getBounds().intersects(tempObject.getBounds())) {
						this.y -= this.velY;
					}
			}
			if(getDistance(this, player) < player.w/2 + this.w/2) {
				this.y -= this.velY;
			}
		}
	}
	
	public double getDistance(GameObject a, GameObject b) {
	    Point result = new Point(); 
	    result.x = Math.abs (a.x + a.w/2- b.x - b.w/2);
	    result.y = Math.abs (a.y + a.h/2- b.y - b.h/2);    
	    double dist = Math.sqrt((result.y)*(result.y) +(result.x)*(result.x));
	    return dist; 
	}
	
	@Override
	public void render(Graphics g) {
		int centerX = x + w / 2;
		int centerY = y + h / 2;
		
		if(this.hp > 0) {
			angle = Math.atan2(centerY - Game.player.y - Game.player.h/2, centerX - Game.player.x - Game.player.w/2) - Math.PI / 2;
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform transform = g2.getTransform();
			g2.rotate(angle, centerX, centerY);
			g.drawImage(Images.div, x, y, w, h, null);
			g2.setTransform(transform);
		}
		else {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform transform = g2.getTransform();
			g2.rotate(angle, centerX, centerY);	
			g.drawImage(Images.div_dead, x ,y , w, h, null);
			g2.setTransform(transform);

		}
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
