package com.naeno.melty.dao;

import com.naeno.melty.models.CustomColor;

import java.util.ArrayList;
import java.util.List;

public class CustomColorDAO {
    private List<CustomColor> customColors = new ArrayList<>();

    public CustomColorDAO() {
        for (int i = 1; i<=12; i++) {
            CustomColor color = new CustomColor(i, "Shirou", "naeno",0,0,0,0,0,0,"https://i.imgur.com/1LXPC9x.png");
            customColors.add(color);
        }
    }

    public List<CustomColor> getCustomColors(){
        return customColors;
    }
}
