package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.EnemyUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

public class Enemy extends Corpse {


    protected boolean isClose;
    protected UserData userData;
    private int iterators;
    private boolean initialMove = true;
    private int hp = 100;
    private boolean isDead = false;


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

    Directions directions = Directions.STOP;


    public void enemyMove() {
        if (isDead) {


        }

        if (initialMove) {
            for (int i = 0; i < 50; i++) {
                body.applyLinearImpulse(Constants.ENEMY_LEFT, Constants.ENEMY_LEFT, true);
                if (body.getPosition().x <= 10f) {
                    System.out.println("STOP!!!!!!!!");
                    break;
                }
            }
            initialMove = false;
        }


        double random = Math.random();

        iterators++;


        if (iterators >= 30) {
            directions = random > 0.4f ? Directions.LEFT : Directions.RIGHT;
            iterators = 0;
        }

        if (isClose) {


        }

        switch (directions) {
            case LEFT:
                //this.setX(this.getX() + 3f);
                body.applyLinearImpulse(Constants.ENEMY_LEFT, Constants.ENEMY_LEFT, true);
                break;
            case RIGHT:
                body.applyLinearImpulse(Constants.ENEMY_RIGHT, Constants.ENEMY_RIGHT, true);
                break;
        }
    }

    public boolean isDead() {
        return isDead;
    }
}
