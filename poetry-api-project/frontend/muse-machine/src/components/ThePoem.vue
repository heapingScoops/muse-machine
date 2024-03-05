<template>
    <div id="poem-background">
        <div id="poem-container">
            <img class="poem-image" v-bind:src="creation.imageUrl">

            <div id="poem-components">
                <h2>{{ creation.poem.title }}</h2>
                <h3>{{ creation.poem.poet }}</h3>
                <div id="poem-element" v-html="formattedPoem"></div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: ['creation'],

    computed: {
        formattedPoem() {
            const poemText = this.creation.poem.poem;
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
    padding: 2%;

}

#poem-container {
    padding-top: 5%;
    background-color: rgb(250, 252, 243);
    width: 50%;
    /* min-width: 350px; */
    margin: auto;
    padding: 5% 2%;
    border-radius: 15px 225px 255px 15px 15px 255px 225px 15px;
    border-style: ridge;
    border-width: 3px;
    border-color: rgb(200, 172, 82);
    box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
    box-sizing: border-box;
    color: #1f1f1f;
    text-decoration: none;
    transition: all 235ms ease-in-out;
    border-bottom-left-radius: 15px 255px;
    border-bottom-right-radius: 225px 15px;
    border-top-left-radius: 255px 15px;
    border-top-right-radius: 15px 225px;
}

#poem-components {
    width: 80%;
    margin: auto;
    padding-top: 5%;
}

.poem-image {
    margin: auto;
    display: block;
    /* width: 512px;
    height: 512px; */
    width: 80%;

    filter: drop-shadow(3px 8px 7px)
}

@media screen and (max-width: 650px){
  #poem-container{
    width: 80%;
  }
  

}

</style>