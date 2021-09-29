
@import com.naeno.melty.models.CustomColor
@import com.naeno.melty.models.Character
@import java.util.List

@param List<CustomColor> colors
@param List<Character> characters
@param Integer charId = null
@param String colorName = null

@layout.base(content = @`
    @tag.searchbar(characters = characters, charId = charId, colorName = colorName)
    @tag.colorlist(colors = colors)
`)