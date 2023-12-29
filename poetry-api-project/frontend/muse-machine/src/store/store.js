import { createStore as _createStore } from 'vuex';

export function createStore() {
    return _createStore({
        state: {
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
            }
        },
        actions: {},
        modules: {},
        // Strict should not be used in production code. It is used here as a
        // learning aid to warn you if state is modified without using a mutation.
        strict: false
    });
}