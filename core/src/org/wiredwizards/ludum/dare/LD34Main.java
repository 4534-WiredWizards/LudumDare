package org.wiredwizards.ludum.dare;

import org.wiredwizards.ludum.dare.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class LD34Main extends Game {
	
	public static final int WIDTH = 720, HEIGHT = 480;
	public static float gameTime = 0;
	
	@Override
	public void create () {
		setScreen(new TitleScreen());
	}

	@Override
	public void render () {
		gameTime += Gdx.graphics.getDeltaTime();
		super.render();
	}
	
	public static void setTheScreen(Screen screen) {
		((Game)Gdx.app.getApplicationListener()).setScreen(screen);
	}
}
