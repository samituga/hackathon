package org.academiadecodigo.murlogs.box2d;

import com.badlogic.gdx.math.Vector2;
import org.academiadecodigo.murlogs.enums.UserDataType;
import org.academiadecodigo.murlogs.utils.Constants;

public class PlayerUserData extends UserData {

    private Vector2 jumpLinearImpulse;

    public PlayerUserData() {
        super();
        jumpLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getJumpLinearImpulse() {
        return jumpLinearImpulse;
    }

    public void setJumpLinearImpulse(Vector2 jumpLinearImpulse) {
        this.jumpLinearImpulse = jumpLinearImpulse;
    }
}

