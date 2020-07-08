package com.swinde.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BulletShoot extends Entity{

	public int dx;
	public int dy;
	public double spd = 1;
	
	public BulletShoot(double x, double y, int width, int height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}

	public void update(){
		
	}
	public void render(Graphics g){
		
	}
	
}
