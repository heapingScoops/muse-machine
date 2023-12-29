//------------------ IMPORTS  ----------------//
import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store/store.js'
import { createRouter } from './router/index.js'



//------------------     INSTANTIATIONS   ----------------//
const store = createStore();
const router = createRouter();
const app = createApp(MyApp);



//------------------ USE AND MOUNT ----------------//
app.use(store);
app.use(router);
app.mount('#app');
