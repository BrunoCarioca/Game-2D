package com.swindie.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	/*Classe que recebe os sprites*/
	private BufferedImage spritesheet;
	
	public Spritesheet(String path){
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Está função retorna uma subimagem do sprite*/
	public BufferedImage getSprite (int x, int y, int width, int height) {
		
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
