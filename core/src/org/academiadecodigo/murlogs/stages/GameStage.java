package org.academiadecodigo.murlogs.stages;

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

public class GameStage extends Stage implements Input {

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
        world.setContactListener(this);
        setUpGround();
        setUpPlayer();
        setupTouchControlAreas();
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

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
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

    private boolean rightSideTouched(float x, float y) {
        System.out.println("FLOAT");
        System.out.println(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY));
        return Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPlayer(b))) {
            player.landed();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        translateScreenToWorldCoordinates(screenX, screenY);
        System.out.println("here");
        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            System.out.println("jumping");
            player.jump();
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
            System.out.println("dodging");
            player.dodge();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (player.isDodging()) {
            player.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
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
}
