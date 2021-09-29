package com.naeno.melty.dao;

import com.naeno.melty.models.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

    private List<Character> chars = new ArrayList<>();

    public CharacterDAO(){
        for (int i = 1; i <= 14; i++) {
            Character character = new Character(i,"Shiki-"+i,"https://i.imgur.com/xAfyWHv.png");
            chars.add(character);
        }
    }

    public List<Character> getChars(){
        return chars;
    }
}
