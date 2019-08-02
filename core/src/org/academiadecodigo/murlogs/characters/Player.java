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
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

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
    private boolean punch;
    private boolean close;
    private boolean isBlocking;
    private int hp = 100;

    public Player(Body body) {
        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.PLAYER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.PLAYER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.PLAYER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }


        TextureAtlas texturesJumpAtlas = new TextureAtlas(Constants.JUMP_ATLAS);
        TextureRegion[] jumpingFrames = new TextureRegion[Constants.PLAYER_JUMPING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_JUMPING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_JUMPING_IMAGE_SET[i];
            jumpingFrames[i] = texturesJumpAtlas.findRegion(path);
        }

        TextureAtlas texturesPunchAtlas = new TextureAtlas(Constants.PUNCH_ATLAS);
        TextureRegion[] punchingFrames = new TextureRegion[Constants.PLAYER_PUNCHING_IMAGE_SET.length];
        for (int i = 0; i < Constants.PLAYER_PUNCHING_IMAGE_SET.length; i++) {
            String path = Constants.PLAYER_PUNCHING_IMAGE_SET[i];
            punchingFrames[i] = texturesPunchAtlas.findRegion(path);
        }
        jumpingAnimation = new Animation(0.2f, jumpingFrames);
        //jumpingTexture = textureAtlas.findRegion(Constants.PLAYER_JUMPING_IMAGE_SET);
        runningAnimation = new Animation(0.2f, runningFrames);
        punchingAnimation = new Animation(0.2f, punchingFrames);
        stateTime += Gdx.graphics.getDeltaTime();
        /*jumpingTexture = textureAtlas.findRegion(Constants.PLAYER_JUMPING_REGION_NAME);
        dodgingTexture = textureAtlas.findRegion(Constants.PLAYER_DODGING_REGION_NAME);
        hitTexture = textureAtlas.findRegion(Constants.PLAYER_HIT_REGION_NAME);*/
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        stateTime += Gdx.graphics.getDeltaTime();

        if (punch) {
            System.out.println("punch");
            batch.draw((TextureRegion) punchingAnimation.getKeyFrame(stateTime,true),(getX() - 1f) * 50, getY() * 15, 128, 256);
        }

        if (jumping) {

            batch.draw((TextureRegion) jumpingAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 15, 128, 256);
            return;
        }

        batch.draw((TextureRegion) runningAnimation.getKeyFrame(stateTime, true), (getX() - 1f) * 50, getY() * 15, 128, 256);

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
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            punch = true;
        }
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

    public void setBlocking(Boolean b){
        isBlocking = b;
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

    public void hitten(){
        System.out.println("hitting");
        hp-=10;  // TODO: 02/08/2019  10
    }
    public boolean isDead() {
        return hp <= 0;
    }
}
