package org.academiadecodigo.murlogs;


import com.badlogic.gdx.Game;
import org.academiadecodigo.murlogs.Screens.MainGame;

public class JohnDoe extends Game {

	@Override
	public void create () {
		setScreen(new MainGame());
	}


}
