package com.naeno.melty.controllers;


import com.naeno.melty.dao.CustomColorDAO;
import com.naeno.melty.models.CustomColor;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class ColorsController {

    private CustomColorDAO colorDAO;

    public ColorsController(CustomColorDAO colorDAO) {
        this.colorDAO = colorDAO;
    }

    public void getColors(Context ctx) {
        // filtering
        ctx.render("browse.jte", Collections.singletonMap("colors", colorDAO.getCustomColors()));
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
