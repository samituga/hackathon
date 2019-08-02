package org.academiadecodigo.murlogs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.murlogs.App;


public class EndScreen implements Screen {

    final App game;
    private Screen mainMenuScreen;
    private Texture image;
    private OrthographicCamera camera;


    public EndScreen(final App game, Screen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        image = new Texture(Gdx.files.internal("bgControls.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the player and others
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.batch.end();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.setScreen(mainMenuScreen);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}

