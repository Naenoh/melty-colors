package com.naeno.melty.dao;

import com.naeno.melty.models.CustomColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomColorDAO {
    private Map<String,CustomColor> customColors = new HashMap<>();

    public CustomColorDAO() {
        for (int i = 1; i<=12; i++) {
            CustomColor color = new CustomColor(i, "Shirou", "naeno",0,0,0,0,0,0,"https://i.imgur.com/1LXPC9x.png");
            customColors.put(String.valueOf(i),color);
        }
    }

    public List<CustomColor> getCustomColors(){
        return customColors.values().stream().toList();
    }

    public CustomColor getColor(String id) {
        return customColors.get(id);
    }

    public CustomColor addColor(String name, String creator, int[] colors, String imageURL) {
        CustomColor color = new CustomColor(0, name,creator,colors[0],colors[1],colors[2],colors[3],colors[4],colors[5], imageURL);
        customColors.put("0",color);
        return color;
    }
}
