<template>
  <a-result status="403" sub-title="Sorry, you don't have access to this page." title="403">
    <template #extra>
      <a-button type="primary" @click="toHome">
        Back Home
      </a-button>
    </template>
  </a-result>
</template>

<script>
export default {
  name: 'Exception403',
  methods: {
    toHome() {
      this.$router.push({path: '/'})
    }
  }
}
</script>
