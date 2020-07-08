package com.swinde.entities;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.swinde.main.Game;
import com.swinde.world.Camera;
import com.swinde.world.World;

public class Enemy extends Entity {
	
	public double SPEED = 0.4;
	/*Variáveis para controlar o tamanho do colisor*/
	public int xmask = 8, ymask = 8;
	public int wmask = 8, hmask = 10;
	/*Variáveis para controlar a animação do inimigo*/
	private int index, maxIndex;
	private int frames, maxFrames;
	private BufferedImage[] sprites;

	public Enemy(double x, double y, int width, int height, BufferedImage sprite[]) {
		super(x, y, width, height, null);
		this.index = 0;
		this.maxIndex = 1;
		this.frames = 0;
		this.maxFrames = 20;
		sprites = new BufferedImage[2];
		sprites[0] = sprite[0];
		sprites[1] = sprite[1];
		/*for (int i = 0; i < sprites.length; i++) {
			this.sprites[i] = Game.spritesheet.getSprite(7*16+(16*i), 16, 16, 16);
		}*/
		
	}
	
	public void update(){
		
		
		/*if(!this.isColliding()) {
			if((int)x < Game.player.x && World.isFree((int)(x+SPEED), this.getY()) &&
					!isColliding((int)(x+SPEED), this.getY())){
				x+=SPEED;
			}else if((int)x > Game.player.x && World.isFree((int)(x-SPEED), this.getY()) &&
					!isColliding((int)(x-SPEED), this.getY())){
				x-=SPEED;
			}
			
			if((int)y < Game.player.y && World.isFree(this.getX(), (int)(y+SPEED)) &&
					!isColliding(this.getX(), (int)(y+SPEED))){
				y+=SPEED;
			}else if((int)y > Game.player.y && World.isFree(this.getX(), (int)(y-SPEED)) &&
					!isColliding(this.getX(), (int)(y-SPEED))){
				y-=SPEED;
			}
		}else{
			if(Game.rand.nextInt(100) < 25){
				Game.player.life -= Game.rand.nextInt(5);
				//System.out.println(Game.player.life);
				Game.player.isDamage = true;
				if(Game.player.life <= 0){
					//System.exit(1);
				}
			}
		}
		
		
		frames++;
		if(frames == maxFrames){
			frames = 0;
			index++;
			if(index >maxIndex){
				index = 0;
			}
		}
	*/
		
	}

	public boolean isColliding(){
		Rectangle enemyCurrent = new Rectangle(this.getX() + xmask, this.getY() + ymask, wmask, hmask);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		return player.intersects(enemyCurrent);
	}
	
	public boolean isColliding(int xnext, int ynext){//Função para verificar se ocorre a colisão
		Rectangle enemyCurrent = new Rectangle(xnext + xmask, ynext + ymask, wmask, hmask);
		for (int i = 0; i < Game.enemys.size(); i++) {
			Enemy e = Game.enemys.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX()+ e.xmask, e.getY()+e.ymask, wmask, hmask);
			if(enemyCurrent.intersects(targetEnemy)){
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics g){
		g.drawImage(this.sprites[index], this.getX()- Camera.x, this.getY() - Camera.y, null);
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + xmask - Camera.x, this.getY()+ ymask - Camera.y, wmask, hmask);
	}
}
