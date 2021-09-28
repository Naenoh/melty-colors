
@layout.base(content = @`
    <form class="flex flex-col flex-wrap" action="/colors" method="post" enctype="multipart/form-data">
        <label >Name
            <input required type="text" name="name">
        </label>
        <label >Creator
            <input required type="text" name="creator">
        </label>
        @for(int i = 0; i<6; i++)
            !{String name = (i == 0) ? "base" : "color"+i;}
            !{String displayName = i == 0 ? "Base" : "Color "+i;}
            <label >${displayName}
                <select required name="${name}">
                    @for(int j = 1; j<=10; j++)
                        <option value="${j}">${j}</option>
                    @endfor
                </select>
            </label>
        @endfor
        <label >Image
            <input accept="image/*" type="file" required name="image">
        </label>
        <label>
            <input type="submit" value="Submit">
        </label>
    </form>
`)