<template>
	<div class="page_user my_home" id="user_address">
		<div class="warp">
			<div class="container">
				<div class="row justify-content-between">
					<div class="col-12 col-md-3">
						<div class="card_menu">
							<!-- 左侧边栏 -->
							<list_admin_menu_user></list_admin_menu_user>
						</div>
					</div>
					<div class="col-12 col-md-9">
						<div class="card_addres pl-2">
							<div class="warp">
								<div class="container-fluid">
									<el-row>
										<div>欢迎来到个人中心</div>
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
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_admin_menu_user from "@/components/diy/list_admin_menu_user.vue";
	import pieChart from "@/components/charts/pie_chart";
	export default {
		data() {
			return {
									list_message_sending: [],
						list_public_number_push: [],
				};
		},
		mounted() {
		},
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
		created() {
			setTimeout(() => {
						// 执行消息发送数据获取
			this.get_list_message_sending();
					// 执行公众号推送数据获取
			this.get_list_public_number_push();
				}, 1000);
		},
		components: {
			pieChart,
			list_admin_menu_user
		},
	};
</script>

<style scoped>
	.container {
		min-height: 800px;
	}
</style>
