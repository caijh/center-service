import VueI18n from 'vue-i18n'

import en from './en'
import zh from './zh'

const VueI18n = new VueI18n({
    locale: 'en',
    messages: {
        en,
        zh,
    }
})

export default VueI18n
