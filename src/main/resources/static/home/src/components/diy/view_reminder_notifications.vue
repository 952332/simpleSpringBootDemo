<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题名称" prop="title_name">
												<el-input id="title_name" v-model="form['title_name']" placeholder="请输入标题名称"
							  v-if="(form['reminder_notifications_id'] && $check_field('set','title_name')) || (!form['reminder_notifications_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title_name')">{{form['title_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','ordinary_user') || $check_field('add','ordinary_user') || $check_field('set','ordinary_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="普通用户" prop="ordinary_user">
													<el-select v-if="(form['reminder_notifications_id'] && $check_field('set','ordinary_user')) || (!form['reminder_notifications_id'] && $check_field('add','ordinary_user'))" id="ordinary_user" v-model="form['ordinary_user']" :disabled="disabledObj['ordinary_user_isDisabled']">
							<el-option v-for="o in list_user_ordinary_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','ordinary_user')" id="ordinary_user" v-model="form['ordinary_user']" :disabled="true">
							<el-option v-for="o in list_user_ordinary_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','notification_date') || $check_field('add','notification_date') || $check_field('set','notification_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="通知日期" prop="notification_date">
								<el-date-picker :disabled="disabledObj['notification_date_isDisabled']" v-if="(form['reminder_notifications_id'] && $check_field('set','notification_date')) || (!form['reminder_notifications_id'] && $check_field('add','notification_date'))" id="notification_date"
						v-model="form['notification_date']" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
					</el-date-picker>
					<div v-else-if="$check_field('get','notification_date')">{{form['notification_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','notification_content') || $check_field('add','notification_content') || $check_field('set','notification_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="通知内容" prop="notification_content">
								<el-input type="textarea" id="notification_content" v-model="form['notification_content']" placeholder="请输入通知内容"
						v-if="(form['reminder_notifications_id'] && $check_field('set','notification_content')) || (!form['reminder_notifications_id'] && $check_field('add','notification_content'))" :disabled="disabledObj['notification_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','notification_content')">{{form['notification_content']}}</div>
							</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/reminder_notifications/view','set') || $check_action('/reminder_notifications/view','add') || $check_option('/reminder_notifications/table','examine')">
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
				<el-form-item v-else>
					<el-button @click="cancel()">返回</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "reminder_notifications_id",
				url_add: "~/api/reminder_notifications/add?",
				url_set: "~/api/reminder_notifications/set?",
				url_get_obj: "~/api/reminder_notifications/get_obj?",
				url_upload: "~/api/reminder_notifications/upload?",

				query: {
					"reminder_notifications_id": 0,
				},

				form: {
								"title_name":  '', // 标题名称
										"ordinary_user": 0, // 普通用户
										"notification_date":  '', // 通知日期
										"notification_content":  '', // 通知内容
											"reminder_notifications_id": 0, // ID
													},
				disabledObj:{
								"title_name_isDisabled": false,
										"ordinary_user_isDisabled": false,
										"notification_date_isDisabled": false,
										"notification_content_isDisabled": false,
										},

	
				
					// 用户列表
				list_user_ordinary_user: [],
						
				
			
			}
		},
		methods: {

	
	
			
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_ordinary_user() {
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_ordinary_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_ordinary_user(id){
				var obj = this.list_user_ordinary_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
								if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
								this.form[key] = form[dbKey]
							}
							if(dbKey === "source_table"){
								this.form['source_table'] = form[dbKey];
							}
							if(dbKey === "source_id"){
								this.form['source_id'] = form[dbKey];
							}
							if(dbKey === "source_user_id"){
								this.form['source_user_id'] = form[dbKey];
							}
						})
					})
				}
						        if (this.form["notification_date"] && this.form["notification_date"].indexOf("-")===-1){
          this.form["notification_date"] = this.$toTime(parseInt(this.form["notification_date"]),"yyyy-MM-dd")
        }
							$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

																if(json.result.obj["notification_date"]=="0000-00-00"){
				  json.result.obj["notification_date"] = null;
				}
				if(parseInt(json.result.obj["notification_date"]) > 9999){
					json.result.obj["notification_date"] = this.$toTime(parseInt(json.result.obj["notification_date"]),"yyyy-MM-dd")
				}
									

			},

																																		async submit(param, func){
				if (!param) {
					param = this.form;
				}
								var pm = this.events("submit_before", Object.assign({}, param)) || param;
				var msg = await this.events("submit_check", pm);
				var ret;
				if (msg) {
					this.$toast(msg, 'danger');
				} else {
																																				ret = this.events("submit_main", pm, func);
				}
				return ret;
			},
			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
						submit_check(param) {
																																	if (!param.notification_date){
					return "通知日期不能为空";
				}
																											return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/reminder_notifications/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reminder_notifications/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reminder_notifications/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reminder_notifications/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reminder_notifications/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
							this.get_list_user_ordinary_user();
									},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
	
	.img_multiple{
		overflow: hidden;
	}
	.img_multiple .img_block{
		float: left;
		margin-right: 5px;
		margin-bottom: 5px;
		position: relative;
	}
	.img_multiple .img_block img{
		height: 100px;
		width: auto;
	}
	.img_multiple .img_del{
		position: absolute;
		top: 5px;
		right: 5px;
		width: 20px;
		height: 20px;
		background: #0000008a;
		color: #fff;
		line-height: 20px;
		text-align: center;
		border-radius: 100%;
		cursor: pointer;
	}




</style>
