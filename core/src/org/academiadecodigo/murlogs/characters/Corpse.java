package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.academiadecodigo.murlogs.box2d.UserData;

public abstract class Corpse extends Actor {

    protected Body body;
    protected UserData userData;

    public Corpse(Body body) {
        this.body = body;
        this.userData = (UserData)body.getUserData();
        body.setFixedRotation(true);
    }

    public abstract UserData getUserData();

}
