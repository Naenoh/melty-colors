
@import com.naeno.melty.models.CustomColor
@import com.naeno.melty.models.Character
@import java.util.List

@param List<CustomColor> colors
@param List<Character> characters
@param Integer charId = null
@param String colorName = null
@param boolean hasMore = false

@layout.base(content = @`
    @tag.searchbar(characters = characters, charId = charId, colorName = colorName)
    <div class="pt-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-2">
        @tag.colorlist(colors = colors, charId = charId, colorName = colorName, hasMore = hasMore)
    </div>
`)