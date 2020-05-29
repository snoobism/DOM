package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import game.main.Game.STATE;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<GameObject> enemies = new LinkedList<GameObject>();
	LinkedList<GameObject> walls = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.id == ID.Player) {
				if(((Player)tempObject).getHp() <= 0) {
					removeObject(tempObject);
					JOptionPane.showMessageDialog(null, Game.score);
					Game.gameState = STATE.Menu;
					continue;
				}
			}
			if(tempObject.id == ID.Enemy) {
				if(((BasicEnemy)tempObject).getHp() <= 0){
					enemies.remove(tempObject);
					continue;
				}
			}
			if(tempObject.id == ID.FlyingEnemy) {
				if(((FlyingEnemy)tempObject).getHp() <= 0){
					enemies.remove(tempObject);
					continue;
				}
			}
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		Game.player.render(g);

	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
		if(object.getId() == ID.Enemy) {
			this.enemies.add(object);
		}
		if(object.getId() == ID.FlyingEnemy) {
			this.enemies.add(object);
		}
		if(object.getId() == ID.Wall) {
			this.walls.add(object);
		}
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
