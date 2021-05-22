<template>
  <div>
    <a-form :form="form" @submit="handleSubmit">

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        hasFeedback
        label="规则编号"
        validateStatus="success"
      >
        <a-input
          v-decorator="[
            'no',
            {rules: [{ required: true, message: '请输入规则编号' }]}
          ]"
          :disabled="true"
          placeholder="规则编号"
        ></a-input>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        hasFeedback
        label="服务调用次数"
        validateStatus="success"
      >
        <a-input-number v-decorator="['callNo', {rules: [{ required: true }]}]" :min="1" style="width: 100%"/>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        hasFeedback
        label="状态"
        validateStatus="warning"
      >
        <a-select v-decorator="['status', {rules: [{ required: true, message: '请选择状态' }], initialValue: '1'}]">
          <a-select-option :value="1">Option 1</a-select-option>
          <a-select-option :value="2">Option 2</a-select-option>
          <a-select-option :value="3">Option 3</a-select-option>
        </a-select>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        hasFeedback
        help="请填写一段描述"
        label="描述"
      >
        <a-textarea v-decorator="['description', {rules: [{ required: true }]}]" :rows="5" placeholder="..."/>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        hasFeedback
        label="更新时间"
        validateStatus="error"
      >
        <a-date-picker
          v-decorator="['updatedAt']"
          format="YYYY-MM-DD HH:mm:ss"
          placeholder="Select Time"
          showTime
          style="width: 100%"
        />
      </a-form-item>

      <a-form-item
        v-bind="buttonCol"
      >
        <a-row>
          <a-col span="6">
            <a-button html-type="submit" type="primary">提交</a-button>
          </a-col>
          <a-col span="10">
            <a-button @click="handleGoBack">返回</a-button>
          </a-col>
          <a-col span="8"></a-col>
        </a-row>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import moment from 'moment'
import pick from 'lodash.pick'

export default {
  name: 'TableEdit',
  props: {
    record: {
      type: [Object, String],
      default: ''
    }
  },
  data() {
    return {
      labelCol: {
        xs: {span: 24},
        sm: {span: 5}
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 12}
      },
      buttonCol: {
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 12, offset: 5}
        }
      },
      form: this.$form.createForm(this),
      id: 0
    }
  },
  // beforeCreate () {
  //   this.form = this.$form.createForm(this)
  // },
  mounted() {
    this.$nextTick(() => {
      this.loadEditInfo(this.record)
    })
  },
  methods: {
    handleGoBack() {
      this.$emit('onGoBack')
    },
    handleSubmit() {
      const {form: {validateFields}} = this
      validateFields((err, values) => {
        if (!err) {
          // eslint-disable-next-line no-console
          console.log('Received values of form: ', values)
        }
      })
    },
    handleGetInfo() {

    },
    loadEditInfo(data) {
      const {form} = this
      // ajax
      console.log(`将加载 ${this.id} 信息到表单`)
      new Promise((resolve) => {
        setTimeout(resolve, 1500)
      }).then(() => {
        const formData = pick(data, ['no', 'callNo', 'status', 'description', 'updatedAt'])
        formData.updatedAt = moment(data.updatedAt)
        console.log('formData', formData)
        form.setFieldsValue(formData)
      })
    }
  }
}
</script>
