<template>
  <q-page class="col q-pa-lg">
    <q-table
      title="Exercise Record"
      :rows="exerciseRecords"
      :columns="columns"
      row-key="id"
      :rows-per-page-options="rowsPerPageOptions"
    >
      <template v-slot:top-right>
        <div class="flex items-center">
          <q-btn
            icon="add"
            color="primary"
            class="q-ml-lg"
            label="Create"
            @click="onCreate"
          />
        </div>
      </template>
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td key="name" :props="props">
            {{ props.row.name }}
          </q-td>
          <q-td key="duration" :props="props">
            {{ props.row.duration }}
          </q-td>
          <q-td key="calories" :props="props">
            {{ props.row.calories }}
          </q-td>
          <q-td key="action" :props="props">
            <q-btn-group>
              <q-btn label="Edit" icon="edit" @click="onEdit(props.row)" />
              <q-btn
                label="Delete"
                icon="clear"
                color="negative"
                @click="onDelete(props.row)"
              />
            </q-btn-group>
          </q-td>
        </q-tr>
      </template>
    </q-table>

    <exercise-record-form
      v-model:visible="modalVisible"
      :info="info"
      @success="initData"
    />
  </q-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { QTableColumn, useQuasar } from 'quasar';
import ExerciseRecordForm from 'src/components/ExerciseRecordForm.vue';
import { ExerciseRecord } from 'src/server/interfaces/exerciseRecord';
import {
  deleteExerciseRecordById,
  getExerciseRecords,
} from 'src/server/apis/exerciseRecord';

defineOptions({
  name: 'ExerciseRecordPage',
});

const $q = useQuasar();

const columns: QTableColumn[] = [
  {
    name: 'name',
    label: 'Name',
    align: 'left',
    field: 'name',
    format: (val: string) => `${val}`,
  },
  {
    name: 'duration',
    label: 'Duration(Minutes)',
    align: 'left',
    field: 'duration',
  },
  {
    name: 'calories',
    label: 'Calories',
    align: 'left',
    field: 'calories',
  },
  {
    name: 'action',
    label: 'Action',
    align: 'left',
    field: (row: ExerciseRecord) => row,
    style: 'width: 300px',
  },
];

const exerciseRecords = ref<ExerciseRecord[]>([]);
const rowsPerPageOptions = ref<number[]>([12, 24, 48, 96]);
const modalVisible = ref<boolean>(false);
const info = ref<ExerciseRecord>();

function onCreate() {
  info.value = undefined;
  modalVisible.value = true;
}

function onEdit(value: ExerciseRecord) {
  info.value = value;
  modalVisible.value = true;
}

function onDelete(value: ExerciseRecord) {
  $q.dialog({
    title: 'Delete',
    message: 'Are you sure you want to delete it?',
    ok: {
      color: 'negative',
    },
    cancel: true,
    persistent: true,
  }).onOk(() => {
    deleteExerciseRecordById(value.id).then(() => {
      initData();
    });
  });
}

function initData() {
  getExerciseRecords().then((res) => {
    exerciseRecords.value = res;
  });
}

onMounted(() => {
  initData();
});
</script>

<style scoped lang="scss"></style>
