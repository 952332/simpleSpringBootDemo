<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','public_number_name') || $check_field('add','public_number_name') || $check_field('set','public_number_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="公众号名称" prop="public_number_name">
												<el-input id="public_number_name" v-model="form['public_number_name']" placeholder="请输入公众号名称"
							  v-if="(form['public_number_push_id'] && $check_field('set','public_number_name')) || (!form['public_number_push_id'] && $check_field('add','public_number_name'))" :disabled="disabledObj['public_number_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','public_number_name')">{{form['public_number_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','public_number_type') || $check_field('add','public_number_type') || $check_field('set','public_number_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="公众号类型" prop="public_number_type">
								<el-select id="public_number_type" v-model="form['public_number_type']"
						v-if="(form['public_number_push_id'] && $check_field('set','public_number_type')) || (!form['public_number_push_id'] && $check_field('add','public_number_type'))">
						<el-option v-for="o in list_public_number_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','public_number_type')">{{form['public_number_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','ordinary_user') || $check_field('add','ordinary_user') || $check_field('set','ordinary_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="普通用户" prop="ordinary_user">
													<el-select v-if="(form['public_number_push_id'] && $check_field('set','ordinary_user')) || (!form['public_number_push_id'] && $check_field('add','ordinary_user'))" id="ordinary_user" v-model="form['ordinary_user']" :disabled="disabledObj['ordinary_user_isDisabled']">
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
								<el-col v-if="$check_field('get','push_time') || $check_field('add','push_time') || $check_field('set','push_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="推送时间" prop="push_time">
								<el-date-picker :disabled="disabledObj['push_time_isDisabled']" v-if="(form['public_number_push_id'] && $check_field('set','push_time')) || (!form['public_number_push_id'] && $check_field('add','push_time'))" id="push_time"
						v-model="form['push_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','push_time')">{{form['push_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','push_content') || $check_field('add','push_content') || $check_field('set','push_content')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="推送内容" prop="push_content">
					<quill-editor v-model.number="form['push_content']"
						v-if="(form['public_number_push_id'] && $check_field('set','push_content')) || (!form['public_number_push_id'] && $check_field('add','push_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','push_content')" v-html="form['push_content']"></div>
				</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/public_number_push/view','set') || $check_action('/public_number_push/view','add') || $check_option('/public_number_push/table','examine')">
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
				field: "public_number_push_id",
				url_add: "~/api/public_number_push/add?",
				url_set: "~/api/public_number_push/set?",
				url_get_obj: "~/api/public_number_push/get_obj?",
				url_upload: "~/api/public_number_push/upload?",

				query: {
					"public_number_push_id": 0,
				},

				form: {
								"public_number_name":  '', // 公众号名称
										"public_number_type":  '', // 公众号类型
										"ordinary_user": 0, // 普通用户
										"push_time":  '', // 推送时间
										"push_content":  '', // 推送内容
											"public_number_push_id": 0, // ID
													},
				disabledObj:{
								"public_number_name_isDisabled": false,
										"public_number_type_isDisabled": false,
										"ordinary_user_isDisabled": false,
										"push_time_isDisabled": false,
										"push_content_isDisabled": false,
										},

	
										// 公众号类型选项列表
				list_public_number_type: ['订阅号','服务号','企业号','小程序号','其他'],
	
				
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
							        if (this.form["push_time"] && this.form["push_time"].indexOf("-")===-1){
            this.form["push_time"] = this.$toTime(parseInt(this.form["push_time"]),"yyyy-MM-dd hh:mm:ss")
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

																				if(json.result.obj["push_time"]=="0000-00-00 00:00:00"){
				  json.result.obj["push_time"] = null;
				}
				if(parseInt(json.result.obj["push_time"]) > 9999){
					json.result.obj["push_time"] = this.$toTime(parseInt(json.result.obj["push_time"]),"yyyy-MM-dd hh:mm:ss")
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
																																												if (!param.push_time){
					return "推送时间不能为空";
				}
																											return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/public_number_push/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/public_number_push/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/public_number_push/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/public_number_push/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/public_number_push/view','get');
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
