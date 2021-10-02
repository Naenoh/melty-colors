@import com.naeno.melty.models.CustomColor
@param CustomColor color

@layout.base(content = @`
    <div class="pt-4 max-w-2xl mx-auto">
        <div class="pb-4 w-full text-center">
            <div class="text-4xl font-bold">${color.name()}</div>
            <div class="text-xl font-italic">by <span class="font-bold">${color.creator()}</span></div>
        </div>
        <div class="grid grid-cols-2 gap-2">
            <img class="border-black border" src="/images/${color.imageURL()}">
            <div class="flex flex-col pl-2 border-l border-black">
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Base</div>
                    <div class="px-2 border">${color.color0()}</div>
                </div>
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Parts 1</div>
                    <div class="px-2 border">${color.color1()}</div>
                </div>
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Parts 2</div>
                    <div class="px-2 border">${color.color2()}</div>
                </div>
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Parts 3</div>
                    <div class="px-2 border">${color.color3()}</div>
                </div>
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Parts 4</div>
                    <div class="px-2 border">${color.color4()}</div>
                </div>
                <div class="m-2 w-32 flex">
                    <div class="w-20 font-bold border-black border text-center">Parts 5</div>
                    <div class="px-2 border">${color.color5()}</div>
                </div>
            </div>
        </div>
    </div>
`)
