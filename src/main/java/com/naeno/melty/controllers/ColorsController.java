package com.naeno.melty.controllers;


import com.naeno.melty.dao.CharacterDAO;
import com.naeno.melty.dao.CustomColorDAO;
import com.naeno.melty.models.CustomColor;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.util.Collections;
import java.util.List;
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
        String colorName = ctx.queryParam("colorName");
        Integer charId = null;
        if (ctx.queryParam("charId") != null && !ctx.queryParam("charId").isEmpty()){
            charId = ctx.queryParamAsClass("charId",Integer.class).get();
        }
        List<CustomColor> colors = colorDAO.getCustomColors(colorName, charId);
        ctx.render("browse.jte", model("colors", colors,"characters", charDAO.getChars(),"charId",charId,"colorName",colorName));
    }

    public void getColor(Context ctx) {
        ctx.render("color.jte",Collections.singletonMap("color", colorDAO.getColor(ctx.pathParamAsClass("id",Integer.class).get())));
    }

    public void addColor(Context ctx) {
        String name = ctx.formParam("name");
        String creator = ctx.formParam("creator");
        Integer charId = ctx.formParamAsClass("charId",Integer.class).get();
        int[] colors = new int[6];
        for (int i = 0; i<6;i++){
            colors[i] = ctx.formParamAsClass("color"+i, Integer.class).get();
        }
        UploadedFile uploadedFile = ctx.uploadedFile("image");
        String imageURL = UUID.randomUUID()+uploadedFile.getExtension();
        FileUtil.streamToFile(uploadedFile.getContent(),imageURL);
        CustomColor color = colorDAO.addColor(name,creator,colors,imageURL,charId);
        ctx.redirect("/colors/" + color.id(), 302);
    }

    public void getHome(Context ctx) {
        ctx.render("home.jte", model("colors", colorDAO.getAllCustomColors(),"characters", charDAO.getChars()));
    }

}
