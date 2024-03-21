<template>
    <div id="black">
        <div v-if=transitionVis id="trans-container-container">
            <span class="trans" id="trans-container" v-html=transitionText></span>
            <span id="blinker" class="trans"> </span>
        </div>

        <div id="home" v-bind:class="{ fadeToBlack: clicked }">
            <div class="welcome">
                <div class="gonna-drift">
                    The
                </div>
                <div class="gonna-drift">
                    Muse
                </div>
                <div class="gonna-drift">
                    Machine
                </div>
            </div>

            <div v-on:click="generateRandomPoem" id="button-55" v-if="this.$store.state.token != ''" class="gonna-drift"
                role="button">start the machine...</div>
            <router-link v-bind:to="{ name: 'register' }" v-if="this.$store.state.token == ''" class="register-button">
                <div id="button-55" role="button">Who are you?</div>
            </router-link>
        </div>
    </div>
</template>


<script>
import apiService from '../services/apiService.js'

export default {
    data() {
        return {
            poem: {},
            clicked: false,
            transitionText: "",
            transitionVis: false
        }
    },
    methods: {
        async generateRandomPoem() {
            this.startDriftingOut();

            //we're passing in the text/html along with the type speed, and spacing out when the lines should appear
            setTimeout(() => this.typeLetters('The machine<br>', 200), 5000);
            setTimeout(() => this.typeLetters('slowly groans', 210), 8000);
            setTimeout(() => this.typeLetters(' to life', 300), 11000);

            try{
                //call service that (a) grabs poem, (b) grabs img url, (c) returns a poem object
                const newCreation = await apiService.getRandomPoem(this.$store.state.token);

                //add poem to store
                this.$store.commit('NEW_CREATION', newCreation);

                //push to poemView
                this.$router.push({ name: 'poem', params: { creationId: newCreation.creationId } })
            }
            catch{
                alert("Whoops.")
                alert("My tongue got crossed,")
                alert("I mean: my wires got tied...")
                alert("(also my API gateway closed after 29 seconds.)")
                alert(":/")
                alert("Try checking your memories...")
                this.$router.push({ name: 'history'})
            }
            
        },
        startDriftingOut() {
            const words = this.$el.querySelectorAll('.gonna-drift');
            this.clicked = true;


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

        },
        typeLetters(transStatement, typeSpeed) {
            this.transitionVis = true;

            let index = 0;

            const typeNextLetter = () => {
                if (index < transStatement.length) {
                    this.transitionText += transStatement.charAt(index);
                    index++;
                    setTimeout(typeNextLetter, typeSpeed); // Adjust the typing speed
                }
            };

            typeNextLetter();

        }
    }
}
</script>



<style>
#trans-container-container {
    margin-left: 3%;
    padding-top: 3%;
    /* border-right: .15em solid rgb(249, 255, 231); */
    /* The typewriter cursor */
    /* animation: blink-caret .75s step-end infinite; */
}
#trans-container {
    white-space: nowrap;
    overflow: hidden;
    /* border-right: .15em solid rgb(249, 255, 231); */
    /* The typewriter cursor */
    /* animation: blink-caret .75s step-end infinite; */
}

#blinker {
    border-left: .15em solid rgb(249, 255, 231);
    /* The typewriter cursor */
    animation: blink-caret .75s step-end infinite;
}

@keyframes blink-caret {

    from,
    to {
        border-color: transparent
    }

    50% {
        border-color: rgb(249, 255, 231);
    }
}

.trans {
    font-size: 5em;
    color: rgb(249, 255, 231);
}


.register-button {
    text-decoration: none;
}

.fadeToBlack {
    animation-name: fadeToBlack;
    animation-duration: 7s;
    animation-fill-mode: forwards;
    animation-timing-function: linear;
    animation-iteration-count: 1;
}

@keyframes fadeToBlack {
    to {
        background-color: black;
        opacity: 0;
    }
}

.driftOut {
    animation-name: driftOut;
    animation-duration: var(--duration, 10s);
    animation-timing-function: ease-in-out;
    animation-iteration-count: 1;
}

.driftOutAlt {
    animation-name: driftOutAlt;
    animation-duration: var(--duration, 12s);
    animation-timing-function: ease-in-out cubic-bezier(0.68, -0.55, 0.265, 1.55);
    animation-iteration-count: 1;
}

@keyframes driftOut {
    to {
        transform: translate(calc(100vw * (var(--dirX))),
                calc(100vh * (var(--dirY))));
        opacity: 0;
        font-size: .6rem;
    }
}

@keyframes driftOutAlt {
    to {
        transform: translate(calc(100vw * (var(--dirX))),
                calc(100vh * (var(--dirY))));
        opacity: 0;
        /* Gradual fade out */
        font-size: 3.8rem;

    }
}

.welcome {
    font-size: 10em;
    color: rgb(249, 255, 231);
}

@media screen and (max-width: 865px) {
    .welcome {
        font-size: 9rem;
    }
    .trans{
        font-size: 3em;
    }
}

@media screen and (max-width: 650px) {
    .welcome {
        font-size: 6rem;
    }
    .trans{
        font-size: 2.5em;
    }
}

@media screen and (max-width: 450px) {
    .welcome {
        font-size: 5rem;
    }
    .trans{
        font-size: 2em;
    }
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

.button-100 {
    margin: auto;
    align-self: center;
    background-color: rgb(249, 255, 231);
    background-image: none;
    border-radius: 15px 225px 255px 15px 15px 255px 225px 15px;
    border-style: solid;
    border-width: 2px;
    box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
    box-sizing: border-box;
    color: #41403e;
    cursor: pointer;
    font-size: 1.5em;
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

.button-100:hover {
    box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
    transform: translate3d(0, 2px, 0);
}

.button-100:focus {
    box-shadow: rgba(0, 0, 0, .3) 2px 8px 4px -6px;
}
</style>