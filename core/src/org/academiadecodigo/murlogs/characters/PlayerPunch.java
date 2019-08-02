package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.PunchUserData;
import org.academiadecodigo.murlogs.box2d.UserData;
import org.academiadecodigo.murlogs.utils.Constants;

public class PlayerPunch extends Corpse {

    public PlayerPunch(Body body){
        super(body);
    }
    @Override
    public PunchUserData getUserData() {
        return (PunchUserData) userData;
    }


}
