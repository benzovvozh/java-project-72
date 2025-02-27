@import hexlet.code.util.NamedRoutes


@template.layout.page(
    content = @`
    <div class="mx-auto p-4 py-md-5">
        <form action="${NamedRoutes.urlsPath()}" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Введите адрес сайта
                    <input type="text" class="form-control" name="url" />
                </label>
            </div>
            <input type="submit" class="btn btn-primary" value="Сохранить" />
        </form>
    </div>
`
)