import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        fileName: ""
    },
    mutations: {
        setFile(state, name) {
            state.fileName = name
        }
    },
    actions: {
        setFileName({commit, state}, fileName) {
            commit("setFileName", fileName)
        }
    },
    modules: {},
    getters: {
        getFileName(state) {
            return state.fileName
        }
    }
})
