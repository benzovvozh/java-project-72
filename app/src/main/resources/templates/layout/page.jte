@import gg.jte.Content
@import hexlet.code.dto.BasePage
@import hexlet.code.util.NamedRoutes
@param BasePage page = null
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Page analyzer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body class="d-flex flex-column min-vh-100">


<header class="p-3 text-bg-dark">
    <div class="container">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <svg class="bi me-2" width="40" height="32">
                <use xlink:href="#bootstrap"></use>
            </svg>
        </a>

        <ul class="nav nav-pills">
            <li class="nav-item"><a href="${NamedRoutes.mainPath()}" class="nav-link active"
                                    aria-current="page">Главная</a></li>
            <li class="nav-item"><a href="${NamedRoutes.urlsPath()}" class="nav-link active" aria-current="page">Список
                    сайтов</a></li>
        </ul>
    </div>
</header>


@if(page != null && page.getFlash() != null)
    <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success">
        <p>${page.getFlash()}</p>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
@endif
${content}
<footer class="footer border-top py-3 mt-5 bg-light">
    <div class="container-xl">
        <div class="text-center">
            created by
            <a href="https://github.com/benzovvozh" target="_blank">Vladimir Shumkov</a>
        </div>
    </div>
</footer>
</body>
</html>
