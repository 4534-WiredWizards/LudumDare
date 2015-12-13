package org.wiredwizards.ludum.dare.screens;

import java.util.LinkedList;

import org.wiredwizards.ludum.dare.GameObject;
import org.wiredwizards.ludum.dare.LD34Main;
import org.wiredwizards.ludum.dare.objects.Block;
import org.wiredwizards.ludum.dare.objects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {
	
	public static final Texture backgrounds = new Texture("backgrounds.png");
	public static final Texture overlays = new Texture("overlays.png");
	private final SpriteBatch batch = new SpriteBatch();
	public static final LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public static Player player;
	public static OrthographicCamera camera;
	public static int currentLayer = 0;
	public static float blockDelta = 0;

	@Override
	public void show() {
		camera = new OrthographicCamera(TitleScreen.getCamWidth(LD34Main.HEIGHT), LD34Main.HEIGHT);
		player = new Player(0,0,camera);
		LD34Main.gameTime = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/* Update */{
			player.update(delta);
			currentLayer = (int)(LD34Main.gameTime / 10f);
			if (currentLayer > 6) {
				// TODO: Make "You Win" screen.
				Gdx.app.exit();
			}
			blockDelta += delta;
			if (blockDelta > 0.6f) {
				blockDelta = 0;
				for (int i = 0; i < camera.viewportWidth / LD34Main.WIDTH + 1; i++) {
					objects.add(new Block());
				}
			}
		}
		camera.position.y = (int)camera.position.y;
		camera.position.x = (int)camera.position.x;
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/* Render */{
			for (int x = -(int)((camera.viewportWidth / 256f)) - 1; x < camera.viewportWidth / 256f; x++) {
				for (int y = -(int)(camera.viewportHeight / 256f); y < (int)(camera.viewportHeight / 256f) + 1; y++) {
					TextureRegion region = new TextureRegion(backgrounds, 0, currentLayer * 64, 64, 64);
					batch.draw(region, (int)(camera.position.x + x * 256 - camera.position.x % 256), (int)(camera.position.y + y * 256 - (camera.position.y / 2) % 256), 256, 256);
				}
			}
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).render(batch, delta);
			}
			player.render(batch, delta);
			for (int x = -(int)((camera.viewportWidth / 256f)) - 1; x < camera.viewportWidth / 256f; x++) {
				for (int y = -(int)(camera.viewportHeight / 256f); y < (int)(camera.viewportHeight / 256f) + 1; y++) {
					TextureRegion region = new TextureRegion(overlays, 0, currentLayer * 64, 64, 64);
					batch.draw(region, (int)(camera.position.x + x * 256 - camera.position.x % 256), (int)(camera.position.y + y * 256 - (camera.position.y * 1.1f) % 256), 256, 256);
				}
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if ((float) LD34Main.WIDTH / LD34Main.HEIGHT < (float) width / height) {
			camera.viewportHeight = LD34Main.HEIGHT;
			camera.viewportWidth = TitleScreen.getCamWidth(LD34Main.HEIGHT);
		} else {
			camera.viewportHeight = TitleScreen.getCamHeight(LD34Main.WIDTH);
			camera.viewportWidth = LD34Main.WIDTH;
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
