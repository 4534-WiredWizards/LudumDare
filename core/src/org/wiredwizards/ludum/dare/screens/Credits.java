package org.wiredwizards.ludum.dare.screens;

import org.wiredwizards.ludum.dare.Graphics;
import org.wiredwizards.ludum.dare.LD34Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	public static String credits;

	@Override
	public void show() {
		batch = new SpriteBatch();
		credits = Gdx.files.internal("credits.dat").readString();
		camera = new OrthographicCamera(TitleScreen.getCamWidth(LD34Main.HEIGHT), LD34Main.HEIGHT);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		LD34Main.gameTime = 0;
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0.05f, 0);
		/*Update*/{
			camera.update();
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/*Render*/{
			TitleScreen.font.draw(batch, credits, 0, LD34Main.gameTime * 80);
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
