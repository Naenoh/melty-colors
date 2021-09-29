package com.naeno.melty;

import com.naeno.melty.controllers.ColorsController;
import com.naeno.melty.dao.CharacterDAO;
import com.naeno.melty.dao.CustomColorDAO;
import com.naeno.melty.models.Character;
import com.naeno.melty.models.CustomColor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.javalin.Javalin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);
        try {
            Files.createDirectory(Paths.get("images"));
            Files.createDirectory(Paths.get("db"));
        } catch (IOException e) {
        }

        Jdbi jdbi = Jdbi.create("jdbc:sqlite:db/database.db").installPlugin(new SqlObjectPlugin()).installPlugin(new SQLitePlugin());
        initDb(jdbi);
        jdbi.registerRowMapper(new Character.CharacterMapper());
        jdbi.registerRowMapper(new CustomColor.ColorMapper());

        CustomColorDAO colorDAO = new CustomColorDAO(jdbi);
        CharacterDAO characterDAO = new CharacterDAO(jdbi);
        ColorsController controller = new ColorsController(colorDAO, characterDAO);

        app.get("/", controller::getHome);
        app.get("/submit", ctx -> ctx.render("submit.jte", Collections.singletonMap("characters", characterDAO.getChars())));
        app.get("/colors", controller::getColors);
        app.get("/colors/<id>", controller::getColor);
        app.post("/colors", controller::addColor);
    }

    public static void initDb(Jdbi jdbi){
        jdbi.useHandle(handle -> {
            handle.execute("CREATE TABLE IF NOT EXISTS characters (id INTEGER PRIMARY KEY, name TEXT NOT NULL, image_name TEXT NOT NULL);");
            handle.execute("CREATE TABLE IF NOT EXISTS colors " +
                    "(id INTEGER PRIMARY KEY," +
                    " name TEXT NOT NULL," +
                    " creator TEXT NOT NULL," +
                    " color0 INTEGER NOT NULL," +
                    " color1 INTEGER NOT NULL," +
                    " color2 INTEGER NOT NULL," +
                    " color3 INTEGER NOT NULL," +
                    " color4 INTEGER NOT NULL," +
                    " color5 INTEGER NOT NULL," +
                    " image_name TEXT NOT NULL," +
                    " char_id INTEGER NOT NULL," +
                    " FOREIGN KEY(char_id) REFERENCES characters(id));");
        });
    }
}
