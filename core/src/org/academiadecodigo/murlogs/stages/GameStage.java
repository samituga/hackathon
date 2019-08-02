

package org.academiadecodigo.murlogs.stages;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.academiadecodigo.murlogs.characters.Enemy;
import org.academiadecodigo.murlogs.characters.Ground;
import org.academiadecodigo.murlogs.characters.Player;
import org.academiadecodigo.murlogs.characters.SideLimit;
import org.academiadecodigo.murlogs.utils.BodyUtils;
import org.academiadecodigo.murlogs.utils.Constants;
import org.academiadecodigo.murlogs.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    //private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    //private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Player player;
    private Enemy enemy;
    private SideLimit leftSideLimit;
    private SideLimit rightSideLimit;

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
        world.setContactListener(this);
        setUpGround();
        setUpPlayer();
        setUpLeftSideLimit();
        setUpRightSideLimit();
        setUpEnemy();

        //setupTouchControlAreas();
    }

    private void setUpPlayer() {
        player = new Player(WorldUtils.createPlayer(world));
        addActor(player);

    }

    private void setUpEnemy() {
        enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);

    }

    private void setUpLeftSideLimit() {
        leftSideLimit = new SideLimit(WorldUtils.createLeftSideLimit(world));
        addActor(leftSideLimit);

    }

    private void setUpRightSideLimit() {
        rightSideLimit = new SideLimit(WorldUtils.createRightSideLimit(world));
        addActor(rightSideLimit);
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

    public Player getPlayer() {
        return player;
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();


        if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPlayer(b))) {

            player.landed();

        }
        if ((BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsPlayer(b)) ||
                BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsEnemy(b)) {
            System.out.println("enemy is close");
            player.setClose(true);
            enemy.setClose(true);
            return;
        }

        player.setClose(false);
        enemy.setClose(false);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public Enemy getEnemy() {
        return enemy;
    }
}

