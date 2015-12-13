package org.wiredwizards.ludum.dare.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.wiredwizards.ludum.dare.LD34Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = LD34Main.WIDTH;
		config.height = LD34Main.HEIGHT;
		config.title = "Weeds";
		config.vSyncEnabled = true;
		new LwjglApplication(new LD34Main(), config);
		
		
	}
}
