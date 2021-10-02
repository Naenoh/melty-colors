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
        String charIdString = ctx.queryParam("charId");
        if (charIdString != null && !charIdString.isEmpty()){
            charId = ctx.queryParamAsClass("charId",Integer.class).get();
        }
        Integer fromId = null;
        String fromIdString = ctx.queryParam("fromId");
        if (fromIdString != null && !fromIdString.isEmpty()) {
            fromId = ctx.queryParamAsClass("fromId",Integer.class).get();
        }
        List<CustomColor> colors = colorDAO.getCustomColors(colorName, charId, fromId);
        boolean hasMore = colors.size() == 21;
        if (hasMore) {
            colors.remove(colors.size()-1);
        }
        Boolean partial = ctx.queryParamAsClass("partial", Boolean.class).getOrDefault(false);
        if (partial) {
            ctx.render("tag/colorlist.jte", model("colors", colors,"characters", charDAO.getChars(),"charId",charId,"colorName",colorName,"hasMore",hasMore));
        } else {
            ctx.render("browse.jte", model("colors", colors,"characters", charDAO.getChars(),"charId",charId,"colorName",colorName,"hasMore",hasMore));
        }
    }

    public void getColor(Context ctx) {
        ctx.render("color.jte",Collections.singletonMap("color", colorDAO.getColor(ctx.pathParamAsClass("id",Integer.class).get())));
    }

    public void addColor(Context ctx) throws Exception {
        String name = ctx.formParamAsClass("name", String.class).get();
        String creator = ctx.formParamAsClass("creator", String.class).get();
        if (name.length() > 32){
            throw new Exception("Name is too long (max size 32 characters).");
        }
        if (creator.length() > 24){
            System.out.println(creator.length());
            throw new Exception("Creator name is too long (max size 24 characters).");
        }
        Integer charId = ctx.formParamAsClass("charId",Integer.class).get();
        int[] colors = new int[6];
        for (int i = 0; i<6;i++){
            colors[i] = ctx.formParamAsClass("color"+i, Integer.class).get();
        }
        UploadedFile uploadedFile = ctx.uploadedFile("image");
        if(uploadedFile == null) {
            throw new Exception("Missing image file.");
        } else if (uploadedFile.getSize() > 300000) {
            throw new Exception("Uploaded image is too big (max size 300kb).");
        }
        String imageURL = UUID.randomUUID()+uploadedFile.getExtension();
        CustomColor color = colorDAO.addColor(name,creator,colors,imageURL,charId);
        FileUtil.streamToFile(uploadedFile.getContent(),"data/images/"+imageURL);
        ctx.redirect("/colors/" + color.id(), 302);
    }

    public void getHome(Context ctx) {
        List<CustomColor> colors = colorDAO.getCustomColors(null,null, null);
        boolean hasMore = colors.size() == 21;
        if (hasMore) {
            colors.remove(colors.size()-1);
        }
        ctx.render("home.jte", model("colors", colors,"characters", charDAO.getChars(), "hasMore", hasMore));
    }

}
