<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>購入</title>
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
    <header class="navbar navbar-expand navbar-light bg-light">
      <div class="container-fluid w-50">
        <div class="d-flex flex-row align-items-center">
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
      <div class="container px-2 d-flex">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/app/home">
              <i class="bi bi-house"></i> HOME</a
            >
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <form action="/app/logout" method="post" novalidate>
              <button type="submit" class="btn btn-outline-secondary">
                <i class="bi bi-door-closed"></i>
                ログアウト
              </button>
            </form>
          </li>
        </ul>
      </div>
    </header>
    <div class="container py-3 px-0">
      <div class="w-50 mx-auto">
        <h1 class="h3">購入</h1>

        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">選択商品</span>
            </h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 class="my-0" id="item_name"></h6>
                  <small class="text-muted">購入商品</small>
                </div>
                <span class="text-muted" id="price"></span>
              </li>
            </ul>
          </div>

          <div class="col-md-7 col-lg-8">
            <form
              id="purchase_form"
              action="/app/purchase"
              method="post"
              novalidate
            >
              <div class="row g-3">
                <div class="col-sm-6">
                  <label for="last_name" class="form-label">姓</label>
                  <input
                    type="text"
                    class="form-control"
                    id="last_name"
                    placeholder="姓"
                    value=""
                    name="last_name"
                    required
                  />
                  <div class="invalid-feedback">姓を入力してください</div>
                </div>

                <div class="col-sm-6">
                  <label for="first_name" class="form-label">名</label>
                  <input
                    type="text"
                    class="form-control"
                    id="first_name"
                    placeholder="名"
                    value=""
                    name="first_name"
                    required
                  />
                  <div class="invalid-feedback">名を入力してください</div>
                </div>

                <div class="col-12">
                  <label for="zip_code" class="form-label">郵便番号</label>
                  <div class="row mx-auto">
                    <input
                      type="text"
                      class="form-control col"
                      id="zip_code"
                      placeholder="ハイフンなしで入力してください"
                      name="zip_code"
                      required
                    />
                    <button
                      class="btn btn-primary ml-2 col-md-auto"
                      type="button"
                      onclick="fillAddress()"
                    >
                      住所検索
                    </button>
                    <div class="invalid-feedback">
                      郵便番号を入力してください
                    </div>
                  </div>
                </div>

                <div class="col-12">
                  <label for="address" class="form-label">都道府県</label>
                  <input
                    type="text"
                    class="form-control"
                    id="address"
                    placeholder="都道府県"
                    name="address"
                    required
                  />
                  <div class="invalid-feedback">都道府県を入力してください</div>
                </div>

                <div class="col-12">
                  <label for="address2" class="form-label">市区町村</label>
                  <input
                    type="text"
                    class="form-control"
                    id="address2"
                    placeholder="市区町村"
                    name="address2"
                    required
                  />
                  <div class="invalid-feedback">市区町村を入力してください</div>
                </div>

                <div class="col-12">
                  <label for="address3" class="form-label"
                    >丁目／番地／号</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="address3"
                    placeholder="丁目／番地／号"
                    name="address3"
                    required
                  />
                  <div class="invalid-feedback">
                    丁目／番地／号を入力してください
                  </div>
                </div>
                <button class="btn btn-primary" type="submit">購入</button>
              </div>
            </form>
          </div>
        </div>
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
    <script async="true">
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
      const fillItem = () => {
        const itemName = '<%=request.getAttribute("item_name") %>';
        const price = '<%=request.getAttribute("price") %>';
        console.log(itemName);
        const itemNameEl = document.getElementById("item_name");
        itemNameEl.innerHTML = itemName;
        const priceEl = document.getElementById("price");
        priceEl.innerHTML = "￥" + price;
      };
      const isValidLastName = () => {
        const lastName = document.getElementById("last_name");
        if (lastName.validity.valid) {
          lastName.className = "form-control is-valid";
          return true;
        }
        lastName.className = "form-control is-invalid";
        return false;
      };
      const isValidFirstName = () => {
        const firstName = document.getElementById("first_name");
        if (firstName.validity.valid) {
          firstName.className = "form-control is-valid";
          return true;
        }
        firstName.className = "form-control is-invalid";
        return false;
      };
      const isValidZipCode = () => {
        const zipCode = document.getElementById("zip_code");
        if (zipCode.validity.valid) {
          zipCode.className = "form-control col is-valid";
          return true;
        }
        zipCode.className = "form-control col is-invalid";
        return false;
      };

      const isValidAddress = () => {
        const address = document.getElementById("address");
        if (address.validity.valid) {
          address.className = "form-control is-valid";
          return true;
        }
        address.className = "form-control is-invalid";
        return false;
      };
      const isValidAddress2 = () => {
        const address = document.getElementById("address2");
        if (address.validity.valid) {
          address.className = "form-control is-valid";
          return true;
        }
        address.className = "form-control is-invalid";
        return false;
      };
      const isValidAddress3 = () => {
        const address = document.getElementById("address3");
        if (address.validity.valid) {
          address.className = "form-control is-valid";
          return true;
        }
        address.className = "form-control is-invalid";
        return false;
      };
      const getAddress = async (zipCode) => {
        const data = {
          zipcode: zipCode,
        };
        const searchParams = new URLSearchParams(data);
        const response = await fetch(
          "https://zipcloud.ibsnet.co.jp/api/search?" + searchParams.toString()
        );
        if (!response.ok) {
          return;
        }
        return await response.json();
      };
      const fillAddress = async () => {
        if (!isValidZipCode()) {
          return;
        }
        const zipCodeEl = document.getElementById("zip_code");
        const zipCode = zipCodeEl.value;

        const { results } = await getAddress(zipCode);
        if (!results) {
          console.log("住所情報の取得に失敗しました");
          return;
        }
        const addressValue = Array.from(results).at(0);
        const { address1, address2, address3 } = addressValue;
        const addressEl = document.getElementById("address");
        const address2El = document.getElementById("address2");
        const address3El = document.getElementById("address3");
        addressEl.value = address1;
        address2El.value = address2;
        address3El.value = address3;
        isValidAddress();
        isValidAddress2();
        isValidAddress3();
        console.log(JSON.stringify(results));
      };
      const validateOnSubmit = () => {
        const form = document.getElementById("purchase_form");
        form.addEventListener("submit", (event) => {
          const array = [
            isValidLastName(),
            isValidFirstName(),
            isValidZipCode(),
            isValidAddress(),
            isValidAddress2(),
            isValidAddress3(),
          ];
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
        const lastName = document.getElementById("last_name");
        lastName.addEventListener("input", () => {
          isValidLastName();
        });
        fillItem();
        const firstName = document.getElementById("first_name");
        firstName.addEventListener("input", () => {
          isValidFirstName();
        });
        const zipCode = document.getElementById("zip_code");
        zipCode.addEventListener("input", () => {
          isValidZipCode();
        });
        const address = document.getElementById("address");
        address.addEventListener("input", () => {
          isValidAddress();
        });
        const address2 = document.getElementById("address2");
        address2.addEventListener("input", () => {
          isValidAddress2();
        });
        const address3 = document.getElementById("address3");
        address3.addEventListener("input", () => {
          isValidAddress3();
        });
        validateOnSubmit(callback);
      })();
    </script>
  </body>
</html>
