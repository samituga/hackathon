package org.academiadecodigo.murlogs.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.murlogs.box2d.GroundUserData;
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.PunchUserData;
import org.academiadecodigo.murlogs.box2d.SideLimitUserData;
import org.academiadecodigo.murlogs.characters.Player;

public class WorldUtils {


    private  Player player;

    public static World createWorld(){
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X ,Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createLeftSideLimit(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.LEFT_LIMIT_X, Constants.LEFT_LIMIT_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.LEFT_LIMIT_WIDTH,Constants.LEFT_LIMIT_HEIGHT);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new SideLimitUserData());
        shape.dispose();
        return body;
    }

    public static Body createRightSideLimit(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.RIGHT_LIMIT_X, Constants.RIGHT_LIMIT_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.RIGHT_LIMIT_WIDTH,Constants.RIGHT_LIMIT_HEIGHT);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new SideLimitUserData());
        shape.dispose();
        return body;
    }

    public static Body createPlayer(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.PLAYER_WIDTH / 2, Constants.PLAYER_HEIGHT / 2);
        CircleShape headShape = new CircleShape();
        headShape.setPosition(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y+2f));
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.PLAYER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.PLAYER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlayerUserData());
        shape.dispose();
        return body;
    }

    public static Body createPunch(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.PLAYER_WIDTH / 5, Constants.PLAYER_HEIGHT / 6);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.PUNCH_DENSITY);
        body.resetMassData();
        body.setUserData(new PunchUserData());
        body.setGravityScale(0f);
        shape.dispose();
        body.setFixedRotation(true);
        return body;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
