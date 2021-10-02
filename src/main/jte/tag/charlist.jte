@import java.util.List
@import com.naeno.melty.models.Character
@param List<Character> characters

<div class="grid grid-cols-4 md:grid-cols-7 gap-2 pt-4 w-max mx-auto">
    @for(Character character : characters)
        <a href="/colors?charId=${character.id()}">
            <img class="border border-black" width="96" height="96" src="/static/images/${character.imageUrl()}" />
        </a>
    @endfor
</div>

