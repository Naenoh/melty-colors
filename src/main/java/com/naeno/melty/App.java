package com.naeno.melty;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.javalin.Javalin;

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
        app.get("/", ctx -> ctx.render("hello.jte"));
    }
}
