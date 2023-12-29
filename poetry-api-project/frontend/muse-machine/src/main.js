//------------------ IMPORTS  ----------------//
import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store/store.js'
import { createRouter } from './router/index.js'
import axios from 'axios'

//---------------------  AXIOS SETUP ---------------//
let currentToken = localStorage.getItem('token');
let currentUser = JSON.parse(localStorage.getItem('user'));



if (currentToken) {
  // Set token axios requests
  console.log(`Token Value: ${currentToken}`);
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

//------------------     INSTANTIATIONS   ----------------//
const store = createStore(currentToken, currentUser); //----- passing in token info --------//
const router = createRouter();
const app = createApp(MyApp);


//------------------ USE AND MOUNT ----------------//
app.use(store);
app.use(router);
app.mount('#app');
