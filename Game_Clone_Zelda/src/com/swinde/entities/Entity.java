package com.swinde.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.swinde.main.Game;
import com.swinde.world.Camera;

public class Entity {
	
	/*Sprites das entidades*/
	public static BufferedImage ENEMY_EN 	= Game.spritesheet.getSprite(7*16, 16, 16, 16);
	public static BufferedImage BULLET_EN 	= Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static BufferedImage MEDKIT_EN	= Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN 	= Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static BufferedImage WEAPON_ENR 	= Game.spritesheet.getSprite(8*16, 0, 16, 16);
	public static BufferedImage WEAPON_ENL  = Game.spritesheet.getSprite(9*16, 0, 16, 16);
	
	/*Classe que representar todos os personagens do jogo*/
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	
	/*Variáveis configuram a mask de colisão*/
	private int xmask, ymask;
	private int wmask, hmask;
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite){
		this.x		= x;
		this.y 		= y;
		this.width 	= width;
		this.height = height;
		this.sprite = sprite;
		/*Setando mask*/
		this.xmask = 0;
		this.ymask = 0;
		this.wmask = width;
		this.hmask = height;
	
	}
	
	public void setMask(int xmask, int ymask, int wmask, int hmask){
		this.xmask = xmask;
		this.ymask = ymask;
		this.wmask = wmask;
		this.hmask = hmask;
	}
	
	public static boolean isCollinding(Entity e1, Entity e2){
		Rectangle entity1 = new Rectangle(e1.getX() + e1.xmask, e1.getY() + e1.ymask, e1.wmask, e1.hmask);
		Rectangle entity2 = new Rectangle(e2.getX() + e2.xmask, e2.getY() + e2.ymask, e2.wmask, e2.hmask);
		
		return entity1.intersects(entity2);
	}
	
	public void update(){
		
	}		
	
	public void render(Graphics g){
		g.drawImage(this.getSprite(), this.getX()-Camera.x, this.getY()-Camera.y, null);
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
