package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.EnemyUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

public class Enemy extends Corpse {


    protected boolean close;
    protected UserData userData;
    private int iterators;
    private boolean initialMove = true;
    private int hp = 100;
    private boolean isDead = false;
    private boolean attack;
    private boolean block;
    private Animation runningAnimation;
    private int stateTime;


    public Enemy(Body body) {
        super(body);

        TextureAtlas textureEnemyAtlas = new TextureAtlas(Constants.ENEMY_ATLAS_PATH);
        TextureRegion[] enemyRunningFrames = new TextureRegion[Constants.ENEMY_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.ENEMY_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.ENEMY_RUNNING_REGION_NAMES[i];
            enemyRunningFrames[i] = textureEnemyAtlas.findRegion(path);
            System.out.println(path);
            System.out.println(enemyRunningFrames[i]);
        }

        runningAnimation = new Animation(0.2f, runningAnimation);
        stateTime += Gdx.graphics.getDeltaTime();


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        stateTime += Gdx.graphics.getDeltaTime();

        if(directions!= null){
            System.out.println(runningAnimation);

            batch.draw((TextureRegion)runningAnimation.getKeyFrame(stateTime,true),0,0, 256, 128);
            return;
        }



    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    Directions directions = Directions.STOP;


    public void enemyMove() {

        if (initialMove) {
            for (int i = 0; i < 50; i++) {
                body.applyLinearImpulse(Constants.ENEMY_LEFT, Constants.ENEMY_LEFT, true);
                if (body.getPosition().x <= 10f) {
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

        if (isClose()) {
            double blockOrAttack = random;


            if (blockOrAttack < 0.1f) {
                block = true;
                attack = false;
            }
            if (blockOrAttack >= 0.1f && blockOrAttack < 0.2f) {
                attack = true;
                block = false;
            }
            if (blockOrAttack >= 0.2f) {
                block = false;
                attack = false;
            }
        }

        switch (directions) {
            case LEFT:
                body.applyLinearImpulse(Constants.ENEMY_LEFT, Constants.ENEMY_LEFT, true);
                break;
            case RIGHT:
                body.applyLinearImpulse(Constants.ENEMY_RIGHT, Constants.ENEMY_RIGHT, true);
                break;
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void hitten() {
        System.out.println(hp + " hp");// TODO: 02/08/2019  10
        hp -= 10;
    }

    public void setClose(boolean b) {
        close = b;
    }

    public boolean isClose() {
        return close;
    }

    public boolean isAttack() {
        return attack;
    }

    public boolean isBlock() {
        return block;
    }
}
