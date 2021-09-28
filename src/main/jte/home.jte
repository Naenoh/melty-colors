@import com.naeno.melty.models.Character
@import com.naeno.melty.models.CustomColor

@import java.util.List

@param List<Character> characters
@param List<CustomColor> colors

@layout.base(content = @`
    @tag.charlist(characters = characters)
    @tag.colorlist(colors = colors)
`)