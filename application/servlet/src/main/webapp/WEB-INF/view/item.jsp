<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>商品一覧</title>
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
    <div class="container-fluid py-3 px-3">
      <form
        id="item_form"
        action="/app/item"
        method="get"
        class="form-inline align-items-start"
        novalidate
      >
        <div class="d-flex flex-row gap-3">
          <div>
            <input
              type="search"
              id="item_name"
              name="item_name"
              placeholder="前方一致検索"
              class="form-control"
              required
            />
            <div class="invalid-feedback">入力内容に誤りがあります</div>
          </div>
          <div>
            <button type="submit" class="btn btn-primary">
              <i class="bi bi-search"></i>
            </button>
          </div>
        </div>
      </form>
      <div id="items" class="w-50">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">商品</th>
              <th scope="col">金額</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody id="item-table-body"></tbody>
        </table>
        <nav aria-label="Page navigation">
          <ul class="pagination" id="pagination"></ul>
        </nav>
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
            <div id="errorMessage" class="toast-body"></div>
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
       * 商品名妥当性チェック
       * @returns {boolean}
       */
      const isValidItemName = () => {
        const itemName = document.getElementById("item_name");
        if (itemName.validity.valid) {
          itemName.className = "form-control";
          return true;
        }
        itemName.className = "form-control is-invalid";
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
       * サブミット時検証
       */
      const validateOnSubmit = () => {
        const form = document.getElementById("item_form");
        form.addEventListener("submit", (event) => {
          const array = [isValidItemName()];
          if (array.every((isValid) => isValid === true)) {
            return;
          }
          event.preventDefault();
        });
      };

      /**
       * ページ付け
       * @param current
       * @param perPage
       * @param totalCount
       * @returns {{current, items: string[]}}
       */
      const paginate = ({ current, perPage, totalCount }) => {
        const division = Math.floor(totalCount / perPage);
        const max = totalCount % perPage > 0 ? division + 1 : division;
        const items = ["1"];
        if (current === 1 && max === 1) {
          return { current, items };
        }
        if (current > 4) {
          items.push("&hellip;");
        }
        const prevShowing = current - 2;
        const nextShowing = current + 2;
        for (
          let i = prevShowing > 2 ? prevShowing : 2;
          i <= Math.min(max, nextShowing);
          i++
        ) {
          items.push(i.toString());
        }
        if (nextShowing + 1 < max) {
          items.push("&hellip;");
        }
        if (nextShowing < max) {
          items.push(max.toString());
        }
        return { current, items };
      };

      /**
       * 商品テーブル作成
       */
      const createItemTable = () => {
        const resultJson = '<%=request.getAttribute("resultJson") %>';
        const itemsEl = document.getElementById("items");
        if (resultJson === "null") {
          itemsEl.className = "d-none";
          return;
        }
        const parsed = JSON.parse(resultJson);
        const { current_page, total_count, per_page, item_name, items } =
          parsed;
        const itemNameEl = document.getElementById("item_name");
        itemNameEl.value = item_name;
        if (total_count === 0) {
          itemsEl.className = "d-none";
          return;
        }
        const itemTableBodyEl = document.getElementById("item-table-body");
        Array.from(items).forEach((item, index) => {
          const { item_name, price } = item;
          const trEl = document.createElement("tr");
          const thEl = document.createElement("th");
          thEl.scope = "row";
          thEl.innerHTML = (
            index +
            1 +
            (current_page - 1) * per_page
          ).toString();
          trEl.appendChild(thEl);
          const itemNameTdEl = document.createElement("td");
          itemNameTdEl.innerHTML = item_name.toString();
          trEl.appendChild(itemNameTdEl);
          const priceTdEl = document.createElement("td");
          priceTdEl.innerHTML = price.toString();
          trEl.appendChild(priceTdEl);
          const arrowTdEl = document.createElement("td");
          const arrowButtonEl = document.createElement("button");
          arrowButtonEl.type = "button";
          arrowButtonEl.className = "btn opacity-0";
          arrowButtonEl.innerHTML = "&gt;";
          arrowButtonEl.onclick = () => {
            const data = {
              item_name,
              price,
            };
            const searchParams = new URLSearchParams(data);
            location.href = "/app/purchase?" + searchParams.toString();
          };
          arrowTdEl.appendChild(arrowButtonEl);
          trEl.appendChild(arrowTdEl);
          itemTableBodyEl.appendChild(trEl);
        });
        const pagination = paginate({
          current: current_page,
          perPage: per_page,
          totalCount: total_count,
        });
        const paginationUlEl = document.getElementById("pagination");
        console.log(pagination.items);
        Array.from(pagination.items).forEach((item) => {
          console.log(item);
          const liEl = document.createElement("li");
          liEl.className = "page-item";
          const aEl = document.createElement("a");
          aEl.className = "page-link";
          aEl.innerHTML = item.toString();
          if (!isNaN(Number(item))) {
            const data = {
              item_name,
              page: item,
            };
            const searchParams = new URLSearchParams(data);
            aEl.href = "/app/item?" + searchParams.toString();
          }
          liEl.appendChild(aEl);
          paginationUlEl.appendChild(liEl);
        });
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
        validateOnSubmit();
        createItemTable();
      })();
    </script>
  </body>
</html>
