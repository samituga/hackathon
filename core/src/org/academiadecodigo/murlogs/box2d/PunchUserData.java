package org.academiadecodigo.murlogs.box2d;

import org.academiadecodigo.murlogs.enums.UserDataType;

public class PunchUserData extends UserData {

    public PunchUserData(){
        super();
        userDataType = UserDataType.PUNCH;
    }
}
