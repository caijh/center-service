<template>
  <a-popover
    v-model="visible"
    :arrowPointAtCenter="true"
    :autoAdjustOverflow="true"
    :getPopupContainer="() => $refs.noticeRef.parentElement"
    :overlayStyle="{ width: '300px', top: '50px' }"
    overlayClassName="header-notice-wrapper"
    placement="bottomRight"
    trigger="click"
  >
    <template slot="content">
      <a-spin :spinning="loading">
        <a-tabs>
          <a-tab-pane key="1" tab="通知">
            <a-list>
              <a-list-item>
                <a-list-item-meta description="一年前" title="你收到了 14 份新周报">
                  <a-avatar slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"
                            style="background-color: white"/>
                </a-list-item-meta>
              </a-list-item>
              <a-list-item>
                <a-list-item-meta description="一年前" title="你推荐的 曲妮妮 已通过第三轮面试">
                  <a-avatar slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png"
                            style="background-color: white"/>
                </a-list-item-meta>
              </a-list-item>
              <a-list-item>
                <a-list-item-meta description="一年前" title="这种模板可以区分多种通知类型">
                  <a-avatar slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/kISTdvpyTAhtGxpovNWd.png"
                            style="background-color: white"/>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </a-tab-pane>
          <a-tab-pane key="2" tab="消息">
            123
          </a-tab-pane>
          <a-tab-pane key="3" tab="待办">
            123
          </a-tab-pane>
        </a-tabs>
      </a-spin>
    </template>
    <span ref="noticeRef" class="header-notice" style="padding: 0 18px" @click="fetchNotice">
      <a-badge count="12">
        <a-icon style="font-size: 16px; padding: 4px" type="bell"/>
      </a-badge>
    </span>
  </a-popover>
</template>

<script>
export default {
  name: 'HeaderNotice',
  data() {
    return {
      loading: false,
      visible: false
    }
  },
  methods: {
    fetchNotice() {
      if (!this.visible) {
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 2000)
      } else {
        this.loading = false
      }
      this.visible = !this.visible
    }
  }
}
</script>

<style lang="css">
.header-notice-wrapper {
  top: 50px !important;
}
</style>
<style lang="less" scoped>
.header-notice {
  display: inline-block;
  transition: all 0.3s;

  span {
    vertical-align: initial;
  }
}
</style>
