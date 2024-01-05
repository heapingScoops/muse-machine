<template>
    <div>
        <the-poem v-bind:creation=selectedCreation />
    </div>

</template>

<script>
import ThePoem from '../components/ThePoem.vue'

export default{
    components: {
        ThePoem
    },
    computed: {
        creationId(){
            return this.$route.params.creationId;
        },
        selectedCreation(){
            return this.$store.state.creations.find( creation => {
                return creation.creationId == this.creationId
            })
        }
    },
    created (){
        this.created = true;
        console.log(this.creation)

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