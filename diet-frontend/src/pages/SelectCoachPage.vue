<template>
  <q-page class="q-pa-md flex flex-center">
    <q-card
      class="q-pa-lg shadow-2 rounded-borders"
      style="max-width: 500px; width: 100%"
    >
      <q-card-section>
        <div class="text-h6 text-center">Select Your Coach</div>
      </q-card-section>

      <q-form @submit="submitRequest">
        <q-select
          v-model="coachId"
          :options="coachOptions"
          label="please select your coach"
          option-label="username"
          option-value="id"
          emit-value
          map-options
          outlined
          :rules="[(val) => !!val || 'please select one coach']"
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
import { ref, onMounted } from 'vue';
import { useQuasar } from 'quasar';
import { getAllCoaches } from 'src/server/apis/user';
import { User } from 'src/server/interfaces/user';
import {
  createCoachStudent,
  getCurrentCoachStudent,
  updateCoachStudentById,
} from 'src/server/apis/coachStudent';

const $q = useQuasar();

const id = ref('');
const coachId = ref('');
const coachOptions = ref<User[]>([]);

function initData() {
  getAllCoaches().then((res) => {
    coachOptions.value = res;
    getCurrentCoachStudent().then((cRes) => {
      if (cRes) {
        id.value = cRes.id;
        coachId.value = cRes.coachId;
      }
    });
  });
}

function submitRequest() {
  if (id.value) {
    updateCoachStudentById(id.value, {
      coachId: coachId.value,
    }).then(() => {
      afterSubmit();
    });
  } else {
    createCoachStudent({
      coachId: coachId.value,
    }).then(() => {
      afterSubmit();
    });
  }
}

function afterSubmit() {
  $q.notify({
    type: 'positive',
    message: 'submit success, wait for coach to confirm',
  });
  initData();
}

onMounted(() => {
  initData();
});
</script>

<style scoped lang="scss"></style>
