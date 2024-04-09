import { createApp } from 'vue'
import App from './App.vue'
import router from './routers/router';
import "bootstrap";

const app = createApp(App);

// Components
import BaseButton from '@/components/button/BaseButton.vue';
import BaseInput from '@/components/input/BaseInput.vue';
import BaseDialog from '@/components/dialog/BaseDialog.vue';
import BaseRating from '@/components/rating/BaseRating.vue';
import BaseCheckbox from '@/components/input/BaseCheckbox.vue';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import BaseDatepicker from '@/components/input/BaseDatepicker.vue';
import DotsLoader from './components/loading/DotsLoader.vue';

app.component('b-button', BaseButton);
app.component('b-input', BaseInput);
app.component('b-dialog', BaseDialog);
app.component('b-rating', BaseRating);
app.component('b-checkbox', BaseCheckbox);
app.component('VueDatePicker', VueDatePicker);
app.component('b-datepicker', BaseDatepicker);
app.component('dots-loader', DotsLoader);

// Method
import helper from './helpers/helper';
import _ from 'lodash';

app.config.globalProperties.$helper = helper;
app.config.globalProperties.$_ = _;

// Vuetify
import vuetify from './plugins/vuetify'

app.use(router);
app.use(vuetify);
app.mount('#app');