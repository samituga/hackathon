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
    private boolean attack;
    private boolean block;
    private Animation runningAnimation;
    private int stateTime;
    private Animation hittingAnimation;
    private int attIt;


    public Enemy(Body body) {
        super(body);


        TextureAtlas textureEnemyAtlas = new TextureAtlas(Constants.ENEMY_ATLAS_PATH);
        TextureRegion[] enemyRunningFrames = new TextureRegion[Constants.ENEMY_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.ENEMY_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.ENEMY_RUNNING_REGION_NAMES[i];
            enemyRunningFrames[i] = textureEnemyAtlas.findRegion(path);

        }

        TextureRegion[] enemyHittingFrames = new TextureRegion[Constants.ENEMY_HITTING_REGION_NAMES.length];
        for (int i = 0; i < Constants.ENEMY_HITTING_REGION_NAMES.length; i++) {
            String path = Constants.ENEMY_HITTING_REGION_NAMES[i];
            enemyHittingFrames[i] = textureEnemyAtlas.findRegion(path);

        }
        hittingAnimation = new Animation(0.2f, enemyHittingFrames);
        runningAnimation = new Animation(0.2f, enemyRunningFrames);
        stateTime += Gdx.graphics.getDeltaTime();


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        stateTime += Gdx.graphics.getDeltaTime();
        int width = (int) (128 / 1.1);
        int height = (int) (256 / 1.2);
        if (directions != null) {
            batch.draw((TextureRegion) runningAnimation.getKeyFrame(stateTime, true), (getX() * 50 - 1f), getY() * 12, width, height);
            return;
        }

        if (attack == true) {
            batch.draw((TextureRegion) hittingAnimation.getKeyFrame(stateTime, true), (getX() * 50 - 1f), getY() * 12, width, height);
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
            directions = random > 0.3f ? Directions.LEFT : Directions.RIGHT;
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
        attIt++;
        if (attIt >= 3) {
            hp -= 2;
            attIt = 0;
        }
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

    public int getHp() {
        return hp;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }
}
