<template>
    <div id="poem-view" v-bind:class="{fadeIn: created}">
        
        <the-poem v-bind:creation=newCreation />
    </div>

</template>

<script>
import ThePoem from '../components/ThePoem.vue'

export default{
    components: {
        ThePoem
    },
    data(){
        return{
            created: false,
            // newCreation: 
        }
    },
    computed: {
        creationId(){
            return this.$route.params.creationId;
        },
        newCreation(){
            return this.$store.state.newCreation;
        }
    },
    created (){
        this.created = true;

    }

}

</script>

<style>
.fadeIn {
    animation-name: fadeIn;
    animation-duration: 2s; /* Adjust the duration as needed */
    animation-fill-mode: forwards;
    animation-timing-function: linear;
    animation-iteration-count: 1;
}
@keyframes fadeIn {
   
    from{
        background-color: black;
        opacity: 0
    }
    to {
        background-color:  rgb(249, 255, 231) ;
        opacity: 1;
    }
}



</style>