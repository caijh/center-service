<template>
  <div>
    <a-card :bordered="false" class="ant-pro-components-tag-select">
      <a-form :form="form" layout="inline">
        <standard-form-row block style="padding-bottom: 11px;" title="所属类目">
          <a-form-item>
            <tag-select>
              <tag-select-option value="Category1">类目一</tag-select-option>
              <tag-select-option value="Category2">类目二</tag-select-option>
              <tag-select-option value="Category3">类目三</tag-select-option>
              <tag-select-option value="Category4">类目四</tag-select-option>
              <tag-select-option value="Category5">类目五</tag-select-option>
              <tag-select-option value="Category6">类目六</tag-select-option>
              <tag-select-option value="Category7">类目七</tag-select-option>
              <tag-select-option value="Category8">类目八</tag-select-option>
              <tag-select-option value="Category9">类目九</tag-select-option>
              <tag-select-option value="Category10">类目十</tag-select-option>
            </tag-select>
          </a-form-item>
        </standard-form-row>

        <standard-form-row grid title="owner">
          <a-row>
            <a-col :md="24">
              <a-form-item :wrapper-col="{ span: 24 }">
                <a-select
                  v-decorator="['owner']"
                  mode="multiple"
                  placeholder="选择 onwer"
                  style="max-width: 268px; width: 100%;"
                  @change="handleChange"
                >
                  <a-select-option v-for="item in owners" :key="item.id">{{ item.name }}</a-select-option>
                </a-select>
                <a class="list-articles-trigger" @click="setOwner">只看自己的</a>
              </a-form-item>
            </a-col>
          </a-row>
        </standard-form-row>

        <standard-form-row grid last title="其它选项">
          <a-row :gutter="16">
            <a-col :lg="10" :md="12" :sm="24" :xl="8" :xs="24">
              <a-form-item :wrapper-col="{ xs: 24, sm: 24, md: 12 }" label="活跃用户">
                <a-select placeholder="不限" style="max-width: 200px; width: 100%;">
                  <a-select-option value="李三">李三</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :lg="10" :md="12" :sm="24" :xl="8" :xs="24">
              <a-form-item :wrapper-col="{ xs: 24, sm: 24, md: 12 }" label="好评度">
                <a-select placeholder="不限" style="max-width: 200px; width: 100%;">
                  <a-select-option value="优秀">优秀</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
          </a-row>
        </standard-form-row>
      </a-form>
    </a-card>

    <a-card :bordered="false" style="margin-top: 24px;">
      <a-list
        :dataSource="data"
        :loading="loading"
        itemLayout="vertical"
        rowKey="id"
        size="large"
      >
        <a-list-item :key="item.id" slot="renderItem" slot-scope="item">
          <template slot="actions">
            <icon-text :text="item.star" type="star-o"/>
            <icon-text :text="item.like" type="like-o"/>
            <icon-text :text="item.message" type="message"/>
          </template>
          <a-list-item-meta>
            <a slot="title" href="https://vue.ant.design/">{{ item.title }}</a>
            <template slot="description">
              <span>
                <a-tag>Ant Design</a-tag>
                <a-tag>设计语言</a-tag>
                <a-tag>蚂蚁金服</a-tag>
              </span>
            </template>
          </a-list-item-meta>
          <article-list-content :avatar="item.avatar" :description="item.description" :href="item.href"
                                :owner="item.owner" :updateAt="item.updatedAt"/>
        </a-list-item>
        <div v-if="data.length > 0" slot="footer" style="text-align: center; margin-top: 16px;">
          <a-button :loading="loadingMore" @click="loadMore">加载更多</a-button>
        </div>
      </a-list>
    </a-card>
  </div>
</template>

<script>
import {ArticleListContent, StandardFormRow, TagSelect} from '@/components'
import IconText from './components/IconText'

const TagSelectOption = TagSelect.Option

const owners = [
  {
    id: 'wzj',
    name: '我自己'
  },
  {
    id: 'wjh',
    name: '吴家豪'
  },
  {
    id: 'zxx',
    name: '周星星'
  },
  {
    id: 'zly',
    name: '赵丽颖'
  },
  {
    id: 'ym',
    name: '姚明'
  }
]

export default {
  components: {
    TagSelect,
    TagSelectOption,
    StandardFormRow,
    ArticleListContent,
    IconText
  },
  data() {
    return {
      owners,
      loading: true,
      loadingMore: false,
      data: [],
      form: this.$form.createForm(this)
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    handleChange(value) {
      console.log(`selected ${value}`)
    },
    getList() {
      this.$http.get('/list/article').then(res => {
        console.log('res', res)
        this.data = res.result
        this.loading = false
      })
    },
    loadMore() {
      this.loadingMore = true
      this.$http.get('/list/article').then(res => {
        this.data = this.data.concat(res.result)
      }).finally(() => {
        this.loadingMore = false
      })
    },
    setOwner() {
      const {form: {setFieldsValue}} = this
      setFieldsValue({
        owner: ['wzj']
      })
    }
  }
}
</script>

<style lang="less" scoped>
.ant-pro-components-tag-select {
  /deep/ .ant-pro-tag-select .ant-tag {
    margin-right: 24px;
    padding: 0 8px;
    font-size: 14px;
  }
}

.list-articles-trigger {
  margin-left: 12px;
}
</style>
