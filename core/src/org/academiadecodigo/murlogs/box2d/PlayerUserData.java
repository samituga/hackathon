package org.academiadecodigo.murlogs.box2d;

import com.badlogic.gdx.math.Vector2;
import org.academiadecodigo.murlogs.enums.UserDataType;
import org.academiadecodigo.murlogs.utils.Constants;

public class PlayerUserData extends UserData {

    private final Vector2 runningPosition = new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.PLAYER_DODGE_X, Constants.PLAYER_DODGE_Y);
    private Vector2 jumpLinearImpulse;

    public PlayerUserData() {
        super();
        jumpLinearImpulse = Constants.PLAYER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getJumpLinearImpulse() {
        return jumpLinearImpulse;
    }

    public void setJumpLinearImpulse(Vector2 jumpLinearImpulse) {
        this.jumpLinearImpulse = jumpLinearImpulse;
    }
    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }

}

