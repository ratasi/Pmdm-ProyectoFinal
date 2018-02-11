package com.example.rafatarrega.navdrawer.model;

/**
 * Created by rafatarrega on 11/2/18.
 */

public class Usuarios {

    private String nickname;
    private String name;

    public Usuarios(){}

    public Usuarios(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
