/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from './plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core';
import {faUser, faServer, faLock, fas} from '@fortawesome/free-solid-svg-icons';
import router from "./router";

const app = createApp(App)
library.add(fas);
registerPlugins(app)
app.component('font-awesome-icon', FontAwesomeIcon);
app.mount('#app')
app.use(router);
