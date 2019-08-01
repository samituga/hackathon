package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

public class Player extends Corpse {

    private boolean dodging;
    private boolean jumping;


    public Player(Body body) {
        super(body);
    }


    @Override
    public PlayerUserData getUserData() {
        return (PlayerUserData) userData;
    }

    public void jump(){
        if(!jumping){
            body.applyLinearImpulse(getUserData().getJumpLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }

    }
    public void moveLeft(){
       body.applyLinearImpulse(Constants.PLAYER_LEFT,Constants.PLAYER_LEFT, true);
    }
    public void moveRight(){
        body.applyLinearImpulse(Constants.PLAYER_RIGHT, Constants.PLAYER_RIGHT, true);
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

    public boolean isDodging() {
        return dodging;
    }

    public Player getPlayer() {
        return this;
    }

}
