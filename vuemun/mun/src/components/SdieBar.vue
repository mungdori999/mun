<template>
  <div class="d-flex">
    <div class="sidebar" id="sidebar" :class="[{ active: isSidebarActive }]">
      <nav class="nav flex-column">
        <a class="nav-link" href="#" @click="getHome">Home</a>
        <a class="nav-link" href="#" @click="getShop">Shop</a>
        <a class="nav-link" href="#">Settings</a>
        <a class="nav-link" href="#" @click="logout">Logout</a>
      </nav>
    </div>
    <div class="content flex-grow-1">
      <i
        class="bi bi-list hamburger"
        id="sidebarToggle"
        @click="toggleSidebar"
        :class="[{ active: isSidebarActive }]"
      ></i>
    </div>
  </div>
</template>

<script>
import axiosInstance from "../axios/axiosinstance";

export default {
  components: {},
  data() {
    return {
      isSidebarActive: false,
    };
  },
  methods: {
    toggleSidebar() {
      this.isSidebarActive = !this.isSidebarActive;
    },
    getHome(event) {
      event.preventDefault();
      this.$store.commit('getHome');
      this.toggleSidebar();
    },
    getShop(event) {
      event.preventDefault();
      this.$store.commit("getShop");
      this.toggleSidebar();
    },
    logout(event) {
      event.preventDefault();
      axiosInstance
        .get("/api/user/logout")
        .then((response) => {
          if (response.status === 200) {
            this.$store.commit("initUserInfo");
            localStorage.removeItem("access");

            //새로고침
            location.reload();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$store.commit("initUserInfo");
          localStorage.removeItem("access");

          //새로고침
          location.reload();
        });
    },
  },
};
</script>

<style>
.sidebar {
  position: fixed;
  top: 0;
  left: -250px;
  height: 100%;
  width: 250px;
  background-color: #343a40;
  transition: left 0.3s ease;
  z-index: 1000;
}
.sidebar.active {
  left: 0;
}
.sidebar .nav-link {
  color: #fff;
}
.content {
  padding-left: 15px;
  padding-top: 15px;
}
.hamburger {
  position: fixed;
  top: 15px;
  left: 15px;
  font-size: 1.5rem;
  cursor: pointer;
  transition: left 0.3s ease;
  z-index: 1001; /* 사이드바보다 위에 표시되도록 설정 */
}
.hamburger.active {
  left: 265px; /* 사이드바 너비(250px) + 기본 여백(15px) */
}
</style>