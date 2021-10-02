@import com.naeno.melty.models.Character
@import com.naeno.melty.models.CustomColor

@import java.util.List

@param List<Character> characters
@param List<CustomColor> colors
@param boolean hasMore = false

@layout.base(content = @`
    @tag.charlist(characters = characters)
    <div class="pt-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-2">
        @tag.colorlist(colors = colors, hasMore = hasMore)
    </div>
`)