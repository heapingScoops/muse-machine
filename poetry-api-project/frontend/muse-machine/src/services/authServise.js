import axios from '.node_modules/axios';

const http = axios.create({
    // baseURL: 'http://localhost:8080/'
    baseURL: 'https://3121maymp2.execute-api.us-east-2.amazonaws.com/Prod/'
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