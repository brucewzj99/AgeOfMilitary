package object;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.*;

public class Controller{
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();// list to store objects created when key pressed
	private LinkedList<Explosion> e = new LinkedList<Explosion>();
	private LinkedList<DestroyedCrate> d = new LinkedList<DestroyedCrate>();
	
	private Explosion Tempexplosion;
	private Bullet Tempbullet;
	private DestroyedCrate Tempdestroyedcrate;
	
	
	public void update() {
		for (int i=0; i < b.size(); i++) {
			Tempbullet = b.get(i);
			Tempbullet.update();

		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i=0; i < b.size(); i++) {
			Tempbullet = b.get(i);
			Tempbullet.draw(g2);
		}
		for (int i=0; i < e.size(); i++) {
			Tempexplosion = e.get(i);
			
			Tempexplosion.draw(g2);
		}
		for (int i=0; i < d.size(); i++) {
			Tempdestroyedcrate = d.get(i);
			
			Tempdestroyedcrate.draw(g2);
		}
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
	}
	
	public void removeBullet(Entity block) {
		b.remove(block);
	}
	public void addExplosion(Explosion block) {
		e.add(block);
	}
	
	public void removeExplosion(Explosion block) {
		e.remove(block);
	}

	public void addDestroyedCrate(DestroyedCrate block) {
		d.add(block);
	}
	
	public void removeDestroyedCrate(DestroyedCrate block) {
		d.remove(block);
	}
}
