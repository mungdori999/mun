<template>
  <div class="text-center" v-if="$store.state.isLogin == true">{{ $store.state.userInfo.name }}의 개인화면</div>
  <div class="wrapper" v-if="$store.state.isLogin == false">
    <div class="login-container" :class="{ 'right-panel-active': sign === 1 }">
      <div class="sign-up-container">
        <form>
          <h1>Create Account</h1>
          <div class="social-links">
            <div>
              <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            </div>
            <div>
              <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
            <div>
              <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
            </div>
          </div>
          <span>or use your email for registration</span>
          <input type="text" placeholder="Name" v-model="name" />
          <input type="email" placeholder="Email" v-model="email" />
          <input type="password" placeholder="Password" v-model="password" />
          <button class="form_btn" @click="signup">Sign Up</button>
        </form>
      </div>
      <div class="sign-in-container">
        <form>
          <h1>Sign In</h1>
          <div class="social-links">
            <div>
              <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            </div>
            <div>
              <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
            <div>
              <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
            </div>
          </div>
          <span>or use your account</span>
          <input type="email" placeholder="Email" v-model="email" />
          <input type="password" placeholder="Password" v-model="password" />
          <button class="form_btn" @click="login">Sign In</button>
        </form>
      </div>
      <div class="overlay-container">
        <div class="overlay-left">
          <h1>Welcome Back</h1>
          <p>To keep connected with us please login with your personal info</p>
          <button id="signIn" @click="showSign(0)" class="overlay_btn">
            Sign In
          </button>
        </div>
        <div class="overlay-right">
          <h1>Hello, Friend</h1>
          <p>Enter your personal details and start journey with us</p>
          <button id="signUp" @click="showSign(1)" class="overlay_btn">
            Sign Up
          </button>
        </div>
      </div>
    </div>
  </div>
  <SideBar v-if="$store.state.isLogin == true"></SideBar>
  <MainGame v-if="$store.state.mainGame == true"></MainGame>
  <Shop v-if="$store.state.shop == true"></Shop>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import SideBar from "./components/SdieBar.vue";
import MainGame from "./components/MainGame.vue";
import Shop from "./components/Shop.vue";
export default {
  name: "App",
  components: {
    SideBar: SideBar,
    MainGame: MainGame,
    Shop : Shop,
  },
  data() {
    return {
      sign: 0,
      name: "",
      email: "",
      password: "",
      role: "",
    };
  },
  mounted() {
    this.$store.dispatch("getUserInfo");
  },
  methods: {
    showSign(bool) {
      this.sign = bool;
    },
    login(event) {
      event.preventDefault();
      var loginData = {
        email: this.email,
        password: this.password,
      };
      this.$store.dispatch("login", loginData);
    },
    signup(event) {
      event.preventDefault();
      axios
        .post("/api/user/signup", {
          name: this.name,
          email: this.email,
          password: this.password,
          role: "USER",
        })
        .then((response) => {
          if (response.status == 200) {
            Swal.fire({
              title: "회원가입성공",
              text: "로그인 후 이용해주세요",
              icon: "success",
            }).then(() => {
              location.reload();
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
@import "./assets/all.css";
</style>
 