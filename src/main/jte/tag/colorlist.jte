@import com.naeno.melty.models.CustomColor
@import java.util.List

@param List<CustomColor> colors

<div class="pt-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-2">
    @for(CustomColor color: colors)
        <a class="border border-black relative" href="/colors/${color.id()}">
            <img class="w-full h-auto" src="/images/${color.imageURL()}" />
            <div class="absolute bg-opacity-60 bg-black text-white bottom-0 w-full pl-2 py-1">
                <span class="font-bold">${color.name()}</span> by <span class="font-bold">${color.creator()}</span>
            </div>
        </a>
    @endfor
</div>
