package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.UserData;

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

    public void landed() {
        jumping = false;
    }

    public void dodge() {
        if (!jumping) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean isDodging() {
        return dodging;
    }

}
