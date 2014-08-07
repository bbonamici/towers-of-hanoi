package com.zolarmc.hanoitowers;

public class HanoiPlayer implements Runnable {

	HanoiTowers game;
	HanoiTower l;
	HanoiTower c;
	HanoiTower r;
	
	long count;

	public HanoiPlayer(HanoiTowers gameRef) {
		game = gameRef;
		l = game.leftTower;
		c = game.centerTower;
		r = game.rightTower;
		
		Thread t = new Thread(this);
		t.start();
		count = 0;
	}

	@Override
	public void run() {
		move(l.getSize(), l, r, c);
		System.out.println("Total calls:" + count);
	}

	private void move(int n, HanoiTower ori, HanoiTower des, HanoiTower aux) {
		count++;
		if(n == 1) { 
			delay();
			des.moveFrom(ori);
		} else {
			//move all N-1 to aux.
			move(n-1, ori, aux, des);
			//move N disc
			delay();
			des.moveFrom(ori);
			//move all N-1 to des
			move(n-1, aux, des, ori);			
		}
	}

	private void delay() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
