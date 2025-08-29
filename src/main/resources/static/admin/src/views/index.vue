<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<div_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></div_label>
					</el-col>
<!--					<el-col :span="4">-->
<!--						<div_label bg_color="bg_green" icon="el-icon-view" :url="url_article_hits" unit="次"-->
<!--								  title="文章浏览量"></div_label>-->
<!--					</el-col>-->
				</el-row>

				<el-row>
								<el-col v-if="$check_figure('/message_sending/table')" :span="8">
						<div class="card chart">
																																																																	<pieChart v-if="list_message_sending.length" id="list_message_sending" :list="list_message_sending" :title="'消息发送统计'"></pieChart>
							<div v-if="!list_message_sending.length">消息发送没有符合条件的数据</div>
															</div>
					</el-col>
							<el-col v-if="$check_figure('/public_number_push/table')" :span="8">
						<div class="card chart">
																							<pieChart v-if="list_public_number_push.length" id="list_public_number_push" :list="list_public_number_push" :title="'公众号推送统计'"></pieChart>
							<div v-if="!list_public_number_push.length">公众号推送没有符合条件的数据</div>
																																				</div>
					</el-col>
					</el-row>

			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import div_label from "@/components/div_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			div_label
		},
		data() {
			return {
				activeName: "third",
									list_message_sending: [],
						list_public_number_push: [],
					url_user_count: "~/api/user/count?",
				url_article_hits: "~/api/article/sum?field=hits",
			};
		},
		created() {
						// 执行消息发送数据获取
			this.get_list_message_sending();
					// 执行公众号推送数据获取
			this.get_list_public_number_push();
			},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
																																																// 获取消息发送统计图数据
			get_list_message_sending() {
				let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																		if (user_group=="普通用户"){
						sqlwhere+= "send_user = " + user_id + " or ";
					}
																																																			if (user_group=="普通用户"){
						sqlwhere+= "receiving_user = " + user_id + " or ";
					}
																																																																						if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								this.$get("~/api/message_sending/list_group?groupby=send_status", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_message_sending = list.map((o) => {
							return {
												name: o[1],
												value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_message_sending,false);
						}
					}
				});
			},
																				// 获取公众号推送统计图数据
			get_list_public_number_push() {
				let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																												if (user_group=="普通用户"){
						sqlwhere+= "ordinary_user = " + user_id + " or ";
					}
																																												if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								this.$get("~/api/public_number_push/list_group?groupby=public_number_type", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_public_number_push = list.map((o) => {
							return {
												name: o[1],
												value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_public_number_push,false);
						}
					}
				});
			},
																					
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
