package org.wiredwizards.ludum.dare.screens;

import org.wiredwizards.ludum.dare.Graphics;
import org.wiredwizards.ludum.dare.Input;
import org.wiredwizards.ludum.dare.LD34Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class TitleScreen implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	public static final Texture tex = new Texture("title.png");
	public static BitmapFont font;
	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(getCamWidth(LD34Main.HEIGHT), LD34Main.HEIGHT);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		generator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans-Bold.ttf"));
		parameter = new FreeTypeFontParameter();
		if (!LD34Main.titleMusic.isPlaying()) {
			LD34Main.titleMusic.play();
			LD34Main.gameMusic.stop();
		}
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
			batch.draw(tex, -(tex.getWidth() * 2) / 2 + Input.getX(camera) / 30f, LD34Main.HEIGHT / 2 - tex.getHeight() * 2 + Input.getY(camera) / 30f,
					tex.getWidth() * 2, tex.getHeight() * 2);

			if (font == null) {
				parameter.size = 40 * Gdx.graphics.getHeight() / LD34Main.HEIGHT;
				font = generator.generateFont(parameter);
				font.getData().setScale(camera.viewportHeight / Gdx.graphics.getHeight());
			}
			GlyphLayout layout = new GlyphLayout();
			// Play
			String play = "Play";
			layout.setText(font, play);
			font.draw(batch, play, -layout.width / 2, 0);
			if (Input.touchGlyph(layout, new Vector2(-layout.width / 2, 0), camera) == 3) {
				LD34Main.setTheScreen(new GameScreen());
			}
			// Credits
			String credits = "Credits";
			layout.setText(font, credits);
			font.draw(batch, credits, -layout.width / 2, -LD34Main.HEIGHT / 10 * 3);
			if (Input.touchGlyph(layout, new Vector2(-layout.width / 2, -LD34Main.HEIGHT / 10 * 3), camera) == 3) {
				LD34Main.setTheScreen(new Credits());
			}
		}
		batch.end();
	}

	/**
	 * Gets the width of a camera with specified height
	 * 
	 * @param camHeight
	 *            The height of the camera.
	 * @return The width of the camera.
	 */
	public static int getCamWidth(int camHeight) {
		// cam.height / cam.width = width / height
		// cam.width = (width * cam.height) / height
		int camWidth = (Gdx.graphics.getWidth() * camHeight) / Gdx.graphics.getHeight();
		return camWidth;
	}

	/**
	 * Gets the height of a camera with specified height
	 * 
	 * @param camWidth
	 *            The width of the camera.
	 * @return The height of the camera.
	 */
	public static int getCamHeight(int camWidth) {
		// cam.height / cam.width = width / height
		// cam.width = (width * cam.height) / height
		int camHeight = (Gdx.graphics.getHeight() * camWidth) / Gdx.graphics.getWidth();
		return camHeight;
	}

	@Override
	public void resize(int width, int height) {
		if ((float) LD34Main.WIDTH / LD34Main.HEIGHT < (float) width / height) {
			camera.viewportHeight = LD34Main.HEIGHT;
			camera.viewportWidth = getCamWidth(LD34Main.HEIGHT);
		} else {
			camera.viewportHeight = getCamHeight(LD34Main.WIDTH);
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
		batch.dispose();
	}

}
