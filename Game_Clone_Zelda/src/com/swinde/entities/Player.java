package com.swinde.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import com.swinde.main.Game;
import com.swinde.world.Camera;
import com.swinde.world.World;

public class Player extends Entity{

	/*Variáveis para controlar o movimento*/
	private boolean right, left, up, down;
	private final double SPEED = 1;
	/*Variáveis que recebem as sprites de animação*/
	private	BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int index, maxIndex; 
	/*Variáveis de controle de animação*/
	private boolean lookLeft;
	private boolean moved;
	private int frames = 0, maxFrames = 5;
	/*Variáveis vida*/
	public double life = 100;
	public double maxLife = 100;
	/*Variaveis para o controle do ataque*/
	private boolean hasGun 	= false;
	public boolean shooting = false;
	public int ammo;
	/*Variaveis de feedback*/
	public boolean isDamage = false;
	private BufferedImage[] spriteDamage;
	private int damageFrame = 0;
	private int damageMaxFrame = 5;

	
	
	
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
		this.ammo = 0;
		this.spriteDamage = new BufferedImage[2];
		
		for (int i = 0; i < 2; i++) {
			this.spriteDamage[i] = Game.spritesheet.getSprite(0 + (16*i), 16, 16, 16);
		}
		
		for (int i = 0; i < 4 ; i++) {
			this.rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
			this.leftPlayer[i]  = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}
		
	}
	
	public void update(){
		moved = false;
		if(right && World.isFree((int)(x+SPEED), this.getY())){
			moved = true;
			lookLeft = false;
			x+=SPEED;
		}else if(left && World.isFree((int)(x-SPEED), this.getY())) {
			moved = true;
			lookLeft = true;
			x-=SPEED;
		}
		if(up && World.isFree(this.getX(), (int)(y-SPEED))) {
			moved = true;
			y-=SPEED;
		}else if(down && World.isFree(this.getX(), (int)(y+SPEED))) {
			moved = true;
			y+=SPEED;
		}

		
		if(moved){
			frames++;
			if(frames == maxFrames){
				frames = 0;
				index++;
				if(index > maxIndex){
					index = 0;
				}
			}
		
		}
		
		if(isDamage){
			damageFrame++;
			if(damageFrame == damageMaxFrame){
				isDamage = false;
				damageFrame = 0;
			}
		}
		
		this.checkCollectMedKit();
		this.checkCollisionAmmo();
		this.checkCollectWeapon();
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.width*16 - Game.WIDTH);
		
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.height*16 - Game.HEIGHT);
	}


	public void checkCollectWeapon(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity e = Game.entities.get(i);
			if(e instanceof Weapon){
				if(Entity.isCollinding(this, e)) {
					hasGun = true;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}
	
	public void checkCollectMedKit(){
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof MedKit){
				if(Entity.isCollinding(this, e)){
					life += 8;
					if(life >= 100)
						life = 100;
					Game.entities.remove(i);	
					return;
				}
			}
	}
		}
	public void render(Graphics g){
		//g.setColor(Color.blue);
		//g.fillRect(this.getX()-Camera.x+3, this.getY()-Camera.y, 16, 16);
		if(!isDamage) {
			if(lookLeft){
				g.drawImage(leftPlayer[index], this.getX( )- Camera.x, this.getY() - Camera.y, null);
				if(hasGun)
					g.drawImage(WEAPON_ENL, this.getX() - 8 - Camera.x, this.getY() - Camera.y, null);
			}else{
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
				if(hasGun)
					g.drawImage(WEAPON_ENR, this.getX() + 8 - Camera.x, this.getY() - Camera.y, null);
			}
		}else{
			if(lookLeft){
				g.drawImage(spriteDamage[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else{
				g.drawImage(spriteDamage[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
	}
	
	public void checkCollisionAmmo() {
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet) {
				if(Entity.isCollinding(this, atual)) {
					ammo+=10;
					//System.out.println("Pegou a arma!");
			
					Game.entities.remove(atual);
				}
			}
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
