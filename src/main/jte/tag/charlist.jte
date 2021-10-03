@import java.util.List
@import com.naeno.melty.models.Character
@param List<Character> characters

<div class="grid grid-cols-5 md:grid-cols-7 gap-1 sm:gap-2 pt-4 w-max mx-auto">
    @for(Character character : characters)
        <a href="/colors?charId=${character.id()}">
            <img class="border border-black w-16 h-16 sm:w-24 sm:h-24" src="/static/images/${character.imageUrl()}" />
        </a>
    @endfor
</div>

