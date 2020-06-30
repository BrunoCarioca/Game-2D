package com.swinde.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.swinde.main.Game;

public class Player extends Entity{

	/*Variáveis para controlar o movimento*/
	private boolean right, left, up, down;
	private final double SPEED = 1.2;
	/*Variáveis que recebem as sprites de animação*/
	private	BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int index, maxIndex; 
	/*Variáveis de controle de animação*/
	private boolean lookLeft;
	private boolean moved;
	private int frames = 0, maxFrames = 5;
	
	public Player(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.right = false;
		this.left  = false;
		this.up	   = false;
		this.down  = false;
		this.index = 0;
		this.maxIndex = 3;
		this.rightPlayer = new BufferedImage[4];
		this.leftPlayer  = new BufferedImage[4];
		this.lookLeft = false;
		this.moved = false;
		
		for (int i = 0; i < 4 ; i++) {
			this.rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
			this.leftPlayer[i]  = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}
		
	}
	
	public void update(){
		moved = false;
		if(right){
			moved = true;
			lookLeft = false;
			x+=SPEED;
		}else if(left) {
			moved = true;
			lookLeft = true;
			x-=SPEED;
		}
		if(up) {
			moved = true;
			y-=SPEED;
		}else if(down) {
			moved = true;
			y+=SPEED;
		}
	
		if(moved){
			frames++;
			if(frames == maxFrames){
				frames = 0;
				index++;
				if(index == maxIndex){
					index = 0;
				}
			}
		}
	
	}

	public void render(Graphics g){
		if(lookLeft){
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		}else{
			g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
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
