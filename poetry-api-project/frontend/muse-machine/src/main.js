//------------------ IMPORTS  ----------------//
//bringing it into existence from the library. 
//I think most of the time here we'll do import { createThing }, we're not just importing the library, we're drilling down to the CREATOR FILE!
// import { createApp } from 'vue'
import { createApp } from './node_modules/vue'
import MyApp from './App.vue'
import { createStore } from './store/store.js'
import { createRouter } from './router/index.js'
import axios from './node_modules/axios'

// import 'vuetify/styles'
// import { createVuetify } from 'vuetify/lib/framework.mjs'
// import * as components from 'vuetify/components'
// import * as directives from 'vuetify/directives'

//---------------------  AXIOS SETUP ---------------//
let currentToken = localStorage.getItem('token');
let currentUser = JSON.parse(localStorage.getItem('user'));



if (currentToken) {
  // Set token axios requests
  console.log(`Token Value: ${currentToken}`);
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

//------------------     INSTANTIATIONS   ----------------//
//HERE we're creating an instance that will be doing all the stuff
const store = createStore(currentToken, currentUser); //----- passing in token info --------//
const router = createRouter();
const app = createApp(MyApp);

// const vuetify = createVuetify({
//   components,
//   directives,
// })


//------------------ USE AND MOUNT ----------------//
//here we're telling the app to use the objects we instantiated.
app.use(store);
app.use(router);
// app.use(vuetify);
app.mount('#app');


