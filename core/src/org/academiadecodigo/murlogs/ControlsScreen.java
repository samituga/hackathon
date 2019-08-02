package org.academiadecodigo.murlogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class ControlsScreen implements Screen {
    final App game;
    private Screen mainMenuScreen;
    private Texture image;
    private OrthographicCamera camera;



    public ControlsScreen(final App game, Screen mainMenuScreen) {
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

        if (Gdx.input.isTouched()) {

            if ((Gdx.input.getX() > 80 && Gdx.input.getX() < 230) && (Gdx.input.getY() > 464 && Gdx.input.getY() < 544)) {
                game.setScreen(mainMenuScreen);
            }
        }
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
