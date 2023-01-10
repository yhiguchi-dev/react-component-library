<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>新規登録</title>
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
        <h1 class="h3">新規登録</h1>
        <form
          id="registration_form"
          action="/app/registration"
          method="post"
          novalidate
        >
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
            <div class="valid-feedback"></div>
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
              pattern="^[a-zA-Z\d]{8,60}$"
            />
            <label for="password" class="form-label" style="color: lightgray"
              >パスワード</label
            >
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">入力内容に誤りがあります</div>
          </div>
          <div class="form-floating">
            <input
              type="password"
              class="form-control"
              id="passwordConfirmation"
              placeholder="Password"
              required
            />
            <label for="password" class="form-label" style="color: lightgray"
              >パスワード確認</label
            >
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">
              パスワードの入力内容と異なっています
            </div>
          </div>
          <div class="mb-3">
            <input
              class="form-check-input"
              type="checkbox"
              onclick="showOrHidePassword()"
              id="passwordCheckbox"
            />
            <label class="form-check-label" for="passwordCheckbox">
              パスワード表示
            </label>
          </div>
          <button type="submit" class="btn btn-primary">新規登録</button>
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
      /**
       * メール妥当性チェック
       * @returns {boolean}
       */
      const isValidEmail = () => {
        const email = document.getElementById("email");
        if (email.validity.valid) {
          email.className = "form-control is-valid";
          return true;
        }
        email.className = "form-control is-invalid";
        return false;
      };

      /**
       * パスワード妥当性チェック
       * @returns {boolean}
       */
      const isValidPassword = () => {
        const password = document.getElementById("password");
        if (password.validity.valid) {
          password.className = "form-control is-valid";
          return true;
        }
        password.className = "form-control is-invalid";
        return false;
      };

      /**
       * 確認用パスワード妥当性チェック
       * @returns {boolean}
       */
      const isValidPasswordConfirmation = () => {
        const password = document.getElementById("password");
        const passwordConfirmation = document.getElementById(
          "passwordConfirmation"
        );
        if (
          passwordConfirmation.validity.valid &&
          password.value === passwordConfirmation.value
        ) {
          passwordConfirmation.className = "form-control is-valid";
          return true;
        }
        passwordConfirmation.className = "form-control is-invalid";
        return false;
      };

      /**
       * オンロード時検証
       * @param callback
       */
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

      /**
       * メール検証
       */
      const validateEmail = () => {
        const email = document.getElementById("email");
        email.addEventListener("input", () => {
          isValidEmail();
        });
      };

      /**
       * パスワード検証
       */
      const validatePassword = () => {
        const password = document.getElementById("password");
        password.addEventListener("input", () => {
          isValidPassword();
        });
      };

      /**
       * 確認用パスワード検証
       */
      const validatePasswordConfirmation = () => {
        const passwordConfirmation = document.getElementById(
          "passwordConfirmation"
        );
        passwordConfirmation.addEventListener("input", () => {
          isValidPasswordConfirmation();
        });
      };

      /**
       * サブミット時検証
       */
      const validateOnSubmit = () => {
        const form = document.getElementById("registration_form");
        form.addEventListener("submit", (event) => {
          const array = [
            isValidEmail(),
            isValidPassword(),
            isValidPasswordConfirmation(),
          ];
          if (array.every((isValid) => isValid === true)) {
            return;
          }
          event.preventDefault();
        });
      };

      /**
       * パスワード表示 非表示
       */
      const showOrHidePassword = () => {
        const password = document.getElementById("password");
        const passwordConfirmation = document.getElementById(
          "passwordConfirmation"
        );
        if (password.type === "password") {
          password.type = "text";
          passwordConfirmation.type = "text";
        } else {
          password.type = "password";
          passwordConfirmation.type = "password";
        }
      };

      /**
       * トースト表示
       * @returns {callback}
       */
      const showToast = () => {
        const toastList = toastElList.map((toastEl) => {
          return new bootstrap.Toast(toastEl, {
            delay: 5000,
            autohide: true,
          });
        });
        return () => {
          toastList[0].show();
        };
      }

      /**
       * ページ読み込みスクリプト
       */
      (() => {
        const callback = showToast();
        validateOnLoad(callback);
        validateEmail();
        validatePassword();
        validatePasswordConfirmation();
        validateOnSubmit();
      })();
    </script>
  </body>
</html>
