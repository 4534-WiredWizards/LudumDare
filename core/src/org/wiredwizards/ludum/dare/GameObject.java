package org.wiredwizards.ludum.dare;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

	public float x, y, width, height;

	public GameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(SpriteBatch batch, float delta) {

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
