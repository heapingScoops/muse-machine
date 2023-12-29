import { createRouter as _createRouter, createWebHistory } from 'vue-router';

//chat gpt below
// import Vue from 'vue';
// import VueRouter from 'vue-router';
// import Home from './views/Home.vue';
// import About from './views/About.vue';

// Vue.use(VueRouter);

//import views 
import HomeView from '../views/HomeView.vue'
import RegisterUserView from '../views/RegisterUserView.vue'
import PoemView from '../views/PoemView.vue'

//log routes 
const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/register',
        name: 'register-user',
        component: RegisterUserView
    },
    {
        path: '/poem/:creationId',
        name: 'poem',
        component: PoemView
    }
  ];
  
  export function createRouter () {
    
    return _createRouter({
      history: createWebHistory(),
      routes: routes
    })
  }