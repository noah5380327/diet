<template>
  <q-dialog v-model="modalVisible">
    <q-card class="table-form-container">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6">Exercise Record Form</div>
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>

      <q-card-section>
        <q-form @submit="onSubmit" class="q-gutter-md">
          <q-input
            outlined
            v-model="name"
            label="Name"
            :rules="[(val: any) => (val && val.length > 0) || 'Name is required']"
          />

          <q-input
            v-model.number="duration"
            label="Duration（Minutes）"
            type="number"
            outlined
            :rules="[(val) => val > 0 || 'Duration must be greater than 0']"
          />

          <q-input
            v-model.number="calories"
            label="Calories"
            type="number"
            outlined
            :rules="[(val) => val > 0 || 'Calories must be greater than 0']"
          />

          <div>
            <q-btn
              no-caps
              label="Submit"
              type="submit"
              color="primary"
              class="full-width"
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { useQuasar } from 'quasar';
import {
  createExerciseRecord,
  createExerciseRecordForStudent,
  updateExerciseRecordById,
} from 'src/server/apis/exerciseRecord';
import { ExerciseRecord } from 'src/server/interfaces/exerciseRecord';

defineOptions({
  name: 'ExerciseRecordCreateForm',
});

interface ExerciseRecordCreateFormProps {
  visible: boolean;
  info?: ExerciseRecord;
  forStudent?: boolean;
  studentId?: string;
}

interface ExerciseRecordCreateFormEmit {
  (e: 'update:visible', v: boolean): void;
  (e: 'success'): void;
}

const props = defineProps<ExerciseRecordCreateFormProps>();
const emit = defineEmits<ExerciseRecordCreateFormEmit>();

const $q = useQuasar();

const modalVisible = computed({
  get: () => {
    return props.visible;
  },
  set: (newValue: boolean) => {
    emit('update:visible', newValue);
  },
});

const name = ref<string>('');
const duration = ref<number | null>(null);
const calories = ref<number | null>(null);

function resetData() {
  name.value = '';
  duration.value = null;
  calories.value = null;
}

function afterSubmit() {
  modalVisible.value = false;
  $q.notify({
    type: 'positive',
    message: 'submit success',
  });
  emit('success');
}

function onSubmit() {
  if (duration.value && calories.value) {
    if (props.info) {
      updateExerciseRecordById(props.info.id, {
        name: name.value,
        duration: duration.value,
        calories: calories.value,
      }).then(() => {
        afterSubmit();
      });
    } else if (props.forStudent) {
      createExerciseRecordForStudent({
        name: name.value,
        duration: duration.value,
        calories: calories.value,
        studentId: props.studentId || '',
      }).then(() => {
        afterSubmit();
      });
    } else {
      createExerciseRecord({
        name: name.value,
        duration: duration.value,
        calories: calories.value,
      }).then(() => {
        afterSubmit();
      });
    }
  }
}

watch(
  modalVisible,
  () => {
    if (modalVisible.value) {
      if (props.info) {
        name.value = props.info.name;
        duration.value = props.info.duration;
        calories.value = props.info.calories;
      } else {
        resetData();
      }
    }
  },
  {
    immediate: true,
  }
);
</script>

<style scoped lang="scss">
.table-form-container {
  width: 700px;
  max-width: 80vw;
}
</style>
