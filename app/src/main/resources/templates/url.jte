@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.layout.page(
page = page,
content = @`
    <div>
    <h1>Сайт: ${page.getUrl().getName()}</h1>
    <table class="table table-bordered table-hover mt-3">
        <tr>
            <td>Id</td>
            <td>${page.getUrl().getId()}</td>
        </tr>
        <tr>
            <td>Имя</td>
            <td>${page.getUrl().getName()}</td>
        </tr>
        <tr>
            <td>Дата создания</td>
            <td>${page.getUrl().getCreatedAtFormatted()}</td>
        </tr>
    </table>
    </div>
    <div>
        <h2>Проверки</h2>
        <form></form>
        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Код ответа</th>
                <th>title</th>
                <th>h1</th>
                <th>description</th>
                <th>Дата проверки</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
`
)