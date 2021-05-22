<template>
  <div :class="prefixCls">
    <div :style="{ height: 46 }" class="chart-wrapper">
      <v-chart :data="dataSource" :force-fit="true" :height="100" :padding="[36, 0, 18, 0]" :scale="scale">
        <v-tooltip/>
        <v-smooth-line :size="2" position="x*y"/>
        <v-smooth-area position="x*y"/>
      </v-chart>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MiniSmoothArea',
  props: {
    prefixCls: {
      type: String,
      default: 'ant-pro-smooth-area'
    },
    scale: {
      type: [Object, Array],
      required: true
    },
    dataSource: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      height: 100
    }
  }
}
</script>

<style lang="less" scoped>
@import "smooth.area.less";
</style>
