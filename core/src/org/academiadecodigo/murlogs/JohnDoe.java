package org.academiadecodigo.murlogs;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.murlogs.Screens.GameScreen;

public class JohnDoe extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
	}


}
