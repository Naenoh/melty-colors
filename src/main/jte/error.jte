@param String message

@layout.base(content = @`
    <div class="mt-4 pl-2 border-l border-black max-w-2xl mx-auto">
        <div class="text-2xl">An error occured :</div>
        <div>${message}</div>
    </div>
`)