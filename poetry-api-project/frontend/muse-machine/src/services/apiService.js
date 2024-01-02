import axios from 'axios';




export default {
    async getRandomPoem(token) {
        console.log(token);
        
        
        let randomId;

        let myPoem = {};
        while(true){
            randomId = Math.floor(Math.random() * 13000);
            await axios.get(`http://localhost:8080/poems/${randomId}`, {headers:{ 'Authorization': `Bearer ${token}`}})
                .then(response => myPoem = response.data)
                .catch(error => {
                    console.error('Error:', error);
                });
            if(formValidator(myPoem.poem)){
                break;
            }

        }
        

        console.table(myPoem)

        //Placeholder image
        //myPoem.imgUrl = '../src/assets/images/typewriter-3.png';

        // DALLE call to get URL
        await axios.get('http://localhost:8080/image/poems/' + randomId, {headers:{ 'Authorization': `Bearer ${token}`}}).then(response => {
            let myDalleObj = response.data; 
            myPoem.imgUrl = response.data;
        })
        .catch(error => {
            console.error('Error:', error);
        });

        

        return myPoem;

    }
    
}

//the below function counts the number of line breaks; if it's below fifteen, it will just get a new goddamn poem.
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



// function checkFormatting(){
    
//     const poemArray = myPoem.poem.toCharArray();

    
//     if(myPoem.poem)
// }