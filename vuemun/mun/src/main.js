import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
// Import Bootstrap JS
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import store from "./store.js"

createApp(App).use(store).mount('#app')
