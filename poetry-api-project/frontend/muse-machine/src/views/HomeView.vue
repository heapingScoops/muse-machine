<template>
    <div id="black" >
        <div id="home" v-bind:class="{fadeToBlack: clicked}">
            <div class="welcome">
                <span class="gonna-drift">
                    The
                </span>
                <span class="gonna-drift">
                    Muse
                </span>
                <span class="gonna-drift">
                    Machine
                </span>
            </div>
            <div v-on:click="generateRandomPoem" id="button-55"  class="gonna-drift" role="button">start the machine...</div>
        </div>
    </div>
</template>


<script>
import apiService from '../services/apiService.js'

export default {
    data() {
        return {
            poem: {},
            clicked: false
        }
    },
    methods: {
        async generateRandomPoem() {
            this.startDriftingOut();
            
            //call service that (a) grabs poem, (b) grabs img url, (c) returns that object
            this.poem = await apiService.getRandomPoem(this.$store.state.token);

            

            //add poem to store
            this.$store.commit('NEW_POEM', this.poem);



            //push to poemView
            

            // setTimeout( () =>
            // this.$router.push({ name: 'poem', params: { creationId: this.poem.creationId } }), 12000)

            this.$router.push({ name: 'poem', params: { creationId: this.poem.creationId } })


            //reset poem
            //this.poem = {}


        },
        startDriftingOut() {
            const words = this.$el.querySelectorAll('.gonna-drift');
            this.clicked = true;

            console.log(words)
            for (let i = 0; i < words.length; i++) {
                words[i].style.setProperty('--dirX', Math.random() * 1.3 - 1); // Random value between -1 and 1
                words[i].style.setProperty('--dirY', Math.random() * 2.1 - 1);
                if (i % 2 === 0) {
                    words[i].classList.add('driftOut');
                }
                else {
                    words[i].classList.add('driftOutAlt')
                }

            }
            
        }
    }
}

</script>



<style>


.fadeToBlack {
    animation-name: fadeToBlack;
    animation-duration: 7s; /* Adjust the duration as needed */
    animation-fill-mode: forwards;
    animation-timing-function: linear;
    animation-iteration-count: 1;
}
@keyframes fadeToBlack {
   

    to {
        background-color: black; /* Fades to black */
        opacity: 0;
    }
}
/* .gonna-drift{
    display: inline-block;
} */
.driftOut {
    animation-name: driftOut;
    animation-duration: var(--duration, 10s); /* Adjust the duration as needed */
    animation-timing-function: ease-in-out;
    animation-iteration-count: 1;
}
.driftOutAlt {
    animation-name: driftOutAlt;
    animation-duration: var(--duration, 7s); /* Adjust the duration as needed */
    animation-timing-function: ease-in-out cubic-bezier(0.68, -0.55, 0.265, 1.55);
    animation-iteration-count: 1;
}

@keyframes driftOut {
    to {
        transform: translate(
            calc(100vw * (var(--dirX))), /* Random path in X direction */
            calc(100vh * (var(--dirY)))  /* Random path in Y direction */
        );
        opacity: 0; /* Gradual fade out */
        font-size: .6rem;
        
    }
}

@keyframes driftOutAlt {
    to {
        transform: translate(
            calc(100vw * (var(--dirX))), 
            calc(100vh * (var(--dirY))) 
        );
        opacity: 0; /* Gradual fade out */
        font-size: 3.8rem;
        
    }
}


.welcome {
    font-size: 10em;
    color: rgb(249, 255, 231);
    width: 30%;

}


#home {

    background-image: url('../assets/images/typewriter-1.png');
    background-position: right;
    background-repeat: no-repeat;
    height: 1000px;
    padding: 2%;

}

#black {
    background-color: black;
}


#button-55 {
    margin: auto;
    width: 15rem;
    align-self: center;
    background-color: rgb(249, 255, 231);
    background-image: none;
    background-position: 0 90%;
    background-repeat: repeat no-repeat;
    background-size: 4px 3px;
    border-radius: 15px 225px 255px 15px 15px 255px 225px 15px;
    border-style: solid;
    border-width: 2px;
    box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
    box-sizing: border-box;
    color: #41403e;
    cursor: pointer;
    font-size: 2em;
    outline: none;
    padding: .75rem;
    text-decoration: none;
    transition: all 235ms ease-in-out;
    border-bottom-left-radius: 15px 255px;
    border-bottom-right-radius: 225px 15px;
    border-top-left-radius: 255px 15px;
    border-top-right-radius: 15px 225px;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
}

#button-55:hover {
    box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
    transform: translate3d(0, 2px, 0);
}


#button-55:focus {
    box-shadow: rgba(0, 0, 0, .3) 2px 8px 4px -6px;
}
</style>