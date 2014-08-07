package com.zolarmc.hanoitowers;

import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.math.Rectangle;

public class HanoiTower extends Rectangle {

	private Stack<HanoiDisc> stack;
	public float xPadding;
	public float yPadding;
	
	public Rectangle selection;
	private String label;

	public HanoiTower(String name) {
		stack = new Stack<HanoiDisc>();
		label = name;
	}

	public HanoiDisc peek() {
		if (stack == null)
			return null;
		else if(stack.size() == 0)
			return null;
		else
			return stack.peek();
	}

	public void moveFrom(HanoiTower originTower) {
		if (stack == null)
			return;
		if (originTower != null && originTower.peek() != null) {
			if (this.getSize() > 0 && this.peek() != null ){
				if(this.peek().value > originTower.peek().value){
					
					this.push(originTower.pop());
				}
			} else { 
				this.push(originTower.pop());
			}
		} 
	}

	public HanoiDisc push(HanoiDisc disc) {
		if (stack == null)
			return null;

		disc.x = this.x - (disc.width / 2) + this.width / 2;
		disc.y = this.y + (this.getSize() * (disc.height + disc.yPadding));
		
		return stack.push(disc);
	}

	public HanoiDisc pop() {
		if (stack == null)
			return null;
		else if (stack.size() == 0)
			return null;
		else
			return stack.pop();
	}

	public int getSize() {
		return stack.size();
	}

	public Iterator<HanoiDisc> getIterator() {
		return stack.iterator();
	}
	
	public String toString() { 
		return label;
	}

}
