@import gg.jte.Content

@param String title = "MELTY COLORS"
@param Content content

<!doctype html>
<html lang="en">
<head>
    @tag.head(title)
</head>
<body class="sans-serif min-h-screen relative bg-gray-200">
    @tag.header()
    <main class="pb-20 max-w-6xl mx-2 md:mx-auto h-full">
        ${content}
    </main>
    @tag.footer()
</body>