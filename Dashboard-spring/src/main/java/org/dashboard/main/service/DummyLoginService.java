package org.dashboard.main.service;

public class DummyLoginService implements LoginService{


    @Override
    public void login(String user, String pswd) {
        System.out.println("user logged in");
    }
}
