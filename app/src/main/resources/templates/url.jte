@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
page = page,
content = @`
    <main class="flex-grow-1">
        <section>
            <div class="container-lg mt-5">
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
            <div class="container-lg mt-5">
                <h2>Проверки</h2>
                <form method="post" action="${NamedRoutes.urlChecks(page.getUrl().getId())}">
                    <button type="submit" class="btn btn-primary">Запустить проверку</button>
                </form>
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

                    @for(var value: page.getUrlCheckList())
                        <tr>
                            <td>${value.getId()}</td>
                            <td>${value.getStatusCode()}</td>
                            <td>${value.getTitle()}</td>
                            <td>${value.getH1()}</td>
                            <td>${value.getDescription()}</td>
                            <td>${value.getCreatedAtFormatted()}</td>
                        </tr>
                    @endfor

                    </tbody>
                </table>
            </div>
        </section>
    </main>
`
)