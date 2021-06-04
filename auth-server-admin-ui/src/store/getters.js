const getters = {
    isMobile: state => state.app.isMobile,
    lang: state => state.app.lang,
    theme: state => state.app.theme,
    color: state => state.app.color,
    welcome: state => state.user.welcome,
}

export default getters
