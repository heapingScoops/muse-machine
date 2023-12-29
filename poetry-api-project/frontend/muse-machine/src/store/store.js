import { createStore as _createStore } from 'vuex';
import axios from 'axios';

//interesting. These are being auto-created. Like we're passing in blank slates we know we'll need
export function createStore(currentToken, currentUser) {
    return _createStore({
        state: {
            token: currentToken || '',
            user: currentUser || {},
            nextCreationId: 2,
            poems: [
                {
                    creationId: 0,
                    poemId: 0,
                    poem: '',
                    poet: '',
                    tags: [],
                    title: '',
                    imgUrl: ''
                },
                {
                    creationId: 1,
                    poemId: 0,
                    poem: '',
                    poet: '',
                    tags: [],
                    title: '',
                    imgUrl: ''
                }




            ]

        },
        mutations: {
            NEW_POEM(state, poem) {
                poem.creationId = this.state.nextCreationId++;
                state.poems.unshift(poem);
            },
            SET_AUTH_TOKEN(state, token) {
                state.token = token;
                localStorage.setItem('token', token);
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            },
            SET_USER(state, user) {
                state.user = user;
                localStorage.setItem('user', JSON.stringify(user));
            },
            LOGOUT(state) {
                localStorage.removeItem('token');
                localStorage.removeItem('user');
                state.token = '';
                state.user = {};
                axios.defaults.headers.common = {};
            }
        },
           
              
              
        
        modules: {},
        // Strict should not be used in production code. It is used here as a
        // learning aid to warn you if state is modified without using a mutation.
        strict: false
    });
}