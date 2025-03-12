@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.DateFormat
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
page = page,
content = @`
    <h1>Список сайтов</h1>
    <div class="container-lg mt-5">
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
                    <!-- ID -->
                <td>${value.getId()}</td>
                    <!-- Ссылка и название -->
                <td>
                    <a href="${NamedRoutes.idUrlPath(value.getId())}">${value.getName()}</a>
                </td>
                    <!-- Последняя проверка -->
                <td>${page.getUrlChecks().isEmpty() ? null :
                        DateFormat.dateFormater(page.getUrlChecks().get(value.getId()).getCreatedAt())}</td>
                    <!-- Последний статус-код -->
                <td>${page.getUrlChecks().isEmpty() ? null :
                             page.getUrlChecks().get(value.getId()).getStatusCode()}</td>
                    
                </tr>
            @endfor
            </tbody>

        </table>
    </div>

`
)