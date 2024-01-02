<template>
    <div id="poem-background">
        <div id="poem-container">
            <img class="poem-image" v-bind:src="poem.imgUrl">

            <div id="poem-components">
                <h2>{{ poem.title }}</h2>
                <h3>{{ poem.poet }}</h3>
                <div id="poem-element" v-html="formattedPoem"></div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: ['creationId'],
    data() {
        return {
            poem: this.$store.state.poems.find(aPoem => aPoem.creationId == this.creationId)
        }
    },
    computed: {
        formattedPoem() {
            const poemText = this.poem.poem;

            // Replace \r\n with <br> to preserve line breaks in HTML //mess with this.
            return poemText.replace(/\r\n/g, '<br>');
        },
    }
}


</script>

<style scoped>
#poem-background {
    background-color: rgb(0, 0, 0); 
    background-image: url('../assets/images/typewriter-1.png');
    background-position: right top 50px;
    background-repeat: no-repeat;
    /* background-position: top; */
  
    padding: 2%;
    
}

#poem-container {
    padding-top: 5%;
    background-color: rgb(249, 255, 231);
    width:50%;
    margin: auto;
    padding-top: 5%;
    padding-bottom: 5%;
    
    

}

#poem-components {
    
    margin-left: 15%;
    margin-top: 5rem;
}
#poem-element{
    /* width: 50%; */
}

.poem-image {
    margin: auto;
    display: block;
    width: 512px;
    height: 512px;
    filter: drop-shadow(3px 8px 7px)
}

</style>