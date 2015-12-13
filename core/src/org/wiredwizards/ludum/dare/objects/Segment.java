package org.wiredwizards.ludum.dare.objects;

import org.wiredwizards.ludum.dare.GameObject;
import org.wiredwizards.ludum.dare.screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Segment extends GameObject {

	public static final Texture sheet = new Texture("stems.png"); // These are
																	// 64 x 32
	public TextureRegion tex = new TextureRegion(sheet);
	public double direction;
	private float time = 0;

	public Segment() {
		super(GameScreen.player.x, GameScreen.player.y, 32, 32);
		direction = GameScreen.player.direction;
	}

	public void render(SpriteBatch batch, float delta) {
		time += delta;
		if (time > 2) {
			GameScreen.objects.remove(this);
		}
		batch.draw(tex, x - tex.getRegionWidth() / 2f, y - tex.getRegionHeight() / 2f, tex.getRegionWidth() / 2, tex.getRegionHeight() / 2, tex.getRegionWidth(), tex.getRegionHeight(), 1, 1,
				(float) Math.toDegrees(direction) - 90f);
	}

}
