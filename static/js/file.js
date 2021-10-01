function getPreview() {
    const preview = document.getElementById("preview");
    console.log();
    preview.src = window.URL.createObjectURL(this.files[0]);
}

const preview = document.getElementById("preview");
const input = document.getElementById("image");
preview.addEventListener("click", () => {
    input.click();
});
input.addEventListener("change", getPreview, false);
console.log("ready!")
document.addEventListener("paste", (e) => {
    const input = document.getElementById("image");
    items = e.clipboardData.items;
    if (items.length >= 1) {
        var isImage = false;
        if (items[0].type.indexOf("image") !== -1) {
            //image
            var blob = items[0].getAsFile();
            var URLObj = window.URL || window.webkitURL;
            var source = URLObj.createObjectURL(blob);
            const input = document.getElementById("image");
            input.files = e.clipboardData.files;
            const preview = document.getElementById("preview");
            preview.src = source;
            isImage = true;
        }
    }
    if (isImage == true) {
        e.preventDefault();
    }
})