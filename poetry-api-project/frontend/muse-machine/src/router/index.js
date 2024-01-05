import { createRouter as _createRouter, createWebHistory } from 'vue-router';

//chat gpt below
// import Vue from 'vue';
// import VueRouter from 'vue-router';
// import Home from './views/Home.vue';
// import About from './views/About.vue';

// Vue.use(VueRouter);

//import views 
import HomeView from '../views/HomeView.vue'
// import RegisterUserView from '../views/RegisterUserView.vue'
import PoemView from '../views/PoemView.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue';
import HistoryView from '../views/HistoryView.vue';
import MemoryView from '../views/MemoryView.vue'

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
        component: RegisterView
    },
    {
        path: '/poem/:creationId',
        name: 'poem',
        component: PoemView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
    },
    {
      path: "/memories",
      name: "history",
      component: HistoryView,
    },
    {
      path: '/poem/:creationId',
      name: 'memory',
      component: MemoryView
  },

  ];
  
  export function createRouter () {
    
    return _createRouter({
      history: createWebHistory(),
      routes: routes
    })
  }