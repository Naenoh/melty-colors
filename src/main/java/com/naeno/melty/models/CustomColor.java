package com.naeno.melty.models;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public record CustomColor(int id, String name, String creator, int color0, int color1, int color2, int color3, int color4, int color5, String imageURL, int charId) {
    public static class ColorMapper implements RowMapper<CustomColor> {

        @Override
        public CustomColor map(ResultSet rs, StatementContext ctx) throws SQLException {
            return new CustomColor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("creator"),
                    rs.getInt("color0"),
                    rs.getInt("color1"),
                    rs.getInt("color2"),
                    rs.getInt("color3"),
                    rs.getInt("color4"),
                    rs.getInt("color5"),
                    rs.getString("image_name"),
                    rs.getInt("char_id"));
        }
    }
}
