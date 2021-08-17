import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        fileMsg: ""
    },
    mutations: {
        setFile(state, name) {
            state.fileMsg = name
        }
    },
    actions: {
        setFileMsg({commit, state}, fileMsg) {
            commit("setFileMsg", fileMsg)
        }
    },
    modules: {},
    getters: {
        getFileMsg(state) {
            return state.fileMsg
        }
    }
})
