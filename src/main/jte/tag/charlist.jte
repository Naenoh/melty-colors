@import java.util.List
@import com.naeno.melty.models.Character
@param List<Character> characters

<div class="grid grid-cols-6 gap-2 pt-4 w-max mx-auto">
    @for(Character character : characters)
        <a href="/colors?charId=${character.id()}">
            <img width="100" height="100" src="${character.imageUrl()}" />
        </a>
    @endfor
</div>

