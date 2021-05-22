<template>
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
      <article-list-content :avatar="item.avatar" :description="item.description" :href="item.href" :owner="item.owner"
                            :updateAt="item.updatedAt"/>
    </a-list-item>
    <div v-if="data.length > 0" slot="footer" style="text-align: center; margin-top: 16px;">
      <a-button :loading="loadingMore" @click="loadMore">加载更多</a-button>
    </div>
  </a-list>
</template>

<script>
import {ArticleListContent} from '@/components'
import IconText from '@/views/list/search/components/IconText'

export default {
  name: 'Article',
  components: {
    IconText,
    ArticleListContent
  },
  data() {
    return {
      loading: true,
      loadingMore: false,
      data: []
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
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
    }
  }
}
</script>

<style scoped>

</style>
