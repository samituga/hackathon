package org.academiadecodigo.murlogs.characters;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.murlogs.box2d.SideLimitUserData;
import org.academiadecodigo.murlogs.box2d.UserData;

public class SideLimit extends Corpse {

    public SideLimit(Body body){
        super(body);
    }

    @Override
    public SideLimitUserData getUserData() {
        return (SideLimitUserData) userData;
    }
}
