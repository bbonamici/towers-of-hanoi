package com.zolarmc.hanoitowers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GestureProcessor implements GestureListener, InputProcessor {

	private boolean panning = false;
	private float panningX, panningY;
	private float panningEX, panningEY;
	
	private HanoiTowers hanoiTowers;
	
	public GestureProcessor(HanoiTowers hanoiTowers) {
		this.hanoiTowers = hanoiTowers;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(!panning) { 
			panningX = x;
			panningY = y;		 
			panning = true;
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		hanoiTowers.pan(panningX, panningY, x, y);
		panning = false;
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.A) {
			
			if (hanoiTowers.first == null) {
				hanoiTowers.first = hanoiTowers.leftTower;
			} else {
				if (hanoiTowers.leftTower != hanoiTowers.first) {
					hanoiTowers.leftTower.moveFrom(hanoiTowers.first);
					
				}
				hanoiTowers.first = null;
			}
		} else if (keycode == Keys.S) {
			if (hanoiTowers.first == null) {
				hanoiTowers.first = hanoiTowers.centerTower;
			} else {
				if (hanoiTowers.centerTower != hanoiTowers.first) {
					hanoiTowers.centerTower.moveFrom(hanoiTowers.first);
					
				}
				hanoiTowers.first = null;
			
			}
		} else if (keycode == Keys.D) {
			if (hanoiTowers.first == null) {
				hanoiTowers.first = hanoiTowers.rightTower;
			} else {
				if (hanoiTowers.rightTower != hanoiTowers.first) {
					hanoiTowers.rightTower.moveFrom(hanoiTowers.first);
					
				}
				hanoiTowers.first = null;
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
