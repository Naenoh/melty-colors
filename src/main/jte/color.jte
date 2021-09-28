@import com.naeno.melty.models.CustomColor
@param CustomColor color

@layout.base(content = @`
    <a class="relative" href="/color/${color.id()}">
        <img class="w-full h-auto" src="${color.imageURL()}" />
        <div class="absolute bg-opacity-60 bg-black text-white bottom-0 w-full pl-2 py-1">
            <span class="font-bold">${color.name()}</span> by <span class="font-bold">${color.creator()}</span>
        </div>
    </a>
`)
