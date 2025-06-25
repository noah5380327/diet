<template>
  <q-page class="q-pa-md">
    <div class="text-h5 text-center q-mb-md">Bound student list</div>

    <q-table
      flat
      bordered
      :rows="students"
      :columns="columns"
      row-key="id"
      v-if="students.length"
    >
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td key="name" :props="props">
            {{ props.row.student.username }}
          </q-td>
          <q-td key="createdTime" :props="props">
            {{ props.row.createdTime }}
          </q-td>
          <q-td key="action" :props="props">
            <q-btn-group>
              <q-btn
                size="sm"
                color="primary"
                label="Recipe"
                @click="onRecipe(props.row)"
              />
              <q-btn
                size="sm"
                color="secondary"
                label="Upload"
                @click="onExercise(props.row)"
              />
            </q-btn-group>
          </q-td>
        </q-tr>
      </template>
    </q-table>

    <q-banner v-else class="bg-grey-2 text-grey-8">
      No bound students.
    </q-banner>

    <exercise-record-form
      v-model:visible="exerciseRecordModalVisible"
      for-student
      :student-id="selectStudentId"
      @success="initData"
    />

    <view-recipe
      v-model:visible="viewRecipeModalVisible"
      :student-id="selectStudentId"
    />
  </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { QTableColumn } from 'quasar';
import { CoachStudentWithDetail } from 'src/server/interfaces/coachStudent';
import { getAllAcceptedCoachStudents } from 'src/server/apis/coachStudent';
import ExerciseRecordForm from 'src/components/ExerciseRecordForm.vue';
import ViewRecipe from 'src/components/ViewRecipe.vue';

const students = ref<CoachStudentWithDetail[]>([]);
const exerciseRecordModalVisible = ref<boolean>(false);
const selectStudentId = ref('');
const viewRecipeModalVisible = ref<boolean>(false);

const columns: QTableColumn[] = [
  {
    name: 'name',
    label: 'Name',
    align: 'left',
    field: (row: CoachStudentWithDetail) => row,
    format: (val: CoachStudentWithDetail) => `${val.student.username}`,
  },
  {
    name: 'createdTime',
    label: 'Binding Time',
    align: 'left',
    field: 'createdTime',
  },
  {
    name: 'action',
    label: 'Action',
    align: 'left',
    field: (row: CoachStudentWithDetail) => row,
    style: 'width: 300px',
  },
];

function initData() {
  getAllAcceptedCoachStudents().then((res) => {
    students.value = res;
  });
}

function onRecipe(student: CoachStudentWithDetail) {
  selectStudentId.value = student.studentId;
  viewRecipeModalVisible.value = true;
}

function onExercise(student: CoachStudentWithDetail) {
  selectStudentId.value = student.studentId;
  exerciseRecordModalVisible.value = true;
}

onMounted(() => {
  initData();
});
</script>

<style scoped lang="scss"></style>
