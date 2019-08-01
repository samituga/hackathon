package org.academiadecodigo.murlogs.box2d;

import org.academiadecodigo.murlogs.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

}
