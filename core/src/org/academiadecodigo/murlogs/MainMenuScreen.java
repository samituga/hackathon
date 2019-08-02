package org.academiadecodigo.murlogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.murlogs.Screens.MainGame;


public class MainMenuScreen implements Screen {



    Texture image;

    final App game;

    OrthographicCamera camera;


    public MainMenuScreen(final App game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);

        image = new Texture(Gdx.files.internal("provBG.jpg"));

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.font.draw(game.batch, "Welcome to Johny Doe!!! ", 100, 150);
        game.font.draw(game.batch, "Press a key to begin!", 100, 100);
        game.batch.draw(new Texture("play.png"), 50, 388);
        game.batch.draw(new Texture("controls.png"), 50, 238);
        game.batch.draw(new Texture("exit.png"), 50, 88);
        game.batch.end();


        if (Gdx.input.isTouched()) {


            if ((Gdx.input.getX() > 50 && Gdx.input.getX() < 250) && (Gdx.input.getY() > 88 && Gdx.input.getY() < 188)) {
                game.setScreen(new MainGame(game));
                return;
            }
            if ((Gdx.input.getX() > 50 && Gdx.input.getX() < 250) && (Gdx.input.getY() > 238 && Gdx.input.getY() < 338)) {
                game.setScreen(new GameScreen(game));
                return;
            }
            if ((Gdx.input.getX() > 50 && Gdx.input.getX() < 250) && (Gdx.input.getY() > 388 && Gdx.input.getY() < 488)) {
                game.setScreen(new GameScreen(game));
            }
        }
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
      game.dispose();
    }

}
