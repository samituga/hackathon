package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.EnemyUserData;
import org.academiadecodigo.murlogs.box2d.PlayerUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

public class Enemy extends Corpse {


    protected boolean isClose;
    protected UserData userData;
    private int iterators;

    public Enemy(Body body) {
        super(body);


    }

    public boolean isClose() {
        return isClose;
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }


    public void enemyMove() {

        Directions directions = Directions.LEFT;

        double random = Math.random();

        iterators++;

        if (iterators >= 10) {
            directions = random > 0.50f ? Directions.LEFT : Directions.RIGHT;
            iterators = 0;
        }

        //body.applyLinearImpulse(Constants.PLAYER_LEFT, Constants.PLAYER_LEFT, true);


        System.out.println(directions);
        switch (directions) {
            case LEFT:
                body.applyLinearImpulse(Constants.PLAYER_LEFT, Constants.PLAYER_LEFT, true);
                break;
            case RIGHT:
                body.applyLinearImpulse(Constants.ENEMY_RIGHT, Constants.ENEMY_RIGHT, true);
                break;
        }
    }


}
