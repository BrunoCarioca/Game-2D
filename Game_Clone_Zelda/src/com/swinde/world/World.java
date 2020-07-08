package com.swinde.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import com.swinde.entities.Bullet;
import com.swinde.entities.Enemy;
import com.swinde.entities.Entity;
import com.swinde.entities.MedKit;
import com.swinde.entities.Weapon;
import com.swinde.main.Game;



public class World {
	
	public static Tiles[] tiles;
	public static int width, height;
	public static final int TILE_SIZE = 16;
	
	public World(String path){
		try {
			
			BufferedImage map = ImageIO.read(getClass().getResource(path));//carregando o sprite do map;
			int[] pixels = new int[map.getWidth()*map.getHeight()];// criando um array que vai receber os pixels do map;
			tiles = new Tiles[map.getWidth()*map.getHeight()];
			width = map.getWidth();
			height = map.getHeight();
			
			map.getRGB(0, 0, map.getWidth(),map.getHeight() , pixels, 0, map.getWidth());// alocando os pixels do sprite no array
			/*Percorrendo os pixels do map*/
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * width)] = new TileFloor(xx*16,yy*16,Tiles.FLOOR);
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * width)] = new TileFloor(xx*16,yy*16,Tiles.FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//Parede
						tiles[xx + (yy * width)] = new TileWall(xx*16,yy*16,Tiles.WALL);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFFFF0000) {
						//Enemy
						BufferedImage[] buf = new BufferedImage[2];
						buf[0] = Game.spritesheet.getSprite(112,16,16,16);
						buf[1] = Game.spritesheet.getSprite(112+16,16,16,16);
						Enemy en = new Enemy(xx*16,yy*16,16,16,buf);
						Game.entities.add(en);
						Game.enemys.add(en);
						
					}else if(pixelAtual == 0xFFFF6A00) {
						//Weapon
						Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
					}else if(pixelAtual == 0XFFFF7F7F) {
						//Life Pack
						MedKit pack = new MedKit(xx*16,yy*16,16,16,Entity.MEDKIT_EN);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFFFFD800){
						//Bullet
						Game.entities.add(new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN));
					}

				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext, int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext /TILE_SIZE;
		
		
		int x2 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext /TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.width)] instanceof TileWall) ||
				(tiles[x2 + (y2*World.width)] instanceof TileWall) ||
				(tiles[x3 + (y3*World.width)] instanceof TileWall) ||
				(tiles[x4 + (y4*World.width)] instanceof TileWall)); 
	}
	
	public void render(Graphics g){
		
		int xStart = Camera.x >> 4;
		int yStart = Camera.y >> 4;
		
		int xFinal = xStart + (Game.WIDTH >> 4);
		int yFinal = yStart + (Game.HEIGHT >> 4);
				
		for(int xx = xStart; xx <= xFinal; xx++){
			for(int yy = yStart; yy <= yFinal; yy++){
				if(xx < 0 || yy < 0 || xx >= width || yy>= height)
				continue;
				Tiles tile = tiles[xx + (yy*width)];
				tile.render(g);
			}
		}
	}
	
	
}
