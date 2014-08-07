package com.zolarmc.hanoitowers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class HanoiTowers extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;

	Rectangle screen;

	List<HanoiTower> towerList;
	List<HanoiDisc> discList;

	HanoiTower leftTower;
	HanoiTower centerTower;
	HanoiTower rightTower;

	HanoiTower first, second;

	List<Rectangle> selectionList;

	Vector3 touchPos = new Vector3();

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();

		screen = new Rectangle(0, 0, 800, 480);

		camera.setToOrtho(false, screen.width, screen.height);
		shapeRenderer.setProjectionMatrix(camera.combined);

		createTowers();
		createDisks(10, leftTower);
		
		Gdx.input.setInputProcessor(new GestureProcessor(this));
		
		HanoiPlayer player = new HanoiPlayer(this);
	}

	private void createDisks(int number, HanoiTower tower) {
		float height = screen.height * 2 / 100;
		float width = tower.width * 1.40f;
		float yPadding = height / 2;

		discList = new ArrayList<HanoiDisc>();

		for (int a = number; a != 0; a--) {
			HanoiDisc disc = new HanoiDisc(a);

			float discWidth = width * disc.value;

			disc.height = height;
			disc.width = discWidth;
			disc.yPadding = yPadding;

			discList.add(disc);
			tower.push(disc);
		}
	}

	private void createTowers() {

		float spacing = screen.getWidth() / 3; // space between towers.
		
		float height = screen.getHeight() * 80 / 100;
		float width = screen.getWidth() * 1 / 100;
		
		float xpadding = spacing / 2 - width/2;
		float ypadding = screen.getHeight() * 10 / 100;

		leftTower = new HanoiTower("left tower");
		centerTower = new HanoiTower("center tower");
		rightTower = new HanoiTower("right tower");

		leftTower.selection = new Rectangle(0, 0, screen.width / 3,
				screen.height);
		centerTower.selection = new Rectangle(screen.width / 3, 0,
				screen.width / 3, screen.height);
		rightTower.selection = new Rectangle(screen.width / 3 * 2, 0,
				screen.width / 3, screen.height);

		towerList = new ArrayList<HanoiTower>();
		towerList.add(leftTower);
		towerList.add(centerTower);
		towerList.add(rightTower);

		int towerIndex = 0;
		for (HanoiTower tower : towerList) {
			tower.x = (spacing * towerIndex) + xpadding;
			tower.y = ypadding;
			tower.width = width;
			tower.height = height;
			towerIndex++;
		}
	}

	private void renderTowers() {

		shapeRenderer.begin(ShapeType.Line);
		for (HanoiTower tower : towerList) {
			shapeRenderer.setColor(255, 0, 0, 1);
			shapeRenderer.rect(tower.selection.x, tower.selection.y,
					tower.selection.width, tower.selection.height);
		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		for (HanoiTower tower : towerList) {
			if(tower == first)
				shapeRenderer.setColor(255, 255, 255, 1);
			else
				shapeRenderer.setColor(255, 0, 0, 1);
			shapeRenderer.rect(tower.x, tower.y, tower.width, tower.height);
			renderDisks(tower);
		}
		shapeRenderer.end();
	}

	private void renderDisks(HanoiTower tower) {

		for (HanoiDisc disc : discList) {
			shapeRenderer.setColor(100, 10, 100, 1);
			shapeRenderer.rect(disc.x, disc.y, disc.width, disc.height);
		}

	}

	@Override
	public void render() {
		camera.update();

		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.rect(0, 0, screen.width, screen.height);

		shapeRenderer.end();

		renderTowers();
	}

	public void pan(float x, float y, float ex, float ey) {

		for (HanoiTower ht : towerList) {
			if (ht.selection.contains(x, y)) {
				for (HanoiTower ht2 : towerList) {
					if (ht != ht2 && ht2.selection.contains(ex, ey)) {
						ht2.moveFrom(ht);
						// System.out.println(ht + " > " + ht2);
						return;
					}
				}
			}
		}
	}
}
