@import com.naeno.melty.models.CustomColor
@import java.util.List
@import gg.jte.support.ForSupport
@param List<CustomColor> colors
@param Integer charId = null
@param String colorName = ""
@param boolean hasMore = false

!{int minId = 0;}
    @for(var color: ForSupport.of(colors))
        <a class="border border-black relative h-120 flex items-center" href="/colors/${color.get().id()}">
            <img class="w-auto mx-auto max-h-full" src="/images/${color.get().imageURL()}" />
            <div class="absolute bg-opacity-60 bg-black text-white bottom-0 w-full pl-2 py-1">
                <span class="font-bold">${color.get().name()}</span> by <span class="font-bold">${color.get().creator()}</span>
            </div>
        </a>
        @if(color.isLast())
            !{minId = color.get().id();}
        @endif
    @endfor
@if(hasMore)
    <button class="mx-auto w-max font-bold py-2 px-4 border-black border sm:col-span-2 lg:col-span-3 xl:col-span-4" hx-get="/colors?fromId=${minId}&charId=${charId}&colorName=${colorName}&partial=true" hx-swap="outerHTML">More...</button>
@endif
