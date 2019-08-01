package org.academiadecodigo.murlogs.stages;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.academiadecodigo.murlogs.characters.Ground;
import org.academiadecodigo.murlogs.characters.Player;
import org.academiadecodigo.murlogs.utils.BodyUtils;
import org.academiadecodigo.murlogs.utils.WorldUtils;

public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World world;
    private Ground ground;
    private Player player;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Vector3 touchPoint;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    public GameStage() {
        setupWorld();
        renderer = new Box2DDebugRenderer();
        setupCamera();
    }

    private void setupWorld() {
        world = WorldUtils.createWorld();

        setUpGround();
        setUpPlayer();
        //setupTouchControlAreas();
    }

    private void setUpPlayer() {
        player = new Player(WorldUtils.createPlayer(world));
        addActor(player);
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }



    @Override
    public void act(float delta) {


        super.act(delta);

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    public void moveRight(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.jump();

        }
    }


}
