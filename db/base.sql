CREATE TABLE IF NOT EXISTS characters (id INTEGER PRIMARY KEY, name TEXT NOT NULL, image_name TEXT NOT NULL);
CREATE TABLE IF NOT EXISTS colors (id INTEGER PRIMARY KEY, name TEXT NOT NULL, creator TEXT NOT NULL, color0 INTEGER NOT NULL, color1 INTEGER NOT NULL, color2 INTEGER NOT NULL, color3 INTEGER NOT NULL, color4 INTEGER NOT NULL, color5 INTEGER NOT NULL, image_name TEXT NOT NULL, char_id INTEGER NOT NULL, FOREIGN KEY(char_id) REFERENCES characters(id))
INSERT INTO characters (name, image_name) values
("Shiki","shiki.webp"),
("Arcueid","arc.webp"),
("Ahika","ahika.webp"),
("Ciel","ciel.webp"),
("Hisui","hisui.webp"),
("Kohaku","kohaku.webp"),
("Maids","maids.webp"),
("Kouma","kouma.webp"),
("Miyako","miyako.webp"),
("Noel","noel.webp"),
("Roa","roa.webp"),
("Vlov","vlov.webp"),
("Rec Arcueid","warc.webp"),
("Saber","saber.webp");