<template>
    <div id="poem-container">
        <img class="poem-image" v-bind:src="poem.imgUrl">

        <div id="poem-components">
            <h2>{{ poem.title }}</h2>
            <h3>{{ poem.poet }}</h3>
            <div id="poem-element" v-html="formattedPoem"></div>
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
#poem-container {
    background-color: rgb(249, 255, 231);


}
#poem-components{
    margin-left: 20%;
    margin-top: 5rem;   
}

.poem-image {
    margin: auto;
    display: block;
    width: 512px;
    height: 512px;
}
</style>