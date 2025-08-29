<template>
  <div class="list_menu diy_menu">
    <router-link
            to="/user_center/index"
            class="menu_item"
            :class="{ selected: this.$route.path=== '/user_center/index'}"
     >
      <span class="left_span">个人首页</span>
      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
    </router-link>
          <router-link
          to="/ordinary_user/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/ordinary_user/table'}"
          v-if="$check_action('/ordinary_user/table','get')">
        <span class="left_span">普通用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/reminder_notifications/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/reminder_notifications/table'}"
          v-if="$check_action('/reminder_notifications/table','get')">
        <span class="left_span">提醒通知</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/message_sending/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/message_sending/table'}"
          v-if="$check_action('/message_sending/table','get')">
        <span class="left_span">消息发送</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/public_number_push/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/public_number_push/table'}"
          v-if="$check_action('/public_number_push/table','get')">
        <span class="left_span">公众号推送</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
      <router-link
              to="/user/collect"
              class="menu_item"
              :class="{ selected: this.$route.path=== '/user/collect'}"
              v-if="$check_action('/collect/list','get')">
        <span class="left_span">收藏</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
  </router-link>
	<router-link
	            to="/comment/table"
	            class="menu_item"
	            :class="{ selected: this.$route.path=== '/comment/table'}"
	            v-if="$check_action('/comment/table','get')">
	      <span class="left_span">评论管理</span>
	      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
	</router-link>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";
export default {
  mixins: [mixin],
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  },

  components: {
  },
};
</script>

<style scoped>
.list_menu {
  flex: 0 0 25%;
  border-bottom: 1px solid #eee;
}
.menu_item {
  display: flex;
  justify-content: space-between;
  padding: 15px 20px;
  border: 1px solid #eee;
  border-bottom: none;
  background-color: #fff;
}
.menu_item.selected {
  background-color: #000;
}
.menu_item.selected span {
  color: #fff ;
}
.content {
  flex: 0 0 74%;
}
</style>
