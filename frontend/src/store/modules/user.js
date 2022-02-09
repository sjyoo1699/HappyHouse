import { USER_REQUEST, USER_ERROR, USER_SUCCESS } from "../actions/user";
import apiCall2 from "../../util/api2";
import Vue from "vue";
import { AUTH_LOGOUT } from "../actions/auth";

const state = {
  status: "",
  profilename: localStorage.getItem("profilename") || "" ,
  realname: localStorage.getItem("realname") || ""
};

const getters = {
  getProfile: state => state.profilename,
  isProfileLoaded: state => !!state.profilename,
  getRealName: state=> state.realname
};

const actions = {
  [USER_REQUEST]: ({ commit, dispatch }, args) => {
    apiCall2(args.usernameOrEmail)
      .then(resp => {
        commit(USER_SUCCESS, resp);
      })
      .catch(() => {
        commit(USER_ERROR);
        dispatch(AUTH_LOGOUT);
      });
  }
};

const mutations = {
  [USER_REQUEST]: state => {
    state.status = "loading";
  },
  [USER_SUCCESS]: (state, resp) => {
    state.status = "success";
    localStorage.setItem("profilename", resp.username);
    localStorage.setItem("realname", resp.name);
    Vue.set(state, "profilename", resp.username);
    Vue.set(state, "realname", resp.name);
  },
  [USER_ERROR]: state => {
    state.status = "error";
  },
  [AUTH_LOGOUT]: state => {
    state.profilename = "";
    state.realname = "";
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
