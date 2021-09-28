package com.naeno.melty.controllers;


import com.naeno.melty.dao.CustomColorDAO;
import io.javalin.http.Context;

import java.util.Collections;
import java.util.Map;

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

}
