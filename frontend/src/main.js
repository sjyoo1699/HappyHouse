import Vue from "vue";
import App from "./App.vue";
import router from "./router/index.js";
import store from "./store/index.js";
import Vuex from "vuex";
import vuetify from "./plugins/vuetify";

Vue.use(Vuex);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
