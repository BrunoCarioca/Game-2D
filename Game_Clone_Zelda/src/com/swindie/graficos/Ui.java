package com.swindie.graficos;

import java.awt.Color;
import java.awt.Graphics;

import com.swinde.main.Game;

public class Ui {
	
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect(4, 4, 50, 8);
		g.setColor(Color.green);
		g.fillRect(4, 4, (int)((Game.player.life/Game.player.maxLife)*50), 8);
	}
}
