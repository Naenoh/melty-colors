package com.naeno.melty.controllers;


import com.naeno.melty.dao.CharacterDAO;
import com.naeno.melty.dao.CustomColorDAO;
import com.naeno.melty.models.CustomColor;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

public class ColorsController {

    private final CustomColorDAO colorDAO;
    private final CharacterDAO charDAO;

    public ColorsController(CustomColorDAO colorDAO, CharacterDAO characterDAO) {
        this.colorDAO = colorDAO;
        this.charDAO = characterDAO;
    }

    public void getColors(Context ctx) {
        // filtering
        ctx.render("browse.jte", model("colors", colorDAO.getCustomColors(),"characters", charDAO.getChars()));
    }

    public void getColor(Context ctx) {
        // db stuff
        ctx.render("color.jte",Collections.singletonMap("color", colorDAO.getColor(ctx.pathParam("id"))));
    }

    public void addColor(Context ctx) {
        String name = ctx.formParam("name");
        String creator = ctx.formParam("creator");
        int[] colors = new int[6];
        for (int i = 0; i<6;i++){
            colors[i] = ctx.formParamAsClass("color"+i, Integer.class).get();
        }
        UploadedFile uploadedFile = ctx.uploadedFile("image");
        String imageURL = "images/"+ UUID.randomUUID()+uploadedFile.getExtension();
        FileUtil.streamToFile(uploadedFile.getContent(),imageURL);
        CustomColor color = colorDAO.addColor(name,creator,colors,imageURL);
        System.out.println(color);
        ctx.redirect("/colors/" + color.id(), 302);
    }

}
