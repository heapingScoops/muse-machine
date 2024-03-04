<template>
    <div>
        <nav id="navBar">

            <div class="header-left">
                <router-link v-bind:to="{ name: 'history' }" v-if="this.$store.state.token != ''" class="nav-option">
                    Memories
                </router-link>
            </div>

            <span id="nav-space"></span>


            <router-link v-bind:to="{ name: 'home' }" id="home-link" class="nav-option">
                <img id="logo" src="../assets/images/muse-machine-logo.png">
            </router-link>

            <span id="nav-space"></span>

            <div class="header-right">

                <!-- <span> &nbsp </span> -->
                <router-link v-bind:to="{ name: 'register' }" v-if="this.$store.state.token == ''"
                    class="nav-option">Register</router-link>
                <!-- <span> &nbsp </span>
                <span> &nbsp </span>
                <span> &nbsp </span> -->
                <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''"
                    class="nav-option">Logout</router-link>
                <!-- <span> &nbsp </span> -->
                <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''"
                    class="nav-option">Login</router-link>
            </div>
        </nav>

    </div>
</template>

<script>
import RegisterUser from './RegisterUser.vue'
export default {
    components: {
        RegisterUser
    }
}

</script>

<style scoped>
#nav-space {
    flex-grow: 1;
}

#navBar {
    display: flex;
    color: #fff;
    align-items: center;
    
    justify-content: space-between;
    background-color: black;
    border-bottom: rgb(52, 50, 40) double 4px;
    padding: 2% 5%;
}
.header-left{
    width: 40%;
    display: flex;
    justify-content: flex-start;
}
.header-right{
    width: 40%;
    display: flex;
    justify-content: flex-end;
}
.header-right a{
    padding: 5%;
}
.nav-option {
    font-size: 2em;
}
#logo {
    filter: invert(1);
    width: 125px;
    height: auto;
    margin: 0px 20px;
}
a {
    color: #fff
}
a:hover {
    color: red;
}
a:after {
    color: rgb(214, 232, 78)
}

@media screen and (max-width: 650px){
  .nav-option{
    font-size: 1.5rem;
  }
  .header-right{
   flex-wrap: wrap;
   align-content: center;
  }

}

</style>