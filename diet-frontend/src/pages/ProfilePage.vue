<template>
  <q-page class="q-pa-md flex flex-center">
    <q-card
      class="q-pa-lg shadow-2 rounded-borders"
      style="max-width: 500px; width: 100%"
    >
      <q-card-section>
        <div class="text-h6 text-center">Fill in your fitness information</div>
      </q-card-section>

      <q-form @submit="submitForm">
        <q-input
          v-model="form.age"
          label="Age"
          type="number"
          :rules="[(val) => val > 0 || 'Please enter your age']"
          outlined
        />
        <q-input
          v-model="form.initWeight"
          label="Init Weight（kg）"
          type="number"
          :rules="[(val) => val > 0 || 'Init weight must greater than 0']"
          outlined
        />
        <q-input
          v-model="form.targetWeight"
          label="Target Weight（kg）"
          type="number"
          :rules="[(val) => val > 0 || 'Target weight must greater than 0']"
          outlined
        />
        <q-input
          v-model="form.trainingDuration"
          label="Training Duration（Unit: day）"
          type="number"
          :rules="[(val) => val > 0 || 'Duration must greater than 0']"
          outlined
        />
        <q-select
          v-model="form.goal"
          label="Goal"
          :options="goalOptions"
          emit-value
          map-options
          outlined
          :rules="[(val) => !!val || 'Please select one goal']"
        />

        <q-btn
          label="Submit"
          color="primary"
          type="submit"
          class="full-width"
        />
      </q-form>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useQuasar } from 'quasar';
import {
  createStudentProfile,
  getCurrentStudentProfile,
  updateStudentProfileById,
} from 'src/server/apis/studentProfile';

defineOptions({
  name: 'ProfilePage',
});

const $q = useQuasar();

const id = ref('');
const form = ref<{
  age: number | null;
  initWeight: number | null;
  targetWeight: number | null;
  trainingDuration: number | null;
  goal: string | null;
}>({
  age: null,
  initWeight: null,
  targetWeight: null,
  trainingDuration: null,
  goal: null,
});

const goalOptions = [
  { label: 'Lose Weight', value: 'LOSE_WEIGHT' },
  { label: 'Gain Muscle', value: 'GAIN_MUSCLE' },
  { label: 'Maintain', value: 'MAINTAIN' },
];

function submitForm() {
  if (
    form.value.age &&
    form.value.initWeight &&
    form.value.targetWeight &&
    form.value.trainingDuration &&
    form.value.goal
  ) {
    if (id.value) {
      updateStudentProfileById(id.value, {
        age: form.value.age,
        initWeight: form.value.initWeight,
        targetWeight: form.value.targetWeight,
        trainingDuration: form.value.trainingDuration,
        goal: form.value.goal,
      }).then(() => {
        afterSubmit();
      });
    } else {
      createStudentProfile({
        age: form.value.age,
        initWeight: form.value.initWeight,
        targetWeight: form.value.targetWeight,
        trainingDuration: form.value.trainingDuration,
        goal: form.value.goal,
      }).then(() => {
        afterSubmit();
      });
    }
  }
}

function afterSubmit() {
  $q.notify({ type: 'positive', message: 'submit success' });
  initData();
}

function initData() {
  getCurrentStudentProfile().then((res) => {
    if (res) {
      id.value = res.id;
      form.value = {
        age: res.age,
        initWeight: res.initWeight,
        targetWeight: res.targetWeight,
        trainingDuration: res.trainingDuration,
        goal: res.goal,
      };
    }
  });
}

onMounted(() => {
  initData();
});
</script>

<style scoped lang="scss"></style>
