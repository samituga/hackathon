package org.academiadecodigo.murlogs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.murlogs.App;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "John Doe";
		config.width = 1024;
		config.height = 576;
		new LwjglApplication(new App(), config);
	}
}
