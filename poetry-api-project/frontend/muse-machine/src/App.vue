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

body {
  font-family: specialElite, fira, 'Josefin Sans', sans-serif;
}

</style>
