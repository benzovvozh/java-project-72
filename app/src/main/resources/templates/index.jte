@import hexlet.code.util.NamedRoutes


@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <form action="${NamedRoutes.urlsPath()}" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Введите адрес сайта
                    <input type="text" class="form-control" name="url"/>
                </label>
                <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Проверить</button>
            </div>

        </form>
    </div>
`
)