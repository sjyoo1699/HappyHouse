<template>
  <v-app>
    <v-sheet class="overflow-hidden"
    >
      <v-app-bar
        absolute
        color="#6A76AB"
        dark
        shrink-on-scroll
        prominent
        src="https://picsum.photos/1920/1080?random"
        fade-img-on-scroll
        scroll-target="#scrolling-techniques-3"
      >
        <template v-slot:img="{ props }">
          <v-img
            v-bind="props"
            gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"
          ></v-img>
        </template>
  
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
  
        <v-toolbar-title>HAPPY HOUSE</v-toolbar-title>
  
        <v-spacer></v-spacer>
  
        <v-btn icon @click="searchGoogle">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
  
        <v-btn icon @click="gitPage">
          <v-icon>mdi-github</v-icon>
        </v-btn>

        <v-btn @click="loginModalToggle = !loginModalToggle"
          v-if="!(isAuthenticated && isProfileLoaded)"
          icon>
          <v-icon>mdi-account-arrow-right</v-icon>
        </v-btn>

        <div v-else>
          <v-app-bar-nav-icon @click="drawer = true">
            <v-avatar :color="ranColor" size="36">
              <span class="white--text headline">{{avatarName(this.getProfile)}}</span>
            </v-avatar>
          </v-app-bar-nav-icon>
        </div>
  
        <template v-slot:extension>
          <v-tabs align-with-title>
            <v-tab to = "/">HOME</v-tab>
            <v-tab to = "/dealList" v-if="isAuthenticated && isProfileLoaded">DEAL LIST</v-tab>
            <v-tab to = "/recommendList" v-if="isAuthenticated && isProfileLoaded">RECOMMENDED LIST</v-tab>
            <v-tab to = "/noticeList" v-if="isAuthenticated && isProfileLoaded">NOTICE</v-tab>
            <v-tab to = "/qna" v-if="isAuthenticated && isProfileLoaded">Q&A</v-tab>
          </v-tabs>
        </template>
      </v-app-bar>
      
      <v-sheet
        id="scrolling-techniques-3"
        class="overflow-y-auto"
        :max-height="clientHeight"
        outliend = false
      >
        <v-container style="height: 280px;"></v-container>
        <v-main>
          <v-container>
            <router-view></router-view>
          </v-container>
        </v-main>
      </v-sheet>
    </v-sheet>

    <v-navigation-drawer 
      v-model="drawer"
      absolute
      temporary
      right
      src = 'https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg'
      dark
    >
        <v-list-item two-line :class="miniVariant && 'px-0'">
          <v-list-item-avatar>
            <v-avatar :color="ranColor">
              <span class="white--text headline">{{avatarName(this.getProfile)}}</span>
            </v-avatar>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{this.getProfile}}</v-list-item-title>
            <v-list-item-subtitle>일반 회원</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
  
        <v-divider></v-divider>
  
        <v-list
          dense
          nav
          class="py-0"
        >
          <v-list-item @click.stop="info">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
  
            <v-list-item-content>
              <v-list-item-title>회원정보</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item @click.stop="logout">
            <v-list-item-icon>
              <v-icon>mdi-account-arrow-left</v-icon>
            </v-list-item-icon>
  
            <v-list-item-content>
              <v-list-item-title>로그아웃</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <template v-slot:append>
          <div class="pa-2">
            <v-btn block @click.stop="notice">NOTICE</v-btn>
          </div>
        </template>
      </v-navigation-drawer>
      
      <v-snackbar
        v-model="loginSuccess"
        timeout="5000"
      >
        <v-icon color="deep-orange darken-3">mdi-home</v-icon>
        {{ getProfile + "님 반갑습니다." }}
        
        <template v-slot:action="{ attrs }">
          <v-btn
            color="red"
            text
            v-bind="attrs"
            @click="loginSuccess = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>

      <v-snackbar
        v-model="logoutSuccess"
        timeout="5000"
      >
        <v-icon color="deep-orange darken-3">mdi-home</v-icon>
         정상적으로 로그아웃 되었습니다.
        
        <template v-slot:action="{ attrs }">
          <v-btn
            color="red"
            text
            v-bind="attrs"
            @click="logoutSuccess = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>

      <v-snackbar
        v-model="alert"
        timeout="5000"
      >
        <v-icon color="deep-orange darken-3">mdi-home</v-icon>
         {{alertMsg}}
        
        <template v-slot:action="{ attrs }">
          <v-btn
            color="red"
            text
            v-bind="attrs"
            @click="alert = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>

        <v-dialog 
          v-model="loginModalToggle"
          max-width=800
          persistent
        >
          <login v-on:closeLoginModal="closeLoginModal"></login>
        </v-dialog>
        <v-dialog 
          v-model="userInfoModalToggle"
          max-width=800
        >
          <user-info-comp 
          v-if="userInfo.id"
          :propId="userInfo.id"
          :propUsername="userInfo.username"
          :propName="userInfo.name"
          :propJoinedAt="userInfo.joinedAt"
          :key="userInfoCompKey"
          v-on:closeUserInfoModal="closeUserInfoModal"
          >
          </user-info-comp>
        </v-dialog>
  </v-app>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
import {AUTH_LOGOUT} from './store/actions/auth'
import Login from "@/components/Login.vue";
import UserInfoComp from "@/components/UserInfoComp.vue";
import http from "@/util/http-common";

export default {
  name: "App",
  data: () => ({
    drawer: false,
    ranColor: "red",
    clientHeight: 1000,
    loginSuccess: false,
    logoutSuccess: false,
    loginModalToggle: false,
    userInfoModalToggle: false,
    userInfo:{},
    alert: false,
    alertMsg: "",
    userInfoCompKey: 0,
  }),
  components: {
    Login,
    UserInfoComp,
  },
  methods: {
    logout: function () {
      this.$store.dispatch(AUTH_LOGOUT).then(() => {
        this.drawer = false;
        const path = '/';
        if (this.$route.path !== path) this.$router.push(path)
      })
    },
    info: function () {
      http.get(`/users/${this.getProfile}`).then(({ data }) => {
        this.userInfo = data;
        console.dir(data);
        this.userInfoCompKey += 1;
        this.userInfoModalToggle=true;
      });
    },
    avatarName : function(name) {
      var tempName = name.split(/(?=[A-Z])/);
      if(tempName.length == 1) return tempName[0].substring(0, 2).toUpperCase();
      else return (tempName[0].substring(0,1) + tempName[1].substring(0,1)).toUpperCase();
    },
    randomColorGenerateor : function() {
      var letters = '0123456789ABCDEF';
      var color = '#';
      for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
      }
      this.ranColor = color;
    },
    handleResize : function() {
      this.clientHeight = document.documentElement.clientHeight;
    },
    closeLoginModal: function() {
      this.loginModalToggle = false;
      this.$router.push('/');
    },
    searchGoogle: function() {
      window.open("https://www.google.com/", "PopupWin", "width=1000,height=900");
    },
    gitPage: function() {
      window.open("https://lab.ssafy.com/edonc700/happyhousefinal", "PopupGit", "width=1000,height=900");
    },
    closeUserInfoModal: function(msg) {
      if(msg != null) {
        this.alertMsg = msg;
        this.alert = true;
      }
      this.userInfoModalToggle = false;
    },
    notice: function() {
      this.$router.push('noticeList');
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated', 'isProfileLoaded','getProfile', 'getRealName']),
    ...mapState({
      authLoading: state => state.auth.status === 'loading'
      ,uname: state => `${state.user.getProfile}`,
    }),
    loading: function () {
      return this.authStatus === 'loading' && !this.isAuthenticated
    }
  },
  mounted() {
    this.randomColorGenerateor();
    this.handleResize();
    window.addEventListener('resize', this.handleResize);
  },
  watch: {
    getProfile : function() {
      if(this.getProfile == "") {
        this.logoutSuccess= true;
      }
      else {
        this.loginSuccess= true;
      }
    }
  }
};
</script>