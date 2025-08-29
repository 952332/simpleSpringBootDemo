<template>
	<div class="diy_edit page_message_sending" id="message_sending_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','send_user') || $check_field('add','send_user') || $check_field('get','send_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发送用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_send_user" :disabled="disabledObj['send_user_isDisabled']" v-model="form['send_user']" v-if="(form['send_user'] && $check_field('set','send_user')) || (!form['send_user'] && $check_field('add','send_user'))" >
								<option v-for="o in list_user_send_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','send_user')">{{ get_user_info("send_user", form['send_user']) }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								用户姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_user_name" v-model="form['user_name']" placeholder="请输入用户姓名" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))"  :disabled="disabledObj['user_name_isDisabled']"/>
							<span v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','send_method') || $check_field('add','send_method') || $check_field('get','send_method')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发送方式:
							</span>
						</div>
								<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_send_method" v-model="form['send_method']" v-if="(form['send_method'] && $check_field('set','send_method')) || (!form['send_method'] && $check_field('add','send_method'))" >
								<option v-for="o in list_send_method" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','send_method')">{{ form['send_method'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','receiving_user') || $check_field('add','receiving_user') || $check_field('get','receiving_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								收件用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_receiving_user" :disabled="disabledObj['receiving_user_isDisabled']" v-model="form['receiving_user']" v-if="(form['receiving_user'] && $check_field('set','receiving_user')) || (!form['receiving_user'] && $check_field('add','receiving_user'))" >
								<option v-for="o in list_user_receiving_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','receiving_user')">{{ get_user_info("receiving_user", form['receiving_user']) }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','receiving_account_number') || $check_field('add','receiving_account_number') || $check_field('get','receiving_account_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								收件账号:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_receiving_account_number" v-model="form['receiving_account_number']" placeholder="请输入收件账号" v-if="(form['receiving_account_number'] && $check_field('set','receiving_account_number')) || (!form['receiving_account_number'] && $check_field('add','receiving_account_number'))"  :disabled="disabledObj['receiving_account_number_isDisabled']"/>
							<span v-else-if="$check_field('get','receiving_account_number')">{{ form['receiving_account_number'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','sending_time') || $check_field('add','sending_time') || $check_field('get','sending_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发送时间:
							</span>
						</div>
								<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['sending_time_isDisabled']" id="form_sending_time" v-model="form['sending_time']" placeholder="请输入发送时间" v-if="(form['sending_time'] && $check_field('set','sending_time')) || (!form['sending_time'] && $check_field('add','sending_time'))" />
							<span v-else-if="$check_field('get','sending_time')">{{ form['sending_time'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','content_details') || $check_field('add','content_details') || $check_field('get','content_details')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								内容详情:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_content_details" v-model="form['content_details']" v-if="(form['content_details'] && $check_field('set','content_details')) || (!form['content_details'] && $check_field('add','content_details'))" :disabled="disabledObj['content_details_isDisabled']" />
							<span v-else-if="$check_field('get','content_details')">{{ form['content_details'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','send_status') || $check_field('add','send_status') || $check_field('get','send_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发送状态:
							</span>
						</div>
								<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_send_status" v-model="form['send_status']" v-if="(form['send_status'] && $check_field('set','send_status')) || (!form['send_status'] && $check_field('add','send_status'))" >
								<option v-for="o in list_send_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','send_status')">{{ form['send_status'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/message_sending/get_obj?",
				url_add: "~/api/message_sending/add?",
				url_set: "~/api/message_sending/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"send_user": 0,
							"user_name": "",
							"send_method": "",
							"receiving_user": 0,
							"receiving_account_number": "",
							"sending_time": "",
							"content_details": "",
							"send_status": "",
						"message_sending_id": 0,
				},

				obj: {
						"send_user": 0, // 发送用户
							"user_name":  '', // 用户姓名
							"send_method":  '', // 发送方式
							"receiving_user": 0, // 收件用户
							"receiving_account_number":  '', // 收件账号
							"sending_time": new Date().getTime(),
							"content_details":  '', // 内容详情
							"send_status":  '', // 发送状态
						"message_sending_id": 0,
				},

				// 表单字段
				form: {
						"send_user": 0, // 发送用户
							"user_name":  '', // 用户姓名
							"send_method":  '', // 发送方式
							"receiving_user": 0, // 收件用户
							"receiving_account_number":  '', // 收件账号
							"sending_time": new Date().getTime(),
							"content_details":  '', // 内容详情
							"send_status":  '', // 发送状态
						"message_sending_id": 0,
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
												// 发送方式选项列表
				list_send_method: ['短信','邮箱'],
									// 用户列表
				list_user_receiving_user: [],
																		// 发送状态选项列表
				list_send_status: ['未发送','已发送'],
			
				// ID字段
				field: "message_sending_id",

			}
		},
		methods: {
																																																																						formatDateValue(value) {
		  const date = new Date(value);
		  return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
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
					async get_user_session_send_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["send_user"] = user_id
								_this.disabledObj['send_user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "send_user") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
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
		
				
				
				
				
		  		get_user_info(name,id){
				var obj = null;
  				  if (name == 'send_user'){
					  obj = this.list_user_send_user.getObj({"user_id":id});
				  }
        				  if (name == 'receiving_user'){
					  obj = this.list_user_receiving_user.getObj({"user_id":id});
				  }
          				var ret = "";
				if(obj){
				  ret = obj.nickname+"-"+obj.username;
				}
				return ret;
			},
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/message_sending/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},
			
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file_multiple(files, str) {
				let _this = this;
				var form = new FormData();
				for (var i = 0; i < files.length; i++) {
					form.set("file", files[i]);
					_this.$post("~/api/message_sending/upload?", form, (res) => {
						if (res.result) {
							if (_this.form[str].length > 0) {
								_this.form[str].push(res.result.url);
							} else {
								_this.form[str] = [res.result.url];
							}
						} else if (res.error) {
							_this.$toast(res.error.message);
						}
					});
				}
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
			  if(arr[i] === "source_table"){
			  	this.form['source_table'] = form[arr[i]]
			  }
			  if(arr[i] === "source_id"){
			  	this.form['source_id'] = form[arr[i]]
			  }
			  if(arr[i] === "source_user_id"){
			  	this.form['source_user_id'] = form[arr[i]]
			  }
            }
          }
        }
																        if (this.form["sending_time"] && JSON.stringify(this.form["sending_time"]).indexOf("-")===-1) {
          this.form["sending_time"] = this.$toTime(parseInt(this.form["sending_time"]), "yyyy-MM-ddThh:mm")
        }
									
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
					this.get_user_session_send_user();
					this.get_list_user_send_user();
													this.get_list_user_receiving_user();
															},
	}
</script>

<style>
	.diy_compute{
		line-height: 40px;
	}
	.diy_field.diy_img_multiple{
		margin: 0;
	}
	.diy_field.diy_img_multiple div{
		float: left;
		position: relative;
		margin: 0 10px 10px 0;
	}
	.diy_field.diy_img_multiple img{
		height: 100px;
		width: auto;
	}
	.diy_field.diy_img_multiple span{
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
