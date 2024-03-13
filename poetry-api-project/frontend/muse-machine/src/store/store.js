import { createStore as _createStore } from 'vuex';
import axios from 'axios';

//interesting. These are being auto-created. Like we're passing in blank slates we know we'll need
export function createStore(currentToken, currentUser) {
    return _createStore({
        state: {
            token: currentToken || '',
            user: currentUser || {},
            newCreation: {
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
            },
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
            isLoading: false,

        },
        mutations: {
            NEW_CREATION(state, creation) {
                state.newCreation = creation
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
            },
            FETCH_CREATIONS(state,creations) {
                state.creations = creations;
            },
            SET_LOADING(state,loading){
                state.loading = loading;
            }
        },
           
              
              
        
        modules: {},
        // Strict should not be used in production code. It is used here as a
        // learning aid to warn you if state is modified without using a mutation.
        strict: false
    });
}