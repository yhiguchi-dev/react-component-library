<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>ホーム</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css"
    />
  </head>
  <body>
    <header class="navbar navbar-light bg-light">
      <div class="container">
        <div class="d-flex align-items-center">
          <a class="navbar-brand" href="https://www.solxyz.co.jp/">
            <img
              src="http://localhost:8080/logo.svg"
              alt="solxyz-logo"
              class="h3"
            />
          </a>
          <h1 class="h3">購買管理</h1>
        </div>
      </div>
    </header>
    <div class="container py-3 px-0">
      <h1 class="h3">ホーム</h1>
      <div class="row">
        <div class="col-lg-12">
          <p>
            <a href="/app/item" class="btn btn-sq-lg btn-primary">
              <i class="bi bi-cart-check"></i><br />
              商品購入
            </a>
            <a href="/app/history" class="btn btn-sq-lg btn-success">
              <i class="bi bi-box-seam"></i><br />
              注文履歴
            </a>
          </p>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
