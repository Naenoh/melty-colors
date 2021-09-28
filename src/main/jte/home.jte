@import com.naeno.melty.models.Character
@import com.naeno.melty.models.CustomColor

@import java.util.List

@param List<Character> characters = new com.naeno.melty.dao.CharacterDAO().getChars()
@param List<CustomColor> colors = new com.naeno.melty.dao.CustomColorDAO().getCustomColors()

@layout.base(content = @`
    @tag.charlist(characters = characters)
    @tag.colorlist(colors = colors)
`)