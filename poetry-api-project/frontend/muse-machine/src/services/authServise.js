import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080/'
});

export default{
    //function signature & params
    //strictly the web service call with 1-url, 2-input params if a put or post
    
    register(user){
        return http.post('/register', user)
    },


    login(user){
        return http.post('/login', user)
    }
}