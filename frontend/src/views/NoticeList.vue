<template>
  <div align = "center">
    <h1 class="display-2 font-weight-bold mb-3">NOTICE</h1>
      <v-sheet color="#6A76AB" elevation="24" rounded="pill" style="color:white;" height="25"></v-sheet>
    <br>
    <div v-if="items.length">
      <v-simple-table >
        <template v-slot:default>
          <colgroup>
            <col :style="{ width: '5%' }" />
            <col :style="{ width: '10%' }" />
            <col :style="{ width: '50%' }" />
            <col :style="{ width: '25%' }" />
          </colgroup>
          <thead>
            <tr>
              <th class="text-left">번호</th>
              <th class="text-left">아이디</th>
              <th class="text-left">제목</th>
              <th class="text-left">날짜</th>
            </tr>
          </thead>
          <tbody>
            <notice-row
              v-for="(item, index) in items"
              :key="`${index}_items`"
              :articleno="item.articleno"
              :id="item.id"
              :subject="item.subject"
              :regtime="item.regtime"
              v-on:showDetail="showDetail"
            />
          </tbody>
        </template>
      </v-simple-table>
    </div>
    <div v-else>글이 없습니다.</div>
    <div class="text-right">
      <v-btn @click="movePage">등록</v-btn>
    </div>
    <v-snackbar
        v-model="alert"
        timeout="5000"
      >
        <v-icon color="deep-orange darken-3">mdi-home</v-icon>
        {{ alertMsg }}
        
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
  </div>
</template>

<script>
import http from "@/util/http-common";
import NoticeRow from "@/components/NoticeRow.vue";
export default {
  name: "NoticeList",
  components: {
    NoticeRow
  },
  data: function() {
    return {
      items: [],
      alert: false,
      alertMsg : "",
    };
  },
  created() {
    http
      .get("/notice/list")
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        this.alertMsg = "에러가 발생했습니다.";
        this.alert = true;
      });
  },
  methods: {
    movePage() {
      this.$router.push("/NoticeCreate");
    },
    showDetail(articleno) {
      this.$router.push(`/NoticeRead?articleno=${articleno}`);
    },
  }
};
</script>

<style scoped>
th {
  background-color:#eeeeee; 
  color:#3e5fba;
}
</style>
