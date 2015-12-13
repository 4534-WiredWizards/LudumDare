package org.wiredwizards.ludum.dare.objects;

import java.util.Random;

import org.wiredwizards.ludum.dare.GameObject;
import org.wiredwizards.ludum.dare.screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block extends GameObject {

	public static final Texture sheet = new Texture("spritesheet.png");
	private TextureRegion tex;
	public static final Random rand = new Random();
	private float time = 0;

	public Block() {
		super(rand.nextInt((int)(GameScreen.camera.viewportWidth / 128f)) * 128, GameScreen.camera.position.y + GameScreen.camera.viewportHeight, 128, 128);
		tex = new TextureRegion(sheet, 32 * rand.nextInt(4), 32 * GameScreen.currentLayer, 32, 32);
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		time += delta;
		if (time > 10) {
			GameScreen.objects.remove(this);
		}
		batch.draw(tex, x, y, 128, 128);
	}

}
