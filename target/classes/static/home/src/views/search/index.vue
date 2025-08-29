<template>
  <div class="page_search search_index">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/ordinary_user/list', 'get')"
				:list="result_ordinary_user_user_name"
				title="普通用户用户姓名"
				source_table="ordinary_user"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/reminder_notifications/list', 'get')"
				:list="result_reminder_notifications_title_name"
				title="提醒通知标题名称"
				source_table="reminder_notifications"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/reminder_notifications/list', 'get')"
				:list="result_reminder_notifications_notification_date"
				title="提醒通知通知日期"
				source_table="reminder_notifications"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/message_sending/list', 'get')"
				:list="result_message_sending_user_name"
				title="消息发送用户姓名"
				source_table="message_sending"
			  ></list_result_search>
																											  <list_result_search
				v-if="$check_action('/public_number_push/list', 'get')"
				:list="result_public_number_push_public_number_name"
				title="公众号推送公众号名称"
				source_table="public_number_push"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/public_number_push/list', 'get')"
				:list="result_public_number_push_public_number_type"
				title="公众号推送公众号类型"
				source_table="public_number_push"
			  ></list_result_search>
															</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_ordinary_user_user_name":[],
															"result_reminder_notifications_title_name":[],
											"result_reminder_notifications_notification_date":[],
															"result_message_sending_user_name":[],
																											"result_public_number_push_public_number_name":[],
								"result_public_number_push_public_number_type":[],
													};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取user_name
	 */
	get_ordinary_user_user_name(){
		let url = "~/api/ordinary_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_user_user_name = json.result.list;
			result_ordinary_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_user_user_name = result_ordinary_user_user_name
		 	}
		});
	},
													/**
	 * 获取title_name
	 */
	get_reminder_notifications_title_name(){
		let url = "~/api/reminder_notifications/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "title_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_reminder_notifications_title_name = json.result.list;
			result_reminder_notifications_title_name.map(o => o.title = o['title_name'])
	  			this.result_reminder_notifications_title_name = result_reminder_notifications_title_name
		 	}
		});
	},
									/**
	 * 获取notification_date
	 */
	get_reminder_notifications_notification_date(){
		let url = "~/api/reminder_notifications/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "notification_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_reminder_notifications_notification_date = json.result.list;
			result_reminder_notifications_notification_date.map(o => o.title = o['notification_date'])
	  			this.result_reminder_notifications_notification_date = result_reminder_notifications_notification_date
		 	}
		});
	},
													/**
	 * 获取user_name
	 */
	get_message_sending_user_name(){
		let url = "~/api/message_sending/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_message_sending_user_name = json.result.list;
			result_message_sending_user_name.map(o => o.title = o['user_name'])
	  			this.result_message_sending_user_name = result_message_sending_user_name
		 	}
		});
	},
																									/**
	 * 获取public_number_name
	 */
	get_public_number_push_public_number_name(){
		let url = "~/api/public_number_push/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "public_number_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_public_number_push_public_number_name = json.result.list;
			result_public_number_push_public_number_name.map(o => o.title = o['public_number_name'])
	  			this.result_public_number_push_public_number_name = result_public_number_push_public_number_name
		 	}
		});
	},
						/**
	 * 获取public_number_type
	 */
	get_public_number_push_public_number_type(){
		let url = "~/api/public_number_push/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "public_number_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_public_number_push_public_number_type = json.result.list;
			result_public_number_push_public_number_type.map(o => o.title = o['public_number_type'])
	  			this.result_public_number_push_public_number_type = result_public_number_push_public_number_type
		 	}
		});
	},
												
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_ordinary_user_user_name();
														this.get_reminder_notifications_title_name();
										this.get_reminder_notifications_notification_date();
														this.get_message_sending_user_name();
																										this.get_public_number_push_public_number_name();
							this.get_public_number_push_public_number_type();
												  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_ordinary_user_user_name();
													  this.get_reminder_notifications_title_name();
									  this.get_reminder_notifications_notification_date();
													  this.get_message_sending_user_name();
																									  this.get_public_number_push_public_number_name();
						  this.get_public_number_push_public_number_type();
													},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
