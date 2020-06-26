package com.swinde.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	/*Classe que representar todos os personagens do jogo*/
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite){
		this.x		= x;
		this.y 		= y;
		this.width 	= width;
		this.height = height;
		this.sprite = sprite;
	}

	public void update(){
		
	}		
	public void render(Graphics g){
		g.drawImage(this.getSprite(), this.getX(), this.getY(), null);
	}
	
	
	public int getX() {
		return (int)x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public BufferedImage getSprite(){
		return sprite;
	}
	
	public void setSprite(BufferedImage sprite){
		this.sprite = sprite;
	}
	

}
