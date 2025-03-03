@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
    <h1>Список сайтов</h1>
<div>
    <ul>
        @for(var value: page.getUrls())
            <li><a href="${NamedRoutes.idUrlPath(value.getId())}">${value.getName()}</a></li>
        @endfor
    </ul>
</div>
    <div>
        <a href="${NamedRoutes.mainPath()}">Вернуться на главную страницу</a>
    </div>
`
)