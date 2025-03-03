@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
page = page,
content = @`
    <h1>Список сайтов</h1>
    <div>
        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th>Id</th>
                <th>Имя</th>
                <th>Последняя проверка</th>
                <th>Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var value: page.getUrls())
                <tr>
                <td>${value.getId()}</td>
                <td>
                    <a href="${NamedRoutes.idUrlPath(value.getId())}">${value.getName()}</a>
                </td>
                <td>${value.getCreatedAtFormatted()}</td>
                <td></td>
                </tr>
            @endfor
            </tbody>

        </table>
    </div>

`
)