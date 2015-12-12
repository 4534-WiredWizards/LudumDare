package org.wiredwizards.ludum.dare;

import org.wiredwizards.ludum.dare.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class LD34Main extends Game {
	
	@Override
	public void create () {
		setScreen(new TitleScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	public static void setTheScreen(Screen screen) {
		((Game)Gdx.app.getApplicationListener()).setScreen(screen);
	}
}
