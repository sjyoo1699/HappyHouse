<template>
  <div id="main_wrap">
    <div id="middle_wrap" v-if="visablelogin">
      <div id="content_wrap">
        <div
        >
          <div id="login_wrap">
            <form class="login" @submit.prevent="login">
              <v-simple-table class="content_table">
                <colgroup>
                  <col style="width:30%;" />
                  <col style="width:70%;" />
                </colgroup>
                <tbody>
                  <tr>
                    <td colspan="2"><h2>HappyHouse 로그인</h2></td>
                  </tr>
                  <tr>
                    <th style="background-color:#eeeeee; color:#3e5fba;">
                      아이디
                    </th>
                    <td>
                      &nbsp;
                      <input
                        type="text"
                        id="_userid"
                        name="id"
                        value
                        data-msg="ID를"
                        title="아이디"
                        required
                        v-model="usernameOrEmail"
                        placeholder="아이디를 입력하세요."
                      />
                    </td>
                  </tr>
                  <tr>
                    <th style="background-color:#eeeeee; color:#3e5fba;">
                      패스워드
                    </th>
                    <td>
                      &nbsp;
                      <input
                        type="password"
                        id="_pwd"
                        name="pwd"
                        value
                        v-model="password"
                        required
                        placeholder="패스워드를 입력하세요."
                        title="패스워드"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" style="height:50px; text-align:center;">
                      <v-btn type="submit">로그인</v-btn> &nbsp;&nbsp;
                      <v-btn
                        type="button"
                        @click="moveToSignUp"
                      >
                        회원가입으로 이동
                      </v-btn>
                      <v-btn @click="close">취소</v-btn>
                    </td>
                  </tr>
                </tbody>
              </v-simple-table>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div v-if="!visablelogin">
      <div v-if="!submitted">
        <form
          action
          method="post"
          id="_frmForm"
          name="frmForm"
          @submit.prevent="registryMyself"
        >
          <v-simple-table class="content_table">
            <colgroup>
              <col style="width:30%;" />
              <col style="width:70%;" />
            </colgroup>
            <tbody>
              <tr> 
                <td colspan="2"><h2>회원가입</h2></td>
              </tr>
            <tr>
              <th>아이디</th>
              <td>
                <input
                  data-msg="이름"
                  type="text"
                  name="username"
                  id="_username"
                  placeholder="아이디를 입력하세요."
                  v-model="customer.username"
                />
              </td>
            </tr>
            <tr>
              <th>이름</th>
              <td>
                <input
                  data-msg="이름"
                  type="text"
                  name="cname"
                  id="_cname"
                  placeholder="이름을 입력하세요."
                  v-model="customer.cname"
                />
              </td>
            </tr>
            <tr>
              <th>이메일</th>
              <td>
                <input
                  data-msg="이메일"
                  type="email"
                  name="email"
                  id="_email"
                  placeholder="이메일을 입력하세요."
                  v-model="customer.email"
                />
              </td>
            </tr>
            <tr>
              <th>패스워드</th>
              <td>
                <input
                  data-msg="패스워드"
                  type="password"
                  name="password"
                  id="_password"
                  placeholder="패스워드를 입력하세요."
                  v-model="customer.password"
                />
              </td>
            </tr>

            <tr>
              <td colspan="2" style="height:50px; text-align:center;">
                <v-btn type="submit" name="button">회원가입</v-btn
                >&nbsp;&nbsp;
                <v-btn type="button" @click="moveToSignIn">
                  로그인으로 이동
                </v-btn>
                <v-btn @click="close">취소</v-btn>
              </td>
            </tr>
            </tbody>
          </v-simple-table>
        </form>
      </div>

      <div class="search_box" v-else>
        <h3>
          <span style="color:blue;">성공적으로 회원가입을 완료하였습니다!</span>
        </h3>
      </div>
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
import { AUTH_REQUEST } from "../store/actions/auth";
import http2 from "../util/http-common2.js";
export default {
  name: "login",
  data() {
    return {
      visablelogin: true,
      usernameOrEmail: "",
      password: "",
      customer: {
        username: "",
        cname: "",
        email: "",
        password: ""
      },
      submitted: false,
      alert: false,
      alertMsg: "",
    };
  },
  methods: {
    login: function() {
      //model에 바인딩된 데이터 모두 ==> this
      const { usernameOrEmail, password } = this;
      this.$store
        .dispatch(AUTH_REQUEST, { usernameOrEmail, password })
        .then(() => {
          this.usernameOrEmail = "";
          this.password = "";
          this.$emit("closeLoginModal");
        })
        .catch(() => {
          this.alertMsg = "로그인에 실패하였습니다. 아이디와 비밀번호를 확인하세요.";
          this.alert = true;
        });
    },
    registryMyself() {
      if (this.customer.username == "") {
        this.alertMsg = "아이디를 입력하세요.";
        this.alert = true;
        return;
      }
      if (this.customer.cname == "") {
        this.alertMsg = "이름을 입력하세요.";
        this.alert = true;
        return;
      }
      if (this.customer.email == "") {
        this.alertMsg = "이메일을 입력하세요.";
        this.alert = true;
        return;
      }
      if (this.customer.password == "") {
        this.alertMsg = "패스워드를 입력하세요.";
        this.alert = true;
        return;
      }

      http2
        .post("/signup", {
          username: this.customer.username,
          name: this.customer.cname,
          email: this.customer.email,
          password: this.customer.password
        })
        .then(response => {
          //console.log(response.data);
          if (
            response.data.success == true ||
            response.data.success == "true"
          ) {
            this.alertMsg = "성공적으로 가입 하였습니다. 로그인 하십시오.";
            this.alert = true;
            this.visablelogin = true;
            this.submitted = true;
            this.newCustomer();
          } else {
            this.alertMsg = "회원가입에 실패하였습니다.";
            this.alert = true;
            this.visablelogin = false;
            this.submitted = false;
            this.newCustomer();
          }
        })
        .catch(() => {
          this.alertMsg = "회원가입에 실패하였습니다.";
          this.alert = true;
          this.visablelogin = false;
          this.submitted = false;
          this.newCustomer();
        });
    },
    newCustomer() {
      this.customer = {
        username: "",
        name: "",
        email: "",
        password: ""
      };
    },
    close() {
      this.newCustomer();
      this.usernameOrEmail="";
      this.password="";
      this.visablelogin= true;
      this.submitted= false;
      this.$emit("closeLoginModal");
    },
    moveToSignUp() {
      this.visablelogin = !this.visablelogin;
      this.usernameOrEmail = "";
      this.password = "";
    },
    moveToSignIn() {
      this.visablelogin = !this.visablelogin;
      this.newCustomer();
    }
  }
};
</script>

<style scoped>
th {
  background-color:#eeeeee; 
  color:#3e5fba;
}
</style>