
import axios from "axios";
import { createStore } from 'vuex'
import Swal from "sweetalert2";
import axiosInstance from "./axios/axiosinstance";

// Create a new store instance.
const store = createStore({
    state() {
        return {
            isLogin: false,
            mainGame:false,
            shop:false,
            userInfo: {},
        }
    },

    mutations: {
        setUserInfo(state, data) {
            state.isLogin = true;
            state.userInfo = data;
            state.mainGame = true;
        },
        initUserInfo(state) {
            state.isLogin = false;
            state.userInfo = {};
            state.mainGame = false;
        },
        getShop(state) {
            state.mainGame = false;
            state.shop = true;
        },
        getHome(state) {
            state.mainGame = true;
            state.shop= false;
        }
    },
    actions: {
        login({ dispatch }, data) {
            axios
                .post("/api/login", {
                    email: data.email,
                    password: data.password,
                })
                .then((response) => {
                    var accessToken = response.headers.access;
                    localStorage.setItem("access", accessToken);
                    // 로그인 성공 후 받은 access 토큰과 사용자 정보 저장
                    dispatch('getUserInfo')
                })
                .catch((error) => {
                    // 요청 오류가 있는 경우 작업 수행
                    if (error.response.status === 401) {
                        Swal.fire({
                            title: "로그인 오류",
                            text: "다시 로그인 하셈",
                            icon: "warning",
                        });
                    }
                });
        },

        getUserInfo({ commit }) {
            axiosInstance.get("/api/user").then((data) => {
                if (data.status === 200) {
                    commit('setUserInfo', data.data)
                }
                if (data.status === 203) {

                    commit('initUserInfo');
                }

            })
                .catch(
                    () => {
                        commit('initUserInfo');

                    }
                )

        },

        signup() {

        }

    }
})

export default store;
