<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
              <th class="text-left">법정동명</th>
              <td>{{dong}}</td>
          </tr>
          <tr>
              <th class="text-left">아파트이름</th>
              <td>{{aptName}}</td>
          </tr>
          <tr>
              <th class="text-left">법정동코드</th>
              <td>{{code}}</td>
          </tr>
          <tr>
              <th class="text-left">거래금액</th>
              <td>{{dealAmount}}</td>
          </tr>
          <tr>
              <th class="text-left">건축연도</th>
              <td>{{buildYear}}</td>
          </tr>
          <tr>
              <th class="text-left">거래연도</th>
              <td>{{dealYear}}. {{dealMonth}}. {{dealDay}}.</td>
          </tr>
          <tr>
              <th class="text-left">전용면적</th>
              <td>{{area}}</td>
          </tr>
          <tr>
              <th class="text-left">층</th>
              <td>{{floor}}</td>
          </tr>
          <tr>
              <th class="text-left">지번</th>
              <td>{{jibun}}</td>
          </tr>
          <tr>
              <th class="text-left">거래유형</th>
              <td>{{type}}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
export default {
  name: "detail",
  props: {
    no: { type: Number },
    dong: { type: String },
    aptName: { type: String },
    code: { type: Number },
    dealAmount: { type: String },
    buildYear: { type: Number },
    dealYear: { type: Number },
    dealMonth: { type: Number },
    dealDay: { type: Number },
    area: { type: Number },
    floor: { type: Number },
    jibun: { type: String },
    type: { type: String },
    lat: { type: String },
    lng: { type: String }
  }
};
</script>

<style scoped>
th {
  background-color:#eeeeee; 
  color:#3e5fba;
}
</style>