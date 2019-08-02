package org.academiadecodigo.murlogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.murlogs.Screens.MainGame;


public class MainMenuScreen implements Screen {

    private Texture image;
    final App game;
    private int it = 0;
    private OrthographicCamera camera;


    public MainMenuScreen(final App game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        image = new Texture(Gdx.files.internal("bgMainMenu.png"));
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.batch.end();


        if (Gdx.input.isTouched()) {
            it--;
            if ((Gdx.input.getX() > 80 && Gdx.input.getX() < 230) && (Gdx.input.getY() > 240 && Gdx.input.getY() < 320)) {
                game.setScreen(new MainGame(game, this));
                return;
            }
            if ((Gdx.input.getX() > 80 && Gdx.input.getX() < 230) && (Gdx.input.getY() > 336 && Gdx.input.getY() < 416)) {
                it = 12;
                game.setScreen(new ControlsScreen(game, this));
                return;
            }
            if ((Gdx.input.getX() > 80 && Gdx.input.getX() < 230) && (Gdx.input.getY() > 444 && Gdx.input.getY() < 522) && it <= 0) {
                System.exit(0);
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
