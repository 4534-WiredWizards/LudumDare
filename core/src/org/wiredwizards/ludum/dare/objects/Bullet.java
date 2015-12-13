package org.wiredwizards.ludum.dare.objects;

import org.wiredwizards.ludum.dare.GameObject;
import org.wiredwizards.ludum.dare.screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bullet extends GameObject {

	public static final Texture seed = new Texture("seed.png");
	public double direction;
	public static float speed = 2000;
	private float time = 0;

	public Bullet() {
		super(GameScreen.player.x, GameScreen.player.y, 32, 32);
		direction = GameScreen.player.direction;
		time = 0;
	}

	public void render(SpriteBatch batch, float delta) {
		x += Math.cos(direction) * speed * delta;
		y += speed * delta;
		time += delta;
		if (time > 3) {
			GameScreen.objects.remove(this);
		}
		TextureRegion region = new TextureRegion(seed);
		batch.draw(region, x - region.getRegionWidth() / 2f, y - region.getRegionHeight() / 2f, region.getRegionWidth() / 2f, region.getRegionHeight() / 2f,
				region.getRegionWidth(), region.getRegionHeight(), 1, 1, (float) Math.toDegrees(direction - (90f - 45f / 2f)));
	}

}
