<template>
    <div id="website">
      <the-header />
      <main>
        <router-view />
      </main>
    </div>
</template>


<script>
import TheHeader from './components/TheHeader.vue'


export default {
  components: {
    TheHeader,

  }
}

</script>







<style>
@font-face {
  font-family: specialElite;
  src: url(./assets/fonts/SpecialElite-Regular.ttf);
}

@font-face {
  font-family: fira;
  src: url(./assets/fonts/Fira_Code/static/FiraCode-Regular.ttf);
}

body, input, button{
  font-family: specialElite, fira, 'Josefin Sans', sans-serif;
}
body{
  margin: 0;
  min-width: 360px;
}



.page-title {
  font-size: 3em;
  margin-top: 10%;
  margin-bottom: 10%;
}

.form-control {
  display: block;
  width: 70%;
  height: 2rem;
  border-radius: 15px 225px 255px 15px 15px 255px 225px 15px;
  border-style: solid;
  border-width: 2px;
  box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
  box-sizing: border-box;
  color: #41403e;
  outline: none;
  text-decoration: none;
  transition: all 235ms ease-in-out;
  border-bottom-left-radius: 15px 255px;
  border-bottom-right-radius: 225px 15px;
  border-top-left-radius: 255px 15px;
  border-top-right-radius: 15px 225px;
  font-size: 1em;
  padding: 5px;
}
label {
  font-size: 1.25em;
}
.user-form-container{
  width: 45%;
  margin: auto;
}
@media screen and (max-width: 650px){
  .page-title{
    font-size: 2rem;
  }
  .user-form-container{
    width: 75%;
  }
  .form-control{
    width: 100%;
  }
  
}



</style>
