package com.swinde.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.swinde.main.Game;

public class Tiles {
	
	/*Tiles*/
	public static final BufferedImage FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static final BufferedImage WALL  = Game.spritesheet.getSprite(16, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x;
	private int y;
	
	public Tiles(int x, int y, BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, getX()-Camera.x, getY()-Camera.y, null);
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public BufferedImage getSprite(){
		return sprite;
	}

	public void setSprite(BufferedImage sprite){
		this.sprite = sprite;
	}

	public BufferedImage getFLOOR(){
		return FLOOR;
	}


	public BufferedImage getWALL(){
		return WALL;
	}

	
}
