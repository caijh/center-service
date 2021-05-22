<template>
  <!-- hidden PageHeaderWrapper title demo -->
  <page-header-wrapper :title="false" content="表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。">
    <a-card :body-style="{padding: '24px 32px'}" :bordered="false">
      <a-form :form="form" @submit="handleSubmit">
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="标题">
          <a-input
            v-decorator="[
              'name',
              {rules: [{ required: true, message: '请输入标题' }]}
            ]"
            name="name"
            placeholder="给目标起个名字"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="起止日期">
          <a-range-picker
            v-decorator="[
              'buildTime',
              {rules: [{ required: true, message: '请选择起止日期' }]}
            ]"
            name="buildTime"
            style="width: 100%"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="目标描述">
          <a-textarea
            v-decorator="[
              'description',
              {rules: [{ required: true, message: '请输入目标描述' }]}
            ]"
            placeholder="请输入你阶段性工作目标"
            rows="4"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="衡量标准">
          <a-textarea
            v-decorator="[
              'type',
              {rules: [{ required: true, message: '请输入衡量标准' }]}
            ]"
            placeholder="请输入衡量标准"
            rows="4"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="客户">
          <a-input
            v-decorator="[
              'customer',
              {rules: [{ required: true, message: '请描述你服务的客户' }]}
            ]"
            placeholder="请描述你服务的客户，内部客户直接 @姓名／工号"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :required="false"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="邀评人"
        >
          <a-input placeholder="请直接 @姓名／工号，最多可邀请 5 人"/>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :required="false"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          label="权重"
        >
          <a-input-number :max="100" :min="0"/>
          <span> %</span>
        </a-form-item>
        <a-form-item
          :labelCol="{lg: {span: 7}, sm: {span: 7}}"
          :required="false"
          :wrapperCol="{lg: {span: 10}, sm: {span: 17} }"
          help="客户、邀评人默认被分享"
          label="目标公开"
        >
          <a-radio-group v-decorator="['target', { initialValue: 1 }]">
            <a-radio :value="1">公开</a-radio>
            <a-radio :value="2">部分公开</a-radio>
            <a-radio :value="3">不公开</a-radio>
          </a-radio-group>
          <a-form-item v-show="form.getFieldValue('target') === 2">
            <a-select mode="multiple">
              <a-select-option value="4">同事一</a-select-option>
              <a-select-option value="5">同事二</a-select-option>
              <a-select-option value="6">同事三</a-select-option>
            </a-select>
          </a-form-item>
        </a-form-item>
        <a-form-item
          :wrapperCol="{ span: 24 }"
          style="text-align: center"
        >
          <a-button htmlType="submit" type="primary">提交</a-button>
          <a-button style="margin-left: 8px">保存</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </page-header-wrapper>
</template>

<script>
export default {
  name: 'BaseForm',
  data() {
    return {
      form: this.$form.createForm(this)
    }
  },
  methods: {
    // handler
    handleSubmit(e) {
      e.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values)
        }
      })
    }
  }
}
</script>
