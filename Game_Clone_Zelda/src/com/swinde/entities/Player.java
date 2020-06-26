package com.swinde.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity{

	/*Variáveis para controlar o movimento*/
	private boolean right, left, up, down;
	private final double SPEED = 1.2;
	
	public Player(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.right = false;
		this.left  = false;
		this.up	   = false;
		this.down  = false;
		
	}
	
	public void update(){
		if(right){
			x+=SPEED;
		}else if(left) {
			x-=SPEED;
		}
		if(up) {
			y-=SPEED;
		}else if(down) {
			y+=SPEED;
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	
	
}
