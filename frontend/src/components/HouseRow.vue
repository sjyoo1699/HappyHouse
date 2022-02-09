<template>
  <tr @click="show">
    <td>{{dong}}</td>
    <td>{{aptName}}</td>
    <td>{{code}}</td>
    <td>{{dealAmount}}</td>
    <td>{{buildYear}}</td>
    <td>{{dealYear}}. {{dealMonth}}. {{dealDay}}.</td>
    <td>{{area}}</td>
    <td>{{floor}}</td>
    <td>{{jibun}}</td>
    <td>{{type}}</td>
  </tr>
</template>

<script>
export default {
  name: "row",
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
  },
  methods: {
    show : function() {
      this.$emit("showModal", this.no);
    }
  }
};
</script>
