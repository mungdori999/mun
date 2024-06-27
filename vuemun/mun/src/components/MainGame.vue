<template>
  <div
    class="d-flex justify-content-center align-items-center vh-100"
    style="flex-direction: column"
    v-if="$store.state.userInfo.itemId"
  >
    <div class="progress" style="width: 300px">
      <div
        class="progress-bar"
        role="progressbar"
        style="width: 25%"
        aria-valuenow="25"
        aria-valuemin="0"
        aria-valuemax="100"
      >
        25%
      </div>
    </div>
    <canvas id="canvas" width="600" height="600"> </canvas>
  </div>

  <div class="container d-flex vh-100" v-else>
    <div class="row justify-content-center align-self-center w-100">
      <div class="col-auto">
        <button class="btn btn-primary" @click="startItem">GET START</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as THREE from "three";
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader.js";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import axiosInstance from "@/axios/axiosinstance";
import Swal from "sweetalert2";
export default {
  data() {
    return  {
      itemName:'',
      gltf: '',
    }
  },
  mounted() {

    if (this.$store.state.userInfo.itemId) {
      axiosInstance.get('/api/item/'+this.$store.state.userInfo.itemId)
      .then((data) => {
          this.itemName = data.data.itemName;
          this.gltf = data.data.gltf;
          this.renderGame()
        })
        .catch((error) => {
          Swal.fire({
            title: "에러",
            text: error,
            icon: "warning",
          });
        });

      
    }
  },

  methods: {
    renderGame() {
      let scene = new THREE.Scene();
      let renderer = new THREE.WebGLRenderer({
        canvas: document.querySelector("#canvas"),
        antialias: true,
        alpha: true, // alpha 옵션을 true로 설정하여 배경을 투명하게
      });

      let camera = new THREE.PerspectiveCamera(30, 1);
      camera.position.set(0, 0, 5);

      // scene.background = new THREE.Color("white");
      scene.background = null;
      // OrbitControls 생성
      const controls = new OrbitControls(camera, renderer.domElement);
      controls.enableDamping = true; // 부드러운 움직임을 위해 댐핑 활성화
      controls.dampingFactor = 0.25;
      controls.screenSpacePanning = false;

      // GLTFLoader를 사용하여 모델 로드
      const loader = new GLTFLoader();
      loader.load(
        this.gltf,
        (gltf) => {
          scene.add(gltf.scene);
          animate(); // 모델 로드 후 애니메이션 시작
        },
        undefined,
        (error) => {
          console.error("An error happened", error);
        }
      );
      // 애니메이션 루프
      const animate = () => {
        requestAnimationFrame(animate);
        controls.update(); // 애니메이션 루프 내에서 컨트롤 업데이트
        renderer.render(scene, camera);
      };

      animate();
    },
    startItem() {
      axiosInstance
        .patch("/api/item/starting")
        .then((response) => {
          if (response.status == 200) {
            Swal.fire({
              title: "잘 키우시오",
              text: "당신의 돌",
              icon: "success",
            }).then(() => {
              location.reload();
            });
          }
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

<style>
</style>