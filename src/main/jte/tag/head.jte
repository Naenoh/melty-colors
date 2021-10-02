@import com.naeno.melty.App

@param String title

<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${title}</title>
@if(App.isProd)
    <link href="/static/css/tailwind.min.css" rel="stylesheet">
@else
    <link href="/static/css/tailwind.css" rel="stylesheet">
@endif
<script src="https://unpkg.com/htmx.org@1.6.0" defer></script>
