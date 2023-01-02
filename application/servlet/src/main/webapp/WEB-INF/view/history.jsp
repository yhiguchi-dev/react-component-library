<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>履歴一覧</title>
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
    <header class="navbar navbar-expand navbar-light bg-light">
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
      <div id="histories" class="w-50">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">履歴</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody id="history-table-body" />
        </table>
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

      const createTable = () => {
        const resultJson = '<%=request.getAttribute("resultJson") %>';
        const historiesEl = document.getElementById("histories");
        if (resultJson === "null") {
          historiesEl.className = "d-none";
          return;
        }
        const parsed = JSON.parse(resultJson);
        const { histories } = parsed;
        const historyTableBodyEl =
          document.getElementById("history-table-body");
        Array.from(histories).forEach((history, index) => {
          const {
            item_name,
            price,
            full_name,
            zip_code,
            prefecture,
            city,
            street,
          } = history;
          const trEl = document.createElement("tr");
          const thEl = document.createElement("th");
          thEl.scope = "row";
          thEl.innerHTML = (index + 1).toString();
          trEl.appendChild(thEl);
          const historyTdEl = document.createElement("td");
          historyTdEl.className = "d-flex";
          const historyDivEl = document.createElement("div");
          const itemNameEl = document.createElement("h6");
          itemNameEl.className = "my-0";
          itemNameEl.innerHTML = item_name.toString();
          const fullNameEl = document.createElement("small");
          fullNameEl.className = "mx-2";
          fullNameEl.innerHTML = full_name.toString();
          const addressEl = document.createElement("small");
          addressEl.className = "text-muted";
          const addressArray = ["〒" + zip_code, prefecture, city, street];
          addressEl.innerText = addressArray.join(" ");
          historyDivEl.appendChild(itemNameEl);
          historyDivEl.appendChild(fullNameEl);
          historyDivEl.appendChild(addressEl);
          const priceTdEl = document.createElement("td");
          const priceEl = document.createElement("span");
          priceEl.className = "text-muted";
          priceEl.innerHTML = "￥" + price;
          priceTdEl.appendChild(priceEl);
          historyTdEl.appendChild(historyDivEl);
          trEl.appendChild(historyTdEl);
          trEl.appendChild(priceTdEl);
          historyTableBodyEl.appendChild(trEl);
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
        createTable();
      })();
    </script>
  </body>
</html>
