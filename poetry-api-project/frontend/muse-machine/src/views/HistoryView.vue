<template>
    <div id="history-view">
        <h1 class="page-title memories-title">Memories...</h1>

        <table id="history-table ">
            <thead class="thead">
                <tr>
                    <th class="birthday-column" scope="col">Birth Day</th>
                    <th class="img-column" scope="col"></th>
                    <th class="poet-column" scope="col">poet</th>
                    <th class="title-column" scope="col">Title</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(creation, index) in this.$store.state.creations" :key=index>
                    <td>
                        {{ creation.creationDate }}
                    </td>
                    <td class="image-cell">
                        <img class="list-thumbnail" :src="creation.imageUrl">
                    </td>
                    <td>
                        {{ creation.poem.poet }}
                    </td>
                    
                    <td>
                        <router-link :to="{name: 'memory', params: { creationId: creation.creationId }}">
                            
                        {{ creation.poem.title }}
                        
                        </router-link>
                    </td>
                    
                </tr>

            </tbody>


        </table>


    </div>

    <!-- /* btw call this memories */ -->
</template>

<script>
import apiService from '../services/apiService';

export default {
    data() {
        return {
            creations: [
                {
                    creationId: 0,
                    userId: 0,
                    imageUrl: '',
                    creationDate: '',
                    poem: {
                        poem: '',
                        poemId: 0,
                        poet: '',
                        tags: '',
                        title: '',
                    }
                }
            ],
        }
    },
    mounted() {
        apiService.fetchCreations(this.$store.state.token)
            .then(response => {
                //this.creations = response.data;
                this.$store.commit('FETCH_CREATIONS', response.data)
            })
        

    }
}

</script>

<style scoped>
#history-view{
    width: 75%;
    margin: auto;
    
}
.memories-title{
    margin: 5% 0;
}

th {
    font-size: 1.5em;
}
.birthday-column{
    width: 10%;
}
.image-cell{
    text-align: center;
}

.list-thumbnail {
    width: 100px;
}

td {
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

@media screen and (max-width: 650px){
  th{
    font-size: 1.25rem;
  }
  #history-view{
    width: 85%;
  }
  .list-thumbnail{
    width: 65px;
  }
}

</style>