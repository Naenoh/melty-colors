package com.naeno.melty;

import com.naeno.melty.controllers.ColorsController;
import com.naeno.melty.dao.CharacterDAO;
import com.naeno.melty.dao.CustomColorDAO;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.javalin.Javalin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        Javalin app = Javalin.create().start(7000);
        try {
            Files.createDirectory(Paths.get("/images"));
        } catch (IOException e) {
        }
        CustomColorDAO colorDAO = new CustomColorDAO();
        CharacterDAO characterDAO = new CharacterDAO();
        ColorsController controller = new ColorsController(colorDAO, characterDAO);

        app.get("/", ctx -> ctx.render("home.jte", model("colors",colorDAO.getCustomColors(), "characters", characterDAO.getChars())));
        app.get("/submit", ctx -> ctx.render("submit.jte"));
        app.get("/colors", controller::getColors);
        app.get("/colors/<id>", controller::getColor);
        app.post("/colors", controller::addColor);
    }
}
