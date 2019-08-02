package org.academiadecodigo.murlogs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.murlogs.App;
import org.academiadecodigo.murlogs.characters.Enemy;
import org.academiadecodigo.murlogs.characters.Player;
import org.academiadecodigo.murlogs.stages.GameStage;
import org.academiadecodigo.murlogs.utils.Constants;

public class MainGame implements Screen {

    private GameStage stage;
    private App app;
    private Player player;
    private Enemy enemy;
    private Texture img;
    private Texture enemyDeath;
    private Texture playerdeath;
    private Screen mainMenuScreen;


    private OrthographicCamera camera;

    public MainGame(App app, Screen mainMenuScreen) {
        stage = new GameStage();
        this.app = app;
        this.mainMenuScreen = mainMenuScreen;
        player = stage.getPlayer();
        enemy = stage.getEnemy();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        img = new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH));
        player.setMainGame(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        checkEndGame();
        camera.update();
        app.batch.setProjectionMatrix(camera.combined);
        app.batch.begin();
        app.batch.draw(img, 0, 0);
        app.batch.end();

        stage.draw();
        stage.act(delta);

        stage.getEnemy().enemyMove();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
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
            player.setDodging(true);
            player.setBlocking(false);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.isDodging()) {
            player.stopDodge();
            player.setDodging(false);
            player.setBlocking(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.setDodging(false);
            player.setBlocking(true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X) && !player.isBlocking() && !player.isDodging()) {
            player.punch();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X) && !player.isBlocking() && enemy.isClose()) {
            System.out.println("Player punching");
            enemy.hitten();
            player.punch();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.setBlocking(true);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.K)) {
            player.setBlocking(false);
        }

        if (enemy.isAttack() && !player.isBlocking() && enemy.isClose()) {
            player.hitten();
        }

    }


    private void checkEndGame() {

        if (enemy.isDead()) { // TODO: 02/08/2019
            enemyDeath = new Texture(Gdx.files.internal(Constants.PLAYER_WIN_IMAGE_PATH));
            finish(enemyDeath);

        }
        if (player.isDead()) { // TODO: 02/08/2019 player
            playerdeath = new Texture(Gdx.files.internal(Constants.PLAYER_LOSE_IMAGE_PATH));
            finish(playerdeath);
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

    private void finish(Texture texture) {


        app.setScreen(new EndScreen(app, mainMenuScreen, texture));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
