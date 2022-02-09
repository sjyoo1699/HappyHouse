<template>
  <header>
    <nav>
      <ul>
        <li>
          <router-link to="/">홈</router-link>
        </li>
        <li v-if="isAuthenticated && isProfileLoaded">
          <router-link to="/noticeList">공지사항</router-link>
        </li>
        <li v-if="isAuthenticated && isProfileLoaded">
          <router-link to="/dealList">실거래가 리스트</router-link>
        </li>
        <li v-if="isAuthenticated && isProfileLoaded">
          <router-link to="/qna">QnA</router-link>
        </li>
        <li v-if="!(isAuthenticated && isProfileLoaded)">
          <router-link to="/login">로그인</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  name: "AppHeader",

  computed: {
    ...mapGetters(["isAuthenticated", "isProfileLoaded", "getProfile"]),
    ...mapState({
      authLoading: state => state.auth.status === "loading",
      uname: state => `${state.user.getProfile}`
    }),
    loading: function() {
      return this.authStatus === "loading" && !this.isAuthenticated;
    }
  }
};
</script>
