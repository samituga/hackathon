package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import org.academiadecodigo.murlogs.Screens.MainGame;
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

import java.sql.Array;
import java.util.Arrays;

public class Player extends Corpse {

    private boolean dodging;
    private boolean jumping;
    private Animation runningAnimation;
    private TextureRegion jumpingTexture;
    private TextureRegion dodgingTexture;
    private TextureRegion hitTexture;
    private float stateTime;
    private int leftIt = 0;
    private int rightIt = 0;
    private Animation jumpingAnimation;
    private Animation punchingAnimation;
    private Animation crouchingAnimation;
    private Animation blockingAnimation;
    private boolean punch;
    private boolean close;
    private boolean isBlocking;
    private int hp = 100;
    private MainGame mainGame;

    public Player(Body body) {
        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.PLAYER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.PLAYER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.PLAYER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }


        TextureAtlas texturesJumpAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] jumpingFrames = new TextureRegion[Constants.PLAYER_JUMPING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_JUMPING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_JUMPING_IMAGE_SET[i];
            jumpingFrames[i] = texturesJumpAtlas.findRegion(path);
        }

        TextureAtlas texturesPunchAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] punchingFrames = new TextureRegion[Constants.PLAYER_PUNCHING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_PUNCHING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_PUNCHING_IMAGE_SET[i];
            punchingFrames[i] = texturesPunchAtlas.findRegion(path);
        }

        TextureRegion[] crouchingFrames = new TextureRegion[Constants.PLAYER_CROUCHING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_CROUCHING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_CROUCHING_IMAGE_SET[i];
            crouchingFrames[i] = texturesPunchAtlas.findRegion(path);
        }

        TextureAtlas textureBlockingAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_BLOCK_PATH);
        TextureRegion[] blockingFrames = new TextureRegion[Constants.PLAYER_BLOCKING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_BLOCKING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_BLOCKING_IMAGE_SET[i];
            blockingFrames[i] = textureBlockingAtlas.findRegion(path);
        }


        jumpingAnimation = new Animation(0.2f, jumpingFrames);
        runningAnimation = new Animation(0.2f, runningFrames);
        punchingAnimation = new Animation(0.2f, punchingFrames);
        crouchingAnimation = new Animation(0.2f, crouchingFrames);
        blockingAnimation = new Animation(0.2f, blockingFrames);
        stateTime += Gdx.graphics.getDeltaTime();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        stateTime += Gdx.graphics.getDeltaTime();

        if (dodging) {
            int width = (int) (128 / 1.4);
            int height = (int) (256 / 1.4);
            batch.draw((TextureRegion) crouchingAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 12, width, height);
            return;
        }

        if (isBlocking) {
            batch.draw((TextureRegion) blockingAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 12, 128, 256);
            return;
        }


        if (jumping) {
            batch.draw((TextureRegion) jumpingAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 12, 128, 256);
            return;
        }

        if (punch) {
            batch.draw((TextureRegion) punchingAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 12, 128, 256);
            punch = false;
            return;

        }

        batch.draw((TextureRegion) runningAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 12, 128, 256);

    }


    @Override
    public float getX() {
        return body.getPosition().x;
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public PlayerUserData getUserData() {
        return (PlayerUserData) userData;
    }

    public void jump() {
        if (!jumping) {
            body.applyLinearImpulse(getUserData().getJumpLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }

    }

    public void moveLeft() {
        leftIt++;
        if (leftIt >= 1) {
            body.applyLinearImpulse(Constants.PLAYER_LEFT, Constants.PLAYER_LEFT, true);
            leftIt = 0;
        }
    }

    public void punch() {
        punch = true;
    }

    public void moveRight() {
        rightIt++;
        if (rightIt >= 1) {
            body.applyLinearImpulse(Constants.PLAYER_RIGHT, Constants.PLAYER_RIGHT, true);
            rightIt = 0;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void dodge() {
        if (!jumping) {
            body.setTransform(new Vector2(body.getPosition()), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        body.setTransform(body.getPosition(), 0f);
    }

    public void setBlocking(Boolean b) {
        isBlocking = b;
    }

    public void setDodging(boolean dodging) {
        this.dodging = dodging;
    }

    public boolean isDodging() {
        return dodging;
    }

    public Player getPlayer() {
        return this;
    }

    public void setClose(boolean b) {
        close = b;
    }

    public boolean isClose() {
        return close;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void hitten() {
        hp -=5;  // TODO: 02/08/2019  10
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public int getHp() {
        return hp;
    }
}
