package org.wiredwizards.ludum.dare;

import org.wiredwizards.ludum.dare.screens.Splash;
import org.wiredwizards.ludum.dare.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class LD34Main extends Game {
	
	public static final int WIDTH = 480, HEIGHT = 720;
	public static float gameTime = 0;
	
	@Override
	public void create () {
		setScreen(new Splash());
	}

	@Override
	public void render () {
		gameTime += Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			setScreen(new TitleScreen());
		}
		super.render();
	}
	
	public static void setTheScreen(Screen screen) {
		((Game)Gdx.app.getApplicationListener()).setScreen(screen);
	}
}
