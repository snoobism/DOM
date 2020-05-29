package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	
	public KeyInput() {

	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					((Player)tempObject).keys[0] = 1;
				}
				if(key == KeyEvent.VK_A) {
					((Player)tempObject).keys[1] = 1;
				}
				if(key == KeyEvent.VK_S) {
					((Player)tempObject).keys[2] = 1;
				}
				if(key == KeyEvent.VK_D) {
					((Player)tempObject).keys[3] = 1;
				}
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					((Player)tempObject).keys[0] = 0;
				}
				if(key == KeyEvent.VK_A) {
					((Player)tempObject).keys[1] = 0;
				}
				if(key == KeyEvent.VK_S) {
					((Player)tempObject).keys[2] = 0;
				}
				if(key == KeyEvent.VK_D) {
					((Player)tempObject).keys[3] = 0;
				}
				
			}
		}
	}

}
