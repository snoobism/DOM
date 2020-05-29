package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
public abstract class GameObject {	
	protected int x, y, w, h;
	protected ID id;
	double velX;
	double velY;
	
	public GameObject(int x, int y, int w, int h, int v, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.w = w;
		this.h = h;
		this.velX = v;
		this.velY = v;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	

		
	
}
