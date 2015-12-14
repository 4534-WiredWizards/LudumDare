package org.wiredwizards.ludum.dare.screens;

import org.wiredwizards.ludum.dare.Graphics;
import org.wiredwizards.ludum.dare.LD34Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	public static String credits;
	public static final Texture tex = new Texture("splash.png");
	public float time = 0;

	@Override
	public void show() {
		batch = new SpriteBatch();
		credits = Gdx.files.internal("credits.dat").readString();
		camera = new OrthographicCamera(TitleScreen.getCamWidth(LD34Main.HEIGHT), LD34Main.HEIGHT);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		LD34Main.gameTime = 0;
		time = 0;
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0f, 0);
		/*Update*/{
			camera.update();
			time += delta;
			if (time > 5 || Gdx.input.justTouched()) {
				LD34Main.setTheScreen(new TitleScreen());
			}
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/*Render*/{
			batch.draw(tex, -LD34Main.WIDTH / 2f, -(((float)LD34Main.WIDTH / tex.getWidth()) * tex.getHeight()) / 2f, LD34Main.WIDTH, ((float)LD34Main.WIDTH / tex.getWidth()) * tex.getHeight());
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
