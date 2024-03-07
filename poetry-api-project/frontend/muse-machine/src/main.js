//------------------ IMPORTS  ----------------//
//bringing it into existence from the library. 
//I think most of the time here we'll do import { createThing }, we're not just importing the library, we're drilling down to the CREATOR FILE!
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
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

//------------------     INSTANTIATIONS   ----------------//
//HERE we're creating an instance that will be doing all the stuff
const store = createStore(currentToken, currentUser); //----- passing in token info --------//
const router = createRouter();
const app = createApp(MyApp);


//------------------ USE AND MOUNT ----------------//
//here we're telling the app to use the objects we instantiated.
app.use(store);
app.use(router);
app.mount('#app');


