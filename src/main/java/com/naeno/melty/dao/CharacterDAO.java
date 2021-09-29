package com.naeno.melty.dao;

import com.naeno.melty.models.Character;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CharacterDAO {

    private final Jdbi jdbi;

    public CharacterDAO(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public List<Character> getChars(){
        List<Character> characters = jdbi.withHandle(handle -> handle.createQuery("select id, name, image_name from characters")
                .mapTo(Character.class)
                .list());
        System.out.println(characters);
        return characters;
    }
}
