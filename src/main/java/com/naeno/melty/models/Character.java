package com.naeno.melty.models;


import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Character(Integer id, String name, String imageUrl) {
    public static class CharacterMapper implements RowMapper<Character> {

        @Override
        public Character map(ResultSet rs, StatementContext ctx) throws SQLException {
            return new Character(rs.getInt("id"), rs.getString("name"), rs.getString("image_name"));
        }
    }
}
