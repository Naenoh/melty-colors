package com.naeno.melty;

import com.naeno.melty.controllers.ColorsController;
import com.naeno.melty.dao.CharacterDAO;
import com.naeno.melty.dao.CustomColorDAO;
import com.naeno.melty.models.Character;
import com.naeno.melty.models.CustomColor;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinJte;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.javalin.Javalin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

public class App {

    public static boolean isProd = false;

    public static void main(String[] args) throws IOException {

        if (args.length > 0 && "prod".equals(args[0])) {
            isProd = true;
        }

        Files.createDirectories(Paths.get("data", "images"));
        Files.createDirectories(Paths.get("data", "db"));

        JavalinJte.configure(createTemplateEngine());
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles(staticFileConfig -> {
                staticFileConfig.hostedPath = "/static";
                staticFileConfig.directory = "static";
                staticFileConfig.location = Location.EXTERNAL;
                staticFileConfig.precompress = isProd;
            });
            config.addStaticFiles(staticFileConfig -> {
                staticFileConfig.hostedPath = "/images";
                staticFileConfig.directory = "data/images";
                staticFileConfig.location = Location.EXTERNAL;
            });
        }).start(7000);

        Jdbi jdbi = Jdbi.create("jdbc:sqlite:data/db/database.db").installPlugin(new SqlObjectPlugin()).installPlugin(new SQLitePlugin());
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
        app.exception(Exception.class, (e, ctx) -> {
            ctx.render("error.jte", model("message", e.getMessage()));
        });
        app.error(404, ctx -> {
            ctx.render("error.jte", model("message", "404 not found."));
        });
    }

    private static TemplateEngine createTemplateEngine() {
        if (isProd) {
            return TemplateEngine.createPrecompiled(Path.of("jte-classes"), ContentType.Html);
        } else {
            DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src", "main", "jte"));
            return TemplateEngine.create(codeResolver, ContentType.Html);
        }
    }

    public static void initDb(Jdbi jdbi) {
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
