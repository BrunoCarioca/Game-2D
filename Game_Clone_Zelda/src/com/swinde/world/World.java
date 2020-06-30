package com.swinde.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	private Tiles[] tiles;
	private int width, height;
	
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
					if(pixels[xx + (yy*map.getWidth())] == 0xFF000000){//se o pixel for da cor preta;
						/*Floor*/
						tiles[xx + (yy*map.getWidth())] = new TileFloor(xx*16, yy*16, Tiles.FLOOR);
					}else if (pixels[xx+(yy*map.getWidth())] == 0xFFFFFFFF){//se o pixel for da cor branca
						/*Wall*/
						tiles[xx + (yy*map.getWidth())] = new TileWall(xx*16, yy*16, Tiles.WALL);
					}else{
						tiles[xx + (yy*map.getWidth())] = new TileFloor(xx*16, yy*16, Tiles.FLOOR);
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g){
		for(int xx = 0; xx < width; xx++){
			for(int yy = 0; yy < height; yy++){
				Tiles tile = tiles[xx + (yy*width)];
				g.drawImage(tile.getSprite(),tile.getX(), tile.getY(),null);
			}
		}
	}
	
	
}
