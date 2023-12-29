<template>
    <div>
        <form v-on:submit.prevent="createNewUser()" id="new-user">
            
            <label for="username">Username</label>
            <input name="username" v-model="newUser.username">
            <br>
            <label for="password">Password</label>
            <input name="password" v-model="newUser.password">
            <br>
            <button type="submit">Register with the machine</button>
        </form>

    </div>
</template>

<script>

export default{
    data(){
        return{
            newUser: {
                username: '',
                password: ''
            }
        }
        
    },
    methods: {
        createNewUser(){
            //Send the info to the place
        }
    }

}
</script>

<style>
</style>