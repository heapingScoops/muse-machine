<template>
    <div>
        <nav id="navBar">
            <img id="logo" src="../assets/images/muse-machine-logo.png">
            <router-link v-bind:to="{name: 'home'}" id="home-link" class="nav-option">Home</router-link>
            <span id="nav-space"></span>
            <router-link v-bind:to="{name: 'register-user'}" class="nav-option">Register</router-link>
        </nav>

    </div>

</template>

<script>
import RegisterUser from './RegisterUser.vue'
export default{
    components: {
        RegisterUser
    }
}

</script>

<style scoped>
#nav-space{
    flex-grow: 1;
}
#navBar{
    display: flex;
    /* background-color: rgb(249, 255, 231); */
    color: rgb(249, 255, 231);
    align-items: center;
    background-color:black;
    border-bottom:  rgb(249, 255, 231) solid 2px;
    padding : 20px 2% 20px 0px;
}
.nav-option{
    font-size:2em;
}
#logo{
    filter: invert(1);
    width: 100px;
    height: auto;
    margin: 0px 20px;
}
a{ 
    color:  rgb(249, 255, 231)
}
a:hover{
    color: red;
}
a:after{
    color: rgb(214, 232, 78)
}


</style>