<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','send_user') || $check_field('add','send_user') || $check_field('set','send_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发送用户" prop="send_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_send_user(form['send_user']) }}
							<el-select v-if="(form['message_sending_id'] && $check_field('set','send_user')) || (!form['message_sending_id'] && $check_field('add','send_user'))" id="send_user" v-model="form['send_user']" :disabled="disabledObj['send_user_isDisabled']">
								<el-option v-for="o in list_user_send_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','send_user')" id="send_user" v-model="form['send_user']" :disabled="true">
								<el-option v-for="o in list_user_send_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="send_user" v-model="form['send_user']" :disabled="disabledObj['send_user_isDisabled']">
							<el-option v-for="o in list_user_send_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="(form['message_sending_id'] && $check_field('set','user_name')) || (!form['message_sending_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','send_method') || $check_field('add','send_method') || $check_field('set','send_method')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发送方式" prop="send_method">
								<el-select id="send_method" v-model="form['send_method']"
						v-if="(form['message_sending_id'] && $check_field('set','send_method')) || (!form['message_sending_id'] && $check_field('add','send_method'))">
						<el-option v-for="o in list_send_method" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','send_method')">{{form['send_method']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','receiving_user') || $check_field('add','receiving_user') || $check_field('set','receiving_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收件用户" prop="receiving_user">
													<el-select v-if="(form['message_sending_id'] && $check_field('set','receiving_user')) || (!form['message_sending_id'] && $check_field('add','receiving_user'))" id="receiving_user" v-model="form['receiving_user']" :disabled="disabledObj['receiving_user_isDisabled']">
							<el-option v-for="o in list_user_receiving_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','receiving_user')" id="receiving_user" v-model="form['receiving_user']" :disabled="true">
							<el-option v-for="o in list_user_receiving_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','receiving_account_number') || $check_field('add','receiving_account_number') || $check_field('set','receiving_account_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收件账号" prop="receiving_account_number">
												<el-input id="receiving_account_number" v-model="form['receiving_account_number']" placeholder="请输入收件账号"
							  v-if="(form['message_sending_id'] && $check_field('set','receiving_account_number')) || (!form['message_sending_id'] && $check_field('add','receiving_account_number'))" :disabled="disabledObj['receiving_account_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','receiving_account_number')">{{form['receiving_account_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','sending_time') || $check_field('add','sending_time') || $check_field('set','sending_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发送时间" prop="sending_time">
								<el-date-picker :disabled="disabledObj['sending_time_isDisabled']" v-if="(form['message_sending_id'] && $check_field('set','sending_time')) || (!form['message_sending_id'] && $check_field('add','sending_time'))" id="sending_time"
						v-model="form['sending_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','sending_time')">{{form['sending_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','content_details') || $check_field('add','content_details') || $check_field('set','content_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="内容详情" prop="content_details">
								<el-input type="textarea" id="content_details" v-model="form['content_details']" placeholder="请输入内容详情"
						v-if="(form['message_sending_id'] && $check_field('set','content_details')) || (!form['message_sending_id'] && $check_field('add','content_details'))" :disabled="disabledObj['content_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','content_details')">{{form['content_details']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','send_status') || $check_field('add','send_status') || $check_field('set','send_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发送状态" prop="send_status">
								<el-select id="send_status" v-model="form['send_status']"
						v-if="(form['message_sending_id'] && $check_field('set','send_status')) || (!form['message_sending_id'] && $check_field('add','send_status'))">
						<el-option v-for="o in list_send_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','send_status')">{{form['send_status']}}</div>
							</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/message_sending/view','set') || $check_action('/message_sending/view','add') || $check_option('/message_sending/table','examine')">
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
				field: "message_sending_id",
				url_add: "~/api/message_sending/add?",
				url_set: "~/api/message_sending/set?",
				url_get_obj: "~/api/message_sending/get_obj?",
				url_upload: "~/api/message_sending/upload?",

				query: {
					"message_sending_id": 0,
				},

				form: {
								"send_user": 0, // 发送用户
										"user_name":  '', // 用户姓名
										"send_method":  '', // 发送方式
										"receiving_user": 0, // 收件用户
										"receiving_account_number":  '', // 收件账号
										"sending_time":  '', // 发送时间
										"content_details":  '', // 内容详情
										"send_status":  '', // 发送状态
											"message_sending_id": 0, // ID
													},
				disabledObj:{
								"send_user_isDisabled": false,
										"user_name_isDisabled": false,
										"send_method_isDisabled": false,
										"receiving_user_isDisabled": false,
										"receiving_account_number_isDisabled": false,
										"sending_time_isDisabled": false,
										"content_details_isDisabled": false,
										"send_status_isDisabled": false,
										},

	
					// 用户列表
				list_user_send_user: [],
						// 用户组
				group_user_send_user: "",
						
										// 发送方式选项列表
				list_send_method: ['短信','邮箱'],
	
				
					// 用户列表
				list_user_receiving_user: [],
						
				
				
										// 发送状态选项列表
				list_send_status: ['未发送','已发送'],
	
			
			}
		},
		methods: {

	
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_send_user() {
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_send_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_send_user() {
							this.form["send_user"] = this.$store.state.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_send_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_send_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_send_user.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["send_user"] = id
									_this.disabledObj['send_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "send_user") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_send_user(id){
				var obj = this.list_user_send_user.getObj({"user_id":id});
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
			 * 获取普通用户用户列表
			 */
			async get_list_user_receiving_user() {
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_receiving_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_receiving_user(id){
				var obj = this.list_user_receiving_user.getObj({"user_id":id});
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
											        if (this.form["sending_time"] && this.form["sending_time"].indexOf("-")===-1){
            this.form["sending_time"] = this.$toTime(parseInt(this.form["sending_time"]),"yyyy-MM-dd hh:mm:ss")
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

																														if(json.result.obj["sending_time"]=="0000-00-00 00:00:00"){
				  json.result.obj["sending_time"] = null;
				}
				if(parseInt(json.result.obj["sending_time"]) > 9999){
					json.result.obj["sending_time"] = this.$toTime(parseInt(json.result.obj["sending_time"]),"yyyy-MM-dd hh:mm:ss")
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
																																																																		if (!param.sending_time){
					return "发送时间不能为空";
				}
																																						return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/message_sending/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/message_sending/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/message_sending/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/message_sending/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/message_sending/view','get');
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
					this.get_list_user_send_user();
					this.get_group_user_send_user();
												this.get_list_user_receiving_user();
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
