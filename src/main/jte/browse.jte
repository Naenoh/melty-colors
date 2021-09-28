
@import com.naeno.melty.models.CustomColor

@import java.util.List
@param List<CustomColor> colors

@layout.base(content = @`
    @tag.colorlist(colors = colors)
`)