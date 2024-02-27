<template>
    <div class="input">
        <input :class="[`${props.type}-custom`]" :type="props.type" :id="uid" :name="name" />
        <label :class="[`${props.type}-custom-label`]" :for="uid">{{ label }}</label>
    </div>
</template>

<script setup>
import { defineProps, watch, ref, defineEmits } from 'vue';
import _ from 'lodash';

// ------------------------------ Props ---------------------------------
const props = defineProps({
    type: {
        type: String,
        default: 'checkbox'
    },
    label: {
        type: String,
        default: null
    },
    modelValue: {},
    name: {
        type: String,
        default: null
    }
})

// ------------------------------ Khai báo biến --------------------------
const uid = _.uniqueId();
const inputValue = ref(props.modelValue);
const emits = defineEmits(['update:modelValue']);

// ------------------------------ Watcher --------------------------------

watch(() => props.modelValue, (newVal) => {
    inputValue.value = newVal;
})

watch(() => inputValue.value, (newVal) => {
    emits('update:modelValue', newVal);
})

// ------------------------------ Lifecycle ------------------------------

// ------------------------------ Hàm xử lý ------------------------------

</script>

<style scoped>
.checkbox-custom,
.radio-custom {
    opacity: 0;
    position: absolute;
}

.checkbox-custom,
.checkbox-custom-label,
.radio-custom,
.radio-custom-label {
    display: inline-flex;
    vertical-align: middle;
    cursor: pointer;
    gap: 6px;
}

.checkbox-custom-label,
.radio-custom-label {
    position: relative;
}

.checkbox-custom+.checkbox-custom-label:before,
.radio-custom+.radio-custom-label:before {
    content: '';
    background: #fff;
    border: 1px solid var(--color-border);
    display: inline-flex;
    vertical-align: middle;
    width: 18px;
    height: 18px;
    align-items: center;
    justify-content: center;
}

.checkbox-custom:hover+.checkbox-custom-label:before,
.radio-custom:hover+.radio-custom-label:before {
    border: 1px solid var(--color-primary);
}

.checkbox-custom:checked+.checkbox-custom-label:before {
    content: "\f00c";
    font-family: 'FontAwesome';
    background: var(--color-primary);
    color: #fff;
    font-size: 13px;
    border: 1px solid var(--color-primary);
}

.radio-custom+.radio-custom-label:before {
    border-radius: 50%;
}

.checkbox-custom+.checkbox-custom-label:before {
    border-radius: 4px;
}

.radio-custom:checked+.radio-custom-label:before {
    content: "\f111";
    font-family: 'FontAwesome';
    color: var(--color-primary);
    font-size: 13px;
    border: 1px solid var(--color-primary);
}
</style>