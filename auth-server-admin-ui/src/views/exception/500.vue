<template>
  <a-result status="500" sub-title="Sorry, the server is reporting an error." title="500">
    <template #extra>
      <a-button type="primary" @click="toHome">
        Back Home
      </a-button>
    </template>
  </a-result>
</template>

<script>
export default {
  name: 'Exception500',
  methods: {
    toHome() {
      this.$router.push({path: '/'})
    }
  }
}
</script>
