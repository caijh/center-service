import {createApp} from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';

import router from './router'
import store from './store'
import i18n from './locales'

import 'ant-design-vue/dist/antd.css';

const app = createApp(App);
app.config.productionTip = false;
app.use(Antd)
app.use(router)
app.use(store)
app.use(i18n)
app.mount('#app')
