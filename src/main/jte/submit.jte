
@layout.base(content = @`
    <form class="pt-4 max-w-2xl mx-auto grid grid-cols-1 md:grid-cols-2" action="/colors" method="post" enctype="multipart/form-data">
        <div class="bg-blue-400"></div>
        <fieldset class="flex flex-col flex-wrap font-bold ml-2 border-l-2 border-black">
            <div class="flex m-2 ">
                <label class="px-2 border border-black w-20 text-center" for="name">Name</label>
                <input class="flex-1 pb-1 border border-l-0" required type="text" id="name" name="name">
            </div>
            <div class="flex m-2">
                <label class="px-2 border border-black w-20 text-center" for="creator">Creator</label>
                <input class="flex-1 pb-1 border border-l-0" required type="text" id="creator" name="creator">
            </div>
            @for(int i = 0; i<6; i++)
                !{String displayName = i == 0 ? "Base" : "Color "+i;}
                <div class="m-2 w-32 flex">
                    <label class="w-20 px-2 border text-center border-black" for="${"color"+i}">${displayName}</label><select class="border border-l-0" id="${"color"+i}" required name="${"color"+i}">
                        @for(int j = 1; j<=10; j++)
                            <option value="${j}">${j}</option>
                        @endfor
                    </select>

                </div>
            @endfor
            <label class="m-2">Image
                <input accept="image/*" type="file" required name="image">
            </label>
            <input class="m-2 ml-2" type="submit" value="Submit">
        </fieldset>
    </form>
`)