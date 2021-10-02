package com.naeno.melty.dao;

import com.naeno.melty.models.CustomColor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomColorDAO {

    private final Jdbi jdbi;

    public CustomColorDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<CustomColor> getAllCustomColors(){
        return jdbi.withHandle(handle -> handle.createQuery("select * from colors").mapTo(CustomColor.class).list());
    }

    public CustomColor getColor(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery("select * from colors where id = :id").bind("id",id).mapTo(CustomColor.class).one());
    }

    public CustomColor addColor(String name, String creator, int[] colors, String imageURL, Integer charId) {
        Integer id = jdbi.withHandle(handle -> handle.createUpdate(
                "insert into colors " +
                        "(name,creator,color0,color1,color2,color3,color4,color5,image_name,char_id)" +
                        " values " +
                        "(:name,:creator,:color0,:color1,:color2,:color3,:color4,:color5,:image_name,:char_id)")
                .bind("name",name)
                .bind("creator",creator)
                .bind("color0",colors[0])
                .bind("color1",colors[1])
                .bind("color2",colors[2])
                .bind("color3",colors[3])
                .bind("color4",colors[4])
                .bind("color5",colors[5])
                .bind("image_name",imageURL)
                .bind("char_id", charId)
                .executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one());
        return new CustomColor(id, name, creator, colors[0],colors[1],colors[2],colors[3],colors[4],colors[5],imageURL,charId);
    }

    public List<CustomColor> getCustomColors(String name, Integer charId) {

        return jdbi.withHandle(handle -> {
            String baseQuery = "select * from colors WHERE 1=1 ";
            if (name != null && !name.isEmpty()) {
                baseQuery += "AND name like :name";
            }
            if (charId != null) {
                baseQuery += "AND char_id = :char_id";
            }
            Query query = handle.createQuery(baseQuery);
            if (name != null && !name.isEmpty()) {
                query.bind("name","%"+name+"%");
            }
            if (charId != null) {
                query.bind("char_id",charId);
            }
            return query.mapTo(CustomColor.class).list();
        });
    }

    public boolean colorExists(int[] colors) {
        Integer count = jdbi.withHandle(handle -> {
            Query query = handle.createQuery("select id from colors where color0 = ? and color1 = ? and color2 = ? and color3 = ? and color4 = ? and color5 = ?");
            for(int i = 0; i < 6; i++){
                query.bind(i,colors[i]);
            }
            return query.mapTo(Integer.class).one();
        });
        return count != null;
    }
}
