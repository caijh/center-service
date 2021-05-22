<template>
  <div>
    <v-chart
      :data="data"
      :forceFit="true"
      :height="height"
      :padding="0"
      :scale="scale"
      :width="width">
      <v-tooltip/>
      <v-interval
        :shape="['liquid-fill-gauge']"
        :tooltip="[
          'transfer*value',
          (transfer, value) => {
            return {
              name: transfer,
              value,
            };
          },
        ]"
        :v-style="{
          lineWidth: 10,
          opacity: 0.75
        }"
        color=""
        position="transfer*value"
      ></v-interval>
      <v-guide
        v-for="(row, index) in data"
        :key="index"
        :content="row.value + '%'"
        :position="{
          gender: row.transfer,
          value: 45
        }"
        :top="true"
        :v-style="{
          fontSize: 100,
          textAlign: 'center',
          opacity: 0.75,
        }"
        type="text"
      />
    </v-chart>
  </div>
</template>

<script>
export default {
  name: 'Liquid',
  props: {
    height: {
      type: Number,
      default: 0
    },
    width: {
      type: Number,
      default: 0
    }
  }
}
</script>

<style scoped>

</style>
