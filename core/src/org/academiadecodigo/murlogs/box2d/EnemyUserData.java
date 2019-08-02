package org.academiadecodigo.murlogs.box2d;

import com.badlogic.gdx.math.Vector2;
import org.academiadecodigo.murlogs.characters.Enemy;
import org.academiadecodigo.murlogs.enums.UserDataType;
import org.academiadecodigo.murlogs.utils.Constants;

public class EnemyUserData extends UserData{

    private final Vector2 runningPosition = new Vector2(Constants.ENEMY_X, Constants.ENEMY_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.ENEMY_DODGE_X, Constants.ENEMY_DODGE_Y);

    public EnemyUserData() {
        super();
        userDataType = UserDataType.ENEMY;
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
