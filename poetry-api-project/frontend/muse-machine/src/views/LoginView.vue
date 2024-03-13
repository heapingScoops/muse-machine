<template>
  
    <div class="user-form-container">
      <form v-on:submit.prevent="login">
        <h1 class="page-title">Who are you?</h1>
        <div role="alert" v-if="invalidCredentials">
          Invalid username or password
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-group">
          <label for="username">Username</label>
          <input class="form-control" type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input class="form-control" type="password" id="password" v-model="user.password" required />
        </div>
        <button type="submit"  class="button-100">Sign in</button>
        <p>
          <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
        </p>
      </form>
    </div>
  
</template>

<script>
import authService from '../services/authServise.js';

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push({ name: "home" });
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#sign-in-btn {
  margin: 2rem 0rem 1rem 0rem;
}
.form-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>