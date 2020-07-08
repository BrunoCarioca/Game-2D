package com.swinde.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.swinde.entities.Enemy;
import com.swinde.entities.Entity;
import com.swinde.entities.Player;
import com.swinde.world.World;
import com.swindie.graficos.Spritesheet;
import com.swindie.graficos.Ui;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	/*Dimensões do mapa*/
	public static final int WIDTH  = 240; 
	public static final int HEIGHT = 160;
	private final int SCALE	 = 3;
	/*Janela*/
	private JFrame windown; 
	/*loopGame*/
	private Thread 	thread;
	private boolean	isRunning;
	/*Desenhando na tela*/
	public static BufferedImage image;
	/*Entidades*/
	public static Player player; 
	public static List<Entity> entities;
	public static List<Enemy> enemys;
	/*Sprites*/
	public static Spritesheet spritesheet;
	/*Mapa do jogo*/
	public static World world;
	/*Variavel que gera nuúmeros randomicos*/
	public static Random rand;
	/*Variáveis de usuário interface*/
	public Ui ui;
	
	public Game() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension (WIDTH*SCALE, HEIGHT*SCALE));
		montarJanela();
		rand = new Random();
		/*Inicializando objetos*/
		ui = new Ui();
		spritesheet	= new Spritesheet("/spritesheet.png");
		image 		= new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities 	= new ArrayList<Entity>();
		enemys		= new ArrayList<Enemy>();
		/*Player*/
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		world = new World("/level1.png");
	}
	
	public static void main (String[] args){
		
		Game game = new Game();
		game.start();	
	}
	
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true; 
		thread.start();
	} 
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
	};
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		world.render(g);
		
		/*Renderizar cada Entidade*/
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		ui.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
		g.setFont(new Font("arial", Font.BOLD ,20));
		g.setColor(Color.white);
		g.drawString("Munição: "+player.ammo, 600, 25);
		bs.show();
	};
	
	
	public void run() {
			//System.out.println("Meu jogo está rodando");
			/*controlando o loop*/
			long lastTime = System.nanoTime(); 				//Pega o tempo atual em nano segundos.
			double amountOfTicks = 60.0;					//Quantidade de frames que eu quero por segundo. 
			double whenUpdate = 1000000000/ amountOfTicks;	//Calcula o momento eu que eu devo atualizo um frame.
			double delta = 0;								//Controlador: sabe o momento em que deve ser atualzado.
			
			while(isRunning) {	
				requestFocus();
				long now = System.nanoTime();
				delta += (now - lastTime)/whenUpdate;
				lastTime = now;
				if(delta >= 1) {
					update();
					render();
					delta--;
				}
		
				
		}
		stop();
	}
	
	private void montarJanela(){		
		
		windown = new JFrame("Game #1");
		windown.add(this);
		windown.setResizable(false);
		windown.pack();
		windown.setLocationRelativeTo(null);
		windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windown.setVisible(true);

		
	}

	/*Eventos de teclado*/
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.setUp(true);
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.setUp(false);
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}