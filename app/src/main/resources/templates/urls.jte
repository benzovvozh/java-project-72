@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
    content = @`
<div>
    <ul>
        @for(var value: page.getUrls())
            <li>${value.getName()}</li>
        @endfor
    </ul>
</div>
    <div>
        <a href="${NamedRoutes.mainPath()}">Вернуться на главную страницу</a>
    </div>
`
)