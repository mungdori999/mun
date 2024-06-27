<template>
  <div class="container" style="padding-top: 30px">
    <div class="row">
      <div class="col-md-3 mb-4" v-for="product in products" :key="product.id">
        <div class="card h-100">
          <img :src="product.imgUrl" class="card-img-top" alt="Product Image" />
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text" style="margin-bottom: 0">
              $ {{ product.price }}
            </p>
            <div class="text-end">
              <span v-for="(tag, i) in product.tags" :key="i" class="card-tag">
                #{{ tag }}
              </span>
            </div>
            <div
              style="
                display: flex;
                justify-content: space-between;
                margin-top: 10px;
              "
            >
              <a
                href="#"
                class="cart-icon"
                @click="setCart($event, product.id)"
              >
                <i class="bi bi-cart"></i>
              </a>
              <a href="#" class="cart-icon">
                <i class="bi bi-cash-coin"></i>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div
    class="cart-container"
    data-bs-toggle="modal"
    data-bs-target="#cartModal"
    @click="$event.preventDefault()"
  >
    <a href="#" class="cart-icon">
      <i class="bi bi-cart"></i>
      <span class="cart-count">{{ cartCount }}</span>
      <!-- 장바구니에 담긴 상품 수량 -->
    </a>
  </div>

  <!-- Modal -->
  <div
    class="modal fade"
    id="cartModal"
    tabindex="-1"
    aria-labelledby="cartModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="justify-content: space-between">
          <h5 class="modal-title" id="cartModalLabel">Cart</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <div class="d-flex align-items-center" v-for="cart in cartList" :key="cart.id" style="padding: 10px 0; ">
            <img
              src="https://via.placeholder.com/150"
              alt="Product"
              class="img-fluid mr-3"
              style="width: 80px; height: 80px"
            />
            <div style="margin-left: 30px;">
              <div> {{ cart.productName }}</div>
              <div>{{ cart.price }}</div>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-primary">Buy</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axiosInstance from "@/axios/axiosinstance";
import Swal from "sweetalert2";

export default {
  mounted() {
    axiosInstance
      .get("/api/products")
      .then((data) => {
        this.products = data.data;
      })
      .catch((error) => {
        Swal.fire({
          title: "에러",
          text: error,
          icon: "warning",
        });
      });
    axiosInstance
      .get("/api/products/cart")
      .then((data) => {
        this.cartCount = data.data.length;
        this.cartList = data.data;
      })
      .catch((error) => {
        Swal.fire({
          title: "에러",
          text: error,
          icon: "warning",
        });
      });
  },
  data() {
    return {
      products: [],
      cartCount: 0,
      cartList:[],
    };
  },
  methods: {
    setCart(event, productId) {
      event.preventDefault();

      axiosInstance
        .post("/api/products/cart", { productId: productId })

        .then((data) => {
          this.cartCount = data.data.length;
          this.cartList = data.data;
        })
        .catch((error) => {
          Swal.fire({
            title: "에러",
            text: error,
            icon: "warning",
          });
        });
    },
  },
};
</script>

<style scoped>
.card {
  transition: transform 0.2s;
}
.card:hover {
  transform: scale(1.05);
}
.cart-container {
  position: fixed;
  bottom: 20px; /* 페이지 하단에서 20px 떨어진 위치 */
  right: 20px; /* 페이지 우측에서 20px 떨어진 위치 */
}

.cart-icon {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #333;
}

.cart-icon i {
  width: 30px; /* 아이콘 크기 조정 */
  height: auto;
  font-size: 2rem; /* 아이콘의 크기를 2rem으로 설정 */
  margin-right: 5px; /* 아이콘과 수량 사이 간격 */
  background-color: transparent; /* 이미지 배경을 투명하게 설정 */
}

.cart-count {
  background-color: red; /* 배경색 */
  color: white; /* 글자색 */
  border-radius: 50%; /* 원형 모양 */
  padding: 5px; /* 내부 여백 */
  font-size: 12px; /* 글자 크기 */
  position: absolute; /* 위치 설정 */
  top: -5px; /* 위로 조금 이동하여 아이콘 위에 표시 */
  right: -5px; /* 오른쪽으로 조금 이동하여 아이콘 옆에 표시 */
}
</style>