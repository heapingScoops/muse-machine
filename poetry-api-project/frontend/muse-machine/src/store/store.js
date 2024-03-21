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
            logoutTimerId: null

        },
        mutations: {
            NEW_CREATION(state, creation) {
                state.newCreation = creation
            },
            SET_AUTH_TOKEN(state, token) {
                state.token = token;
                localStorage.setItem('token', token);
                axios.defaults.headers.common['Authorization'] = `Bearer ${token};`
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
            FETCH_CREATIONS(state, creations) {
                state.creations = creations;
            },
            CLEAR_LOGOUT_TIMER(state) {
                //deletes the setInterval function from being a thing
                clearTimeout(state.logoutTimerId);
                state.logoutTimerId = null;
            },
            SET_LOGOUT_TIMER_ID(state, id){
                state.logoutTimerId = id;
            },
            SET_LOADING(state, loading) {
                state.loading = loading;
            }
        },
        //actions are typically used for aysnc operations, and encapsulating reusable things. 
        actions: {
            //actions automatically take in a context object with lots of stuff. {commit} and {dispatch} are 2 of the stuffs, and this is object destructuring
            setAuthAction({ commit, dispatch }, token) {
                //call mutation above
                commit('SET_AUTH_TOKEN', token);
                //call action below to deal with the async fuckery of the scheduled logout
                dispatch('scheduleLogout', token);

            },
            scheduleLogout({ commit }, token) {
                //calls mutation which clears logout timerId, which, in the case of a logout, will stop the setTimeout below
                commit('CLEAR_LOGOUT_TIMER');

                //parse the token Payload
                const tokenPayload = JSON.parse(atob(token.split('.')[1]));
                //extract the exp time (Unix time stamp in seconds, converted to milliseconds) 
                const expiryTime = tokenPayload.exp * 1000;
                //Difference between exp time (ms) and now (already in ms)
                const remainingTime = expiryTime - Date.now();

                //after remainingTime, 'LOGOUT' mutation will be called.
                const timerId = setTimeout(() => {
                    commit('LOGOUT')
                }, remainingTime)

                //saves the timerId in state
                commit('SET_LOGOUT_TIMER_ID', timerId)
            }


        },




        modules: {},
        // Strict should not be used in production code. It is used here as a
        // learning aid to warn you if state is modified without using a mutation.
        strict: false
    });
}