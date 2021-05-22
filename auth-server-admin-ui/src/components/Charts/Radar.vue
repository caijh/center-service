<template>
  <v-chart :data="data" :forceFit="true" :padding="[20, 20, 95, 20]" :scale="scale" height="400">
    <v-tooltip></v-tooltip>
    <v-axis :dataKey="axis1Opts.dataKey" :grid="axis1Opts.grid" :line="axis1Opts.line" :tickLine="axis1Opts.tickLine"/>
    <v-axis :dataKey="axis2Opts.dataKey" :grid="axis2Opts.grid" :line="axis2Opts.line" :tickLine="axis2Opts.tickLine"/>
    <v-legend :offset="30" dataKey="user" marker="circle"/>
    <v-coord radius="0.8" type="polar"/>
    <v-line :size="2" color="user" position="item*score"/>
    <v-point :size="4" color="user" position="item*score" shape="circle"/>
  </v-chart>
</template>

<script>
const axis1Opts = {
  dataKey: 'item',
  line: null,
  tickLine: null,
  grid: {
    lineStyle: {
      lineDash: null
    },
    hideFirstLine: false
  }
}
const axis2Opts = {
  dataKey: 'score',
  line: null,
  tickLine: null,
  grid: {
    type: 'polygon',
    lineStyle: {
      lineDash: null
    }
  }
}

const scale = [
  {
    dataKey: 'score',
    min: 0,
    max: 80
  }, {
    dataKey: 'user',
    alias: '类型'
  }
]

export default {
  name: 'Radar',
  props: {
    data: {
      type: Array,
      default: null
    }
  },
  data() {
    return {
      axis1Opts,
      axis2Opts,
      scale
    }
  }
}
</script>

<style scoped>

</style>
