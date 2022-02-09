<template>
  <div>
    <v-simple-table>
      <template v-slot:default borderless>
        <tbody>
            <tr>
                <th class="text-left">번호</th>
                <td class="text-left">{{articleno}}</td>
            </tr>
            <tr>
                <th class="text-left">아이디</th>
                <td class="text-left">{{id}}</td>
            </tr>
            <tr>
                <th class="text-left">제목</th>
                <td class="text-left">{{subject}}</td>
            </tr>
            <tr>
                <th class="text-left">날짜</th>
                <td class="text-left">{{getFormatDate(regtime)}}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <v-p>
                        {{content}}
                    </v-p>
                </td>
            </tr>
        </tbody>
      </template>
    </v-simple-table>
    <br />
    <br />
    <div class="text-center">
        <v-btn to="/noticeList">목록</v-btn>
    </div>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "detail",
  props: {
    articleno: { type: Number },
    id: { type: String },
    subject: { type: String },
    content: { type: String },
    regtime: { type: String }
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    }
  }
};
</script>
