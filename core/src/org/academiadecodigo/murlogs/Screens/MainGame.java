package org.academiadecodigo.murlogs.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.murlogs.App;
import org.academiadecodigo.murlogs.characters.Player;
import org.academiadecodigo.murlogs.stages.GameStage;
import org.academiadecodigo.murlogs.utils.Constants;

public class MainGame implements Screen {

    private GameStage stage;
    private App app;
    private Player player;
    private boolean crouch;
    private final Texture background = new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH));
    private Texture img;


    private OrthographicCamera camera;

    public MainGame(App app) {
        stage = new GameStage();
        this.app = app;
        player = stage.getPlayer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        img = new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        app.batch.setProjectionMatrix(camera.combined);
        app.batch.begin();
        app.batch.draw(img, 0, 0);
        app.batch.end();
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.dodge();
            crouch = true;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.DOWN) && crouch) {
            player.stopDodge();
            crouch = false;
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
        app.dispose();

    }

}
