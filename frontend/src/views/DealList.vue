<template>
  <div align="center">
      <h1 class="display-2 font-weight-bold mb-3">DEAL LIST</h1>
      <v-sheet color="#6A76AB" elevation="24" rounded="pill" style="color:white;" height="25" width="80%">거래 내역을 조회하고 상세 정보, 주변 정보를 볼 수 있습니다.</v-sheet>
      <br>
    <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
      <ul id="category">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
    <br>
    <v-row align="center" justify="left">
      <v-switch v-model="checked1" class="ma-2" label="아파트 매매"></v-switch>
      <v-switch v-model="checked2" class="ma-2" label="아파트 전월세"></v-switch>
      <v-switch v-model="checked3" class="ma-2" label="연립, 주택 매매"></v-switch>
      <v-switch v-model="checked4" class="ma-2" label="연립, 주택 전월세"></v-switch>
    </v-row>
    <v-row justify="left">
        <v-col cols="1">
            <v-select
            :items="searchItems"
            v-model="selected"
            label="검색분류"
          ></v-select>
        </v-col>
        <v-col cols="2">
            <v-text-field label="검색어를 입력하세요" v-model="message" @keyup="searchL" v-show="searchShow"></v-text-field>
        </v-col>
    </v-row>
    
    
    <div v-if="items.length">
        <v-simple-table>
        <template v-slot:default>
            <colgroup>
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '24%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '10%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '7%' }" />
                <col :style="{ width: '10%' }" />
            </colgroup>
            <thead>
            <tr>
                <th class="text-left">법정동명</th>
                <th class="text-left">아파트이름</th>
                <th class="text-left">법정동코드</th>
                <th class="text-left">거래금액</th>
                <th class="text-left">건축연도</th>
                <th class="text-left">거래연도</th>
                <th class="text-left">전용면적</th>
                <th class="text-left">층</th>
                <th class="text-left">지번</th>
                <th class="text-left">거래유형</th>
            </tr>
            </thead>
            <tbody>
                <list-row
                    v-for="(item, index) in items"
                    :key="`${index}_items`"
                    :no="item.no"
                    :dong="item.dong"
                    :aptName="item.aptName"
                    :code="item.code"
                    :dealAmount="item.dealAmount"
                    :buildYear="item.buildYear"
                    :dealYear="item.dealYear"
                    :dealMonth="item.dealMonth"
                    :dealDay="item.dealDay"
                    :area="item.area"
                    :floor="item.floor"
                    :jibun="item.jibun"
                    :type="item.type"
                    v-on:showModal="showModal"
                />
            </tbody>
        </template>
        </v-simple-table>
    </div>

    <div v-else>목록이 없습니다.</div>
    <div style="padding-center : 200px">
        <v-btn text small color="primary" @click="pageMove(1)">최신</v-btn>
        <v-btn text small color="primary" @click="pageMove(priv)">이전</v-btn>
      <span v-for="index in indexes" v-bind:key="index">
        <v-btn flat small @click="pageMove(startPage+index-1)" v-if="index==1">{{startPage+index-1}}</v-btn>
        <v-btn text small @click="pageMove(startPage+index-1)" v-else>{{startPage+index-1}}</v-btn>
      </span>
      <v-btn text small color="primary" @click="pageMove(next)">다음</v-btn>
      <v-btn text small color="primary" @click="pageMove(end)">마지막</v-btn>
    </div>

    <v-dialog v-model="dialog" max-width="600">
        <v-card>
          <v-card-title class="headline">거래 내역 상세조회</v-card-title>
          <detail
            :no="item.no"
            :dong="item.dong"
            :aptName="item.aptName"
            :code="item.code"
            :dealAmount="item.dealAmount"
            :buildYear="item.buildYear"
            :dealYear="item.dealYear"
            :dealMonth="item.dealMonth"
            :dealDay="item.dealDay"
            :area="item.area"
            :floor="item.floor"
            :jibun="item.jibun"
            :type="item.type"
          />
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" flat @click.native="dialog = false">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

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
import ListRow from "@/components/HouseRow.vue";
import Detail from "@/components/HouseDetail.vue";
export default {
  name: "deallist",
  components: {
    ListRow,
    Detail,
  },
  data: function() {
    return {
      item: {},
      items: [],
      nav: "",
      checked1: true,
      checked2: true,
      checked3: true,
      checked4: true,
      startPage: "",
      endPage: "",
      indexes: "",
      message: "",
      selected: "",
      markers: [],
      dialog: false,
      searchItems:[
          {
              text:'전체',
              value:'전체'
          }, 
          {
              text:'건물명',
              value:'건물명'
          }, 
          {
              text:'동이름',
              value:'동이름'
          }
          ],
      searchShow: false,
      alert: false,
      alertMsg: "",
    };
  },
  computed: {
    priv() {
      return this.nav.startRange ? 1 : this.startPage - 1;
    },
    next() {
      return this.nav.endRange ? this.endPage : this.endPage + 1;
    },
    end() {
      return this.nav.totalPageCount;
    }
  },
  watch: {
    selected : function() {
        if(this.selected == "전체") {
            this.searchShow = false;
            this.message = "";
            this.search();
        }
        else this.searchShow = true;
    }
  },
  created() {
    http
      .post("/house", {
        aptName: this.aptNamee,
        currentPage: 1,
        dong: this.dongg,
        searchType: [
          this.checked1,
          this.checked2,
          this.checked3,
          this.checked4
        ],
        sizePerPage: 10
      })
      .then(({ data }) => {
        this.items = data;
        window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
      })
      .catch(() => {
        this.alertMsg = "검색 조건을 선택해주세요.";
        this.alert = true;
      });
    http
      .put("/house", {
        aptName: this.aptNamee,
        currentPage: 1,
        dong: this.dongg,
        searchType: [
          this.checked1,
          this.checked2,
          this.checked3,
          this.checked4
        ],
        sizePerPage: 10
      })
      .then(({ data }) => {
        this.nav = data;
        this.startPage =
          ((this.nav.currentPage - 1) / this.nav.naviSize) * this.nav.naviSize +
          1;
        this.endPage = this.startPage + this.nav.naviSize - 1;
        if (this.nav.totalPageCount < this.endPage)
          this.endPage = this.nav.totalPageCount;
        this.indexes = this.endPage - this.startPage + 1;
      })
      .catch(() => {
        this.alertMsg = "검색 조건을 선택해주세요.";
        this.alert = true;
      });
  },
  methods: {
    showModal(no) {
        http.get(`/house/${no}`).then(({ data }) => {
            this.item = data;
        });
        this.dialog = true;
    },
    pageMove(pg) {
      if (this.selected == "건물명") {
        this.aptNamee = this.message;
      } else if (this.selected == "동이름") {
        this.dongg = this.message;
      } else {
          this.aptNamee = undefined;
          this.dongg = undefined;
      }
      http
        .post("/house", {
          aptName: this.aptNamee,
          currentPage: pg,
          dong: this.dongg,
          searchType: [
            this.checked1,
            this.checked2,
            this.checked3,
            this.checked4
          ],
          sizePerPage: 10
        })
        .then(({ data }) => {
          this.items = data;
          window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        })
        .catch(() => {
          this.alertMsg = "검색 조건을 선택해주세요.";
          this.alert = true;
        });
      http
        .put("/house", {
          aptName: this.aptNamee,
          currentPage: pg,
          dong: this.dongg,
          searchType: [
            this.checked1,
            this.checked2,
            this.checked3,
            this.checked4
          ],
          sizePerPage: 10
        })
        .then(({ data }) => {
          this.nav = data;
          this.startPage =
            ((this.nav.currentPage - 1) / this.nav.naviSize) *
              this.nav.naviSize +
            1;
          this.endPage = this.startPage + this.nav.naviSize - 1;
          if (this.nav.totalPageCount < this.endPage)
            this.endPage = this.nav.totalPageCount;
          this.indexes = this.endPage - this.startPage + 1;
        })
        .catch(() => {
          this.alertMsg = "검색 조건을 선택해주세요.";
          this.alert = true;
        });
    },

    search() {
      this.pageMove(1);
    },

    searchL(e) {
      if (e.which == 13) {
        this.search();
      }
    },

    initMap() {
      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5642135, 127.0016985),
        level: 8
      };
      var map = new kakao.maps.Map(container, options);

      var bounds = new kakao.maps.LatLngBounds();
      for (var i = 0; i < this.items.length; i++) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(this.items[i].lat, this.items[i].lng), // 마커를 표시할 위치
          title: this.items[i].aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          clickable: true
        });
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(
          new kakao.maps.LatLng(this.items[i].lat, this.items[i].lng)
        );
        var content =
          '<div>' +
          this.items[i].aptName +
          "</div>";
        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: content // 인포윈도우에 표시할 내용
        });
        // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
        // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        (function(marker, infowindow) {
          // 마커에 클릭이벤트를 등록합니다
          kakao.maps.event.addListener(marker, "click", function() {
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(map, marker);
          });
        })(marker, infowindow);
      }
      marker.setMap(map);
      map.setBounds(bounds);
      
      ////////////////////////////////////////////////////////////

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "idle", searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
      addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();
      
      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }

      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document
          .getElementById(currCategory)
          .getAttribute("data-order");

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(
            new kakao.maps.LatLng(places[i].y, places[i].x),
            order
          );

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function(marker, place) {
            kakao.maps.event.addListener(marker, "click", function() {
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, order) {
        var imageSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imgOptions
          ),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content +=
            '    <span title="' +
            place.address_name +
            '">' +
            place.address_name +
            "</span>";
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          "</span>" +
          "</div>" +
          '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        console.log(1);
        var category = document.getElementById("category"),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === "on") {
          currCategory = "";
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById("category"),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = "";
        }

        if (el) {
          el.className = "on";
        }
      }
    },

    addScript() {
      const script = document.createElement("script");
      /* global kakao */ script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=c0bc706b6bc821a05c4b8cd8bdfe570a";
      document.head.appendChild(script);
    }
  }
};
</script>

<style>
th {
  background-color:#eeeeee; 
  color:#3e5fba;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 80%;
  height: 500px;
  z-index: 0;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 1;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>