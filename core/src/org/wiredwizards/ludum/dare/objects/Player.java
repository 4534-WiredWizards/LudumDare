package org.wiredwizards.ludum.dare.objects;

import org.wiredwizards.ludum.dare.GameObject;
import org.wiredwizards.ludum.dare.Input;
import org.wiredwizards.ludum.dare.LD34Main;
import org.wiredwizards.ludum.dare.screens.GameScreen;
import org.wiredwizards.ludum.dare.screens.TitleScreen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {

	public static final Texture flower = new Texture("flower.png");
	public double direction = 0;
	public float speed;
	public static float vSpeed = 128;
	private Camera camera;
	private float segmentDelta = 0;

	public Player(float x, float y, Camera camera) {
		super(x, y, 52, 52);
		this.camera = camera;
	}

	public void update(float delta) {
		vSpeed = 600 + LD34Main.gameTime * 0.5f;
		speed = (float) Math.sqrt(Math.pow(vSpeed, 2) + Math.pow(x - Input.getX(camera), 2));
		direction = Math.atan2(128, Input.getX(camera) - x);
		segmentDelta += speed * delta;
		if (segmentDelta > 32) {
			segmentDelta -= 32;
			GameScreen.objects.add(new Segment());
		}
		x += (float) (Math.cos(direction) * speed * delta);
		y += vSpeed * delta;// (float) (Math.sin(direction) * speed * delta);
		// camera.position.x = x;
		camera.position.y = y + LD34Main.HEIGHT / 4f;
		for (int i = 0; i < GameScreen.objects.size(); i++) {
			GameObject o = GameScreen.objects.get(i);
			if (!(o instanceof Segment)) {
				if (o.getBounds().overlaps(getBounds())) {
					y = 0;
					LD34Main.gameTime = (int)(LD34Main.gameTime / 10f) * 10;
					GameScreen.objects.clear();
				}
			}
		}
	}

	public void render(SpriteBatch batch, float delta) {
		TextureRegion tex = new TextureRegion(flower);
		batch.draw(tex, x - tex.getRegionWidth() / 2f, y - tex.getRegionHeight() / 2f, tex.getRegionWidth() / 2f, tex.getRegionHeight() / 2f,
				tex.getRegionWidth(), tex.getRegionHeight(), height / tex.getRegionHeight(), height / tex.getRegionHeight(),
				(float) Math.toDegrees(direction) - 90f);
		TitleScreen.font.draw(batch, String.valueOf((int)(LD34Main.gameTime / 10f)), -camera.viewportWidth / 2f, camera.position.y + camera.viewportHeight / 2f);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x - 32 / 2f, y - 32 / 2f, 32, 32);
	}

}
