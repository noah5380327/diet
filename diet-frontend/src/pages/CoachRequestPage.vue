<template>
  <q-page class="q-pa-md">
    <div class="text-h5 text-center q-mb-md">Student Binding Request</div>

    <q-table
      flat
      bordered
      :rows="requests"
      :columns="columns"
      row-key="id"
      v-if="requests.length"
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
                color="positive"
                label="Accept"
                @click="acceptRequest(props.row)"
              />
              <q-btn
                size="sm"
                color="negative"
                label="Reject"
                @click="rejectRequest(props.row)"
              />
            </q-btn-group>
          </q-td>
        </q-tr>
      </template>
    </q-table>

    <q-banner v-else class="bg-grey-2 text-grey-8">
      No pending student requests.
    </q-banner>
  </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { QTableColumn, useQuasar } from 'quasar';
import { CoachStudentWithDetail } from 'src/server/interfaces/coachStudent';
import {
  acceptCoachStudentById,
  rejectCoachStudentById,
  getAllPendingCoachStudents,
} from 'src/server/apis/coachStudent';

const $q = useQuasar();

const requests = ref<CoachStudentWithDetail[]>([]);

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
    label: 'Request Time',
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
  getAllPendingCoachStudents().then((res) => {
    requests.value = res;
  });
}

function acceptRequest(row: CoachStudentWithDetail) {
  acceptCoachStudentById(row.id).then(() => {
    $q.notify({
      type: 'positive',
      message: `Accepted ${row.student.username}'s Request`,
    });
    initData();
  });
}

function rejectRequest(row: CoachStudentWithDetail) {
  rejectCoachStudentById(row.id).then(() => {
    $q.notify({
      type: 'info',
      message: `Rejected ${row.student.username}'s Request`,
    });
    initData();
  });
}

onMounted(() => {
  initData();
});
</script>

<style scoped lang="scss"></style>
