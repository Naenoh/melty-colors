@import com.naeno.melty.models.Character
@import java.util.List

@param List<Character> characters
@param Integer charId = null
@param String colorName = null

<form class="flex w-full p-2 border-black border-b" action="/colors" method="get">
    <div class="flex-1 flex">
        <label class="text-lg px-2 border-black border" for="name">Color name</label>
        <input class="flex-1 border border-l-0" type="text" name="colorName" id="name" value="${colorName}">
    </div>
    <div class="px-2">
        <label class="h-full inline-block text-lg px-2 border-black border" for="char">Character</label><select class="pl-2 h-full text-lg border border-l-0" name="charId" id="char">
            <option selected="${charId == null}" value="">All</option>
            @for(Character character : characters)
                <option selected="${character.id().equals(charId)}" value="${character.id()}">${character.name()}</option>
            @endfor
        </select>
    </div>
    <input class="text-lg px-2 border-black border" type="submit" value="Search">
</form>