<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>ログイン</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
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
          <h1 class="h3" style="width: 8rem">購買管理</h1>
        </div>
      </div>
    </header>
    <div class="container py-3 px-0">
      <div class="w-25 mx-auto">
        <h1 class="h3">ログイン</h1>
        <form id="login_form" action="/app/login" method="post" novalidate>
          <div class="form-floating">
            <input
              type="email"
              class="form-control"
              id="email"
              placeholder="name@example.com"
              name="email"
              required
            />
            <label for="email" class="form-label" style="color: lightgray"
              >メールアドレス</label
            >
            <div class="invalid-feedback">入力内容に誤りがあります</div>
          </div>
          <div class="form-floating">
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder="Password"
              name="password"
              required
            />
            <label for="password" class="form-label" style="color: lightgray"
              >パスワード</label
            >
            <div class="invalid-feedback">入力内容に誤りがあります</div>
          </div>
          <div>
            <a href="/app/registration" class="link-primary"
              >新規登録はこちら</a
            >
          </div>
          <button type="submit" class="btn btn-primary">ログイン</button>
        </form>
      </div>
      <div class="toast-container position-fixed p-3 bottom-0 end-0">
        <div
          class="toast text-white bg-danger border-0"
          role="alert"
          aria-live="assertive"
          aria-atomic="true"
        >
          <div class="d-flex align-items-stretch">
            <button
              type="button"
              class="btn-close btn-close-white"
              data-bs-dismiss="toast"
            ></button>
            <div id="errorMessage" class="toast-body" />
          </div>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script>
      const isValidEmail = () => {
        const email = document.getElementById("email");
        if (email.validity.valid) {
          email.className = "form-control";
          return true;
        }
        email.className = "form-control is-invalid";
        return false;
      };

      const isValidPassword = () => {
        const password = document.getElementById("password");
        if (password.validity.valid) {
          password.className = "form-control";
          return true;
        }
        password.className = "form-control is-invalid";
        return false;
      };

      const validateOnLoad = (callback) => {
        const errorMessage = '<%=request.getAttribute("errorMessage") %>';
        const errorMessageEl = document.getElementById("errorMessage");
        window.addEventListener("load", () => {
          if (errorMessage && errorMessage !== "null") {
            errorMessageEl.innerHTML = errorMessage;
            callback();
          }
        });
      };

      const validateEmail = () => {
        const email = document.getElementById("email");
        email.addEventListener("input", () => {
          isValidEmail();
        });
      };

      const validatePassword = () => {
        const password = document.getElementById("password");
        password.addEventListener("input", () => {
          isValidPassword();
        });
      };

      const validateOnSubmit = () => {
        const form = document.getElementById("login_form");
        form.addEventListener("submit", (event) => {
          const array = [isValidEmail(), isValidPassword()];
          if (array.every((isValid) => isValid === true)) {
            return;
          }
          event.preventDefault();
        });
      };

      (() => {
        const toastElList = [].slice.call(document.querySelectorAll(".toast"));
        const toastList = toastElList.map((toastEl) => {
          return new bootstrap.Toast(toastEl, {
            delay: 5000,
            autohide: true,
          });
        });
        const callback = () => {
          toastList[0].show();
        };
        validateOnLoad(callback);
        validateEmail();
        validatePassword();
        validateOnSubmit();
      })();
    </script>
  </body>
</html>
