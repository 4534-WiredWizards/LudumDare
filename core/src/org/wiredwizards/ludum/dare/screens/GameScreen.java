package org.wiredwizards.ludum.dare.screens;

import java.util.LinkedList;

import org.wiredwizards.ludum.dare.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	private final SpriteBatch batch = new SpriteBatch();
	public static final LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		{
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).render(batch, delta);
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
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
