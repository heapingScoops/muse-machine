import axios from 'axios';
const BASE_URL='https://3121maymp2.execute-api.us-east-2.amazonaws.com/Prod/'
// const BASE_URL='http://localhost:8080/'



export default {

    fetchCreations(token){
        // return axios.get('http://localhost:8080/creations')
        return axios.get('https://3121maymp2.execute-api.us-east-2.amazonaws.com/Prod/creations')
    },

    async getRandomPoem(token) {     
        //searches for a poem until it finds one that doesn't have fucked up line breaks
        //NOTE: I should have done this checking on the back-end
        let randomId;
        let myPoem = {};
        while(true){
            randomId = Math.floor(Math.random() * 13000);
            await axios.get(`${BASE_URL}poems/${randomId}`, {headers:{'Authorization': `Bearer ${token}`}})
                .then(response => myPoem = response.data)
                .catch(error => {
                    console.error('Error:', error);
                });
            if(formValidator(myPoem.poem)){
                break;
            }
        }

        let newCreation = {};
        //sends the approved poemId to backend to generate an image
        await axios.get(BASE_URL + 'poems/image/' + randomId, {headers:{ 'Authorization': `Bearer ${token}`}, timeout: 45000}).then(response => {
            
            newCreation = response.data;
        })
        .catch(error => {
            console.error('Error:', error);
        });

        return newCreation;
    }
    
}

//counts the number of line breaks; if it's below fifteen, it will get a new goddamn poem.
function formValidator(poemText){
    let count = 0;
    let position = poemText.indexOf(`\n`);
    while (position !== -1) {
        count++;
        position = poemText.indexOf(`\n`, position + 1);
    }
    if(count < 15){
        return false;
    }
    return true;
    
}



//WHen you press the button:
//*A horrid revving sounds* *The machine groans to life*
//*A horrid revving sounds* *I slowly groan to life*
