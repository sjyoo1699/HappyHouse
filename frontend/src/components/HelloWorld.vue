<template>
  <v-container>
    <v-row class="text-center">
      <div style="position: relative; ">
        <div class="entry" style="float: left; width: 25%;">
          <router-link to="/dealList">
            <v-img :src="require('../assets/main1.jpg')" class="my-3" contain height="450" />
          </router-link>
          <h1 class="title">DEAL LIST</h1>
        </div>
        <div class="entry" style="float: left; width: 25%;">
          <router-link to="/recommendList">
            <v-img :src="require('../assets/main2.jpg')" class="my-3" contain height="450" />
          </router-link>
          <h1 class="title">RECOMMENDED LIST</h1>
        </div>
        <div class="entry" style="float: left; width: 25%;">
          <router-link to="/noticeList">
            <v-img :src="require('../assets/main3.jpg')" class="my-3" contain height="450" />
          </router-link>
          <h1 class="title">NOTICE</h1>
        </div>
        <div class="entry" style="float: left; width: 25%;">
          <router-link to="/qna">
            <v-img :src="require('../assets/main4.jpg')" class="my-3" contain height="450" />
          </router-link>
          <h1 class="title">Q&A</h1>
        </div>
      </div>
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">HAPPY HOUSE</h1>
        <p class="subheading font-weight-regular">We recommend the best house for you.</p>
      </v-col>
    </v-row>
    <br />
    <br />
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">DEVELOPERS</h1>
        <br />
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="3">
        <v-card align="left" outlined raised width="450">
          <div>DEVELOPER</div>
          <p class="display-1 text--primary">
            Yoo Seongjun
          </p>
          <p>SSAFY</p>
          <div class="text--primary">
            edonc700@gmail.com<br>
            <a href="https://lab.ssafy.com/edonc700" target="blank">https://lab.ssafy.com/edonc700</a>
          </div>
        </v-card>
      </v-col>
      <v-col cols="3">
        <v-card align="left" outlined raised width="450">
          <div>DEVELOPER</div>
          <p class="display-1 text--primary">
            Lim dohyeon
          </p>
          <p>SSAFY</p>
          <div class="text--primary">
            gigjf@naver.com<br>
            <a href="https://lab.ssafy.com/gigjf" target="blank">https://lab.ssafy.com/gigjf</a>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "HelloWorld"
};
</script>

<style>
h1.title {
  text-shadow: 1px 1px 2px #666666;
  color: rgb(255, 255, 255);
  text-align: center;
  position: relative;
  bottom: 60px;
}
.my-3:hover {
  filter: drop-shadow(10px 10px 20px rgb(136, 136, 136));
}
</style>