
@import com.naeno.melty.models.CustomColor
@import com.naeno.melty.models.Character
@import java.util.List

@param List<CustomColor> colors
@param List<Character> characters

@layout.base(content = @`
    @tag.searchbar(characters = characters)
    @tag.colorlist(colors = colors)
`)